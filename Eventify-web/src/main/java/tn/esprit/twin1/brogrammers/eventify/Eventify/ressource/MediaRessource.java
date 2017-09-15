/*package tn.esprit.twin1.brogrammers.eventify.Eventify.ressource;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
/*
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.IMediaBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Media;
/*

@Path("media")
@RequestScoped
public class MediaRessource {

	@EJB
	IMediaBusinessLocal mediaBusniess;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Media getMediaById(@PathParam("id") int idMedia)
	{
		return mediaBusniess.findMediaById(idMedia);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addMedia(Media media)
	{
		mediaBusniess.create(media);
	}
}
*/