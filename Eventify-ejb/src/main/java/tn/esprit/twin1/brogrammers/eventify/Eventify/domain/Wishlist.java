package tn.esprit.twin1.brogrammers.eventify.Eventify.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Wishlist
 *
 */
@Entity


public class Wishlist implements Serializable {

	private WishlistPK wishlistPK;
	private Date dateAdding;
	private User user;
	private Event event;
	
	private static final long serialVersionUID = 1L;

	public Wishlist() {
		super();
	}
	
	public Wishlist(Event event,User user){
		this.event =event;
		this.user =user;
		this.dateAdding= new Date();
	}
	
	
  
	
	
	public Wishlist(WishlistPK wishlistPK, Date dateAdding) {
		super();
		this.wishlistPK = wishlistPK;
		this.dateAdding = dateAdding;
	}

	public Wishlist(Date dateAdding, User user, Event event) {
		super();
		this.dateAdding = dateAdding;
		this.user = user;
		this.event = event;
	}

	
	
	public Wishlist(WishlistPK wishlistPK, Date dateAdding, User user, Event event) {
		super();
		this.wishlistPK = wishlistPK;
		this.dateAdding = dateAdding;
		this.user = user;
		this.event = event;
	}

	public Wishlist(Date dateAdding) {
		super();
		this.dateAdding = dateAdding;
	}

	@EmbeddedId
	public WishlistPK getWishlistPK() {
		return wishlistPK;
	}
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "userId",referencedColumnName="id", updatable = false, insertable = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "eventId",referencedColumnName="id", updatable = false, insertable = false)
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public void setWishlistPK(WishlistPK wishlistPK) {
		this.wishlistPK = wishlistPK;
	}

	public Date getDateAdding() {
		return this.dateAdding;
	}

	public void setDateAdding(Date dateAdding) {
		this.dateAdding = dateAdding;
	}
   
}
