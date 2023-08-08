<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="menu d-flex">
	<nav class="w-75">
		<ul class="nav d-flex justify-content-between">
			<!-- nav, nav-item, nav-link 3종 세트 -->
			<c:if test="${not empty userName}">
				<li class="nav-item"><a href="#" class="nav-link">경기장 예약</a></li>
				<li class="nav-item"><a href="#" class="nav-link">매칭 등록</a></li>
				<li class="nav-item"><a href="#" class="nav-link">매칭 목록</a></li>
				<li class="nav-item"><a href="/team/team_create_view" class="nav-link">팀 만들기</a></li>
				<li class="nav-item"><a href="#" class="nav-link">팀 가입</a></li>
				<li class="nav-item"><a href="#" class="nav-link">마이페이지</a></li>
			</c:if>
		</ul>
	</nav>
	<!-- 로그인 사용자 + login/logout-->
	<div class="w-25 d-flex justify-content-end align-items-center">
		<c:if test="${not empty userName}">
			<small class="mr-5">
				<b class="text-info">${userName}</b>님 안녕하세요
			</small>
			<small><a href="/user/sign_out" class="loginBtn mr-5">LOGOUT</a></small>
		</c:if>
		<c:if test="${empty userName}">
			<a href="/user/sign_in_view" class="loginBtn mr-5">LOGIN</a>
		</c:if>
	</div>
</div>