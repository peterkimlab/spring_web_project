package org.zerock.sample;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

//테스트 코드는 우선 현재 테스트 코드가 스프링을 실행하는 역할을 할 것이라는 것을 @Runwith어노테이션으로 표현

/* @ContextConfiguration = 지정된 클래스나 문자열을  이용해서 필요한 객체들을 스프링 내장 객체로 등록하게 됨(빈으로 등록된다)
@ContextConfiguration에 문자열은 'classpath:'나 'file:'을 이용할 수있으므로 이클립스에서 자동으로 생성된 root-context.xml경로 지정이 가능. */

//@Log4j Lombok을 이용해서 로그를 기록하는 Logger를 변수로 생성함. 별도의 처리없이Log4j라이브러리와 설저이 존재하면 바로 사용가능

//Spring Legacy Project= 기본으로 log4j 설정이 완료 되있어서 별도 처리 필요없다 (src/main/resources 와 src/test/resources존재함)

//@Autowired는 해당 인스턴스 변수가 스프링으로 부터  자동주입해 달라는 표시이고,스프링이 정상 주입이 가능하면 obj변수에 Restaurant타입의 객체를 주입하게 됨.

//@Test Junit에서 테스트 대상을  표시하는것

//assertNotNull(restaurant)=restaurant변수가 null이 아니어야만 테스트가 성공한다는 의미  (Run As-> Junit Test)

@RunWith(SpringJUnit4ClassRunner.class)//  테스트 코드 표시 
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class SampleTest {
	
	@Setter(onMethod_ = {@Autowired})
    private Restaurant restaurant;
    
 
    @Test
    public void testExist() {
        assertNotNull(restaurant);
        
        log.info(restaurant);
        log.info("-----------------------------");
        log.info(restaurant.getChef());
        
    }

}
