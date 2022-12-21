<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ur Favorite OTT</title>

    <!-- jQuery 라이브러리 연결 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

    <!-- Bootstrap 프레임워크 연결 -->
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <!-- Popper JS -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

<style>

	/* div { border: 1px solid #64FFDA; } */

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
    	width: 19.7%;
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

	#content_container {
		margin-left : 8%;
		margin-right : 8%;
	}
    #content_container a {
    	text-decoration: none;
    	color: gray;
    }
    #community { 
        width: 100%; 
        height: 100%; 
        margin: auto;
        border: solid white;
        border-radius: 45px; 
        padding: 30px 30px;
    }

    #community>div { width: 100%; }

    #communityBtn { height: 10%;}
    #communityTable { height: 30%;}

    .table { text-align: center; height: 5px; margin-top: 5px;}

    thead>tr { font-weight: bolder; font-size: medium; }
    
    .form-group { margin: auto;}
	
	.table>tbody>tr {
        opacity: 0.7;
        cursor: pointer;
    }
  
	/* .community { height: auto; } */
	


	
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
					<td width="380" height="20"></td>
				</tr>
				<tr>
					<td width="380" style="font-size:50px; font-weight:900;">커뮤니티</td>
				</tr>
			</table>
				
			<!-- !!! 본인이 맡은 탭 div에 id="selected_tab" 붙어녛기 !!!-->
			
			<br><br><br><br><br>
				
			<div class="community">
				<div id="community">
					<div id="communityBtn"><div class="form-group row"></div></div>
						
					<div class="form-button" align="right">
						<!-- 로그인 후 상태일 경우만 보여지는 글쓰기 버튼 -->
						<c:if test="${ not empty loginUser }">
							<a href="communityEnrollForm.co" class="btn btn-info" style="color:white">글작성</a>
						</c:if>
					</div>

					<div id="communityTable" style="margin-top:10px;">
						<table class="table">
							<thead>
								<tr>
									<td style=color:white;>글번호</td>
									<td style=color:white;>글제목</td>
									<td style=color:white;>등록일</td>
									<td style=color:white;>작성자</td>
									<td style=color:white;>조회수</td>
								</tr>
							</thead>
							
							<tbody>
								<c:choose>
									<c:when test="${ empty list }">
										<tr>
											<td style=color:white; colspan="5">존재하는 게시글이 없습니다.</td>
										</tr>
									</c:when>
									<c:otherwise>
										<c:forEach var="c" items="${ list }">
										<tr>
											<td style=color:white;>${ c.comNo }</td>
											<td style=color:white;>${ c.comTitle }</td>
											<td style=color:white;>${ c.comRegisterDate }</td>
											<td style=color:white;>${ c.userNickname }</td>
											<td style=color:white;>${ c.comHit }</td>
										</tr>
										</c:forEach>
									</c:otherwise>
									</c:choose> 	                            	
							</tbody>
						</table>
								
						<c:if test="${ not empty list }">
							<script>
								$(function() {
									$(".table>tbody>tr").click(function() {
										
										location.href = "communityDetail.co?cno=" + $(this).children().eq(0).text();
									});
								});
							</script>
						</c:if>     
					</div>  
				
					<!-- 페이징 처리 시작 -->
					
					<br><br><br><br>   
					<div align="center">	
						<div id="pagingArea" style="width: 200px;">
							<ul class="pagination">
							   <c:choose>
								  <c:when test="${ pi.currentPage eq 1 }">
									 <c:choose>
										   <c:when test="${ empty condition }">
											   <li class="page-item disabled"><a class="page-link" href="#">Previous</a></li>
										   </c:when>
										   <c:otherwise>
											   <li class="page-item disabled"><a class="page-link" href="#">Previous</a></li>
										   </c:otherwise>
										</c:choose>
								  </c:when>
								  <c:otherwise>
									 <c:choose>
										<c:when test="${ empty condition }">
											<li class="page-item"><a class="page-link" href="communityList.co?cpage=${ pi.currentPage - 1 }">Previous</a></li>
										</c:when>
										<c:otherwise>
											<li class="page-item"><a class="page-link" href="search.co?cpage=${ pi.currentPage - 1 }&condition=${ condition }&searchword=${ searchword }">Previous</a></li>
										</c:otherwise>
									 </c:choose>
								  </c:otherwise>
							   </c:choose>
								
								<c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }">
									<c:choose>
										<c:when test="${ empty condition }">
											<li class="page-item"><a class="page-link" href="communityList.co?cpage=${ p }">${ p }</a></li>
										</c:when>
										<c:otherwise>
											<li class="page-item"><a class="page-link" href="search.co?cpage=${ p }&condition=${ condition }&searchword=${ searchword }">${ p }</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								
								<c:choose>
								  <c:when test="${ pi.currentPage eq pi.maxPage }">
									 <c:choose>
										<c:when test="${ empty keyword }">
											<li class="page-item disabled"><a class="page-link" href="#">Next</a></li>
										</c:when>
										<c:otherwise>
											<li class="page-item disabled"><a class="page-link" href="#">Next</a></li>
										</c:otherwise>
									 </c:choose>
								  </c:when>
								  <c:otherwise>
									 <c:choose>
										<c:when test="${ empty keyword }">
											<li class="page-item"><a class="page-link" href="communityList.co?cpage=${ pi.currentPage + 1 }">Next</a></li>
										</c:when>
										<c:otherwise>
											<li class="page-item"><a class="page-link" href="search.co?cpage=${ pi.currentPage + 1 }&condition=${ condition }&searchword=${ searchword }">Next</a></li>
										</c:otherwise>
									 </c:choose>
								  </c:otherwise>
							   </c:choose>
							</ul>
						</div>
					</div>
					<!-- 페이징 처리 끝 -->

					<div style="float: left; margin-top: -53px" >
						<div style=" float: left;">
							<form  id="1" action="search.co" method="get">
								<div id="selectForm" class="select" style="width: 100px; float: left;" align="center">
									<div id="selectForm" class="select">
										<div style="width: 100px">
											<select class="custom-select" name="condition">
												<option value="title">제목</option>
												<option value="content">내용</option>
												<option value="titleContent">제목+내용</option>
											</select>
										</div>
									</div>
								</div>

								<div style="width: 300px; float: left;">
									<div class="input-group">
										<input type="text" name="searchword" value="${ searchword }" class="form-control" placeholder="검색어를 입력하세요">
										<div class="input-group-append">
											<button class="btn btn-secondary" type="submit" style="background-color: #64FFDA; color: black;">
												<i class="fa fa-search"></i>
											</button>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>


				</div>
			</div>
		</div>

		<!-- 푸터 영역 -->
        <jsp:include page="../common/footer.jsp" />
    </div>
    
</body>
</html>