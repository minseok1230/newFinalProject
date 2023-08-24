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
		<c:if test="${myPageView.user.teamId != null}">
		<div class="mt-3">
				<img src="${myPageView.team.profileImagePath}"  width="50" alt="팀로고">
				<span class="ml-3 font-weight-bold">${myPageView.team.name}</span>
				<a href="/member/member_list_view?teamId=${myPageView.team.id}" class="btn btn-sm btn-secondary ml-3"><small>팀원</small></a>
				<c:if test="${myPageView.user.role == '팀장'}">
					<a href="/team/team_update_view" class="btn btn-sm btn-secondary ml-2"><small>팀수정</small></a>
				</c:if>
			</div>
		
		<br><br><br>
		
		
		<!-- 나의 경기장 예약 목록 (statium)-->
		<c:if test="${myPageView.user.role == '팀장'}">
			<h5 class="font-weight-bold text-info">경기장 예약 목록</h5>
			<div>
				<table class="table table-bordered table-sm">
					<thead  class="table-secondary">
						<tr class="text-center">
							<th>Date</th>
							<th>Region</th>
							<th>Stadium</th>
							<th>Time</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${myPageView.reservationList}" var="reservation">
							<tr class="text-center">
								<td class="align-middle"><fmt:formatDate value="${reservation.matchDate}" pattern="yyyy.M.d(E)"/></td>
								<td class="align-middle">${reservation.region}</td>
								<td class="align-middle">${reservation.stadiumName}</td>
								<td class="align-middle">${reservation.matchTime}</td>
								<c:if test="${reservation.possibleCancel}">
									<td class="align-middle">
										<button type="button"  class="deleteBtn btn btn-danger btn-sm" data-toggle="modal" data-target="#modal" data-reservation-id ="${reservation.id}"><small>취소</small></button>
									</td>
								</c:if>
								<c:if test="${!reservation.possibleCancel}">
										<td class="align-middle text-danger font-weight-bold">취소불가</td>
								</c:if>
								
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
						<th>Region</th>
						<th>stadium</th>
						<th>Name</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${myPageView.matchViewList}" var ="matchView">
						<tr class="text-center">
						<!-- (안양)20.07.31 비산 20:00 ~ 22:00 -->
							<td class="align-middle"><fmt:formatDate value="${matchView.reservation.matchDate}" pattern="yyyy.M.d(E)"/></td>
							<td class="align-middle">${matchView.reservation.region}</td>
							<td class="align-middle">${matchView.reservation.stadiumName}</td>
							<td class="align-middle"><a href="/match/match_detail_view?matchId=${matchView.match.id}">${matchView.match.title}</a></td>
							<c:if test="${matchView.match.state =='모집중'}">	
								<td class="align-middle font-weight-bold text-warning">모집중</td>
							</c:if>
							<c:if test="${matchView.match.state == '매칭완료'}">	
								<td class="align-middle font-weight-bold text-danger">매칭완료</td>
							</c:if>
							<c:if test="${matchView.match.state == '경기완료'}">	
								<td class="align-middle font-weight-bold text-primary">경기완료</td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<br><br><br>
		
		<!-- 매칭 확정 목록 (match) -->
		<h5 class="font-weight-bold text-info">매칭 확정 목록</h5>
		<div>
			<table class="table table-bordered table-sm">
				<thead  class="table-secondary">
					<tr class="text-center">
						<th>Date</th>
						<th>Region</th>
						<th>Stadium</th>
						<th>Time</th>
						<th>Team</th>
					</tr>
				</thead>
				<tbody>
						<c:forEach items="${doneMatchRelationViewList}" var="doneMatch">
							<tr class="text-center">
								<td class="align-middle"><fmt:formatDate value="${doneMatch.matchView.reservation.matchDate}" pattern="yyyy.M.d(E)"/></td>
								<td class="align-middle">${doneMatch.matchView.reservation.region}</td>
								<td class="align-middle">${doneMatch.matchView.reservation.stadiumName}</td>
								<td class="align-middle">${doneMatch.matchView.reservation.matchTime}</td>
								<td class="align-middle">${doneMatch.team.name}</td>
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
						<th>Date</th>
						<th>Region</th>
						<th>Stadium</th>
						<th>Time</th>
						<th>Team</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${applyMatchRelationViewList}" var="applyMatch">
							<tr class="text-center">
								<td class="align-middle"><fmt:formatDate value="${applyMatch.matchView.reservation.matchDate}" pattern="yyyy.M.d(E)"/></td>
								<td class="align-middle">${applyMatch.matchView.reservation.region}</td>
								<td class="align-middle">${applyMatch.matchView.reservation.stadiumName}</td>
								<td class="align-middle">${applyMatch.matchView.reservation.matchTime}</td>
								<td class="align-middle">${applyMatch.team.name}</td>
								<td class="align-middle font-weight-bold text-danger">수락대기중</td>
							</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<br><br><br>
		</c:if>
		
		<!-- 매칭 신청받은 목록 (match) -->
		<c:if test="${myPageView.user.role == '팀장'}">
		<h5 class="font-weight-bold text-info">매칭 신청받은 목록</h5>
		<div>
			<table class="table table-bordered table-sm">
				<thead  class="table-secondary">
					<tr class="text-center">
						<th>Date</th>
						<th>Region</th>
						<th>Stadium</th>
						<th>Time</th>
						<th>Team</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${applyedMatchRelationViewList}" var="applyedMatch">
							<tr class="text-center">
								<td class="align-middle"><fmt:formatDate value="${applyedMatch.matchView.reservation.matchDate}" pattern="yyyy.M.d(E)"/></td>
								<td class="align-middle">${applyedMatch.matchView.reservation.region}</td>
								<td class="align-middle">${applyedMatch.matchView.reservation.stadiumName}</td>
								<td class="align-middle">${applyedMatch.matchView.reservation.matchTime}</td>
								<td class="align-middle">${applyedMatch.team.name}</td>
								<td class="align-middle">
									<button type="button" class="acceptMatchbtn btn-info btn-sm" data-matchrelation-id="${applyedMatch.matchRelation.id}"><small>수락</small></button>
								</td>
							</tr>
					</c:forEach>
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
									<td class="align-middle">${memberView.user.name}</td>
									<td class="align-middle">${memberView.user.birth}</td>
									<td class="align-middle">${memberView.user.position}</td>
									<td class="align-middle">
										<button type="button" class="acceptMemberBtn btn btn-info btn-sm" data-user-id="${memberView.user.id}" data-team-id="${memberView.team.id}"><small>수락</small></button>
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

<!-- Modal -->
<div class="modal fade" id="modal">
	modal-dialog-centered: 모달창 수직기준 가운데 위치
	<div class="modal-dialog modal modal-dialog-centered">
		<div class="modal-content text-center">
				<div class="modal-header text-center">
       				<h5 class="modal-title" id="staticBackdropLabel">경기장 예약 취소</h5>
    			</div>
    			<div class="modal-body">
       			  	<span>예약을 정말 <b class="text-danger">취소</b>하시겠습니까?</span> <br>
       			  	<span>예약을 취소하면 관련된 매칭글도 자동 삭제됩니다.</span>
    			</div>
    			<div class="modal-footer d-flex justify-content-between">
		      		<div class="py-3">	
		      			<a href="#" id="deleteStadiumBtn">삭제하기</a>
		      		</div>
		      		<div class="py-3">	
		      			<a href="#" data-dismiss="modal">취소하기</a>
		      		</div>
		      	</div>
    	</div>
	</div>
</div> 

<script>
$(document).ready(function(){
	
	
	/* 팀 가입 신청 취소*/
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
	
	var selectedReservationId; // 선택된 예약 ID를 저장할 변수
    
    $('.deleteBtn').on('click', function(e){
    	e.preventDefault();
        selectedReservationId = $(this).data("reservation-id"); // 선택된 예약 ID를 변수에 저장
        $('#deleteStadiumBtn').attr('data-reservation-id', selectedReservationId);
    });
    
    $('#deleteStadiumBtn').on('click', function(e){
    	e.preventDefault();
        let reservationId = selectedReservationId;
		
        $.ajax({
        	
        	type: "delete"
        	, url : "/reservation/" + reservationId
        	
        	, success: function(data){
        		if (data.code == 1){
        			location.href = "/main/my_page_view"; 
        		} else{
        			alert(data.errorMessage);
        		}
        	}
        
        	, error: function(request, status, error){
        		alert("관리자에게 문의바랍니다.");
        	}
        });
    });
    
    
    
    
    // 팀원 승인
    $('.acceptMemberBtn').on('click', function(){
    	let userId = $(this).data("user-id");
    	let teamId = $(this).data("team-id");
    	
    	$.ajax({
    		
    		type: "put"
    		, url: "/member/" + teamId + "/" + userId
    		
    		, success: function(data){
    			if (data.code == 1){
    				alert("축하합니다!! 팀원으로 등록되었습니다!!");
    				location.href="/main/my_page_view";
    			} else{
    				alert(data.errorMessage);
    			}
    		}
    	
    		, error: function(request, status, error){
    			alert("관리자에게 문의 바랍니다.")
    		}
    		
    	})
    	
    });
    
    
    // 매칭 수락
    $('.acceptMatchbtn').on('click', function(){
    	let matchRelationId = $(this).data("matchrelation-id");
    	
    	$.ajax({
    		
    		type: "put"
    		,url : "/matchRelation/" + matchRelationId
    		
    		, success : function(data){
    			if (data.code == 1){
    				alert("매칭을 수락하였습니다. ");
    				location.reload(true);
    			} else{
    				alert(data.errorMessage);
    			}
    		}
    	
    		, error: function(request, status, error){
    			alert("관리자에게 문의해주세요.")
    		}
    	});
    });
    
    
	
});
</script>
























