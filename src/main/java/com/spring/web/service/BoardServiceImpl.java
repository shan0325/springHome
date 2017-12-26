package com.spring.web.service;

import javax.transaction.Transactional;

import org.apache.commons.text.StringEscapeUtils;
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
	public Page<Board> getBoardList(Integer BOARD_MENUID, Pageable pageable, Integer lastBrdid) {
		
		//return boardRepository.findByMenuidAndDepth(BOARD_MENUID, 1, pageable);

		if(lastBrdid != null && lastBrdid > 0) {
			return boardRepository.getBoardListMore(BOARD_MENUID, 1, pageable, lastBrdid);
		} else {
			return boardRepository.getBoardList(BOARD_MENUID, 1, pageable);
		}
	}

	@Override
	public Board getBoard(Long brdid) {

		//return boardRepository.findOne(brdid);
		Board boardDetail = boardRepository.getBoardByQuerydsl(brdid);
		
		//내용 unescape Html 처리
		boardDetail.setContents(StringEscapeUtils.unescapeHtml4(boardDetail.getContents()));
		
		return boardDetail;
	}

}
