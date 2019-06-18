package naver.isjy123.springboard.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import naver.isjy123.springboard.domain.Reply;

public interface ReplyService {
	//댓글저장을 위한 메소드
	public int register(HttpServletRequest request);
	
	//글번호에 해당하는 댓글 목록을 가져오는 메소드
	public List<Reply> list(int bno);
	
	//댓글 번호에 해당하는 댓글을 삭제하는 메소드
	public int delete(int rno);
	
	//댓글 수정을 위한 메소드
	public int update(Reply reply);
}
