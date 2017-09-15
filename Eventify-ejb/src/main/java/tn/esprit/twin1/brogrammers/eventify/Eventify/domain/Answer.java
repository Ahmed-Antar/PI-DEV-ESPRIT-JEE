package tn.esprit.twin1.brogrammers.eventify.Eventify.domain;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Answers
 *
 */
@Entity

public class Answer implements Serializable {

	
	private AnswerPK answerPK;

	private User user;
	private Attribut attribut;

	private String answer;
	private Date dateAnswer;


	private static final long serialVersionUID = 1L;

	public Answer() {
		super();
	}

	
	
	public Answer(AnswerPK answerPK, String answer, Date dateAnswer) {
		super();
		this.answerPK = answerPK;
		this.answer = answer;
		this.dateAnswer = dateAnswer;
	}

	
	


	public Answer(AnswerPK answerPK, User user, Attribut attribut, String answer, Date dateAnswer) {
		super();
		this.answerPK = answerPK;
		this.user = user;
		this.attribut = attribut;
		this.answer = answer;
		this.dateAnswer = dateAnswer;
	}



	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Date getDateAnswer() {
		return this.dateAnswer;
	}

	public void setDateAnswer(Date dateAnswer) {
		this.dateAnswer = dateAnswer;
	}

	@EmbeddedId
	public AnswerPK getAnswerPK() {
		return answerPK;
	}

	public void setAnswerPK(AnswerPK answerPK) {
		this.answerPK = answerPK;
	}

	@ManyToOne
	@JoinColumn(insertable=false,updatable=false,name="idUser",referencedColumnName="id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne
	@JoinColumn(insertable=false,updatable=false,name="idAttribut",referencedColumnName="id")
	public Attribut getAttribut() {
		return attribut;
	}

	public void setAttribut(Attribut attribut) {
		this.attribut = attribut;
	}

}
