package org.zerock.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {

	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;

	@Test
	public void testGetList() {

		mapper.getList().forEach(board -> log.info(board));

	}
	
	@Test
	public void testInsert() {
		BoardVO vo = new BoardVO();
		vo.setTitle("코인 분석");
		vo.setContent("저항과 지지 라인 잡기");
		vo.setWriter("Burger");
		
		mapper.insert(vo);
		log.info("---------------------------");
		log.info("after insert " + vo.getBno());
	}
	
	@Test
	public void testInsertSelectKey() {
		BoardVO vo = new BoardVO();
		vo.setTitle("SelectKey 코인 분석");
		vo.setContent("SelectKey 저항과 지지 라인 잡기");
		vo.setWriter("SelectKey Burger");
		
		mapper.insertSelectKey(vo);
		
		log.info("---------------------------");
		log.info("after insert selectkey " + vo.getBno());
	}
	
	@Test
	public void testRead() {
		BoardVO vo = mapper.read(3L);
		log.info(vo);
	}
	
	@Test
	public void testDelete() {
		int count = mapper.delete(2L);
		log.info("count : " + count);
	}
	
	@Test
	public void testUpdate() {
		BoardVO vo = new BoardVO();
		vo.setBno(5L);
		vo.setTitle("Updated Title");
		vo.setContent("Updated content");
		vo.setWriter("user00");
		
		log.info("count : " + mapper.update(vo));
	}

}
