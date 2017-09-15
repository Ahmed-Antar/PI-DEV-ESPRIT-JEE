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

import tn.esprit.twin1.brogrammers.eventify.Eventify.business.OrganizationBusiness;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.OrganizationBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Event;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Organization;

@Path("organization")
@RequestScoped
public class OrganizationResource {
	
	
	
	@EJB
	OrganizationBusinessLocal organizationBusiness;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllOrganizations()
	{
		//System.out.println(organizationBusiness.getAllOrganizations().toString());
		

		return Response.status(Status.FOUND).entity(organizationBusiness.getAllOrganizations()).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addOrganization(Organization organization)
	{
		organizationBusiness.create(organization);
		return Response.status(Status.CREATED).build();
	}
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateOrganization(Organization organization){
		organizationBusiness.updateOrganization(organization);
		return Response.status(Status.OK).build();
	}
	
	
	
	
	@DELETE
	@Path("{id}")
	public Response deleteOrganization(@PathParam(value="id")int id){
		if(organizationBusiness.deleteOrganization(id))
			return Response.status(Status.OK).build();
	return Response.status(Response.Status.NOT_FOUND).build();
		
	}
	/*
	@DELETE
	@Path("{id}")
	public void deleteOrganization(@PathParam("id") int id) {

		organizationBusiness.deleteOrganization(id);

	}
	*/
	
	/*@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllEvents()
	{
		System.out.println("************************************");
		System.out.println(organizationBusiness.getAllOrganizations().toString());
		System.out.println("************************************");

		return Response.status(Status.FOUND).entity(organizationBusiness.getAllOrganizations()).build();
	}
	*/
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response findOrganizationById(@PathParam("id") int id)
	{  
		Organization o = organizationBusiness.findOrganizationById(id);
		if(o==null)
		{
			return Response.status(Status.NOT_FOUND).build();
		}
		else
		{
			return Response.status(Status.OK).entity(o).build();
		}
	}
	
	
/*	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{organizationType}")
	public Response findOrganizationByType(@PathParam("organizationType") String organizationType)
	{
		List<Organization> o = organizationBusiness.findOrganizationByType(organizationType);
		if(o==null)
		{
			return Response.status(Status.NOT_FOUND).build();
		}
		else
		{
			return Response.status(Status.OK).entity(o).build();
		}
	}
	*/
	
	
	@GET
	@Path("{id}/events")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMyEvents(@PathParam("id") int id){

		return Response.status(Status.OK).entity(organizationBusiness.getMyEvents(id)).build();
				
	}

	@GET
	@Path("/search")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response SearchForOrganizations(@QueryParam(value="search")String search,
									@QueryParam(value="organizationName")String organizationName,
									@QueryParam(value="organizationType")String organizationType
									){
		
		List<Organization> liste=null;
		if(search!=null && organizationName==null && organizationType==null )
		liste = organizationBusiness.SearchForOrganizations(search);
		else if (search==null && organizationName!=null && organizationType==null)
			liste = organizationBusiness.findOrganizationByName(organizationName);
		else if (search==null && organizationName==null && organizationType!=null)
			liste = organizationBusiness.findOrganizationByType(organizationType);
		else if (search==null && organizationName==null && organizationType==null)
			{ liste= organizationBusiness.getAllOrganizations();
			}
		return Response.status(Status.OK).entity(liste).build();
	}
	
	
	
	
	@GET
	@Path("physique")
	@Produces(MediaType.APPLICATION_JSON)
	public String  GetNbOrganizationPhysiqueStatistics(){
        
		return organizationBusiness.GetNbOrganizationPhysiqueStatistics();
				
	}
	@GET
	@Path("morale")
	@Produces(MediaType.APPLICATION_JSON)
	public String  GetNbOrganizationMoraleStatistics(){
        
		return organizationBusiness.GetNbOrganizationMoraleStatistics();
				
	}
	
	@GET
	@Path("test")
	@Produces(MediaType.APPLICATION_JSON)
	public void  test(){
        
	 organizationBusiness.GetNbOrganizerByOrganization();
				
	}
	
	
}
