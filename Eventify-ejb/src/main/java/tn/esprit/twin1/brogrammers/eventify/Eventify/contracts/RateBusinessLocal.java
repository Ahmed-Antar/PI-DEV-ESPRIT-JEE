package tn.esprit.twin1.brogrammers.eventify.Eventify.contracts;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Event;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Rate;

@Local
public interface RateBusinessLocal {
		
	public void createRate(Rate rate);
	
	public void modifyRate(Rate rate);
	
	public boolean deleteRateByUser(int UserId,int EventId);
	
	public float CalculRate(int idEvent);
	
	public List<Rate> GetAllRatesOfEvent(int idEvent);
	
	public List<Rate> GetAllRatesOfUser(int idUser);
	
	public Rate getRateByUserIdAndEventId(int UserId, int EventId);
	
	public Event BestEventRated();
	

}