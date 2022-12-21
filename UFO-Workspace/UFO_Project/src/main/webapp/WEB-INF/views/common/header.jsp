<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Header</title>

    <!-- CSS 스타일시트 -->
    <link rel="stylesheet" href="resources/css/headerFooterCss.css">
</head>

<body>

    <c:if test="${ not empty alertMsg }">
		<script>
			alert("${ alertMsg }");
		</script>
		<c:remove var="alertMsg" scope="session" />
	</c:if>

    <!-- 헤더 영역 -->
    <div id="header_container">

        <!-- 헤더 로고 -->
        <div id="header_logo_container">
            <a href="<%= request.getContextPath() %>">
                <img id="header_logo" src="resources/image/common/main-logo_dark.png">
            </a>
        </div>

        <!-- 헤더 메뉴 -->
        <div id="header_menu_container">
            <ul>
                <li class="main_menu">
                    <a href="commentList.co">TV 프로그램</a>
                </li>
                <li class="main_menu">
                    <a href="commentList.co">영화</a>
                </li>
                <li class="main_menu">
                    <a href="communityList.co">커뮤니티</a>
                </li>
                <li class="main_menu">
                    <a href="adminNoticeList.no">고객센터</a>
                    <ul class="sub_menu">
                        <li>
                            <a href="adminNoticeList.no">공지사항</a>
                        </li>
                        <li>
                            <a href="adminFAQ.no">FAQ</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>

        <div id="header_empty_container"></div>

        <!-- 헤더 검색 -->
        <div id="header_search_container">
            <form action="" id="header_search_form">
                <i class='fa-solid fa-magnifying-glass'></i>
                <input type="search" name="search" id="header_search_input" placeholder="제목, 감독 및 출연진으로 검색해보세요">
                <button type="button" id="header_search_btn">
                    <i class='fa-solid fa-magnifying-glass'></i>
                </button>
            </form>
        </div>

        <div id="header_theme_container">
            <input class="check" id="theme_cb" type="checkbox">
            <label for="theme_cb">
                <div id="theme_btn_container">
                    <i class="fa-solid fa-circle-half-stroke" id="theme_btn"></i>
                </div>
            </label>
        </div>

        <div id="header_profile_container">
            <c:choose>
                <c:when test="${ empty loginUser }">
                    <ul>
                        <li class="main_menu logout_menu">
                            <a href="loginForm.me">로그인</a>
                            <ul class="sub_menu">
                                <li>
                                    <a href="loginForm.me">로그인</a>
                                </li>
                                <li>
                                    <a href="userEnrollForm.me">회원가입</a>
                                </li>
                                <li>
                                    <a href="payment.pay">이용권</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </c:when>
                <c:when test="${ loginUser.userProfile eq 0 }">
                    <ul>
                   		<li class="main_menu login_menu">
                        	<a href="admin_list.me">
		                       <img id="member_profile" src="resources/image/member/profile${ loginUser.userProfile }.png">
		                    </a>
		                    <ul class="sub_menu mypage_menu">
                             	<li id="header_userInfo">
                             		<a href="#">
                             			<img id="member_profile" src="resources/image/member/profile${ loginUser.userProfile }.png">
                                    	<p>${ loginUser.userNickname } 님</p>
                             		</a>
                                </li>
                                <li>
                                    <a href="admin_list.me">관리자 페이지</a>
                                </li>
                                <li>
                                    <a href="payment.pay">이용권</a>
                                </li>
                                <li>
                                    <a href="logout.me">로그아웃</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </c:when>
                <c:otherwise>
                    <ul>
                        <li class="main_menu login_menu">
                         <a href="myPage.me">
                            <img id="member_profile" src="resources/image/member/profile${ loginUser.userProfile }.png">
                         </a>
                         <ul class="sub_menu mypage_menu">
                             <li id="header_userInfo">
                             	<a href="update.me">
                                 <img id="member_profile" src="resources/image/member/profile${ loginUser.userProfile }.png">
                                 <p>${ loginUser.userNickname } 님</p>
                             	</a>
                             </li>
                             <li>
                                 <a href="myPage.me">마이 페이지</a>
                             </li>
                             <li>
                                 <a href="payment.pay">이용권</a>
                             </li>
                             <li>
                                 <a href="logout.me">로그아웃</a>
                             </li>
                         </ul>
                     </li>
                 </ul>
                </c:otherwise>
            </c:choose>
        </div>

    </div>

    <script>
        $(function() {

            // 검색 버튼 클릭 시
            $('#header_search_btn').click(function() {

                // 아이콘 변경
                $(this).children().toggleClass("fa-x");

                // 메뉴 숨김
                $('#header_empty_container').toggleClass("active");

                // 검색창 활성화
                $('#header_search_container').toggleClass("active");
            });

            $('.logout_menu').click(function() {

                $('.logout_menu').addClass("login");
                $('.login_menu').addClass("login");

                $('.logout_menu').removeClass("logout");
                $('.login_menu').removeClass("logout");
            });

            $('.login_menu').click(function() {

                $('.logout_menu').addClass("logout");
                $('.login_menu').addClass("logout");

                $('.logout_menu').removeClass("login");
                $('.login_menu').removeClass("login");
            });
        });
    </script>

</body>
</html>
</body>
</html>