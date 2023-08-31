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
			<img src="${myPageView.user.profileImagePath}" width="50" style="border: 3px solid gold;" alt="프로필사진">
			<span class="ml-3 font-weight-bold">${myPageView.user.name}(<small>${myPageView.user.email}</small>)</span>
			<a href="/user/user_update_view" class="btn btn-sm btn-secondary ml-3" style="height:30px; width:65px;"><small>프로필수정</small></a>
		</div>
		
		<%--여기서 부터 팀정보가 있고 없고 유무에따라 갈라진다. --%>
		<!-- 팀 정보 -->
		<c:if test="${myPageView.user.teamId != null}">
			<div class="mt-3">
					<img src="${myPageView.team.profileImagePath}"  width="50" style="border: 3px solid gold;" alt="팀로고">
					<span class="ml-3 font-weight-bold">${myPageView.team.name}</span>
					<a href="/member/member_list_view?teamId=${myPageView.team.id}" style="height:30px; width:55px;" class="btn btn-sm btn-secondary ml-3"><small>팀원</small></a>
					<c:if test="${myPageView.user.role == '팀장'}">
						<a href="/team/team_update_view" style="height:30px; width:55px;" class="btn btn-sm btn-secondary ml-2"><small>팀수정</small></a>
					</c:if>
			</div>
			<br><br><br>
		</c:if>

		<c:if test="${myPageView.user.role != null}">
			<div>
						<ul class="tabs d-flex justify-content-center font-weight-bold">
							<c:if test="${myPageView.user.role == '팀장'}">
								<li class="tab-link " data-tab="tab-1">경기장 예약</li>
							</c:if>
							<c:if test="${myPageView.user.teamId != null}">
								<li class="tab-link " data-tab="tab-2">팀 매칭글</li>
								<li class="tab-link current" data-tab="tab-3">매칭 확정</li>
							</c:if>
							<c:if test="${myPageView.user.role == '팀장'}">
								<li class="tab-link" data-tab="tab-4">매칭 신청</li>
								<li class="tab-link" data-tab="tab-5">매칭 요청</li>
								<li class="tab-link" data-tab="tab-6">가입 승인</li>
							</c:if>
						</ul>
					
				<!-- 나의 경기장 예약 목록 (statium)-->
				<c:if test="${myPageView.user.teamId != null}">
					<div id="tab-1" class="tab-content ">
						<c:if test="${myPageView.user.role == '팀장'}">
							<h5 class="font-weight-bold text-info text-center">경기장 예약 목록</h5>
							<div>
								<table class="table table-bordered table-sm">
									<thead  class="table-secondary">
										<tr class="text-center">
											<th style="width: 15%">Date</th>
											<th style="width: 10%">Region</th>
											<th style="width: 40%">Stadium</th>
											<th style="width: 25%">Time</th>
											<th style="width: 10%"></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${myPageView.reservationList}" var="reservation">
											<tr class="text-center">
												<td class="align-middle"><fmt:formatDate value="${reservation.matchDate}" pattern="yyyy.M.d(E)"/></td>
												<td class="align-middle" >${reservation.region}</td>
												<td class="align-middle" >${reservation.stadiumName}</td>
												<td class="align-middle" >${reservation.matchTime}</td>
												<c:if test="${reservation.possibleCancel}">
													<td class="align-middle" >
														<button type="button"  class="deleteBtn btn btn-danger btn-sm" data-toggle="modal" data-target="#modal" data-reservation-id ="${reservation.id}"><small>취소</small></button>
													</td>
												</c:if>
												<c:if test="${!reservation.possibleCancel}">
														<td class="align-middle text-danger font-weight-bold" >취소불가</td>
												</c:if>
												
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<br><br><br>
						</c:if>
					</div>
					
					
					<!-- 나의 팀 매칭글 목록 (match) -->
					<div id="tab-2" class="tab-content ">
						<h5 class="font-weight-bold text-info text-center">팀 매칭글 목록</h5>
						<div>
							<table class="table table-bordered table-sm">
								<thead  class="table-secondary">
									<tr class="text-center">
										<th style="width: 15%">Date</th>
										<th style="width: 10%">Region</th>
										<th style="width: 40%">stadium</th>
										<th style="width: 25%">Name</th>
										<th style="width: 10%"></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${myPageView.matchViewList}" var ="matchView">
										<tr class="text-center">
										<!-- (안양)20.07.31 비산 20:00 ~ 22:00 -->
											<td class="align-middle" ><fmt:formatDate value="${matchView.reservation.matchDate}" pattern="yyyy.M.d(E)"/></td>
											<td class="align-middle" >${matchView.reservation.region}</td>
											<td class="align-middle" >${matchView.reservation.stadiumName}</td>
											<td class="align-middle" ><a href="/match/match_detail_view?matchId=${matchView.match.id}">${matchView.match.title}</a></td>
											<c:if test="${matchView.match.state =='모집중'}">	
												<td class="align-middle font-weight-bold text-warning" >모집중</td>
											</c:if>
											<c:if test="${matchView.match.state == '매칭완료'}">	
												<td class="align-middle font-weight-bold text-danger" >매칭완료</td>
											</c:if>
											<c:if test="${matchView.match.state == '경기완료'}">	
												<td class="align-middle font-weight-bold text-primary" >경기완료</td>
											</c:if>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<br><br><br>
					</div>
					
					
					<!-- 매칭 확정 목록 (match) -->
					<div id="tab-3" class="tab-content current">
						<h5 class="font-weight-bold text-info text-center">매칭 확정 목록</h5>
						<div>
							<table class="table table-bordered table-sm">
								<thead  class="table-secondary">
									<tr class="text-center">
										<th style="width: 15%">Date</th>
										<th style="width: 10%">Region</th>
										<th style="width: 35%">Stadium</th>
										<th style="width: 10%">Time</th>
										<th style="width: 15%">Team</th>
										<th style="width: 15%"></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${doneMatchRelationViewList}" var="doneMatch">
										<tr class="text-center">
											<td class="align-middle" ><fmt:formatDate value="${doneMatch.matchView.reservation.matchDate}" pattern="yyyy.M.d(E)"/></td>
											<td class="align-middle" >${doneMatch.matchView.reservation.region}</td>
											<td class="align-middle" >
												<span>${doneMatch.matchView.reservation.stadiumName}</span>
												<button type="button" id="stadiumMap" class="stadiumMap btn btn-sm btn-primary" data-toggle="modal" data-target="#stadium" data-stadium-name="${doneMatch.matchView.reservation.stadiumName}">
				      							 지도보기
				   								</button>
											</td>
											<td class="align-middle" >${doneMatch.matchView.reservation.matchTime}</td>
											<td class="align-middle" >${doneMatch.team.name}</td>
											<c:if test="${doneMatch.matchView.match.state == '매칭완료'}">
												<td class="align-middle font-weight-bold  text-danger" >${doneMatch.matchView.match.state}</td>
											</c:if>
											<c:if test="${doneMatch.matchView.match.state == '경기완료'}">
												<td class="align-middle font-weight-bold  text-primary" >
												 	<c:if test="${myPageView.user.role == '팀장'}">
														<a href="/rating/rating_view?teamId=${teamId}&matchedTeamId=${doneMatch.team.id}&matchId=${doneMatch.matchView.match.id}" id="ratingBtn" >평점주기</a>
													</c:if>
												 	<c:if test="${myPageView.user.role != '팀장'}">
														경기완료
													</c:if>
												</td>
											</c:if>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<br><br><br>
					</div>
					
					
					<!-- 매칭 신청 목록 (match) -->
					<div id="tab-4" class="tab-content">
						<c:if test="${myPageView.user.role == '팀장'}">
						<h5 class="font-weight-bold text-info text-center">매칭 신청 목록</h5>
						<div>
							<table class="table table-bordered table-sm">
								<thead  class="table-secondary">
									<tr class="text-center">
										<th style="width: 15%">Date</th>
										<th style="width: 10%">Region</th>
										<th style="width: 35%">Stadium</th>
										<th style="width: 10%">Time</th>
										<th style="width: 15%">Team</th>
										<th style="width: 20%"></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${applyMatchRelationViewList}" var="applyMatch">
											<tr class="text-center">
												<td class="align-middle" ><fmt:formatDate value="${applyMatch.matchView.reservation.matchDate}" pattern="yyyy.M.d(E)"/></td>
												<td class="align-middle" >${applyMatch.matchView.reservation.region}</td>
												<td class="align-middle" >${applyMatch.matchView.reservation.stadiumName}</td>
												<td class="align-middle" >${applyMatch.matchView.reservation.matchTime}</td>
												<td class="align-middle" >${applyMatch.team.name}</td>
												<td class="align-middle font-weight-bold text-danger" >수락대기중</td>
											</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<br><br><br>
						</c:if>
					</div>
					
					
					<!-- 매칭 신청받은 목록 (match) -->
					<div id="tab-5" class="tab-content">
						<c:if test="${myPageView.user.role == '팀장'}">
						<h5 class="font-weight-bold text-info text-center">매칭 요청 목록</h5>
						<div>
							<table class="table table-bordered table-sm">
								<thead  class="table-secondary">
									<tr class="text-center">
										<th style="width: 15%">Date</th>
										<th style="width: 10%">Region</th>
										<th style="width: 35%">Stadium</th>
										<th style="width: 10%">Time</th>
										<th style="width: 15%">Team</th>
										<th style="width: 20%"></th>
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
													<button type="button" class="acceptMatchbtn btn-info btn-sm" data-matchrelation-id="${applyedMatch.matchRelation.id}" data-matchedteam-id="${applyedMatch.team.id}"><small>수락</small></button>
												</td>
											</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<br><br><br>
						</c:if>
					</div>
					
					
					
					<!-- 팀 가입 승인 목록 -->
					<div id="tab-6" class="tab-content">
							<c:if test="${myPageView.user.role == '팀장'}">
								<h5 class="font-weight-bold text-info text-center">가입 승인 목록</h5>
								<div>
									<table class="table table-bordered table-sm">
										<thead  class="table-secondary">
											<tr class="text-center">
												<th style="width: 25%">Name</th>
												<th style="width: 25%">Birth</th>
												<th style="width: 25%">position</th>
												<th style="width: 25%"></th>
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
					</div>
			 </c:if> 
					
			</div>
		</c:if>
		<!-- 팀 가입 신청 목록-->
		<c:if test="${myPageView.requestMember != null}">
				<h5 class="font-weight-bold text-info mt-4 text-center">팀 가입 신청 목록</h5>
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


<!-- Modal for Stadium Map -->
<div class="modal fade" id="stadium">
	<div class="modal-dialog modal-xl modal-dialog-centered">
		<div class="modal-content text-center">
			<div class="modal-header text-center">
				<h5 class="modal-title" id="staticBackdropLabel">경기장 위치</h5>
			</div>
			<div class="modal-body">
				<div class="mt-3 d-flex justify-content-center w-100">
					<div id="map" style="width: 100%; height: 600px;"></div>
				</div>
			</div>
			<div class="modal-footer d-flex justify-content-end">
				<div class="py-3">
					<a href="#" data-dismiss="modal">나가기</a>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- services와 clusterer, drawing 라이브러리 불러오기 -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=dc8476ea9375c69674cea47fce478ccf&libraries=services,clusterer,drawing"></script>

<script>
	$(document).ready(
			function() {

				/* 팀 가입 신청 취소*/
				$('#requestDeleteBtn').on('click', function() {
					let teamId = $(this).data("team-id");
					let userId = $(this).data("user-id");

					$.ajax({

						type : "delete",
						url : "/member/" + teamId + "/" + userId

						,
						success : function(data) {
							if (data.code == 1) {
								alert("요청 취소되었습니다.");
								location.reload(true);
							}
						}

						,
						error : function(request, status, error) {
							alert("관리자 문의 바랍니다.");
						}

					});
				});

				var selectedReservationId; // 선택된 예약 ID를 저장할 변수

				$('.deleteBtn').on(
						'click',
						function(e) {
							e.preventDefault();
							selectedReservationId = $(this).data(
									"reservation-id"); // 선택된 예약 ID를 변수에 저장
							$('#deleteStadiumBtn').attr('data-reservation-id',
									selectedReservationId);
						});

				$('#deleteStadiumBtn').on('click', function(e) {
					e.preventDefault();
					let reservationId = selectedReservationId;

					$.ajax({

						type : "delete",
						url : "/reservation/" + reservationId

						,
						success : function(data) {
							if (data.code == 1) {
								location.href = "/main/my_page_view";
							} else {
								alert(data.errorMessage);
							}
						}

						,
						error : function(request, status, error) {
							alert("관리자에게 문의바랍니다.");
						}
					});
				});

				// 팀원 승인
				$('.acceptMemberBtn').on('click', function() {
					let userId = $(this).data("user-id");
					let teamId = $(this).data("team-id");
					

					$.ajax({

						type : "put",
						url : "/member/" + teamId + "/" + userId

						,
						success : function(data) {
							if (data.code == 1) {
								alert("축하합니다!! 팀원으로 등록되었습니다!!");
								location.href = "/main/my_page_view";
							} else {
								alert(data.errorMessage);
							}
						}

						,
						error : function(request, status, error) {
							alert("관리자에게 문의 바랍니다.")
						}

					});

				});

				// 매칭 수락
				$('.acceptMatchbtn').on('click', function() {
					let matchRelationId = $(this).data("matchrelation-id");
					let matchedTeamId = $(this).data("matchedteam-id");
					
					$.ajax({

						type : "put",
						url : "/matchRelation/" + matchRelationId 

						,
						success : function(data) {
							if (data.code == 1) {
								alert("매칭을 수락하였습니다. ");
								location.reload(true);
							} else {
								alert(data.errorMessage);
							}
						}

						,
						error : function(request, status, error) {
							alert("관리자에게 문의해주세요.")
						}
					});
				});

				
				
				// 경기장 지도 뿌리기
				$(".stadiumMap").on('click', function() {
	                let stadiumName = $(this).data("stadium-name");

	                // Modal shown event handler
	                $('#stadium').on('shown.bs.modal', function () {
	                    var mapContainer = document.getElementById('map');
	                    var mapOption = {
	                        center: new kakao.maps.LatLng(37.54699, 127.09598),
	                        level: 3
	                    };
	                    var map = new kakao.maps.Map(mapContainer, mapOption);
	                    var inputData = [stadiumName];

	                    var count = 0;
	                    var ps = new kakao.maps.services.Places();
	                    var bounds = new kakao.maps.LatLngBounds();

	                    kewwordSearch(inputData[count]);

	                    function kewwordSearch(keword) {
	                        ps.keywordSearch(keword, placesSearchCB);
	                        count = count + 1;
	                    }

	                    function placesSearchCB(data, status, pagination) {
	                        if (status === kakao.maps.services.Status.OK) {
	                            displayMarker(data[0]);
	                            bounds.extend(new kakao.maps.LatLng(data[0].y, data[0].x));
	                            if (count < inputData.length) {
	                                kewwordSearch(inputData[count]);
	                            } else if (count == inputData.length) {
	                                setBounds();
	                            }
	                        }
	                    }

	                    function displayMarker(place) {
	                        var marker = new kakao.maps.Marker({
	                            map: map,
	                            position: new kakao.maps.LatLng(place.y, place.x),
	                        });
	                        kakao.maps.event.addListener(marker, 'click', function() {
	                            var position = this.getPosition();
	                            var url = 'https://map.kakao.com/link/map/' + place.id;
	                            window.open(url, '_blank');
	                        });
	                    }

	                    function setBounds() {
	                        map.setBounds(bounds, 1000, 1000, 1000, 1000);
	                        var newLevel = map.getLevel() + 1;
	                        map.setLevel(newLevel);
	                    }
	                });

	                // Show the modal
	                $('#stadium').modal('show');
	            });
				
				// 마이페이지 나누기
				$('ul.tabs li').click(function(){
				    var tab_id = $(this).attr('data-tab');

				    $('ul.tabs li').removeClass('current');
				    $('.tab-content').removeClass('current');

				    $(this).addClass('current');
				    $("#"+tab_id).addClass('current');
				  })
	        });
</script>
























