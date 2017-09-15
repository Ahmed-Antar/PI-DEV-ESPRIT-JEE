package tn.esprit.twin1.brogrammers.eventify.Eventify.contracts;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Favorite;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Wishlist;

@Local
public interface FavoriteBusinessLocal {
	
	public void addFavorite(Favorite favorite);
	public boolean RemoveFavorite(int userId,int categoryId);
	public Favorite getFavoriteByUserAndCategory(int userId,int categoryId);
	public List<Favorite> getFavoritesByUser(int userId);
}
