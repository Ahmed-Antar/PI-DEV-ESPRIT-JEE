package tn.esprit.twin1.brogrammers.eventify.Eventify.ressource;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;



import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.CommentBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Comment;



@Path("comments")
@RequestScoped
public class CommentResource {
	
	
	
	@EJB
	CommentBusinessLocal CommentBusiness ;
	

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addRate(Comment comment)
	{
		
		try {
			CommentBusiness.AddComment(comment);
			return Response.status(Status.CREATED).build();
		    } 
		catch (Exception e)
		{
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
	}
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateRate(Comment comment){
		try {
			CommentBusiness.updateComment(comment);
			return Response.status(Status.OK).build();
		} catch (Exception e) {
			return Response.status(Status.NOT_MODIFIED).build();
		}
	}
	

	@DELETE
	public Response deleteRate(@QueryParam(value="iduser")int idUser,@QueryParam(value="idevent")int idEvent)
	{
		
		
		if(CommentBusiness.DeleteComment(idUser, idEvent))
			{
			System.out.println("deleted");
			return Response.status(Status.OK).build();
			}
		
		else
			{
			System.out.println("not deleted");
			return Response.status(Status.NOT_FOUND).build();
			}
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response getCommentsByEvent(@PathParam("id")int idEvent)
	{
		
		List<Comment> comments=  CommentBusiness.getCommentsByEvent(idEvent);
		return Response.status(Status.OK).entity(comments).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{idUser}/{idEvent}")
	public Response GetCommentByUserIdAndEventId(@PathParam("idUser")int idUser,@PathParam("idEvent")int idEvent)
	{
		
		 Comment comments=  CommentBusiness.GetCommentByUserIdAndEventId(idUser, idEvent);
		 
		return Response.status(Status.OK).entity(comments).build();
	}
	

}
