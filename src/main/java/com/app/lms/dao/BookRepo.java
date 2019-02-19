package com.app.lms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.lms.entity.BookEntity;

public interface BookRepo extends JpaRepository<BookEntity, Integer> {

}
