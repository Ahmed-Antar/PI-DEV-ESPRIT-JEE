package tn.esprit.twin1.brogrammers.eventify.Eventify.contracts;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Notification;

@Local
public interface NotificationBusinessLocal {

	public List<Notification> getNotificationsByUser(int id);
}
