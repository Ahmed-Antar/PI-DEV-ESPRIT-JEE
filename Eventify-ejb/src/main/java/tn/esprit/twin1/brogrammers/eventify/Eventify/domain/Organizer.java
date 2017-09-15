package tn.esprit.twin1.brogrammers.eventify.Eventify.domain;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.enumeration.OrganizerState;



@Entity
public class Organizer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private OrganizerPK  organizerPK;
    private OrganizerState State=OrganizerState.SENT;
	private User user;
	private Organization organization;
	
	private List<Task> tasks; //addedbybadran //modifier par narimen
	
	
	public Organizer (OrganizerPK  organizerPK)
	{
		this.organizerPK=organizerPK;
	}
	public Organizer (OrganizerPK  organizerPK,OrganizerState State)
	{
		this.organizerPK=organizerPK;
		this.State=State;
	}
	public Organizer (User user,Organization organization)
	{
		this.user=user;
		this.organization=organization;
	}
	
	
	@EmbeddedId
	public OrganizerPK getOrganizerPK() {
		return organizerPK;
	}

	public void setOrganizerPK(OrganizerPK organizerPK) {
		this.organizerPK = organizerPK;
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
	@JoinColumn(insertable=false,updatable=false,name="idOrganization",referencedColumnName="id")
	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Organizer() {
		super();
	}
	//addedbyBadran
	/* @ManyToOne
	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}*/
	@OneToMany(mappedBy="organizer", fetch = FetchType.LAZY,cascade=CascadeType.REMOVE)
	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	@Enumerated(EnumType.STRING)
	public OrganizerState getState() {
		return State;
	}

	public void setState(OrganizerState state) {
		State = state;
	}
	
	
	
	

}
