package tn.esprit.twin1.brogrammers.eventify.Eventify.contracts;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Attribut;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Question;

@Remote
public interface QuestionBusinessRemote {
	public Question createQuestion(Question question);
	public boolean updateQuestion(Question question);
	public boolean deleteQuestion(int id);
	public Question getQuestionById(int id);
	public List<Attribut> getMyAttributs(int id);

}
