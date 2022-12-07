package com.urfavoriteott.urfavoriteott.member.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import com.urfavoriteott.ufo.common.model.vo.PageInfo;
import com.urfavoriteott.ufo.member.model.dao.MemberDao;
import com.urfavoriteott.ufo.member.model.vo.Member;

=======
import com.urfavoriteott.urfavoriteott.common.model.vo.PageInfo;
import com.urfavoriteott.urfavoriteott.member.model.dao.MemberDao;
import com.urfavoriteott.urfavoriteott.member.model.vo.Member;
>>>>>>> upstream/main

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private MemberDao memberDao;
	
	@Override
	public Member loginMember(Member m) {
		return memberDao.loginMember(sqlSession, m);
	}
	
	@Override
<<<<<<< HEAD
	public int insertMember(Member m) {
		
		return memberDao.insertMember(sqlSession, m);
		
	}
	
	@Override
=======
>>>>>>> upstream/main
	public int nicknameCheck(String checkNickname) {
		
		return memberDao.nicknameCheck(sqlSession, checkNickname);
	}

	@Override
	public int updateMember(Member m) {
		
		return memberDao.updateMember(sqlSession, m);
	}
	
	@Override
	public int selectListCount() {
		
		return memberDao.selectListCount(sqlSession);
	}

	@Override
	public ArrayList<Member> selectList(PageInfo pi) {
		
		return memberDao.selectList(sqlSession, pi);
	}

	@Override
	public int selectSearchCount(HashMap<String, String> map) {
		
		return memberDao.selectSearchCount(sqlSession, map);
	}

	@Override
	public ArrayList<Member> selectSearchList(HashMap<String, String> map, PageInfo pi) {
		
		return memberDao.selectSearchList(sqlSession, map, pi);
	}

	@Override
	public int updatePwd(int userNo) {
		
		return memberDao.updatePwd(sqlSession, userNo);
	}
<<<<<<< HEAD
	
	@Override
	public int passWordUpdate(String updatePassword) {
		return 0;
	}
=======
>>>>>>> upstream/main

	@Override
	public int deleteMember(int userNo) {
		
		return memberDao.deleteMember(sqlSession, userNo);
	}


	
}
