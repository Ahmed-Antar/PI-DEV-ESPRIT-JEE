package tn.esprit.twin1.brogrammers.eventify.Eventify.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ReferrelUserPK
 *
 */
@Embeddable
public class ReferrelUserPK implements Serializable {

	private int idUserReferred;
	private int idUserReferral;
	
	private static final long serialVersionUID = 1L;

	public ReferrelUserPK() {
		super();
	}

	public int getIdUserReferred() {
		return idUserReferred;
	}

	public void setIdUserReferred(int idUserReferred) {
		this.idUserReferred = idUserReferred;
	}

	public int getIdUserReferral() {
		return idUserReferral;
	}

	public void setIdUserReferral(int idUserReferral) {
		this.idUserReferral = idUserReferral;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idUserReferral;
		result = prime * result + idUserReferred;
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
		ReferrelUserPK other = (ReferrelUserPK) obj;
		if (idUserReferral != other.idUserReferral)
			return false;
		if (idUserReferred != other.idUserReferred)
			return false;
		return true;
	}
	
	
   
}
