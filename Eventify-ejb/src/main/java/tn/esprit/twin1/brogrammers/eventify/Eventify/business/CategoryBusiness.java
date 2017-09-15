package tn.esprit.twin1.brogrammers.eventify.Eventify.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.CategoryBusinessLocal;
import tn.esprit.twin1.brogrammers.eventify.Eventify.contracts.CategoryBusinessRemote;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Category;
import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Organization;

/**
 * Session Bean implementation class CategoryBusiness
 */
@Stateless
@LocalBean
public class CategoryBusiness implements CategoryBusinessLocal,CategoryBusinessRemote {

	
	
	@PersistenceContext(unitName = "Eventify-ejb")
	EntityManager entityManager;

    /**
     * Default constructor. 
     */
    public CategoryBusiness() {
    	
    }

	@Override
	public void addCategory(Category category) {
		try {
			entityManager.persist(category);		

		} catch (Exception e) {
			System.out.println("Unable to add");
		}
	}

	@Override
	public void deleteCategory(int id) {
		entityManager.remove(entityManager.find(Category.class, id));		
		
	}

	@Override
	public Category findById(int id) {
		try {
			Query query = entityManager.
					createQuery("SELECT new Category(c.id,c.categoryName) FROM Category c "
							+ "WHERE c.id=:param")
					.setParameter("param", id);
			 return (Category) query.getSingleResult();

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Category> getAllCategories() {
		Query query = entityManager.
				createQuery("SELECT new Category(c.id,c.categoryName) FROM Category c ");
		 return (List<Category>) query.getResultList();
	}

}
