package tn.esprit.twin1.brogrammers.eventify.Eventify.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Rate
 *
 */
@Entity
public class Rate implements Serializable {

	
	private RatePK ratePK;
	private float note;
	private User user;
	private Event event;
	
	private static final long serialVersionUID = 1L;
	
	
	
	
	

	public Rate(RatePK ratePK, float note) {
		super();
		this.ratePK = ratePK;
		this.note = note;
	}




	public Rate(RatePK ratePK) {
		super();
		this.ratePK = ratePK;
	}




	public Rate() {
		super();
	}

	
	
	
	public Rate(float note) {
		super();
		this.note = note;
	}




	public Rate(RatePK ratePK, float note, User user, Event event) {
		super();
		this.ratePK = ratePK;
		this.note = note;
		this.user = user;
		this.event = event;
	}




	public Rate(float note, User user, Event event) {
		super();
		this.note = note;
		this.user = user;
		this.event = event;
	}



	public Rate(float note, User user, Event event,RatePK ratePK) {
		super();
		this.note = note;
		this.user = user;
		this.event = event;
		this.ratePK=ratePK;
	}

	
	@EmbeddedId
	public RatePK getRatePK() {
		return ratePK;
	}

	public void setRatePK(RatePK ratePK) {
		this.ratePK = ratePK;
	}

	
	
	@ManyToOne
	@JoinColumn(insertable=false,updatable=false,name="idUser",referencedColumnName="id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne
	@JoinColumn(insertable=false,updatable=false,name="idEvent",referencedColumnName="id")
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public float getNote() {
		return note;
	}

	public void setNote(float note) {
		this.note = note;
	}
	
	

}
