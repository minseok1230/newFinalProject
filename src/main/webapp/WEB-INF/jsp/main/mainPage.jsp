<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div>
	<div class="d-flex justify-content-center">
		<img src="/static/image/main/mainPage_banner01.jpg" width="900" alt="메인화면 배너">
	</div>
	<br><br><br>
	<div class="d-flex justify-content-center">
		<img src="/static/image/main/football_image.webp" width="100" alt="메인화면 배너">
	</div>
	
	<div class="font-fantasy text-center">SOCCER MATCHING</div>
	<h4 class="text-center font-weight-bold">축구하고 싶을때 간편하게 매칭!</h4>
	<br><br><br>
	
	<div class="d-flex justify-content-between">
		<h2 class="text-center">축구 · 풋살 <br>매칭하고 싶을때!</h2>
		<h2 class="text-center">축구 · 풋살팀 <br>만들고 싶을때!</h2>
		<h2 class="text-center">축구 · 풋살팀 <br>가입하고 싶을때!</h2>
	</div>
</div>

<br><br><br><br>

<!-- 공지사항 -->
	<div>
		<div class="d-flex justify-content-between">
			<h4 class="font-weight-bold">공지사항</h4>
			<span class="mr-3 mt-3"><a href="/board/board_list_view?type=공지사항">더보기></a></span>
		</div><hr>
		
		<!-- 공지사항 목록 3개 가져오기  -->
		<c:forEach items="${noticeList}" var="notice">
			<div>
				<h5>${notice.content}</h5>
				<%-- <%--ZonedDateTime -> Date -> String 두번의 형변환 필요 --%>
				<fmt:parseDate value="${notice.createdAt}" pattern="yyyy-MM-d'T'HH:mm:ss" var="parsedCreatedAt"/>
				<fmt:formatDate value="${parsedCreatedAt}" pattern="yyyy년 M월 d일 HH:mm:ss(E)"/> 
			</div><hr>
		</c:forEach>
	</div>
	<br><br>
	<hr style="background:black; height:1px;">
	<br><br>
<!-- 게시판 -->
	<div>
		<div class="d-flex justify-content-between">
			<h4 class="font-weight-bold">게시판</h4>
			<span class="mr-3 mt-3"><a href="/board/board_list_view?type=게시판">더보기></a></span>
		</div><hr>
		<!-- 게시판 목록 3개 가져오기  -->
		<c:forEach items="${boardList}" var="board">
			<div>
				
				<h5>${board.content}</h5>
				<%-- <%--ZonedDateTime -> Date -> String 두번의 형변환 필요 --%>
				<fmt:parseDate value="${board.createdAt}" pattern="yyyy-MM-d'T'HH:mm:ss" var="parsedCreatedAt"/>
				<fmt:formatDate value="${parsedCreatedAt}" pattern="yyyy년 M월 d일 HH:mm:ss(E)"/> 
				<small>${board.userId}</small>
			</div><hr>
		</c:forEach>
	</div>
