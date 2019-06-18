package naver.isjy123.springboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import naver.isjy123.springboard.domain.Reply;
import naver.isjy123.springboard.service.ReplyService;
import naver.isjy123.springboard.service.UserService;
@RestController
public class JsonController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private ReplyService replyService;
	
	@RequestMapping(value="/reply/update", method=RequestMethod.GET)
	public Map<String, Object> update(Reply reply){
		Map<String, Object> map = new HashMap<String,Object>();
			
		int result = replyService.update(reply);
		if(result >= 0)
			map.put("result", true);
		else
			map.put("result", false);
			
		return map;
	}
	
	
	
	//댓글 번호에 해당하는 댓글을 삭제하는 메소드
	@RequestMapping(value="/reply/delete", method = RequestMethod.GET)  //성공과 실패여부를 알려줄때는 Map
	public Map<String, Object> delete(@RequestParam("rno") int rno){
		
		
		int result = replyService.delete(rno);
		Map <String, Object> map = new HashMap<String, Object>();
		map.put("result", result>0);
		
		return map;
	}
	
	//댓글 작성을 위한 메소드
	@RequestMapping(value="/reply/register", method = RequestMethod.GET)
	public Map<String, Object> replyRegister(HttpServletRequest request){
		int result = replyService.register(request);
		Map<String, Object> map = new HashMap<String,Object>();
		if(result > 0) {
			map.put("result", true);
		}else {
			map.put("result", false);
		}
			
		return map;
	}
	
	//이메일 중복검사 요청을 처리할 메소드
	@RequestMapping(value="user/emailcheck", method=RequestMethod.GET)
	public Map<String, Object> emailcheck(@RequestParam("email") String email){
		
		//서비스 메소드 호출
		String result = userService.emailcheck(email);
		//출력할 데이터 만들기
		Map<String, Object> map = new HashMap<String, Object>();
		//중복된 이메일 없으면 result 라는 키에 true를 저장하고 중복된 이메일이면 result라는 키에 false가 저장
		
		map.put("result",result == null);
		return map;
	}
	@RequestMapping(value="user/nicknamecheck", method=RequestMethod.GET)
	public Map<String, Object> nicknamecheck(@RequestParam("nickname") String nickname){
		
		//서비스 메소드 호출
		String result = userService.nicknamecheck(nickname);
		//출력할 데이터 만들기
		
		Map<String, Object> map = new HashMap<String, Object>();
		//중복된 이메일 없으면 result 라는 키에 true를 저장하고 중복된 이메일이면 result라는 키에 false가 저장
		
		map.put("result",result == null);
		
		return map;
	}
	@RequestMapping(value="address", method=RequestMethod.GET)
	public Map<String, String> address(@RequestParam("param") String param){
		Map<String, String> map = new HashMap<>();
		String address = userService.convertAddress(param);
		map.put("adrress", address);
		
		return map;
	}
	
	//글번호 //기본형은이면 map을 만들어서, 아니면 그냥 리턴해도 됨
	@RequestMapping(value="/reply/list", method=RequestMethod.GET)
	public List<Reply> replylist(@RequestParam("bno") int bno) {
		return replyService.list(bno);
	}
	

	
}
