package com.rubypaper.persistence;

import com.rubypaper.domain.Board;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.rubypaper.domain.BoardDsl;

public interface DynamicBoardRepository extends CrudRepository<BoardDsl, Long>, QuerydslPredicateExecutor<BoardDsl> {

}
