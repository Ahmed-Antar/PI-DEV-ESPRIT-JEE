package tn.esprit.twin1.brogrammers.eventify.Eventify.ressource;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.CategoryBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Category;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Organization;

@Path("categories")
@RequestScoped
public class CategoryBusiness {
	
	@EJB
	CategoryBusinessLocal categoryBusiness;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCategory(Category category)
	{
		categoryBusiness.addCategory(category);
		return Response.status(Status.CREATED).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteCategory(@PathParam("id")int id){
		categoryBusiness.deleteCategory(id);
		return Response.status(Status.OK).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCategories(){
		
		return Response.status(Status.OK).entity(categoryBusiness.getAllCategories()).build();
	}


}
