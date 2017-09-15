package tn.esprit.twin1.brogrammers.eventify.Eventify.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.enumeration.StateInvitation;

/**
 * Entity implementation class for Entity: ReferrelUser
 *
 */
@Entity
public class ReferrelUser implements Serializable {

	private ReferrelUserPK referrelUserPK;
	private User userReferred;
	private User userReferral;
	private Date dateInvitation;
	private StateInvitation stateInvitation;
	private Event event;
	
	

	@ManyToOne
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public ReferrelUser(User userReferred, User userReferral, StateInvitation stateInvitation) {
		super();
		this.userReferred = userReferred;
		this.userReferral = userReferral;
		this.stateInvitation = stateInvitation;
	}

	public ReferrelUser(ReferrelUserPK referrelUserPK, Date dateInvitation, StateInvitation stateInvitation) {
		super();
		this.referrelUserPK = referrelUserPK;
		this.dateInvitation = dateInvitation;
		this.stateInvitation = stateInvitation;
	}

	public ReferrelUser(ReferrelUserPK referrelUserPK, User userReferred, User userReferral,
			StateInvitation stateInvitation) {
		super();
		this.referrelUserPK = referrelUserPK;
		this.userReferred = userReferred;
		this.userReferral = userReferral;
		this.stateInvitation = stateInvitation;
	}

	private static final long serialVersionUID = 1L;

	public ReferrelUser() {
		super();
	}



	@EmbeddedId
	public ReferrelUserPK getReferrelUserPK() {
		return referrelUserPK;
	}

	public void setReferrelUserPK(ReferrelUserPK referrelUserPK) {
		this.referrelUserPK = referrelUserPK;
	}

	@ManyToOne
	@JoinColumn(insertable=false,updatable=false,name="idUserReferred",referencedColumnName="id")
	public User getUserReferred() {
		return userReferred;
	}

	public void setUserReferred(User userReferred) {
		this.userReferred = userReferred;
	}

	@ManyToOne
	@JoinColumn(insertable=false,updatable=false,name="idUserReferral",referencedColumnName="id")
	public User getUserReferral() {
		return userReferral;
	}

	public void setUserReferral(User userReferral) {
		this.userReferral = userReferral;
	}

	public Date getDateInvitation() {
		return dateInvitation;
	}

	public void setDateInvitation(Date dateInvitation) {
		this.dateInvitation = dateInvitation;
	}

	@Enumerated(EnumType.STRING)
	public StateInvitation getStateInvitation() {
		return stateInvitation;
	}

	public void setStateInvitation(StateInvitation stateInvitation) {
		this.stateInvitation = stateInvitation;
	}


	
	
	
   
}
