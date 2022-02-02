package org.zerock.service;

import org.springframework.stereotype.Service;
import org.zerock.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@RequiredArgsConstructor
@ToString
public class BoardServiceImpl implements BoardService {
	// 인터페이스 쓰는 목적은 실제 객체가 뭔지 모르게 한다. 타입만 보고 코딩 할 수 있게 함.
	
	private final BoardMapper mapper;
}
