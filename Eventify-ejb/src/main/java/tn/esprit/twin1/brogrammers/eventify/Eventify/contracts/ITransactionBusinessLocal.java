package tn.esprit.twin1.brogrammers.eventify.Eventify.contracts;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Transaction;

@Local
public interface ITransactionBusinessLocal {
	List<Transaction> getAllTransactions();

	public boolean create(Transaction transaction);

	public boolean updateTransaction(Transaction transaction);

	public boolean deleteTransactionById(int id);

	public Transaction findTransactionById(int idtransaction);
}
