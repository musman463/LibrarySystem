package com.app.lms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.lms.entity.CategoryEntity;

public interface CategoryRepo extends JpaRepository<CategoryEntity, Integer> {

}
