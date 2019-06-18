package naver.isjy123.springboard.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import naver.isjy123.springboard.domain.Board;
import naver.isjy123.springboard.domain.SearchCriteria;

public interface BoardService {
	//게시글 작성을 위한 메소드
	public int register(HttpServletRequest request);
	
	//게시글 전체 목록 가져오는 메소드
	public List<Board> list();
	//글번호를 가지고 하나의 게시글을 가져오는 메소드 
	public Board detail(int bno);
	//게시글 수정보기 처리를 위한 메소드
	public Board updateView(int bno);
	//게시글 수정 처리를 위한 메소드
	public void update(HttpServletRequest request);
	//글번호를 가지고 게시글을 삭제하는 메소드 
	public int delete(int bno);
	//페이지 번호에 해당하는 데이터 목록을 가져오는 메소드
	//데이터 목록 외에도 다른 데이터를 리턴해야 하기 때문에 리턴타입을 변경
	public Map<String, Object> list(SearchCriteria criteria);
}
