package naver.isjy123.springboard.domain.pagemaker;


import naver.isjy123.springboard.domain.SearchCriteria;

public class PageMaker {
	// 전체 데이터개수를 저장할 변수
	private int totalCount;

	// 페이지 번호의 시작과 종료를 저장할 변수
	private int startPage, endPage;
	// 이전과 다음 버튼의 존재 여부를 저장할 변수

	private boolean prev, next;
	// 하나의 페이지에 보여질 페이지 번호 개수를 저장할 변수
	
	private int displayPageNum = 10;
	// 이전에 설정한 데이터들을 저장하기 위한 변수

	private SearchCriteria cri;

	public int getTotalCount() {

		return totalCount;

	}

	public void setTotalCount(int totalCount) {

		this.totalCount = totalCount;
		// 전체 데이터 개수가 확정되면 나머지를 계산하도록 메소드를 호출
		calcData();
	}

	// 연산을 해서 set이 가능한 항목을 연산할 메소드

	private void calcData() {

		// 종료 페이지 번호 계산
		endPage =(int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);
		
		// 시작 페이지 번호 계산
		startPage = endPage - displayPageNum + 1;

		// 전체 페이지 개수 구하기
		int totalPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));

		// end 페이지 수정
		if (endPage > totalPage) {
			endPage = totalPage;
		}

		// 이전과 다음 출력 여부를 설정
		prev = startPage == 1 ? false : true;
		next = endPage == totalPage ? false : true;
	}

	public int getStartPage() {

		return startPage;

	}

	public void setStartPage(int startPage) {

		this.startPage = startPage;

	}

	public int getEndPage() {

		return endPage;

	}

	public void setEndPage(int endPage) {

		this.endPage = endPage;

	}

	public boolean isPrev() {

		return prev;

	}

	public void setPrev(boolean prev) {

		this.prev = prev;

	}

	public boolean isNext() {

		return next;

	}

	public void setNext(boolean next) {

		this.next = next;

	}

	public int getDisplayPageNum() {

		return displayPageNum;

	}

	public void setDisplayPageNum(int displayPageNum) {

		this.displayPageNum = displayPageNum;

	}

	public SearchCriteria getCri() {
		return cri;
	}

	public void setCri(SearchCriteria cri) {
		this.cri = cri;
	}
	


}
