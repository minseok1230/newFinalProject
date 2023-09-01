<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="d-flex justify-content-center">
	<div class="w-75">
		<div class="d-flex justify-content-center mt-4">
			<h2 class="font-weight-bold">${matchView.match.title} </h2>
		</div>
			<!-- 경기장 지도 -->
			<div class="mt-3 d-flex justify-content-center w-100">
				<div id="map" style="width:1000px;height:400px;"></div>
			</div>
			
			<!-- services와 clusterer, drawing 라이브러리 불러오기 -->
			<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=dc8476ea9375c69674cea47fce478ccf&libraries=services,clusterer,drawing"></script>
			
			<script>
			
				let stadium = "${matchView.reservation.stadiumName}"
				var inputData = [stadium]

				var mapContainer = document.getElementById('map'), mapOption = {
					center : new kakao.maps.LatLng(37.54699, 127.09598),
					level : 3
				};
				
				// 지도생성 
				var map = new kakao.maps.Map(mapContainer, mapOption);
				// 검색어 목록에서 몇번째 검색어 처리 
				var count = 0;
				//장소 검색 서비스를 사용하기 위한 객체 
				var ps = new kakao.maps.services.Places();
				var bounds = new kakao.maps.LatLngBounds();

				if (inputData != null) {
					kewwordSearch(inputData[count]);
				}

				function kewwordSearch(keword) {
					ps.keywordSearch(keword, placesSearchCB);
					count = count + 1;
				}

				function placesSearchCB(data, status, pagination) {
					if (status === kakao.maps.services.Status.OK) {
						displayMarker(data[0]);
						bounds.extend(new kakao.maps.LatLng(data[0].y,
								data[0].x));
						if (count < inputData.length) {
							kewwordSearch(inputData[count]);
						} else if (count == inputData.length) {
							setBounds();
						}
					}
				}
				
				//마커 표시 함수 
				function displayMarker(place) {
					var marker = new kakao.maps.Marker({
						map : map,
						position : new kakao.maps.LatLng(place.y, place.x),
					});
					kakao.maps.event.addListener(marker, 'click', function() {
						var position = this.getPosition();
						var url = 'https://map.kakao.com/link/map/' + place.id;
						window.open(url, '_blank');
					});
				}

				function setBounds() {
					map.setBounds(bounds, 1000, 1000, 1000, 1000);
					var newLevel = map.getLevel() + 1; // 현재 확대 수준에서 1을 빼서 덜 확대된 영역으로 설정
				    map.setLevel(newLevel);
				}
				
				// 주소 검색 함수 (Promise 활용)
		        function searchStadium(stadiumName) {
		            return new Promise(function(resolve, reject) {
		                var ps = new kakao.maps.services.Places();

		                ps.keywordSearch(stadiumName, function(data, status) {
		                    if (status === kakao.maps.services.Status.OK) {
		                        var coords = new kakao.maps.LatLng(data[0].y, data[0].x);
		                        var marker = new kakao.maps.Marker({
		                            position: coords,
		                            map: map
		                        });
		                        map.setCenter(coords);
		                        resolve(data[0].address_name);
		                    } else {
		                        reject("주소 검색 실패, 정확한 위치는 카카오맵에서 검색해주세요!");
		                    }
		                });
		            });
		        }
		        
		        // 주소 복사 함수
		        function copyAddress() {
		            searchStadium(inputData[0]).then(function(address) {
		                var tempInput = document.createElement("input");
		                tempInput.style = "position: absolute; left: -1000px; top: -1000px";
		                tempInput.value = address;
		                document.body.appendChild(tempInput);
		                tempInput.select();
		                document.execCommand("copy");
		                document.body.removeChild(tempInput);
		                alert("주소가 복사되었습니다.");
		            }).catch(function(error) {
		                alert(error);
		            });
		        }
		        
		     // 현재 페이지 URL 공유 함수
		        function sharePage() {
		            var currentURL = window.location.href;
		            var tempInput = document.createElement("input");
		            tempInput.style = "position: absolute; left: -1000px; top: -1000px";
		            tempInput.value = currentURL;
		            document.body.appendChild(tempInput);
		            tempInput.select();
		            document.execCommand("copy");
		            document.body.removeChild(tempInput);
		            alert("현재 페이지 URL이 복사되었습니다: ");
		        }
			</script>


			<!-- 매칭 정보 ( 경기장, 주소 ) -->
			<h5 class="mt-3 font-weight-bold"><fmt:formatDate value="${matchView.reservation.matchDate}" pattern="yyyy.M.d(E)"/> ${matchView.reservation.matchTime}</h5>
			<div class="d-flex">
				<h5 class="font-weight-bold">${matchView.reservation.stadiumName}</h5>
			</div>
			
			<!-- 주소 복사하기 / 매칭글 공유하기 -->
			<div class="mt-3 mb-3">
				<button type="button" class="copyAddress btn btn-secondary btn-sm" onclick="copyAddress()">주소복사하기</button>
				<button type="button" class="shareAddress btn btn-secondary btn-sm ml-2" onclick="sharePage()">공유하기</button>
			</div>
			
			<!-- 팀명 / 팀프로필 -->
			<div class="d-flex align-items-center mt-3 mb-3">
				<img src="${matchView.team.profileImagePath}"  width="30" alt="팀로고">
				<div class="ml-2 font-weight-bold text-warning">${matchView.team.name}</div>
			</div>
			
			<!-- 매칭 내용 -->
			<textarea class="form-control font-weight-bold" rows="10" readonly>
${matchView.match.content}
			</textarea> <br>
			<div>ㆍ실력 : ${matchView.team.skill}</div><br>
			<div>ㆍ참가비 : ${matchView.match.price}</div><br>
			<div>ㆍ문의 : ${matchView.leader.phoneNumber}</div><br>
			
			
			<!--  댓글  -->
			<span class="font-weight-bold">댓글</span>	
			<br><br>
			<div class="d-flex">
				<input type="text" class="form-control w-75" id="content" name="content" placeholder="댓글을 입력하세요">
				<button type="submit" class="btn btn-info" id="commentSubmitBtn" name="commentSubmitBtn">댓글등록</button>
			</div>	
			
			<!-- 댓글 목록 -->
			<c:forEach items="${matchView.commentList}" var="comment">
				<div>
					<div class="d-flex mt-3">
						<fmt:parseDate value="${comment.comment.createdAt}" pattern="yyyy-MM-d'T'HH:mm:ss" var="parsedCreatedAt"/>
						
						<div class="mr-2">${comment.user.name} </div>
					</div>
					<div class="commentDate d-flex mt-1">
						<div><fmt:formatDate value="${parsedCreatedAt}" pattern="yyyy.M.d(E) HH:mm"/></div>
						<div class="ml-2"><a href="#">대댓글</a></div>
					</div>
					<div class="d-flex mt-2">
						<small class="text-secondary font-weight-bold">${comment.comment.content}</small>
						<c:if test="${comment.comment.userId == userId}">
							<small class="ml-3"><a href="#" class="deleteCommentBtn" data-comment-id="${comment.comment.id}">X</a></small>
						</c:if>
					</div>
					<hr>
				</div>
			</c:forEach>
			
			<!-- 목록 / 수정 / 삭제 버튼 -->
			<div class="d-flex justify-content-between mt-4 mb-4">
				<a href="/match/match_list_view" class="btn btn-secondary">목록</a>
				<c:if test="${matchView.reservation.possibleCancel}">
					<c:if test="${matchView.leader.role == '팀장' && teamId == matchView.team.id && userRole == '팀장'}">
						<div>
							<a href="/match/match_update_view?matchId=${matchView.match.id}" id="updateMatchBtn" class="btn btn-secondary mr-3">수정</a>
							<a href="#" class=" w-100" data-toggle="modal" data-target="#modal">
								<button type="submit" id="deleteMatch" class="btn btn-secondary" data-match-id = "${matchView.match.id}">삭제</button>
							</a>
						</div>
					</c:if>
				</c:if>
					<c:if test="${teamId != matchView.team.id && userRole == '팀장' && matchView.match.state == '모집중'}">
						<button type="submit" id="applyMatchBtn" class="btn btn-info w-50" data-match-id="${matchView.match.id}" data-team-id ="${teamId}" data-matchingteam-id = "${matchView.team.id}">신청하기</button>
					</c:if>
				
			</div>
			
			
	</div>
</div>    


<!-- Modal -->
<div class="modal fade" id="modal">
	<%-- modal-dialog-centered: 모달창 수직기준 가운데 위치--%>
	<div class="modal-dialog modal-sm modal-dialog-centered">
		<div class="modal-content text-center">
				<div class="modal-header text-center">
       				<h5 class="modal-title" id="staticBackdropLabel">매칭글 삭제</h5>
    			</div>
    			<div class="modal-body">
       			  	<span>매칭을  <b class="text-danger">삭제</b>하시겠습니까?</span> <br>
    			</div>
    			<div class="modal-footer d-flex justify-content-between">
		      		<div class="py-3">	
		      			<a href="#" id="deleteMatchBtn">삭제하기</a>
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
	
	// 댓글 등록
	$('#commentSubmitBtn').on('click', function(){
		let content = $('#content').val();
		let type = "매칭글";
		let matchId = ${matchView.match.id};
		
		$.ajax({
			type : "put"	
			,url : "/comment/" + type + "/" + matchId
			,data : {"content" : content}
			
			,success: function(data){
				if (data.code == 1){
					location.reload();
				} else{
					alert(data.errorMessage);
				}
			}
			
			, error: function(request, status, error){
				alert("댓글등록에 실패했습니다. 관리자 문의 바랍니다.");
			}
			
		});
	});
	
	//댓글 삭제
	$('.deleteCommentBtn').on('click', function(e){
		e.preventDefault();
		let commentId = $(this).data("comment-id");
		
		$.ajax({
			type: "delete"
			, url : "/comment/" + commentId
			
			, success: function(data){
				if (data.code == 1){
					location.reload();
				} else{
					alert(data.errorMessage);
				}
			}
		
			, error : function(request, status, error){
				alert("댓글 삭제 실패했습니다. 관리자 문의 바랍니다.")
			}
		});
	});
	
	
	$('#applyMatchBtn').on('click', function(){
		let teamId = $(this).data("team-id");
		let matchingTeamId = $(this).data("matchingteam-id");
		let matchId = $(this).data("match-id");
		
		$.ajax({
			
			type: "post"
			, url : "/matchRelation/create_matchRelation"
			, data : {"teamId":teamId, "matchingTeamId":matchingTeamId, "matchId":matchId}
		
		    , success : function(data){
		    	if (data.code == 1){
		    		alert("매칭 신청이 완료되었습니다.");
		    		location.href = "/main/my_page_view";
		    	} else{
		    		alert("매칭 신청이 실패하였습니다.");
		    	}
		    }
		    
		    , error : function(request, status, error){
		    	alert("관리자 문의 바랍니다.");
		    }
			
		});
	});
	
	/* 모달에 값 넘겨주기 */
	$('#deleteMatch').on('click', function(e){
		e.preventDefault(); 
		
		let matchId = $(this).data('match-id'); 
		
		// 한개인 모달 태그에(재활용) data-post-id를 심어줌
		$('#modal').data('match-id', matchId); // setting
	});
	
	
	/* 매칭글 삭제 */
	$('#modal #deleteMatchBtn').on('click', function(e){
		e.preventDefault();
		let matchId = $('#modal').data("match-id");
		
		$.ajax({
			type: "delete"
			,url : "/match/" + matchId
			
			,success: function(data){
				if(data.code == 1){
					alert("매칭글 삭제가 완료되었습니다.")
					location.href="/match/match_list_view";
				} else{
					alert(data.errorMessage);
				}
			}
			
			, error: function(request, ststus, error){
				alert("매칭글 삭제 실패했습니다. 관리자에게 문의해주세요.")
			}
		});
	});
	
});
</script>
    
    
    
    
    
    
    
    
    
    
    
    
