package com.spring.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.text.StringEscapeUtils;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spring.web.domain.Board;
import com.spring.web.dto.BoardDto;
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
	public Page<BoardDto.Board> getBoardList(@PageableDefault(sort={"regdt"}, direction=Direction.DESC, size=5) Pageable pageable) {
		Page<Board> page = boardService.findByMenuidAndDepth(BOARD_MENUID, pageable);
		
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
		Board board = boardService.findOne(brdid);
		
		//modelMapper를 사용하여 응답해줄 파라미터 지정
		return new ResponseEntity<BoardDto.BoardDetail>(modelMapper.map(board, BoardDto.BoardDetail.class), HttpStatus.OK);
	}
	
	/**
	 * 앨범 리스트
	 * @param pageable
	 * @return
	 */
	@GetMapping("/album")
	public Page<BoardDto.Board> getAlbumList(@PageableDefault(sort={"regdt"}, direction=Direction.DESC, size=5) Pageable pageable) {
		Page<Board> page = boardService.findByMenuidAndDepth(ALBUM_MENUID, pageable);
		
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
		Board album = boardService.findOne(brdid);
		
		//modelMapper를 사용하여 응답해줄 파라미터 지정
		return new ResponseEntity<BoardDto.BoardDetail>(modelMapper.map(album, BoardDto.BoardDetail.class), HttpStatus.OK);
	}
	

	
}
