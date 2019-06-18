package naver.isjy123.springboard;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import naver.isjy123.springboard.domain.User;
import naver.isjy123.springboard.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "user/register", method = RequestMethod.GET)
	public void register(Model model) {
	}

	@RequestMapping(value = "user/register", method = RequestMethod.POST)
	public String registerPost(MultipartHttpServletRequest request, RedirectAttributes rttr) {
		int result = userService.register(request);
		System.out.print("result:" + result);
		if (result > 0) {
			rttr.addFlashAttribute("insert", "success");
			return "redirect:/";
		} else {
			return "redirect:user/register";
		}
	}
	
	@RequestMapping(value="user/login", method=RequestMethod.GET)
	public String login(Model model) {
		return "user/login";
	}
	
	//login.jsp 에서 login요청을 했을 때 처리하는 메소드
	@RequestMapping(value="user/login", method=RequestMethod.POST)
	public String login(HttpServletRequest request, Model model, HttpSession session, RedirectAttributes attr) {
		User user = userService.login(request);
		//로그인 실패한 경우
		if(user==null) {
			attr.addFlashAttribute("msg","없는 이메일이거나 잘못된 비밀번호");
			return "redirect:login";
		}else {
			//로그인 성공했을 때는 로그인 정보를 세션에 저장
			session.setAttribute("user", user);
			if(session.getAttribute("dest") == null) {
				return "redirect:/";
			}else {
				return "redirect:/" + session.getAttribute("dest").toString();
			}
		}
	}
	
	//home.jsp 에서 logout 요청했을 대 처리 메소드
	@RequestMapping(value="user/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		//세션 초기화
		session.invalidate(); // session.removeAttribute("user");
		return "redirect:/";
	}
	

}
