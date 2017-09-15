package tn.esprit.twin1.brogrammers.eventify.Eventify.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: CommentPK
 *
 */
@Embeddable
public class CommentPK implements Serializable {

	
	private int idUser;
	private int idEvent;
	
	
	private static final long serialVersionUID = 1L;

	public CommentPK() {
		super();
	}   
	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}   
	public int getIdEvent() {
		return this.idEvent;
	}

	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idEvent;
		result = prime * result + idUser;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommentPK other = (CommentPK) obj;
		if (idEvent != other.idEvent)
			return false;
		if (idUser != other.idUser)
			return false;
		return true;
	}
   
}
