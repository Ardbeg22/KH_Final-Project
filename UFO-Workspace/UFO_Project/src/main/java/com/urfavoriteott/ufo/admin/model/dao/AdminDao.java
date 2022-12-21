package com.urfavoriteott.ufo.admin.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.urfavoriteott.ufo.admin.model.vo.Report;
import com.urfavoriteott.ufo.admin.model.vo.Sales;
import com.urfavoriteott.ufo.common.model.vo.PageInfo;
import com.urfavoriteott.ufo.contents.model.vo.Payment;
import com.urfavoriteott.ufo.contents.model.vo.Review;
import com.urfavoriteott.ufo.member.model.vo.Member;

@Repository
public class AdminDao {
	
	/**
	 * 관리자 - 회원 리스트 조회용 메소드 - 작성자 : 장희연
	 * 전체 회원 수 조회
	 * @param sqlSession
	 * @return
	 */
	public int selectListCount(SqlSessionTemplate sqlSession) {
		
		return sqlSession.selectOne("memberMapper.selectListCount");
	}
	
	/**
	 * 관리자 - 회원 리스트 조회용 메소드 - 작성자 : 장희연
	 * 회원 리스트 조회
	 * @param sqlSession
	 * @param pi
	 * @return
	 */
	public ArrayList<Member> selectList(SqlSessionTemplate sqlSession, PageInfo pi) {
		
		int limit = pi.getBoardLimit();
		int offset = (pi.getCurrentPage() - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return (ArrayList)sqlSession.selectList("memberMapper.selectList", null, rowBounds);
	}
	
	/**
	 * 관리자 - 회원 검색 조회용 메소드 - 작성자 : 장희연
	 * 검색 조건에 부합하는 회원 수 조회
	 * @param sqlSession
	 * @param map
	 * @return
	 */
	public int selectSearchCount(SqlSessionTemplate sqlSession, HashMap<String, String> map) {
		
		return sqlSession.selectOne("memberMapper.selectSearchCount", map);
	}
	
	/**
	 * 관리자 - 회원 검색 조회용 메소드 - 작성자 : 장희연
	 * 검색된 회원 리스트 조회
	 * @param sqlSession
	 * @param map
	 * @param pi
	 * @return
	 */
	public ArrayList<Member> selectSearchList(SqlSessionTemplate sqlSession, HashMap<String, String> map, PageInfo pi) {
		
		int limit = pi.getBoardLimit();
		int offset = (pi.getCurrentPage() - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return (ArrayList)sqlSession.selectList("memberMapper.selectSearchList", map, rowBounds);
	}
	
	/**
	 * 관리자 - 비밀번호 초기화용 메소드 - 작성자 : 장희연
	 * @param sqlSession
	 * @param userNo
	 * @return
	 */
	public int updatePwd(SqlSessionTemplate sqlSession, int userNo) {

		return sqlSession.update("memberMapper.updatePwd", userNo);
	}
	
	/**
	 * 관리자 - 회원 탈퇴 처리용 메소드 - 작성자 : 장희연
	 * @param sqlSession
	 * @param userNo
	 * @return
	 */
	public int deleteMember(SqlSessionTemplate sqlSession, int userNo) {
	
		return sqlSession.update("memberMapper.deleteMember", userNo);
	}
	
	/**
	 * 관리자 - 한달이용권 월별 매출 메소드 - 작성자 : 장희연
	 * @param sqlSession
	 * @return
	 */
	public ArrayList<Sales> selectSalesPerMonthOnce(SqlSessionTemplate sqlSession) {
		
		return (ArrayList)sqlSession.selectList("adminMapper.selectSalesPerMonthOnce");
	}
	
	/**
	 * 관리자 - 월간구독권 월별 매출 메소드 - 작성자 : 장희연
	 * @param sqlSession
	 * @return
	 */
	public ArrayList<Sales> selectSalesPerMonthSub(SqlSessionTemplate sqlSession) {
		
		return (ArrayList)sqlSession.selectList("adminMapper.selectSalesPerMonthSub");
	}
	
	/**
	 * 관리자 - 월별 매출 메소드 - 작성자 : 장희연
	 * @param sqlSession
	 * @return
	 */
	public ArrayList<Sales> selectSalesPerMonth(SqlSessionTemplate sqlSession) {
		
		return (ArrayList)sqlSession.selectList("adminMapper.selectSalesPerMonth");
	}
	
	/**
	 * 관리자 - 한달이용권 연도별 매출 메소드 - 작성자 : 장희연
	 * @param sqlSession
	 * @return
	 */
	public ArrayList<Sales> selectSalesPerYearOnce(SqlSessionTemplate sqlSession) {
		
		return (ArrayList)sqlSession.selectList("adminMapper.selectSalesPerYearOnce");
	}
	
	/**
	 * 관리자 - 월간구독권 연도별 매출 메소드 - 작성자 : 장희연
	 * @param sqlSession
	 * @return
	 */
	public ArrayList<Sales> selectSalesPerYearSub(SqlSessionTemplate sqlSession) {
		
		return (ArrayList)sqlSession.selectList("adminMapper.selectSalesPerYearSub");
	}
	
	/**
	 * 관리자 - 연도별 매출 메소드 - 작성자 : 장희연
	 * @param sqlSession
	 * @return
	 */
	public ArrayList<Sales> selectSalesPerYear(SqlSessionTemplate sqlSession) {
		
		return (ArrayList)sqlSession.selectList("adminMapper.selectSalesPerYear");
	}
	
	public ArrayList<Integer> selectViewsTV(SqlSessionTemplate sqlSession, String genre) {
		
		return (ArrayList)sqlSession.selectList("adminMapper.selectViewsTV", genre);
	}
	
	public ArrayList<Integer> selectViewsMovie(SqlSessionTemplate sqlSession, String genre) {
		
		return (ArrayList)sqlSession.selectList("adminMapper.selectViewsMovie", genre);
	}
	
	/**
	 * 관리자 페이지 코멘트 관리를 위한 페이징바 - 작성자: 수빈
	 * @param sqlSession
	 * @return
	 */
	public int selectAdminCommentListCount(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("adminMapper.selectAdminCommentListCount");
	}
	
	/**
	 * 관리자 페이지에서 코멘트 관리를 위해 모든 코멘트 조회 (select) - 작성자: 수빈
	 * @param sqlSession
	 * @param pi
	 * @return
	 */
	public ArrayList<Review> selectAdminCommentList(SqlSessionTemplate sqlSession, PageInfo pi) {
		
		int limit = pi.getBoardLimit();
		int offset = (pi.getCurrentPage() -1) * limit;
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return (ArrayList)sqlSession.selectList("adminMapper.selectAdminCommentList", null, rowBounds);
	}

	/**
	 * 관리자 페이지 코멘트 관리에서 검색을 위한 페이징바(select) - 작성자: 수빈
	 * @param sqlSession
	 * @param map
	 * @return
	 */
	public int searchAdminCommentListCount(SqlSessionTemplate sqlSession, HashMap map) {
		
		return sqlSession.selectOne("adminMapper.searchAdminCommentListCount", map);
	}

	/**
	 * 관리자 페이지 코멘트 관리에서 검색된 코멘트 조회 (select) - 작성자: 수빈
	 * @param sqlSession
	 * @param map
	 * @param pi
	 * @return
	 */
	public ArrayList<Review> searchAdminCommentList(SqlSessionTemplate sqlSession, HashMap map, PageInfo pi) {
		
		int limit = pi.getBoardLimit();
		int offset = (pi.getCurrentPage() -1) * limit;
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return (ArrayList)sqlSession.selectList("adminMapper.searchAdminCommentList", map, rowBounds);
	}
	
	/**
	 * 관리자 페이지 코멘트 관리에서 선택된 코멘트 삭제 (update) - 작성자: 수빈
	 * @param sqlSession
	 * @param checkNum
	 * @return
	 */
	public int deleteAdminComment(SqlSessionTemplate sqlSession, int checkNum) {
		return sqlSession.update("adminMapper.deleteAdminComment", checkNum);
	}
	
	/**
	 * 관리자 페이지 신고 관리를 위한 페이징바(select) - 작성자: 수빈
	 * @param sqlSession
	 * @return
	 */
	public int reportedCommentListCount(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("adminMapper.reportedCommentListCount");
	}
	
	/**
	 * 관리자 페이지에서 코멘트 신고 관리를 위해 신고된 모든 코멘트 조회 (select) - 작성자: 수빈
	 * @param sqlSession
	 * @param pi
	 * @return
	 */
	public ArrayList<Report> reportedCommentList(SqlSessionTemplate sqlSession, PageInfo pi) {
		
		int limit = pi.getBoardLimit();
		int offset = (pi.getCurrentPage() -1) * limit;
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return (ArrayList)sqlSession.selectList("adminMapper.reportedCommentList", null, rowBounds);
		
	}
	
	/**
	 * 관리자 페이지 신고 관리에서 신고된 코멘트를 삭제(REPORT_STATUS='Y') 하는 메소드 - 작성자: 수빈
	 * @param sqlSession
	 * @param reportNo
	 * @return
	 */
	public int changeStatusReportedComment(SqlSessionTemplate sqlSession, int reportNo) {
		return sqlSession.update("adminMapper.changeStatusReportedComment", reportNo);
	}
	
	
	/**
	 * 관리자 페이지 신고 관리에서 신고된 코멘트를 삭제(REVIEW_STATUS='N') 하는 메소드 - 작성자: 수빈
	 * @param sqlSession
	 * @param reportNo
	 * @return
	 */
	public int deleteReportedComment(SqlSessionTemplate sqlSession, int reviewNo) {
		return sqlSession.update("adminMapper.deleteReportedComment", reviewNo);
	}
	
	/**
	 * 관리자 페이지 신고 관리에서 처리된 코멘트 보기 버튼 클릭 시 페이징바(select) - 작성자: 수빈
	 * @param sqlSession
	 * @return
	 */
	public int processedCommentListCount(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("adminMapper.processedCommentListCount");
	}
	
	/**
	 * 관리자 페이지 신고 관리에서 처리된 코멘트 보기 버튼 클릭 시 신고 처리된 모든 코멘트 조회 (select) - 작성자: 수빈
	 * @param sqlSession
	 * @param pi
	 * @return
	 */
	public ArrayList<Report> processedCommentList(SqlSessionTemplate sqlSession, PageInfo pi) {
		
		int limit = pi.getBoardLimit();
		int offset = (pi.getCurrentPage() -1) * limit;
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return (ArrayList)sqlSession.selectList("adminMapper.processedCommentList", null, rowBounds);
		
	}
	
	/**
	 * 관리자 페이지 신고 관리에서 신고된 코멘트를 되돌리는(REPORT_STATUS='N') 메소드 - 작성자: 수빈
	 * @param sqlSession
	 * @param reportNo
	 * @return
	 */
	public int resetStatusReportedComment(SqlSessionTemplate sqlSession, int reportNo) {
		return sqlSession.update("adminMapper.resetStatusReportedComment", reportNo);
	}
	
	/**
	 * 관리자 페이지 신고 관리에서 신고된 코멘트를 삭제 상태를 되돌리는(REVIEW_STATUS='Y') 메소드 - 작성자: 수빈
	 * @param sqlSession
	 * @return
	 */
	public int resetReportedComment(SqlSessionTemplate sqlSession, int reviewNo) {
		return sqlSession.update("adminMapper.resetReportedComment", reviewNo);
	}
	
	/**
	 * 관리자 페이지 댓글 신고 관리를 위한 페이징바(select) - 작성자: 수빈
	 */
	public int reportedReplyListCount(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("adminMapper.reportedReplyListCount");
	}

	/**
	 * 관리자 페이지에서 신고 관리를 위해 신고된 모든 댓글 조회 (select) - 작성자: 수빈
	 */
	public ArrayList<Report> reportedReplyList(SqlSessionTemplate sqlSession, PageInfo pi) {
		
		int limit = pi.getBoardLimit();
		int offset = (pi.getCurrentPage() -1) * limit;
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return (ArrayList)sqlSession.selectList("adminMapper.reportedReplyList", null, rowBounds);
	}
	
	/**
	 * 관리자 페이지 신고 관리에서 신고된 댓글 삭제(REPORT_STATUS='Y') 하는 메소드 - 작성자: 수빈
	 * @param sqlSession
	 * @param reportNo
	 * @return
	 */
	public int changeStatusReportedReply(SqlSessionTemplate sqlSession, int reportNo) {
		return sqlSession.update("adminMapper.changeStatusReportedReply", reportNo);
	}
	
	/**
	 * 관리자 페이지 신고 관리에서 신고된 댓글을 삭제(REVIEW_STATUS='N') 하는 메소드 - 작성자: 수빈
	 * @param sqlSession
	 * @param comRplNo
	 * @return
	 */
	public int deleteReportedReply(SqlSessionTemplate sqlSession, int comRplNo) {
		return sqlSession.update("adminMapper.deleteReportedReply", comRplNo);
	}
	
	/**
	 * 관리자 페이지 신고 관리에서 처리된 댓글 보기 버튼 클릭 시 페이징바(select) - 작성자: 수빈
	 * @param sqlSession
	 * @return
	 */
	public int processedReplyListCount(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("adminMapper.processedReplyListCount");
	}
	
	/**
	 * 관리자 페이지 신고 관리에서 처리된 댓글 보기 버튼 클릭 시 신고 처리된 모든 댓글 조회 (select) - 작성자: 수빈
	 * @param sqlSession
	 * @param pi
	 * @return
	 */
	public ArrayList<Report> processedReplyList(SqlSessionTemplate sqlSession, PageInfo pi) {
		
		int limit = pi.getBoardLimit();
		int offset = (pi.getCurrentPage() -1) * limit;
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return (ArrayList)sqlSession.selectList("adminMapper.processedReplyList", null, rowBounds);
		
	}
	
	/**
	 * 관리자 페이지 신고 관리에서 신고된 댓글을 되돌리는(REPORT_STATUS='N') 메소드 - 작성자: 수빈
	 * @param sqlSession
	 * @param reportNo
	 * @return
	 */
	public int resetStatusReportedReply(SqlSessionTemplate sqlSession, int reportNo) {
		return sqlSession.update("adminMapper.resetStatusReportedReply", reportNo);
	}
	
	/**
	 * 관리자 페이지 신고 관리에서 신고된 댓글 삭제 상태를 되돌리는(REVIEW_STATUS='Y') 메소드 - 작성자: 수빈
	 * @param sqlSession
	 * @param comRplNo
	 * @return
	 */
	public int resetReportedReply(SqlSessionTemplate sqlSession, int comRplNo) {
		return sqlSession.update("adminMapper.resetReportedReply", comRplNo);
	}

	public int selectAdminPaymentListCount(SqlSessionTemplate sqlSession) {
		
		return sqlSession.selectOne("adminMapper.selectAdminPaymentListCount");
	}

	public ArrayList<Payment> selectAdminPaymentList(SqlSessionTemplate sqlSession, PageInfo pi) {
		
		int limit = pi.getBoardLimit();
		int offset = (pi.getCurrentPage()-1) * limit;
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		return (ArrayList)sqlSession.selectList("adminMapper.selectAdminPaymentList",null, rowBounds);
	}

	/**
	 * 관리자 페이지 신고 관리 - 신고된 전체 커뮤니티 게시글 개수 조회 - 작성자 : 황혜진
	 * @param sqlSession
	 * @return
	 */
	public int reportedCommunityListCount(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("adminMapper.reportedCommunityListCount");
	}

	/**
	 * 관리자 페이지 신고 관리 - 신고된 전체 커뮤니티 게시글 조회 - 작성자 : 황혜진
	 * @param sqlSession
	 * @param pi
	 * @return
	 */
	public ArrayList<Report> reportedCommunityList(SqlSessionTemplate sqlSession, PageInfo pi) {
		
		int limit = pi.getBoardLimit();
		int offset = (pi.getCurrentPage() -1) * limit;
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return (ArrayList)sqlSession.selectList("adminMapper.reportedCommunityList", null, rowBounds);
	}

	/**
	 * 관리자 페이지 신고 관리 - 신고된 커뮤니티 게시글을 삭제(REPORT_STATUS='Y') 하는 메소드 - 작성자: 황혜진 
	 * @param sqlSession
	 * @param reportNo
	 * @return
	 */
	public int changeStatusReportedCommunity(SqlSessionTemplate sqlSession, int reportNo) {
		return sqlSession.update("adminMapper.changeStatusReportedCommunity", reportNo);
	}

	/**
	 * 관리자 페이지 신고 관리 - 신고된 커뮤니티 게시글을 삭제(COM_STATUS='N') 하는 메소드 - 작성자: 황혜진
	 * @param sqlSession
	 * @param comNo
	 * @return
	 */
	public int deleteReportedCommunity(SqlSessionTemplate sqlSession, int comNo) {
		return sqlSession.update("adminMapper.deleteReportedCommunity", comNo);
	}

	/**
	 * 관리자 페이지 신고 관리 - 신고 후 삭제 처리된 커뮤니티 게시글 개수 조회 - 작성자 : 황혜진
	 * @param sqlSession
	 * @return
	 */
	public int processedCommunityListCount(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("adminMapper.processedCommunityListCount");
	}

	/**
	 * 관리자 페이지 신고 관리 - 신고 후 삭제 처리된 커뮤니티 게시글 조회 - 작성자 : 황혜진
	 * @param sqlSession
	 * @param pi
	 * @return
	 */
	public ArrayList<Report> processedCommunityList(SqlSessionTemplate sqlSession, PageInfo pi) {
		int limit = pi.getBoardLimit();
		int offset = (pi.getCurrentPage() -1) * limit;
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return (ArrayList)sqlSession.selectList("adminMapper.processedCommunityList", null, rowBounds);
	}

	/**
	 * 관리자 페이지 신고 관리 - 신고된 게시글 되돌리기 (REPORT_STATUS='N') 메소드 - 작성자: 황혜진 
	 * @param sqlSession
	 * @param reportNo
	 * @return
	 */
	public int resetStatusReportedCommunity(SqlSessionTemplate sqlSession, int reportNo) {
		return sqlSession.update("adminMapper.resetStatusReportedCommunity", reportNo);
	}

	/**
	 * 관리자 페이지 신고 관리 - 신고된 게시글 삭제 상태 되돌리기 (COM_STATUS='Y') 메소드 - 작성자: 황혜진
	 * @param sqlSession
	 * @param comNo
	 * @return
	 */
	public int resetReportedCommunity(SqlSessionTemplate sqlSession, int comNo) {
		return sqlSession.update("adminMapper.resetReportedCommunity", comNo);
	}
}