package tn.esprit.twin1.brogrammers.eventify.Eventify.contracts;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Answer;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Event;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.User;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Wishlist;

@Local
public interface WishlistBusinessLocal {

	public void addEventToWishlist(Wishlist wishlist);
	public boolean RemoveEventFromWishlist(int userId,int eventId);
	public List<Wishlist> getWishlistByUserId(int id);
	public List<Wishlist> getWishlistByEventId(int id);
	public Wishlist getWishlistByEventIdAndUserId(int userId,int eventId);

	
}
