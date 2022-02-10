package org.zerock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.mapper.AuthorMapper;
import org.zerock.model.AuthorVO;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
    AuthorMapper authorMapper;
	
	@Override
	public void authorEnroll(AuthorVO author) throws Exception {
		
		authorMapper.authorEnroll(author);
	}

}
