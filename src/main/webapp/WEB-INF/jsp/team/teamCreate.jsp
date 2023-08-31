<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 팀 생성 화면 -->

<div class="d-flex justify-content-center">
	<div class="w-50">
		<div class="d-flex justify-content-center mt-4">
			<h2>TEAM CREATION</h2>
		</div>
		<form id="createTeamForm" method="post" action="/team/create_team">
			<!-- 팀명 -->
			<label for="teamName">팀명</label>
			<div class="input-group">
				<input type="text" class="form-control" id="teamName" name="teamName" placeholder="팀명을 입력하세요">
				<div class="input-group-append">
					<button type="button" id="isDuplicatedBtn" class="btn btn-secondary">중복 확인</button>
				</div>
			</div>
			
			<%-- 팀명 체크 결과 --%>
			<%-- d-none 클래스: display none (보이지 않게) --%>
			<div>
				<div id="teamCheckDuplicated" class="small text-danger d-none">이미 사용중입니다.</div>
				<div id="teamCheckOk" class="small text-success d-none">사용 가능합니다.</div>				
			</div>
			
			
			<!-- 팀실력(skill)  -->
			<div class="mt-3">
				<label for="skill">팀 실력</label>
				<select id="skill" name="skill" class="form-control" required>
					<option value="" disabled selected>실력 선택</option>
					<option value="상">상</option>
					<option value="중상">중상</option>
					<option value="중">중</option>
					<option value="중하">중하</option>
					<option value="하">하</option>
				</select>
			</div>
			
			<!-- 활동 지역  -->
			<div class=" mt-3">
				<label for="activeArea">활동지역</label>
				<input type="text" class="form-control" id="activeArea" name="activeArea" placeholder="주요활동지역을 입력하세요">
			</div>
			
			<!-- 팀 소개  -->
			<div class="mt-3">
				<label for="introduce">소개</label>
				<textarea class="form-control" id="introduce" name="introduce" rows="6" placeholder="팀 소개"></textarea>
			</div>
			
			
			<!--  가입 버튼 -->
			<button type="submit" id="createTeamBtn" class="btn btn-secondary mt-4 w-100">팀만들기</button>	
		</form>
	</div>
</div>


<script>
$(document).ready(function(){
	
	// 팀명 중복확인
	$('#isDuplicatedBtn').on('click', function(){
		
		let teamName = $('#teamName').val().trim();
		
		if (!teamName){
			alert("팀명을 입력하세요")
			return false;
		}
		
		// 경고 문구 초기화 
		$('#teamCheckDuplicated').addClass('d-none');
		$('#teamCheckOk').addClass('d-none');
		
		$.ajax({
			
			url: "/team/is_duplicated_team"
			, data: {"teamName" : teamName}
		
			, success: function(data){
				if (data.isDuplicationTeam){
					$('#teamCheckDuplicated').removeClass('d-none');
				} 
				
				if (!data.isDuplicationTeam){
					$('#teamCheckOk').removeClass('d-none');
				}
			}
			
			, error: function(request, status, error){
				alert("중복확인에 실패했습니다. 관리자에게 문의 부탁드립니다.")
			}
		});
	});
	
	// 팀 만들기
	$('#createTeamForm').on('submit', function(e){
		e.preventDefault();
		
		let teamName = $('#teamName').val().trim();
		let skill = $('#skill').val();
		let activeArea = $('#activeArea').val().trim();
		let introduce = $('#introduce').val();
		
		if (!teamName){
			alert("팀명을 입력하세요.");
			return false;
		}
		
		
		if (!activeArea){
			alert("활동지역을 입력하세요.");
			return false;
		}
		
		if ($('#teamCheckOk').hasClass('d-none')) {
			alert("팀명 중복확인을 해주세요");
			return false;
		}
		
		let url = $(this).attr('action');
		console.log(url);
		let params = $(this).serialize(); 
		console.log(params);
		
		$.post(url, params)
		.done(function(data){
			if (data.code == 1){
				alert("★팀생성이 완료되었습니다★");
				location.href = "/team/team_list_view";
			} else{
				alert(data.errorMessage);
				location.href = "/main/my_page_view";
			}
		});
	});
});
</script>










