package tn.esprit.twin1.brogrammers.eventify.Eventify.contracts;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Organization;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Organizer;

@Local
public interface OrganizerBusinessLocal {
	public void assignOrganizer(Organizer organizer);
	public void updateOrganizer(Organizer organizer);
	public boolean deleteOrganizer(int UserId,int OrganizationId);
//	public Organizer getOrganizerById(int id);
	public List<Organizer> getAllOrganizersByOrganization(int OrganizationId);
	public List<Organizer> getAllOrganizersByUser(int UserId);
	public Organizer getAllOrganizersByUserIdAndOrganizationId(int UserId,int OrganizationId);
//	public List<Organization> SearchForOrganizers(String search);
//	public Organization findOrganizerById(int id);
	public void GetNbOrganizerByOrganization();
	
	public void AcceptOrganizerRole(int UserId, int OrganizationId);
	public void RejectOrganizerRole(int UserId, int OrganizationId);


}
