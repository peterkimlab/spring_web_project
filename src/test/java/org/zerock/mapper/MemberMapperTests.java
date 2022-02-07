package org.zerock.mapper;

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
import org.zerock.controller.BoardControllerTests;
import org.zerock.model.MemberVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)

//Test for Controller
@WebAppConfiguration

@ContextConfiguration({ 
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@Log4j
public class MemberMapperTests {
	
	@Setter(onMethod_ = { @Autowired })
	private WebApplicationContext ctx;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}

	@Autowired
	private MemberMapper membermapper;			//MemberMapper.java 인터페이스 의존성 주입
	
	//회원가입 쿼리 테스트 메서드
	@Test
	public void memberJoin() throws Exception{
		MemberVO member = new MemberVO();
		
		member.setMemberId("test1");			//회원 id
		member.setMemberPw("test1");			//회원 비밀번호
		member.setMemberName("test");		//회원 이름
		member.setMemberMail("test");		//회원 메일
		member.setMemberAddr1("test");		//회원 우편번호
		member.setMemberAddr2("test");		//회원 주소
		member.setMemberAddr3("test");		//회원 상세주소
		
		membermapper.memberJoin(member);			//쿼리 메서드 실행
		
	}
	
	@Test 
	public void testMemberJoin() throws Exception { 
 
		String resultPage = mockMvc 
				.perform(MockMvcRequestBuilders.post("/member/join").param("memberId", "Test3").param("memberPw", "admin") 
						.param("memberName", "admin").param("memberMail", "admin")
						.param("memberAddr1", "admin").param("memberAddr2", "admin").param("memberAddr3", "admin"))
				.andReturn().getModelAndView().getViewName(); 
 
		log.info(resultPage); 
 
	}
	
	// 아이디 중복검사
	@Test
	public void memberIdChk() throws Exception{
		String id = "test";	// 존재하는 아이디
		String id2 = "test123";	// 존재하지 않는 아이디
		membermapper.idCheck(id);
		membermapper.idCheck(id2);
	}
	
	/* 로그인 쿼리 mapper 메서드 테스트 */
    @Test
    public void memberLogin() throws Exception{
        
        MemberVO member = new MemberVO();    // MemberVO 변수 선언 및 초기화
        
        /* 올바른 아이디 비번 입력경우 */
        member.setMemberId("test");
        member.setMemberPw("test");
        
        /* 올바른 않은 아이디 비번 입력경우 */
        //member.setMemberId("test1123");
        //member.setMemberPw("test1321321");
        
        membermapper.memberLogin(member);
        System.out.println("결과 값 : " + membermapper.memberLogin(member));
        
    }
	
	
	
}
