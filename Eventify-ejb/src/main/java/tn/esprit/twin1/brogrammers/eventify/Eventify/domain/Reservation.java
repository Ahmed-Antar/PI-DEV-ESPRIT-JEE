package tn.esprit.twin1.brogrammers.eventify.Eventify.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.*;

import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.enumeration.EventState;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.enumeration.PaymentMethod;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.enumeration.ReservationState;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.enumeration.TimerState;

/**
 * Entity implementation class for Entity: Reservation
 *
 */
@Entity

public class Reservation implements Serializable {

	private int id;
	private float amount;
	private Date reservationDate;
	private User user;
	private ReservationState reservationState = ReservationState.CONFIRMED;
	private PaymentMethod paymentMethod;
	private Transaction transaction;
	private Ticket ticket;
	private TimerState timerState = TimerState.FINISHED;
	private String country;
	private String address;
	private String address2;
	private String city;
	private int postalCode;
	
	private String firstName;
	private String lastName;
	private String email;
	private String phone;



	private static final long serialVersionUID = 1L;

	public Reservation(float amount, Date reservationDate, ReservationState reservationState,
			PaymentMethod paymentMethod, User user, Ticket ticket, TimerState timerState) {
		super();

		this.amount = amount;
		this.reservationDate = reservationDate;
		this.user = user;
		this.reservationState = reservationState;
		this.paymentMethod = paymentMethod;
		this.ticket = ticket;
		this.timerState = timerState;
	}

	public Reservation(int id, float amount, Date reservationDate, ReservationState reservationState,
			PaymentMethod paymentMethod, User user, Ticket ticket, TimerState timerState) {
		super();
		this.id = id;
		this.amount = amount;
		this.reservationDate = reservationDate;
		this.user = user;
		this.reservationState = reservationState;
		this.paymentMethod = paymentMethod;
		this.ticket = ticket;
		this.timerState = timerState;
	}

	public Reservation(int id, float amount, Date reservationDate, ReservationState reservationState, User user,
			Transaction transaction) {
		super();
		this.id = id;
		this.amount = amount;
		this.reservationDate = reservationDate;
		this.user = user;
		this.reservationState = reservationState;
		this.transaction = transaction;
	}

	public Reservation(int id, float amount, Date reservationDate, ReservationState reservationState,
			PaymentMethod paymentMethod) {
		super();
		this.id = id;
		this.amount = amount;
		this.reservationDate = reservationDate;
		this.reservationState = reservationState;
		this.paymentMethod = paymentMethod;
	}

	public Reservation(int id, float amount, Date reservationDate, ReservationState reservationState,
			PaymentMethod paymentMethod, Ticket ticket) {
		super();
		this.id = id;
		this.amount = amount;
		this.reservationDate = reservationDate;
		this.reservationState = reservationState;
		this.paymentMethod = paymentMethod;
		this.ticket = ticket;
	}

	public Reservation(int id, Ticket ticket) {
		super();
		this.id = id;
		this.ticket = ticket;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Enumerated(EnumType.STRING)
	public ReservationState getReservationState() {
		return reservationState;
	}

	public void setReservationState(ReservationState reservationState) {
		this.reservationState = reservationState;
	}

	public Reservation() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getAmount() {
		return this.amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getReservationDate() {
		return this.reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	@OneToOne(mappedBy = "reservation", cascade=CascadeType.REMOVE)
	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	@Enumerated(EnumType.STRING)
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Enumerated(EnumType.STRING)
	public TimerState getTimerState() {
		return timerState;
	}

	public void setTimerState(TimerState timerState) {
		this.timerState = timerState;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", amount=" + amount + ", reservationDate=" + reservationDate + ", user="
				+ user + ", ReservationState reservationState=" + reservationState + ", transaction=" + transaction
				+ ",+ ]";
	}

}
