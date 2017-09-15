package tn.esprit.twin1.brogrammers.eventify.Eventify.business;

import java.util.Iterator;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.AttributBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.AttributBusinessRemote;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Attribut;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Event;

/**
 * Session Bean implementation class AttributBusiness
 */
@Stateless
@LocalBean
public class AttributBusiness implements AttributBusinessRemote, AttributBusinessLocal {

	
	@PersistenceContext(unitName = "Eventify-ejb")
	EntityManager entityManager;

    /**
     * Default constructor. 
     */
    public AttributBusiness() {
    }

	@Override
	public void createAttribut(Attribut attribut) {
		try {
	        entityManager.persist(attribut);

		} catch (Exception e) {
			System.err.println("Failed to Add");
		}
		
	}

	@Override
	public void updateAttribut(Attribut attribut) {
		try {
	        entityManager.merge(attribut);

		} catch (Exception e) {
			System.err.println("Failed to Modify");
		}
		
	}

	@Override
	public boolean deleteAttribut(int id) {
		
		try {
			entityManager.remove(getAttributById(id));
			return true;
		} catch (Exception e) {
			System.err.println("Failed To Delete");
			return false;
		}
	}

	@Override
	public Attribut getAttributById(int id) {
		try {
			Query query = entityManager.
					createQuery("SELECT new Attribut(a.id,a.attributValue,a.duplicated) FROM Attribut a WHERE a.id=:param")
					.setParameter("param", id);
			 return (Attribut) query.getSingleResult();

		} catch (Exception e) {
			System.err.println("Cant Find Attribut");
			return null;
		}
	}

}
