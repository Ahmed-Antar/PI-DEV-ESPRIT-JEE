package tn.esprit.twin1.brogrammers.eventify.Eventify.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Transaction
 *
 */
@Entity

public class Transaction implements Serializable {

	   
	
	private int id;
	private String token;
	private float amount;
	private Reservation reservation;
	
	private static final long serialVersionUID = 1L;

	public Transaction() {
		super();
	}   
	
	public Transaction(int id, String token, float amount, Reservation reservation) {
		super();
		this.id = id;
		this.token = token;
		this.amount = amount;
		this.reservation = reservation;
	}
	public Transaction(String token, float amount, Reservation reservation) {
		super();
		
		this.token = token;
		this.amount = amount;
		this.reservation = reservation;
	}
	
	public Transaction(int id, String token, float amount) {
		super();
		this.id = id;
		this.token = token;
		this.amount = amount;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@OneToOne(cascade = {CascadeType.REMOVE,CascadeType.MERGE} )
	public Reservation getReservation() {
		return reservation;
	}
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
  
}
