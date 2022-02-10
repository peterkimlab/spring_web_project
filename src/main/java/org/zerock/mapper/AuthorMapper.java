package org.zerock.mapper;

import java.util.List;

import org.zerock.model.AuthorVO;
import org.zerock.model.Criteria;

public interface AuthorMapper {
	
	/* 작가 등록 */
	public void authorEnroll(AuthorVO author);

	/* 작가 목록 */
	public List<AuthorVO> authorGetList(Criteria cri);
	
}
