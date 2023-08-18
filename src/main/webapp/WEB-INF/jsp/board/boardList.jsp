<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
					<c:forEach items="${boardViewList}" var="board">
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
						<c:if test="${pageMaker.prev == true}">
							<li class="page-item">
								<a class="page-link" href="/board/board_list_view?type=${boardType}&clickPage=${pageMaker.currentPageList[0] - 1}" aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a>
							</li>
						</c:if>
							<c:forEach items="${pageMaker.currentPageList}" var="page">
								<li class="page-item"><a class="page-link"
									href="/board/board_list_view?type=${boardType}&clickPage=${page}">${page}</a>
								</li>
							</c:forEach>
						<c:if test="${pageMaker.next == true}">
							<li class="page-item">
								<a class="page-link" href="/board/board_list_view?type=${boardType}&clickPage=${pageMaker.currentPageList[fn:length(pageMaker.currentPageList) - 1] + 1}" aria-label="Next"> <span aria-hidden="true">&raquo;</span></a>
							</li>
						</c:if>
					</ul>
				</nav>
			</div>
	</div>
</Div>



