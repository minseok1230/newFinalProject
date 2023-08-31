<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex justify-content-center">
	<div class="w-50">
		<div class="d-flex justify-content-center mt-4">
			<h2>TEAM MODIFY</h2>
		</div>
		
		<%-- 이미지가 있을때만 이미지 영역 추가 --%>
		<c:if test="${not empty myTeam.profileImagePath}">
			<div class="d-flex justify-content-center">
				<a id="NewfileUploadBtn"><img src="${myTeam.profileImagePath}" alt="업로드 이미지" width="200"></a>
			</div>
		</c:if>
		
		<!-- 팀 로고 추가 -->
		<div class="content-bottom d-flex justify-content-center">
  			<div class="d-flex">
  				<%-- file 태그를 숨겨두고 이미지를 클릭하면 file 태그를 클릭한 것처럼 효과를 준다 --%>
  				<input type="file" id="file" accept=".jpg, .jpeg, .png, .gif" class="d-none"> 
  				
  				<%-- 파일 업로드 버튼 --%>
  				<c:if test="${empty myTeam.profileImagePath}">
  					<a href="#" id="fileUploadBtn"><img width="150" src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-image-512.png"></a>
  				</c:if>	
  			</div>	
  		</div>
  		
  		
  		<%-- 업로드 된 임시 파일 이름 저장되는 곳 --%>
  		<div id="fileName" class="text-center"></div>
  		
  		
  		<!-- 팀명 -->
		<label for="teamName">팀명</label>
		<div class="input-group">
			<input type="text" class="form-control" id="teamName" name="teamName" placeholder="팀명을 입력하세요" value="${myTeam.name}">
			<div class="input-group-append">
				<button type="button" id="isDuplicatedBtn" class="btn btn-secondary"  data-team-name="${myTeam.name}">중복 확인</button>
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
			<select id="skill" name="skill" class="form-control">
				<option value="" disabled selected>${myTeam.skill}</option>
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
			<input type="text" class="form-control" id="activeArea" name="activeArea" placeholder="주요활동지역을 입력하세요" value="${myTeam.activeArea}">
		</div>
			
		<!-- 팀 소개  -->
		<div class="mt-3">
			<label for="introduce">소개</label>
			<textarea class="form-control" id="introduce" name="introduce" rows="6" placeholder="팀 소개">${myTeam.introduce}</textarea>
		</div>
		
		
		<!-- 수정/삭제 버튼 -->
		<div class="d-flex">
			<button type="submit" id="updateTeamBtn" class="btn btn-secondary mt-4 w-100" data-team-id="${userTeamId}">수정</button>	
			<%-- <a href="#" class=" w-100" data-toggle="modal" data-target="#modal">
				<button type="submit" id="more-btn" class="btn btn-danger mt-4 w-100" data-team-id="${myTeam.id}">삭제</button>	
			</a> --%>
		</div>
	</div>
</div>


<!-- Modal -->
<div class="modal fade" id="modal">
	<%-- modal-dialog-centered: 모달창 수직기준 가운데 위치--%>
	<div class="modal-dialog modal-sm modal-dialog-centered">
		<div class="modal-content text-center">
				<div class="modal-header text-center">
       				<h5 class="modal-title" id="staticBackdropLabel">팀삭제</h5>
    			</div>
    			<div class="modal-body">
       			  	<span>팀을 정말 <b class="text-danger">삭제</b>하시겠습니까?</span> <br>
       			  	<span>팀을 삭제하면 복구는 불가능 합니다.</span>
    			</div>
    			<div class="modal-footer d-flex justify-content-between">
		      		<div class="py-3">	
		      			<a href="#" id="deleteTeamBtn">삭제하기</a>
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
	
	/*  팀명 중복확인  */
	$('#isDuplicatedBtn').on('click', function(){
		
		let teamName = $('#teamName').val().trim();
		let originTeamName = $(this).data("team-name");
		
		// 경고 문구 초기화 
		$('#teamCheckDuplicated').addClass('d-none');
		$('#teamCheckOk').addClass('d-none');
		
		if (!teamName){
			alert("팀명을 입력하세요")
			return false;
		}
		
		if (teamName === originTeamName){
			$('#teamCheckOk').removeClass('d-none');
			return false;
		}
		
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
	
	
	/*  팀 수정  */
	$('#updateTeamBtn').on('click', function(e){
		e.preventDefault();
		
		let teamName = $('#teamName').val().trim();
		let skill = $('#skill').val();
		if (skill == null){
			skill = '${myTeam.skill}';
		}
		let activeArea = $('#activeArea').val().trim();
		let introduce = $('#introduce').val();
		let file = $('#file').val();
		let teamId = $(this).data('team-id');
		
		if (!teamName){
			alert("제목을 입력하세요.");
			return;
		}
		
		if (!skill){
			alert("실력을 선택하세요.");
			return;
		}
		
		if (!activeArea){
			alert("활동지역을 입력하세요.");
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
		formData.append("teamName", teamName);
		formData.append("skill", skill);
		formData.append("activeArea", activeArea);
		formData.append("introduce", introduce);
		formData.append("file", $('#file')[0].files[0]);
		
		$.ajax({
			// request
			type: "put"
			, url : "/team/" + teamId
			, data : formData
			, enctype: "multipart/form-data" // 파일 업로드를 위한 필수 설정
			, processData: false // 파일 업로드를 위한 필수 설정
			, contentType: false // 파일 업로드를 위한 필수 설정
			
			// response
			, success:function(data){
				if (data.code == 1){
					alert("팀정보가 수정되었습니다.");
					location.href = "/main/my_page_view";
				} else{
					alert(data.errorMessage);
				}
			}
			
			, error: function(request, status, error){
				alert("팀정보 수정 실패했습니다. 관리자 문의 바랍니다.");
			}
		});
		
	});
	
	$('#more-btn').on('click', function(e){
		e.preventDefault(); 
		
		let teamId = $(this).data('team-id'); 
		
		// 한개인 모달 태그에(재활용) data-post-id를 심어줌
		$('#modal').data('team-id', teamId); // setting
	});
	
	
	/*  팀삭제 *************보류************** */
	$('#modal #deleteTeamBtn').on('click', function(e){
		e.preventDefault();
		let teamId = $('#modal').data("team-id");
		
		$.ajax({
			type: "delete"
			,url : "/team/" + teamId
			
			,success: function(data){
				if(data.code == 1){
					alert("팀 삭제가 완료되었습니다.")
					location.href="/main/my_page_view";
				} else{
					alert(data.errorMessage);
				}
			}
			
			, error: function(request, ststus, error){
				alert("팀 삭제 실패했습니다. 관리자에게 문의해주세요.")
			}
		});
	});
	
});
</script>














