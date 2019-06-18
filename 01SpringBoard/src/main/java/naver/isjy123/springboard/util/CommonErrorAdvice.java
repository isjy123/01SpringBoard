package naver.isjy123.springboard.util;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Component
//설정된 패키지에서 예외가 발생하면 동작하도록 해주는 설정
@ControllerAdvice("naver.isjy123.springboard")
public class CommonErrorAdvice {
	//예외처리 메소드
	@ExceptionHandler(Exception.class)
	//Controller처리 방법과 유사
	public ModelAndView errorHandling(Exception ex) {
		ModelAndView mav = new ModelAndView();
		//에러페이지 이름을 설정 - ViewResolcer를 적용
		mav.setViewName("error/error");
		//전달 데이터 설정
		mav.addObject("exception", ex);
		return mav;
		
	}
}
