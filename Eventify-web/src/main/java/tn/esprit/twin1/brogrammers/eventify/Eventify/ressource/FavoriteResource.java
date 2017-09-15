package tn.esprit.twin1.brogrammers.eventify.Eventify.ressource;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.FavoriteBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Favorite;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Wishlist;

@Path("favorites")
@RequestScoped
public class FavoriteResource {

	@EJB
	FavoriteBusinessLocal favoriteBusiness;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addFavorite(Favorite favorite) {
		favoriteBusiness.addFavorite(favorite);
		return Response.status(Status.CREATED).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFavorites(@QueryParam(value = "userId") int userId) {
		if (userId != 0) {
			List<Favorite> liste = favoriteBusiness.getFavoritesByUser(userId);
			if (liste.size() == 0)
				return Response.status(Status.NOT_FOUND).entity(liste).build();
			else
				return Response.status(Status.OK).entity(liste).build();
		} else {
			return Response.status(Status.BAD_REQUEST).build();
		}

	}

	@DELETE
	public Response deleteFavorite(@QueryParam(value = "userId") int userId,
			@QueryParam(value = "categoryId") int categoryId) {

		if(userId != 0 && categoryId != 0)
		{
			boolean var = favoriteBusiness.RemoveFavorite(userId, categoryId);
			if(var)
				return Response.status(Status.OK).build();
			else 
				return Response.status(Status.NOT_FOUND).build();
			

		}
		else {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
	}

}
