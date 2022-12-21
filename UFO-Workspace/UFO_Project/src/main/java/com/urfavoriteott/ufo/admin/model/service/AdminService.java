package com.urfavoriteott.ufo.admin.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.urfavoriteott.ufo.admin.model.vo.Report;
import com.urfavoriteott.ufo.admin.model.vo.Sales;
import com.urfavoriteott.ufo.common.model.vo.PageInfo;
import com.urfavoriteott.ufo.contents.model.vo.Payment;
import com.urfavoriteott.ufo.contents.model.vo.Review;
import com.urfavoriteott.ufo.member.model.vo.Member;

public interface AdminService {
	
	/**
	 * 관리자 - 회원 리스트 조회용 메소드 - 작성자 : 장희연
	 * 전체 회원 수 조회
	 * @return
	 */
	int selectListCount();
	/**
	 * 관리자 - 회원 리스트 조회용 메소드 - 작성자 : 장희연
	 * 회원 리스트 조회
	 * @param pi : 페이징 처리를 위한 페이지 정보
	 * @return
	 */
	ArrayList<Member> selectList(PageInfo pi);

	/**
	 * 관리자 - 회원 검색 조회용 메소드 - 작성자 : 장희연
	 * 검색 조건에 부합하는 회원 수 조회
	 * @param map : keyword(아이디, 닉네임), searchword(검색어)가 담긴 HashMap
	 * @return
	 */
	int selectSearchCount(HashMap<String, String> map);
	
	/**
	 * 관리자 - 회원 검색 조회용 메소드 - 작성자 : 장희연
	 * 검색된 회원 리스트 조회
	 * @param map : keyword(아이디, 닉네임), searchword(검색어)가 담긴 HashMap
	 * @param pi : 페이징 처리를 위한 페이지 정보
	 * @return
	 */
	ArrayList<Member> selectSearchList(HashMap<String, String> map, PageInfo pi);
	
	/**
	 * 관리자 - 비밀번호 초기화용 메소드 - 작성자 : 장희연
	 * @param userNo : 비밀번호를 초기화할 회원의 회원번호
	 * @return
	 */
	int updatePwd(int userNo);
	
	/**
	 * 관리자 - 회원 탈퇴 처리용 메소드 - 작성자 : 장희연
	 * @param userNo : 탈퇴 처리할 회원의 회원번호
	 * @return
	 */
	int deleteMember(int userNo);
	
	/**
	 * 관리자 - 한달이용권 월별 매출 메소드 - 작성자 : 장희연
	 * @return
	 */
	ArrayList<Sales> selectSalesPerMonthOnce();
	
	/**
	 * 관리자 - 월간구독권 월별 매출 메소드 - 작성자 : 장희연
	 * @return
	 */
	ArrayList<Sales> selectSalesPerMonthSub();
	
	/**
	 * 관리자 - 월별 매출 메소드 - 작성자 : 장희연
	 * @return
	 */
	ArrayList<Sales> selectSalesPerMonth();
	
	/**
	 * 관리자 - 한달이용권 연도별 매출 메소드 - 작성자 : 장희연
	 * @return
	 */
	ArrayList<Sales> selectSalesPerYearOnce();
	
	/**
	 * 관리자 - 월간구독권 연도별 매출 메소드 - 작성자 : 장희연
	 * @return
	 */
	ArrayList<Sales> selectSalesPerYearSub();
	
	/**
	 * 관리자 - 연도별 매출 메소드 - 작성자 : 장희연
	 * @return
	 */
	ArrayList<Sales> selectSalesPerYear();
	
	ArrayList<Integer> selectViewsTV(String genre);
	
	ArrayList<Integer> selectViewsMovie(String genre);
	
	/**
	 * 관리자 페이지 코멘트 관리를 위한 페이징바 - 작성자: 수빈
	 * @return
	 */
	int selectAdminCommentListCount();

	/**
	 * 관리자 페이지에서 코멘트 관리를 위해 모든 코멘트 조회 (select) - 작성자: 수빈
	 * @param pi : 페이징바
	 * @return
	 */
	ArrayList<Review> selectAdminCommentList(PageInfo pi);
	
	/**
	 * 관리자 페이지 코멘트 관리에서 검색을 위한 페이징바(select) - 작성자: 수빈
	 * @param map : 조건과 검색어를 담은 HashMap
	 * @return
	 */
	int searchAdminCommentListCount(HashMap map);
	
	/**
	 * 관리자 페이지 코멘트 관리에서 검색된 코멘트 조회 (select) - 작성자: 수빈
	 * @param map : 조건과 검색어를 담은 HashMap
	 * @param pi : 페이징바
	 * @return
	 */
	ArrayList<Review> searchAdminCommentList(HashMap map, PageInfo pi);
	
	/**
	 * 관리자 페이지 코멘트 관리에서 선택된 코멘트 삭제 (update) - 작성자: 수빈
	 * @param checkNum : checkbox 중 선택된 것의 리뷰 번호
	 * @return
	 */
	int deleteAdminComment(int checkNum); 
	
	/**
	 * 관리자 페이지 코멘트 신고 관리를 위한 페이징바(select) - 작성자: 수빈
	 * @return
	 */
	int reportedCommentListCount();
	
	/**
	 * 관리자 페이지에서 신고 관리를 위해 신고된 모든 코멘트 조회 (select) - 작성자: 수빈
	 * @param pi
	 * @return
	 */
	ArrayList<Report> reportedCommentList(PageInfo pi);
	
	/**
	 * 관리자 페이지 신고 관리에서 신고된 코멘트를 삭제(REPORT_STATUS='Y') 하는 메소드 - 작성자: 수빈
	 * @param reportNo
	 * @return
	 */
	int changeStatusReportedComment(int reportNo);
	
	/**
	 * 관리자 페이지 신고 관리에서 신고된 코멘트를 삭제(REVIEW_STATUS='N') 하는 메소드 - 작성자: 수빈
	 * @param reviewNo
	 * @return
	 */
	int deleteReportedComment(int reviewNo);

	/**
	 * 관리자 페이지 신고 관리에서 처리된 코멘트 보기 버튼 클릭 시 페이징바(select) - 작성자: 수빈
	 * @return
	 */
	int processedCommentListCount();

	/**
	 * 관리자 페이지 신고 관리에서 처리된 코멘트 보기 버튼 클릭 시 신고 처리된 모든 코멘트 조회 (select) - 작성자: 수빈
	 * @param pi
	 * @return
	 */
	ArrayList<Report> processedCommentList(PageInfo pi);
	
	/**
	 * 관리자 페이지 신고 관리에서 신고된 코멘트를 되돌리는(REPORT_STATUS='N') 메소드 - 작성자: 수빈
	 * @param reportNo
	 * @return
	 */
	int resetStatusReportedComment(int reportNo);
	
	/**
	 * 관리자 페이지 신고 관리에서 신고된 코멘트 삭제 상태를 되돌리는(REVIEW_STATUS='Y') 메소드 - 작성자: 수빈
	 * @param reviewNo
	 * @return
	 */
	int resetReportedComment(int reviewNo);
		
	/**
	 * 관리자 페이지 댓글 신고 관리를 위한 페이징바(select) - 작성자: 수빈
	 * @return
	 */
	int reportedReplyListCount();
	
	/**
	 * 관리자 페이지에서 신고 관리를 위해 신고된 모든 댓글 조회 (select) - 작성자: 수빈
	 * @param pi
	 * @return
	 */
	ArrayList<Report> reportedReplyList(PageInfo pi);
	
	/**
	 * 관리자 페이지 신고 관리에서 신고된 댓글 삭제(REPORT_STATUS='Y') 하는 메소드 - 작성자: 수빈
	 * @param reportNo
	 * @return
	 */
	int changeStatusReportedReply(int reportNo);
	
	/**
	 * 관리자 페이지 신고 관리에서 신고된 댓글을 삭제(REVIEW_STATUS='N') 하는 메소드 - 작성자: 수빈
	 * @param reviewNo
	 * @return
	 */
	int deleteReportedReply(int comRplNo);
	
	/**
	 * 관리자 페이지 신고 관리에서 처리된 댓글 보기 버튼 클릭 시 페이징바(select) - 작성자: 수빈
	 * @return
	 */
	int processedReplyListCount();
	
	/**
	 * 관리자 페이지 신고 관리에서 처리된 댓글 보기 버튼 클릭 시 신고 처리된 모든 댓글 조회 (select) - 작성자: 수빈
	 * @param pi
	 * @return
	 */
	ArrayList<Report> processedReplyList(PageInfo pi);
	
	/**
	 * 관리자 페이지 신고 관리에서 신고된 댓글을 되돌리는(REPORT_STATUS='N') 메소드 - 작성자: 수빈
	 * @param reportNo
	 * @return
	 */
	int resetStatusReportedReply(int reportNo);
	
	/**
	 * 관리자 페이지 신고 관리에서 신고된 댓글 삭제 상태를 되돌리는(REVIEW_STATUS='Y') 메소드 - 작성자: 수빈
	 * @param comRplNo
	 * @return
	 */
	int resetReportedReply(int comRplNo);

	/**
	 * 관리자 페이지에서 보이는 유저들 결제내역 수 - 작성자: 성현
	 * @return
	 */
	int selectAdminPaymentListCount();
	
	/**
	 * 관리자 페이지에서 보이는 유저들 결제내역 상세 - 작성자: 성현
	 * @param pi
	 * @return
	 */
	ArrayList<Payment> selectAdminPaymentList(PageInfo pi);
	
	/**
	 * 관리자 페이지 신고 관리 - 신고된 전체 커뮤니티 게시글 개수 조회 - 작성자 : 황혜진
	 * @return
	 */
	int reportedCommunityListCount();
	
	/**
	 * 관리자 페이지 신고 관리 - 신고된 전체 커뮤니티 게시글 조회 - 작성자 : 황혜진
	 * @param pi
	 * @return
	 */
	ArrayList<Report> reportedCommunityList(PageInfo pi);
	
	/**
	 * 관리자 페이지 신고 관리 - 신고된 커뮤니티 게시글을 삭제(REPORT_STATUS='Y') 하는 메소드 - 작성자: 황혜진 
	 * @param reportNo
	 * @return
	 */
	int changeStatusReportedCommunity(int reportNo);
	
	/**
	 * 관리자 페이지 신고 관리 - 신고된 커뮤니티 게시글을 삭제(COM_STATUS='N') 하는 메소드 - 작성자: 황혜진
	 * @param comNo
	 * @return
	 */
	int deleteReportedCommunity(int comNo);
	
	/**
	 * 관리자 페이지 신고 관리 - 신고 후 삭제 처리된 커뮤니티 게시글 개수 조회 - 작성자 : 황혜진
	 * @return
	 */
	int processedCommunityListCount();
	
	/**
	 * 관리자 페이지 신고 관리 - 신고 후 삭제 처리된 커뮤니티 게시글 조회 - 작성자 : 황혜진
	 * @param pi
	 * @return
	 */
	ArrayList<Report> processedCommunityList(PageInfo pi);
	
	/**
	 * 관리자 페이지 신고 관리 - 신고된 게시글 되돌리기 (REPORT_STATUS='N') 메소드 - 작성자: 황혜진 
	 * @param reportNo
	 * @return
	 */
	int resetStatusReportedCommunity(int reportNo);
	
	/**
	 * 관리자 페이지 신고 관리 - 신고된 게시글 삭제 상태 되돌리기 (COM_STATUS='Y') 메소드 - 작성자: 황혜진
	 * @param comNo
	 * @return
	 */
	int resetReportedCommunity(int comNo);

}