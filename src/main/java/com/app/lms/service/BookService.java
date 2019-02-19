package com.app.lms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.lms.dao.BookRepo;
import com.app.lms.entity.BookEntity;

@Service
public class BookService {

	private BookRepo bookRepo;
	
	@Autowired
	public BookService(BookRepo bookRepo) {
		this.bookRepo = bookRepo;
	}
	
	public List<BookEntity> findAll() {
		return bookRepo.findAll();
	}

	public BookEntity findById(int theId) {
		Optional<BookEntity> result = bookRepo.findById(theId);
		
		BookEntity theBook = null;
		
		if (result.isPresent()) {
			theBook = result.get();
		}
		else {
			// we didn't find the user
			throw new RuntimeException("Did not find book id - " + theId);
		}
		
		return theBook;
	}

	public void save(BookEntity theBook) {
		bookRepo.save(theBook);
	}

	public void deleteById(int theId) {
		bookRepo.deleteById(theId);
	}
}

