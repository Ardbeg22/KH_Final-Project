package com.urfavoriteott.ufo.member.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.urfavoriteott.ufo.common.model.vo.PageInfo;
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
	 * 비밀번호 재설정용 메소드 - 작성자 : 동민
	 * @param updatePassword : 비밀번호 재설정할 비밀번호 
	 * @return
	 */
	int passwordUpdate(String updatePassword);
	
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
	
}
