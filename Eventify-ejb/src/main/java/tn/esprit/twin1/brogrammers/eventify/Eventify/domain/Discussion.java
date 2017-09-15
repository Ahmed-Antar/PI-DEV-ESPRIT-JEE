package tn.esprit.twin1.brogrammers.eventify.Eventify.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class Discussion implements Serializable {
	private int id;
	private String Messagedata;
	private Date messageDate;
	private int status;
	private User user;
	private Task task;
	
	
	
	
	
	
	public Discussion() {
		super();
	}
	public Discussion(int id, String messagedata, Date messageDate, int status, User user, Task task) {
		super();
		this.id = id;
		Messagedata = messagedata;
		this.messageDate = messageDate;
		this.status = status;
		this.user = user;
		this.task = task;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMessagedata() {
		return Messagedata;
	}
	public void setMessagedata(String messagedata) {
		Messagedata = messagedata;
	}
	public Date getMessageDate() {
		return messageDate;
	}
	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@ManyToOne
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@ManyToOne
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	

}
