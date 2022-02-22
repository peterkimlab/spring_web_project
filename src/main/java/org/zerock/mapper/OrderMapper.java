package org.zerock.mapper;

import org.zerock.model.OrderPageItemDTO;

public interface OrderMapper {

	/* 주문 상품 정보 */	
	public OrderPageItemDTO getGoodsInfo(int bookId);	
	
}
