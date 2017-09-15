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

import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.OrganizerBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Organizer;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Wishlist;

@Path("organizer")
@RequestScoped
public class OrganizerResource {
	@EJB
	OrganizerBusinessLocal organizerBusiness;
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateOrganization(Organizer organizer){
		organizerBusiness.updateOrganizer(organizer);
		return Response.status(Status.OK).build();
	}
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response assignOrganizer(Organizer organizer){
		organizerBusiness.assignOrganizer(organizer);
		return Response.status(Status.OK).build();
	}

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllOrganizersByUserIdAndOrganizationId(@QueryParam(value="idUser")int idUser,@QueryParam(value="idOrganization")int idOrganization)
	{
		
		if(idUser!=0 && idOrganization==0){
			List<Organizer> liste=organizerBusiness.getAllOrganizersByUser(idUser);
			return Response.status(Status.OK).entity(liste).build();
		}
		else if (idUser!=0 && idOrganization!=0){
			Organizer r=organizerBusiness.getAllOrganizersByUserIdAndOrganizationId(idUser, idOrganization);
			return Response.status(Status.OK).entity(r).build();
		}
		else {
			List<Organizer> liste=organizerBusiness.getAllOrganizersByOrganization(idOrganization);
			return Response.status(Status.OK).entity(liste).build();
		}

	}
	
	
	@PUT
	@Path("accept")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response AcceptOrganizerRole(@QueryParam(value="UserId")int UserId, @QueryParam(value="OrganizationId")int OrganizationId){
		organizerBusiness.AcceptOrganizerRole(UserId, OrganizationId);
		return Response.status(Status.OK).build();
	}
	
	@PUT
	@Path("refuse")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response RejectOrganizerRole(@QueryParam(value="UserId")int UserId, @QueryParam(value="OrganizationId")int OrganizationId){
		organizerBusiness.RejectOrganizerRole(UserId, OrganizationId);
		return Response.status(Status.OK).build();
	}
	@GET
	@Path("test")
	@Produces(MediaType.APPLICATION_JSON)
	public void  test(){
        
	 organizerBusiness.GetNbOrganizerByOrganization();
				
	}
	
	
	
	@DELETE
	public Response deleteOrganizer(@QueryParam("UserId")int UserId,
									@QueryParam("OrganizationId")int OrganizationId){
		
		if(organizerBusiness.deleteOrganizer(UserId, OrganizationId))
			return Response.status(Status.OK).build();
		else
			return Response.status(Status.NOT_FOUND).build();
	}
	
	
	
}
