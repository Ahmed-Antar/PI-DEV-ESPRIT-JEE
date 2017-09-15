package tn.esprit.twin1.brogrammers.eventify.Eventify.contracts;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Event;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.ReferrelUser;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.User;

@Local
public interface RefferUserBusinessLocal {
	
	public void ChooseReferred(ReferrelUser Reffered);
	
	public List<ReferrelUser> FindReferredsByIdReferral(int idReferral);
	
	public User FindReferralByIdReferred(int idReferred);
	
	public boolean RemoveReferred(int idReferred);
	
	public List<User> FindAllReferrals();
	
	public void updateReffered(ReferrelUser Referred);
	
	public List<ReferrelUser> FindAll();

}
