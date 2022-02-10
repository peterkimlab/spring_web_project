package org.zerock.service;

import org.zerock.model.AuthorVO;

public interface AuthorService {

	/* 작가 등록 */
	public void authorEnroll(AuthorVO author) throws Exception;
	
}
