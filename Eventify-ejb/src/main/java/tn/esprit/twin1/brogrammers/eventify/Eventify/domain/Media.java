package tn.esprit.twin1.brogrammers.eventify.Eventify.domain;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Media
 *
 */
@Entity
@Table(name="MyMedia")
public class Media implements Serializable {

	   
	/*Media Prop*/
	private int id;
	private String typeMedia;
	private String pathMedia;
	private Date mediaDate;
	/*End Media Prop*/
	private static final long serialVersionUID = 1L; //Default Value Added when using Add new "JPA Entity"
	
	
	/*Navigation  Prop*/
	
	private Event event;
	
	
	/*End Navigation  Prop*/
	
	/*Constructors*/
	public Media() {
		super();
	}
	/*End Constructors*/
	
	/*Getter And Setter*/
	@Id
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	} 
	
	/*Navigation Prop Getter And Setter*/
	@ManyToOne
	public Event getEvent() {
		return event;
	}

	
	public void setEvent(Event event) {
		this.event = event;
	}
	/*End Navigation Prop Getter And Setter*/
	
	
	public String getTypeMedia() {
		return this.typeMedia;
	}

	public void setTypeMedia(String typeMedia) {
		this.typeMedia = typeMedia;
	}   
	public String getPathMedia() {
		return this.pathMedia;
	}

	public void setPathMedia(String pathMedia) {
		this.pathMedia = pathMedia;
	}   
	public Date getMediaDate() {
		return this.mediaDate;
	}

	public void setMediaDate(Date mediaDate) {
		this.mediaDate = mediaDate;
	}
	
	
	
   
}
