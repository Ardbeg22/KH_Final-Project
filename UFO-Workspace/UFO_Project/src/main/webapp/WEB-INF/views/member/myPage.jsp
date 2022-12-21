<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
<style>
	#content_container {
		margin-left : 8%;
		margin-right : 8%;
	}
    #content_container a {
    	text-decoration: none;
    	color: gray;
    }
    
    /* 회원 프로필 효과 */
	#user_profile {
		margin-top: 2%;
		margin-bottom: 2%;
	}
	#subscribe_tab {
		background-color: #3b3a40;
		height: 70px;
		text-align: center;
		line-height: 70px;
	}
	
	/* 마이페이지 탭 효과 */
	#mypage_navi>div {
    	display: inline-block;
    	width: 16.4%;
    	margin-top: 25px;
    	font-size: 20px;
    	padding: 10px;
		text-align: center;
    }
    #mypage_navi {
    	border-bottom: 1px solid gray;
    }
    /*
    #mypage_navi>div:active {
    	border-bottom:3px solid #64FFDA;
    }
    */
    #mypage_navi a:hover {
    	color: white;
    }
    #selected_tab {
    	border-bottom:3px solid #64FFDA;
    }
    #selected_tab a {
    	color: white;
    	font-weight: bold;
    }
    
    /* 회원 프로필, 비밀번호변경 버튼 효과 */
    #user_profile button, #pwd_btn {
    	border: 1px solid gray;
    	background-color: rgb(23, 26, 33);
    	color: gray;
    	height: 30px;
    	font-size: 17px;
    }
    #user_profile button:hover, #pwd_btn:hover {
    	background-color: #64FFDA;
    	border: 1px solid #64FFDA;
    }
</style>
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
	                <td rowspan="2" width="220">
	                	<c:choose>
	                		<c:when test="${ loginUser.userProfile eq 1 }">
	                			<img src="resources/image/member/profile1.png" width="170" height="170">
	                		</c:when>
	                		<c:when test="${ loginUser.userProfile eq 2 }">
	                			<img src="resources/image/member/profile2.png" width="170" height="170">
	                		</c:when>
	                		<c:when test="${ loginUser.userProfile eq 3 }">
	                			<img src="resources/image/member/profile3.png" width="170" height="170">
	                		</c:when>
	                		<c:when test="${ loginUser.userProfile eq 4 }">
	                			<img src="resources/image/member/profile4.png" width="170" height="170">
	                		</c:when>
	                		<c:when test="${ loginUser.userProfile eq 5 }">
	                			<img src="resources/image/member/profile5.png" width="170" height="170">
	                		</c:when>
	                		<c:when test="${ loginUser.userProfile eq 6 }">
	                			<img src="resources/image/member/profile6.png" width="170" height="170">
	                		</c:when>
	                		<c:when test="${ loginUser.userProfile eq 7 }">
	                			<img src="resources/image/member/profile7.png" width="170" height="170">
	                		</c:when>
	                		<c:when test="${ loginUser.userProfile eq 8 }">
	                			<img src="resources/image/member/profile8.png" width="170" height="170">
	                		</c:when>
	                		<c:when test="${ loginUser.userProfile eq 9 }">
	                			<img src="resources/image/member/profile9.png" width="170" height="170">
	                		</c:when>
	                		<c:otherwise>
	                			<img src="resources/image/member/profile10.png" width="170" height="170">
	                		</c:otherwise>
	                	</c:choose>
	                </td>
	                <td colspan="2" width="380" style="font-size:35px; font-weight:900;">${ loginUser.userNickname }</td>
	                <td>
	                	<button type="button" onclick="location.href='updateForm.me'">회원정보 수정</button>
	                	<button type="button" onclick="location.href='myPageupdatePwdForm.me'">비밀번호 변경</button>
	                	<button type="button" data-toggle="modal" data-target="#deleteModal">회원 탈퇴</button>
	                </td> 
	            </tr>
					
	            <tr>
	                <td style="font-size:20px; color:gray; font-weight:900;">나의 이용권</td>
	                <!-- 
			                    이용권 구독을 하지 않은 경우 '사용 중인 이용권이 없습니다'
			                    라는 멘트와 함께 이용권 구독 페이지로 가는 a태그
	                -->
					<c:choose>
						<c:when test="${empty payment}">
							<td style="font-size:20px; font-weight:900;">사용 중인 이용권이 없습니다</td>
							<td><button onclick="location.href='#'">이용권 구독</button></td>
						</c:when>
						<c:otherwise>
							<td style="font-size:20px; font-weight:900;">
								<c:choose>
									<c:when test="${fn:contains(payment.payOrderNo,'pay')}">일반이용권</c:when>
									<c:otherwise>정기구독권</c:otherwise>
								</c:choose>
							</td>
							<td style="font-size:18px; font-weight:600;">
								만료일: ${payment.payEndDate}
							</td>
						</c:otherwise>
					</c:choose>
	            </tr>
	        </table>
	    	
		    <!-- 회원탈퇴 버튼 클릭 시 보여질 Modal -->
		    <div class="modal fade" id="deleteModal">
		        <div class="modal-dialog modal-sm">
		            <div class="modal-content" style="background-color:black;">
		                <!-- Modal Header -->
		                <div class="modal-header">
		                    <h4 class="modal-title">회원탈퇴</h4>
		                    <button type="button" class="close" data-dismiss="modal">&times;</button>
		                </div>
		
		                <form action="delete.me" method="post">
		                    <!-- Modal body -->
		                    <div class="modal-body">
		                        <div align="center">
		                            탈퇴 후 복구가 불가능합니다. <br>
		                            정말로 탈퇴 하시겠습니까? <br>
		                        </div>
		                        <br>
		                            <label for="userPwd" class="mr-sm-2">Password : </label>
		                            <input type="password" class="form-control mb-2 mr-sm-2" placeholder="Enter Password" id="userPwd" name="userPwd"> <br>
		                    		<input type="hidden" name="userNo" value="${ loginUser.userNo }">
		                    </div>
		                    <!-- Modal footer -->
		                    <div class="modal-footer" align="center">
		                        <button type="submit" class="btn btn-danger">탈퇴하기</button>
		                    </div>
		                </form>
		            </div>
		        </div>
		    </div>
			
			<c:if test="${empty payment}">
		    <!-- 이용권 구독하지 않은 경우만 나타는 구독 유도탭 -->
		    <div align="center" id="subscribe_tab">이용권을 구독하고 인기 TV프로그램과 다양한 영화를 자유롭게 시청하세요!  이용권 구독하기></div>
			</c:if>
		    <div id="mypage_navi">
		        <div><a href="">시청 내역</a></div>
		        <div><a href="">볼래요</a></div>
		        <div><a href="myPayment.me">이용권 내역</a></div>       
		        <div><a href="myComment.me">별점 및 코멘트 내역</a></div>
		        <div><a href="myCommunityList.me">커뮤니티 글 내역</a></div>
		        <div><a href="myCommunityReplyList.me">커뮤니티 댓글 내역</a></div>
			</div>
        </div>

		<!-- 푸터 영역 -->
        <jsp:include page="../common/footer.jsp" />

    </div>

</body>
</html>