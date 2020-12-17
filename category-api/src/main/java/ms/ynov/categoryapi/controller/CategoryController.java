package ms.ynov.categoryapi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ms.ynov.categoryapi.model.Category;
import ms.ynov.categoryapi.repository.CategoryRepository;

@RestController
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	// Recupération de toutes les catégories
	@GetMapping("/category")
	public Iterable<Category> getCategories(){
		Iterable<Category> categories = categoryRepository.findAll();
		return categories;
	}

	// Recupération d'une categorie
	@GetMapping("/category/{id}")
	public Optional<Category> getCategory(@PathVariable("id") int id){
		Optional<Category> category = categoryRepository.findById(id);
		return category;		
	}

	// Creation d'une categorie
	@PostMapping("/category/create")
	public Category createCategory(@RequestBody Category category) {
		category = categoryRepository.save(category);
		return category;
	}

	// Suppresion d'une categorie
	@DeleteMapping("/category/delete/{id}")
	public void deleteCategory(@PathVariable("id") int id) {
		categoryRepository.deleteById(id);
	}

	// Modification d'une categorie
	@PutMapping("/category/update/{id}")
	public Category updateUser(@PathVariable("id") int id, @RequestBody Category category) {
		Category currentCategory = categoryRepository.findById(id).get();
		
		String name = category.getName();
		if(name != null) {
			currentCategory.setName(name);
		}
		
		currentCategory = categoryRepository.save(currentCategory);
		return currentCategory;
	}
	
	
}
