<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- jstl 의 core 기능을 사용하기 위한 설정 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring MVC Board</title>
<!-- 모바일에서 접속했을 때 화면의 너비를 디바이스의 크기만큼으로 출력하기 위한 설정 -->
<meta
	content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
	name='viewport'>
<!-- 부트스트랩을 사용하기 위한 설정 -->
<link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />

<!-- IE9이하의 버전에서 접속했을 때 HTML5의 semantic 태그를 인식하도록 하는 설정, 조금 이후에는 설정을 안해도 됨 -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.comrespond/1.4.2respond.min.js"></script>
<![endif ]-->


</head>

<script src="/resources/jquery/jquery.min.js"></script>

<body class="skin-blue sidebar-mini">
	<div class="wrapper">
		<header class="main-header">
			<div class="page-header">
			
				<h1>Spring MVC 게시판</h1>
			</div>
		</header>
	</div>
	<aside class="main-sidebar">
		<section class="sidebar">
			<ul class="nav nav-tabs">
				<li role="presentation" class="active"><a href="/">메인</a></li>
				<li role="presentation"><a href="/board/list">목록보기 </a></li>
				
				<c:if test="${user==null }">
					<li role="presentation"><a href="/user/register">회원가입 </a></li>
				</c:if>
				
				<c:if test="${user!=null }">
				
					<li role="presentation"><a href="/board/register">게시물 쓰기 </a></li>
					<li role="presentation">
						<span class = "badge">
							<img src="/userimage/${user.image }" width= 20 height = 20/>
						</span>
						${user.nickname }
					</li>
					
				</c:if>
			</ul>
		</section>
	</aside>
