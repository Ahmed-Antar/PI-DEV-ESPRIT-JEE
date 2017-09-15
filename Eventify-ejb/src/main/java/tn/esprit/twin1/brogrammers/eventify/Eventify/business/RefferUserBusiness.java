package tn.esprit.twin1.brogrammers.eventify.Eventify.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.RefferUserBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.RefferUserBusinessRemote;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.UserBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.ReferrelUser;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.User;


/**
 * Session Bean implementation class RefferUserBusiness
 */
@Stateless
@LocalBean
public class RefferUserBusiness implements RefferUserBusinessRemote, RefferUserBusinessLocal {

    /**
     * Default constructor. 
     */
	

	@PersistenceContext(unitName = "Eventify-ejb")
	EntityManager entityManager;
	
	 @EJB
     UserBusinessLocal userBusiness;


	@Override
	public void ChooseReferred(ReferrelUser Reffered) {
		entityManager.persist(Reffered);
		
	}


	@Override
	public boolean RemoveReferred(int idReferred) {
		entityManager.remove(entityManager.merge(FindReferralByIdReferred(idReferred)));
		return true;
	}

	
	@Override
	public List<ReferrelUser> FindAll() {
		
		Query query = entityManager.
				createQuery("SELECT new ReferrelUser(r.referrelUserPK,r.dateInvitation,r.stateInvitation)"
						+ " FROM ReferrelUser r");
		
		return query.getResultList();	
	}
	
	
	@Override
	public List<User> FindAllReferrals() {
		
		
		
		List<ReferrelUser> RU = FindAll();
		
		List<User> users = new ArrayList<User>();
		
		for (ReferrelUser referrelUser : RU) {
			
			
			users.add(userBusiness.findUserById(referrelUser.getReferrelUserPK().getIdUserReferral()));
			
		}
		
		
		return users;
	}


	@Override
	public void updateReffered(ReferrelUser Referred) {
		
		entityManager.merge(Referred);
		
	}


	@Override
	public List<ReferrelUser> FindReferredsByIdReferral(int idReferral) 
	{
		try {
			
			Query query =  entityManager.
					createQuery("SELECT new ReferrelUser(r.referrelUserPK,r.dateInvitation,r.stateInvitation)"
							+ " FROM ReferrelUser r WHERE r.referrelUserPK.idUserReferral=:param")
					.setParameter("param", idReferral);
			System.out.println("ReferrelUser finded ");
			 return query.getResultList();

		} catch (Exception e) {
			System.err.println("Cant Find ReferrelUser");
			return new ArrayList<ReferrelUser>();
		}
	}

	
	@Override
	public User FindReferralByIdReferred(int idReferred) 
		{
			return userBusiness.findUserById(idReferred);
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
