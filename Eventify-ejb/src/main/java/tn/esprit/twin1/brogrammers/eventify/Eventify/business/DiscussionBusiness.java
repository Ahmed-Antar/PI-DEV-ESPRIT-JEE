package tn.esprit.twin1.brogrammers.eventify.Eventify.business;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.DiscussionBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.DiscussionBusinessRemote;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Discussion;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Task;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.User;

@Stateless
public class DiscussionBusiness implements DiscussionBusinessLocal, DiscussionBusinessRemote {

	@PersistenceContext(unitName = "Eventify-ejb")
	EntityManager entityManager;

	public DiscussionBusiness() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addDiscussion(Discussion discussion) {
		entityManager.persist(discussion);
	}

	@Override
	public List<Discussion> getDiscussionsByTask(int taskid) {
		Query query = entityManager
				.createQuery("SELECT new Discussion(d.id, d.messagedata,d.messageDate, d.status, user, task) "
						+ "FROM Discussion d " + "JOIN  d.user user " + "JOIN d.task task " + "WHERE task.id =:param"
						+ " ORDER BY d.messageDate")
				.setParameter("param", taskid);
		List<Discussion> Discussions = (List<Discussion>) query.getResultList();
		;
		return Discussions;
	}

	@Override
	public int getNbrDiscussionsPerTask(int taskid) {
		Query query = entityManager
				.createQuery("SELECT new Discussion(d.id, d.messagedata,d.messageDate, d.status, user, task)"
						+ "FROM Task t " + "JOIN  t.user user " + "JOIN t.task task " + "WHERE user.idUser =:param"
						+ "ORDER BY d.messageDate")
				.setParameter("param", taskid);
		List<Discussion> Discussions = (List<Discussion>) query.getResultList();
		;
		return Discussions.size();
	}

}
