package com.spring.web.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.web.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {

	Page<Board> findByMenuidAndDepth(Integer menuid, Integer depth, Pageable pageable);

}
