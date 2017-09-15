package tn.esprit.twin1.brogrammers.eventify.Eventify.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.EventBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.OrganizationBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.OrganizerBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.TaskBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.TaskBusinessRemote;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Organizer;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Task;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.enumeration.OrganizerState;;

/**
 * Session Bean implementation class TaskBusiness
 */

@Stateless
public class TaskBusiness implements TaskBusinessRemote, TaskBusinessLocal {

	/**
	 * Default constructor.
	 */
	@PersistenceContext(unitName = "Eventify-ejb")
	EntityManager entityManager;

	@EJB
	OrganizationBusinessLocal organizationBusiness;

	@EJB
	OrganizerBusinessLocal organizerBusinessLocal;
	
	@EJB
	EventBusinessLocal eventBusinessLocal;
	public TaskBusiness() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void updateTask(Task task) {

		entityManager.merge(task);

	}

	@Override
	public void deleteTask(int id) {
		Task t = findTaskByID(id);
		entityManager.remove(entityManager.merge(t));

	}

	@Override
	public Task findTaskByID(int id) {
		Query query = entityManager
				.createQuery("SELECT new Task(t.id,t.taskTitle,t.taskDescription,t.taskStatus,t.createdAt) "
						+ "FROM Task t WHERE t.id=:param");
		return (Task) query.setParameter("param", id).getSingleResult();

	}

	// ok
	@Override
	public List<Task> getAllTasksByEventID(int idEvent) {
		Query query = entityManager
				.createQuery(
						"SELECT new Task(t.id,t.taskTitle,t.taskDescription,t.taskStatus,t.createdAt) FROM Task t WHERE t.event.id = :idEvent")
				.setParameter("idEvent", idEvent);
		return (List<Task>) query.getResultList();

	}

	@Override
	public List<Task> GetTasksByOrganizer(int idOrganizer) {
		Query query = entityManager
				.createQuery(
						"SELECT new Task(t.id,t.taskTitle,t.taskDescription,t.taskStatus,"
						+ "t.createdAt,e,organizer) FROM Task t "
						+ "JOIN  t.organizer organizer "
						+ "JOIN t.event e "
						+ "WHERE organizer.organizerPK.idUser =:param")
				.setParameter("param", idOrganizer);
		
		List<Task> tasks = (List<Task>) query.getResultList();
		
		for (Task task : tasks) {
			task.setOrganizer(organizerBusinessLocal.getAllOrganizersByUserIdAndOrganizationId
					(task.getOrganizer().getOrganizerPK().getIdUser(),
							task.getOrganizer().getOrganizerPK().getIdOrganization()));
			
			task.setEvent(eventBusinessLocal.findEventById(task.getEvent().getId()));
		}
		
		
		return tasks;
	}

	@Override
	public void assignTaskToOrgnizer(int idOrgnizer, int Taskid) {
		Task t = findTaskByID(Taskid);
		System.out.println("***************************"+t.getTaskTitle());

		 Query query = entityManager.
				createQuery("SELECT new Organizer(o.organizerPK) FROM Organizer o WHERE o.organizerPK.idUser=:param")
				.setParameter("param", idOrgnizer);
		 Organizer o = (Organizer) query.getSingleResult();
		 
		System.out.println("***************************"+o.getState().toString());
		t.setOrganizer(o);
		entityManager.merge(t);

	}

	
	public void taskStatusCompleted(int Taskid) {
		// Organizer o=entityManager.find(Organizer.class,idOrgnizer);
		Task t = findTaskByID(Taskid);

		
		t.setTaskStatus(2);
		entityManager.merge(t);

	}

	@Override
	public int getTaskStatus(int taskID) {
		Task t = findTaskByID(taskID);

		return t.getTaskStatus();
	}

	@Override
	public void createTask(Task task) {
		entityManager.persist(task);

	}

}
