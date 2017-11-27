package com.spring.web.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.web.domain.Board;

@Repository("boardRepository")
public interface BoardRepository extends JpaRepository<Board, Long> {

	Page<Board> findByMenuidAndDepth(Integer menuid, Integer depth, Pageable pageble);

}
