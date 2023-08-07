<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="d-flex justify-content-center" >
	<div class="w-75">
		<div class="d-flex justify-content-between mt-5 mb-3">
			<c:if test="${boardType eq '공지사항'}">
				<h3 class="font-weight-bold">공지사항</h3>
			</c:if>
			<c:if test="${boardType eq '게시판'}">
				<h3 class="font-weight-bold">게시판</h3>
			</c:if>
						
			<a href="/board/board_create_view?type=${boardType}" class="mt-2 mr-3">글쓰기</a>
		</div>
		<table class="table text-center" >
			<tbody>
				<c:forEach items="${boardList}" var="board">
					<tr>
							<td>${board.title}</td>
							<td>${board.userId}</td>
							<td><a href="/board/board_detail_view?boardId=${board.id}">${board.content}</a></td>
							<td>
								<fmt:parseDate value="${board.createdAt}" pattern="yyyy-MM-d'T'HH:mm:ss" var="parsedCreatedAt"/>
								<fmt:formatDate value="${parsedCreatedAt}" pattern="yyyy년 M월 d일 HH:mm:ss(E)"/> 
							</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</Div>