package com.urfavoriteott.ufo.community.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.urfavoriteott.ufo.common.model.vo.PageInfo;
import com.urfavoriteott.ufo.community.model.vo.Community;
import com.urfavoriteott.ufo.community.model.vo.CommunityReply;

public interface CommunityService {

	// 커뮤니티 게시판 리스트 조회 서비스 + 페이징 처리
	/**
	 * 게시글의 총 갯수 구하는 메소드 - 작성자 : 황혜진 
	 * @return
	 */
	int selectCommunityListCount();
	
	/**
	 * 커뮤니티 리스트 조회 메소드(관리자 + 회원) - 작성자 : 황혜진
	 * @param pi
	 * @return
	 */
	ArrayList<Community> selectCommunityList(PageInfo pi);
	
	/**
	 * 커뮤니티 검색 조회용 메소드(검색조건에 부합하는 게시글 수 조회) 
	 * @param map
	 * @return
	 */
	int selectSearchCommCount(HashMap<String, String> map);
	
	/**
	 * 커뮤니티 검색 조회용 메소드(검색된 게시글 리스트 조회)
	 * @param map
	 * @param pi
	 * @return
	 */
	ArrayList<Community> selectSearchCommList(HashMap<String, String> map, PageInfo pi);
	
	/**
	 * 커뮤니티 게시글 작성 메소드 - 작성자 : 황혜진
	 * @param c : 작성할 커뮤니티 게시글 정보
	 * @return
	 */
	int insertCommunity(Community c);

	
	// 커뮤니티 상세 조회 서비스
	/**
	 * 커뮤니티 조회수 증가 메소드(관리자 + 회원) - 작성자 : 황혜진
	 * @param comNo : 조회수 증가시킬 커뮤니티 게시글 번호
	 * @return
	 */

	int communityIncreaseCount(int comNo);

	/**
	 * 커뮤니티 상세 조회 메소드(관리자 + 회원) - 작성자 : 황혜진
	 * @param comNo : 상세조회할 커뮤니티 게시글 번호
	 * @return
	 */
	Community selectCommunity(int comNo);

	/**
	 * 커뮤니티 삭제 메소드 - 작성자 : 황혜진
	 * @param comNo : 삭제하고자할 커뮤니티 게시글 번호
	 * @return
	 */
	int deleteCommunity(int comNo);

	/**
	 * 커뮤니티 수정 메소드 - 작성자 : 황혜진
	 * @param c : 수정하고자할 커뮤니티 게시글 정보
	 * @return
	 */
	int updateCommunity(Community c);

	/**
	 * 커뮤니티 게시글 신고 버튼 클릭시 신고 - 작성자: 황혜진 
	 * @param reportReason
	 * @param form_loginUserNo
	 * @param form_communityUserNo
	 * @param form_comNo
	 * @param model
	 * @return
	 */
	int reportCommunity(HashMap<String, String> map);
	
	/**
	 * 댓글 작성 서비스 - 작성자 : 황혜진
	 * @param r : 작성할 댓글의 정보
	 * @return
	 */
	int insertReply(CommunityReply r);
	
	/**
	 * 댓글 리스트 조회 서비스 - 작성자 : 황혜진
	 * @param cno : 작성할 게시글의 댓글 정보
	 * @return
	 */
	ArrayList<CommunityReply> selectReplyList(int cno);
	
	/**
	 * 댓글 삭제 서비스 - 작성자 : 황혜진
	 * @param r : 삭제하고자할 커뮤니티 댓글 정보
	 * @return
	 */
	int deleteReply(CommunityReply r);
	
	/**
	 * 커뮤니티 댓글 신고를 눌렀을 때 사용할 메소드 - 작성자: 수빈
	 * @param map
	 * @return
	 */
	int reportReply(HashMap map);

	
}