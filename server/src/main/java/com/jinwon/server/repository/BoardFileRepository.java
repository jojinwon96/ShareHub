package com.jinwon.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jinwon.server.entity.BoardFileEntity;

public interface BoardFileRepository extends JpaRepository<BoardFileEntity, Integer> {

}
