package tn.esprit.twin1.brogrammers.eventify.Eventify.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.AnswerBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.AnswerBusinessRemote;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Answer;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Attribut;

/**
 * Session Bean implementation class AnswerBusiness
 */
@Stateless
public class AnswerBusiness implements AnswerBusinessRemote, AnswerBusinessLocal {

	
	@PersistenceContext(unitName = "Eventify-ejb")
	EntityManager entityManager;

	
    /**
     * Default constructor. 
     */
    public AnswerBusiness() {
    }


	@Override
	public void createAnswer(Answer answer) {
		try {
	        entityManager.persist(answer);

		} catch (Exception e) {
			System.err.println("Failed to Add");
		}
		
	}


	@Override
	public void updateAnswer(Answer answer) {
		try {
	        entityManager.merge(answer);

		} catch (Exception e) {
			System.err.println("Failed to Modify");
		}
		
	}

	//A Verifier
	@Override
	public boolean deleteAnswer(int id) {
		try {
			entityManager.remove(getAnswerByUserId(id));
			return true;
		} catch (Exception e) {
			System.err.println("Failed To Delete");
			return false;
		}
	}


	@Override
	public List<Answer> getAnswerByUserId(int id) {
		try {
			Query query = entityManager.
					createQuery("SELECT new Answer(a.answerPK,a.answer,a.dateAnswer) FROM Answer a WHERE a.answerPK.idUser=:param")
					.setParameter("param", id);
			 return (List<Answer>) query.getResultList();

		} catch (Exception e) {
			System.err.println("Cant Find Answer");
			return new ArrayList<Answer>();
		}
	}


	@Override
	public List<Answer> getAnswerByAttributId(int id) {
		try {
			Query query = entityManager.
					createQuery("SELECT new Answer(a.answerPK,a.answer,a.dateAnswer) FROM Answer a WHERE a.answerPK.idAttribut=:param")
					.setParameter("param", id);
			 return (List<Answer>) query.getResultList();

		} catch (Exception e) {
			System.err.println("Cant Find Answer");
			return new ArrayList<Answer>();
		}
	}


	@Override
	public List<Answer> getAnswerByAttributIdAndUserId(int userId, int attributId) {
		try {
			Query query = entityManager.
					createQuery("SELECT new Answer(a.answerPK,a.answer,a.dateAnswer) "
							+ "FROM Answer a "
							+ "WHERE a.answerPK.idAttribut=:attributId AND "
							+ "a.answerPK.idUser=:userId")
					.setParameter("attributId", attributId)
					.setParameter("userId", userId);
			 return (List<Answer>) query.getResultList();

		} catch (Exception e) {
			System.err.println("Cant Find Answer");
			return new ArrayList<Answer>();
		}
	
	}



}
