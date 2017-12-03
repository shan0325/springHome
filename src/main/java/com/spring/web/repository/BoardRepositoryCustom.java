package com.spring.web.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.web.domain.Board;

public interface BoardRepositoryCustom {
	
	Page<Board> getBoardList(Integer menuid, Integer depth, Pageable pageable);

	Board getBoardByQuerydsl(Long brdid);
}
