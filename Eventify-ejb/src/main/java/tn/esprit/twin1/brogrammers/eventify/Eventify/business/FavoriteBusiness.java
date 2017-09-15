package tn.esprit.twin1.brogrammers.eventify.Eventify.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.FavoriteBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.FavoriteBusinessRemote;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Favorite;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Wishlist;

/**
 * Session Bean implementation class FavoriteBusiness
 */
@Stateless
@LocalBean
public class FavoriteBusiness implements FavoriteBusinessRemote, FavoriteBusinessLocal {

 
	
	@PersistenceContext(unitName = "Eventify-ejb")
	EntityManager entityManager;

    public FavoriteBusiness() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addFavorite(Favorite favorite) {
		try {
			entityManager.persist(favorite);

		} catch (Exception e) {
			System.out.println("unable to add a favorite");
		}
		
	}

	@Override
	public boolean RemoveFavorite(int userId,int categoryId) {
		try {
			entityManager.remove(entityManager.merge(getFavoriteByUserAndCategory(userId, categoryId)));
			return true;
		} catch (Exception e) {
			System.out.println("Unable to delete favorite");
			return false;
		}
	}

	@Override
	public Favorite getFavoriteByUserAndCategory(int userId, int categoryId) {
		try {
			Query query = entityManager.
					createQuery("SELECT new Favorite(f.favoritePK,f.priority) FROM Favorite f"
							+ " WHERE f.favoritePK.categoryId=:categoryId AND f.favoritePK.userId=:userId")
					.setParameter("categoryId", categoryId)
					.setParameter("userId", userId);
			 return (Favorite) query.getSingleResult();

		} catch (Exception e) {
			System.err.println("Cant Find Favorite");
			return null; } 
		}

	@Override
	public List<Favorite> getFavoritesByUser(int userId) {
		try {
			Query query = entityManager.
					createQuery("SELECT new Favorite(f.favoritePK,f.priority) FROM Favorite f"
							+ " WHERE f.favoritePK.userId=:userId")
					.setParameter("userId", userId);
			 return query.getResultList();

		} catch (Exception e) {
			System.err.println("Cant Find Favorite");
			return new ArrayList<Favorite>();
		}
	} 
	

	

}
