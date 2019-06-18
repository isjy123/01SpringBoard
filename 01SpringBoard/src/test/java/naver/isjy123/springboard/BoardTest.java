package naver.isjy123.springboard;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import naver.isjy123.springboard.service.UserService;

//Spring 테스트 클래스 만들어주는 어노테이션 설정
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class BoardTest {
	//테스트할 주입 객체 생성
	@Autowired
	private DataSource dataSource;
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private UserService userService;
	
	@Test
	public void emailcheck() {
		System.out.println(userService.emailcheck("isjy123@naver.com"));
		System.out.println(userService.emailcheck("isjy123@naver.com"));
		
	}
	
	//테스트할 메소드
	@Test
	public void conTest() {
		try(Connection con= dataSource.getConnection()){
			System.out.println("con" + con);
		}catch(Exception e) {
			System.out.println("예외:"+e.getMessage());
			e.getStackTrace();
		}
	}
	//테스트 할 메소드
	@Test
	public void mybatisTest() {
		System.out.println("mybatis : " + sqlSession);
	}
}
