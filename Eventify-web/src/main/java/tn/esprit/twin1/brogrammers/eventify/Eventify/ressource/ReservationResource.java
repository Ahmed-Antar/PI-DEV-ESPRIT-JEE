package tn.esprit.twin1.brogrammers.eventify.Eventify.ressource;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.annotation.PostConstruct;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.paypal.api.payments.RedirectUrls;

import tn.esprit.twin1.brogrammers.eventify.Eventify.business.ReservationBusiness;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.IReservationBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.ITicketBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Reservation;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Ticket;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.enumeration.PaymentMethod;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.enumeration.ReservationState;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.enumeration.TimerState;
import tn.esprit.twin1.brogrammers.eventify.Eventify.util.Paypal.PaymentWithPayPalServlet;

@Path("reservation")
@RequestScoped
public class ReservationResource {
	@EJB
	IReservationBusinessLocal reservationBusiness;
	@EJB
	ITicketBusinessLocal ticketBusiness;
	

	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllReservations() {

		return Response.status(Status.OK).entity(reservationBusiness.getAllReservations()).build();

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response findReservationById(@PathParam("id") int idReservation) {
		Reservation r = reservationBusiness.findReservationById(idReservation);
		if (r == null) {
			return Response.status(Status.NOT_FOUND).build();
		} else {
			return Response.status(Status.OK).entity(r).build();
		}
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response addReservation(Reservation reservation) {
		
	
		
			reservationBusiness.create(reservation);
			
			return Response.status(Status.OK).entity(reservation).build();
		
	}
	
	
	
	
	
/*
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response addReservation(Reservation reservation, @QueryParam("nbTicket") int nbTicket) {
		System.out.println("IDDDDDDDDDDDDDDDDDDDDDDDD:"+reservation.getTicket().getId());
		int idticket = reservation.getTicket().getId();
		Ticket ticketcheck = ticketBusiness.findTicketById(idticket);
	
		if (ticketcheck.getNbTickets() > nbTicket) {
			System.out.println(ticketcheck.getNbTickets()+"////////"+ nbTicket);
			reservationBusiness.create(reservation);
			Ticket ticket = reservation.getTicket();
			int oldNbTicket = ticketcheck.getNbTickets();
			ticketcheck.setNbTickets(oldNbTicket - nbTicket);
			System.out.println("New NbTick:" + ticketcheck.getNbTickets());
			ticketBusiness.UpdateNbTicket(ticketcheck.getId(), oldNbTicket - nbTicket);

			new Timer().schedule(new java.util.TimerTask() {
				@Override
				public void run() {
					System.out.println("timeeeeeeeeeeeeeeer1");
					reservation.setTimerState(TimerState.FINISHED);
					updateReservation(reservation,0);
					if (reservation.getReservationState()!=ReservationState.CONFIRMED)
					{
						ticketcheck.setNbTickets(oldNbTicket + nbTicket);
					ticketBusiness.UpdateNbTicket(ticketcheck.getId(), oldNbTicket);
					System.out.println("Arja3 NbTick:" + ticketcheck.getNbTickets());
					System.out.println("timeeeeeeeeeeeeeeer2");}

				}
			}, 10000);
			return Response.status(Status.CREATED).build();
		}

		else {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
*/
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response updateReservation(Reservation reservation,@PathParam("id")int id) {
		reservation.setTimerState(TimerState.FINISHED);
		if(reservationBusiness.updateReservation(reservation))
			return  Response.status(Status.OK).build();
		return Response.status(Status.BAD_REQUEST).build();
	}

	@DELETE
	@Path("{id}")
	public Response deleteReservation(@PathParam(value = "id") int id) {
		if (reservationBusiness.deleteReservationById(id)) {
			return Response.status(Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("find")
	public Response getReservations(@QueryParam(value = "reservationState") ReservationState reservationState,
			@QueryParam(value = "timerState") TimerState timerState,
			@QueryParam(value = "paymentMethod") PaymentMethod paymentMethod,
			@QueryParam(value = "userId") int userId) {
		List<Reservation> liste = null;
		if (reservationState == null && timerState == null && paymentMethod == null && userId == 0) {
			liste = reservationBusiness.getAllReservations();
		} else if (reservationState != null && timerState == null && paymentMethod == null && userId == 0) {
			liste = reservationBusiness.findReservationByState(reservationState);
		} else if (reservationState == null && timerState != null && paymentMethod == null && userId == 0) {
			liste = reservationBusiness.findReservationByTimerState(timerState);
		} else if (reservationState == null && timerState == null && paymentMethod != null && userId == 0) {
			liste = reservationBusiness.findReservationByPaymentMethod(paymentMethod);
		} else if (reservationState == null && timerState == null && paymentMethod == null && userId != 0) {
			liste = reservationBusiness.findReservationByUserId(userId);
		}
		return Response.status(Status.OK).entity(liste).build();
	}

	@GET
	@Path("myconfirmedreservation")
	@Produces(MediaType.TEXT_PLAIN)
	public Response CheckConfirmedReservationSum(@QueryParam(value = "idEvent") int idEvent) {

		return Response.ok(reservationBusiness.CheckConfirmedReservationSum(idEvent)).build();
	}

	@GET
	@Path("ReservationsByPaymentMethod")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAllReservationGroupByPaymentMethod(@QueryParam(value = "idEvent") int idEvent) {
		return Response.status(Status.OK).entity(reservationBusiness.getAllReservationGroupByPaymentMethod(idEvent))
				.build();

	}
	
	@GET
	@Path("EventAmount")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getSumOfAmountForOneEvent(@QueryParam(value = "idEvent") int idEvent) {
		return Response.ok(reservationBusiness.getSumOfAmountForOneEvent(idEvent)).build();

	}
	
	@GET
	@Path("test")
	public void getSumOfAmountForOneEventf() {
		reservationBusiness.getAmountOrderByYear();

	}
	
}
