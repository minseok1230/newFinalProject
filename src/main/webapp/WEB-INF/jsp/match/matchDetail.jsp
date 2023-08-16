<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="d-flex justify-content-center">
	<div class="w-75">
		<div class="d-flex justify-content-center mt-4">
			<h2>MATCHING </h2>
		</div>
			
			<!-- 경기장 지도 -->
			<div class="mt-3 d-flex justify-content-center w-100">
				<div id="map" style="width:1000px;height:400px;"></div>
			</div>
			<!-- services와 clusterer, drawing 라이브러리 불러오기 -->
			<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=dc8476ea9375c69674cea47fce478ccf&libraries=services,clusterer,drawing"></script>
			
			<script>
			
				let stadium = "${matchView.reservation.stadiumName}"
				var inputData = [ stadium ]

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
			</script>
			
			<!-- 매칭 정보 ( 경기장, 주소 ) -->
			<h5 class="mt-3 font-weight-bold"><fmt:formatDate value="${matchView.reservation.matchDate}" pattern="yyyy.M.d(E)"/> ${matchView.reservation.matchTime}</h5>
			<div class="d-flex">
				<h5 class="font-weight-bold">${matchView.reservation.stadiumName}</h5>
				<small class="mt-1 ml-2 font-weight-bold">(주소명입력)</small>
			</div>
			
			<!-- 주소 복사하기 / 매칭글 공유하기 -->
			<div class="mt-3">
				<button type="button" class="copyAddress btn btn-secondary btn-sm">주소복사하기</button>
				<button type="button" class="shareAddress btn btn-secondary btn-sm ml-2">공유하기</button>
			</div>
			
			<!-- 팀명 / 팀프로필 -->
			<div class="d-flex align-items-center mt-3">
				<img src="${matchView.team.profileImagePath}"  width="30" alt="팀로고">
				<div class="ml-2">${matchView.team.name}</div>
			</div>
			
			
			<!-- 매칭 내용 -->
			<div>
				${matchView.match.content}
			</div>
	</div>
</div>    
    
    
    
    
    
    
    
    
    
    
    
    
