package tn.esprit.twin1.brogrammers.eventify.Eventify.contracts;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.twin1.brogrammers.eventify.Eventify.domain.Category;
@Remote
public interface CategoryBusinessRemote {
	public void addCategory(Category category);
	public void deleteCategory(int id);
	public Category findById(int id);
	public List<Category> getAllCategories();
}
