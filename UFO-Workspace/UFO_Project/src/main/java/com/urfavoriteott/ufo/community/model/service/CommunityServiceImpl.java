package com.urfavoriteott.ufo.community.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urfavoriteott.ufo.common.model.vo.PageInfo;
import com.urfavoriteott.ufo.community.model.dao.CommunityDao;
import com.urfavoriteott.ufo.community.model.vo.Community;
import com.urfavoriteott.ufo.community.model.vo.CommunityReply;

@Service
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Autowired
    private CommunityDao communityDao;
    
    /* ------------------------------------------------------------------------------------------------------------------------------ */

    /**
     *  게시글의 총 갯수 구하는 메소드 - 작성자 : 황혜진 
     */
	@Override
	public int selectCommunityListCount() {
		return communityDao.selectCommunityListCount(sqlSession);
	}

	/**
	 * 커뮤니티 리스트 조회 메소드(관리자 + 회원) - 작성자 : 황혜진
	 */
	@Override
	public ArrayList<Community> selectCommunityList(PageInfo pi) {
		return communityDao.selectCommunityList(sqlSession, pi);
	}
	
	/**
	 * 커뮤니티 검색 조회용 메소드(검색조건에 부합하는 게시글 수 조회) 
	 * @param map
	 * @return
	 */
	@Override
	public int selectSearchCommCount(HashMap<String, String> map) {
		return communityDao.selectSearchCommCount(sqlSession, map);
	};

	/**
	 * 커뮤니티 검색 조회용 메소드(검색된 게시글 리스트 조회)
	 * @param map
	 * @param pi
	 * @return
	 */
	@Override
	public ArrayList<Community> selectSearchCommList(HashMap<String, String> map, PageInfo pi) {
		return communityDao.selectSearchCommList(sqlSession, map, pi);
	}

	/**
	 * 커뮤니티 게시글 작성 메소드 - 작성자 : 황혜진
	 */
	@Override
	public int insertCommunity(Community c) {
		return communityDao.insertCommunity(sqlSession, c);
	}

	/**
	 * 커뮤니티 조회수 증가 메소드(관리자 + 회원) - 작성자 : 황혜진
	 */
	@Override
	public int communityIncreaseCount(int comNo) {
		return communityDao.communityIncreaseCount(sqlSession, comNo);
	}

	/**
	 * 커뮤니티 상세 조회 메소드(관리자 + 회원) - 작성자 : 황혜진
	 */
	@Override
	public Community selectCommunity(int comNo) {
		return communityDao.selectCommunity(sqlSession, comNo);
	}

	/**
	 * 커뮤니티 삭제 메소드 - 작성자 : 황혜진
	 */
	@Override
	public int deleteCommunity(int comNo) {
		return communityDao.deleteCommunity(sqlSession, comNo);
	}

	/**
	 * 커뮤니티 수정 메소드 - 작성자 : 황혜진
	 */
	@Override
	public int updateCommunity(Community c) {
		return communityDao.updateCommunity(sqlSession, c);
	}

	/**
	 * 커뮤니티 게시글 신고 버튼 클릭시 신고 - 작성자: 황혜진 
	 */
	@Override
	public int reportCommunity(HashMap<String, String> map) {
		return communityDao.reportCommunity(sqlSession, map);
	}
	
	/**
	 *  댓글 작성 서비스 - 작성자 : 황혜진
	 */
	@Override
	public int insertReply(CommunityReply r) {
		
		return communityDao.insertReply(sqlSession, r);
		
	}

	/**
	 * 댓글 리스트 조회 서비스 - 작성자 : 황혜진
	 */
	@Override
	public ArrayList<CommunityReply> selectReplyList(int cno) {
		
		return communityDao.selectReplyList(sqlSession, cno);
	}
	
	/**
	 * 댓글 삭제 서비스 - 작성자 : 황혜진
	 */
	@Override
	public int deleteReply(CommunityReply r) {
		return communityDao.deleteReply(sqlSession, r);
	}
	
	/**
	 * 커뮤니티 댓글 신고를 눌렀을 때 사용할 메소드 - 작성자: 수빈
	 */
	@Override
	public int reportReply(HashMap map) {
		return communityDao.reportReply(sqlSession, map);
	}

	
}