package com.example.pi2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pi2.model.Category;
import com.example.pi2.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository catRepository;

	
	public List<Category> retrieveAllCategorys() {
		// TODO Auto-generated method stub
		return (List<Category>) catRepository.findAll();
	}

	
	public Category addCategory(Category c) {
		// TODO Auto-generated method stub
		return catRepository.save(c);
	}

	
	public void deleteCategory(Long id_cat) {
		// TODO Auto-generated method stub
		catRepository.deleteById((long) id_cat);
	}

	
	public Category updateCategory(Category c) {
		// TODO Auto-generated method stub
		return catRepository.save(c);
	}

	
	public Category retrieveCategory(String id_cat) {
		// TODO Auto-generated method stub
		return catRepository.findById(Long.parseLong(id_cat)).orElse(null);
	}
	
}
