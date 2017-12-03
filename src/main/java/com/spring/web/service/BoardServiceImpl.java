package com.spring.web.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.web.domain.Board;
import com.spring.web.repository.BoardRepository;

@Service("boardService")
@Transactional
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	
	@Override
	public Page<Board> findByMenuidAndDepth(Integer BOARD_MENUID, Pageable pageable) {
		
		//return boardRepository.findByMenuidAndDepth(BOARD_MENUID, 1, pageable);
		return boardRepository.getBoardList(BOARD_MENUID, 1, pageable);
	}

	@Override
	public Board findOne(Long brdid) {

		//return boardRepository.findOne(brdid);
		return boardRepository.getBoardByQuerydsl(brdid);
	}

}
