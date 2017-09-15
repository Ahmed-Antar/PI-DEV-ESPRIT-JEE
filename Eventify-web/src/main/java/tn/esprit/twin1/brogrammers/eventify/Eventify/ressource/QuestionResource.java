package tn.esprit.twin1.brogrammers.eventify.Eventify.ressource;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.QuestionBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Question;

@Path("questions")
@RequestScoped
public class QuestionResource {
	
	@EJB
	QuestionBusinessLocal questionBusiness;
	
	

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response getQuestionById(@PathParam("id") int id){
		System.err.println("**********************************" + id + "**********************************");
		Question question =questionBusiness.getQuestionById(id);
		
		if(question!=null){
			return Response.status(Status.OK).entity(question).build();
		}
		else
			return Response.status(Status.NOT_FOUND).build();
		
		
	}

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addQuestion(Question questions){
		Question q = questionBusiness.createQuestion(questions);
		return Response.status(Status.CREATED).entity(q).build();
		
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response updateQuestion(Question questions,@PathParam(value="id")int id){
		if(questionBusiness.updateQuestion(questions))
			return Response.status(Status.OK).build();
		else
			return Response.status(Status.NOT_MODIFIED).build();
		
	}

	@DELETE
	@Path("{id}")
	public Response deleteQuestion(@PathParam(value="id")int id){
		if(questionBusiness.deleteQuestion(id))
			return Response.status(Status.OK).build();
		else
			return Response.status(Status.NOT_FOUND).build();
		
	}

	@GET
	@Path("{id}/attributs")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMyAttributs(@PathParam("id") int id){
		return Response.status(Status.OK).entity(questionBusiness.getMyAttributs(id)).build();

	}


	
}
