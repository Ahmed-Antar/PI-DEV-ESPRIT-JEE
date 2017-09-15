package tn.esprit.twin1.brogrammers.eventify.Eventify.ressource;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.twin1.brogrammers.eventify.Eventify.util.CognitiveServiceTextAnalytics;
@Path("sentiments")
@RequestScoped
public class SentimentResource {


	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getScore(@QueryParam("comment")String comment){
		String t = CognitiveServiceTextAnalytics.GetSentimentAnalytics(comment);
		System.out.println("**********************");
		System.out.println("********* "+ t +"*************");
		System.out.println("**********************");
		return t;

	}

}
