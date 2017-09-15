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

import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.AnswerBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Answer;

@Path("answers")
@RequestScoped
public class AnswerResource {
	@EJB
	AnswerBusinessLocal answerBusiness;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addAnswer(Answer answer){
		
		try {
			answerBusiness.createAnswer(answer);
			return Response.status(Status.CREATED).build();
		} catch (Exception e) {
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
	}
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response updateAnswer(Answer answer,@PathParam(value="id")int id){
		try {
			answerBusiness.updateAnswer(answer);
			return Response.status(Status.OK).build();
		} catch (Exception e) {
			return Response.status(Status.NOT_MODIFIED).build();
		}

	}
	
	
	@DELETE
	@Path("{id}")
	public Response deleteAnswer(@PathParam(value="id")int id){
		
		boolean b = answerBusiness.deleteAnswer(id);
		if(b)
			return Response.status(Status.OK).build();
		else
			return Response.status(Status.NOT_FOUND).build();

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAnswerByUserId(@QueryParam("userId")int userId,
			@QueryParam("attributId")int attributId){
		List answers = (List) answerBusiness.getAnswerByAttributId(attributId);
		System.out.println(answers.toString());
		if(answers.size()!=0)
			return Response.status(Status.OK).entity(answers).build();
		else
			return Response.status(Status.NOT_FOUND).build();

	}

	

}
