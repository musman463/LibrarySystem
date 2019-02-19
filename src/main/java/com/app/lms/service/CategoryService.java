package com.app.lms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.lms.dao.CategoryRepo;
import com.app.lms.entity.CategoryEntity;

@Service
public class CategoryService {

	private CategoryRepo categoryRepo;
	
	@Autowired
	public CategoryService(CategoryRepo categoryRepo) {
		this.categoryRepo = categoryRepo;
	}
	
	public List<CategoryEntity> findAll() {
		return categoryRepo.findAll();
	}

	public CategoryEntity findById(int theId) {
		Optional<CategoryEntity> result = categoryRepo.findById(theId);
		
		CategoryEntity theCategory = null;
		
		if (result.isPresent()) {
			theCategory = result.get();
		}
		else {
			// we didn't find the user
			throw new RuntimeException("Did not find category id - " + theId);
		}
		
		return theCategory;
	}

	public void save(CategoryEntity theCategory) {
		categoryRepo.save(theCategory);
	}

	public void deleteById(int theId) {
		categoryRepo.deleteById(theId);
	}
}

