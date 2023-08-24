<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="d-flex justify-content-center">
	<div class="w-75">
		<div class="d-flex justify-content-center mt-4">
			<h2>TEAM LIST</h2>
		</div>
		
		<div>
			<table class="table table-bordered">
				<thead  class="table-secondary">
					<tr class="text-center">
						<th>팀명</th>
						<th>실력</th>
						<th>활동지역</th>
						<th>팀원수</th>
						<c:if test="${userTeamId == null && member == null}">
							<th>가입</th>
						</c:if>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${teamViewList}" var="teamView">
					<tr class="text-center">
						<td>${teamView.team.name}</td>
						<td>${teamView.team.skill}</td>
						<td>${teamView.team.activeArea}</td>
						<td>${teamView.totalTeamMember}</td>
						<c:if test="${userTeamId == null && member == null}">
						<!-- && team.id != member.teamId} -->
							<td>
								<button type="button" class="joinTeamBtn btn btn-warning btn-sm" data-team-id="${teamView.team.id}">가입신청</button>
							</td>
						</c:if>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>

		<!-- 페이징 -->
		<div class="d-flex justify-content-center">
			<nav>
				<ul class="pagination">
					<c:if test="${pageMaker.paging.prev == true}">
						<li class="page-item"><a class="page-link"
							href="/team/team_list_view?clickPage=${pageMaker.paging.currentPageList[0] - 1}"
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a>
						</li>
					</c:if>
					<c:forEach items="${pageMaker.paging.currentPageList}" var="page">
						<li class="page-item"><a class="page-link"
							href="/team/team_list_view?clickPage=${page}">${page}</a>
						</li>
					</c:forEach>
					<c:if test="${pageMaker.paging.next == true}">
						<li class="page-item"><a class="page-link"
							href="/team/team_list_view?clickPage=${pageMaker.paging.currentPageList[fn:length(pageMaker.paging.currentPageList) - 1] + 1}"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span></a></li>
					</c:if>
				</ul>
			</nav>
		</div>
	</div>
</div>

<script>
$(document).ready(function(){
	
	$('.joinTeamBtn').on('click', function(){
		let teamId = $(this).data("team-id");
		
		$.ajax({
			
			type: "post"
			, url: "/member/create_member" 
			, data : {"teamId": teamId}
			, success: function(data){
				if (data.code == 1){
					alert("팀가입신청이 완료되었습니다.");
					location.href = "/main/my_page_view";
				} else{
					alert(data.errorMessage);
				}
			}
		
			, error: function(request, status, error){
				alert("팀 가입신청이 실패하였습니다. 관리자에게 문의해주세요.");
			}
		});
	});
});
</script>














