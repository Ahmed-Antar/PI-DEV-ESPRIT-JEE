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

import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.RateBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Event;

import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Rate;
import tn.esprit.twin1.brogrammers.eventify.Eventify.util.SpellCheck;

@Path("rates")
@RequestScoped
public class RateResource {
	
	@EJB
	RateBusinessLocal RateBusiness;
	
	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addRate(Rate rate)
	{
		
		try {
			RateBusiness.createRate(rate);
			return Response.status(Status.CREATED).build();
		    } 
		catch (Exception e)
		{
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateRate(Rate rate){
		try {
			RateBusiness.modifyRate(rate);
			return Response.status(Status.OK).build();
		} catch (Exception e) {
			return Response.status(Status.NOT_MODIFIED).build();
		}

	}
	
	
	@DELETE
	public Response deleteRate(@QueryParam(value="iduser")int idUser,@QueryParam(value="idevent")int idEvent)
	{
		
		
		if(RateBusiness.deleteRateByUser(idUser,idEvent))
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
	public Response getAllRatesByUserIdAndRatesId(@QueryParam(value="iduser")int idUser,@QueryParam(value="idevent")int idEvent)
	{
		
		if(idUser!=0 && idEvent==0){
			List<Rate> liste=RateBusiness.GetAllRatesOfUser(idUser);
			return Response.status(Status.OK).entity(liste).build();
		}
		else if (idUser!=0 && idEvent!=0){
			Rate r=RateBusiness.getRateByUserIdAndEventId(idUser, idEvent);
			return Response.status(Status.OK).entity(r).build();
		}
		else {
			List<Rate> liste=RateBusiness.GetAllRatesOfEvent(idEvent);
			return Response.status(Status.OK).entity(liste).build();
		}

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{idevent}")
	public Response CalculRate(@PathParam(value="idevent")int idEvent)
	{
		try {
			float rate = RateBusiness.CalculRate(idEvent);
			System.out.println("\n\n\n\n\n\n\n\nMoyenne Rate : "+rate+"\n\n\n\n\n\n\n\n");
			return Response.status(Status.OK).build();
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("cant calculate this shit !!!!!!!!!!");
		return Response.status(Status.BAD_REQUEST).build();
	}
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/best")
	public Response BestEvent()
	{
		Event BestEvent = RateBusiness.BestEventRated();
		SpellCheck.checkWord("Bill Gatas");
		
		return Response.status(Status.OK).entity(BestEvent).build();
		
	}
	
	
	
	
	
	
	
	
}











