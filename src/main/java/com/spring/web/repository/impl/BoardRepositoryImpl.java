package com.spring.web.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.web.domain.Board;
import com.spring.web.domain.QBoard;
import com.spring.web.repository.BoardRepositoryCustom;

@Repository
public class BoardRepositoryImpl implements BoardRepositoryCustom {

	@Autowired
	private JPAQueryFactory queryFactory;

	@Override
	public Page<Board> getBoardList(Integer menuid, Integer depth, Pageable pageable) {
		
		QBoard board = QBoard.board;
		
		QueryResults<Board> result = queryFactory.selectFrom(board)
												.where(board.menuid.eq(menuid), board.depth.eq(depth))
												.fetchResults();
		
		return new PageImpl<>(result.getResults(), pageable, result.getTotal());
	}
	
	@Override
	public Board getBoardByQuerydsl(Long brdid) {
		
		QBoard board = QBoard.board;
        
		return queryFactory.selectFrom(board)
							.where(board.brdid.eq(brdid))
							.fetchOne();
	}

}
