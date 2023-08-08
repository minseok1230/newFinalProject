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
			<table class="table table-hover" >
				<tbody>
					<c:forEach items="${boardList}" var="board">
						<tr>
								<td class=" text-start" style="width: 20%">${board.board.title}</td>
								<td class=" text-start" style="width: 50%"><a href="/board/board_detail_view?boardId=${board.board.id}">${board.board.content}</a></td>
								<td class=" text-center" style="width: 10%"><small>${board.user.name}</small></td>
								<td class=" text-center" style="width: 20%">
									<fmt:parseDate value="${board.board.createdAt}" pattern="yyyy-MM-d'T'HH:mm:ss" var="parsedCreatedAt"/>
									<small><fmt:formatDate value="${parsedCreatedAt}" pattern="yyyy년 M월 d일(E) HH:mm"/> </small>
								</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<!-- 페이징 -->
			<div class="d-flex justify-content-center">
				<nav>
					<ul class="pagination">
						<li class="page-item">
							<a class="page-link" href="/board/board_list_view?type=${boardType}&prevId=1" aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a>
						</li>
						<li class="page-item"><a class="page-link" href="/board/board_list_view?type=${boardType}&topPageNum=${topPageNum}&page=${page}">${topPageNum}</a></li>
						<li class="page-item"><a class="page-link" href="/board/board_list_view?type=${boardType}&topPageNum=${topPageNum + 1}&page=${page}">${topPageNum + 1}</a></li>
						<li class="page-item"><a class="page-link" href="/board/board_list_view?type=${boardType}&topPageNum=${topPageNum + 2}&page=${page}">${topPageNum + 2}</a></li>
						<li class="page-item"><a class="page-link" href="/board/board_list_view?type=${boardType}&topPageNum=${topPageNum + 3}&page=${page}">${topPageNum + 3}</a></li>
						<li class="page-item"><a class="page-link" href="/board/board_list_view?type=${boardType}&topPageNum=${topPageNum + 4}&page=${page}">${topPageNum + 4}</a></li>
						<li class="page-item">
							<a class="page-link" href="/board/board_list_view?type=${boardType}&nextId=1&topPageNum=${topPageNum + 5}&page=${page}" aria-label="Next"> <span aria-hidden="true">&raquo;</span></a>
						</li>
					</ul>
				</nav>
			</div>
	</div>
</Div>