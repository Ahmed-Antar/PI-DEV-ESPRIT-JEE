package tn.esprit.twin1.brogrammers.eventify.Eventify.domain;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.enumeration.EventCategory;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.enumeration.EventState;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.enumeration.EventType;

/**
 * Entity implementation class for Entity: Event
 *
 */
@Entity
@Table(name="MyEvent")
public class Event implements Serializable {

	private int id;
	private String title;
	private String theme;
	private Date startTime;
	private Date endTime;
	private double longitude;
	private double latitude;
	private int placeNumber;
	private EventType eventType; 
	//private EventCategory eventCategory;
	private int nbViews;
	//private int nbLikes;
	private Date createdAt;
	private String facebookLink;
	private String twitterLink;
	private EventState eventState;
	private String backgroundImage;
	private String email;
	private String phone;
	private double rateAvg;
	
	private List<Report> reports;
	
	
	private static final long serialVersionUID = 1L;
	
	/* 	Foreign KEY Start */
	private Organization organization;
	private Category category;
	private List<Media> medias;
	private List<Wishlist> wishlists;
	private List<Rate> rates;
	private List<Question> questions;
	private List<Task> tasks;
	private List<Ticket> tickets;
	private List<Comment> comments;
	private List<ReferrelUser> referrels;
	
	/* 	Foreign KEY END */

	//Event Type Conference ,Class,Seminar,Party ...
	//Event Category Business,Food Drink Spirituality Family , Education , Health

	
	
	
	public Event() {
		super();
	}	
	


	




	@OneToMany(mappedBy="event")
	public List<ReferrelUser> getReferrels() {
		return referrels;
	}









	public void setReferrels(List<ReferrelUser> referrels) {
		this.referrels = referrels;
	}









	public Event(int id, String title, String theme, Date startTime, Date endTime, double longitude, double latitude,
			int placeNumber, EventType eventType, Category category, int nbViews, Date createdAt,
			String facebookLink, String twitterLink, EventState eventState,
			String backgroundImage,String email,String phone, Organization organization) {
		super();
		this.id = id;
		this.title = title;
		this.theme = theme;
		this.startTime = startTime;
		this.endTime = endTime;
		this.longitude = longitude;
		this.latitude = latitude;
		this.placeNumber = placeNumber;
		this.eventType = eventType;
		this.category = category;
		this.nbViews = nbViews;
		this.createdAt = createdAt;
		this.facebookLink = facebookLink;
		this.twitterLink = twitterLink;
		this.eventState = eventState;
		this.backgroundImage = backgroundImage;
		this.email = email;
		this.phone = phone;

		this.organization = organization;
	}





	public Event(int id, String title, String theme, Date startTime, Date endTime, double longitude, double latitude,
			int placeNumber, EventType eventType, Category category, int nbViews, Date createdAt,
			String facebookLink, String twitterLink, EventState eventState,
			String backgroundImage,String email,String phone) {
		super();
		this.id = id;
		this.title = title;
		this.theme = theme;
		this.startTime = startTime;
		this.endTime = endTime;
		this.longitude = longitude;
		this.latitude = latitude;
		this.placeNumber = placeNumber;
		this.eventType = eventType;
		this.category = category;
		this.nbViews = nbViews;
		this.createdAt = createdAt;
		this.facebookLink = facebookLink;
		this.twitterLink = twitterLink;
		this.eventState = eventState;
		this.backgroundImage = backgroundImage;
		this.email = email;
		this.phone = phone;


	}



	public Event(String title, String theme, Date startTime, Date endTime, double longitude, double latitude,
			int placeNumber, EventType eventType, Category category, int nbViews, Date createdAt,
			String facebookLink, String twitterLink, EventState eventState,
			String backgroundImage,String email,String phone) {
		super();
		this.title = title;
		this.theme = theme;
		this.startTime = startTime;
		this.endTime = endTime;
		this.longitude = longitude;
		this.latitude = latitude;
		this.placeNumber = placeNumber;
		this.eventType = eventType;
		this.category = category;
		this.nbViews = nbViews;
		this.createdAt = createdAt;
		this.facebookLink = facebookLink;
		this.twitterLink = twitterLink;
		this.eventState = eventState;
		this.backgroundImage = backgroundImage;
		this.email = email;
		this.phone = phone;


	}



	//constructeur Rate
	







	public Event(int id, String title, String theme, Date startTime, Date endTime, double longitude, double latitude,
			int placeNumber, EventType eventType, int nbViews, Date createdAt, String facebookLink, String twitterLink,
			EventState eventState,
			String backgroundImage,String email,String phone,double rateAvg,
			Organization organization,
			Category category) {
		super();
		this.id = id;
		this.title = title;
		this.theme = theme;
		this.startTime = startTime;
		this.endTime = endTime;
		this.longitude = longitude;
		this.latitude = latitude;
		this.placeNumber = placeNumber;
		this.eventType = eventType;
		this.nbViews = nbViews;
		this.createdAt = createdAt;
		this.facebookLink = facebookLink;
		this.twitterLink = twitterLink;
		this.eventState = eventState;
		this.backgroundImage = backgroundImage;
		this.email = email;
		this.phone = phone;
		this.rateAvg = rateAvg;
		this.organization = organization;
		this.category = category;
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}


	public void setId(int id) {
		this.id = id;
	} 
	
	
	@OneToMany(mappedBy="event", fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	public List<Media> getMedias() {
		return medias;
	}
	public void setMedias(List<Media> medias) {
		this.medias = medias;
	}
	
	
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}   
	public String getTheme() {
		return this.theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}   
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}   
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}   
	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}   
	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}   
	public int getPlaceNumber() {
		return this.placeNumber;
	}

	public void setPlaceNumber(int placeNumber) {
		this.placeNumber = placeNumber;
	}   
	@Enumerated(EnumType.STRING)
	public EventType getEventType() {
		return this.eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
	
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}









	/*
	@Enumerated(EnumType.STRING)
	public EventCategory getEventCategory() {
		return eventCategory;
	}

	public void setEventCategory(EventCategory eventCategory) {
		this.eventCategory = eventCategory;
	}
	*/
	public int getNbViews() {
		return this.nbViews;
	}

	public void setNbViews(int nbViews) {
		this.nbViews = nbViews;
	}   
	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	
	
	
	public String getFacebookLink() {
		return facebookLink;
	}

	public void setFacebookLink(String facebookLink) {
		this.facebookLink = facebookLink;
	}

	public String getTwitterLink() {
		return twitterLink;
	}

	public void setTwitterLink(String twitterLink) {
		this.twitterLink = twitterLink;
	}
	
	
	
	public String getBackgroundImage() {
		return backgroundImage;
	}


	public void setBackgroundImage(String backgroundImage) {
		this.backgroundImage = backgroundImage;
	}









	@Transient
	public double getRateAvg() {
		return rateAvg;
	}

	public void setRateAvg(double rateAvg) {
		this.rateAvg = rateAvg;
	}



	@Enumerated(EnumType.STRING)
	public EventState getEventState() {
		return eventState;
	}


	public void setEventState(EventState eventState) {
		this.eventState = eventState;
	}



	/* FOREIGN KEY Start */
	@ManyToOne(fetch=FetchType.LAZY)
	public Organization getOrganization() {
		return organization;
	}


	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@OneToMany(mappedBy="event", fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	public List<Wishlist> getWishlists() {
		return wishlists;
	}


	public void setWishlists(List<Wishlist> wishlists) {
		this.wishlists = wishlists;
	}

	@OneToMany(mappedBy="event", fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	public List<Rate> getRates() {
		return rates;
	}


	public void setRates(List<Rate> rates) {
		this.rates = rates;
	}


	@OneToMany(mappedBy="event", fetch = FetchType.LAZY,cascade=CascadeType.ALL)  
	public List<Question> getQuestions() {
		return questions;
	}


	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	@OneToMany(mappedBy="event", fetch = FetchType.LAZY,cascade=CascadeType.ALL)  
	public List<Task> getTasks() {
		return tasks;
	}


	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	@OneToMany(mappedBy="event", fetch = FetchType.LAZY,cascade=CascadeType.ALL)  
	public List<Ticket> getTickets() {
		return tickets;
	}


	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	@OneToMany(mappedBy="event",cascade=CascadeType.ALL)
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}


	@OneToMany(cascade = CascadeType.ALL, mappedBy = "event", fetch = FetchType.LAZY)
	public List<Report> getReports() {
		return reports;
	}


	public void setReports(List<Report> reports) {
		this.reports = reports;
	}
	
	
	
	
	
	
	/* FOREIGN KEY END */
	
}
