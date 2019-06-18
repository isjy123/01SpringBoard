<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 다른 파일 내용을 가져오기 -->
<%@ include file="include/header.jsp"%>
<section class="content">
	<div class="box">
		<div class="box-header with-border" id="addressdisp">
		</div>
	
		<c:if test="${user==null }">
		<div class="box-header with-border">
			<a href="user/login"><h3 class="box-title">로그인</h3></a>
		</div>
		</c:if>
		
		<c:if test="${user!=null }">
		<div class="box-header with-border">
			<a href="user/logout"><h3 class="box-title">로그아웃</h3></a>
		</div>
			<div class="box-header with-border">
				<a href="board/register"><h3 class="box-title">글쓰기</h3></a>
			</div>
			<div class="box-header with-border">
				<a href="board/list"><h3 class="box-title">게시판 목록보기</h3></a>
			</div>
		</c:if>
		
		<div class="box-header with-border">
			<a href="user/register"><h3 class="box-title">회원가입</h3></a>
		</div>
	</div>
</section>
<c:if test="${insert != null }">
	<link rel="stylesheet"
		href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script>
		$(function() {
			$("#dialog-confirm").dialog({
				resizable : false,
				height : "auto",
				width : 400,
				modal : true,
				buttons : {
					"닫기" : function() {
						$(this).dialog("close");
					},
				}
			});
		});
	</script>
</c:if>
<div id="dialog-confirm" title="회원가입" style="display: none">
	<p>
		<span class="ui-icon ui-icon-alert" style="float: left; margin: 12px 12px 20px 0;"></span> 
			회원가입에 성공하셨습니다.이제 로그인하고 사용하시면 됩니다.
	</p>
</div>



<%@ include file="include/footer.jsp"%>

<script>
	//10초를 주기로 동작 수행하는 타이머 생성
	setInterval(function(){
		//현재 위치 정보를 가져오기 위한 HTML5 API
		navigator.geolocation.getCurrentPosition(function(position){
			//위치정보 가져오기
			var coords = position.coords;
			var papram = coords.latitude + ":" + coords.longitude;
			//jquery에서 ajax 요청
			$.ajax({
				uri:"address",
				data:{param:"param"},
				dataType:"json",
				success:function(data){
					document.getElementById("addressdisp").innerHTML = "현재위치" + data.address
				}
			})
			
		});
		alert("10초마다 대화상자 출력")
	}, 600000);
</script>