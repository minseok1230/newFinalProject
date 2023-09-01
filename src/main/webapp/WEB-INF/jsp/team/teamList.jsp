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
						<th style="width: 20%">팀명</th>
						<th style="width: 20%" >실력</th>
						<th style="width: 20%">활동지역</th>
						<th style="width: 20%">팀원수</th>
						<c:if test="${userTeamId == null && member == null}">
							<th style="width: 20%">가입</th>
						</c:if>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${teamViewList}" var="teamView">
					<tr class="text-center">
						<td>
							<a class="teamIntroduce" href="#" class=" w-100" data-toggle="modal" data-target="#modal" data-team-introduce ="${teamView.team.introduce}">${teamView.team.name}</a>
						</td>
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


<!-- Modal -->
<div class="modal fade" id="modal">
	<%-- modal-dialog-centered: 모달창 수직기준 가운데 위치--%>
	<div class="modal-dialog modal-md modal-dialog-centered">
		<div class="modal-content text-center">
				<div class="modal-header text-center">
       				<h5 class="modal-title" id="staticBackdropLabel">팀소개</h5>
    			</div>
    			<div class="modal-body">
       			  	<textarea id="introduce" rows="12" cols="50"></textarea>
    			</div>
    			<div class="modal-footer d-flex justify-content-end">
		      		<div class="py-3">	
		      			<a href="#" data-dismiss="modal">취소하기</a>
		      		</div>
		      	</div>
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
	
	
	
	/* 모달에 값 넘겨주기 */
	$('.teamIntroduce').on('click', function(e){
		e.preventDefault(); 
		
		let teamIntroduce = $(this).data('team-introduce');

		  // 모달 내 span 태그에 넘겨준 값 설정
		  $('.modal-body #introduce').text(teamIntroduce);

		  // 모달 열기
		  $('#modal').modal('show');
	});
});
</script>














