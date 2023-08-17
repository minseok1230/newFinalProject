<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
	<div class="w-50">
		<div class="d-flex justify-content-center mt-4">
			<h2>MATCH MODIFY</h2>
		</div>
		
		<label for="stadium">나의 경기장</label>
		<div class="d-flex mb-4">
			<input type="text" id="stadium" name="stadium" class="form-control"  value="${matchView.reservation.stadiumName}" readonly>		
		</div>
		
		<label for="title">제목</label>
		<div class="d-flex mb-4">
			<input type="text" id="title" name="title" class="form-control"  value="${matchView.match.title}">		
		</div>
		
		<label for="teamName">팀이름</label>
		<div class="d-flex mb-4">
			<input type="text" id="teamName" name="teamName" class="form-control"  value="${matchView.team.name}" readonly>		
		</div>
		
		<label for="price">금액</label>
		<div class="d-flex mb-4">
			<div class="input-group">
				<input type="text" class="form-control" name="price" id="price" name="price" value="${matchView.match.price}">
				<span class="input-group-text">원</span>
			</div>	
		</div>
		
		<label for="content">내용</label>
		<textarea id="content" class="form-control" rows="10" name="content" placeholder="내용을 입력하세요">${matchView.match.content}</textarea>
		
		<br><br>
		
		<div class="d-flex justify-content-end">
			<button type="submit" id="updateMatchBtn" class="btn btn-primary mr-3" data-match-id="${matchView.match.id}">수정하기</button>
		</div>
	</div>
</div>

<script>
$(document).ready(function(){
	$('#updateMatchBtn').on('click', function(){
		let stadium = $('#stadium').val();
		let title = $('#title').val();
		let teamName = $('#teamName').val();
		let price = $('#price').val();
		let content = $('#content').val();
		let matchId = $(this).data('match-id');
		
		if (!title){
			alert("제목을 입력하세요.")
			return false;
		}
		if (!price){
			alert("금액을 입력하세요.")
			return false;
		}
		
		$.ajax({
			type: "put"
			,url: "/match/" + matchId
			,data: {"title":title, "price":price, "content":content}
		
			, success: function(data){
				if (data.code == 1){
					alert("수정되었습니다.");
					location.href = "/match/match_list_view";
				} else{
					alert(data.errorMessage);
				}
			}
			
			, error: function(request, status, error){
				alert("매칭글 수정 실패했습니다. 관리자 문의 바랍니다.");
			}
		});
		
	});
});
</script>












