package tn.esprit.twin1.brogrammers.eventify.Eventify.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.WishlistBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.WishlistBusinessRemote;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Answer;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Event;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.User;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Wishlist;

/**
 * Session Bean implementation class WishlistBusiness
 */
@Stateless
public class WishlistBusiness implements WishlistBusinessRemote, WishlistBusinessLocal {

	@PersistenceContext(unitName = "Eventify-ejb")
	EntityManager entityManager;

	@Override
	public void addEventToWishlist(Wishlist wishlist) {
		entityManager.persist(wishlist);
		
	}

	//A Verifier
	@Override
	public boolean RemoveEventFromWishlist(int userId,int eventId) {
		
		if(getWishlistByEventIdAndUserId(userId, eventId)!=null)
		{
			entityManager.remove(entityManager.merge(getWishlistByEventIdAndUserId(userId, eventId)));;
			return true;
		}
		else 
		return false;
			
		
		
	}

	@Override
	public List<Wishlist> getWishlistByUserId(int id) {
		try {
			Query query = entityManager.
					createQuery("SELECT new Wishlist(w.wishlistPK,w.dateAdding) FROM Wishlist w WHERE w.wishlistPK.userId=:param")
					.setParameter("param", id);
			 return query.getResultList();

		} catch (Exception e) {
			System.err.println("Cant Find Wishlist");
			return new ArrayList<Wishlist>();
		}
	}

	@Override
	public List<Wishlist> getWishlistByEventId(int id) {
		try {
			Query query = entityManager.
					createQuery("SELECT new Wishlist(w.wishlistPK,w.dateAdding) FROM Wishlist w WHERE w.wishlistPK.eventId=:param")
					.setParameter("param", id);
			 return query.getResultList();

		} catch (Exception e) {
			System.err.println("Cant Find Wishlist");
			return new ArrayList<Wishlist>();
		}
	}

	@Override
	public Wishlist getWishlistByEventIdAndUserId(int userId,int eventId) {
		try {
			Query query = entityManager.
					createQuery("SELECT new Wishlist(w.wishlistPK,w.dateAdding) FROM Wishlist w"
							+ " WHERE w.wishlistPK.eventId=:eventId AND w.wishlistPK.userId=:userId")
					.setParameter("eventId", eventId)
			.setParameter("userId", userId);
			 return (Wishlist) query.getSingleResult();

		} catch (Exception e) {
			System.err.println("Cant Find Wishlist");
			return null; } 
		} 
	

    

}
