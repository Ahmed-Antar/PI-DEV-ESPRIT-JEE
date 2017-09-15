package tn.esprit.twin1.brogrammers.eventify.Eventify.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: AnswerPK
 *
 */

@Embeddable
public class AnswerPK implements Serializable {

	private int idUser;
	private int idAttribut;
	
	private static final long serialVersionUID = 1L;

	public AnswerPK() {
		super();
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIdAttribut() {
		return idAttribut;
	}

	public void setIdAttribut(int idAttribut) {
		this.idAttribut = idAttribut;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idAttribut;
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
		AnswerPK other = (AnswerPK) obj;
		if (idAttribut != other.idAttribut)
			return false;
		if (idUser != other.idUser)
			return false;
		return true;
	}
	
	
   
}
