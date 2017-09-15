package tn.esprit.twin1.brogrammers.eventify.Eventify.ressource;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.AccessToken;

import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.IReservationBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.ITicketBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.ITransactionBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Reservation;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Ticket;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Transaction;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.enumeration.ReservationState;
import tn.esprit.twin1.brogrammers.eventify.Eventify.util.TicketGenerator;
import tn.esprit.twin1.brogrammers.eventify.Eventify.util.Paypal.PaymentWithPayPalServlet;

@Path("transaction")
@RequestScoped
public class TransactionResource {
	@Context
	UriInfo uri;

	@EJB
	ITransactionBusinessLocal transactionBusiness;

	PaymentWithPayPalServlet p = new PaymentWithPayPalServlet();

	@EJB
	IReservationBusinessLocal reservationBusiness;
	@EJB
	ITicketBusinessLocal ticketBusiness;

	// lena ajout transaction
	@GET
	@Path("paypal/pay/{idReservation}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response pay(@Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse,
			@PathParam("idReservation") String idReservation) throws ServletException, IOException {
		//System.out.println("firaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaas");
		//System.out.println(reservation);
		//Payment pp = p.createPayment(servletRequest, servletResponse, reservation);
		// System.out.println(Payment.getOAuthTokenCredential());
		//Transaction tr = new Transaction("", reservation.getAmount() + reservation.getAmount() * (7 / 100),
		//		reservation);
		// transactionBusiness.create(tr);
		//Reservation reservation = reservationBusiness.findReservationById(idReservation);
		return Response.status(Status.OK).entity(p.createPayment(servletRequest, servletResponse, idReservation))
				.build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Transaction> getAllTransaction() {

		return transactionBusiness.getAllTransactions();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response findTransactionById(@PathParam("id") int id) {
		Transaction t = transactionBusiness.findTransactionById(id);
		if (t == null) {
			return Response.status(Status.NOT_FOUND).build();
		} else {
			return Response.status(Status.OK).entity(t).build();
		}
	}

	/********/

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addTransaction(Transaction transaction) {
		if (transactionBusiness.create(transaction))
			return Response.status(Status.CREATED).build();
		return Response.status(Status.BAD_REQUEST).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response updateTransaction(@PathParam("id") int idTransaction, Transaction transaction) {
		if (transactionBusiness.updateTransaction(transaction))
			return Response.status(Status.OK).build();
		return Response.status(Status.BAD_GATEWAY).build();
	}

	@DELETE
	@Path("{id}")
	public Response deleteTransaction(@PathParam(value = "id") int id) {
		if (transactionBusiness.deleteTransactionById(id)) {
			return Response.status(Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("paypalredirectcancled")
	public Response PaypalCancled() {

		return Response.status(Status.BAD_REQUEST).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("paypalredirect")
	public Response PaypalConfirmed() {
		String myUri = uri.getQueryParameters().toString();

		String url = myUri.substring(myUri.lastIndexOf("=[") + 1);
		String resulturl = "";
		for (int i = 1; i < 21; i++) {
			resulturl = resulturl + url.charAt(i);

		}
		AccessToken ak = new AccessToken(resulturl, 5000000);

		//System.out.println("ahawaaaaaaaa" + myUri);
		//System.out.println("Mellekhr: " + resulturl);

		String urlidres = myUri.substring(myUri.lastIndexOf("id=[") + 1);
		System.out.println("eff:" + urlidres);
		String resulreservation = "";
		for (int i = 3; i < 4; i++) {
			resulreservation = resulreservation + urlidres.charAt(i);

		}
		System.out.println(resulreservation);
		int idreservationintegere = Integer.valueOf(resulreservation);
		// or
		Reservation reservation = reservationBusiness.findReservationById(1);
		System.out.println(reservation);
		Transaction transaction = new Transaction(resulturl, reservation.getAmount(), reservation);
		System.out.println(transaction);
		transactionBusiness.create(transaction);
		reservationBusiness.UpdateReservationState(reservation.getId());
		//reservationBusiness.updateReservation(reservation);
		TicketGenerator ticketgenerator = new TicketGenerator();
		System.out.println("tiki: " + ticketgenerator);
		System.out.println(reservation.getTicket());
		int idtickettogenerate = reservationBusiness.getIDTicketByReservationId(reservation.getId());
		System.out.println("Reser: " + reservation.getId());
		Ticket tickettoGenerate = ticketBusiness.TikcetWithEventRelation(idtickettogenerate);
		System.out.println("Ticket To Generate: " + tickettoGenerate);
		ticketgenerator.GenerateTicket(tickettoGenerate);

		return Response.status(Status.OK).build();

	}

}
