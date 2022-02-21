package org.zerock.service;

import org.zerock.model.CartDTO;

public interface CartService {
	
	/* 장바구니 추가 */
	public int addCart(CartDTO cart);	

}
