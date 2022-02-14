package org.zerock.mapper;

import java.util.List;

import org.zerock.model.BookVO;
import org.zerock.model.CateVO;
import org.zerock.model.Criteria;

public interface AdminMapper {
	
	/* 상품 등록 */
	public void bookEnroll(BookVO book);
	
	/* 카테고리 리스트 */
	public List<CateVO> cateList();
	
	/* 상품 리스트 */
	public List<BookVO> goodsGetList(Criteria cri);
	
	/* 상품 총 개수 */
	public int goodsGetTotal(Criteria cri);
	
}
