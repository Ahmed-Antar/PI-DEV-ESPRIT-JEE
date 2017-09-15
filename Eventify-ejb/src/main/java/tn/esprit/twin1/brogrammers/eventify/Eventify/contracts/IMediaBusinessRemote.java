package tn.esprit.twin1.brogrammers.eventify.Eventify.contracts;

import javax.ejb.Remote;

import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Media;

@Remote
public interface IMediaBusinessRemote {
	public void create(Media media);
	public Media findMediaById(int idMedia);
}
