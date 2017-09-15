package tn.esprit.twin1.brogrammers.eventify.Eventify.contracts;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.User;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Wishlist;

@Local
public interface UserBusinessLocal {
	
	public void createUser(User user);
	public boolean activateAccount(String confirmationToken);
	public User findUserById(int id);
	public List<User> findAllUsers();
	public void updateUser(User user);
	public void deleteUser(int id);
	public List<Wishlist> getMyWishlist(int idUser); //added by Ibra
	public String loginUser(String username, String pwd);
	public boolean uploadProfileImage(String imgToUpload);
	public boolean changePwd(User user, String oldPwd, String newPwd);
	

}
