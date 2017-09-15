package tn.esprit.twin1.brogrammers.eventify.Eventify.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.enumeration.AccountState;

/**
 * Entity implementation class for Entity: User
 * 
 * PS : DON'T FUCKING TOUCH THIS LOVELY BY HAKIM
 *
 */
@Entity

public class User implements Serializable {

	private int id;
	private String firstName;
	private String lastName;
	private String username;
	private String profileImage;
	private String numTel;
	private String email;
	private String password;
	private Date creationDate;
	private int loyaltyPoint;
	private AccountState accountState;// Enumeration
	private String confirmationToken;
	private int banState;
	private String country;
	private static final long serialVersionUID = 1L;

	private List<Wishlist> wishlists;
	private List<Favorite> favorites;
	private List<Notification> notifications;
	private List<Organization> organizations;
	private List<Reservation> reservations;
	private List<Organizer> organizers;
	private List<ReferrelUser> ReferredUsers;
	private List<ReferrelUser> ReferralUsers;
	private List<Answer> answers;
	private List<Rate> rates;
	private List<Comment> comments;
	private List<Message>messages;
	
	private List<Report> reports;
	
	private List<Report> iReported;
	
	 private List<Discussion> discussions;

	/*********************************
	 * Constructors
	 *********************/
	public User() {
		super();
	}

	public User(int id, String firstName, String lastName, String username,String profileImage,String numTel, String email, String password,
			Date creationDate, int loyaltyPoint, AccountState accountState, String confirmationToken) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.profileImage =profileImage;
		this.numTel=numTel;
		this.email = email;
		this.password = password;
		this.creationDate = creationDate;
		this.loyaltyPoint = loyaltyPoint;
		this.accountState = accountState;
		this.confirmationToken = confirmationToken;

	}
	
	public User(int id, String firstName, String lastName, String username,String profileImage,String numTel, String email,String country, String password,
			Date creationDate, int loyaltyPoint, AccountState accountState, String confirmationToken) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.profileImage =profileImage;
		this.numTel=numTel;
		this.email = email;
		this.country=country;
		this.password = password;
		this.creationDate = creationDate;
		this.loyaltyPoint = loyaltyPoint;
		this.accountState = accountState;
		this.confirmationToken = confirmationToken;

	}

	public User(String firstName, String lastName, String username,String profileImage,String numTel, String email, String password, Date creationDate,
			int loyaltyPoint) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.profileImage =profileImage;
		this.numTel=numTel;
		this.email = email;
		this.password = password;
		this.creationDate = creationDate;
		this.loyaltyPoint = loyaltyPoint;
	}
	
	public User(String firstName, String lastName, String username,String profileImage,String numTel, String email,String country, String password, Date creationDate,
			int loyaltyPoint) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.profileImage =profileImage;
		this.numTel=numTel;
		this.email = email;
		this.country=country;
		this.password = password;
		this.creationDate = creationDate;
		this.loyaltyPoint = loyaltyPoint;
	}

	public User(int id, String confirmationToken) {
		super();
		this.id = id;
		this.confirmationToken = confirmationToken;
	}

	public User(int id, String username, String confirmationToken) {
		super();
		this.id = id;
		this.username = username;
		this.confirmationToken = confirmationToken;
	}

	/*********************************
	 * End Of Constructors
	 *********************/

	/*********************************
	 * PK
	 *********************/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/***********************************
	 * End of PK
	 ******************************/

	/**************************************
	 * Navigation Attributes
	 ******************************/
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Wishlist> getWishlists() {
		return wishlists;
	}

	public void setWishlists(List<Wishlist> wishlists) {
		this.wishlists = wishlists;
	}

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Favorite> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Organization> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(List<Organization> organizations) {
		this.organizations = organizations;
	}

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Organizer> getOrganizers() {
		return organizers;
	}

	public void setOrganizers(List<Organizer> organizers) {
		this.organizers = organizers;
	}

	@OneToMany(mappedBy = "userReferred", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<ReferrelUser> getReferredUsers() {
		return ReferredUsers;
	}

	public void setReferredUsers(List<ReferrelUser> referredUsers) {
		ReferredUsers = referredUsers;
	}

	@OneToMany(mappedBy = "userReferral", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<ReferrelUser> getReferralUsers() {
		return ReferralUsers;
	}

	public void setReferralUsers(List<ReferrelUser> referralUsers) {
		ReferralUsers = referralUsers;
	}

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Rate> getRates() {
		return rates;
	}

	public void setRates(List<Rate> rates) {
		this.rates = rates;
	}
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	
	/*********************
	 * End of Navigation Attributes
	 ******************************/

	


	/**************************************
	 * Simple Attributes
	 ******************************/
	@Column(nullable = false)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(nullable = false)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(unique = true, nullable = false)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(nullable = false)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getLoyaltyPoint() {
		return loyaltyPoint;
	}

	public void setLoyaltyPoint(int loyaltyPoint) {
		this.loyaltyPoint = loyaltyPoint;
	}

	@Enumerated(EnumType.STRING)
	public AccountState getAccountState() {
		return accountState;
	}

	public void setAccountState(AccountState accountState) {
		this.accountState = accountState;
	}

	@Column(nullable = false)
	public String getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	@Column(unique = true, nullable = false)
	@Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$", message = "{invalid.email}")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

	public String getProfileImage() {
		return profileImage;
	}

	@Column(nullable = true)
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	@Column(unique = true, nullable = true)
	public String getNumTel() {
		return numTel;
	}

	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	/*********************
	 * End of Simple Attributes
	 ******************************/

	public void assignOrganizerToThisUser(List<Organizer> organizers) {
		this.setOrganizers(organizers);
		for (Organizer o : organizers) {
			o.setUser(this);
		}
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
	public List<Report> getiReported() {
		return iReported;
	}

	public void setiReported(List<Report> iReported) {
		this.iReported = iReported;
	}

	public int getBanState() {
		return banState;
	}

	public void setBanState(int banState) {
		this.banState = banState;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY,cascade=CascadeType.ALL)  
	public List<Discussion> getDiscussions() {
		return discussions;
	}
	public void setDiscussions(List<Discussion> discussions) {
		this.discussions = discussions;
	}
	

}
