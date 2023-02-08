package com.sbs.exam.demo.service;

import org.springframework.stereotype.Service;

import com.sbs.exam.demo.repository.BoardRepository;
import com.sbs.exam.demo.vo.Board;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository boardRepository;

	public Board getBoardById(int id) {
		return boardRepository.getBoardById(id);
	}
}