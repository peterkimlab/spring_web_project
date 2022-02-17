package org.zerock.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mapper.AdminMapper;
import org.zerock.model.BookVO;
import org.zerock.model.CateVO;
import org.zerock.model.Criteria;

import lombok.extern.log4j.Log4j;

@Service
public class AdminServiceImpl implements AdminService {
	
	private static final Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Autowired
	private AdminMapper adminMapper;	
	
	/* 상품 등록 */
	@Transactional
	@Override
	public void bookEnroll(BookVO book) {
		
		log.info("(srevice)bookEnroll........");
		
		adminMapper.bookEnroll(book);
		
		if(book.getImageList() == null || book.getImageList().size() <= 0) {
			return;
		}
		
		book.getImageList().forEach(attach ->{
			
			attach.setBookId(book.getBookId());
			adminMapper.imageEnroll(attach);
			
		});
		
	}
	
	/* 카테고리 리스트 */
	@Override
	public List<CateVO> cateList() {
		
		log.info("(service)cateList........");
		
		return adminMapper.cateList();
	}
	
	/* 상품 리스트 */
	@Override
	public List<BookVO> goodsGetList(Criteria cri) {
		log.info("goodsGetTotalList()..........");
		return adminMapper.goodsGetList(cri);
	}

	/* 상품 총 갯수 */
	public int goodsGetTotal(Criteria cri) {
		log.info("goodsGetTotal().........");
		return adminMapper.goodsGetTotal(cri);
	}
	
	/* 상품 조회 페이지 */
	@Override
	public BookVO goodsGetDetail(int bookId) {
		
		log.info("(service)bookGetDetail......." + bookId);
		
		return adminMapper.goodsGetDetail(bookId);
	}
	
	/* 상품 정보 수정 */
	@Transactional
	@Override
	public int goodsModify(BookVO vo) {

		int result = adminMapper.goodsModify(vo);
		
		if(result == 1 && vo.getImageList() != null && vo.getImageList().size() > 0) {
			
			adminMapper.deleteImageAll(vo.getBookId());
			
			vo.getImageList().forEach(attach -> {
				
				attach.setBookId(vo.getBookId());
				adminMapper.imageEnroll(attach);
				
			});
			
		}
		
		return result;
	}
	
	/* 상품 정보 삭제 */
	@Override
	public int goodsDelete(int bookId) {

		log.info("goodsDelete..........");
		
		return adminMapper.goodsDelete(bookId);
	}
	
	
}
