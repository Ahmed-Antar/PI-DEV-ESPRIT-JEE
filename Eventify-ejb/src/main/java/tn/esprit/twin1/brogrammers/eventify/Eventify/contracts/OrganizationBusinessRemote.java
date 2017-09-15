package tn.esprit.twin1.brogrammers.eventify.Eventify.contracts;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Event;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Organization;

@Remote
public interface OrganizationBusinessRemote {
	public void create(Organization organization);
	public void updateOrganization(Organization organization);
	public boolean deleteOrganization(int id);
	public List<Organization> getAllOrganizations();
	public Organization findOrganizationById(int id);
	public List<Organization> findOrganizationByType(String type);
	public List<Organization> findOrganizationByName(String name);
	//public List<Organization> findOrganizationByEvent(Event event);
	public List<Event> getMyEvents(int id);
	public List<Organization> SearchForOrganizations(String search);
	public String GetNbOrganizationPhysiqueStatistics();
	public String GetNbOrganizationMoraleStatistics();

	public void GetNbOrganizerByOrganization();

}
