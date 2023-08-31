<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div>
	<div class="d-flex justify-content-center mt-5">
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
		<div class="d-flex justify-content-between">
			<h4 class="font-weight-bold">※공지사항※</h4>
			<span class="mr-3 mt-3"><a href="/board/board_list_view?type=공지사항">더보기></a></span>
		</div>
			
		<table class="table table-striped">
			<thead>
				<tr>
					<th>제목</th>
					<th>날짜</th>
				</tr>
			</thead>
			<tbody>
				<!-- 공지사항 목록 3개 가져오기  -->
				<c:forEach items="${noticeList}" var="notice">
					<tr>
						<td><a href="/board/board_detail_view?boardId=${notice.id}">${notice.title}</a></td>
						<td>
							<%-- <%--ZonedDateTime -> Date -> String 두번의 형변환 필요 --%>
							<fmt:parseDate value="${notice.createdAt}" pattern="yyyy-MM-d'T'HH:mm:ss" var="parsedCreatedAt"/>
							<fmt:formatDate value="${parsedCreatedAt}" pattern="yyyy . M. d(E) HH:mm"/> 
						</td>	
					</tr>
				</c:forEach>
			</tbody>
		</table>
	
	<br><br>
	<hr style="background:black; height:1px;">
	<br><br>
<!-- 게시판 -->
	<div>
		<div class="d-flex justify-content-between">
			<h4 class="font-weight-bold">※게시판※</h4>
			<span class="mr-3 mt-3"><a href="/board/board_list_view?type=게시판">더보기></a></span>
		</div><hr>
		<!-- 게시판 목록 3개 가져오기  -->
		<table class="table table-striped">
			<thead>
				<tr>
					<th>제목</th>
					<th>글쓴이</th>
					<th>날짜</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${boardList}" var="board">
				<tr>
					<td><a href="/board/board_detail_view?boardId=${board.id}">${board.title}</a></td>
					<td><small>${board.userId}</small></td>
					<td>
						<%-- <%--ZonedDateTime -> Date -> String 두번의 형변환 필요 --%>
						<fmt:parseDate value="${board.createdAt}" pattern="yyyy-MM-d'T'HH:mm:ss" var="parsedCreatedAt"/>
						<fmt:formatDate value="${parsedCreatedAt}" pattern="yyyy. M. d(E) HH:mm"/> 
					</td>	
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
