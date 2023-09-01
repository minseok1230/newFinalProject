<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="d-flex justify-content-center">
	<div class="w-50">
		<div class="d-flex justify-content-center mt-4">
			<h2>MATCH ENROLLMENT</h2>
		</div>
		<form id="matchUpForm" method="post" action="/match/create_match">
			<!-- 경기장 선택 -->
			<div class="mt-3">
				<label for="reservationId">나의 경기장</label> 
				<select id="reservationId" name="reservationId" class="form-control" required>
						<option value="" disabled selected>경기장선택</option>
						<c:forEach items="${matchViewList}" var="matchView">
							<%-- <fmt:parseDate value="${reservation.matchDate}" pattern="EEE MMM dd HH:mm:ss z yyyy" var="parsedMatchDate"/> --%>
							
							<option value="${matchView.reservation.id}" ><fmt:formatDate value="${matchView.reservation.matchDate}" pattern="yyyy.M.d(E)"/> ${matchView.reservation.matchTime} ${matchView.reservation.region} ${matchView.reservation.stadiumName}</option>
						</c:forEach>
				</select>
			</div>
			
			<!-- 매칭글 -->
			<div class="mt-3">
				<label for="title">제목</label> 
				<input type="text" class="form-control" id="title" name="title" placeholder="제목을 입력하세요">
			</div>
			
			<!-- 팀이름 -->
			<div class="mt-3">
				<label for="teamName">팀이름</label> 
				<input type="text" class="form-control" id="teamName" name="teamName"  value="${team.name}" readonly>
			</div>
			
			<!-- 금액 -->
			<div class="mt-3">
				<label for="price">금액</label> 
				<div class="input-group">
					<input type="text" class="form-control" id="price" name="price" placeholder="금액을 입력하세요">
					<span class="input-group-text">원</span>
				</div>
			</div>
			
			<!-- 내용 -->
			<div class="mt-3">
				<label for="content">내용</label> 
				<textarea class="form-control" id="content" name="content" placeholder="내용을 입력하세요" rows="10">
대상: 
	
준비물:
	
기타내용: 
				</textarea>
				
			</div>
			
			<!--  가입 버튼 -->
			<button type="submit" id="matchBtn" class="btn btn-secondary mt-4 mb-4 w-100">등록하기</button>
		</form>
	</div>
</div>


<script>
$(document).ready(function(){
	$('#matchUpForm').on('submit', function(e){
		e.preventDefault(); // 서브밋 기능 중단 // 화면 내려가기 방지
		
		let reservationId = $('#reservationId').val();
		let title = $('#title').val().trim();
		let teamName = $('#teamName').val();
		let price = $('#price').val();
		let content = $('#content').val();
		// 사용자 입력에서 줄바꿈 문자('\n')를 <br> 태그로 변경
        content = content.replace(/\n/g, '<br>');
		// 숫자 체크 
		let check = /^[0-9]+$/;
		
		if (!reservationId){
			alert("나의 경기장을 선택해주세요.")
			return false;
		}
		if (!title){
			alert("제목을 입력해주세요.")
			return false;
		}
		if (!price){
			alert("가격을 입력해주세요.")
			return false;
		}
		if (!content){
			alert("내용을 입력해주세요.")
			return false;
		}
		
		if (!check.test(price)){
			alert("숫자만 입력해주세요.");
			return false;
		}
		
		let url = $(this).attr('action');
		console.log(url);
		let params = $(this).serialize(); // 폼태그에 있는 name 속성-값들로 파라미터 구성
		console.log(params);
		
		$.post(url, params)
		.done(function(data){
			if (data.code == 1){
				alert("매칭등록이 완료되었습니다!");
				location.href = "/match/match_list_view";
			} else{
				alert(errorMessage);
			}
		});
		
		
	});
});

</script>















