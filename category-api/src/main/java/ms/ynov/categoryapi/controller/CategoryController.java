package ms.ynov.categoryapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ms.ynov.categoryapi.model.Category;
import ms.ynov.categoryapi.repository.CategoryRepository;

@RestController
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping("/category")
	public Iterable<Category> getCategories(){
		Iterable<Category> categories = categoryRepository.findAll();
		return categories;
	}
	
	
}
