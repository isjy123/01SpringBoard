package naver.isjy123.springboard.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import naver.isjy123.springboard.domain.Reply;

@Repository
public class ReplyDao {
	@Autowired
	private SqlSession sqlSession;
	
	//댓글 저장을 위한 메소드
	public int register(Reply reply) {
		System.out.println("DAO");
		int result = sqlSession.insert("reply.register", reply);
		
		System.out.println("DAO 결과 : " + result);
		return result;
	}
	
	//글 번호에 해당하는 댓글 목록을 가져오는 메소드
	public List<Reply> list(int bno){
		return sqlSession.selectList("reply.list", bno);
	}
	
	//댓글 번호에 해당하는 댓글을 삭제하는 메소드 
	public int delete(int rno) {
		return sqlSession.delete("reply.delete", rno);
	}
	//댓글 수정을 위한 메소드 생성
	public int update(Reply reply) {
		return sqlSession.delete("reply.update", reply);
	}
}
