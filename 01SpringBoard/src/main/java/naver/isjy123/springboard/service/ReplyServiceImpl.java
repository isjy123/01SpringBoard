package naver.isjy123.springboard.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import naver.isjy123.springboard.dao.ReplyDao;
import naver.isjy123.springboard.domain.Reply;
import naver.isjy123.springboard.domain.User;
@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	private ReplyDao replyDao;

	@Override
	public int register(HttpServletRequest request) {
		
		
		
		System.out.println("서비스");
		
		int result = -1;
		//파라미터를 읽어오기
		String replyText = request.getParameter("replyText");
		int bno = Integer.parseInt(request.getParameter("bno"));
		//접속중인 유저의 email과 nickname 가져오기
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String email = user.getEmail();
		String nickname = user.getNickname();
		
		//DAO 매개변수 만들기
		Reply reply = new Reply();
		reply.setBno(bno);
		reply.setEmail(email);
		reply.setNickname(nickname);
		reply.setReplytext(replyText);
		
		System.out.println("reply : " + reply);
		
		result = replyDao.register(reply);
		
		return result;
		
	}

	@Override
	public List<Reply> list(@RequestParam("bno") int bno) {
		
		return replyDao.list(bno);
	}

	@Override
	public int delete(int rno) {
		
		return replyDao.delete(rno);
	}

	@Override
	public int update(Reply reply) {
		return replyDao.update(reply);
		
	}

}
