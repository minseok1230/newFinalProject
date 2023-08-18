<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="d-flex justify-content-center">
	<div class="w-75">
		<div class="d-flex justify-content-start mt-4">
			<h2>MY PAGE</h2>
		</div>
		
		<!-- 개인 프로필 -->
		<div class="mt-3 ">
			<img src="${myPageView.user.profileImagePath}" width="50" alt="프로필사진">
			<span class="ml-3 font-weight-bold">${myPageView.user.name}(<small>${myPageView.user.email}</small>)</span>
			<a href="/user/user_update_view" class="btn btn-sm btn-secondary ml-3"><small>프로필수정</small></a>
		</div>
		
		<%--여기서 부터 팀정보가 있고 없고 유무에따라 갈라진다. --%>
		<!-- 팀 정보 -->
		<c:if test="${userTeamId != null}">
		<div class="mt-3">
				<img src="${team.profileImagePath}"  width="50" alt="팀로고">
				<span class="ml-3 font-weight-bold">${team.name}</span>
				<a href="#" class="btn btn-sm btn-secondary ml-3"><small>팀원관리</small></a>
				<a href="/team/team_update_view" class="btn btn-sm btn-secondary ml-2"><small>팀수정</small></a>
			</div>
		
		<br><br><br>
		
		<!-- 나의 경기장 예약 목록 (statium)-->
		<c:if test="${myPageView.user.role == '팀장'}">
			<h5 class="font-weight-bold text-info">경기장 예약 목록</h5>
			<div>
				<table class="table table-bordered table-sm">
					<thead  class="table-secondary">
						<tr class="text-center">
							<th>Date.</th>
							<th>경기장</th>
							<th>Time</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${myPageView.reservationList}" var="reservation">
							<tr class="text-center">
								<td class="align-middle"><fmt:formatDate value="${reservation.matchDate}" pattern="yyyy.M.d(E)"/></td>
								<td class="align-middle">${reservation.stadiumName}</td>
								<td class="align-middle">${reservation.matchTime}</td>
								<td class="align-middle">
									<button type="button" class="btn btn-danger btn-sm"><small>취소</small></button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<br><br><br>
		</c:if>
		
		<!-- 나의 팀 매칭글 목록 (match) -->
		<h5 class="font-weight-bold text-info">팀 매칭글 목록</h5>
		<div>
			<table class="table table-bordered table-sm">
				<thead  class="table-secondary">
					<tr class="text-center">
						<th>Date</th>
						<th>Name</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${myPageView.matchViewList}" var ="matchView">
						<tr class="text-center">
						<!-- (안양)20.07.31 비산 20:00 ~ 22:00 -->
							<td class="align-middle">${matchView.reservation.region}</td>
							<td class="align-middle">${matchView.match.title}</td>
							<td class="align-middle">
								<button type="button" class="btn btn-info btn-sm"><small>수정</small></button>
								<button type="button" class="btn btn-info btn-sm"><small>매칭중</small></button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<br><br><br>
		
		
		
		<!-- 매칭 신청 목록 (match) -->
		<c:if test="${myPageView.user.role == '팀장'}">
		<h5 class="font-weight-bold text-info">매칭 신청 목록</h5>
		<div>
			<table class="table table-bordered table-sm">
				<thead  class="table-secondary">
					<tr class="text-center">
						<th>Date.</th>
						<th>Name</th>
						<th>팀이름</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
						<tr class="text-center">
							<td class="align-middle">(안양)20.07.31 비산 20:00 ~ 22:00</td>
							<td class="align-middle">즐거운 축구해요~</td>
							<td class="align-middle">FC ANYANG</td>
							<td class="align-middle">
								<button type="button" class="btn btn-warning btn-sm"><small>신청완료</small></button>
								<button type="button" class="btn btn-success btn-sm"><small>매칭완료</small></button>
							</td>
						</tr>
				</tbody>
			</table>
		</div>
		<br><br><br>
		</c:if>
		
		<!-- 팀 가입 승인 목록 -->
		<c:if test="${myPageView.user.role == '팀장'}">
		<h5 class="font-weight-bold text-info">팀 가입 승인 목록</h5>
		<div>
			<table class="table table-bordered table-sm">
				<thead  class="table-secondary">
					<tr class="text-center">
						<th>Name</th>
						<th>나이</th>
						<th>position</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${myPageView.memberViewList}" var ="memberView">
						<tr class="text-center">
							<td class="align-middle">홍길동</td>
							<td class="align-middle">30</td>
							<td class="align-middle">CDM</td>
							<td class="align-middle">
								<button type="button" class="btn btn-info btn-sm"><small>수락</small></button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		</c:if>
		
		</c:if>
		<!-- 팀 가입 신청 목록-->
		<c:if test="${myPageView.requestMember != null}">
			<h5 class="font-weight-bold text-info mt-4">팀 가입 신청 목록</h5>
			<div>
				<table class="table table-bordered table-sm">
					<thead  class="table-secondary">
						<tr class="text-center">
							<th>팀이름</th>
							<th>활동지역</th>
							<th>실력</th>
							<th>팀원수</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
							<tr class="text-center">
								<td class="align-middle">${myPageView.team.name}</td>
								<td class="align-middle">${myPageView.team.activeArea}</td>
								<td class="align-middle">${myPageView.team.skill}</td>
								<td class="align-middle">팀원수</td>
								<td class="align-middle">
									<button type="button" class="btn btn-info btn-sm" disabled><small>승인대기</small></button>
									<button type="button" id="requestDeleteBtn" class="btn btn-info btn-sm" data-team-id ="${myPageView.team.id}" data-user-id = "${myPageView.user.id}"><small>취소</small></button>
								</td>
							</tr>
					</tbody>
				</table>
			</div>
		</c:if>
	</div>
</div>

<script>
$(document).ready(function(){
	
	$('#requestDeleteBtn').on('click', function(){
		let teamId = $(this).data("team-id");
		let userId = $(this).data("user-id");

		$.ajax({
			
			type: "delete"
			, url: "/member/" + teamId + "/" + userId
			
			, success: function(data){
				if (data.code == 1){
					alert("요청 취소되었습니다.");
					location.reload(true);
				}
			}
		
			, error: function(request, status, error){
				alert("관리자 문의 바랍니다.");
			}
			
		});
	});
});
</script>
























