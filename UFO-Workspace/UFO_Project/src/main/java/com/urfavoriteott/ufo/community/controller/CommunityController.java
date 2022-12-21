package com.urfavoriteott.ufo.community.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.urfavoriteott.ufo.common.model.vo.PageInfo;
import com.urfavoriteott.ufo.common.template.Pagination;
import com.urfavoriteott.ufo.community.model.service.CommunityService;
import com.urfavoriteott.ufo.community.model.vo.Community;
import com.urfavoriteott.ufo.community.model.vo.CommunityReply;

@Controller
public class CommunityController {

    @Autowired
    private CommunityService communityService;
    
    /**
	 * 커뮤니티 리스트 조회 메소드 - 작성자 : 황혜진
	 * @param currentPage
	 * @param model
	 * @return
	 */
	@RequestMapping("communityList.co")
	public String selectCommunityList(@RequestParam(value="cpage", defaultValue="1") int currentPage, Model model) {
		
		int listCount = communityService.selectCommunityListCount();
		
		int pageLimit = 10;
		int boardLimit = 10;
		
		PageInfo pi = Pagination.getPageInfo(listCount, currentPage, pageLimit, boardLimit);
		
		ArrayList<Community> list = communityService.selectCommunityList(pi);
		
	    model.addAttribute("pi", pi);
	    model.addAttribute("list", list);
	    
	    return "community/communityListView";
	}
	
	/**
	 * 커뮤니티 리스트 검색 메소드
	 * @param currentPage
	 * @param condition
	 * @param searchword
	 * @param model
	 * @return
	 */
	@RequestMapping("search.co")
	public String selectSearchCommList(@RequestParam(value="cpage", defaultValue="1")int currentPage, String condition, String searchword, Model model) {
		
		HashMap<String, String> map = new HashMap<>();
		map.put("condition", condition);
		map.put("searchword", searchword);
		System.out.println("condition : " + condition);
		System.out.println("searchword :" + searchword);
		
		int searchCount = communityService.selectSearchCommCount(map);
		int pageLimit = 10;
		int boardLimit = 10;
		PageInfo pi = Pagination.getPageInfo(searchCount, currentPage, pageLimit, boardLimit);
		
		ArrayList<Community> list = communityService.selectSearchCommList(map, pi);
		System.out.println(searchCount);
		System.out.println(list);
		
		model.addAttribute("list", list);
		model.addAttribute("pi", pi);
		
		model.addAttribute("condition", condition);
		model.addAttribute("searchword", searchword);
		
		return "community/communityListView";
	}
	
	/**
	 * 커뮤니티 게시글 등록 페이지 이동 메소드 - 작성자 : 황혜진
	 * @return
	 */
	 @RequestMapping("communityEnrollForm.co")
	 public String communityEnrollForm() {
		 
		 return "community/communityEnrollForm";
	 }
	 
	 /**
	  * 커뮤니티 게시글 등록 메소드 - 작성자 : 황혜진
	  * @param c : 등록할 커뮤니티 게시물
	  * @param session
	  * @param mv
	  * @return
	  */
	 @RequestMapping("communityEnroll.co")
	 public ModelAndView insertCommunity(Community c, HttpSession session, ModelAndView mv) {
	 
		 int result = communityService.insertCommunity(c);
		 
		 if(result > 0) { // 성공
			 
			 session.setAttribute("alertMsg", "성공적으로 게시글이 등록되었습니다.");
			 
			 mv.setViewName("redirect:/communityList.co");
			 
		 }
		 else {
			 
			 mv.addObject("errorMsg", "커뮤니티 게시글 작성 실패").setViewName("common/errorPage");
		 }
	 
		 return mv;
		 
	 }
	 
	 /**
	  * 커뮤니티 상세조회 메소드 - 작성자 : 황혜진
	  * @param cno : 상세조회할 커뮤니티 게시물
	  * @param mv
	  * @return
	  */
	 @RequestMapping("communityDetail.co")
	 public ModelAndView selectCommunity(int cno, ModelAndView mv) {
		 
		 int result = communityService.communityIncreaseCount(cno);
	
		 
		 if(result > 0) { // 조회수 증가 성공시
			 
			 Community c = communityService.selectCommunity(cno);
			 
			 mv.addObject("c", c).setViewName("community/communityDetailView");
		 }
		 
		 else { // 실패
			 
			 mv.addObject("errorMsg", "커뮤니티 상세조회 실패").setViewName("common/errorPage");
			 
		 }
		 
		 return mv;
		 
	 }
	 
	 /**
	  * 커뮤니티 게시글 삭제 메소드 - 작성자 : 황혜진
	  * @param cno : 삭제할 커뮤니티 게시물
	  * @param session
	  * @param model
	  * @return
	  */
	 @RequestMapping("communityDelete.co")
	 public String deleteCommunity(int cno, HttpSession session, Model model) {
		 
		 int result = communityService.deleteCommunity(cno);
		 
			 if(result > 0) { // 커뮤니티 게시글 삭제 성공
				 
				 session.setAttribute("alertMsg", "성공적으로 게시글이 삭제되었습니다.");
				 return "redirect:/communityList.co";
			 }
			 
			 else { // 커뮤니티 게시글 삭제 실패
				 
				 model.addAttribute("errorMsg", "커뮤니티 게시글 삭제 실패");
				 
				 return "common/errorPage";
			 }
		 }
	 
	 /**
	  * 커뮤니티 게시글 수정하기 페이지 이동 메소드 - 작성자 : 황혜진
	  * @param cno : 수정할 커뮤니티 게시글 번호
	  * @param model
	  * @return
	  */
	 @RequestMapping("communityUpdateForm.co")
	 public String updateForm(@RequestParam(value="cno")  int cno, Model model) {
		 
		 Community c  = communityService.selectCommunity(cno);
		 
		 model.addAttribute("c", c);
		 
		 return "community/communityUpdateForm";
	 }
	 
	 /**
	  * 커뮤니티 게시글 수정 메소드 - 작성자 : 황혜진
	  * @param c : 수정할 커뮤니티 게시글 정보
	  * @param session
	  * @param model
	  * @return
	  */
	 @RequestMapping("communityUpdate.co")
	 public String updateCommunity(Community c, HttpSession session, Model model) {
		 
		 int result = communityService.updateCommunity(c);
		 
		 // System.out.println(c.getComTitle());
		 // System.out.println(c.getComContent());
		 // System.out.println(c.getComNo());
		 
		 if(result > 0) { // 커뮤니티 게시글 수정 성공
			 
			 session.setAttribute("alertMsg", "성공적으로 커뮤니티 게시글 수정에 성공하였습니다.");
			 
			 return "redirect:/communityDetail.co?cno=" + c.getComNo();
		 }
		 
		 else { // 커뮤니티 게시글 수정 실패
			 
			 model.addAttribute("errorMsg", "커뮤니티 게시글 수정 실패");
			 
			 // System.out.println(result);
			 return "common/errorPage";
			 
		 }
	 }

		/**
		 * 커뮤니티 게시글 신고 버튼 클릭시 신고 - 작성자: 황혜진 
		 * @param reportReason
		 * @param form_loginUserNo
		 * @param form_communityUserNo
		 * @param form_comNo
		 * @param model
		 * @return
		 */
		    @ResponseBody
		    @RequestMapping(value="reportCommunity.co", produces="text/html; charset=UTF-8")
		    public String reportCommunity(String reportReason, String form_loginUserNo, String form_communityUserNo, String form_comNo, Model model) {
		    	
		    	// System.out.println("reportReason: " + reportReason);
		    	// System.out.println("loginUserNo: " + form_loginUserNo);
		    	// System.out.println("commentUserNo: " + form_commentUserNo);
		    	// System.out.println("reviewNo: " + form_reviewNo);
		    	
		    	HashMap<String, String> map = new HashMap<>();
		    	map.put("reportReason", reportReason);
		    	map.put("form_loginUserNo", form_loginUserNo);
		    	map.put("form_communityUserNo", form_communityUserNo);
		    	map.put("form_comNo", form_comNo);

		    	int result = communityService.reportCommunity(map);
		    	
		    	if(result > 0) {
			    	
			    	return "<script>"
			    		 + "alert('성공적으로 신고 접수되었습니다.');"
			    		 + "location.href='communityList.co';"
			    		 + "</script>";
			    	
		    	} else {
		    		
		    		return "<script>"
		   	    		 + "alert('신고 접수에 실패하였습니다. 잠시 후 다시 시도해 주세요.');"
		   	    		 + "location.href='communityList.co';"
		   	    		 + "</script>";
		    		
		    	}
		    }
		    
		    /**
			  * 커뮤니티 댓글 등록 메소드 - 작성자 : 황혜진
			  * @param r : 작성할 커뮤니티 댓글 정보
			  * @return
			  */
			 @ResponseBody
			 @RequestMapping(value="rinsert.co", produces="text/html; charset=UTF-8") // => produces 생략 가능
			 public String ajaxInsertReply(CommunityReply r) {
			      
			    // System.out.println(r);
			      
			    int result = communityService.insertReply(r);
			      
			    return (result > 0) ? "success" : "fail";  
			      
			 }	 
			 
			/**
			 * 커뮤니티 댓글 리스트 조회 메소드 - 작성자 : 황혜진
			 * @param cno : 리스트 조회할 게시글의 댓글 리스트 정보
			 * @return
			 */
		    @ResponseBody
		    @RequestMapping(value="rlist.co", produces="application/json; charset=UTF-8")
		    public String ajaxSelectReplyList(int cno) {
		      
		       ArrayList<CommunityReply> list = communityService.selectReplyList(cno);
		       
		       // System.out.println(list);
		       
		       return new Gson().toJson(list);
		    }
		    
			/**
			* 커뮤니티 댓글 삭제 메소드  - 작성자 : 황혜진
			* @param r : 작성할 댓글 삭제 정보
			* @return
			*/
			@ResponseBody
			@RequestMapping(value="rdelete.co", produces="text/html; charset=UTF-8") // => produces 생략 가능
			public String ajaxDeleteReply(CommunityReply r) {
				
			// System.out.println(r);
				
			int result = communityService.deleteReply(r);
				
			return (result > 0) ? "success" : "fail";
			
			}

		    /**
		     * 커뮤니티 댓글 신고를 눌렀을 때 사용할 메소드 - 작성자: 수빈
		     * @param reportReason
		     * @param form_comRplNo
		     * @param form_comRplUserNo
		     * @param form_loginUserNo
		     * @return
		     */
		    @ResponseBody
		    @RequestMapping(value="reportReply.co", produces="text/html; charset=UTF-8")
		    public String reportReply(String form_cno, String reportReason, String form_comRplNo, String form_comRplUserNo, String form_loginUserNo) {
		    	
		    	// System.out.println(reportReason);
		    	// System.out.println(form_comRplNo);
		    	// System.out.println(form_comRplUserNo);
		    	// System.out.println(form_loginUserNo);
		    	
		    	HashMap<String, String> map = new HashMap<>();
		    	map.put("reportReason", reportReason);
		    	map.put("comRplNo", form_comRplNo);
		    	map.put("comRplUserNo", form_comRplUserNo);
		    	map.put("loginUserNo", form_loginUserNo);
		    	
		    	int result = communityService.reportReply(map);
		    	
		    	if(result > 0) {

		        	return "<script>"
		   	    		 + "alert('성공적으로 신고 접수되었습니다.');"
		   	    		 + "location.href='communityDetail.co?cno=" + form_cno + "';"
		   	    		 + "</script>";
		    		
		    	} else {
		    		
		    		return "<script>"
		      	    		 + "alert('신고 접수에 실패하셨습니다. 잠시 후 다시 시도해 주세요.');"
		      	    		+ "location.href='communityDetail.co?cno=" + form_cno + "';"
		      	    		 + "</script>";
		    		
		    	}
		    }
	 }
