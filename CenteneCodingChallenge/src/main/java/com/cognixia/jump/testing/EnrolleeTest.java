package com.cognixia.jump.testing;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cognixia.jump.model.Enrollee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
/**
 * Tests for Employee Rest APIs
 * 
 */

public class EnrolleeTest extends AbstractTest {
	
	@Test
	public void getEnrolleeList() throws Exception {
		
	   String uri = "http://localhost:8080/api/enrollees";
	   
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	      .accept(MediaType.APPLICATION_JSON)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   System.out.println(status);
	   assertEquals(200, status);
	   String content = mvcResult.getResponse().getContentAsString();
	   List<Enrollee> enrollees = (List<Enrollee>) super.mapFromJson(content,  Enrollee.class);
	   assertTrue(enrollees.size() > 0);
	}

}
