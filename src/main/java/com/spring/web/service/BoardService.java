package com.spring.web.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.web.domain.Board;

public interface BoardService {


	public Page<Board> findByMenuid(Integer BOARD_MENUID, Pageable pageable);

	public Board findOne(Integer brdid);

}
