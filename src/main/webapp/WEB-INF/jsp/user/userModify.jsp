<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="d-flex justify-content-center">
	<div class="w-50">
		<div class="d-flex justify-content-center mt-4">
			<h2>PROFILE MODIFY</h2>
		</div>
		
		<%-- 이미지가 있을때만 이미지 영역 추가 --%>
		 <c:if test="${not empty user.profileImagePath}">
			<div class="d-flex justify-content-center">
				<a id="NewfileUploadBtn"><img src="${user.profileImagePath}" alt="업로드 이미지" width="200"></a>
			</div>
		</c:if>
		
		<!-- 팀 로고 추가 -->
		<div class="content-bottom d-flex justify-content-center">
  			<div class="d-flex">
  				<!-- file 태그를 숨겨두고 이미지를 클릭하면 file 태그를 클릭한 것처럼 효과를 준다 -->
  				<input type="file" id="file" accept=".jpg, .jpeg, .png, .gif" class="d-none"> 
  				
  				<!-- 파일 업로드 버튼 -->
  				<c:if test="${empty user.profileImagePath}">
  					<a href="#" id="fileUploadBtn"><img width="150" src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-image-512.png"></a>
  				</c:if>	
  			</div>	
  		</div>
  		
  		
  		<!-- 업로드 된 임시 파일 이름 저장되는 곳 -->
  		<div id="fileName" class="text-center"></div> 
  		
			
		<!-- 비밀번호  -->
			<div class="mt-3">
				<label for="password">새로운 비밀번호</label>
				<input type="password" class="form-control" id="password" name="password" placeholder="비밀번호를 입력하세요">
				<div id="passwordCheck" class="small text-danger d-none">6자 이상 입력하세요.</div>
			</div>
			
			<!-- 비밀번호 확인  -->
			<div class=" mt-3">
				<label for="confirmPassword">새로운 비밀번호 확인</label>
				<input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="비밀번호를 입력하세요">
			</div>
			
			<!-- 이름  -->
			<div class="mt-3">
				<label for="name">이름</label>
				<input type="text" class="form-control" id="name" name="name" value="${user.name}" placeholder="이름을 입력하세요">
			</div>
			
			<!-- 휴대전화번호  -->
			<div class="mt-3">
				<label for="phoneNumber">휴대전화번호</label>
				<input type="text" class="form-control" id="phoneNumber" name="phoneNumber" value="${user.phoneNumber}" placeholder="휴대전화번호를 입력하세요 (ex. 010-1111-1111)">
				<div id="phoneNumberFormatCheck" class="small text-success d-none">휴대전화번호 형식이 맞지 않습니다.</div>	
			</div>
			
			<!-- 생년월일  -->
			<div class="my-3">
				<label for="birth">생년월일</label>
				<input type="text" class="form-control" id="birth" name="birth" value="${user.birth}" placeholder="생년월일을 입력하세요 (ex. 20021022)">
			</div>
			
			<!-- 주요 포지션 -->
			<div class="my-3">
				<label for="position">포지션</label>
				<select id="position" name="position" class="form-control" required>
					<option value="" disabled selected>${user.position}</option>
					<option value="ST">ST</option>
					<option value="LF">LF</option>
					<option value="RF">RF</option>
					<option value="CAM">CAM</option>
					<option value="CDM">CDM</option>
					<option value="CM">CM</option>
					<option value="CB">CB</option>
					<option value="RB">RB</option>
					<option value="LB">LB</option>
				</select>
			</div>
		
		
			<!--  가입 버튼 -->
			<button type="submit" id="updateUserBtn" class="btn btn-secondary mt-4 w-100" data-user-id="${user.id}">수정하기</button>	
	</div>
</div>

<script>
$(document).ready(function(){
	// 로고 없을때
	$('#fileUploadBtn').on('click', function(e) {
		e.preventDefault(); 
		$('#file').click(); 
	});
	
	
	// 로고 있을때
	$('#NewfileUploadBtn').on('click', function(e) {
		e.preventDefault(); 
		$('#file').click(); 
	});
	
	$('#file').on('change', function(e) {
		let fileName = e.target.files[0].name; 
		
		// 확장자 유효성 확인
		let ext = fileName.split(".").pop().toLowerCase();
		if (ext != "jpg" && ext != "png" && ext != "gif" && ext != "jpeg") {
			alert("이미지 파일만 업로드 할 수 있습니다.");
			$('#file').val("");  
			$('#fileName').text(''); 
			return;
		}
		
		// 유효성 통과한 이미지는 상자에 업로드 된 파일 이름 노출
		$('#fileName').text(fileName);
	});
	
	/* 프로필 수정*/
	
	$('#updateUserBtn').on('click', function(e){
		e.preventDefault();
		
		let name = $('#name').val().trim();
		let phoneNumber = $('#phoneNumber').val().trim();
		let birth = $('#birth').val().trim();
		let position = $('#position').val();
		let file = $('#file').val();
		let password = $('#password').val();
		let userId = $(this).data('user-id');
		
		
		
		if (!name){
			alert("이름을 입력하세요.");
			return;
		}
		
		if (!phoneNumber){
			alert("휴대전화번호를 선택하세요.");
			return;
		}
		
		if (!birth){
			alert("생년월일을 입력하세요.");
			return;
		}
		
		if (!position){
			alert("포지션을 선택하세요.");
			return;
		}
		
		// 파일이 업로드 된 경우 확장자 체크 
		if (file){
			let ext = file.split(".").pop().toLowerCase();
			if ($.inArray(ext, ['jpg', 'jpeg', 'gif', 'png']) == -1){
				alert("이미지 파일만 업로드 할 수 있습니다.");
				$('#file').val(""); // 파일을 비운다.
				return; 
			}
		}
		
		let formData = new FormData();
		formData.append("name", name);
		formData.append("phoneNumber", phoneNumber);
		formData.append("birth", birth);
		formData.append("position", position);
		formData.append("password", password);
		formData.append("file", $('#file')[0].files[0]);
		
		$.ajax({
			// request
			type: "put"
			, url : "/user/" + userId
			, data : formData
			, enctype: "multipart/form-data" // 파일 업로드를 위한 필수 설정
			, processData: false // 파일 업로드를 위한 필수 설정
			, contentType: false // 파일 업로드를 위한 필수 설정
			
			// response
			, success:function(data){
				if (data.code == 1){
					alert("프로필이 수정되었습니다.")
					location.reload(true);
				} else{
					alert(data.errorMessage);
				}
			}
			
			, error: function(request, status, error){
				alert("프로필 수정 실패했습니다. 관리자 문의 바랍니다.");
			}
		});
		
	});
});
</script>