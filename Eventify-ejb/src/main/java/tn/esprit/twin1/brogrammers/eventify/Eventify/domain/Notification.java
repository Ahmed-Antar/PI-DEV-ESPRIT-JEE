package tn.esprit.twin1.brogrammers.eventify.Eventify.domain;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Notification
 *
 */
@Entity

public class Notification implements Serializable {


	private int id;
	private String notificationTitle;
	private String notificationDescription;
	private Date notificationDate;
	private int notificationStatus;
	private static final long serialVersionUID = 1L;
	
	private User user;
	
	
	
	public Notification(int id, String notificationTitle, String notificationDescription, Date notificationDate,
			int notificationStatus, User user) {
		super();
		this.id = id;
		this.notificationTitle = notificationTitle;
		this.notificationDescription = notificationDescription;
		this.notificationDate = notificationDate;
		this.notificationStatus = notificationStatus;
		this.user = user;
	}
	@ManyToOne
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Notification() {
		super();
	}   
	
	@Id
	public int getId() {
		return this.id;
	}
	//

	public void setId(int id) {
		this.id = id;
	}   
	public String getNotificationTitle() {
		return this.notificationTitle;
	}

	public void setNotificationTitle(String notificationTitle) {
		this.notificationTitle = notificationTitle;
	}   
	public String getNotificationDescription() {
		return this.notificationDescription;
	}

	public void setNotificationDescription(String notificationDescription) {
		this.notificationDescription = notificationDescription;
	}   
	public Date getNotificationDate() {
		return this.notificationDate;
	}

	public void setNotificationDate(Date notificationDate) {
		this.notificationDate = notificationDate;
	}
	public int getNotificationStatus() {
		return notificationStatus;
	}
	public void setNotificationStatus(int notificationStatus) {
		this.notificationStatus = notificationStatus;
	}
   
	
}
