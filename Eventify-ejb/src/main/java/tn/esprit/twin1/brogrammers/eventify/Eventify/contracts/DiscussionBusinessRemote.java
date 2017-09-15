package tn.esprit.twin1.brogrammers.eventify.Eventify.contracts;

import java.util.List;

import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Discussion;

public interface DiscussionBusinessRemote {
public void  addDiscussion(Discussion discussion);
	
	public List<Discussion> getDiscussionsByTask(int taskid);
	public int getNbrDiscussionsPerTask(int taskid);
}
