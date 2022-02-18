package org.zerock.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

public class testBatch {


	@Scheduled(cron = "0 * * * * *")
	public void testMethod() throws Exception{
		
		System.out.println("배치 실행 테스트.......");
		System.out.println("===================================");
		
	}
	
}
