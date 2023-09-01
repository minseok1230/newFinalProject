<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex justify-content-center">
	<div class="w-50">
		<div class="d-flex justify-content-center mt-4">
			<h2>FIELD RESERVATION</h2>
		</div>
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
			<div class="mt-3 d-flex justify-content-center">
				<div id="map" class="d-none" style="width:500px;height:400px;"></div>
			</div>
			<!-- services와 clusterer, drawing 라이브러리 불러오기 -->
			<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=dc8476ea9375c69674cea47fce478ccf&libraries=services,clusterer,drawing"></script>
			
			<script>
				$('#stadium').on('change', function(){
					$('#map').removeClass('d-none');
					let stadium = $('#stadium').val();
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
					var newLevel = map.getLevel() +2; // 현재 확대 수준에서 1을 빼서 덜 확대된 영역으로 설정
				    map.setLevel(newLevel);
				}
				});
			</script>

			<!-- 매칭 시간 선택  -->
			<div id="stadiumBox" class="mt-3">
				<label for="matchTime">시간</label>
				<select id="matchTime" name="matchTime" class="form-control" required>
					<option value="" disabled selected>시간선택</option>
					<option value="06:00~08:00" >[1] 06:00~08:00</option>
					<option value="08:00~10:00" >[2] 08:00~10:00</option>
					<option value="10:00~12:00" >[3] 10:00~12:00</option>
					<option value="12:00~14:00" >[4] 12:00~14:00</option>
					<option value="14:00~16:00" >[5] 14:00~16:00</option>
					<option value="16:00~18:00" >[6] 16:00~18:00</option>
					<option value="18:00~20:00" >[7] 18:00~20:00</option>
					<option value="20:00~22:00" >[8] 20:00~22:00</option>
					<option value="22:00~24:00" >[9] 22:00~24:00</option>
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
			<button type="submit" id="kakaoPayBtn" class="btn btn-warning mt-4 w-100">결제하기</button>	
			<button type="submit" id="checkPayBtn" class="btn btn-warning mt-4 w-100 d-none" disabled>결제완료</button>	
			<button type="submit" id="reservationBtn" class="btn btn-secondary mt-4 mb-4 w-100">예약하기</button>	
			
		
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
        , minDate:1 // 오늘과 그 이후만 선택 가능
    });
    
    /* 지역 select 선택  */ 
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
    
    /* 예약하기 버튼 */
    $('#reservationBtn').on('click', function(){
    	let matchDate = $('#matchDate').val().trim();
    	let matchDateToJavaScriptDateS = new Date($('#matchDate').val().trim());  // 입력된 날짜를 JavaScript Date 객체로 변환
        let currentDate = new Date();  // 현재 날짜를 가져옴
        
    	let region = $('#region').val();
    	let stadium = $('#stadium').val();
    	let matchTime = $('#matchTime').val();
    	let teamName = $('#teamName').val();
    	
    	if ($('#checkPayBtn').hasClass("d-none")){
    		alert("결제를 진행해주세요.");
    		return false;
    	}

        // 입력된 날짜가 비어있거나 오늘 날짜보다 이전인 경우
    	// 입력된 날짜가 비어있거나 오늘 날짜보다 이전인 경우
        if (!matchDate || matchDate < currentDate){
            alert("날짜를 선택해주세요");
            return false;
        }
        
        if (matchDateToJavaScriptDateS < currentDate){
        	alert("내일 날짜부터 예약가능합니다.");
        	return false;
        }
    	
    	if (!region){
    		alert("지역을 선택해주세요");
    		return false;
    	}
    	if (!stadium){
    		alert("경기장을 선택해주세요");
    		return false;
    	}
    	if (!matchTime){
    		alert("시간을 선택해주세요");
    		return false;
    	}
    	
    	 $.ajax({
    		
    		type: "post"
    		, url : "/reservation/create_reservation"
    		, data : {"matchDate":matchDate,"region":region,"stadium":stadium,"matchTime":matchTime,"teamName":teamName,}
    	
    		,success: function(data){
    			if (data.code == 1){
    				alert("경기장 예약이 완료되었습니다!!");
    				location.href = "/main/my_page_view";
    			} else{
    				alert(data.errorMessage);
    				location.reload(true);
    			}
    		}
    		
    		, error: function(request, status, error){
				alert("경기장 예약에 실패했습니다. 관리자 문의 바랍니다.");
			}
    	}); 
    });
    
    
    // 카카오 결제 
    $('#kakaoPayBtn').on('click', function(){
    	
    	let teamId = ${team.id};
       	let matchDate = $('#matchDate').val();
        let region = $('#region').val();
        let stadiumName = $('#stadium').val();
        let matchTime = $('#matchTime').val();
        
        $('#kakaoPayBtn').removeClass('d-none');
	    $('#checkPayBtn').addClass('d-none');
        
        if (!matchDate || !region || !stadium || !matchTime){
        	alert("날짜, 지역, 경기장, 시간을 모두 선택해주세요.");
            return false;
        }
    	
        $.ajax({
        	type : "post"
        	, url : "/payment/check"
        	, data : {"teamId":teamId,"region":region,"stadiumName":stadiumName,"matchTime":matchTime,"matchDate":matchDate}
        
        	, success : function(data){
        		
        		if (data.duplicate){
        			alert("다른 팀이 이미 결제중입니다. 다른 시간을 이용해주세요.");
        			location.reload(true);
        		}
        		// DB에 존재할 경우 (결제가 된 경우)
        		if (data.valid){
        			alert("이미 결제가 완료되었습니다. 예약을 진행해주세요.");
        			$('#kakaoPayBtn').addClass('d-none');
      		        $('#checkPayBtn').removeClass('d-none');
      		        $('#region').prop('disabled', true);
      		        $('#stadium').prop('disabled', true);
      		        $('#matchTime').prop('disabled', true);
      		        $('#matchDate').prop('disabled', true);
        		} else{
        			
        			//결제 요청
        			IMP.init('imp08002543'); /*iamport 대신 자신의 "가맹점 식별코드"를 사용*/
        			  
        			IMP.request_pay({
        			  pg: "kakaopay",
        			  pay_method: "card",
        			  merchant_uid : 'merchant_'+new Date().getTime(),
        			  name : '결제테스트',
        			  amount : 1,
        			  buyer_email : '${user.email}',
        			  buyer_name : '${user.name}',
        			  buyer_tel : '${user.phoneNumber}',
        			  buyer_date : 'matchDate',
        			  buyer_region : 'region',
        			  buyer_stadium : 'stadium',
        			  buyer_matchTime : 'matchTime',
        			  buyer_teamName : 'teamName',
        			}, function (rsp) { // callback
        			    if (rsp.success) {
        			      // 결제 성공 시 로직,
        			      // db 저장 하고 넘기자 
        			      $.ajax({
        			    	  type : "post"
        			          , url : "/payment/add"
        			          , data : {"teamId":teamId,"region":region,"stadiumName":stadiumName,"matchTime":matchTime,"matchDate":matchDate}
        			      
        			      	  , success : function(data){
        			      		  if (data.code == 1){
        			      			alert("결제성공")
        	        			      $('#kakaoPayBtn').addClass('d-none');
        	        			      $('#checkPayBtn').removeClass('d-none');
        	        			      $('#region').prop('disabled', true);
        	            		      $('#stadium').prop('disabled', true);
        	            		      $('#matchTime').prop('disabled', true);
        	            		      $('#matchDate').prop('disabled', true);
        			      		  } else{
        			      			  alert(data.errorMessage);
        			      		  }
        			      	  }
        			      	  
        			      	  , error: function(request, status, error) {
                                  alert("데이터 저장 실패");
                              }
        			      });
        			      
        			    } else {
        			      // 결제 실패 시 로직,
        			      alert("결제실패 : " + rsp.error_msg)
        			      location.reload(true);
        			    }
        			 });
        		}
        	}
        	
        	, error : function(request, status, error){
        		alert("[결제오류] 관리자에게 문의 바랍니다.");
        	}
        	
        });
    });

    
    
    
});
</script>
















