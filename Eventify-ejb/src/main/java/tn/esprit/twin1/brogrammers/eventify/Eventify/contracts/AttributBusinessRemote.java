package tn.esprit.twin1.brogrammers.eventify.Eventify.contracts;

import javax.ejb.Remote;

import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Attribut;

@Remote
public interface AttributBusinessRemote {
	public void createAttribut(Attribut attribut);
	public void updateAttribut(Attribut attribut);
	public boolean deleteAttribut(int id);
	public Attribut getAttributById(int id);

}
