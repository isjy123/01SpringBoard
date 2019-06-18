package naver.isjy123.springboard.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

//자동으로 bean을 생성하기 위한 어노테이션
@Component
public class AuthInterceptor implements HandlerInterceptor {
	//요청을 처ㅣ하기전에 호출되는 메소드
	public boolean preHandle(
			HttpServletRequest request,
			HttpServletResponse response,
			Object handler) throws Exception{
		//로그인 여부 확인
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null) {
			//공통된 부분을 제외한 요청 가져오기
			String uri = request.getRequestURI();
			String contextPath = request.getContextPath();
			String requestUri = uri.substring(contextPath.length()+1);
			
			//파라미터 가져오기
			String query = request.getQueryString();
			if(query == null || query.equals("null")) {
				query = "";
			}else {
				query = "?" + query;
			}
			//세션에 요청주소 제공
			//로그인 되면 dest로 이동하기 위해서
			session.setAttribute("dest", requestUri + query);
			session.setAttribute("loginmsg", "로그인 해야 이용할 수 있는 서비스입니다.");
			response.sendRedirect("/user/login");
			return false;
		}
		
		
		//true를 리턴하면 요청을 처리하는 Controller 에게 넘어감
		return true;
	}
}
