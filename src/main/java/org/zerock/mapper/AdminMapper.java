package org.zerock.mapper;

import java.util.List;

import org.zerock.model.BookVO;
import org.zerock.model.CateVO;

public interface AdminMapper {
	
	/* 상품 등록 */
	public void bookEnroll(BookVO book);
	
	/* 카테고리 리스트 */
	public List<CateVO> cateList();
	
}
