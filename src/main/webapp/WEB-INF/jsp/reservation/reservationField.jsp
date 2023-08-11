<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
	<div class="w-50">
		<div class="d-flex justify-content-center mt-4">
			<h2>FIELD RESERVATION</h2>
		</div>
		<form id="reservationForm" method="post" action="/reservation/reservation_field">
			<!-- 날짜 -->
			<div class="mt-3">
				<label for="Date">날짜</label>
				<div class="input-group">
					<input type="text" class="form-control" id="Date" name="Date" placeholder="날짜를 입력하세요">
				</div>
			</div>
			
			
			<!-- 경기장선택  -->
			<div class="mt-3">
				<label for="field">경기장</label>
				<div class="input-group">
					<input type="text" class="form-control" id="field" name="field" placeholder="경기장 선택">
					<div class="input-group-append">
						<button type="button" id="findField" class="btn btn-secondary">찾기</button>
					</div>
				</div>
			</div>
			
			<!-- 매칭 시간  -->
			<div class="mt-3">
				<label for="time">시간</label>
				<div>
					<input type="text" class="form-control" id="time" name="time" placeholder="시간을 선택하세요">
				</div>
			</div>
			
			<!-- 팀이름  -->
			<div class="mt-3">
				<label for="teamName">팀이름</label>
				<div>
					<input type="text" class="form-control" id="teamName" name="teamName" rows="6" placeholder="팀명">
				</div>
			</div>
			
			
			<!--  가입 버튼 -->
			<button type="submit" id="reservationBtn" class="btn btn-secondary mt-4 w-100">예약하기</button>	
		</form>
	</div>
</div>