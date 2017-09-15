package tn.esprit.twin1.brogrammers.eventify.Eventify.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Iterator;

import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.CategoryBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.OrganizationBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.OrganizationBusinessRemote;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.UserBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Category;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Event;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Organization;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.User;

/**
 * Session Bean implementation class OrganizationBusiness
 */
@Stateless
public class OrganizationBusiness implements OrganizationBusinessRemote, OrganizationBusinessLocal {

	@PersistenceContext(unitName = "Eventify-ejb")
	EntityManager entityManager;
	
	@EJB
	UserBusinessLocal userbusiness;
	
	@EJB
	CategoryBusinessLocal categoryBusiness;
    /**
     * Default constructor. 
     */
    public OrganizationBusiness() {}

	@Override
	public void create(Organization organization) {
		try {
			entityManager.persist(organization);
		} catch (Exception e) {
			System.err.println("error");
		}
		
		
	}

	@Override
	public void updateOrganization(Organization organization) {
		entityManager.merge(organization);
		
	}

	@Override
	public boolean deleteOrganization(int id) {
		entityManager.remove(entityManager.find(Organization.class, id));
		return true;
	}

	
	
	@Override
	public List<Organization> getAllOrganizations() {
		List<Organization> organizations = (List<Organization>) entityManager.createQuery
				("SELECT new Organization(o.id,o.organizationName,o.organizationType,o.creationDate,user) FROM Organization o JOIN o.user user").getResultList();
	   
		for (Organization organization : organizations) {
			User user = userbusiness.findUserById(organization.getUser().getId());
			organization.setUser(user);
			
		}
		
		return organizations;
	}

	@Override
	public Organization findOrganizationById(int id) {
		Query query = entityManager.
				createQuery("SELECT new Organization(o.id,o.organizationName,o.organizationType,o.creationDate) FROM Organization o WHERE o.id=:param")
				.setParameter("param", id);
		 return (Organization) query.getSingleResult();
		
	}
	


	@Override
	public List<Organization> findOrganizationByType(String type) {
		Query query = entityManager
	    		.createQuery("SELECT o FROM Organization o WHERE o.organizationType LIKE :type")
	    		.setParameter("type", type);
	    return (List<Organization>) query.getResultList();
	}

	@Override
	public List<Organization> findOrganizationByName(String name) {
		Query query = entityManager
	    		.createQuery("SELECT o FROM Organization o WHERE o.organizationName LIKE :name")
	    		.setParameter("name", name);
	    return (List<Organization>) query.getResultList();
	}
	
	public List<Event> getMyEvents(int id){
		System.out.println("*************");
		System.out.println(id);
		System.out.println("*************");
		List<Event> events = (List<Event>)entityManager.createQuery("SELECT new Event(e.id,e.title,e.theme,e.startTime,"
						+ "e.endTime,e.longitude,e.latitude,e.placeNumber,e.eventType,c,"
						+ "e.nbViews,e.createdAt,e.facebookLink,e.twitterLink,e.eventState,e.backgroundImage,e.email,e.phone) "
						+ "FROM Organization o "
						+ "JOIN o.events e "
						+ "JOIN e.category c "
						+ "WHERE o.id=:param")
				.setParameter("param", id).getResultList();
		 
		System.out.println("*****here 111********");

		for (Event event : events) {
			Category category = categoryBusiness.findById(event.getCategory().getId());
			event.setCategory(category);
			System.out.println("*****here 2222********");
			
			Organization organization = findOrganizationById(id);
			event.setOrganization(organization);
			
			System.out.println("*****here 333********");

		}
		

		    return events;
	}

	@Override
	public List<Organization> SearchForOrganizations(String search) {
		Query query = entityManager
	    		.createQuery("SELECT o FROM Organization o WHERE o.organizationName LIKE :search OR o.organizationType LIKE :search1 ")
	    		.setParameter("search",'%' +search +'%')
	    		.setParameter("search1",'%' +search +'%');
	    return (List<Organization>) query.getResultList();
	}

	@Override
	public String GetNbOrganizationPhysiqueStatistics() {
		Query query = entityManager
	    .createQuery("SELECT o FROM Organization o where o.organizationType ='PHYSIQUE' ");
	    
		int x = query.getResultList().size();
		Query query2 = entityManager
			    .createQuery("SELECT o FROM Organization o ");
			     
		int y = query2.getResultList().size();
		
		return (x*100/y)+"%";
	}

	@Override
	public void GetNbOrganizerByOrganization() {

		List<Object[]> results = (List<Object[]>) entityManager
		        .createQuery( "SELECT org.organizationName,COUNT(State) " +
					    "FROM Organizer o  JOIN o.organization org " +
					    "GROUP BY org.organizationName")
		        .getResultList();
		for (Object[] result : results) {
		    String name = (String) result[0];
		    System.out.println(name);
		    int count = ((Number) result[1]).intValue();
		    System.out.println(count);
//ou remplacer * par :o.organizerPK.idOrganization
		}
		
		
		
	}

	@Override
	public String GetNbOrganizationMoraleStatistics() {
		Query query = entityManager
			    .createQuery("SELECT o FROM Organization o where o.organizationType ='MORALE' ");
			    
				int x = query.getResultList().size();
				Query query2 = entityManager
					    .createQuery("SELECT o FROM Organization o ");
					     
				int y = query2.getResultList().size();
				
				return (x*100/y)+"%";
	}

	
	
	
	
	/* public String ProjectPercentageOfCompletion(int idProject){
		float Percentage=0;
		String LabelPercentage=null;
		List<Task> AccomplishTasks;
		List<Task> AllTasks;
		int NbrAllTasks=0;
		int NbrAccomplishTasks=0;
		String AccomplishTasksQuery="select t from Task t where t.taskpk.idProject=:id and t.state='Done'";
		TypedQuery <Task> query = entityManager.createQuery(AccomplishTasksQuery, Task.class);
		query.setParameter("id", idProject);
		AccomplishTasks = query.getResultList();
		
		for(Task task:AccomplishTasks){
	    	 NbrAccomplishTasks ++;
	    	 
	     }
		
		String AllTasksQuery="select t from Task t where t.taskpk.idProject=:id";
		TypedQuery <Task> query2 = entityManager.createQuery(AllTasksQuery, Task.class);
		query2.setParameter("id", idProject);
		AllTasks = query2.getResultList();
		
		for(Task task:AllTasks){
	    	 NbrAllTasks ++;
	    	 
	     }
		
		Percentage = (100*NbrAccomplishTasks)/NbrAllTasks;
		return LabelPercentage = Percentage+"%";
	}*/
	 

}
