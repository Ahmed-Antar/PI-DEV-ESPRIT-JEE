package tn.esprit.twin1.brogrammers.eventify.Eventify.contracts;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Event;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Rate;

@Remote
public interface RateBusinessRemote {
		
public void createRate(Rate rate);
	
	public void modifyRate(Rate rate);
	
	public boolean deleteRateByUser(int UserId,int EventId);
	
	public float CalculRate(int idEvent);
	
	public List<Rate> GetAllRatesOfEvent(int idEvent);
	
	public List<Rate> GetAllRatesOfUser(int idUser);
	
	public Rate getRateByUserIdAndEventId(int UserId, int EventId);
	
	public Event BestEventRated();
}
	
