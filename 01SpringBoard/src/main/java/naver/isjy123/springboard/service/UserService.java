package naver.isjy123.springboard.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import naver.isjy123.springboard.domain.Board;
import naver.isjy123.springboard.domain.User;

public interface UserService {
	//email 중복체크 메소드
	public String emailcheck(String email);
	//nickname 중복체크 메소드
	public String nicknamecheck(String nickname);
	//회원가입 처리 메소드
	public int register(MultipartHttpServletRequest request);
	//로그인 처리를 위한 메소드
	public User login(HttpServletRequest request);
	
	//위도와 경도 문자열을 받아서 주소를 리턴하는 메소드
	public String convertAddress(String param);

	
}
