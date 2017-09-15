package tn.esprit.twin1.brogrammers.eventify.Eventify.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Report
 *
 */
@Entity

public class Report implements Serializable {

	
	private int id;
	private String subject;
	private String content;
	private int state;
	private Event event;
	private User user;
	private Date reportDate;
	private User userWhoReport;
	
	private static final long serialVersionUID = 1L;

	public Report() {
		super();
	}


	public Report(int id, String subject, String content, int state, Event event, User user, Date reportDate,
			User userWhoReport) {
		super();
		this.id = id;
		this.subject = subject;
		this.content = content;
		this.state = state;
		this.event = event;
		this.user = user;
		this.reportDate = reportDate;
		this.userWhoReport = userWhoReport;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@ManyToOne
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@ManyToOne
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public Date getReportDate() {
		return reportDate;
	}


	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}


	@ManyToOne
	public User getUserWhoReport() {
		return userWhoReport;
	}


	public void setUserWhoReport(User userWhoReport) {
		this.userWhoReport = userWhoReport;
	}
   
	
}
