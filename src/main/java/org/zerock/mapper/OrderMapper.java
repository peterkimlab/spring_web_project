package org.zerock.mapper;

import org.zerock.model.BookVO;
import org.zerock.model.CartDTO;
import org.zerock.model.MemberVO;
import org.zerock.model.OrderDTO;
import org.zerock.model.OrderItemDTO;
import org.zerock.model.OrderPageItemDTO;

public interface OrderMapper {

	/* 주문 상품 정보 */	
	public OrderPageItemDTO getGoodsInfo(int bookId);
	
	/* 주문 상품 정보(주문 처리) */	
	public OrderItemDTO getOrderInfo(int bookId);
	
	/* 주문 테이블 등록 */
	public int enrollOrder(OrderDTO ord);
	
	/* 주문 아이템 테이블 등록 */
	public int enrollOrderItem(OrderItemDTO orid);
	
	/* 주문 금액 차감 */
	public int deductMoney(MemberVO member);
	
	/* 주문 재고 차감 */
	public int deductStock(BookVO book);
	
	/* 카트 제거(주문) */
	public int deleteOrderCart(CartDTO dto);
	
}
