package tn.esprit.twin1.brogrammers.eventify.Eventify.contracts;

import javax.ejb.Local;

import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Media;

@Local
public interface IMediaBusinessLocal {
	public void create(Media media);
	public Media findMediaById(int idMedia);
}
