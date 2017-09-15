package tn.esprit.twin1.brogrammers.eventify.Eventify.contracts;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Attribut;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Event;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Question;


@Local
public interface QuestionBusinessLocal {
	
	public Question createQuestion(Question question);
	public boolean updateQuestion(Question question);
	public boolean deleteQuestion(int id);
	public Question getQuestionById(int id);
	public List<Attribut> getMyAttributs(int id);

	

}
