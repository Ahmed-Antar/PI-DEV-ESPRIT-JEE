package tn.esprit.twin1.brogrammers.eventify.Eventify.domain;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.enumeration.QuestionCategory;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.enumeration.QuestionType;

/**
 * Entity implementation class for Entity: Questions
 *
 */
@Entity
public class Question implements Serializable {

	   
	private int id;
	private String questionDescription;
	private QuestionType questionType;
	private QuestionCategory questionCategory;
	private int status;
	private Date questionDate;
	private int order;
	
	private static final long serialVersionUID = 1L;

	private Event event;
	
	private List<Attribut> attributs;

	
	public Question() {
		super();
	}   
	
	
	
	public Question(String questionDescription, QuestionType questionType, QuestionCategory questionCategory, int status,
			Date questionDate, int order) {
		super();
		this.questionDescription = questionDescription;
		this.questionType = questionType;
		this.questionCategory = questionCategory;
		this.status = status;
		this.questionDate = questionDate;
		this.order = order;
	}

	


	public Question(int id, String questionDescription, QuestionType questionType, QuestionCategory questionCategory,
			int status, Date questionDate, int order) {
		super();
		this.id = id;
		this.questionDescription = questionDescription;
		this.questionType = questionType;
		this.questionCategory = questionCategory;
		this.status = status;
		this.questionDate = questionDate;
		this.order = order;
	}
	
	



	public Question(int id) {
		super();
		this.id = id;
	}



	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getQuestionDescription() {
		return this.questionDescription;
	}

	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}   
	
	@Enumerated(EnumType.STRING)
	public QuestionType getQuestionType() {
		return this.questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}   
	
	@Enumerated(EnumType.STRING)
	public QuestionCategory getQuestionCategory() {
		return this.questionCategory;
	}

	public void setQuestionCategory(QuestionCategory questionCategory) {
		this.questionCategory = questionCategory;
	}   
	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}   
	public Date getQuestionDate() {
		return this.questionDate;
	}

	public void setQuestionDate(Date questionDate) {
		this.questionDate = questionDate;
	}   
	
	@Column(name = "`order`")
	public int getOrder() {
		return this.order;
	}


	public void setOrder(int order) {
		this.order = order;
	}

	/* Foreign Key Start */
	
	@ManyToOne(fetch=FetchType.LAZY)
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}

	
	//Assocation With Attributs
	
	@OneToMany(mappedBy="question",cascade=CascadeType.ALL)
	public List<Attribut> getAttributs() {
		return attributs;
	}

	public void setAttributs(List<Attribut> attributs) {
		this.attributs = attributs;
	}
	
	
	
	/* Foreign Key End  */
   
}
