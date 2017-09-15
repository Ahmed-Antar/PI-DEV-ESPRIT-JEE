package tn.esprit.twin1.brogrammers.eventify.Eventify.domain;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Attributs
 *
 */
@Entity

public class Attribut implements Serializable {

	   
	
	private int id;
	private String attributValue;
	private boolean duplicated;
	private static final long serialVersionUID = 1L;
	/* Foreign Key */
	private List<Answer> answers;
	private Question question;
	
	

	public Attribut() {
		super();
	}
	
	
	
	public Attribut(int id, String attributValue, boolean duplicated) {
		super();
		this.id = id;
		this.attributValue = attributValue;
		this.duplicated = duplicated;
	}
	
	
	



	public Attribut(String attributValue, boolean duplicated, Question question) {
		super();
		this.attributValue = attributValue;
		this.duplicated = duplicated;
		this.question = question;
	}



	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getAttributValue() {
		return this.attributValue;
	}

	public void setAttributValue(String attributValue) {
		this.attributValue = attributValue;
	}

	
	
	public boolean isDuplicated() {
		return duplicated;
	}



	public void setDuplicated(boolean duplicated) {
		this.duplicated = duplicated;
	}



	@OneToMany(mappedBy="attribut",cascade=CascadeType.ALL)
	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
   
	
}
