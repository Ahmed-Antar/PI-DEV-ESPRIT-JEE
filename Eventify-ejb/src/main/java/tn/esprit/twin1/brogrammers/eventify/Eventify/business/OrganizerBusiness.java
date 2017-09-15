package tn.esprit.twin1.brogrammers.eventify.Eventify.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.OrganizerBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.OrganizerBusinessRemote;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Event;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Organization;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Organizer;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.User;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Wishlist;

/**
 * Session Bean implementation class OrganizerBusiness
 */
@Stateless
public class OrganizerBusiness implements OrganizerBusinessRemote, OrganizerBusinessLocal {

	
	@PersistenceContext(unitName = "Eventify-ejb")
	EntityManager entityManager;
    /**
     * Default constructor. 
     */
    public OrganizerBusiness() {
        // TODO Auto-generated constructor stub
    }
    
    
    @Override
	public void assignOrganizer(Organizer organizer) {
		try {
			entityManager.persist(organizer);
		} catch (Exception e) {
			System.err.println("error");
		}
		
	}
    
    
	@Override
	public void updateOrganizer(Organizer organizer) {
		entityManager.merge(organizer);
		
	}



	@Override
	public List<Organizer> getAllOrganizersByOrganization(int OrganizationId) {
		
	  
		    try {
				Query query = entityManager.
						createQuery("SELECT new Organizer(o.organizerPK) FROM Organizer o WHERE o.organizerPK.idOrganization=:param")
						.setParameter("param", OrganizationId);
				 return query.getResultList();

			} catch (Exception e) {
				System.err.println("Cant Find Organizer");
				return new ArrayList<Organizer>();
			}
	
	}



	

	@Override
	public boolean deleteOrganizer(int UserId, int OrganizationId) {
		
			entityManager.remove(entityManager.merge(getAllOrganizersByUserIdAndOrganizationId(UserId, OrganizationId)));;
			return true;
		
	}

	@Override
	public List<Organizer> getAllOrganizersByUser(int UserId) {
		  try {
				Query query = entityManager.
						createQuery("SELECT new Organizer(o.organizerPK) FROM Organizer o WHERE o.organizerPK.idUser=:param")
						.setParameter("param", UserId);
				 return query.getResultList();

			} catch (Exception e) {
				System.err.println("Cant Find Organizer");
				return new ArrayList<Organizer>();
			}
	}

	@Override
	public Organizer getAllOrganizersByUserIdAndOrganizationId(int UserId, int OrganizationId) {
		try {
			Query query = entityManager.
					createQuery("SELECT new Organizer(o.organizerPK,o.State) FROM Organizer o"
							+ " WHERE o.organizerPK.idOrganization=:OrganizationId AND o.organizerPK.idUser=:UserId")
			.setParameter("OrganizationId", OrganizationId)
			.setParameter("UserId", UserId);
			 return (Organizer) query.getSingleResult();

		} catch (Exception e) {
			System.err.println("Cant Find Organizer");
			return null; } 
		}


	@Override
	public void GetNbOrganizerByOrganization() {

		/*List<Object[]> results = (List<Object[]>) entityManager
		        .createQuery("SELECT oz.organizerPK AS name, COUNT(oz) AS total FROM Organizer oz JOIN oz.Organization o GROUP BY o.organizationName,o.id")
		        .getResultList();
		for (Object[] result : results) {
		    String name = (String) result[0];
		    System.out.println(name);
		    int count = ((Number) result[1]).intValue();
		    System.out.println(count);

		}
		*/
		TypedQuery<Object[]> q = entityManager.createQuery(
			    "SELECT org.organizationName,COUNT(State) " +
			    "FROM Organizer o  JOIN o.organization org " +
			    "GROUP BY org.organizationName", Object[].class);
/* 		TypedQuery<Object[]> q = entityManager.createQuery(
			    "SELECT org.organizationName,count(o.Stat) " +
			    "FROM Organizer o  JOIN Organization org on  o.organizerPK.idOrganization=org.id" +
			    "GROUP BY org.organizationName", Object[].class);
*/          System.out.println(q);
			List<Object[]> resultList = q.getResultList();
			Map<String, Long> resultMap = new HashMap<String, Long>(resultList.size());
			for (Object[] result : resultList)
			  resultMap.put((String)result[0], (Long)result[1]);
		
		
		
	}


	@Override
	public void AcceptOrganizerRole(int UserId, int OrganizationId) {
		Query query  = entityManager.createQuery("UPDATE Organizer o SET State ='ACCEPTED' where  o.organizerPK.idOrganization=:OrganizationId AND o.organizerPK.idUser=:UserId")
				.setParameter("OrganizationId", OrganizationId)
				.setParameter("UserId", UserId);
		query.executeUpdate();
		
	}


	@Override
	public void RejectOrganizerRole(int UserId, int OrganizationId) {
		// TODO Auto-generated method stub
		Query query  = entityManager.createQuery("UPDATE Organizer o SET State ='REFUSED' where  o.organizerPK.idOrganization=:OrganizationId AND o.organizerPK.idUser=:UserId")
				.setParameter("OrganizationId", OrganizationId)
				.setParameter("UserId", UserId);
		query.executeUpdate();
	}
	
	
	
	}


