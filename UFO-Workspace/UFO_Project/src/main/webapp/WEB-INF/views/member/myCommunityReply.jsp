<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마이 페이지 커뮤니티 글 내역</title>

	<link href="resources/css/myCommunityReply.css" rel="stylesheet">

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
	<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
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
        	
	        <table id="user_profile">
	            <tr>
	                <td rowspan="2" width="220"><img src="" width="170" height="170"></td>
	                <td colspan="2" width="380" style="font-size:35px; font-weight:900;">//이용자닉네임//</td>
	                <td>
	                	<button type="button" onclick="location.href='updateForm.me'">회원정보 수정</button>
	                	<button type="button" data-toggle="modal" data-target="#updatePwdForm">비밀번호 변경</button>
	                </td> 
	            </tr>
	            <tr>
	                <td style="font-size:20px; color:gray; font-weight:900;">나의 이용권</td>
	                <!-- 
			                    이용권 구독을 하지 않은 경우 '사용 중인 이용권이 없습니다'
			                    라는 멘트와 함께 이용권 구독 페이지로 가는 a태그
	                -->
	                <td style="font-size:20px; font-weight:900;">사용 중인 이용권이 없습니다</td>
	                <td><button onclick="location.href='##'">이용권 구독</button></td>
	            </tr>
	        </table>
	    	
		    <!-- 이용권 구독하지 않은 경우만 나타는 구독 유도탭 -->
		    <div align="center" id="subscribe_tab">이용권을 구독하고 인기 TV프로그램과 다양한 영화를 자유롭게 시청하세요!  이용권 구독하기></div>
		    
		    <!-- !!! 본인이 맡은 탭 div에 id="selected_tab" 붙어녛기 !!!-->
		    <div id="mypage_navi">
		        <div><a href="">시청 내역</a></div>
		        <div><a href="">볼래요</a></div>
		        <div><a href="">이용권 내역</a></div>       
		        <div class="rating_comment"><a href="myComment.me">별점 및 코멘트 내역</a></div>
		        <div class="rating_community"><a href="myCommunityList.me">커뮤니티 글 내역</a></div>
		        <div id="selected_tab" class="rating_communityReply"><a href="myCommunityReplyList.me">커뮤니티 댓글 내역</a></div>
			</div>

			<br>

			<div id="myCommunityReply">
				<table id="myCommunityReplyTable">
					<thead>
						<tr id="communityReply_head" class="line">
							<th class="communityReply_head1"width="10%;">선택</th>
							<th class="communityReply_head1" width="60%;">댓글내용</th>
							<th class="communityReply_head1" width="30%;">등록일</th>
						</tr>
					</thead>
					<tbody>
                              	<c:choose>
								<c:when test="${ empty list }">
									<tr>
										<td></td>
                                    		<td style=color:white;>조회된 게시글이 없습니다.</td>
                                    		<td></td>
                                    	</tr>
                                   </c:when>
                                   <c:otherwise>
                                   
										<c:forEach var="cr" items="${ list }">
											<tr class="personalCommunityReply">
												<td>
													<input type="checkbox" name="selectCommunityReply" id="selectCommunityReply" value="${ cr.comRplNo }">
												</td>
												<td id="comRplContent" style=color:white; >${ cr.comRplContent }</td>
												<td style=color:white;>${ cr.comRplRegisterDate }</td>
											</tr>
										</c:forEach>
                                   </c:otherwise>
                                </c:choose> 	
                                <tr><th colspan="4" style="text-align: right;"><button type="button" class="btn btn-danger" onclick="deleteCommunityReply();">삭제</button></th></tr>                            	
                              </tbody>
					</table>

					<br><br>
					<!-- 페이징 처리 시작 -->
					<div id="pagingArea">
						<c:choose>
							<c:when test="${ pi.currentPage eq 1 }">
								<button type="button" onclick="location.href='#';" disabled>«</button>
							</c:when>
							<c:otherwise>
								<button type="button" onclick="location.href='myCommunityReplyList.me?cpage=${ pi.currentPage - 1}';">«</button>
							</c:otherwise>
						</c:choose>
						
						<c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }" step="1">
							<button type="button" onclick="location.href='myCommunityReplyList.me?cpage=${ p }';">${ p }</button>
						</c:forEach>
						
						<c:choose>
							<c:when test="${ pi.currentPage eq pi.maxPage }">
								<button type="button" onclick="location.href='#';" disabled>»</button>
							</c:when>
							<c:otherwise>
								<button type="button" onclick="location.href='myCommunityReplyList.me?cpage=${ pi.currentPage + 1}';">»</button>
							</c:otherwise>
						</c:choose>
					</div>
					<!-- 페이징 처리 끝 -->

				<br><br>

			</div>

        </div>

	<!-- 체크박스 기능 -->
		<script>
		    		$(".rating_communityReply").on("click", function() {
		    		
			       		$("#loginUserForm").attr("action", "myCommunityReplyList.me").submit();
		    			
		    		});
		    		
		    		function deleteCommunityReply() {
						
						 // 체크박스의 댓글번호를 담을 배열 선언
						let communityReplyNoArr = [];
						
						// name 값이 selectCommunityReply 속성 취득 
						const selectCommunityReply = document.getElementsByName("selectCommunityReply");
						
						// 취득한 수만큼 반복 돌리기
						for (let i = 0; i < selectCommunityReply.length; i++) {
							
							// 속성 중에 체크된 항목이 있을 경우
							if(selectCommunityReply[i].checked == true) {
								communityReplyNoArr.push(selectCommunityReply[i].value);
							}
						}
						
						// 결과를 표시
						console.log(communityReplyNoArr); // 체크박스된 댓글의 번호가 잘 뽑힘!
						console.dir(communityReplyNoArr); // Array[선택된checkbox숫자]
						
						if(confirm("선택된 댓글을 삭제하시겠습니까?")) {
							
							$.ajax({
								url : "deleteCommunityReply.me",
								data : { communityReplyNoArr : communityReplyNoArr },
								type : "get",
								success : function(result) {
									if(result > 0) {
										alert("성공적으로 삭제되었습니다.");
										location.reload();
									}
								},
								error : function() {
									console.log("마이페이지 커뮤니티 댓글 삭제용 ajax 통신 실패!");
								}
							});
						}
					}
		</script>
		
		<!-- 푸터 영역 -->
        <jsp:include page="../common/footer.jsp" />

    </div>

</body>
</html>