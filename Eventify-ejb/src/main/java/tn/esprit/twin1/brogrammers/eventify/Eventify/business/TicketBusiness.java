package tn.esprit.twin1.brogrammers.eventify.Eventify.business;

import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.EventBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.ITicketBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.ITicketBusinessRemote;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Event;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Ticket;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.User;

/**
 * Session Bean implementation class TicketBusiness
 */
@Stateless
public class TicketBusiness implements ITicketBusinessRemote, ITicketBusinessLocal {

	@PersistenceContext(unitName = "Eventify-ejb")
	EntityManager entityManager;

	@EJB
	EventBusinessLocal eventbusiness;

	@Override
	public boolean create(Ticket ticket) {

		try {
			entityManager.persist(ticket);
			return true;
		} catch (Exception e) {
			System.err.println("Can't add Ticket! :(");
			return false;
		}
	}

	@Override
	public List<Ticket> getAllTickets() {
		List<Ticket> ticket = (List<Ticket>) entityManager
				.createQuery("SELECT new Ticket(t.id,t.nbTickets,t.typeTicket,t.priceTicket,t.backgroundImage,event) "
						+ "FROM Ticket t JOIN t.event event")
				.getResultList();

		for (Ticket tickets : ticket) {

			Event event = eventbusiness.findEventById(tickets.getEvent().getId());
			tickets.setEvent(null); 

		}
		return ticket;
	}

	@Override
	public boolean updateTicket(Ticket ticket) {
		try {
			entityManager.merge(ticket);
			return true;
		} catch (Exception e) {
			System.err.println("Can't modify Ticket! :(");
			return false;
		}
	}

	@Override
	public boolean deleteTicketById(int id) {
		try {entityManager.remove(entityManager.find(Ticket.class, id));
		return true;}
		catch (Exception e) {
			System.err.println("Can't delete Ticket! :(");
			return false;
		}
	}

	@Override
	public Ticket findTicketById(int idTicket) {
		Query query = entityManager
				.createQuery("SELECT new Ticket(t.id,t.nbTickets,t.typeTicket,t.priceTicket,t.backgroundImage) "
						+ "FROM Ticket t WHERE t.id=:idtick");

		Ticket t = (Ticket) query.setParameter("idtick", idTicket).getSingleResult();
		// t.setEvent(eventbusiness.findEventById(t.getEvent().getId()));
		return t;
	}
	
	
	@Override
	public List<Ticket> findTicketByEventId(int idEvent) {
		
		
		List<Ticket> ticket = (List<Ticket>) entityManager
				.createQuery("SELECT new Ticket(t.id,t.nbTickets,t.typeTicket,t.priceTicket,t.backgroundImage,t.description,event) "
						+ "FROM Ticket t JOIN t.event event  where event.id =:idevent").setParameter("idevent", idEvent)
				.getResultList();

		for (Ticket tickets : ticket) {

			Event event = eventbusiness.findEventById(tickets.getEvent().getId());
			tickets.setEvent(event); 

		}
		return ticket;
		
		
		
		
		
	}

	@Override
	public List<Ticket> findTicketByType(String typeTicket) {
		Query query = entityManager.createQuery("SELECT NEW Ticket(t.id,t.nbTickets, t.priceTicket, t.typeTicket) FROM Ticket t WHERE t.typeTicket LIKE :typeTicket")
				.setParameter("typeTicket", typeTicket);
		return (List<Ticket>) query.getResultList();
	}



	/**************************************** MET ********************************/
	@Override
	public List<Ticket> getAllTicketsEventGroupedByType(int idevent) {
		Query query  = entityManager.createQuery("SELECT NEW Ticket(t.id,t.nbTickets, t.priceTicket, t.typeTicket) FROM Ticket t where event.id =:idevent GROUP BY t.typeTicket,t.id")
				.setParameter("idevent", idevent);
		return (List<Ticket>) query.getResultList();
	}

	@Override
	public List<Ticket> AvailableTicketsOrderByPrice(int idevent) {
		Query query  = entityManager.createQuery("SELECT NEW Ticket(t.id,t.nbTickets, t.priceTicket, t.typeTicket) FROM Ticket t where event.id =:idevent "
				+ "AND t.nbTickets > 0 ORDER BY t.priceTicket ASC")
				.setParameter("idevent", idevent);
		return (List<Ticket>) query.getResultList();
	}

	@Override
	public boolean UpdateNbTicket(int idTicket,int nbareser) {
		try {Query query  = entityManager.createQuery("UPDATE Ticket SET nbTickets =:nbareser where id =:idTicket")
				.setParameter("idTicket", idTicket)
				.setParameter("nbareser", nbareser);
		query.executeUpdate();
		return true;}
		catch (Exception e) {
			System.err.println("Can't update! :(");
			return false;
		}
	}

	@Override
	public String TicketsPerCent(int eventId) {
		/*
		Query query  = entityManager.createQuery("SELECT NEW Ticket(t.typeTicket) FROM Ticket t where event.id =:idevent")
				.setParameter("idevent", eventId);
	List<Ticket> listticket = (List<Ticket>) query.getResultList();
		
		
		
		
		
		float Percentage=0;
		String LabelPercentage=null;
		List<Ticket> AccomplishTickets;
		List<Ticket> AllTickets;
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
		return LabelPercentage = Percentage+"%";*/
		return "";
	}

	@Override
	public Ticket TikcetWithEventRelation(int idTicket) {
		
		return entityManager.find(Ticket.class, idTicket);
	}


}
