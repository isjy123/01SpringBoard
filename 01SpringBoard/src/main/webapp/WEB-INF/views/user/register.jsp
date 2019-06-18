<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>
<section class="content">

	<!-- 회원가입 폼 : 유효성 검사를 위해서 id를 부여-->
	<!-- enctype은 file을 전송할 때 사용, 최근에는 form의 전송방식은 거의 post -->
	<form id="registerform" enctype="multipart/form-data" method="post">
		<p align="center">
		<table border="1" width="50%" height="80%" align='center'>
			<tr>
				<td colspan="3" align="center"><h2>회원 가입</h2></td>
			</tr>
			<tr>
				<!-- rowspan은 행을 합치는 것 -->
				<td rowspan="5" align="center">
					<p></p> <img id="img" width="100" height="100" border="1" /> <br />
					<br /> <input type='file' id="image" name="image"
					accept=".jpg, .jpeg, .gif, .png" /><br />
				</td>
			</tr>

			<tr>
				<td bgcolor="#f5f5f5"><font size="2">&nbsp;&nbsp;&nbsp;&nbsp;이메일</font></td>
				<td>&nbsp;&nbsp;&nbsp; <!--HTML5에서 input의 type을 추가된 셩태로 설정하면 형식 검사도 수행해 줌 -->
					<input type="email" name="email" id="email" size="30" maxlength=50
					required="required" />
					<div id="emailDiv"></div>
				</td>

			</tr>
			<tr>
				<td bgcolor="#f5f5f5"><font size="2">&nbsp;&nbsp;&nbsp;&nbsp;비밀번호</font></td>
				<td>&nbsp;&nbsp;&nbsp; <input type="password" name="pw" id="pw"
					size="20" required="required" />
				</td>
			</tr>
			<tr>
				<td bgcolor="#f5f5f5"><font size="2">&nbsp;&nbsp;&nbsp;&nbsp;비밀번호
						확인</font></td>
				<td>&nbsp;&nbsp;&nbsp; <input type="password" id="pwconfirm"
					size="20" required="required" />
					<div id="pwDiv"></div>
				</td>
			</tr>
			<tr>
				<td width="17%" bgcolor="#f5f5f5"><font size="2">&nbsp;&nbsp;&nbsp;&nbsp;닉네임</font></td>
				<td>&nbsp;&nbsp;&nbsp; <!-- pattern은 정규식 패턴을 설정해서 유효성 검사 수행, title은 유효성 검사에 실패했을 때 보여지는 텍스트인데 브라우저에 잘 적용 안됨 -->
					<input type="text" name="nickname" id="nickname" size="20"
					pattern="([a-z, A-Z, 가-힣]){2,}" required="required"
					title="닉네임은 문자 2자 이상입니다." />
					<div id="nicknameDiv"></div>
				</td>

			</tr>
			<tr>
				<td align="center" colspan="3">
					<p></p> <input type="submit" value="회원가입" class="btn btn-warning" />
					<!-- 인라인 이벤트 처리 방식으로 클릭이벤트 처리, 간단한 구문에는 유용하지만 태그 안에 스크립트 코드가 있어서 가독성이 떨어짐
					최근에는 비추천 --> <input type="button" value="메인으로"
					class="btn btn-success" onclick="javascript:window.location='/'">
					<p></p>
				</td>
			</tr>
		</table>
	</form>
	<br /> <br />
</section>
<%@include file="../include/footer.jsp"%>

<script>

	document.getElementById("image").addEventListener("change", function(e){
		//파일 선택 여부 확인
		//프로그래밍 언어들 중에는 데이터가 없으면 false를 리턴하기도 함
		if(this.files && this.files([0])){
			//파일 이름 가져오기
			var filename = this.files[0].name;
			//파일의 내용을 읽는 것은 비동기적을 동작
			reader.readAsDataURL(this.files[0])
			//파일 읽는게 동작이 종료되면 처리
			reader.addEventListner("load", function(e){
				//읽은 내용을 img 태그에 출력
				document.getElementById("img").src = e.target.result;
			})
			
		}
		
	})
	//이메일 중복검사 여부를 토과했는지를 저장할 변수 생성
	var emailcheck= false;
	//email 입력란에서 포커스가 떠나면
	document.getElementById("email").addEventListener("focusout", function(e){
		var email = document.getElementById("email").value;
		if(email.trim().length >0){
			$.ajax({
				url:'emailcheck',
				data:{
					'email':email
				},
				dataType:'json',
				sucess:function(data){
					var emaildiv = document.getElementById("emailDiv")
					if(data.result==true){
						emaildiv.innerHTML = '사용가능한 이메일 임'
						emaildiv.style.color = 'green';
						emailcheck = true
					}else{
						emaildiv.innerHTML = '이미 사용중인 이메일 임'
						emaildiv.style.color = 'red';
							emailcheck = false
					}
						
				}
			});
		}
		
	});
	
	//닉네임 중복체크 여부를 저장할 변수 선언
	var nicknamecheck = false;
	
	var nickname =document.getElementById("nickname");
	var nicknamediv = document.getElementById("nicknameDiv");
	
	nickname.addEventListener("focusout", function(e){
		var nickname = document.getElementById("nickname").value;
		$.ajax({
			uri:"nicknamecheck",
			data:{
				"nickname":nickname
			},
			dataType='json',
			success:function(data){
				if(data.result == true){
					nicknamediv.innerHTML = "사용가능한 닉";
					nicknamediv.style.color = 'green';
					nicknamecheck = true
				}else{
					nicknamediv.innerHTML="이미 사용중인 닉";
					nicknamediv.style.color = 'red';
					nicknamecheck = false
				}
			}
		});
	});
	
	document.getElementById("registerform").addEventListener("submit",function(e){
			if(idcheck == false){
				document.getElementById("emailDiv").innerHTML = "이메일 중복검사를 수행하세요!!";
				document.getElementById("emailDiv").style.color='red';
				document.getElementById("email").focus();
				e.preventDefault();
		}
			if(nicknamecheck == false){
				document.getElementById("nicknameDiv").innerHTML = "닉네임 중복검사를 수행하세요!!";
				document.getElementById("nicknameDiv").style.color='red';
				document.getElementById("nickname").focus();
				e.preventDefault();
		}
			var pw = document.getElementById("pw").value;
			var pwconfirm = document.getElementById("pwconfirm").value;
			if(pw != pwconfirm){
				document.getElementById("pwDiv").innerHTML = "2개의 비밀번호가 다릅니다!!";
				document.getElementById("pwDiv").style.color='red';
				document.getElementById("pw").focus();
				e.preventDefault();
		}
			var pattern1 = /[0-9]/; // 숫자 var
			pattern2 = /[a-zA-Z]/; // 문자 var
			pattern3 = /[~!@#$%^&*()_+|<>?:{}]/;// 특수문자
			
			if(!pattern1.test(pw) || !pattern2.test(pw) || !pattern3.test(pw) || pw.length < 8) {
				document.getElementById("pwDiv").innerHTML = "비밀번호는 8자리 이상 문자, 숫자, 특수문자로 구성하여야 합니다.";
				document.getElementById("pw").focus();
				e.preventDefault();
		}
	})

</script>