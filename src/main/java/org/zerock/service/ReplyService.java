package org.zerock.service;

import org.zerock.model.Criteria;
import org.zerock.model.ReplyDTO;
import org.zerock.model.ReplyPageDTO;

public interface ReplyService {

	/* 댓글 등록 */
	public int enrollReply(ReplyDTO dto);	
	
	/* 댓글 존재 체크 */
	public String checkReply(ReplyDTO dto);
	
	/* 댓글 페이징 */
	public ReplyPageDTO replyList(Criteria cri);
	
}
