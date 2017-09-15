package tn.esprit.twin1.brogrammers.eventify.Eventify.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.NotificationBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.NotificationBusinessRemote;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.UserBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Event;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Notification;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Ticket;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.User;

/**
 * Session Bean implementation class NotificationBusiness
 */
@Stateless
@LocalBean
public class NotificationBusiness implements NotificationBusinessRemote, NotificationBusinessLocal {

    /**
     * Default constructor. 
     */
	
	@PersistenceContext(unitName = "Eventify-ejb")
	EntityManager entityManager;


	@EJB
	UserBusinessLocal userBusiness;
	
    public NotificationBusiness() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<Notification> getNotificationsByUser(int id) {

		List<Notification> notifications = (List<Notification>) entityManager
				.createQuery("SELECT new Notification(n.id,n.notificationTitle,n.notificationDescription,n.notificationDate,n.notificationStatus,user) "
						+ "FROM Notification n JOIN n.user user "
						+ "WHERE user.id = :id")
				.setParameter("id", id)
				.getResultList();
		
		for (Notification notification : notifications) {

			User user = userBusiness.findUserById(notification.getUser().getId());
			notification.setUser(user);
		}
		return notifications;
	}

}
