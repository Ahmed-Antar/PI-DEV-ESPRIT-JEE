package tn.esprit.twin1.brogrammers.eventify.Eventify.ressource;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.twin1.brogrammers.eventify.Eventify.business.DiscussionBusiness;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.DiscussionBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.TaskBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.UserBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Discussion;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Task;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.User;

@Path("Discussions")
@RequestScoped
public class DiscussionResource {
	@EJB
	DiscussionBusinessLocal discussionBusiness;

	@EJB
	TaskBusinessLocal taskBusiness;
	
	@EJB
	UserBusinessLocal userBusiness;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response AddMyDiscussion(Discussion discussion) {
		discussionBusiness.addDiscussion(discussion);
		return Response.status(Status.CREATED).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response getDiscussionsByTask(@PathParam("id")int id)
	{
		List<Discussion> discussions = discussionBusiness.getDiscussionsByTask(id);
		
		for (Discussion discussion : discussions) {
			User u = userBusiness.findUserById(discussion.getUser().getId());
			Task t = taskBusiness.findTaskByID(discussion.getTask().getId());
			
			discussion.setTask(t);
			discussion.setUser(u);
		}
		
		return Response.status(Status.OK).entity(discussions).build();
	}

}
