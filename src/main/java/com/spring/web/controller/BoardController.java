package com.spring.web.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spring.web.domain.Board;
import com.spring.web.service.BoardService;

@RestController
public class BoardController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="boardService")
	private BoardService boardService;
	
	private final Integer BOARD_MENUID = 529;
	private final Integer ALBUM_MENUID = 547;
	private final Integer VIDEO_MENUID = 0;
	
	/**
	 * 게시판 리스트
	 * @param pageable
	 * @return
	 */
	@GetMapping("/board")
	public ResponseEntity<Page<Board>> getBoardList(@PageableDefault(sort={"regdt"}, direction=Direction.DESC, size=5) Pageable pageable) {
		Page<Board> page = boardService.findByMenuidAndDepth(BOARD_MENUID, pageable);
		
		return new ResponseEntity<Page<Board>>(page, HttpStatus.OK);
	}
	
	/**
	 * 게시판 상세보기
	 * @param brdid
	 * @return
	 */
	@GetMapping("/board/{brdid}")
	public ResponseEntity<Board> getBoard(@PathVariable Long brdid) {
		Board board = boardService.findOne(brdid);
		
		return new ResponseEntity<Board>(board, HttpStatus.OK);
	}
	
	/**
	 * 앨범 리스트
	 * @param pageable
	 * @return
	 */
	@GetMapping("/album")
	public ResponseEntity<Page<Board>> getAlbumList(@PageableDefault(sort={"regdt"}, direction=Direction.DESC, size=5) Pageable pageable) {
		Page<Board> page = boardService.findByMenuidAndDepth(ALBUM_MENUID, pageable);
		
		return new ResponseEntity<Page<Board>>(page, HttpStatus.OK);
	}
	
	/**
	 * 앨범 상세보기
	 * @param brdid
	 * @return
	 */
	@GetMapping("/album/{brdid}")
	public ResponseEntity<Board> getAlbum(@PathVariable Long brdid) {
		Board album = boardService.findOne(brdid);
		
		return new ResponseEntity<Board>(album, HttpStatus.OK);
	}
	

	
}
