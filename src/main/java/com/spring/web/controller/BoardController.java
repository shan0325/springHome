package com.spring.web.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.hibernate.boot.jaxb.hbm.spi.ResultSetMappingBindingDefinition;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.web.common.ErrorResponse;
import com.spring.web.domain.Board;
import com.spring.web.dto.BoardDto;
import com.spring.web.exception.BoardNotFoundException;
import com.spring.web.service.BoardService;

@RestController
public class BoardController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="boardService")
	private BoardService boardService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private final Integer BOARD_MENUID = 529;
	private final Integer ALBUM_MENUID = 547;
	private final Integer VIDEO_MENUID = 0;
	
	/**
	 * 게시판 리스트
	 * @param pageable
	 * @return
	 */
	@GetMapping("/board")
	public Page<BoardDto.Board> getBoardList(@PageableDefault(sort={"regdt"}, direction=Direction.DESC, size=10) Pageable pageable,
												@RequestParam(required=false) Integer lastBrdid,
												@RequestParam(required=false) Integer categorycd) {
		
		Page<Board> page = boardService.getBoardList(BOARD_MENUID, pageable, lastBrdid, categorycd);
		
		//modelMapper를 사용하여 응답해줄 파라미터 지정
		List<BoardDto.Board> collect = page.getContent()
										.stream()
										.map(i -> {
											i.setContents(StringEscapeUtils.unescapeHtml4(i.getContents()));
											return modelMapper.map(i, BoardDto.Board.class);
										})
										.collect(Collectors.toList());
		
		return new PageImpl<>(collect, pageable, page.getTotalElements());
	}
	
	/**
	 * 게시판 상세보기
	 * @param brdid
	 * @return
	 */
	@GetMapping("/board/{brdid}")
	public ResponseEntity<BoardDto.BoardDetail> getBoard(@PathVariable Long brdid) {
		
		Board board = boardService.getBoard(brdid);
		
		//modelMapper를 사용하여 응답해줄 파라미터 지정
		return new ResponseEntity<BoardDto.BoardDetail>(modelMapper.map(board, BoardDto.BoardDetail.class), HttpStatus.OK);
	}
	
	/**
	 * 게시판 등록
	 * @param request
	 * @param board
	 * @return
	 */
	@PostMapping("/board")
	public ResponseEntity insertBoard(HttpServletRequest request, 
										@RequestBody @Valid BoardDto.Create board,
										BindingResult result) {
		
		if(result.hasErrors()) {
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setMessage(ErrorResponse.MESSAGE_BAD_REQUEST);
			errorResponse.setCode(ErrorResponse.CODE_BAD_REQUEST);
			errorResponse.setErrors(result.getFieldErrors());
			
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}
		
		Board newBoard = boardService.insertBoard(request, BOARD_MENUID, board);
		
		return new ResponseEntity<>(modelMapper.map(newBoard, BoardDto.BoardDetail.class), HttpStatus.CREATED);
	}
	
	/**
	 * 게시판 수정
	 * @param brdid
	 * @param updateBoard
	 * @param result
	 * @return
	 */
	@PutMapping("/board/{brdid}")
	public ResponseEntity updateBoard(@PathVariable Long brdid,
										@RequestBody @Valid BoardDto.Update updateBoard,
										BindingResult result) {
		
		if(result.hasErrors()) {
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setMessage(ErrorResponse.MESSAGE_BAD_REQUEST);
			errorResponse.setCode(ErrorResponse.CODE_BAD_REQUEST);
			errorResponse.setErrors(result.getFieldErrors());
			
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}
		
		Board board = boardService.updateBoard(brdid, updateBoard);
		
		return new ResponseEntity<>(modelMapper.map(board, BoardDto.BoardDetail.class), HttpStatus.OK);
	}
	
	/**
	 * 게시판 삭제
	 * @param brdid
	 * @param deleteBoard
	 * @param result
	 * @return
	 */
	@DeleteMapping("/board/{brdid}")
	public ResponseEntity deleteBoard(@PathVariable Long brdid) {
		
		boardService.deleteBoard(brdid);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * 비밀번호 확인
	 * @param brdid
	 * @param paramMap
	 * @return
	 */
	@PostMapping("/board/{brdid}/checkPwd")
	public ResponseEntity checkPwd(@PathVariable Long brdid,
									@RequestBody @Valid BoardDto.CheckPassword checkPwd,
									BindingResult result) {
		
		if(result.hasErrors()) {
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setMessage(ErrorResponse.MESSAGE_BAD_REQUEST);
			errorResponse.setCode(ErrorResponse.CODE_BAD_REQUEST);
			errorResponse.setErrors(result.getFieldErrors());
			
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}
		  
		boolean resultVal = boardService.checkPassword(brdid, checkPwd.getPwd());
		
		return new ResponseEntity<>(resultVal, HttpStatus.OK);
	}
	
	
	
	/**
	 * 앨범 리스트
	 * @param pageable
	 * @return
	 */
	@GetMapping("/album")
	public Page<BoardDto.Board> getAlbumList(@PageableDefault(sort={"regdt"}, direction=Direction.DESC, size=10) Pageable pageable,
												@RequestParam(required=false) Integer lastBrdid,
												@RequestParam(required=false) Integer categorycd) {
		
		Page<Board> page = boardService.getBoardList(ALBUM_MENUID, pageable, lastBrdid, categorycd);
		
		//modelMapper를 사용하여 응답해줄 파라미터 지정
		List<BoardDto.Board> collect = page.getContent()
										.stream()
										.map(i -> {
											i.setContents(StringEscapeUtils.unescapeHtml4(i.getContents()));
											return modelMapper.map(i, BoardDto.Board.class);
										})
										.collect(Collectors.toList());
		
		return new PageImpl<>(collect, pageable, page.getTotalElements());
	}
	
	/**
	 * 앨범 상세보기
	 * @param brdid
	 * @return
	 */
	@GetMapping("/album/{brdid}")
	public ResponseEntity<BoardDto.BoardDetail> getAlbum(@PathVariable Long brdid) {
		
		Board album = boardService.getBoard(brdid);
		
		//modelMapper를 사용하여 응답해줄 파라미터 지정
		return new ResponseEntity<BoardDto.BoardDetail>(modelMapper.map(album, BoardDto.BoardDetail.class), HttpStatus.OK);
	}
	
	@ExceptionHandler(BoardNotFoundException.class)
	public ResponseEntity handleBoardNotFoundException(BoardNotFoundException e) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage("[" + e.getBrdid() + "]에 해당하는 게시판이 없습니다.");
		errorResponse.setCode("board.not.found.exception");
		
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	
}
