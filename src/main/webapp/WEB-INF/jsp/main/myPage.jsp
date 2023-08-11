<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex justify-content-center">
	<div class="w-75">
		<div class="d-flex justify-content-start mt-4">
			<h2>MY PAGE</h2>
		</div>
		
		<!-- 개인 프로필 -->
		<div class="mt-3 ">
			<img src="${user.profileImagePath}" width="50" alt="프로필사진">
			<span class="ml-3 font-weight-bold">최민석 님(<small>alstjr0311@naver.com</small>)</span>
			<a href="/user/user_update_view" class="btn btn-sm btn-secondary ml-3"><small>프로필수정</small></a>
		</div>
		
		
		<!-- 팀 정보 -->
		<c:if test="${userTeamId != null}">
		<div class="mt-3">
				<img src="${team.profileImagePath}"  width="50" alt="팀로고">
				<span class="ml-3 font-weight-bold">안양축돌이</span>
				<a href="#" class="btn btn-sm btn-secondary ml-3"><small>팀원관리</small></a>
				<a href="/team/team_update_view" class="btn btn-sm btn-secondary ml-2"><small>팀수정</small></a>
			</div>
		</c:if>
		<br><br><br>
		
		<!-- 나의 경기장 예약 목록 (statium)-->
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
						<tr class="text-center">
							<td class="align-middle">23.08.9</td>
							<td class="align-middle">안양 비산 축구장</td>
							<td class="align-middle">20:00 ~ 22:00</td>
							<td class="align-middle">
								<button type="button" class="btn btn-danger btn-sm"><small>취소</small></button>
							</td>
						</tr>
				</tbody>
			</table>
		</div>
		<br><br><br>
		
		<!-- 나의 팀 매칭 목록 (match) -->
		<h5 class="font-weight-bold text-info">팀 매칭 목록</h5>
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
						<tr class="text-center">
							<td class="align-middle">(안양)20.07.31 비산 20:00 ~ 22:00</td>
							<td class="align-middle">안양 비산 축구장 매칭 구해요~~</td>
							<td class="align-middle">
								<button type="button" class="btn btn-info btn-sm"><small>수정</small></button>
								<button type="button" class="btn btn-info btn-sm"><small>매칭중</small></button>
							</td>
						</tr>
						<tr class="text-center">
							<td class="align-middle">(안양)20.07.31 석수 20:00 ~ 22:00</td>
							<td class="align-middle">재밌는 축구해요~~</td>
							<td class="align-middle">
								<button type="button" class="btn btn-success btn-sm"><small>매칭완료</small></button>
							</td>
						</tr>
						<tr class="text-center">
							<td class="align-middle">(과천)20.07.31 과천 20:00 ~ 22:00</td>
							<td class="align-middle">즐축~~</td>
							<td class="align-middle">
								<button type="button" class="btn btn-danger btn-sm"><small>경기완료</small></button>
								<button type="button" class="btn btn-success btn-sm"><small>평점주기</small></button>
							</td>
						</tr>
				</tbody>
			</table>
		</div>
		<br><br><br>
		
		<!-- 매칭 신청 목록 (match) -->
		<h5 class="font-weight-bold text-info">경기장 예약 목록</h5>
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
		
		<!-- 팀 가입 신청 목록 -->
		<h5 class="font-weight-bold text-info">경기장 예약 목록</h5>
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
						<tr class="text-center">
							<td class="align-middle">홍길동</td>
							<td class="align-middle">30</td>
							<td class="align-middle">CDM</td>
							<td class="align-middle">
								<button type="button" class="btn btn-info btn-sm"><small>수락</small></button>
							</td>
						</tr>
				</tbody>
			</table>
		</div>
		
		
	</div>
</div>
























