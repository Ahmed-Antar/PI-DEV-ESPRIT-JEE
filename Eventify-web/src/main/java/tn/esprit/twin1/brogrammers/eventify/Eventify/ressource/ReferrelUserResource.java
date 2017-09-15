package tn.esprit.twin1.brogrammers.eventify.Eventify.ressource;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
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

import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.RefferUserBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Rate;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.ReferrelUser;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.User;

@Path("referrel")
public class ReferrelUserResource {

	@EJB
	RefferUserBusinessLocal refferUserBusiness;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response AddReferrel(ReferrelUser referrelUser) {

		try {
			refferUserBusiness.ChooseReferred(referrelUser);
			return Response.status(Status.CREATED).build();
		} catch (Exception e) {
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response FindReferrds(@PathParam("id") int idReferral) {
		try {
			List<ReferrelUser> liste = refferUserBusiness.FindReferredsByIdReferral(idReferral);

			return Response.status(Status.OK).entity(liste).build();
		} catch (Exception e) {
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)

	public Response FindReferral(@QueryParam(value = "id") int idReferred) {
		User user = refferUserBusiness.FindReferralByIdReferred(idReferred);
		if (user != null)
			return Response.status(Status.OK).entity(user).build();
		else
			return Response.status(Status.BAD_REQUEST).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/allusers")
	public Response FindAllReferralsUsers() {

		try {
			List<User> users = refferUserBusiness.FindAllReferrals();

			return Response.status(Status.OK).entity(users).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).build();
		}

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public Response FindAll() {

		List<ReferrelUser> referrels = refferUserBusiness.FindAll();
		return Response.status(Status.OK).entity(referrels).build();

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateRef(ReferrelUser ref) {
		try {
			refferUserBusiness.updateReffered(ref);
			return Response.status(Status.OK).build();
		} catch (Exception e) {
			return Response.status(Status.NOT_MODIFIED).build();
		}

	}

}
