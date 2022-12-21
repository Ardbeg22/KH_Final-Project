package com.urfavoriteott.ufo.community.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.urfavoriteott.ufo.common.model.vo.PageInfo;
import com.urfavoriteott.ufo.community.model.vo.Community;
import com.urfavoriteott.ufo.community.model.vo.CommunityReply;

@Repository
public class CommunityDao {

	/**
	 * 커뮤니티 게시글의 총 개수를 구하는 메소드 - 작성자 : 황혜진 
	 * @param sqlSession
	 * @return
	 */
	public int selectCommunityListCount(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("communityMapper.selectCommunityListCount");
	}

	/**
	 * 커뮤니티 리스트 조회 메소드(관리자 + 회원) - 작성자 : 황혜진
	 * @param sqlSession
	 * @param pi
	 * @return
	 */
	public ArrayList<Community> selectCommunityList(SqlSessionTemplate sqlSession, PageInfo pi) {
		int limit = pi.getBoardLimit();
		int offset = (pi.getCurrentPage() - 1) * limit;
      
		RowBounds rowBounds = new RowBounds(offset, limit);		
		
		return (ArrayList)sqlSession.selectList("communityMapper.selectCommunityList", null, rowBounds);
	}

	/**
	 * 커뮤니티 검색 조회용 메소드(검색조건에 부합하는 게시글 수 조회) 
	 * @param sqlSession
	 * @param map
	 * @return
	 */
	public int selectSearchCommCount(SqlSessionTemplate sqlSession, HashMap<String, String> map) {
		
		return sqlSession.selectOne("communityMapper.selectSearchCommCount", map);
	}
	

	/**
	 * 커뮤니티 검색 조회용 메소드(검색된 게시글 리스트 조회)
	 * @param sqlSession
	 * @param map
	 * @param pi
	 * @return
	 */
	public ArrayList<Community> selectSearchCommList(SqlSessionTemplate sqlSession, HashMap<String, String> map, PageInfo pi) {
		
		int limit = pi.getBoardLimit();
		int offset = (pi.getCurrentPage() - 1) * limit;
      
		RowBounds rowBounds = new RowBounds(offset, limit);		
		
		return (ArrayList)sqlSession.selectList("communityMapper.selectSearchCommList", map, rowBounds);
	}
	
	/**
	 * 커뮤니티 게시글 작성 메소드 - 작성자 : 황혜진
	 * @param sqlSession
	 * @param c : 작성할 커뮤니티 게시글의 정보
	 * @return
	 */
	public int insertCommunity(SqlSessionTemplate sqlSession, Community c) {
		
		return sqlSession.insert("communityMapper.insertCommunity", c);
	}
	
	/**
	 * 커뮤니티 게시글 조회수 증가 메소드(관리자 + 회원) - 작성자 : 황혜진
	 * @param sqlSession
	 * @param comNo : 조회수 증가할 커뮤니티 게시글 번호
	 * @return
	 */
	public int communityIncreaseCount(SqlSessionTemplate sqlSession, int comNo) {
		
		return sqlSession.update("communityMapper.communityIncreaseCount", comNo);
	}
	
	/**
	 * 커뮤니티 게시글 상세조회 메소드(관리자 + 회원) - 작성자 : 황혜진
	 * @param sqlSession
	 * @param comNo : 상세조회할 커뮤니티 게시글 번호
	 * @return
	 */
	public Community selectCommunity(SqlSessionTemplate sqlSession, int comNo) {
		
		return sqlSession.selectOne("communityMapper.selectCommunity", comNo);
	}

	/**
	 * 커뮤니티 게시글 삭제 메소드 - 작성자 : 황혜진
	 * @param sqlSession
	 * @param comNo : 삭제할 커뮤니티 게시글 번호
	 * @return
	 */
	public int deleteCommunity(SqlSessionTemplate sqlSession, int comNo) {
		
		return sqlSession.update("communityMapper.deleteCommunity", comNo);
	}

	/**
	 * 커뮤니티 수정 메소드 - 작성자 : 황혜진
	 * @param sqlSession
	 * @param c : 수정할 커뮤니티 게시글 정보
	 * @return
	 */
	public int updateCommunity(SqlSessionTemplate sqlSession, Community c) {
		
		return sqlSession.update("communityMapper.updateCommunity", c);
	}

	/**
	 * 커뮤니티 게시글 신고 버튼 클릭시 신고 - 작성자: 황혜진
	 * @param sqlSession
	 * @param map
	 * @return
	 */
	public int reportCommunity(SqlSessionTemplate sqlSession, HashMap<String, String> map) {
		return sqlSession.update("communityMapper.reportCommunity", map);
	}
	
	/**
	 * 커뮤니티 댓글 작성 메소드 - 작성자 : 황혜진
	 * @param sqlSession
	 * @param r : 작성할 댓글의 정보
	 * @return
	 */
	public int insertReply(SqlSessionTemplate sqlSession, CommunityReply r) {
		      
		return sqlSession.insert("communityMapper.insertReply", r);
	}	
	
	/**
	 * 커뮤니티 댓글 리스트 조회 메소드 - 작성자 : 황혜진
	 * @param sqlSession
	 * @param cno : 조회할 게시글의 댓글 정보
	 * @return
	 */
	public ArrayList<CommunityReply> selectReplyList(SqlSessionTemplate sqlSession, int cno) {
		      
		return (ArrayList)sqlSession.selectList("communityMapper.selectReplyList", cno);
	}	
	
	/**
	 * 댓글 삭제 서비스 - 작성자 : 황혜진
	 * @param sqlSession
	 * @param r : 삭제하고자할 커뮤니티 댓글 정보
	 * @return
	 */
	public int deleteReply(SqlSessionTemplate sqlSession, CommunityReply r) {
		return sqlSession.update("communityMapper.deleteReply", r);
	}
	
	/**
	 * 커뮤니티 댓글 신고를 눌렀을 때 사용할 메소드 - 작성자: 수빈
	 * @param sqlSession
	 * @param map
	 * @return
	 */
	public int reportReply(SqlSessionTemplate sqlSession, HashMap map) {
		return sqlSession.insert("communityMapper.reportReply", map);
	}
}