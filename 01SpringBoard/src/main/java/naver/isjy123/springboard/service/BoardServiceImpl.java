package naver.isjy123.springboard.service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import naver.isjy123.springboard.dao.BoardDao;
import naver.isjy123.springboard.domain.Board;
import naver.isjy123.springboard.domain.SearchCriteria;
import naver.isjy123.springboard.domain.User;
import naver.isjy123.springboard.domain.pagemaker.PageMaker;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao boardDao;
	
	
	@Override
	@Transactional
	public int register(HttpServletRequest request) {
		
		int result = -1;
		//파라미터 읽기
		//request.setCharacterEncoding("utf-8");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		//ip주소 찾아오기
		String ip = request.getRemoteAddr();
		
		//로그인한 유저의 email 찾아오기
		User user = (User)request.getSession().getAttribute("user");
		String email = user.getEmail();
		
		//Dao 와 파라미터 만들기
		Board board = new Board();
		if(title.trim().length() == 0) {
			title="제목없음";
		}
		board.setTitle(title);
		if(content.trim().length() == 0) {
			content="내용없음";
		}
		board.setContent(content);
		board.setIp(ip);
		board.setEmail(email);
		
		//Dao메소드 호출
		result = boardDao.register(board);
		
		return result;
	}

	@Override
	@Transactional
	public List<Board> list() {
		
		
		List<Board> list = boardDao.list();
		//최근의 게시판에서는 현재날짜와 동일한 날자에 작성된 게시글들은 시간 출력, 이전 날자에 작성된 것은 날짜를 출력한다
		//Board 객체의 dispdate 항목에 위처럼 저장
		
		//오늘 날짜 가져오기
		Calendar cal = new GregorianCalendar();
		java.sql.Date today = new java.sql.Date(cal.getTimeInMillis());
		for(Board board : list) {
			String regdate = board.getRegdate().toString();
			if(today.toString().contentEquals(regdate.substring(0,10))) {
				board.setDispdate(regdate.substring(11, 16));
			}else {
				board.setDispdate(regdate.substring(5, 10));
			}
			//글번호를 가지고 댓글 개수를 가져와서 저장
			board.setReplycnt(boardDao.replycnt(board.getBno()));
		}
		
		return list;
	}

	@Override
	public Board detail(int bno) {
		Board board = null;
		//조회수 1 증가
		int result = boardDao.updateReadcnt(bno);
		//1 증가에 성공했으면 데이터 가져오기
		if(result>=0) {
			board = boardDao.detail(bno);
			board.setTitle(board.getTitle().trim());
		}
		//댓글 개수를 저장
		board.setReplycnt(boardDao.replycnt(bno));
		return board;
	}

	@Override
	public Board updateView(int bno) { 
		return boardDao.detail(bno);
	}

	@Override
	public void update(HttpServletRequest request) {
		
		// 매개변수가 request일 때는 파라미터를 읽습니다. 
		int bno = Integer.parseInt(request.getParameter("bno")); String title = request.getParameter("title");
		String content = request.getParameter("content");
		if (title.length() == 0) { 
			title = "무제";
		}
		if (content.length() == 0) { 
			content = "냉무"; 
		}
		// 필요한 데이터를 생성
		String ip = request.getRemoteAddr(); Board vo = new Board(); vo.setBno(bno);
		vo.setTitle(title); vo.setContent(content);
		vo.setIp(ip);
		boardDao.update(vo);
		
	}

	@Override
	public int delete(int bno) {
		int result = boardDao.delete(bno);
		return result;
	}

	@Override
	public Map<String, Object> list(SearchCriteria criteria) {
		
		Map <String, Object> map = new HashMap<String, Object>();
		//페이지 번호에 해당하는 데이터 목록 가져오기
		List<Board> list = boardDao.list(criteria);
		
		if(list.size() == 0) {
			// 데이터가 하나도 없으면 페이지 번호를 출력하지 않기 위해서 페이지번호 하나 감소시켜 데이터를 가져옴
			criteria.setPage(criteria.getPage()-1);
			list = boardDao.list(criteria);
		}
		
		//날짜 출력부분 설정
		//오늘 날짜가져오기 - 문자열로 만들기
		Calendar cal = new GregorianCalendar();
		java.sql.Date today = new java.sql.Date(cal.getTimeInMillis());
		
		//오늘 작성된 글은 dispdate에 시간과 분을, 그게아니면 월과 일을 저장
		for(Board board: list) {
			String regdate = board.getRegdate().toString();
			board.setTitle(board.getTitle().trim());
			if(today.toString().equals(regdate.substring(0,10))) {
				board.setDispdate(regdate.substring(11,16));
			}else {
				board.setDispdate(regdate.substring(5,10));
			}
		}
		
		//데이터 목록 저장
		map.put("list", list);
		
		//출력할 페이지 번호 연산
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(criteria);
		pageMaker.setTotalCount(boardDao.totalCount(criteria));
		map.put("pageMaker", pageMaker);
		
		return map;
		
	}
	

}
