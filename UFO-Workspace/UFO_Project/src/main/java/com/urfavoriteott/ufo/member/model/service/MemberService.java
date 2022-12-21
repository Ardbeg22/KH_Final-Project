package com.urfavoriteott.ufo.member.model.service;

import java.util.ArrayList;

import com.urfavoriteott.ufo.common.model.vo.PageInfo;
import com.urfavoriteott.ufo.community.model.vo.Community;
import com.urfavoriteott.ufo.contents.model.vo.Payment;
import com.urfavoriteott.ufo.contents.model.vo.Review;
import com.urfavoriteott.ufo.member.model.vo.Member;


public interface MemberService {

	/**
	 * 로그인용 메소드 - 작성자 : 동민
	 * @param m : 로그인할 사용자의 계정
	 * @return
	 */
	Member loginMember(Member m);
	
	/**
	 * 회원가입용 메소드 - 작성자 : 동민
	 * @param m : 회원가입할 사용자의 계정
	 * @return
	 */
	int insertMember(Member m);
	
	/**
	 * 아이디 중복 체크용 메소드 - 작성자 : 동민
	 * @param checkId : 중복체크할 사용자의 아이디
	 * @return
	 */
	int idCheck(String checkId);
	
	/**
	 * 비밀번호 재설정 메소드 - 작성자 : 동민
	 * @param m : 비밀번호 재설정할 사용자 정보
	 * @return
	 */
	int passwordUpdate(Member m);
  
  /**
	 * 카카오 간편로그인 메소드 - 작성자 : 동민
	 * @param authorize_code
	 * @return
	 */
	String getAccessToken(String authorize_code);
	
	/**
	 * 카카오 간편로그인 메소드2 - 작성자 : 동민
	 * @param access_Token
	 * @return
	 */
	Member getUserInfo(String access_Token);
	
	/**
	 * 사용자 - 닉네임 중복체크용 메소드 - 작성자 : 장희연
	 * @param checkNickname : 중복체크할 사용자의 닉네임
	 * @return
	 */
	int nicknameCheck(String checkNickname);
	
	/**
	 * 사용자 - 회원 정보 수정용 메소드 - 작성자 : 장희연
	 * @param m : 사용자의 수정할 회원 정보(아이디, 회원번호, 프로필, 닉네임)
	 * @return
	 */
	int updateMember(Member m);
	
	/**
	 * 사용자 - 회원 탈퇴 처리용 메소드 - 작성자 : 장희연
	 * @param userNo : 로그인한 사용자(탈퇴할 회원)의 회원 번호
	 * @return
	 */
	int deleteMember(int userNo);
  
	/**
	 * 마이 페이지 별점 및 코멘트 내역 조회를 위한 페이징바(select) - 작성자 : 수빈
	 * @param loginUserNo
	 * @return
	 */
	int selectMyCommentListCount(String loginUserNo);
	
	/**
	 * 마이 페이지 별점 및 코멘트 내역에서 코멘트 조회 (select) - 작성자 : 수빈
	 * @param pi
	 * @param loginUserNo
	 * @return
	 */
	ArrayList<Review> selectMyCommentList(PageInfo pi, String loginUserNo);
	
	/**
	 * 마이 페이지 별점 및 코멘트 내역에서 선택된 리뷰 삭제 메소드 - 작성자: 수빈
	 * @param checkNum
	 * @return
	 */
	int deleteMyComment(int checkNum);

	/** 작성자: 성현 / 마이페이지 결제내역 조회 페이징 처리에 필요한 결제내역 수 조회
	 * @param loginUserNo
	 * @return
	 */
	int selectMyPaymentListCount(int loginUserNo);

	/**
	 * 작성자: 성현 / 마이페이지 결제내역 조회
	 * @param pi
	 * @param loginUserNo
	 * @return
	 */
	ArrayList<Payment> selectMyPaymentList(PageInfo pi, int loginUserNo);

	Payment payChecker(Member loginUser);
	
	/**
	 * 마이 페이지 커뮤니티 내가 쓴 글 총 개수 구하는 메소드 - 작성자 : 황혜진
	 * @param userNo
	 * @return
	 */
	int selectMyCommunityListCount(int userNo);

	/**
	 * 마이 페이지 커뮤니티 내가 쓴 글 전체 조회 - 작성자 : 황혜진
	 * @param pi
	 * @param userNo 
	 * @return
	 */
	ArrayList<Community> selectMyCommunityList(PageInfo pi, int userNo);

	/**
	 * 마이 페이지 커뮤니티 글 내역 중 선택된 글 삭제 - 작성자: 황혜진 
	 * @param checkNum : 선택된 글번호 
	 * @return
	 */
	int deleteMyCommunity(int checkNum);

	/**
	 *  마이 페이지 커뮤니티 내가 쓴 댓글 개수 구하는 메소드  - 작성자 : 황혜진
	 * @param userNo
	 * @return
	 */
	int selectMyCommunityReplyListCount(int userNo);

	/**
	 * 마이 페이지 커뮤니티 내가 쓴 댓글 전체 조회 - 작성자 : 황혜진
	 * @param pi
	 * @param userNo
	 * @return
	 */
	ArrayList<Community> selectMyCommunityReplyList(PageInfo pi, int userNo);

	/**
	 * 마이 페이지 댓글 내역 중 선택된 댓글 삭제 - 작성자: 황혜진
	 * @param checkNum : 선택된 댓글 번호 
	 * @return
	 */
	int deleteMyCommunityReply(int checkNum); 
}
