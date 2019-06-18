<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>
<div class="box">
	<div class="box-header with-border">
		<c:if test="${msg == null}">
			<h3 class="box-title">게시판 목록보기</h3>
		</c:if>
		<c:if test="${msg != null}">
			<h3 class="box-title">${msg}</h3>
		</c:if>
	</div>

	<!-- 데이터 목록 개수를 설정하는 부분 -->
	<div class="box-header with-border">
		<form class="form-inline">
			<label for="count" class="col-sm-1 control-label"> 목록개수</label> <select
				id="count" class="form-control" style="width: 150px; height: 30px;">
				<option value="5"
					<c:out value="${map.pageMaker.cri.perPageNum==5?'selected':'' }"/>>
					5개씩 보기</option>
				<option value="10"
					<c:out value="${map.pageMaker.cri.perPageNum==10?'selected':'' }"/>>
					10개씩 보기</option>
				<option value="15"
					<c:out value="${map.pageMaker.cri.perPageNum==15?'selected':'' }"/>>
					15개씩 보기</option>
				<option value="20"
					<c:out value="${map.pageMaker.cri.perPageNum==20?'selected':'' }"/>>
					20개씩 보기</option>
			</select>
		</form>
	</div>

	<div class="box-body">
		<table class="table table-bordered table-hover">
			<tr>
				<th width="11%">글번호</th>
				<th width="46%">제목</th>

				<th width="16%">작성자</th>
				<th width="16%">작성일</th>
				<th width="11%">조회수</th>
			</tr>
			<c:forEach var="vo" items="${map.list }">
				<tr>
					<td align="right">${vo.bno}&nbsp;</td>
					<td>&nbsp;<a href="/board/detail/${vo.bno}">${vo.title}</a>&nbsp;
						<span class="badge bg-blue">${vo.replycnt }</span> <c:if
							test="${vo.replycnt>= 5 || vo.readcnt >= 100}">
							<img src="/resources/hot.png" width="50" height="50">
						</c:if></td>

					<td>&nbsp;${vo.nickname}</td>
					<td>&nbsp; ${vo.dispdate}</td>
					<td align="right"><span class="badge bg-blue">
							${vo.readcnt}</span>&nbsp;</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<!-- 페이지 번호 출력영역 -->
	<div class="box-footer">
		<div class="text-center">
			<!-- 부트스트랩에서 페이지 번호를 가로로 출력하기 위한 설정 -->
			<ul class="pagination">
				<!-- 데이터가 존재하는 경우만 페이지 번호는 출력 -->
				<c:if test="${map.pageMaker.totalCount != 0 }">
					<!-- 이전 출력 여부를 설정 -->
					<c:if test="${map.pageMaker.prev}">
						<li>
							<!-- 현재 출력된 시작 페이지 번호 - 1을 해서
						데이터를 요청하는 링크를 생성 --> <a
							href="list?page=${map.pageMaker.startPage-1} &perPageNum=${map.pageMaker.cri.perPageNum}
							&searchType = ${map.pageMaker.cri.searchType }
							&keyword = ${map.pageMaker.cri.keyword }
							">
								이전</a>
						</li>
					</c:if>
					<!-- 페이지 번호 출력
					시작 페이지 번호 부터 끝 페이지 번호까지 출력
					현재 페이지 번호는 활성화 시켜서 선택할 수 없도록 -->
					<c:forEach var="idx" begin="${map.pageMaker.startPage}"
						end="${map.pageMaker.endPage}">
						<li
							<c:out value= "${map.pageMaker.cri.page==idx?'class=active':''}"/>>
							<a
							href="list?page=${idx}&perPageNum=${map.pageMaker.cri.perPageNum}
							&searchType = ${map.pageMaker.cri.searchType }
							&keyword = ${map.pageMaker.cri.keyword }
							">${idx}
						</a>
						</li>
					</c:forEach>
					<!-- 다음 출력 여부를 설정
					마지막 페이지 번호 + 1 에 해당하는 링크를 설정 -->
					<c:if test="${map.pageMaker.next}">
						<li><a
							href="list?page=${map.pageMaker.endPage+1}&perPageNum=${map.pageMaker.cri.perPageNum}
							&searchType = ${map.pageMaker.cri.searchType }
							&keyword = ${map.pageMaker.cri.keyword }">
								다음 </a></li>
					</c:if>
				</c:if>
			</ul>
		</div>

		<div class="box-body text-center">
			<select name="searchType" id="searchType">
				<option value="n"
					<c:out value="${cri.searchType==null?'selected':''}"/>>--</option>

				<option value="t"
					<c:out value="${cri.searchType=='t'?'selected':''}"/>>제목만</option>

				<option value="c"
					<c:out value="${cri.searchType=='c'?'selected':''}"/>>내용만</option>

				<option value="w"
					<c:out value="${cri.searchType=='w'?'selected':''}"/>>작성자</option>

				<option value="tc"
					<c:out value="${cri.searchType=='tc'?'selected':''}"/>>제목
					또는 내용</option>

			</select> <input type="text" name="keyword" id="keyword"
				value="${cri.keyword}" />
			<button id="searchBtn" class="btn btn-success">검색</button>
		</div>




		<div class="box-footer">
			<div class="text-center">
				<button id='mainBtn' class="btn-primary">메인으로</button>
			</div>
			<script>
			document.getElementById('mainBtn').addEventListener("click",
					function(event) {
						location.href = "../";
					});
		</script>
		</div>
	</div>
	<%@include file="../include/footer.jsp"%>
	<script>
	//검색 버튼을 눌렀을 때 동작할 이벤트 핸들러 작성
 	document.getElementById("searchBtn").addEventListener(
 			"click", function(e){
 		//검색조건 가져오기
 		var searchType = document.getElementById("searchType").value
 		//검색어 가져오기
 		var keyword = document.getElementById("keyword").value
 		
 		//요청
 		location.href = '/board/list?' + 'page=${map.pageMaker.cri.page}&perPageNum=' + '${map.pageMaker.cri.perPageNum}' + '&searchType='
 				+ searchType + '&keyword=' + keyword
 		
 	});
	
	
	 	//목록 개수를 변경했을 때 호출될 메소드
	 	document.getElementById("count").addEventListener("change", function(e){
	 		
	 		//검색 조건 가져오기
	 		var searchType = document.getElementById("searchType").value
	 		
	 		//검색어 가져오기
	 		var keyword = document.getelementById("keyword").value
	 		
	 		//요청
	 		location.href = '/board/list?' + 'page=1&perPageNum=' + this.value + '&searchType=' + searchType + '&keyword=' + keyword
	 		
	 		//현재 페이지 번호는 1 데이터 개수는 선택한 데이터 개수로 설정해서
	 		//데이터를 다시 호출합니다.
	 		location.href = 'list?' + 'page=1&perPageNum=' + this.value
	 	});
	 </script>