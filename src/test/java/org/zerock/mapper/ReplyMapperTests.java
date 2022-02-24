package org.zerock.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.model.Criteria;
import org.zerock.model.ReplyDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ReplyMapperTests {
	
	@Autowired
	private ReplyMapper mapper;

	
	@Test
	public void replyEnrollTest() {
		
		String id = "a";
		int bookId = 1;
		double rating = 3.5;
		String content = "댓글 테스트";
		
		ReplyDTO dto = new ReplyDTO();
		dto.setBookId(bookId);
		dto.setMemberId(id);
		dto.setRating(rating);
		dto.setContent(content);
		
		mapper.enrollReply(dto);
		
		
	}
	
	@Test
	public void replyListTest() {
		
		Criteria cri = new Criteria(1,10);    // 3페이지 & 10개 행 표시
        cri.setBookId(1);
		
        List<ReplyDTO> list = mapper.getReplyList(cri);
		
		for(int i = 0; i < list.size(); i++) {
            System.out.println("replyList" + i + ".........." + list.get(i));
        }
		
	}
	
}
