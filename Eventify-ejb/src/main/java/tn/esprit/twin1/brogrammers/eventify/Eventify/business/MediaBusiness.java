package tn.esprit.twin1.brogrammers.eventify.Eventify.business;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.IMediaBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.IMediaBusinessRemote;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Media;

@Stateless
public class MediaBusiness implements IMediaBusinessLocal,IMediaBusinessRemote  {

	@PersistenceContext(unitName = "Eventify-ejb")
	EntityManager entityManager;

	@Override
	public void create(Media media) {
	
		entityManager.persist(media);
		
	}

	
	@Override
	public Media findMediaById(int idMedia) {
		// TODO Auto-generated method stub
		return entityManager.find(Media.class, idMedia);
	}

	

}
