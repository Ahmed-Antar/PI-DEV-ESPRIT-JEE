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

import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.AttributBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Attribut;

@Path("attributs")
@RequestScoped
public class AttributResource {

	@EJB
	AttributBusinessLocal attributBusiness;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addAttribut(Attribut attribut){
		
		try {
			attributBusiness.createAttribut(attribut);
			return Response.status(Status.CREATED).build();
		} catch (Exception e) {
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
	}
	
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateAttribut(Attribut attribut,@PathParam(value="id")int id){
		try {
			attributBusiness.updateAttribut(attribut);
			return Response.status(Status.OK).build();
		} catch (Exception e) {
			return Response.status(Status.NOT_MODIFIED).build();
		}

	}
	
	
	@DELETE
	@Path("{id}")
	public Response deleteAttribut(@PathParam(value="id")int id){
		
		boolean b = attributBusiness.deleteAttribut(id);
		if(b)
			return Response.status(Status.OK).build();
		else
			return Response.status(Status.NOT_FOUND).build();

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response getAttributById(@PathParam("id")int id){
		Attribut attribut = attributBusiness.getAttributById(id);
		if(attribut!=null)
			return Response.status(Status.OK).entity(attribut).build();
		else
			return Response.status(Status.NOT_FOUND).build();

	}
	
	
	
	
}
