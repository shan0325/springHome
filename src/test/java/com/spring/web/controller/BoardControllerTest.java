package com.spring.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.spring.web.domain.Board;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardControllerTest {

	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new BoardController()).build();
	}
	
	@Test
	public void insertBoard() throws Exception {
		
		MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
		paramMap.add("regnm", "홍길동");
		paramMap.add("pwd", "1234");
		paramMap.add("contents", "hello");
		
		mockMvc.perform(post("/board").contentType(MediaType.APPLICATION_JSON).params(paramMap))
				.andDo(print())
				.andExpect(status().isOk());
		
	}
		
	@Test
	public void getBoard() throws Exception {
		
		mockMvc.perform(get("/board").param("size", "2"))
				.andDo(print())
				.andExpect(status().isOk());
		
	}
	
	
}
