package tn.esprit.twin1.brogrammers.eventify.Eventify.business;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.IBnaqueBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.IBnaqueBusinessRemote;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Banque;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Reservation;

/**
 * Session Bean implementation class IBnaqueBusiness
 */
@Stateless
@LocalBean
public class BnaqueBusiness implements IBnaqueBusinessRemote, IBnaqueBusinessLocal {

	@PersistenceContext(unitName = "Eventify-ejb")
	EntityManager entityManager;

	/**
	 * Default constructor.
	 */

	public BnaqueBusiness() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean checkPaymentDtails(String fullName, String cardNumber, String expMonth, String expYear, int ccv) {

		try {
			Query query = entityManager.createQuery("SELECT new Banque(b.id) "
					+ "FROM Banque b WHERE (b.fullName=:fName AND b.cardNumber=:cNumber AND b.expMonth=:expM"
					+ " AND b.expYear=:expY AND b.ccv=:cv) ");

			Banque b = (Banque) query.setParameter("fName", fullName).setParameter("cNumber", cardNumber)
					.setParameter("expM", expMonth).setParameter("expY", expYear).setParameter("cv", ccv)
					.getSingleResult();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public boolean updateAmount(String cardNumber, double amount) {
		try {
			Query query = entityManager
					.createQuery("UPDATE Banque SET amount = amount-:amount WHERE cardNumber =:cardNumber"
							+ "")
					.setParameter("cardNumber", cardNumber).setParameter("amount", amount);
			query.executeUpdate();
			return true;
		} catch (Exception e) {
			System.err.println("Can't update! :("+e);
			return false;
		}
	}

}
