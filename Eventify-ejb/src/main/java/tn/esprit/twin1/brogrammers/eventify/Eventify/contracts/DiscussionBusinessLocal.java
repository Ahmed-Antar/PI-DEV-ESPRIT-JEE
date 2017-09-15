package tn.esprit.twin1.brogrammers.eventify.Eventify.contracts;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Discussion;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Task;

@Local
public interface DiscussionBusinessLocal {

	public void  addDiscussion(Discussion discussion);
	
	public List<Discussion> getDiscussionsByTask(int taskid);
	
	public int getNbrDiscussionsPerTask(int taskid);
	
}
