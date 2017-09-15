package tn.esprit.twin1.brogrammers.eventify.Eventify.contracts;

import javax.ejb.Remote;

@Remote
public interface IBnaqueBusinessRemote {
	public boolean checkPaymentDtails(String fullName, String cardNumber, String expMonth, String expYear, int ccv);
	public boolean updateAmount(String cardNumber, double amount);

}
