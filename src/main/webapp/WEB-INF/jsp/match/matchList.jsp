<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="d-flex justify-content-center">
	<div class="w-75">
		<div class="d-flex justify-content-center mt-4">
			<h2>MATCHING LIST</h2>
		</div>
		
		<!-- 검색창 -->
		<div class="d-flex justify-content-between mt-4">
			<div class="input-group col-5">
				<input type="text" class="form-control" id="region" name="region" placeholder="지역 검색 (ex. 안양시) ">
				<div class="input-group-append">
					<a type="button" id="regionSearch" class="btn btn-secondary">검색</a>
				</div>
			</div>
			
			<div class="input-group col-5">
				<input type="text" class="form-control" id="title" name="title" placeholder="제목 검색 (ex. 실력 하하) ">
				<div class="input-group-append">
					<a type="button" id="titleSearch" class="btn btn-secondary">검색</a>
				</div>
			</div>
		</div><br><br><br><br>
		<div>
			<table class="table table-bordered">
				<thead  class="table-secondary">
					<tr class="text-center">
						<th>Game.</th>
						<th>Title.</th>
						<th>Team</th>
						<th>Price.</th>
						<th>State</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${matchViewList}" var="matchView">
						<tr class="text-center">
							<td>${matchView.reservation.region} ${matchView.reservation.stadiumName}<br> <fmt:formatDate value="${matchView.reservation.matchDate}" pattern="yyyy.M.d(E)"/> ${matchView.reservation.matchTime}</td>
							<td><a href="/match/match_detail_view?matchId=${matchView.match.id}">${matchView.match.title}</a></td>
							<td>${matchView.team.name}</td>
							<td>${matchView.match.price }</td>
							<c:if test="${matchView.match.state == '모집중'}">
								<td class="font-weight-bold text-warning">${matchView.match.state}</td>
							</c:if>
							<c:if test="${matchView.match.state == '매칭완료'}">
								<td class="font-weight-bold text-danger">${matchView.match.state}</td>
							</c:if>
							<c:if test="${matchView.match.state == '경기완료'}">
								<td class="font-weight-bold text-primary">${matchView.match.state}</td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		<!-- 페이징 -->
		<c:if test="${pageMaker != null}">
			<div class="d-flex justify-content-center">
				<nav>
					<ul class="pagination">
						<c:if test="${pageMaker.paging.prev == true}">
							<li class="page-item">
								<a class="page-link" href="/match/match_list_view?&clickPage=${pageMaker.paging.currentPageList[0] - 1}" aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a>
							</li>
						</c:if>
							<c:forEach items="${pageMaker.paging.currentPageList}" var="page">
								<li class="page-item"><a class="page-link"
									href="/match/match_list_view?&clickPage=${page}">${page}</a>
								</li>
							</c:forEach>
						<c:if test="${pageMaker.paging.next == true}">
							<li class="page-item">
								<a class="page-link" href="/match/match_list_view?&clickPage=${pageMaker.paging.currentPageList[fn:length(pageMaker.paging.currentPageList) - 1] + 1}" aria-label="Next"> <span aria-hidden="true">&raquo;</span></a>
							</li>
						</c:if>
					</ul>
				</nav>
			</div>
		</c:if>
	</div>
</div>


<script>
$(document).ready(function(){
	/* 매칭글 검색 */
	$('#regionSearch').on('click', function(){
		let region = $('#region').val().trim();
		// URL에 region 값을 추가하여 href 속성 설정
	     let link = "/match/match_list_view?regionSearch=" + region;
	    $('#regionSearch').attr('href', link);
	});
	
	$('#titleSearch').on('click', function(){
		let title = $('#title').val().trim();
		// URL에 region 값을 추가하여 href 속성 설정
	     let link = "/match/match_list_view?titleSearch=" + title;
	    $('#titleSearch').attr('href', link);
	});
});
</script>























