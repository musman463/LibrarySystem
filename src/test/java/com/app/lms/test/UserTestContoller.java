package com.app.lms.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.app.lms.rest.UserController;
import com.app.lms.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserTestContoller {

	@Autowired
	private MockMvc mockMvc;
 
    @MockBean
    private UserService userService;
	
	String exampleUserJson = "{\"name\":\"username\",\"cnic\":\"1234567890\",\"mobile\":\"923451234567\",\"address\":\"useraddress\"}";

    @Test
    public void canCreateANewUser() throws Exception {
        // when
        MockHttpServletResponse response = mockMvc.perform(
        		MockMvcRequestBuilders.post("/api/users").contentType(MediaType.APPLICATION_JSON).content(
                		exampleUserJson)).andReturn().getResponse();
 
        // then
		assertEquals(response.getStatus(), HttpStatus.OK.value());

    }
}
