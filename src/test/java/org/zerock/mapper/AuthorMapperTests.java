package org.zerock.mapper;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.zerock.model.AuthorVO;
import org.zerock.model.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)

//Test for Controller
@WebAppConfiguration

@ContextConfiguration({ 
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@Log4j
public class AuthorMapperTests {
	
	@Setter(onMethod_ = { @Autowired })
	private WebApplicationContext ctx;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}

	@Autowired
	private AuthorMapper mapper;			//AuthorMapper.java 인터페이스 의존성 주입
	
	/* 작가 등록 테스트 */
    @Test
    public void authorEnroll() throws Exception{
        
        AuthorVO author = new AuthorVO();
        
        author.setNationId("01");
        author.setAuthorName("테스트");
        author.setAuthorIntro("테스트 소개");
        
        mapper.authorEnroll(author);
        
    }
    
    /* 작가 목록 테스트 */
    @Test
    public void authorGetListTest() throws Exception{
        
        Criteria cri = new Criteria(1,10);    // 3페이지 & 10개 행 표시
        cri.setKeyword("테스트");
        
        
        List<AuthorVO> list = mapper.authorGetList(cri);
        
        for(int i = 0; i < list.size(); i++) {
            System.out.println("list" + i + ".........." + list.get(i));
        }
        
    }
    
    /* 작가 총 수 */
	@Test
	public void authorGetTotalTest() throws Exception{
		
		Criteria cri = new Criteria();
		cri.setKeyword("테스트");
		
		int total = mapper.authorGetTotal(cri);
		
		System.out.println("total........." + total);
		
		
	}
	
	/* 작가 상세 페이지 */
	@Test
	public void authorGetDetailTest() {
		
		int authorId = 30;
		
		AuthorVO author = mapper.authorGetDetail(authorId);
		
		System.out.println("author......." + author);
		
	}
	
	/* 작가 정보 수정 */
	@Test
	public void authorModifyTest() {
		
		AuthorVO author = new AuthorVO();
				
		author.setAuthorId(1);
		System.out.println("수정 전...................." + mapper.authorGetDetail(author.getAuthorId()));
		
		author.setAuthorName("수정");
		author.setNationId("01");
		author.setAuthorIntro("소개 수정 하였습니다.");
		
		mapper.authorModify(author);
		System.out.println("수정 후...................." + mapper.authorGetDetail(author.getAuthorId()));
		
	}
}
