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
	#main { width: 100%; height: 100%; }

    #community { /* 글작성, 버튼을 감싸고 있는 div */
        width: 100%;
        height: 100%;
        margin: auto;
    }

	#enroll-form { width: 100%; height: 100%; }

    #enroll-form>table { /* 게시판 */
        width: 100%;
        height: 90%;
        border-collapse: separate;
	    border-spacing: 0 20px;
        text-align: center;
    }

	table th { width: 10%; font-size: 15px; }

	#btns { height: 10%;}

    #enroll-form>table input, #enroll-form>table textarea { /* 제목과 내용을 입력할 칸 */
        width: 100%;
        box-sizing: border-box;
    }

    input[type=text] { /* 제목을 입력할 칸 */
        height: 35px;
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
	            <td width="380" height="20"></td>
	        </tr>
	        <tr>
	            <td width="380" style="font-size:50px; font-weight:900;">커뮤니티</td>
	        </tr>
	    </table>
	    	
	    	<!-- !!! 본인이 맡은 탭 div에 id="selected_tab" 붙어녛기 !!!-->
        

            <div class="community">

			<br><br><br>
			
					<div id="main">
                        <div id="notice">
                            <form id="enroll-form" action="communityUpdate.co" method="post">
                                <table align="center">
                                    <tr>
                                        <th>제목</th>
                                        <td>
                                        <input type="text" id="comTitle" name="comTitle" class="form-control" value="${ c.comTitle }" maxlength="33" required>
                                        <input type="hidden" id="comNo" name="comNo" class="form-control" value="${ c.comNo }">
                                        </td>
                                        
                                    </tr>

                                    <br>
									<tr>
                        				<td><input type="hidden" id="userNo" class="form-control" value="${ loginUser.userNo }" name="userNo"></td>
                   				   </tr>
                                    <tr>
                                        <th>내용</th>
                                        <td>
                                            <textarea name="comContent" id="comContent" class="form-control" rows="19" required style="resize:none">${c.comContent}</textarea>
                                        </td>
                                    </tr>
                                </table>

                                <div id="btns"> <!-- margin-left:1100px  -->
                                    <button type="submit" class="btn btn-info" style="margin-left:800px">수정하기</button>
					                <button type="button" class="btn btn-secondary" onclick="history.back();">뒤로가기</button>
					                <!-- history.back() : 이전 페이지로 돌아가게 해주는 함수 -->
                                </div>
                    
                            </form>
                        </div>
                    </div> 
           </div>
           

        </div>
        

		<!-- 푸터 영역 -->
        <jsp:include page="../common/footer.jsp" />

    </div>
    
    

  
    

</body>
</html>