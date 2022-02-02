package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.BoardVO;

public interface BoardMapper {

	public List<BoardVO> getList();
	
	public void insert(BoardVO board);

	public Integer insertSelectKey(BoardVO board);
	
	BoardVO read(Long bno);
	
	int delete(Long bno);
	
	int update(BoardVO board);

}