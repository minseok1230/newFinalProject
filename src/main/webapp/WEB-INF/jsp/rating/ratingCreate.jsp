<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<br><br>
<div class="d-flex justify-content-center  h-100">
		<div class="w-75">
			<h1 class="text-center exo font-weight-bold mt-5">${oppositeTeam.name}</h1>
			
			<div class="text-center mb-5">
				<img src="/static/image/main/empty_star.png" class="empty1"  width="100" height="100">
				<img src="/static/image/main/full_star.png" class="full1 d-none" width="100" height="100">
				<img src="/static/image/main/empty_star.png" class="empty2" width="100" height="100">
				<img src="/static/image/main/full_star.png" class="full2 d-none" width="100" height="100">
				<img src="/static/image/main/empty_star.png" class="empty3" width="100" height="100">
				<img src="/static/image/main/full_star.png" class="full3 d-none" width="100" height="100">
				<img src="/static/image/main/empty_star.png" class="empty4 " width="100" height="100">
				<img src="/static/image/main/full_star.png" class="full4 d-none" width="100" height="100">
				<img src="/static/image/main/empty_star.png" class="empty5" width="100" height="100">
				<img src="/static/image/main/full_star.png" class="full5  d-none" width="100" height="100">
			</div>
			
				<div class="d-flex justify-content-center mt-3">
           			<button class="btn btn-warning text-center" id="removeAll">초기화</button>
       			</div>
				<button class="btn btn-warning w-100 mt-3" id="admitRatingBtn" data-team-id="${oppositeTeam.id}" data-matchedteam-id="${matchedTeamId}"  data-match-id="${matchId}"}>평점등록</button>
		</div>
			
</div>

<script>
$(document).ready(function(){
    $('#removeAll').on('click', function(){
        for (var i = 1; i <= 5; i++) {
            let full = '.full' + i;
            let empty = '.empty' + i;
            $(full).addClass("d-none");
            $(empty).removeClass("d-none");
        }
    });

    $('.empty1, .empty2, .empty3, .empty4, .empty5').on('click', function(){
         let index = parseInt($(this).attr("class").match(/\d+/)[0]);
         for (var j = 1; j <= index; j++) {
             let full = '.full' + j;
             let empty = '.empty' + j;
             $(full).removeClass("d-none");
             $(empty).addClass("d-none");
         }
     });
    
    $('#admitRatingBtn').on('click', function(){
    	let count = 0;
    	for (let i = 1; i <= 5; i++){
    		let full = '.full' + i;
    		if (!$(full).hasClass("d-none")){
    			count++;
    		}
    	}
		
    	let teamId = $(this).data("team-id");
    	let matchedTeamId = $(this).data("matchedteam-id");
    	let matchId = $(this).data("match-id");
    	
    	$.ajax({
    		type: "post"
    		, url : "/rating/create_rating"
    		, data : {"count" : count, "teamId" : teamId, "matchedTeamId" : matchedTeamId, "matchId":matchId}
    	
    		,success : function(data){
    			if (data.code == 1){
    				alert("성공");
    			} else if (data.code == 300){
    				alert("이미 완료하였습니다.");
    			} else {
    				alert(data.errorMessage);
    			}
    		}
    		
    		, error : function(request , status, error){
    			alert("관리자 문의 바랍니다.");
    		}
    	});
    });
    
    
});
</script>


















