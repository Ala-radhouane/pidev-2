package com.example.pi2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.pi2.model.Category;
import com.example.pi2.service.CategoryService;

@RestController
public class CategoryController {

	@Autowired
	CategoryService catService;

	// http://localhost:8080/retrieve-all-categorys
	@GetMapping("/retrieve-all-categorys")
	@ResponseBody
	public List<Category> getCategorys() {
		List<Category> list = catService.retrieveAllCategorys();
		return list;
	}

	// http://localhost:8080/retrieve-category/{cat-id}
	@GetMapping("/retrieve-category/{cat-id}")
	@ResponseBody
	public Category retrieveCategory(@PathVariable("cat-id") String id_cat) {
		return catService.retrieveCategory(id_cat);
	}

	// Ajouter : http://localhost:8080/add-category
	@PostMapping("/add-category")
	@ResponseBody
	public Category addCategory(@RequestBody Category c) {
		Category cat = catService.addCategory(c);
		return cat;
	}

	// http://localhost:8080/remove-category/{cat-id}
	@DeleteMapping("/remove-category/{cat-id}")
	@ResponseBody
	public void removeCategory(@PathVariable("cat-id") Long id_cat) {
		catService.deleteCategory(id_cat);
	}

	// http://localhost:8080/modify-category
	@PutMapping("/modify-category")
	@ResponseBody
	public Category modifyCategory(@RequestBody Category c) {
		return catService.updateCategory(c);
	}
	
}
