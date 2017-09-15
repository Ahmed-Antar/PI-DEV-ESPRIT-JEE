package tn.esprit.twin1.brogrammers.eventify.Eventify.contracts;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Notification;

@Remote
public interface NotificationBusinessRemote {
	public List<Notification> getNotificationsByUser(int id);

}
