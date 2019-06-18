package naver.isjy123.springboard.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import naver.isjy123.springboard.criteria.Criteria;
import naver.isjy123.springboard.domain.Board;
import naver.isjy123.springboard.domain.SearchCriteria;

@Repository
public class BoardDao {
	@Autowired
	private SqlSession sqlSession;

	// 게시글 작성을 위한 메소드
	public int register(Board board) {
		return sqlSession.insert("board.register", board);
	}

	// 게시글 전체를 가져오는 메소드
	public List<Board> list() {
		return sqlSession.selectList("board.list");
	}

	public Board detail(int bno) {
		return sqlSession.selectOne("board.detail", bno);
	}

	public int updateReadcnt(int bno) {
		return sqlSession.update("board.updateReadcnt", bno);
	}
	
	public void update(Board vo) { 
		sqlSession.update("board.update", vo);
	}
	
	public int delete(int bno) { 
		return sqlSession.delete("board.delete", bno);
	}
	//글번호에  해당하는 댓글 개수를 가져오는 메소드
	public int replycnt(int bno) {
		return sqlSession.selectOne("board.replycnt", bno);
	}
	
	//전체 데이터 개수를 가져오는 메소드 - 페이징 구현
	public int totalCount(SearchCriteria criteria) {
		return sqlSession.selectOne("board.totalCount", criteria);
	}
	
	//페이지 번호에 해당하는 데이터 목록을 가져오는 메소드
	public List<Board> list(SearchCriteria cri){
		return sqlSession.selectList("board.list", cri);
	}
	
}
