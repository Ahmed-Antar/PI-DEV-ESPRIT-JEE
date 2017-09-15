package tn.esprit.twin1.brogrammers.eventify.Eventify.contracts;

import javax.ejb.Local;

@Local
public interface IBnaqueBusinessLocal {
	public boolean checkPaymentDtails(String fullName, String cardNumber, String expMonth, String expYear, int ccv);
	public boolean updateAmount(String cardNumber, double amount);

}
