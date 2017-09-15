package tn.esprit.twin1.brogrammers.eventify.Eventify.ressource;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.NotificationBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Event;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Notification;

@Path("notifications")
@RequestScoped
public class NotificationResource {

	@EJB
	NotificationBusinessLocal notificationBusiness;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response findNotificationsByUser(@PathParam("id") int id)
	{
		List<Notification> notifications = notificationBusiness.getNotificationsByUser(id);
		if(notifications==null)
		{
			return Response.status(Status.NOT_FOUND).build();
		}
		else
		{
			return Response.status(Status.OK).entity(notifications).build();
		}
	}

}
