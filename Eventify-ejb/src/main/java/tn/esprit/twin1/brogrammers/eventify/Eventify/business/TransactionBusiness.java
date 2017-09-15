package tn.esprit.twin1.brogrammers.eventify.Eventify.business;

import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.IReservationBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.ITransactionBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.ITransactionBusinessRemote;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Reservation;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Transaction;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.User;

/**
 * Session Bean implementation class TransactionBusiness
 */
@Stateless
@LocalBean
public class TransactionBusiness implements ITransactionBusinessRemote, ITransactionBusinessLocal {

	@PersistenceContext(unitName = "Eventify-ejb")
	EntityManager entityManager;
	@EJB
	IReservationBusinessLocal reservationbusinessloccal;

	@Override
	public List<Transaction> getAllTransactions() {
		List<Transaction> transaction = (List<Transaction>) entityManager
				.createQuery("SELECT new Transaction(t.id,t.token,t.amount,reservation) "
						+ "FROM Transaction t JOIN t.reservation reservation")
				.getResultList();

		for (Transaction transactions : transaction) {

			Reservation reservation = reservationbusinessloccal
					.findReservationById(transactions.getReservation().getId());
			transactions.setReservation(reservation);

		}
		return transaction;

	}

	@Override
	public boolean create(Transaction transaction) {
		entityManager.persist(transaction);
		return true;
		

	}

	@Override
	public boolean updateTransaction(Transaction transaction) {
		try {entityManager.merge(transaction);
		return true;}
		catch (Exception e) {
			System.err.println("Failed to Add");
			return false;
		}

	}

	@Override
	public boolean deleteTransactionById(int id) {
		entityManager.remove(entityManager.find(Transaction.class, id));
		return true;
	}

	@Override
	public Transaction findTransactionById(int idtransaction) {
		Query query = entityManager.createQuery(
				"SELECT new Transaction(t.id,t.token,t.amount) " + "FROM Transaction t WHERE t.id=:idtran");
		Transaction t = (Transaction) query.setParameter("idtran", idtransaction).getSingleResult();
		// r.setUser(userbusiness.findUserById(r.getUser().getId()));
		return t;
	}
}
