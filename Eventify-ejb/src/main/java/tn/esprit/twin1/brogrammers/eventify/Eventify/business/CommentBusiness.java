package tn.esprit.twin1.brogrammers.eventify.Eventify.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.CommentBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.CommentBusinessRemote;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.EventBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.UserBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Comment;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Event;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Ticket;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.User;


/**
 * Session Bean implementation class CommentBusiness
 */
@Stateless
@LocalBean
public class CommentBusiness implements CommentBusinessRemote, CommentBusinessLocal {

    /**
     * Default constructor. 
     */
	
	
	@PersistenceContext(unitName = "Eventify-ejb")
	EntityManager entityManager;
	@EJB
	UserBusinessLocal userbusiness;
	
    public CommentBusiness() {
        // TODO Auto-generated constructor stub
    }


	@Override
	public void AddComment(Comment comment) {
		
		entityManager.persist(comment);
		
	}


	@Override
	public boolean DeleteComment(int idUser, int idEvent) {
		
		if(GetCommentByUserIdAndEventId(idUser, idEvent)!=null)
		{
			
			Query query = entityManager.
					createQuery("DELETE FROM Comment c "
							+ "WHERE c.commentPK.idEvent=:param AND c.commentPK.idUser=:param1 ")
					.setParameter("param", idEvent).setParameter( "param1" , idUser);
			System.out.println("comment finded by event");
			 query.executeUpdate();
			
						return true;
		}
		else 
		return false;
	}


	@Override
	public void updateComment(Comment comment) {
		entityManager.merge(comment);
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getCommentsByEvent(int id) {
		try {
			List<Comment> commment = (List<Comment>) entityManager.
					createQuery("SELECT new Comment(user, c.contain,c.commentPK) FROM Comment c JOIN c.user user "
							+ "WHERE c.commentPK.idEvent=:param")
					.setParameter("param", id).getResultList();
			for (Comment commments : commment) {

				User user = userbusiness.findUserById(commments.getUser().getId());
				commments.setUser(user);

			}
			
			System.out.println("comment finded by event");
			 return commment;
			 
			 
			 

		} catch (Exception e) {
			System.err.println("Cant Find comment of event");
			return new ArrayList<Comment>();
		}
	
	}


	@Override
	public Comment GetCommentByUserIdAndEventId(int idUser, int idEvent) {
		try {
			Query query = entityManager.
					createQuery("SELECT new Comment(c.contain,c.commentPK) FROM Comment c"
							+ " WHERE (c.commentPK.idUser=:userId AND c.commentPK.idEvent=:eventId) ")
			.setParameter("userId", idUser)
			.setParameter("eventId", idEvent);
			System.out.println("Comment finded by event and user ");
			 return (Comment) query.getSingleResult();
			 

		} catch (Exception e) {
			System.err.println("Cant Find Comment  event and user : "+e);
			return null; } 
		
	}



}
