<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex justify-content-center">
	<div class="w-75">
		<div class="d-flex justify-content-center mt-4">
			<h2>TEAM LIST</h2>
		</div>
		
		<div>
			<table class="table table-bordered">
				<thead  class="table-secondary">
					<tr class="text-center">
						<th>팀명</th>
						<th>실력</th>
						<th>활동지역</th>
						<th>팀원수</th>
						<th>가입</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${teamList}" var="team">
						<tr class="text-center">
							<td>${team.name}</td>
							<td>${team.skill}</td>
							<td>${team.activeArea}</td>
							<td>팀원수</td>
							<td>
								<c:if test="${userTeamId == null}">
									<button type="button" class="btn btn-warning btn-sm">가입신청</button>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>