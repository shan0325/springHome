package com.spring.web.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.web.domain.Board;
import com.spring.web.dto.BoardDto;
import com.spring.web.dto.BoardDto.Delete;
import com.spring.web.dto.BoardDto.Update;

public interface BoardService {


	public Page<Board> getBoardList(Integer BOARD_MENUID, Pageable pageable, Integer lastBrdid);

	public Board getBoard(Long brdid);

	public Board insertBoard(HttpServletRequest request, Integer BOARD_MENUID, BoardDto.Create board);

	public Board updateBoard(Long brdid, BoardDto.Update updateBoard);

	public void deleteBoard(Long brdid, Delete deleteBoard);

}
