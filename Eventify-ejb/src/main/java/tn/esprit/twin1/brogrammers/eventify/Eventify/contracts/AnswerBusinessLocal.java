package tn.esprit.twin1.brogrammers.eventify.Eventify.contracts;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Answer;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Attribut;

@Local
public interface AnswerBusinessLocal {
	
	public void createAnswer(Answer answer);
	public void updateAnswer(Answer answer);
	public boolean deleteAnswer(int id);
	public List<Answer> getAnswerByUserId(int id);
	public List<Answer> getAnswerByAttributId(int id);
	public List<Answer> getAnswerByAttributIdAndUserId(int userId,int attributId);

}
