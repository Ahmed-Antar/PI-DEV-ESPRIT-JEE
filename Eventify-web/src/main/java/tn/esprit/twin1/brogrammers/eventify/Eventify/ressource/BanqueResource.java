package tn.esprit.twin1.brogrammers.eventify.Eventify.ressource;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.IBnaqueBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.ITransactionBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Transaction;

@Path("banque")
@RequestScoped
public class BanqueResource {

	@EJB
	IBnaqueBusinessLocal banqueBusiness;
	
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("{fullName}/{cardNumber}/{expMonth}/{expYear}/{ccv}")
	public boolean checkCardPayment(
			@PathParam("fullName")String fullName,@PathParam("cardNumber") String cardNumber,@PathParam("expMonth")String expMonth,
			@PathParam("expYear")String expYear,@PathParam("ccv")int ccv) {
		boolean result = banqueBusiness.checkPaymentDtails(fullName, cardNumber, expMonth, expYear, ccv);
		
		if (result == true) {
			return true;
		} else {
			return false;
		}
	}
	
	
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Path("{cardNumber}/{amount}")
	public boolean update(
			@PathParam("cardNumber")String cardNumber,@PathParam("amount") String amount) {
		boolean result = banqueBusiness.updateAmount(cardNumber, Double.parseDouble(amount));
		
		if (result == true) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
}
