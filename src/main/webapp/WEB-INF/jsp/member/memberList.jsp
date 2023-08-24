<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex justify-content-center">
	<div class="w-75">
		<div class="d-flex justify-content-center mt-4">
			<h2>TEAM MEMBER LIST</h2>
		</div>
		
		<div>
			<table class="table table-bordered">
				<thead>
					<tr class="text-center table-secondary">
						<th>이름</th>
						<th>전화번호</th>
						<th>생년월일</th>
						<th>포지션</th>
					</tr>
				</thead>
				<tbody>
						<tr class="text-center">
							<td>${leader.name}</td>
							<td>${leader.phoneNumber}</td>
							<td>${leader.birth}</td>
							<td>${leader.role} / ${leader.position}</td>
						</tr>
						<c:forEach items="${memberViewList}" var="memberView">
							<tr class="text-center">
								<td>${memberView.user.name}</td>
								<td>${memberView.user.phoneNumber}</td>
								<td>${memberView.user.birth}</td>
								<td>${memberView.user.position}</td>
							</tr>
						</c:forEach>
				</tbody>
			</table>
		</div>
		<c:if test="${userRole != '팀장'}">
		 	<button type="submit" id="quitTeamBtn" class="btn btn-danger mt-4 w-100" data-team-id="${userTeamId}" data-user-id="${userId}">탈퇴</button>
		</c:if>
	</div>
</div>

<script>
$(document).ready(function(){
	$('#quitTeamBtn').on('click', function(){
		
		let teamId = $(this).data("team-id");
		let userId = $(this).data("user-id");
		
		$.ajax({
			
			type: "delete"
			, url : "/member/" + teamId + "/" + userId
			
			, success : function(data){
				if (data.code == 1){
					alert("팀탈퇴에 성공하셨습니다.");
					location.href ="/main/main_view"
				}
			}
		
		    , error: function(request, status, error){
		    	alert("관리자 문의 바랍니다.");
		    }
			
		});
	});
});
</script>

















