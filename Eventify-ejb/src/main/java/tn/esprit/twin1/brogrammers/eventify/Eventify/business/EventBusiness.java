package tn.esprit.twin1.brogrammers.eventify.Eventify.business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import tn.esprit.twin1.brogrammers.eventify.Eventify.util.*;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.event.DocumentEvent.EventType;

import org.apache.commons.lang3.time.DateFormatUtils;

import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.CategoryBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.EventBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.EventBusinessRemote;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.OrganizationBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.OrganizerBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.QuestionBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.UserBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Category;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Event;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Organization;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Organizer;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Question;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Ticket;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.User;

/**
 * Session Bean implementation class EventBusiness
 */
@Stateless
public class EventBusiness implements EventBusinessRemote, EventBusinessLocal {

	@PersistenceContext(unitName = "Eventify-ejb")
	EntityManager entityManager;

	@EJB
	OrganizationBusinessLocal organizationBusiness;

	@EJB
	OrganizerBusinessLocal organizerBusiness;

	@EJB
	CategoryBusinessLocal categoryBusiness;

	@EJB
	QuestionBusinessLocal questionBusiness;
	
	@EJB
	UserBusinessLocal userBusiness;
	
	// ajout
	@Override
	public void create(Event event) {
		try {
			entityManager.persist(event);

		} catch (Exception e) {
			System.err.println("Failed to Add");
		}

	}

	@Override
	public List<Event> getAllEvents() {
		// Query query = entityManager.createQuery("SELECT e FROM Event e");
		// return (List<Event>) query.getResultList();

		List<Event> events = (List<Event>) entityManager
				.createQuery(
						"SELECT new Event(e.id,e.title,e.theme,e.startTime,"
						+ "e.endTime,e.longitude,e.latitude,e.placeNumber,"
						+ "e.eventType," + "c,"
						+ "e.nbViews,e.createdAt,e.facebookLink,e.twitterLink,"
						+ "e.eventState,e.backgroundImage,e.email,e.phone,o) " 
						+ "FROM Event e "
						+ "JOIN e.organization o "
						+ "JOIN e.category c"
						)
				.getResultList();

		for (Event event : events) {
			Organization organization = organizationBusiness.findOrganizationById(event.getOrganization().getId());
			event.setOrganization(organization);
		}

		for (Event event : events) {
			Category category = categoryBusiness.findById(event.getCategory().getId());
			event.setCategory(category);
		}

		return events;

	}

	@Override
	public void updateEvent(Event event) {
		try {
			
			if(findEventById(event.getId()).getEventState().toString().equals("UNPUBLISHED") && event.getEventState().toString().equals("PUBLISHED"))
			{
				List<Organizer> organizers = (List<Organizer>)entityManager.createQuery
						("SELECT new Organizer(o.organizerPK) FROM Organizer o "
								+ "WHERE o.organizerPK.idOrganization=:param")
								.setParameter("param", event.getOrganization().getId()).getResultList();
				System.out.println("*******Organizers************");
				for (Organizer organizer : organizers) {
					System.out.println("*********ID User********");
					System.out.println(organizer.getOrganizerPK().getIdUser());
					User u = userBusiness.findUserById(organizer.getOrganizerPK().getIdUser());
					//String s = SmsSender.SendSMS(u.getNumTel(), "Get Ready for work , Event : "+event.getTitle()+" is Published");
					System.out.println("************SMS ID*******");
					//System.out.println(s);
				}
			}
				
				entityManager.merge(event);

		} catch (Exception e) {
			System.err.println("Failed to Modify");
		}
	}

	@Override
	public boolean deleteEvent(int id) {

		try {
			entityManager.remove(entityManager.find(Event.class, id));
			return true;

		} catch (Exception e) {
			System.err.println("Failed to delete");

			return false;
		}
			
		

	}

	@Override
	public Event findEventById(int idEvent) {

		try {
			Query query = entityManager.createQuery("SELECT new Event(e.id,e.title,e.theme,e.startTime,"
					+ "e.endTime,e.longitude,e.latitude,e.placeNumber,e.eventType,c,"
					+ "e.nbViews,e.createdAt,e.facebookLink,e.twitterLink,e.eventState,e.backgroundImage,e.email,e.phone,o) "
					+ "FROM Event e JOIN e.category c JOIN e.organization o WHERE e.id=:param").setParameter("param", idEvent);
			Event e = (Event) query.getSingleResult();

			
			Organization organization = organizationBusiness.findOrganizationById(e.getOrganization().getId());
			e.setOrganization(organization);
			Category category = categoryBusiness.findById(e.getCategory().getId());
			e.setCategory(category);
			return e;

		} catch (Exception e) {
			System.err.println("Failed to find the event");
			return null;
		}
	}

	@Override
	public List<Event> findEventByType(EventType type) {
		List<Event> events = (List<Event>) entityManager
				.createQuery("SELECT new Event(e.id,e.title,e.theme,e.startTime,"
						+ "e.endTime,e.longitude,e.latitude,e.placeNumber,e.eventType," + "c,"
						+ "e.nbViews,e.createdAt,e.facebookLink,e.twitterLink,e.eventState,e.backgroundImage,e.email,e.phone,o) "
						+ "FROM Event e JOIN e.organization o JOIN e.category c " + "WHERE e.eventType LIKE :type")
				.setParameter("type", type).getResultList();

		for (Event event : events) {
			Organization organization = organizationBusiness.findOrganizationById(event.getOrganization().getId());
			event.setOrganization(organization);
		}

		for (Event event : events) {
			Category category = categoryBusiness.findById(event.getCategory().getId());
			event.setCategory(category);
		}

		return events;
	}

	@Override
	public List<Event> findEventByCategory(String categoryName) {
		List<Event> events = (List<Event>) entityManager
				.createQuery("SELECT new Event(e.id,e.title,e.theme,e.startTime,"
						+ "e.endTime,e.longitude,e.latitude,e.placeNumber,e.eventType," + "c,"
						+ "e.nbViews,e.createdAt,e.facebookLink,e.twitterLink,e.eventState,e.backgroundImage,e.email,e.phone,o) " + "FROM Event e "
						+ "JOIN e.organization o " + "JOIN e.category c " + "WHERE c.categoryName LIKE :category")
				.setParameter("category", categoryName).getResultList();

		for (Event event : events) {
			Organization organization = organizationBusiness.findOrganizationById(event.getOrganization().getId());
			event.setOrganization(organization);
		}

		for (Event event : events) {
			Category category = categoryBusiness.findById(event.getCategory().getId());
			event.setCategory(category);
		}

		return events;
	}

	@Override
	public List<Event> findEventByPeriode(Date startTime, Date endTime) {
		List<Event> events = (List<Event>) entityManager
				.createQuery("SELECT new Event(e.id,e.title,e.theme,e.startTime,"
						+ "e.endTime,e.longitude,e.latitude,e.placeNumber,e.eventType," + "c,"
						+ "e.nbViews,e.createdAt,e.facebookLink,e.twitterLink,e.eventState,e.backgroundImage,e.email,e.phone,o) "
						+ "FROM Event e JOIN e.organization o JOIN e.category c "
						+ "WHERE e.startTime = :startTime AND e.endTime = :endTime")
				.setParameter("startTime", startTime).setParameter("endTime", endTime).getResultList();
		for (Event event : events) {
			Organization organization = organizationBusiness.findOrganizationById(event.getOrganization().getId());
			event.setOrganization(organization);
		}

		for (Event event : events) {
			Category category = categoryBusiness.findById(event.getCategory().getId());
			event.setCategory(category);
		}

		return events;
	}

	@Override
	public List<Event> findEventByDate(Date date) {
		List<Event> events = (List<Event>) entityManager
				.createQuery("SELECT new Event(e.id,e.title,e.theme,e.startTime,"
						+ "e.endTime,e.longitude,e.latitude,e.placeNumber,e.eventType," + "c,"
						+ "e.nbViews,e.createdAt,e.facebookLink,e.twitterLink,e.eventState,e.backgroundImage,e.email,e.phone,o) "
						+ "FROM Event e JOIN e.organization o JOIN e.category c "
						+ "WHERE :date BETWEEN e.startTime AND e.endTime ")
				.setParameter("date", date).getResultList();
		for (Event event : events) {
			Organization organization = organizationBusiness.findOrganizationById(event.getOrganization().getId());
			event.setOrganization(organization);
		}

		for (Event event : events) {
			Category category = categoryBusiness.findById(event.getCategory().getId());
			event.setCategory(category);
		}

		return events;
	}

	@Override
	public List<Event> SearchForEvents(String search) {
		List<Event> events = (List<Event>) entityManager
				.createQuery("SELECT new Event(e.id,e.title,e.theme,e.startTime,"
						+ "e.endTime,e.longitude,e.latitude,e.placeNumber,e.eventType,c,"
						+ "e.nbViews,e.createdAt,e.facebookLink,e.twitterLink,e.eventState,e.backgroundImage,e.email,e.phone,o) "
						+ "FROM Event e JOIN e.organization o JOIN e.category c "
						+ "WHERE e.title LIKE :search OR e.theme LIKE :search1 ")
				.setParameter("search", '%' + search + '%').setParameter("search1", '%' + search + '%').getResultList();

		for (Event event : events) {
			Organization organization = organizationBusiness.findOrganizationById(event.getOrganization().getId());
			event.setOrganization(organization);
		}

		for (Event event : events) {
			Category category = categoryBusiness.findById(event.getCategory().getId());
			event.setCategory(category);
		}

		return events;

	}

	@Override
	public List<Event> getPopularEvents() {

		List<Event> events = (List<Event>) entityManager
				.createQuery("SELECT new Event(e.id,e.title,e.theme,"
						+ "e.startTime,e.endTime,e.longitude,e.latitude,"
						+ "e.placeNumber,e.eventType,e.nbViews,e.createdAt,"
						+ "e.facebookLink,e.twitterLink,"
						+ "e.eventState,e.backgroundImage,e.email,e.phone,AVG(r.note),o,c) FROM Event e "
						+ "JOIN e.organization o "
						+ "JOIN e.category c "
						+ "JOIN e.rates r "
						+ "GROUP BY e.id" )
				.getResultList();

		return events;

	}

	@Override
	public List<Event> findEventNearBy(double myLongitude, double myLatitude) {
		List<Event> events = getAllEvents();
		List<Event> nearByEvents = new ArrayList<>();
		for (Event event : events) {

			if (Distance.distance(myLatitude, myLongitude, event.getLatitude(), event.getLongitude()) < 1) {
				nearByEvents.add(event);
			}
		}
		return nearByEvents;
	}

	@Override
	public List<Event> getFavoriteEventByUser(int idUser) {
		List<Event> events = (List<Event>) entityManager
				.createQuery("SELECT new Event(e.id,e.title,e.theme,e.startTime,"
						+ "e.endTime,e.longitude,e.latitude,e.placeNumber,e.eventType,c,"
						+ "e.nbViews,e.createdAt,e.facebookLink,e.twitterLink,e.eventState,e.backgroundImage,e.email,e.phone,o)" + "FROM Event e "
						+ "JOIN e.organization o JOIN e.category c "
						+ "JOIN c.favorites f "
						+ "WHERE f.favoritePK.userId = :userId " + "ORDER BY f.priority")
				.setParameter("userId", idUser).getResultList();
		for (Event event : events) {
			Organization organization = organizationBusiness.findOrganizationById(event.getOrganization().getId());
			event.setOrganization(organization);
		}

		for (Event event : events) {
			Category category = categoryBusiness.findById(event.getCategory().getId());
			event.setCategory(category);
		}

		return events;
	}

	@Override
	public List<Question> getMyQuestions(int id) {
		List<Question> questions = (List<Question>) entityManager.createQuery
				("SELECT new Question(q.id,q.questionDescription,q.questionType,"
						+ "q.questionCategory,q.status,q.questionDate,q.order) FROM Event e "
						+ "JOIN e.questions q WHERE e.id=:param")
				.setParameter("param", id).getResultList();
		

		return questions;
		
	}
	
	@Override
	public List<User> NotifyUsersForSoonEvent(){
			
		return null;
	}

	@Override
	public Event getMyRate(int id) {

		try {
			Event event = (Event) entityManager
					.createQuery("SELECT new Event(e.id,e.title,e.theme,"
							+ "e.startTime,e.endTime,e.longitude,e.latitude,"
							+ "e.placeNumber,e.eventType,e.nbViews,e.createdAt,"
							+ "e.facebookLink,e.twitterLink,"
							+ "e.eventState,e.backgroundImage,e.email,e.phone,AVG(r.note),o,c) FROM Event e "
							+ "JOIN e.organization o "
							+ "JOIN e.category c "
							+ "JOIN e.rates r "
							+ "WHERE e.id=:param "
							+ "GROUP BY e.id" )
					.setParameter("param", id)
					.getSingleResult();

			return event;

			
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Ticket> getMyTickets(int id) {
		List<Ticket> tickets = (List<Ticket>) entityManager.createQuery
				("SELECT new Ticket("
						+ "t.id ,t.nbTickets,t.typeTicket,t.priceTicket,t.backgroundImage) "
						+ "FROM Event e "
						+ "JOIN e.tickets t WHERE e.id=:param")
				.setParameter("param", id).getResultList();
		

		return tickets;
	}
	
	
	
	

}
