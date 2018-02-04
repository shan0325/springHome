package com.spring.web.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.net.ssl.SSLEngineResult.Status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.web.dto.BoardDto;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BoardControllerTest {

	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void insertBoard() throws Exception {
		BoardDto.Create board = new BoardDto.Create();
		board.setRegnm("홍길동");
		board.setPwd("password");
		board.setContents("hello");
		
		ResultActions result = mockMvc.perform(post("/board")
										.contentType(MediaType.APPLICATION_JSON)
										.content(objectMapper.writeValueAsString(board)));
		
		result.andDo(print());
		result.andExpect(status().isCreated());
	}
	
	@Test
	public void insertBoard_BadRequest()throws Exception {
		BoardDto.Create board = new BoardDto.Create();
		board.setRegnm(" ");
		board.setPwd("123");
		board.setContents("hello");
		
		ResultActions result = mockMvc.perform(post("/board")
										.contentType(MediaType.APPLICATION_JSON)
										.content(objectMapper.writeValueAsString(board)));
		
		result.andDo(print());
		result.andExpect(status().isBadRequest());
		result.andExpect(jsonPath("$.code", is("Bad.Request")));
	}
		
	@Test
	public void getBoard() throws Exception {
		
		mockMvc.perform(get("/board").param("size", "2"))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void updateBoard() throws Exception {
		
		BoardDto.Update updateBoard = new BoardDto.Update();
		updateBoard.setRegnm("홍길동");
		updateBoard.setPwd("1234");
		updateBoard.setContents("헬로 홍길동");
		
		ResultActions result = mockMvc.perform(put("/board/121")
										.contentType(MediaType.APPLICATION_JSON)
										.content(objectMapper.writeValueAsString(updateBoard)));
		
		result.andDo(print());
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.contents", is("헬로 홍길동")));
	}
	
	@Test
	public void updateBoard_NotFound() throws Exception {
		
		BoardDto.Update updateBoard = new BoardDto.Update();
		updateBoard.setRegnm("홍길동");
		updateBoard.setPwd("1234");
		updateBoard.setContents("헬로 홍길동");
		
		ResultActions result = mockMvc.perform(put("/board/999")
										.contentType(MediaType.APPLICATION_JSON)
										.content(objectMapper.writeValueAsString(updateBoard)));
		
		result.andDo(print());
		result.andExpect(status().isNotFound());
	}
	
	@Test
	public void deleteBoard() throws Exception {
		
		ResultActions result = mockMvc.perform(delete("/board/121"));
		
		result.andDo(print());
		result.andExpect(status().isOk());
	}
	
	
	
}
