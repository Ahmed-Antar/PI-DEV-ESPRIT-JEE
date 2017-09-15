package tn.esprit.twin1.brogrammers.eventify.Eventify.contracts;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.User;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Wishlist;

@Remote
public interface WishlistBusinessRemote {
	public void addEventToWishlist(Wishlist wishlist);
	public boolean RemoveEventFromWishlist(int userId,int eventId);
	public List<Wishlist> getWishlistByUserId(int id);
	public List<Wishlist> getWishlistByEventId(int id);
	public Wishlist getWishlistByEventIdAndUserId(int userId,int eventId);

}
