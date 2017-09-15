package tn.esprit.twin1.brogrammers.eventify.Eventify.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class WishlistPK implements Serializable{

	  private int userId;

	  private int eventId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + eventId;
		result = prime * result + userId;
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
		WishlistPK other = (WishlistPK) obj;
		if (eventId != other.eventId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	
	

	  

	  
}
