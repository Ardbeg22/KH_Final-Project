<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 신고 관리</title>

	<link href="resources/css/adminReportedCommunity.css" rel="stylesheet">

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
	<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
	<!--
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://bootswatch.com/5/lux/bootstrap.css">
-->
</head>
<body>
	<!-- 전체 영역 -->
    <div class="wrap">

		<!-- 플로팅 버튼 영역 -->
        <jsp:include page="../common/floatingButton.jsp" />

		<!-- 헤더 영역 -->
        <jsp:include page="../common/header.jsp" />
	
		
        <!-- 컨텐츠 영역 (개별 구현 구역) -->
        <div id="content_container">
        	
	        <table id="admin_profile">
	            <tr>
	                <td width="220"><img src="resources/image/user/profile/admin.png" width="170" height="170"></td>
	                <td width="380" style="font-size:50px; font-weight:900;">관리자</td>
	            </tr>
	        </table>
	    	
	    	<!-- !!! 본인이 맡은 탭 div에 id="selected_tab" 붙어녛기 !!!-->
		    <div id="admin_mypage_navi">
		        <div><a href="admin_list.me">회원 관리</a></div>
		        <div><a href="">콘텐츠 관리</a></div>
				<div><a href="commentList.ad">코멘트 관리</a></div>
		        <div><a href="">이용권 관리</a></div>       
		        <div id="selected_tab"><a href="reportedCommunity.ad">신고 관리</a></div>
		        <div><a href="admin_stat.st">통계 관리</a></div>
			</div>

			<!-- 이곳부터 본인 화면 구현 -->
			<div id="reportCommunityManagement">
				<br>
				<div id="reportCategory" name="reportCategory" onchange="changeSelect()">
					<div style="float : left;">
						<!-- 구현하는 페이지 option에 옵션 selected 넣을 것 -->
							<select id="reportPageTab" name="reportCategory" onchange="changeSelect()">
								<option value="community" name="community" selected>커뮤니티 글</option>
								<option value="communityReply" name="communityReply">커뮤니티 댓글</option>
								<option value="comment" name="comment">코멘트</option>
							</select>
						</div>
					<div style="text-align:right; color: white; font-size:bold;"><button type="button" onclick="processedCommunityList();">처리된 글 보기</button></div>
				</div>
				
					<div id="communityListAll">
						<br>
					<c:choose>
						<c:when test="${ not empty list }">
							<table id="communityTable">
								<thead>
									<tr id="community_head" class="line">
										<th class="community_head1" width="10%;">신고자</th>
										<th class="community_head1" width="10%;">작성일</th>
										<th class="community_head1" width="10%;">글번호</th>
										<th class="community_head1" width="25%;">글제목</th>
										<th class="community_head1" width="20%;">신고사유</th>
										<th class="community_head1" width="8%;">신고처리</th>
										<th class="community_head1" width="17%;">삭제상태</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="c" items="${ list }">
										<tr class="personalCommunity">
											<td id="userMail">${ c.userId }</td>
											<td id="communityDate">${ c.comRegisterDate }</td>
											<td id="communityNo">${ c.comNo }</td>
											<td id="community_Title">${ c.comTitle }</td>
											<td id="reportedReason">${ c.reportReason }</td>
											<td id="reportedCount">${ c.reportStatus }</td>
											<td>
												<input type="hidden" id="communityReportNo" name="communityReportNo" value=${ c.reportNo }>
												<input type="hidden" id="communityNo" name="communityNo" value=${ c.comNo }>
												<button type="button" class="btn btn-danger" id="deleteReportedCommunity">삭제</button>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
		
							<br><br>
		
							<div id="pagingArea">
								<c:choose>
									<c:when test="${ pi.currentPage eq 1 }">
										<button type="button" onclick="location.href='#';" disabled>«</button>
									</c:when>
									<c:otherwise>
										<button type="button" onclick="location.href='reportedCommunity.ad?cpage=${ pi.currentPage - 1}';">«</button>
									</c:otherwise>
								</c:choose>
								
								<c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }" step="1">
									<button type="button" onclick="location.href='reportedCommunity.ad?cpage=${ p }';">${ p }</button>
								</c:forEach>
								
								<c:choose>
									<c:when test="${ pi.currentPage eq pi.maxPage }">
										<button type="button" onclick="location.href='#';" disabled>»</button>
									</c:when>
									<c:otherwise>
										<button type="button" onclick="location.href='reportedCommunity.ad?cpage=${ pi.currentPage + 1}';">»</button>
									</c:otherwise>
								</c:choose>
							</div>
							</c:when>
							<c:otherwise>
								조회된 내역이 없습니다.
							</c:otherwise>
							</c:choose>
								
				</div> <!-- communityListAll 영역 끝 -->

				<!-- 리스트 중 글제목 클릭시 게시글 상세 보기 -->
				<script> 
					$(function() {
						$("#community_Title").click(function() {
							
							location.href = "communityDetail.co?cno=" + $("#communityNo").text();
						});
					});
				</script>

				<!-- 신고관리 option category select시 url 이동 -->
				<script> 
					function changeSelect(){ 
	
						var selectList = document.getElementById("reportPageTab")
						
						if(selectList.options[selectList.selectedIndex].value == "community"){
							location.href = "reportedCommunity.ad";
						}
						if(selectList.options[selectList.selectedIndex].value == "communityReply"){
							location.href = "reportedReply.ad";
						}
						if(selectList.options[selectList.selectedIndex].value == "comment"){
							location.href = "reportedComment.ad";
						}
					}
				</script>
				
				
				<!-- 신고글 삭제 버튼 클릭시 삭제 -->
				<script>
					$("#communityListAll").on("click", "#deleteReportedCommunity", function(){
						
						const reportNo = $(this).siblings("#communityReportNo").val();
						const comNo = $(this).siblings("#communityNo").val();
						
						// console.log(reportNo);
						// console.log(comNo);
						
						if(confirm("해당 게시글을 삭제 처리하시겠습니까?")) {
							
							$.ajax({
								url : "deleteReportedCommunity.ad",
								type : "get",
								data : { reportNo : reportNo,
										 comNo : comNo
									   },
								success : function(result) {
									
									console.log(result);
									if(result > 0) {
										alert("성공적으로 처리되었습니다.");
										location.reload();
									}
									
								},
								error : function() {
									console.log("신고된 글 삭제용 ajax 통신 실패!");
								}
								
							});
						}
					
					});
					
					function processedCommunityList() {
						location.href="processedCommunityList.ad";
					}
				</script>

			</div>
			
        </div>

		<!-- 푸터 영역 -->
        <jsp:include page="../common/footer.jsp" />

    </div>
    
</body>
</html>