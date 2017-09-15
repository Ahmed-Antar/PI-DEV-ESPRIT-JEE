package tn.esprit.twin1.brogrammers.eventify.Eventify.domain;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.persistence.Embeddable;

@Embeddable
public class OrganizerPK implements Serializable {

	/**
	 * 
	 */
	
	   
	private static final long serialVersionUID = 1L;
	private int idUser;
	private int idOrganization;

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIdOrganization() {
		return idOrganization;
	}

	public void setIdOrganization(int idOrganization) {
		this.idOrganization = idOrganization;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idOrganization;
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
		OrganizerPK other = (OrganizerPK) obj;
		if (idOrganization != other.idOrganization)
			return false;
		if (idUser != other.idUser)
			return false;
		return true;
	}
	
	

}
