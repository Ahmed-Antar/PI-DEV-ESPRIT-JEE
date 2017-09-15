package tn.esprit.twin1.brogrammers.eventify.Eventify.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category implements Serializable{

	private int id;
	private String categoryName;
	
	private static final long serialVersionUID = 1L;

	
	private List<Favorite> favorites;
	private List<Event> events;
	
	
	
	public Category() {
		super();
	}
	public Category(String categoryName) {
		super();
		this.categoryName = categoryName;
	}
	
	
	
	public Category(int id, String categoryName) {
		super();
		this.id = id;
		this.categoryName = categoryName;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	@OneToMany(mappedBy="category", fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	public List<Favorite> getFavorites() {
		return favorites;
	}
	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}
	
	@OneToMany(mappedBy="category",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
	
	
	

	
}
