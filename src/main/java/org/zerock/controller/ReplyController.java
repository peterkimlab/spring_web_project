package org.zerock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.zerock.model.ReplyDTO;
import org.zerock.service.ReplyService;


// 댓글 요청 처리의 경우 정부 뷰를 만들지 않고 http body 바로 데이터를 담아 반환할 것이기 때문에 클래스 선언부에 @RestController 어노테이션을 추가
@RestController
@RequestMapping("/reply")
public class ReplyController {

	@Autowired
	private ReplyService replyService;
	
	/* 댓글 등록 */
	@PostMapping("/enroll")
	public void enrollReplyPOST(ReplyDTO dto) {
		replyService.enrollReply(dto);
	}	
	
}
