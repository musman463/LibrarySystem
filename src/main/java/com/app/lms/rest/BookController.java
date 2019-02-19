package com.app.lms.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.lms.entity.BookEntity;
import com.app.lms.entity.CategoryEntity;
import com.app.lms.request.BookRequest;
import com.app.lms.service.BookService;
import com.app.lms.service.CategoryService;

@RestController
@RequestMapping("/api")
public class BookController {

	private BookService bookService;
	
	private CategoryService categoryService;
	
	@Autowired
	public BookController(BookService bookService, CategoryService categoryService) {
		this.bookService = bookService;
		this.categoryService = categoryService;
	}
	
	// expose "/books" and return list of books
	@GetMapping("/books")
	public List<BookEntity> findAll() {
		return bookService.findAll();
	}

	// add mapping for GET /books/{bookId}
	
	@GetMapping("/books/{bookId}")
	public BookEntity getBook(@PathVariable int bookId) {
		
		BookEntity theBook = bookService.findById(bookId);
		
		if (theBook == null) {
			throw new RuntimeException("BookEntity id not found - " + bookId);
		}
		
		return theBook;
	}
	
	// add mapping for POST /books - add new book
	
	@PostMapping("/books")
	public BookEntity addBook(@RequestBody BookRequest bookRequest) {
		
		CategoryEntity theCategory = categoryService.findById(bookRequest.getCategoryId());
		
		if (theCategory == null) {
			throw new RuntimeException("CategoryEntity id not found - " + bookRequest.getCategoryId());
		}
		
		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update
		BookEntity theBook = new BookEntity();
		theBook.setId(0);
		theBook.setName(bookRequest.getName());
		theBook.setAuthorName(bookRequest.getAuthorName());
		theBook.setCategory(theCategory);
		
		bookService.save(theBook);
		
		return theBook;
	}
	
	// add mapping for PUT /books - update existing book
	
	@PutMapping("/books")
	public BookEntity updateBook(@RequestBody BookRequest bookRequest) {
		CategoryEntity theCategory = categoryService.findById(bookRequest.getCategoryId());
		
		if (theCategory == null) {
			throw new RuntimeException("CategoryEntity id not found - " + bookRequest.getCategoryId());
		}
		
		BookEntity theBook = new BookEntity();
		theBook.setId(bookRequest.getId());
		theBook.setName(bookRequest.getName());
		theBook.setAuthorName(bookRequest.getAuthorName());
		theBook.setCategory(theCategory);
		
		bookService.save(theBook);
		
		return theBook;
	}
	
	// add mapping for DELETE /books/{bookId} - delete book
	
	@DeleteMapping("/books/{bookId}")
	public String deleteBook(@PathVariable int bookId) {
		
		BookEntity tempBook = bookService.findById(bookId);
		
		// throw exception if null
		
		if (tempBook == null) {
			throw new RuntimeException("BookEntity id not found - " + bookId);
		}
		
		bookService.deleteById(bookId);
		
		return "Deleted book id - " + bookId;
	}	
	
}
