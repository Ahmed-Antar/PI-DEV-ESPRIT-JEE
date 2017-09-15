package tn.esprit.twin1.brogrammers.eventify.Eventify.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Banque
 *
 */
@Entity

public class Banque implements Serializable {

	private int id;
	private String fullName;
	private String cardNumber;
	private String expMonth;
	private String expYear;
	private int ccv;
	private double amount;
	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Banque(int id, String fullName, String cardNumber, String expMonth, String expYear, int ccv, double amount) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.cardNumber = cardNumber;
		this.expMonth = expMonth;
		this.expYear = expYear;
		this.ccv = ccv;
		this.amount = amount;
	}

	public Banque(String fullName, String cardNumber, String expMonth, String expYear, int ccv) {
		super();
		this.fullName = fullName;
		this.cardNumber = cardNumber;
		this.expMonth = expMonth;
		this.expYear = expYear;
		this.ccv = ccv;
	}
	
	public Banque(int id) {
		super();
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpMonth() {
		return expMonth;
	}

	public void setExpMonth(String expMonth) {
		this.expMonth = expMonth;
	}

	public String getExpYear() {
		return expYear;
	}

	public void setExpYear(String expYear) {
		this.expYear = expYear;
	}

	public int getCcv() {
		return ccv;
	}

	public void setCcv(int ccv) {
		this.ccv = ccv;
	}

	private static final long serialVersionUID = 1L;

	public Banque() {
		super();
	}
   
}
