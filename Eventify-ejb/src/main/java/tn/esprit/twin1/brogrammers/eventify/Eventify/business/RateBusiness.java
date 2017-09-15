package tn.esprit.twin1.brogrammers.eventify.Eventify.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.EventBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.RateBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.RateBusinessRemote;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.UserBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Event;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Rate;


/**
 * Session Bean implementation class RateBusiness
 */
@Stateless
@LocalBean
public class RateBusiness implements RateBusinessRemote, RateBusinessLocal {

    /**
     * Default constructor. 
     */
	
	@PersistenceContext(unitName = "Eventify-ejb")
	EntityManager entityManager;
	
	@EJB
	UserBusinessLocal userbusiness;
	
	@EJB
	EventBusinessLocal eventbusiness;
	
	
	
	public RateBusiness() {
    	super();
        
    }

	@Override
	public void createRate(Rate rate) {
		
		
		entityManager.persist(rate);
		
	}

	@Override
	public void modifyRate(Rate rate) {
		entityManager.merge(rate);
	}
	
	

	@Override
	public boolean deleteRateByUser(int UserId,int EventId) {
		
		if(getRateByUserIdAndEventId(UserId, EventId)!=null)
		{
			entityManager.remove(entityManager.merge(getRateByUserIdAndEventId(UserId, EventId)));;
			return true;
		}
		else 
		return false;
	}



	@Override
	public float CalculRate(int idEvent) {
		
		List<Rate> rates = GetAllRatesOfEvent(idEvent);
		
		float MoyenneRate = 0;
		int RatesCount = 0;
		for(Rate rate : rates)	
		{
			MoyenneRate += rate.getNote();
			RatesCount++;
		}
		
		
		return MoyenneRate/RatesCount;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Rate> GetAllRatesOfEvent(int idEvent) {
		
		 try {
				Query query = entityManager.
						createQuery("SELECT new Rate(r.ratePK,r.note) FROM Rate r WHERE r.ratePK.idEvent=:param")
						.setParameter("param", idEvent);
				System.out.println("rates finded by event");
				 return query.getResultList();

			} catch (Exception e) {
				System.err.println("Cant Find rates of event");
				return new ArrayList<Rate>();
			}
		
	}
	@SuppressWarnings("unchecked") 
	@Override
	public List<Rate> GetAllRatesOfUser(int idUser) {
		
		 try {
				Query query = entityManager.
						createQuery("SELECT new Rate(r.ratePK,r.note) FROM Rate r WHERE r.ratePK.idUser=:param")
						.setParameter("param", idUser);
				System.out.println("rates finded by user");
				 return query.getResultList();

			} catch (Exception e) {
				System.err.println("Cant Find Rates of user");
				return new ArrayList<Rate>();
			}
		
	}
	
	@Override
	public Rate getRateByUserIdAndEventId(int UserId, int EventId) {
		try {
			Query query = entityManager.
					createQuery("SELECT new Rate(r.ratePK,r.note) FROM Rate r"
							+ " WHERE r.ratePK.idUser=:userId AND r.ratePK.idEvent=:eventId")
			.setParameter("userId", UserId)
			.setParameter("eventId", EventId);
			System.out.println("rates finded by event and user ");
			 return (Rate) query.getSingleResult();
			 

		} catch (Exception e) {
			System.err.println("Cant Find rate  event and user");
			return null; } 
		
		}

	@Override
	public Event BestEventRated() {
		
		List<Event> events = new ArrayList<Event>();
		events = eventbusiness.getAllEvents();
		
		float rate = 0 ;
		Event bestEvent = new Event();
		for (Event event : events) {
			
			if(rate< CalculRate(event.getId()))
			{
				rate=CalculRate(event.getId());
				
				bestEvent=event;
			}
			
		}
		
		return bestEvent;
	}
	
}
	
