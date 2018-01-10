package com.spring.web.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.commons.text.StringEscapeUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.web.SpringHomeApplication;
import com.spring.web.domain.Board;
import com.spring.web.dto.BoardDto;
import com.spring.web.repository.BoardRepository;

@Service("boardService")
@Transactional
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	/**
	 * board 리스트 가져오기
	 */
	@Override
	public Page<Board> getBoardList(Integer BOARD_MENUID, Pageable pageable, Integer lastBrdid) {
		
		//return boardRepository.findByMenuidAndDepth(BOARD_MENUID, 1, pageable);

		if(lastBrdid != null && lastBrdid > 0) {
			return boardRepository.getBoardListMore(BOARD_MENUID, 1, pageable, lastBrdid);
		} else {
			return boardRepository.getBoardList(BOARD_MENUID, 1, pageable);
		}
	}

	/**
	 * board 상세 가져오기
	 */
	@Override
	public Board getBoard(Long brdid) {

		//return boardRepository.findOne(brdid);
		Board boardDetail = boardRepository.getBoardByQuerydsl(brdid);
		
		//내용 unescape Html 처리
		boardDetail.setContents(StringEscapeUtils.unescapeHtml4(boardDetail.getContents()));
		
		return boardDetail;
	}

	/**
	 * board 등록
	 */
	@Override
	public Board insertBoard(HttpServletRequest request, Integer BOARD_MENUID, BoardDto.Create boardCreate) {
		
		Board board = modelMapper.map(boardCreate, Board.class);
		board.setSitemenuid(SpringHomeApplication.SITEMENUID);
		board.setMenuid(BOARD_MENUID);
		
		String ip = request.getHeader("X-FORWARDED-FOR");
		if(ip == null) {
			ip = request.getRemoteAddr();
		}
		board.setIp(ip);
		board.setRegdt(new Date());
		
		//저장
		Board newBoard = boardRepository.save(board);
		newBoard.setTopbrdid(newBoard.getBrdid());
		
		//업데이트
		newBoard = boardRepository.save(newBoard);

		return newBoard;
	}

}
