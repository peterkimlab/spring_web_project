package org.zerock.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;

import lombok.extern.log4j.Log4j;
import sun.rmi.runtime.Log;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {
	
	@Autowired
	private BoardService service;
	
	@Test
	public void testPrint() {
		log.info("service : " + service.toString());
	}
	
	@Test
	public void testGetList() {
		service.getList().forEach(board -> log.info(board));
	}
	
	@Test
	public void testRegister() {
		BoardVO vo = new BoardVO();
		vo.setTitle("비지니스 영역");
		vo.setContent("저항과 지지 라인 잡기");
		vo.setWriter("Burger");
		
		long bno = service.register(vo);
		log.info("생성된 게시물의 번호 : " + bno);
	}

	@Test
	public void testGet() {

		log.info(service.get(1L));
	}

	@Test
	public void testDelete() {

		// 게시물 번호의 존재 여부를 확인하고 테스트할 것
		log.info("REMOVE RESULT: " + service.remove(2L));

	}

	@Test
	public void testUpdate() {

		BoardVO board = service.get(1L);

		if (board == null) {
			return;
		}

		board.setTitle("제목 수정합니다.");
		log.info("MODIFY RESULT: " + service.modify(board));
	}

}
