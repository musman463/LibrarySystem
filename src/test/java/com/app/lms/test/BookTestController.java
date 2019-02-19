package com.app.lms.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.app.lms.entity.CategoryEntity;
import com.app.lms.rest.BookController;
import com.app.lms.service.BookService;
import com.app.lms.service.CategoryService;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookTestController {

	@Autowired
	private MockMvc mockMvc;
 
    @MockBean
    private BookService bookService;
	
    @MockBean
    private CategoryService categoryService;
    
	String exampleBookJson = "{\"name\":\"bookname\",\"authorName\":\"authorname\",\"categoryId\":1}";

    @Test
    public void canCreateANewBook() throws Exception {
        // when
        CategoryEntity categoryEntity = new CategoryEntity(1, "drama");
        
        Mockito.when(categoryService.findById(categoryEntity.getId()))
          .thenReturn(categoryEntity);
        
        MockHttpServletResponse response = mockMvc.perform(
        		MockMvcRequestBuilders.post("/api/books").contentType(MediaType.APPLICATION_JSON).content(
        				exampleBookJson)).andReturn().getResponse();
 
        // then
		assertEquals(response.getStatus(), HttpStatus.OK.value());

    }
}
