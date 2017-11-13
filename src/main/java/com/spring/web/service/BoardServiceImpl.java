package com.spring.web.service;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.web.domain.Board;
import com.spring.web.repository.BoardRepository;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	
	@Resource(name="boardRepository")
	private BoardRepository boardRepository;
	
	@Override
	public Page<Board> findByMenuid(Integer BOARD_MENUID, Pageable pageable) {
		
		return boardRepository.findByMenuid(BOARD_MENUID, pageable);
	}

	@Override
	public Board findOne(Integer brdid) {

		return boardRepository.findOne(brdid);
	}

}
