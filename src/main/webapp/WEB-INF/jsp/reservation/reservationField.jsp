<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex justify-content-center">
	<div class="w-50">
		<div class="d-flex justify-content-center mt-4">
			<h2>FIELD RESERVATION</h2>
		</div>
		<form id="reservationForm" method="post" action="/reservation/reservation_field">
			<!-- 날짜 -->
			<div class="mt-3">
				<label for="matchDate">날짜</label>
				<div class="input-group">
					<input type="text" class="form-control" id="matchDate" name="matchDate" placeholder="날짜를 입력하세요">
				</div>
			</div>

			<!-- 지역(region)  -->
			<div class="mt-3">
				<label for="region">지역</label>
				<select id="region" name="region" class="form-control" required>
					<option value="" disabled selected>지역 선택</option>
					<c:forEach items="${regionList}" var="region">
						<option value=${region}>${region}</option>
					</c:forEach>
				</select>
			</div>

			<!-- 경기장 선택 (해당 지역)  -->
			<div id="stadiumBox" class="mt-3 d-none">
				<label for="stadium">경기장</label>
				<select id="stadium" name="stadium" class="form-control" required>
					<option value="" disabled selected>경기장 선택</option>
				</select>
			</div>
			
			<!-- 경기장 지도 -->
			<!-- <div id="stadiumMap" class="mt-3">
				
			 	
			</div> -->
			<div id="map" style="width:500px;height:400px;"></div>
			<!-- services와 clusterer, drawing 라이브러리 불러오기 -->
			<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=dc8476ea9375c69674cea47fce478ccf&libraries=services,clusterer,drawing"></script>
			<script>
				var container = document.getElementById('map');
				var options = {
					center : new kakao.maps.LatLng(33.450701, 126.570667),
					level : 3
				};

				var map = new kakao.maps.Map(container, options);
			</script>

			<!-- 매칭 시간 선택  -->
			<div id="stadiumBox" class="mt-3">
				<label for="matchTime">시간</label>
				<select id="matchTime" name="matchTime" class="form-control" required>
					<option value="" disabled selected>시간선택</option>
					<option value="1" >6:00~08:00</option>
					<option value="2" >8:00~10:00</option>
					<option value="3" >10:00~12:00</option>
					<option value="4" >12:00~14:00</option>
					<option value="5" >14:00~16:00</option>
					<option value="6" >16:00~18:00</option>
					<option value="7" >18:00~20:00</option>
					<option value="8" >20:00~22:00</option>
					<option value="9" >22:00~24:00</option>
				</select>
			</div>
			
			
			<!-- 팀이름  -->
			<div class="mt-3">
				<label for="teamName">팀이름</label>
				<div>
					<input type="text" class="form-control" id="teamName" name="teamName"  value="${team.name}" disabled>
				</div>
			</div>
			
			
			<!--  가입 버튼 -->
			<button type="submit" id="reservationBtn" class="btn btn-secondary mt-4 w-100">예약하기</button>	
		</form>
	</div>
</div>

<script>
$(document).ready(function(){
	// 모든 데이터피커에 적용
    $.datepicker.setDefaults({
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'] // 요일을 한글로 변경
        , dateFormat: 'yy-mm-dd'
        ,showOtherMonths: true //이전 달과 다음 달 날짜를 표시
        ,showMonthAfterYear:true //연도 표시 후 달 표시
        ,maxDate: "+1M"
    });

    // 오늘 날짜로 이동하는 함수
    $.datepicker._gotoToday = function(id) {
        $(id).datepicker('setDate', new Date()).datepicker('hide').blur();
    };

    $('#matchDate').datepicker({
        showButtonPanel: true // 오늘 버튼 노출
        , minDate:0 // 오늘과 그 이후만 선택 가능
    });
    
    // 지역 select 선택 
    $('#region').on('change', function(e){
    	e.preventDefault();
    	$('#stadiumBox').removeClass('d-none');
    	let region = $('#region').val();
    	
    	
    	$.ajax({
    		type:"get"
    		, url : "/reservation/stadium_list"
    		, data : {"region": region}
    	
    		, success: function(data){
    			
    			$('#stadium').empty();
    			var option = $("<option></option>")
	        	 .attr("value", "")
	        	 .attr("disabled", true)
    			 .attr("selected", true)
	        	 .text("경기장 선택");
	        	$('#stadium').append(option);
    			
    	        // 받아온 데이터를 반복하면서 옵션을 생성하여 추가
    	        for (var i = 0; i < data.length; i++) {
    	        	var option = $("<option></option>")
    	        	 .attr("value", data[i])
    	        	 .text(data[i]);
    	        	$('#stadium').append(option);
    	        }
    		}
    		
    		, error: function(request, status, error){
				alert("목록 불러오기에 실패하였습니다. 관리자 문의 바랍니다.");
			}
    	})

    });
});
</script>
















