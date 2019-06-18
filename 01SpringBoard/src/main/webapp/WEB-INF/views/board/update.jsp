<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<section class="center">
	<div class="box box-primary">
		<div class="box-header">
			<h3 class="box-title">게시물 수정</h3>
		</div>
		<form role="form" id="updateForm" action="/board/update" method="post">
			<!-- 글번호를 넘겨주기 위해서 숨김 객체로 생성 -->
			<input type="hidden" name="bno" value="${vo.bno}" />
			<div class="box-body">
				<div class="form-group">
					<label>제목</label> <input type="text" name="title"
						class="form-control" value="${vo.title}" />
				</div>
				<div class="form-group">
					<label>내용</label>
					<textarea name="content" class="form-control" placeholder="내용 입력"
						rows="5">${vo.content}</textarea>
				</div>
				<div class="form-group">
					<label>작성자</label> <input type="text" class="form-control"
						value="${vo.nickname}" readonly="readonly" />
				</div>
			</div>
		</form>
		<div class="box-footer">
			<button class="btn btn-success" id="updatebtn">수정완료</button>
			<button class="btn btn-warning" id="mainbtn">메인으로</button>
			<button class="btn btn-primary" id="listbtn">목록보기</button>
		</div>
	</div>
</section>
<%@ include file="../include/footer.jsp"%>

<script>
	//메인 버튼을 눌렀을 때 처리 
	document.getElementById("mainbtn").addEventListener("click",function() { location.href = "/";});
	
	//목록 버튼을 눌렀을 때 처리 
	document.getElementById("listbtn").addEventListener("click", function() {location.href = "/board/list";});
	
	//수정완료버튼을눌렀을때처리 
	document.getElementById("updatebtn").addEventListener("click", function() { document.getElementById("updateForm").submit();}); 
</script>