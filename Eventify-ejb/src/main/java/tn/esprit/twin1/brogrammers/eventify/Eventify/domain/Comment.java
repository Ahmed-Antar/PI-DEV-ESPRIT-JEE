package tn.esprit.twin1.brogrammers.eventify.Eventify.domain;

import java.io.Serializable;
import java.lang.String;


import javax.persistence.*;

/**
 * Entity implementation class for Entity: Comment
 *
 */
@Entity

public class Comment implements Serializable {


	
	private String Contain;
	private User user;
	private Event event;
	private CommentPK commentPK;
	private static final long serialVersionUID = 1L;
	
	public Comment(User user, String contain, CommentPK commentPK) {
		super();
		this.user = user;
		Contain = contain;
		this.commentPK = commentPK;
	}

	public Comment(String contain, CommentPK commentPK) {
		super();
		
		Contain = contain;
		this.commentPK = commentPK;
	}

	public Comment() {
		super();
	}   
	
@EmbeddedId
public CommentPK getCommentPK() {
	return this.commentPK;
}

public void setCommentPK(CommentPK commentPK) {
	this.commentPK = commentPK;
}

	public String getContain() {
		return this.Contain;
	}

	public void setContain(String Contain) {
		this.Contain = Contain;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUser", referencedColumnName = "id", insertable = false, updatable = false)
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne
	@JoinColumn(name = "idEvent", referencedColumnName = "id", updatable = false, insertable = false)
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	

}

