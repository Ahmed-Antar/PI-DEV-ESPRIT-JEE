package tn.esprit.twin1.brogrammers.eventify.Eventify.contracts;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Reservation;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.enumeration.PaymentMethod;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.enumeration.ReservationState;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.enumeration.TimerState;

@Local
public interface IReservationBusinessLocal {
	
	public boolean create(Reservation reservation);
	public List<Reservation> getAllReservations();
	public boolean updateReservation(Reservation reservation);
	public boolean deleteReservationById(int id);
	public Reservation findReservationById(int idReservation);
	
	/*MET*/
	public List<Reservation> findReservationByState(ReservationState reservationState);
	public List<Reservation> findReservationByTimerState(TimerState timerState);
	public List<Reservation> findReservationByPaymentMethod(PaymentMethod paymentMethod);
	public List<Reservation> findReservationByUserId(int userId);
	public int CheckConfirmedReservationSum(int idEvent);
	public List<Reservation> getAllReservationGroupByPaymentMethod(int idEvent);
	public Double getSumOfAmountForOneEvent(int idEvent);
	public Map<String,Long> getAmountOrderByYear();
	boolean UpdateReservationState(int idReservation);
	public int getIDTicketByReservationId(int reservationId);

	/*MET*/
}
