<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
	<div class="w-75">
		<div class="d-flex  mt-5 mb-4">
			<%-- <h3 class="font-weight-bold ">${boardDetail.title}</h3> --%>
			<input type="text" id="subject" class="form-control" placeholder="제목을 입력하세요" value="${boardDetail.title}">		
		</div>
			
		<%-- <div class="form-control h-50">
			${boardDetail.content}
		</div> --%>
		<textarea id="content" class="form-control" rows="10" placeholder="내용을 입력하세요">${boardDetail.content}</textarea>
		
		<br><br>
		
		<div class="d-flex justify-content-end">
			<button type="submit" id="updateBoardBtn" class="btn btn-primary mr-3">수정</button>
		</div>
	</div>
</div>