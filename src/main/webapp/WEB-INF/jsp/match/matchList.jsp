<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="d-flex justify-content-center">
	<div class="w-75">
		<div class="d-flex justify-content-center mt-4">
			<h2>MATCHING LIST</h2>
		</div>
		
		<div>
			<table class="table table-bordered">
				<thead  class="table-secondary">
					<tr class="text-center">
						<th>Game.</th>
						<th>Title.</th>
						<th>Team</th>
						<th>Price.</th>
						<th>State</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${matchViewList}" var="matchView">
						<tr class="text-center">
							<td>${matchView.reservation.region} ${matchView.reservation.stadiumName}<br> <fmt:formatDate value="${matchView.reservation.matchDate}" pattern="yyyy.M.d(E)"/> ${matchView.reservation.matchTime}</td>
							<td>${matchView.match.title }</td>
							<td>${matchView.team.name}</td>
							<td>${matchView.match.price }</td>
							<td><button type="button" class="joinTeamBtn btn btn-warning btn-sm">${matchView.match.state}</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>