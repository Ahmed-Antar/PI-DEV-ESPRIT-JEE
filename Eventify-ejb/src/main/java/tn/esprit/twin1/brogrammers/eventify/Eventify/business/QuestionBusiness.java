package tn.esprit.twin1.brogrammers.eventify.Eventify.business;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.QuestionBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.QuestionBusinessRemote;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Attribut;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Event;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Question;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.enumeration.QuestionCategory;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.enumeration.QuestionType;



/**
 * Session Bean implementation class QuestionBusiness
 */
@Stateless
public class QuestionBusiness implements QuestionBusinessRemote, QuestionBusinessLocal {

	
	@PersistenceContext(unitName = "Eventify-ejb")
	EntityManager entityManager;

    /**
     * Default constructor. 
     */
    public QuestionBusiness() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Question createQuestion(Question question) {

		try {
			entityManager.persist(question);
			return question;

		} catch (Exception e) {
			System.err.println("Cant find Event");
			return null;
		}
		
	}

	@Override
	public boolean updateQuestion(Question question) {
		if(getQuestionById(question.getId())!=null){
			entityManager.merge(question);
			return true;
		}
		else
			return false;
		
		
	}

	@Override
	public boolean deleteQuestion(int id) {
		if(getQuestionById(id)!=null){
			entityManager.remove(id);
			return true;
		}
		else
			return false;
		
	}

	@Override
	public Question getQuestionById(int id) {
		Query query = entityManager.
				createQuery("SELECT new Question("
						+ "q.id,"
						+ "q.questionDescription,"
						+ "q.questionType,"
						+ "q.questionCategory,"
						+ "q.status,"
						+ "q.questionDate,"
						+ "q.order) FROM Question q WHERE q.id=:param")
				.setParameter("param", id);
		 return (Question) query.getSingleResult();
	}

	@Override
	public List<Attribut> getMyAttributs(int id) {
		 Query query = entityManager.createQuery("SELECT new Attribut(a.id,a.attributValue,a.duplicated) FROM Question q JOIN q.attributs a WHERE q.id=:param");
	    return (List<Attribut>) query.setParameter("param", id).getResultList();

	}

    
    
    
    

}
