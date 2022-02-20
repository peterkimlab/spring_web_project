package org.zerock.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.mapper.AttachMapper;
import org.zerock.mapper.BookMapper;
import org.zerock.model.AttachImageVO;
import org.zerock.model.BookVO;
import org.zerock.model.CateVO;
import org.zerock.model.Criteria;


@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookMapper bookMapper;
	
	@Autowired
	private AttachMapper attachMapper;
	
	/* 상품 검색 */
	@Override
	public List<BookVO> getGoodsList(Criteria cri) {
		
		System.out.println("getGoodsList().......");
		
		String type = cri.getType();
		String[] typeArr = type.split("");
		String[] authorArr = bookMapper.getAuthorIdList(cri.getKeyword());
		
		
		if(type.equals("A") || type.equals("AC") || type.equals("AT") || type.equals("ACT")) {
			if(authorArr.length == 0) {
				return new ArrayList();
			}
		}
		
		for(String t : typeArr) {
			if(t.equals("A")) {
				cri.setAuthorArr(authorArr);
			}
		}		
		
		List<BookVO> list = bookMapper.getGoodsList(cri);
		
		list.forEach(book -> {
			
			int bookId = book.getBookId();
			
			List<AttachImageVO> imageList = attachMapper.getAttachList(bookId);
			
			book.setImageList(imageList);
			
		});
		
		return list;
	}

	/* 사품 총 갯수 */
	@Override
	public int goodsGetTotal(Criteria cri) {
		
		System.out.println("goodsGetTotal().......");
		
		return bookMapper.goodsGetTotal(cri);
	}
	
	/* 국내 카테고리 리스트 */
	@Override
	public List<CateVO> getCateCode1() {
		
		System.out.println("getCateCode1().......");
		
		return bookMapper.getCateCode1();
	}

	/* 외국 카테고리 리스트 */
	@Override
	public List<CateVO> getCateCode2() {
		
		System.out.println("getCateCode2().......");
		
		return bookMapper.getCateCode2();
	}
	
}
