package org.zerock.service;

import java.util.List;

import org.zerock.model.BookVO;
import org.zerock.model.CateVO;
import org.zerock.model.Criteria;

public interface BookService {

	/* 상품 검색 */
	public List<BookVO> getGoodsList(Criteria cri);
	
	/* 상품 총 갯수 */
	public int goodsGetTotal(Criteria cri);
	
	/* 국내 카테고리 리스트 */
	public List<CateVO> getCateCode1();
	
	/* 외국 카테고리 리스트 */
	public List<CateVO> getCateCode2();
	
}
