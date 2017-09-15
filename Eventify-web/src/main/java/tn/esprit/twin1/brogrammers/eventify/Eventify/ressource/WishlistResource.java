package tn.esprit.twin1.brogrammers.eventify.Eventify.ressource;

import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.WishlistBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Wishlist;

@Path("wishlists")
@RequestScoped
public class WishlistResource {


	@EJB
	WishlistBusinessLocal wishlistBusiness;
	


	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addToWishlist(Wishlist wishlist){
		wishlistBusiness.addEventToWishlist(wishlist);
		return Response.status(Status.CREATED).build();
	}
	
	
	@DELETE
	public Response RemoveFromWishlist(@QueryParam("userId")int userId,
									@QueryParam("eventId")int eventId){
		
		if(wishlistBusiness.RemoveEventFromWishlist(userId, eventId))
			return Response.status(Status.OK).build();
		else
			return Response.status(Status.NOT_FOUND).build();
	}
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response GetWishlistByUser(@QueryParam(value="userId")int userId,
										@QueryParam(value="eventId")int eventId)
	{
		
		if(userId!=0 && eventId==0){
			List<Wishlist> liste=wishlistBusiness.getWishlistByUserId(userId);
			return Response.status(Status.OK).entity(liste).build();
		}
		else if(userId==0&&eventId!=0){
			List<Wishlist> liste=wishlistBusiness.getWishlistByEventId(eventId);
			return Response.status(Status.OK).entity(liste).build();
		}
		else
		{
			Wishlist w =wishlistBusiness.getWishlistByEventIdAndUserId(userId, eventId);
			return Response.status(Status.OK).entity(w).build();

		}

	}

	
	
	
	

}
