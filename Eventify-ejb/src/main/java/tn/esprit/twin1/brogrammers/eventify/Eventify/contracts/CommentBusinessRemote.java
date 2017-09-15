package tn.esprit.twin1.brogrammers.eventify.Eventify.contracts;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Comment;


@Remote
public interface CommentBusinessRemote {
	
public void AddComment(Comment comment);
	
	public boolean DeleteComment(int idUser,int idEvent);
	
	public void updateComment(Comment comment);
	
	public List<Comment> getCommentsByEvent(int id);
	
	public Comment GetCommentByUserIdAndEventId(int idUser,int idEvent);

}
