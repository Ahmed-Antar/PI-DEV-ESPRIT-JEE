package tn.esprit.twin1.brogrammers.eventify.Eventify.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class RatePK implements Serializable {

	private int idUser;
	private int idEvent;
	
	private static final long serialVersionUID = 1L;


	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIdEvent() {
		return idEvent;
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
		RatePK other = (RatePK) obj;
		if (idEvent != other.idEvent)
			return false;
		if (idUser != other.idUser)
			return false;
		return true;
	}
	
	

}
