package tn.esprit.twin1.brogrammers.eventify.Eventify.contracts;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.swing.event.DocumentEvent.EventType;

import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Event;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Organization;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Question;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Ticket;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.User;

@Local
public interface EventBusinessLocal {
	public void create(Event event);
	public List<Event> getAllEvents();
	public void updateEvent(Event event);
	public boolean deleteEvent(int id);
	public Event findEventById(int idEvent);
	public List<Event> findEventByType(EventType type);
	public List<Event> findEventByCategory(String category);
	public List<Event> findEventByPeriode(Date startTime,Date endTime);
	public List<Event> findEventByDate(Date date);
	public List<Event> findEventNearBy(double myLongitude,double myLatitude);
	public List<Event> SearchForEvents(String search);
	public List<Event> getPopularEvents();
	public List<Event> getFavoriteEventByUser(int idUser);
	public List<Question> getMyQuestions(int id);
	public List<User> NotifyUsersForSoonEvent();
	public Event getMyRate(int id);
	public List<Ticket> getMyTickets(int id);

}
