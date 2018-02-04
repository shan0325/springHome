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
import com.spring.web.exception.BoardNotFoundException;
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
	public Page<Board> getBoardList(Integer BOARD_MENUID, Pageable pageable, Integer lastBrdid, Integer categorycd) {
		
		//return boardRepository.findByMenuidAndDepth(BOARD_MENUID, 1, pageable);

		if(lastBrdid != null && lastBrdid > 0) {
			return boardRepository.getBoardListMore(BOARD_MENUID, 1, pageable, lastBrdid, categorycd);
		} else {
			return boardRepository.getBoardList(BOARD_MENUID, 1, pageable, categorycd);
		}
	}

	/**
	 * board 상세 가져오기
	 */
	@Override
	public Board getBoard(Long brdid) {

		//return boardRepository.findOne(brdid);
		Board boardDetail = getBoardDetail(brdid);
		
		//내용 unescape Html 처리
		boardDetail.setContents(StringEscapeUtils.unescapeHtml4(boardDetail.getContents()));
		
		return boardDetail;
	}
	
	public Board getBoardDetail(Long brdid) {
		//return boardRepository.findOne(brdid);
		Board boardDetail = boardRepository.getBoardByQuerydsl(brdid);
		
		if(boardDetail == null) {
			throw new BoardNotFoundException(brdid);
		}
		
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
		board.setTitle("board");
		
		String ip = request.getHeader("X-FORWARDED-FOR");
		if(ip == null) {
			ip = request.getRemoteAddr();
		}
		board.setIp(ip);
		board.setRegdt(new Date());
		
		//저장
		Board newBoard = boardRepository.save(board);
		newBoard.setTopbrdid(newBoard.getBrdid());
		newBoard.setTitle("board_" + newBoard.getBrdid());
		
		//업데이트
		newBoard = boardRepository.save(newBoard);

		return newBoard;
	}

	/**
	 * board 수정
	 */
	@Override
	public Board updateBoard(Long brdid, BoardDto.Update updateBoard) {

		Board boardDetail = getBoardDetail(brdid);
		boardDetail.setRegnm(updateBoard.getRegnm());
		boardDetail.setPwd(updateBoard.getPwd());
		boardDetail.setContents(updateBoard.getContents());
		
		return boardRepository.save(boardDetail);
	}

	/**
	 * board 삭제
	 */
	@Override
	public void deleteBoard(Long brdid) {
		
		boardRepository.delete(brdid);
	}

	@Override
	public boolean checkPassword(Long brdid, String pwd) {
		
		Board boardDetail = getBoardDetail(brdid);
		
		if(!pwd.equals(boardDetail.getPwd())) {
			
			return false;
		}
		
		return true;
	}

}
