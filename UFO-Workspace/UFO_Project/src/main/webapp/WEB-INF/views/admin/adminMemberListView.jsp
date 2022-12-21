<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-1.11.1.min.js"; type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://bootswatch.com/5/lux/bootstrap.css">
<style>
	#content_container {
		margin-left : 8%;
		margin-right : 8%;
	}
    #content_container a {
    	text-decoration: none;
    	color: gray;
    }
    
    /* 관리자 프로필 효과 */
	#admin_profile {
		margin-top: 2%;
		margin-bottom: 2%;
	}
	
	/* 관리탭 효과 */
    #admin_mypage_navi>div {
    	display: inline-block;
    	width: 16%;
    	margin-top: 25px;
    	font-size: 20px;
    	padding: 10px;
		text-align: center;
    }
    #admin_mypage_navi {
    	border-bottom: 1px solid gray;
    }
    /*
    #admin_mypage_navi>div:active {
    	border-bottom:3px solid #64FFDA;
    }
    */
    #admin_mypage_navi a:hover {
    	color: white;
    }
    #selected_tab {
    	border-bottom:3px solid #64FFDA;
    }
    #selected_tab a {
    	color: white;
    	font-weight: bold;
    }
    
    /* 검색창 효과 */
    #member_searchForm {
    	margin-top: 50px;
    	margin-bottom: 50px;
    }
	#member_searchForm select, #member_searchForm input, #member_searchForm button {
		display: inline-block;
	}
	#member_searchForm select {
		width: 100px;
		height: 50px;
		background-color: rgb(23, 26, 33);
		color: white;
	}
	#member_searchForm input {
		border: 1px solid gray;
		background-color: rgb(23, 26, 33);
		color: white;
	}
	
	/* 회원 리스트 효과 */
	#member_list>table {
		color: white;
	}
	#member_list th {
		font-size: 18px;
		text-align: center;
	}
	#member_list td {
		text-align: center;
		vertical-align : middle;
	}
	
	/* 버튼들 효과 */
	#member_list button {
		height: 40px;
		padding-right: 15px;
		padding-left: 15px;
	}
	#member_searchForm button, #member_list button {
		border: 1px solid #64FFDA;
		background-color: rgb(23, 26, 33);
		color: white;
	}
	#member_searchForm button:hover, #member_list button:hover {
		background-color: #64FFDA;
    	border: 1px solid #64FFDA;
    	color: gray;
	}
	
	/* 페이징바 효과 */
	#pagingArea {
		width:fit-content; margin:auto;
	}
	.page-link {
		background-color: rgb(23, 26, 33);
		color: white;
	}
	.page-link:hover {
		background-color: #64FFDA;
    	border: 1px solid #64FFDA;
    	color: gray;
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
        	
	        <table id="admin_profile">
	            <tr>
	                <td width="220"><img src="resources/image/member/profile0.png" width="170" height="170"></td>
	                <td width="380" style="font-size:50px; font-weight:900;">관리자</td>
	            </tr>
	        </table>
	    	
		    <div id="admin_mypage_navi">
		        <div id="selected_tab"><a href="admin_list.me">회원 관리</a></div>
		        <div><a href="">콘텐츠 관리</a></div>
		        <div><a href="commentList.ad">코멘트 관리</a></div>
		        <div><a href="adminPaymentList.ad">이용권 관리</a></div>       
		        <div><a href="reportedCommunity.ad">신고 관리</a></div>
		        <div><a href="admin_stat.st">통계 관리</a></div>
			</div>
			
			<div id="member_searchForm" align="center">
	            <form action="admin_search.me" method="get">
		            <select name="keyword">
			            <option value="" hidden disabled selected>SELECT</option>
			            <option value="userId">아이디</option>
			            <option value="userNickname">닉네임</option>
		            </select>
		            <input type="text" name="searchword" value="${ searchword }" class="form-control" placeholder="Search" style="width:200px;" required>
		            <button type="submit" class="btn btn-primary">SEARCH</button>
	            </form>  
          	</div>
          	<c:if test="${ not empty keyword }">
          		<script>
          			$(function() {
          				$("#member_searchForm option[value=${keyword}]").attr("selected", true);
          			});
          		</script>
          	</c:if>
          	
			<div id="member_list">
				<table class="table" align="center">
					<thead>
						<tr>
							<th width="5%">NO</th>
							<th width="20%">아이디</th>
							<th width="20%">닉네임</th>
							<th width="15%">가입일</th>
							<th width="10%">상태</th>
							<th width="18%"></th>
							<th width="12%"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="m" items="${ list }" >
							<tr>
								<td>${ m.userNo }</td>
								<td>${ m.userId }</td>
								<td>${ m.userNickname }</td>
								<td>${ m.userEnrollDate }</td>
								<td>${ m.userStatus }</td>
								<td><button onclick="resetPwd(${ m.userNo });">비밀번호 초기화</button></td>
								<td><button onclick="deleteMem(${ m.userNo });">탈퇴처리</button></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			<script>
			function resetPwd(userNo) {
				$.ajax({
					url : "admin_updatePwd.me",
					type : "post",
					data : { 
						userNo : userNo 
					},
					success : function(result) {
						
						console.log(result);
						if(result == "success") {
							
							alert("비밀번호 초기화에 성공했습니다.");
							location.href = "admin_list.me";
						} else {

							alert("비밀번호 초기화에 실패했습니다.");
						}
					},
					error : function() {
						console.log("ajax 통신 실패!");
					}
				});
			}
			
			function deleteMem(userNo) {
				$.ajax({
					url : "admin_delete.me",
					type : "post",
					data : { 
						userNo : userNo 
					},
					success : function(result) {
						
						console.log(result);
						if(result == "success") {
							
							alert("회원탈퇴에 성공했습니다.");
							location.href = "admin_list.me";
						} else {

							alert("회워탈퇴에 실패했습니다.");
						}
					},
					error : function() {
						console.log("ajax 통신 실패!");
					}
				});
			}
			</script>
			
			<div id="pagingArea">
                <ul class="pagination" align="center">
                	<c:choose>	
                		<c:when test="${ pi.currentPage eq 1 }">
                			<c:choose>
                				<c:when test="${ empty keyword }">
                					<li class="page-item"><a class="page-link" href="#">&laquo;</a></li>
                				</c:when>
                				<c:otherwise>
                					<li class="page-item"><a class="page-link" href="#">&laquo;</a></li>
                				</c:otherwise>
                			</c:choose>
                		</c:when>
                		<c:otherwise>
                			<c:choose>
                				<c:when test="${ empty keyword }">
                					<li class="page-item"><a class="page-link" href="admin_list.me?cpage=${ pi.currentPage - 1 }">&laquo;</a></li>
                				</c:when>
                				<c:otherwise>
                					<li class="page-item"><a class="page-link" href="admin_list.me?cpage=${ pi.currentPage - 1 }&keyword=${ keyword }&searchword=${ searchword }">&laquo;</a></li>
                				</c:otherwise>
                			</c:choose>
                		</c:otherwise>
                	</c:choose>

					<c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }">
						<c:choose>
							<c:when test="${ empty keyword }">
								<li class="page-item"><a class="page-link" href="admin_list.me?cpage=${ p }">${ p }</a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a class="page-link" href="admin_list.me?cpage=${ p }&keyword=${ keyword }&searchword=${ searchword }">${ p }</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					
					<c:choose>
						<c:when test="${ pi.currentPage eq pi.maxPage }">
							<c:choose>
								<c:when test="${ empty keyword }">
									<li class="page-item"><a class="page-link" href="#">&raquo;</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link" href="#">&raquo;</a></li>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${ empty keyword }">
									<li class="page-item"><a class="page-link" href="admin_list.me?cpage=${ pi.currentPage + 1 }">&raquo;</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link" href="admin_list.me?cpage=${ pi.currentPage + 1 }&keyword=${ keyword }&searchword=${ searchword }">&raquo;</a></li>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
                </ul>
            </div>
            
        </div>


		<!-- 푸터 영역 -->
        <jsp:include page="../common/footer.jsp" />

    </div>
    
</body>
</html>