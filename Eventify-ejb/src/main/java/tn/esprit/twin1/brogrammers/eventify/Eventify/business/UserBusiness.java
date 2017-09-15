package tn.esprit.twin1.brogrammers.eventify.Eventify.business;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.UserBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.UserBusinessRemote;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.User;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Wishlist;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.enumeration.AccountState;
import tn.esprit.twin1.brogrammers.eventify.Eventify.util.AuthJWT;
import tn.esprit.twin1.brogrammers.eventify.Eventify.util.FTPProvider;
import tn.esprit.twin1.brogrammers.eventify.Eventify.util.FaceCognitive;
import tn.esprit.twin1.brogrammers.eventify.Eventify.util.MD5Hash;

/**
 * Session Bean implementation class UserBusiness
 * 
 * PS : DON'T FUCKING TOUCH THIS LOVELY BY HAKIM
 */
@Stateless
public class UserBusiness implements UserBusinessRemote, UserBusinessLocal {

	@PersistenceContext(unitName = "Eventify-ejb")
	EntityManager entityManager;

	public UserBusiness() {

	}

	@Override
	public void createUser(User user) {
		user.setPassword(MD5Hash.getMD5Hash(user.getPassword()));
		user.setAccountState(AccountState.NOTACTIVATED);
		String activationHashedCode = MD5Hash.getMD5Hash(user.getUsername() + user.getEmail());
		user.setConfirmationToken(activationHashedCode);
		user.setCreationDate(new Date());
		// Emailer.sendEmail("Eventify Account Activation",
		// "http://localhost:18080/Eventify-web/rest/users/confirm/"+activationHashedCode,
		// user.getEmail());
		entityManager.persist(user);
		// Emailer.SendEmail(user.getEmail(), "Eventify Account Activation",
		// EmailTemplate.activiationTemplate("http://localhost:18080/Eventify-web/rest/users/confirm/"+activationHashedCode));

	}

	@Override
	public boolean activateAccount(String confirmationToken) {
		Query query = entityManager.createQuery(
				"SELECT new User(u.id,u.confirmationToken) " + "FROM User u WHERE u.confirmationToken=:param");
		User u = null;
		try {
			u = (User) query.setParameter("param", confirmationToken).getSingleResult();

			if (u != null && u.getConfirmationToken().equals(confirmationToken)) {
				User userTochange = findUserById(u.getId());
				userTochange.setAccountState(AccountState.ACTIVATED);
				String faceListName = userTochange.getUsername();
				System.out.println("\n\n\n\n\n\n\n\n faceListName :  " + faceListName + "\n\n\n\n\n\n\n\n ");
				FaceCognitive.CreateFaceList(faceListName);
				FaceCognitive.AddFaceToList("http://s.plurielles.fr/mmdia/i/16/8/brad-pitt-2564168_2041.jpg",
						faceListName);// To change get Image with ftp
				updateUser(userTochange);
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.out.println("\n\n\n\n\n\n confirmationToken Not Found | User Not Set \n\n\n\n\n\n ");
			return false;
		}

	}

	@Override
	public User findUserById(int id) {
		Query query = entityManager.createQuery(
				"SELECT new User(u.id,u.firstName,u.lastName,u.username,u.profileImage,u.numTel,u.email,u.password,u.creationDate,u.loyaltyPoint,u.accountState,u.confirmationToken) "
						+ "FROM User u WHERE u.id=:param");
		return (User) query.setParameter("param", id).getSingleResult();
	}

	@Override
	public List<User> findAllUsers() {
		Query query = entityManager.createQuery(
				"SELECT new User(u.id,u.firstName,u.lastName,u.username,u.profileImage,u.numTel,u.email,u.password,u.creationDate,u.loyaltyPoint,u.accountState,u.confirmationToken) "
						+ "FROM User u");
		return (List<User>) query.getResultList();
	}

	@Override
	public void updateUser(User user) {

		entityManager.merge(user);

	}

	@Override
	public void deleteUser(int id) {
		// entityManager.remove(findUserById(id));
		entityManager.remove(entityManager.find(User.class, id));

	}

	@Override
	public String loginUser(String username, String pwd) {

			String hashedPwd = MD5Hash.getMD5Hash(pwd);
			Query query = entityManager.createQuery(
					"SELECT new User(u.id,u.firstName,u.lastName,u.username,u.profileImage,u.numTel,u.email,u.country,u.password,u.creationDate,u.loyaltyPoint,u.accountState,u.confirmationToken) "
							+ "FROM User u WHERE ( (u.username=:uname OR u.email=:uname) AND u.password=:upwd) ");
			User userLogged = (User) query.setParameter("uname", username).setParameter("upwd", hashedPwd)
					.getSingleResult();

			String vf = AuthJWT.SignJWT("User", userLogged);
			// AuthJWT.VerifyJWT(vf);

			return vf;
		



	}



	@Override
	public List<Wishlist> getMyWishlist(int idUser) {
		Query query = entityManager
				.createQuery("SELECT new Wishlist(w.dateAdding) FROM User u JOIN u.wishlists w WHERE u.id=:param");
		return query.setParameter("param", idUser).getResultList();
	}

	@Override
	public boolean uploadProfileImage(String imgToUpload) {

		if (FTPProvider.UploadImageToFTP(imgToUpload)) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean changePwd(User user, String oldPwd, String newPwd) {
		System.out.println("\n\n\n\n\n\n\n\n u : "+user.getId()+"\n\n\n\n\n\n\n\n\n");
		Query query = entityManager.createQuery(
				"SELECT new User(u.id,u.firstName,u.lastName,u.username,u.profileImage,u.numTel,u.email,u.password,u.creationDate,u.loyaltyPoint,u.accountState,u.confirmationToken) "
						+ "FROM User u WHERE  (u.id=:uid)");
		User userToChnage = (User) query.setParameter("uid", user.getId()).getSingleResult();
		if (user.getPassword().equals(MD5Hash.getMD5Hash(oldPwd))) {
			user.setPassword(MD5Hash.getMD5Hash(newPwd));
			entityManager.merge(user);
			return true;
		}
		return false;

	}

}
