package tn.esprit.twin1.brogrammers.eventify.Eventify.ressource;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.swing.event.DocumentEvent.EventType;
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

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.EventBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Event;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Question;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.User;
import tn.esprit.twin1.brogrammers.eventify.Eventify.util.CognitiveServiceTextAnalytics;

@Path("events")
@RequestScoped
public class EventResource {

	@EJB
	EventBusinessLocal eventBusiness;
	
	
	
	/*
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllEvents()
	{
		System.out.println("************************************");
		System.out.println(eventBusiness.getAllEvents().toString());
		System.out.println("************************************");

		return Response.status(Status.FOUND).entity(eventBusiness.getAllEvents()).build();
	}
	*/
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response findEventById(@PathParam("id") int id)
	{
		Event e = eventBusiness.findEventById(id);
		if(e==null)
		{
			return Response.status(Status.NOT_FOUND).build();
		}
		else
		{
			return Response.status(Status.OK).entity(e).build();
		}
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addEvent(Event event)
	{
		try {
			eventBusiness.create(event);
			return Response.status(Status.CREATED).build();

		} catch (Exception e) {
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response updateEvent(Event event,@PathParam("id")int id){
		eventBusiness.updateEvent(event);
		return Response.status(Status.OK).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteEvent(@PathParam(value="id")int id){
		boolean b= eventBusiness.deleteEvent(id);
		if(b)
		{
			System.err.println("*************"+id+"***************");
			return Response.status(Status.OK).build();
		}
	return Response.status(Response.Status.BAD_REQUEST).build();
		
	}
	
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response SearchForEvents(@QueryParam(value="search")String search,
									@QueryParam(value="type")String type,
									@QueryParam(value="category")String category,
									@QueryParam(value="organization")int organization,
									@QueryParam(value="longitude") String longitude,
									@QueryParam(value="latitude") String latitude,
									@QueryParam(value="userId") int userId,
									@QueryParam(value="popular") int popular
									
			){
		
		List<Event> liste=null;
		if(search!=null && type==null && category==null && longitude==null && latitude ==null && organization==0 && userId==0 && popular==0)
		liste = eventBusiness.SearchForEvents(search);
		else if (search==null && type!=null && category==null && longitude==null && latitude ==null && organization==0 && userId==0 && popular==0)
			liste = eventBusiness.findEventByType(EventType.class.cast(type));
		else if (search==null && type==null && category!=null && longitude==null && latitude ==null && organization==0 && userId==0 && popular==0)
			liste = eventBusiness.findEventByCategory(category);
		/*else if (search==null && type==null && category==null && organization>0)
			liste = eventBusiness.findEventByOrganization(organization);*/
		else if (search==null && type==null && category==null && longitude!=null && latitude !=null && organization==0 && userId==0 && popular==0)
			liste= eventBusiness.findEventNearBy(Double.parseDouble(longitude), Double.parseDouble(latitude));
		else if (search==null && type==null && category==null && longitude==null && latitude ==null && organization==0 && userId!=0 && popular==0)
			liste= eventBusiness.getFavoriteEventByUser(userId);
		else if (search==null && type==null && category==null && longitude==null && latitude ==null && organization==0 && userId==0 && popular!=0)
			liste= eventBusiness.getPopularEvents();
		else if (search==null && type==null && category==null && longitude==null && latitude ==null && organization==0 && userId==0 && popular==0)
				 liste= eventBusiness.getAllEvents();

		return Response.status(Status.OK).entity(liste).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/questions")
	public Response getMyQuetions(@PathParam("id")int id){
		return Response.status(Status.OK).entity(eventBusiness.getMyQuestions(id)).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("test")
	public List<User> Notify(){
		return eventBusiness.NotifyUsersForSoonEvent();
	}

	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/rate")
	public Response GetMyRate(@PathParam("id")int id)
	{
		return Response.status(Status.OK).entity(eventBusiness.getMyRate(id)).build();
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/tickets")
	public Response getMyTickets(@PathParam("id")int id){
		return Response.status(Status.OK).entity(eventBusiness.getMyTickets(id)).build();
	}

	
	
	
	

}
