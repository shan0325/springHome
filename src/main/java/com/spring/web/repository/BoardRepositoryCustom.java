package com.spring.web.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.web.domain.Board;

public interface BoardRepositoryCustom {
	
	/**
	 * 게시판 리스트 가져오기
	 * @param menuid
	 * @param depth
	 * @param pageable
	 * @return
	 */
	Page<Board> getBoardList(Integer menuid, Integer depth, Pageable pageable, Integer categorycd);
	
	/**
	 * 더보기 게시판 리스트 가져오기
	 * @param bOARD_MENUID
	 * @param i
	 * @param pageable
	 * @param lastBrdid
	 * @return
	 */
	Page<Board> getBoardListMore(Integer menuid, Integer depth, Pageable pageable, Integer lastBrdid, Integer categorycd);

	/**
	 * 게시판 상세정보 가져오기
	 * @param brdid
	 * @return
	 */
	Board getBoardByQuerydsl(Long brdid);
	
}
