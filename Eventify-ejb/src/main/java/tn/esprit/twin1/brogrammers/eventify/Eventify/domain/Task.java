package tn.esprit.twin1.brogrammers.eventify.Eventify.domain;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.persistence.*;



/**
 * Entity implementation class for Entity: Task
 *
 */
@Entity
public class Task implements Serializable {

	
	private int id;
	private String taskTitle;
	private String taskDescription;
	private int taskStatus;
	private Date createdAt;
	private static final long serialVersionUID = 1L;
	//private List<Organizer> organizers;
	private Organizer organizer;
	private Event event;
	private List<Discussion> discussions;
	
	

	
	public Task(int id, String taskTitle, String taskDescription, int taskStatus, Date createdAt) {
		super();
		this.id = id;
		this.taskTitle = taskTitle;
		this.taskDescription = taskDescription;
		this.taskStatus = taskStatus;
		this.createdAt = createdAt;
	}
	public Task(String taskTitle, String taskDescription, int taskStatus, Date createdAt,Event e) {
		super();
		
		this.taskTitle = taskTitle;
		this.taskDescription = taskDescription;
		this.taskStatus = taskStatus;
		this.createdAt = createdAt;
		this.setEvent(e);
	}
	
	public Task(int id, String taskTitle, String taskDescription,
			int taskStatus, Date createdAt,Organizer organizer) {
		super();
		this.id = id;
		this.taskTitle = taskTitle;
		this.taskDescription = taskDescription;
		this.taskStatus = taskStatus;
		this.createdAt = createdAt;
		this.organizer = organizer;
	}
	public Task(int id, String taskTitle, String taskDescription,
			int taskStatus, Date createdAt,Event e,Organizer organizer) {
		super();
		this.id = id;
		this.taskTitle = taskTitle;
		this.taskDescription = taskDescription;
		this.taskStatus = taskStatus;
		this.createdAt = createdAt;
		this.event= e;
		this.organizer = organizer;
	}

	
	
	public Task() {
		super();
	} 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getTaskTitle() {
		return this.taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}   
	public String getTaskDescription() {
		return this.taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}   
	public int getTaskStatus() {
		return this.taskStatus;
	}

	public void setTaskStatus(int taskStatus) {
		this.taskStatus = taskStatus;
	}   
	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	
	//coment by narimen
	/*@OneToMany(mappedBy="task")
	public List<Organizer> getOrganizers() {
		return organizers;
	}
	public void setOrganizers(List<Organizer> organizers) {
		this.organizers = organizers;
	}*/
	
	@ManyToOne
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	// add by narimen
	@ManyToOne
	public Organizer getOrganizer() {
		return organizer;
	}
	public void setOrganizer(Organizer organizer) {
		this.organizer = organizer;
	}
	
	@OneToMany(mappedBy="task", fetch = FetchType.LAZY,cascade=CascadeType.ALL)  
	public List<Discussion> getDiscussions() {
		return discussions;
	}
	public void setDiscussions(List<Discussion> discussions) {
		this.discussions = discussions;
	}
	
	

	
	
	
	
   
}
