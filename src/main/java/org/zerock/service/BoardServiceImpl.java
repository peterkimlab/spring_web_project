package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Service
@RequiredArgsConstructor
@ToString
public class BoardServiceImpl implements BoardService {
	// 인터페이스 쓰는 목적은 실제 객체가 뭔지 모르게 한다. 타입만 보고 코딩 할 수 있게 함.
	
	private final BoardMapper mapper;

	@Override
	public Long register(BoardVO board) {
		mapper.insertSelectKey(board);
		
		return board.getBno();
	}

	@Override
	public BoardVO get(Long bno) {
		
		return mapper.read(bno);
	}

	@Override
	public int modify(BoardVO board) {
		
		return mapper.update(board);
	}

	@Override
	public int remove(Long bno) {
		
		return mapper.delete(bno);
	}

	@Override
	public List<BoardVO> getList() {
		
		return mapper.getList();
	}
}
