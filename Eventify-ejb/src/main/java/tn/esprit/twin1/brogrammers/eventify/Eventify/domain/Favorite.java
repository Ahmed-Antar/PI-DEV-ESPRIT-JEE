package tn.esprit.twin1.brogrammers.eventify.Eventify.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Favorite implements Serializable {

	private FavoritePK favoritePK;
	private int priority;
	private User user;
	private Category category;
	
	private static final long serialVersionUID = 1L;

	public Favorite(FavoritePK favoritePK, int priority) {
		super();
		this.favoritePK = favoritePK;
		this.priority = priority;
	}

	
	
	public Favorite(int priority, User user, Category category) {
		super();
		this.priority = priority;
		this.user = user;
		this.category = category;
	}



	public Favorite(FavoritePK favoritePK, int priority, User user, Category category) {
		super();
		this.favoritePK = favoritePK;
		this.priority = priority;
		this.user = user;
		this.category = category;
	}



	public Favorite() {
		super();
	}

	@EmbeddedId
	public FavoritePK getFavoritePK() {
		return favoritePK;
	}

	public void setFavoritePK(FavoritePK favoritePK) {
		this.favoritePK = favoritePK;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
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
	@JoinColumn(name = "categoryId",referencedColumnName="id", updatable = false, insertable = false)
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	
	
}
