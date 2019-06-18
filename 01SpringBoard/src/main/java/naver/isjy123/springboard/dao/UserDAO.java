package naver.isjy123.springboard.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import naver.isjy123.springboard.domain.User;

@Repository
public class UserDAO {
	@Autowired
	private SqlSession sqlSession;
	
	//email 중복체크를 수행해주는 메소드
	public String emailcheck(String email) {
		return sqlSession.selectOne("user.emailcheck" + "", email);
	}
	
	//nickname 중복체크를 수행해주는 메소드
	public String nicknamecheck(String nickname) {
		return sqlSession.selectOne("user.nicknamecheck", nickname);
	}
	//회원가입을 처리해 줄 메소드 생성
	public int register(User user) {
		return sqlSession.insert("user.register", user);
	}
	//로그인을 처리해 줄 메소드 생성
	public User login(String email) {
		return sqlSession.selectOne("user.login",email);
	}
}

