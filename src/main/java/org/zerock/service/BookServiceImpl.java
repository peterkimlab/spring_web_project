package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.zerock.mapper.BookMapper;
import org.zerock.model.BookVO;
import org.zerock.model.Criteria;


@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookMapper bookMapper;
	
	/* 상품 검색 */
	@Override
	public List<BookVO> getGoodsList(Criteria cri) {
		
		System.out.println("getGoodsList().......");
		
		return bookMapper.getGoodsList(cri);
	}

	/* 사품 총 갯수 */
	@Override
	public int goodsGetTotal(Criteria cri) {
		
		System.out.println("goodsGetTotal().......");
		
		return bookMapper.goodsGetTotal(cri);
	}	
	
}
