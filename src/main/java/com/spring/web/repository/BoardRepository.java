package com.spring.web.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.web.domain.Board;

@Repository("boardRepository")
@Transactional
public interface BoardRepository extends JpaRepository<Board, Integer> {

	Page<Board> findByMenuid(Integer menuid, Pageable pageble);

}
