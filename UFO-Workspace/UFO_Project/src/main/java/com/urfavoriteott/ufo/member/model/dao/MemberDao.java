package com.urfavoriteott.ufo.member.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

import com.urfavoriteott.ufo.common.model.vo.PageInfo;
import com.urfavoriteott.ufo.community.model.vo.Community;
import com.urfavoriteott.ufo.contents.model.vo.Payment;
import com.urfavoriteott.ufo.contents.model.vo.Review;
import com.urfavoriteott.ufo.member.model.vo.Member;


@Component
public class MemberDao {

	/**
	 * 로그인용 메소드 - 작성자 : 동민
	 * @param sqlSession
	 * @param m : 로그인할 사용자의 계정
	 * @return
	 */
	public Member loginMember(SqlSessionTemplate sqlSession, Member m) {
		
		return sqlSession.selectOne("memberMapper.loginMember", m);
		
	}
	
	/**
	 * 회원가입용 메소드 - 작성자 : 동민
	 * @param sqlSession
	 * @param m : 회원가입할 사용자의 계정
	 * @return
	 */
	public int insertMember(SqlSessionTemplate sqlSession, Member m) {
		
		return sqlSession.insert("memberMapper.insertMember", m);
	}
	
	public int nicknameCheck(SqlSessionTemplate sqlSession, String checkNickname) {
		
		return sqlSession.selectOne("memberMapper.nicknameCheck", checkNickname);
	}
	
	/**
	 * 아이디 중복체크용 메소드 - 작성자 : 동민
	 * @param sqlSession
	 * @param checkId : 중복체크할 사용자의 아이디
	 * @return
	 */
	public int idCheck(SqlSessionTemplate sqlSession, String checkId) {
		
		return sqlSession.selectOne("memberMapper.idCheck", checkId);
		
	}
	
	/**
	 * 비밀번호 재설정 메소드 - 작성자 : 동민
	 * @param sqlSession
	 * @param m : 비밀번호 재설정할 사용자 정보
	 * @return
	 */
	public int passwordUpdate(SqlSessionTemplate sqlSession, Member m) {
		
		return sqlSession.update("memberMapper.passwordUpdate", m);
	}
  
  /**
	 * 카카오 간편로그인 정보 저장 메소드 - 작성자 : 동민
	 * @param sqlSession
	 * @param userInfo : 카카오 로그인 정보 저장할 데이터
	 */
	public void kakaoinsert(SqlSessionTemplate sqlSession, HashMap<String, Object> userInfo) {
		sqlSession.insert("memberMapper.kakaoInsert",userInfo);
	}

	/**
	 * 카카오 간편로그인 정보 확인 메소드 - 작성자 : 동민 
	 * @param sqlSession
	 * @param userInfo : 카카오 로그인 정보 확인할 데이터
	 * @return
	 */
	public Member findkakao(SqlSessionTemplate sqlSession, HashMap<String, Object> userInfo) {
		System.out.println("RN:"+userInfo.get("nickname"));
		System.out.println("RE:"+userInfo.get("email"));
		Member m = sqlSession.selectOne("memberMapper.findKakao", userInfo);
		System.out.println("m : " + m);
		return m;
		// return sqlSession.selectOne("memberMapper.findKakao", userInfo);
  }
	
	/**
	 * 사용자 - 회원 정보 수정용 메소드 - 작성자 : 장희연
	 * @param sqlSession
	 * @param m : 사용자의 수정할 회원 정보(아이디, 회원번호, 프로필, 닉네임)
	 * @return
	 */
	public int updateMember(SqlSessionTemplate sqlSession, Member m) {
		
		return sqlSession.update("memberMapper.updateMember", m);
	}
	
	/**
	 * 사용자 - 회원 탈퇴 처리용 메소드 - 작성자 : 장희연 
	 * @param sqlSession
	 * @param userNo : 로그인한 사용자(탈퇴할 회원)의 회원 번호
	 * @return
	 */
	public int deleteMember(SqlSessionTemplate sqlSession, int userNo) {
		
		return sqlSession.update("memberMapper.deleteMember", userNo);
	}
	
	/**
	 * 마이 페이지 별점 및 코멘트 내역 조회를 위한 페이징바(select) - 작성자 : 수빈
	 * @param sqlSession
	 * @param loginUser
	 * @return
	 */
	public int selectMyCommentListCount(SqlSessionTemplate sqlSession, String loginUserNo) {
		
		return sqlSession.selectOne("memberMapper.selectMyCommentListCount", loginUserNo);
	}
	
	/**
	 * 마이 페이지 별점 및 코멘트 내역에서 코멘트 조회 (select) - 작성자 : 수빈
	 * @param sqlSession
	 * @param pi
	 * @param loginUser
	 * @return
	 */
	public ArrayList<Review> selectMyCommentList(SqlSessionTemplate sqlSession, PageInfo pi, String loginUserNo) {
		
		int limit = pi.getBoardLimit();
		int offset = (pi.getCurrentPage() -1) * limit;
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return (ArrayList)sqlSession.selectList("memberMapper.selectMyCommentList", loginUserNo, rowBounds);
	}
	
	/**
	 * 마이 페이지 별점 및 코멘트 내역에서 선택된 리뷰 삭제 메소드 - 작성자: 수빈
	 * @param sqlSession
	 * @param checkNum
	 * @return
	 */
	public int deleteMyComment(SqlSessionTemplate sqlSession, int checkNum) {
		return sqlSession.update("memberMapper.deleteMyComment", checkNum);
	}

	/**
	 * 작성자: 성현 / 마이페이지 결제내역 페이징처리에 필요한 listCount 조회
	 * @param sqlSession
	 * @param loginUserNo
	 * @return
	 */
	public int selectMyPaymentListCount(SqlSessionTemplate sqlSession, int loginUserNo) {
		
		return sqlSession.selectOne("memberMapper.selectMyPaymentListCount", loginUserNo);
	}

	/**
	 * 작성자: 성현 / 마이페이지 결제내역 조회
	 * @param sqlSession
	 * @param pi
	 * @param loginUserNo
	 * @return
	 */
	public ArrayList<Payment> selectMyPaymentList(SqlSessionTemplate sqlSession, PageInfo pi, int loginUserNo) {
		
		int limit = pi.getBoardLimit();
		int offset = (pi.getCurrentPage() - 1) * limit;
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return (ArrayList)sqlSession.selectList("memberMapper.selectMyPaymentList", loginUserNo, rowBounds);
	}

	public Payment payChecker(SqlSessionTemplate sqlSession, Member loginUser) {
		return sqlSession.selectOne("memberMapper.payChecker",loginUser);
	}
	
	/**
	 * 마이 페이지 커뮤니티 내가 쓴 글 개수 구하는 메소드 - 작성자 : 황혜진
	 * @param sqlSession
	 * @param userNo
	 * @return
	 */
	public int selectMyCommunityListCount(SqlSessionTemplate sqlSession, int userNo) {
		
		return sqlSession.selectOne("memberMapper.selectMyCommunityListCount", userNo);
	}

	/**
	 * 마이 페이지 커뮤니티 내가 쓴 글 전체 조회 - 작성자 : 황혜진
	 * @param sqlSession
	 * @param pi
	 * @param userNo
	 * @return
	 */
	public ArrayList<Community> selectMyCommunityList(SqlSessionTemplate sqlSession, PageInfo pi, int userNo) {
		
		int limit = pi.getBoardLimit();
		int offset = (pi.getCurrentPage() - 1) * limit;
      
		RowBounds rowBounds = new RowBounds(offset, limit);		
		
		return (ArrayList)sqlSession.selectList("memberMapper.selectMyCommunityList", userNo, rowBounds);
	}
	
	/**
	 * 마이페이지 커뮤니티 글 내역 중 선택된 글 삭제 - 작성자: 황혜진 
	 * @param sqlSession
	 * @param checkNum
	 * @return
	 */
	public int deleteMyCommunity(SqlSessionTemplate sqlSession, int checkNum) {
		return sqlSession.update("memberMapper.deleteMyCommunity", checkNum);
	}

	/**
	 *  마이 페이지 커뮤니티 내가 쓴 댓글 개수 구하는 메소드  - 작성자 : 황혜진
	 * @param sqlSession
	 * @param userNo
	 * @return
	 */
	public int selectMyCommunityReplyListCount(SqlSessionTemplate sqlSession, int userNo) {
		return sqlSession.selectOne("memberMapper.selectMyCommunityReplyListCount", userNo);
	}

	/**
	 * 마이 페이지 커뮤니티 내가 쓴 댓글 전체 조회 - 작성자 : 황혜진
	 * @param sqlSession
	 * @param pi
	 * @param userNo
	 * @return
	 */
	public ArrayList<Community> selectMyCommunityReplyList(SqlSessionTemplate sqlSession, PageInfo pi, int userNo) {
		int limit = pi.getBoardLimit();
		int offset = (pi.getCurrentPage() - 1) * limit;
      
		RowBounds rowBounds = new RowBounds(offset, limit);		
		
		return (ArrayList)sqlSession.selectList("memberMapper.selectMyCommunityReplyList", userNo, rowBounds);
	}

	/**
	 * 마이 페이지 댓글 내역 중 선택된 댓글 삭제 - 작성자: 황혜진
	 * @param sqlSession
	 * @param checkNum
	 * @return
	 */
	public int deleteMyCommunityReply(SqlSessionTemplate sqlSession, int checkNum) {
		return sqlSession.update("memberMapper.deleteMyCommunityReply", checkNum);
	}
}
