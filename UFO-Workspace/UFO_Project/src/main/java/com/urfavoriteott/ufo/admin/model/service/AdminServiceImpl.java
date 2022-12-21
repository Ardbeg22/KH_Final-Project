package com.urfavoriteott.ufo.admin.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urfavoriteott.ufo.admin.model.dao.AdminDao;
import com.urfavoriteott.ufo.admin.model.vo.Report;
import com.urfavoriteott.ufo.admin.model.vo.Sales;
import com.urfavoriteott.ufo.common.model.vo.PageInfo;
import com.urfavoriteott.ufo.contents.model.vo.Payment;
import com.urfavoriteott.ufo.contents.model.vo.Review;
import com.urfavoriteott.ufo.member.model.vo.Member;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Autowired
    private AdminDao adminDao;
    
    @Override
	public int selectListCount() {
		
		return adminDao.selectListCount(sqlSession);
	}

	@Override
	public ArrayList<Member> selectList(PageInfo pi) {
		
		return adminDao.selectList(sqlSession, pi);
	}

	@Override
	public int selectSearchCount(HashMap<String, String> map) {
		
		return adminDao.selectSearchCount(sqlSession, map);
	}

	@Override
	public ArrayList<Member> selectSearchList(HashMap<String, String> map, PageInfo pi) {
		
		return adminDao.selectSearchList(sqlSession, map, pi);
	}

	@Override
	public int updatePwd(int userNo) {
		
		return adminDao.updatePwd(sqlSession, userNo);
	}

	@Override
	public int deleteMember(int userNo) {
		
		return adminDao.deleteMember(sqlSession, userNo);
	}
	
	@Override
	public ArrayList<Sales> selectSalesPerMonthOnce() {
		
		return adminDao.selectSalesPerMonthOnce(sqlSession);
	}

	@Override
	public ArrayList<Sales> selectSalesPerMonthSub() {
		
		return adminDao.selectSalesPerMonthSub(sqlSession);
	}
	
	@Override
	public ArrayList<Sales> selectSalesPerMonth() {
		
		return adminDao.selectSalesPerMonth(sqlSession);
	}
	
	@Override
	public ArrayList<Sales> selectSalesPerYearOnce() {
		
		return adminDao.selectSalesPerYearOnce(sqlSession);
	}

	@Override
	public ArrayList<Sales> selectSalesPerYearSub() {
		
		return adminDao.selectSalesPerYearSub(sqlSession);
	}

	@Override
	public ArrayList<Sales> selectSalesPerYear() {
		
		return adminDao.selectSalesPerYear(sqlSession);
	}
	
	@Override
	public ArrayList<Integer> selectViewsTV(String genre) {
		
		return adminDao.selectViewsTV(sqlSession, genre);
	}
	
	@Override
	public ArrayList<Integer> selectViewsMovie(String genre) {
		
		return adminDao.selectViewsMovie(sqlSession, genre);
	}

	/**
	 * 관리자 페이지 코멘트 관리를 위한 페이징바 - 작성자: 수빈
	 * @return
	 */
	@Override
	public int selectAdminCommentListCount() {
		return adminDao.selectAdminCommentListCount(sqlSession);
	}

	/**
	 * 관리자 페이지에서 코멘트 관리를 위해 모든 코멘트 조회 (select) - 작성자: 수빈
	 * @return
	 */
	@Override
	public ArrayList<Review> selectAdminCommentList(PageInfo pi) {
		return adminDao.selectAdminCommentList(sqlSession, pi);
	}

	/**
	 * 관리자 페이지 코멘트 관리에서 검색을 위한 페이징바(select) - 작성자: 수빈
	 * @return
	 */
	@Override
	public int searchAdminCommentListCount(HashMap map) {
		return adminDao.searchAdminCommentListCount(sqlSession, map);
	}

	/**
	 * 관리자 페이지 코멘트 관리에서 검색된 코멘트 조회 (select) - 작성자: 수빈
	 * @return
	 */
	@Override
	public ArrayList<Review> searchAdminCommentList(HashMap map, PageInfo pi) {
		return adminDao.searchAdminCommentList(sqlSession, map, pi);
	}


	/**
	 * 관리자 페이지 코멘트 관리에서 선택된 코멘트 삭제 (update) - 작성자: 수빈
	 */
	@Override
	public int deleteAdminComment(int checkNum) {
		return adminDao.deleteAdminComment(sqlSession, checkNum);
	}

	/**
	 * 관리자 페이지 코멘트 신고 관리를 위한 페이징바(select) - 작성자: 수빈
	 */
	@Override
	public int reportedCommentListCount() {
		return adminDao.reportedCommentListCount(sqlSession);
	}

	/**
	 * 관리자 페이지에서 신고 관리를 위해 신고된 모든 코멘트 조회 (select) - 작성자: 수빈
	 */
	@Override
	public ArrayList<Report> reportedCommentList(PageInfo pi) {
		return adminDao.reportedCommentList(sqlSession, pi);
	}
	
	/**
	 * 관리자 페이지 신고 관리에서 신고된 코멘트를 삭제(REPORT_STATUS='Y') 하는 메소드 - 작성자: 수빈
	 */
	@Override
	public int changeStatusReportedComment(int reportNo) {
		return adminDao.changeStatusReportedComment(sqlSession, reportNo);
	}

	/**
	 * 관리자 페이지 신고 관리에서 신고된 코멘트를 삭제(REVIEW_STATUS='N') 하는 메소드 - 작성자: 수빈
	 */
	@Override
	public int deleteReportedComment(int reviewNo) {
		return adminDao.deleteReportedComment(sqlSession, reviewNo);
	}

	/**
	 * 관리자 페이지 신고 관리에서 처리된 코멘트 보기 버튼 클릭 시 페이징바(select) - 작성자: 수빈
	 */
	@Override
	public int processedCommentListCount() {
		return adminDao.processedCommentListCount(sqlSession);
	}

	/**
	 * 관리자 페이지 신고 관리에서 처리된 코멘트 보기 버튼 클릭 시 신고 처리된 모든 코멘트 조회 (select) - 작성자: 수빈
	 */
	@Override
	public ArrayList<Report> processedCommentList(PageInfo pi) {
		return adminDao.processedCommentList(sqlSession, pi);
	}

	/**
	 * 관리자 페이지 신고 관리에서 신고된 코멘트를 되돌리는(REPORT_STATUS='N') 메소드 - 작성자: 수빈
	 */
	@Override
	public int resetStatusReportedComment(int reportNo) {
		return adminDao.resetStatusReportedComment(sqlSession, reportNo);
	}

	/**
	 * 관리자 페이지 신고 관리에서 신고된 코멘트를 삭제 상태를 되돌리는(REVIEW_STATUS='Y') 메소드 - 작성자: 수빈
	 */
	@Override
	public int resetReportedComment(int reviewNo) {
		return adminDao.resetReportedComment(sqlSession, reviewNo);
	}

	/**
	 * 관리자 페이지에서 보이는 유저들 결제내역 수 - 작성자: 성현
	 * @return
	 */
	@Override
	public int selectAdminPaymentListCount() {
		return adminDao.selectAdminPaymentListCount(sqlSession);
	}

	/**
	 * 관리자 페이지에서 보이는 유저들 결제내역 상세 - 작성자: 성현
	 * @param pi
	 * @return
	 */
	@Override
	public ArrayList<Payment> selectAdminPaymentList(PageInfo pi) {
		return adminDao.selectAdminPaymentList(sqlSession, pi);
	}
	
	/**
	 * 관리자 페이지 댓글 신고 관리를 위한 페이징바(select) - 작성자: 수빈
	 */
	@Override
	public int reportedReplyListCount() {
		return adminDao.reportedReplyListCount(sqlSession);
	}

	/**
	 * 관리자 페이지에서 신고 관리를 위해 신고된 모든 댓글 조회 (select) - 작성자: 수빈
	 */
	@Override
	public ArrayList<Report> reportedReplyList(PageInfo pi) {
		return adminDao.reportedReplyList(sqlSession, pi);
	}

	/**
	 * 관리자 페이지 신고 관리에서 신고된 댓글 삭제(REPORT_STATUS='Y') 하는 메소드 - 작성자: 수빈
	 */
	@Override
	public int changeStatusReportedReply(int reportNo) {
		return adminDao.changeStatusReportedReply(sqlSession, reportNo);
	}

	/**
	 * 관리자 페이지 신고 관리에서 신고된 댓글을 삭제(REVIEW_STATUS='N') 하는 메소드 - 작성자: 수빈
	 */
	@Override
	public int deleteReportedReply(int comRplNo) {
		return adminDao.deleteReportedReply(sqlSession, comRplNo);
	}

	/**
	 * 관리자 페이지 신고 관리에서 처리된 댓글 보기 버튼 클릭 시 페이징바(select) - 작성자: 수빈
	 */
	@Override
	public int processedReplyListCount() {
		return adminDao.processedReplyListCount(sqlSession);
	}

	/**
	 * 관리자 페이지 신고 관리에서 처리된 댓글 보기 버튼 클릭 시 신고 처리된 모든 댓글 조회 (select) - 작성자: 수빈
	 */
	@Override
	public ArrayList<Report> processedReplyList(PageInfo pi) {
		return adminDao.processedReplyList(sqlSession, pi);
	}

	/**
	 * 관리자 페이지 신고 관리에서 신고된 댓글을 되돌리는(REPORT_STATUS='N') 메소드 - 작성자: 수빈
	 */
	@Override
	public int resetStatusReportedReply(int reportNo) {
		return adminDao.resetStatusReportedReply(sqlSession, reportNo);
	}

	/**
	 * 관리자 페이지 신고 관리에서 신고된 댓글 삭제 상태를 되돌리는(REVIEW_STATUS='Y') 메소드 - 작성자: 수빈
	 */
	@Override
	public int resetReportedReply(int comRplNo) {
		return adminDao.resetReportedReply(sqlSession, comRplNo);
	}
	
	/**
	 * 관리자 페이지 신고 관리 - 신고된 전체 커뮤니티 게시글 개수 조회 - 작성자 : 황혜진
	 */
	@Override
	public int reportedCommunityListCount() {
		return adminDao.reportedCommunityListCount(sqlSession);
	}

	/**
	 * 관리자 페이지 신고 관리 - 신고된 전체 커뮤니티 게시글 조회 - 작성자 : 황혜진
	 */
	@Override
	public ArrayList<Report> reportedCommunityList(PageInfo pi) {
		return adminDao.reportedCommunityList(sqlSession, pi);
	}

	/**
	 * 관리자 페이지 신고 관리 - 신고된 커뮤니티 게시글을 삭제(REPORT_STATUS='Y') 하는 메소드 - 작성자: 황혜진 
	 */
	@Override
	public int changeStatusReportedCommunity(int reportNo) {
		return adminDao.changeStatusReportedCommunity(sqlSession, reportNo);
	}

	/**
	 * 관리자 페이지 신고 관리 - 신고된 커뮤니티 게시글을 삭제(COM_STATUS='N') 하는 메소드 - 작성자: 황혜진
	 */
	@Override
	public int deleteReportedCommunity(int comNo) {
		return adminDao.deleteReportedCommunity(sqlSession, comNo);
	}

	/**
	 * 관리자 페이지 신고 관리 - 신고 후 삭제 처리된 커뮤니티 게시글 개수 조회 - 작성자 : 황혜진
	 */
	@Override
	public int processedCommunityListCount() {
		return adminDao.processedCommunityListCount(sqlSession);
	}

	/**
	 * 관리자 페이지 신고 관리 - 신고 후 삭제 처리된 커뮤니티 게시글 조회 - 작성자 : 황혜진
	 */
	@Override
	public ArrayList<Report> processedCommunityList(PageInfo pi) {
		return adminDao.processedCommunityList(sqlSession, pi);
	}

	/**
	 * 관리자 페이지 신고 관리 - 신고된 게시글 되돌리기 (REPORT_STATUS='N') 메소드 - 작성자: 황혜진 
	 */
	@Override
	public int resetStatusReportedCommunity(int reportNo) {
		return adminDao.resetStatusReportedCommunity(sqlSession, reportNo);
	}

	/**
	 * 관리자 페이지 신고 관리 - 신고된 게시글 삭제 상태 되돌리기 (COM_STATUS='Y') 메소드 - 작성자: 황혜진
	 */
	@Override
	public int resetReportedCommunity(int comNo) {
		return adminDao.resetReportedCommunity(sqlSession, comNo);
	}
}