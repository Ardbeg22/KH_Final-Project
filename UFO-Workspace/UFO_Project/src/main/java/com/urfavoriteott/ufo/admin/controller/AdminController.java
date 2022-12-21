package com.urfavoriteott.ufo.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.urfavoriteott.ufo.admin.model.service.AdminService;
import com.urfavoriteott.ufo.admin.model.vo.Report;
import com.urfavoriteott.ufo.admin.model.vo.Sales;
import com.urfavoriteott.ufo.common.model.vo.PageInfo;
import com.urfavoriteott.ufo.common.template.Pagination;
import com.urfavoriteott.ufo.contents.model.vo.Payment;
import com.urfavoriteott.ufo.contents.model.vo.Review;
import com.urfavoriteott.ufo.member.model.vo.Member;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@RequestMapping("admin_stat.st")
	public String statistics() {
		
		return "admin/adminStatisticsView";
	}
	
	/**
	 * 관리자 - 회원 리스트 조회용 메소드 - 작성자 : 장희연
	 * @param currentPage : 요청한 페이지번호
	 * @param model
	 * @return
	 */
	@RequestMapping("admin_list.me")
	public String selectList(@RequestParam(value="cpage", defaultValue="1")int currentPage, Model model) {
		
		int listCount = adminService.selectListCount();
		int pageLimit = 10;
		int boardLimit = 10;
		PageInfo pi = Pagination.getPageInfo(listCount, currentPage, pageLimit, boardLimit);
		
		ArrayList<Member> list = adminService.selectList(pi);
		
		model.addAttribute("list", list);
		model.addAttribute("pi", pi);
		
		return "admin/adminMemberListView";
	}
	
	/**
	 * 관리자 - 회원 검색 조회용 메소드 - 작성자 : 장희연
	 * @param currentPage : 요청한 페이지번호
	 * @param keyword : 검색조건(아이디, 닉네임)
	 * @param searchword : 검색어
	 * @param model
	 * @return
	 */
	@RequestMapping("admin_search.me")
	public String selectSearchList(@RequestParam(value="cpage", defaultValue="1")int currentPage, String keyword, String searchword, Model model) {
		
		HashMap<String, String> map = new HashMap<>();
		map.put("keyword", keyword);
		map.put("searchword", searchword);
		
		int searchCount = adminService.selectSearchCount(map);
		int pageLimit = 10;
		int boardLimit = 10;
		PageInfo pi = Pagination.getPageInfo(searchCount, currentPage, pageLimit, boardLimit);
		
		ArrayList<Member> list = adminService.selectSearchList(map, pi);
		
		model.addAttribute("list", list);
		model.addAttribute("pi", pi);
		
		model.addAttribute("keyword", keyword);
		model.addAttribute("searchword", searchword);
		
		return "admin/adminMemberListView";
	}
	
	/**
	 * 관리자 - 비밀번호 초기화용 메소드 - 작성자 : 장희연
	 * @param userNo : 비밀번호를 초기화할 회원의 회원번호
	 * @param model 
	 * @param session
	 * @return
	 */
	
	@RequestMapping("admin_updatePwd.me")
	public String updatePwd(int userNo) {
		
		int result = adminService.updatePwd(userNo);
		
		return (result > 0) ? "success" : "fail";
	}
	
	/**
	 * 관리자 - 회원 탈퇴 처리용 메소드 - 작성자 : 장희연
	 * @param userNo : 탈퇴 처리할 회원의 회원번호
	 * @param model
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("admin_delete.me")
	public String deleteMember(int userNo, Model model, HttpSession session) {
		
		int result = adminService.deleteMember(userNo);
		
		return (result > 0) ? "success" : "fail";
	}
	
	/**
	 * 관리자 - 한달이용권 월별 매출 메소드 - 작성자 : 장희연
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="salesPerMonthOnce.st", produces="application/json; charset=UTF-8")
	public String selectSalesPerMonthOnce() {
		
		ArrayList<Sales> list = adminService.selectSalesPerMonthOnce();
		
		return new Gson().toJson(list);
	}
	
	/**
	 * 관리자 - 월간구독권 월별 매출 메소드 - 작성자 : 장희연
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="salesPerMonthSub.st", produces="application/json; charset=UTF-8")
	public String selectSalesPerMonthSub() {
		
		ArrayList<Sales> list = adminService.selectSalesPerMonthSub();
		
		return new Gson().toJson(list);
	}
	
	/**
	 * 관리자 - 월별 매출 메소드 - 작성자 : 장희연
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="salesPerMonth.st", produces="application/json; charset=UTF-8")
	public String selectSalesPerMonth() {
		
		ArrayList<Sales> list = adminService.selectSalesPerMonth();
		
		return new Gson().toJson(list);
	}
	
	/**
	 * 관리자 - 한달이용권 연도별 매출 메소드 - 작성자 : 장희연
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="salesPerYearOnce.st", produces="application/json; charset=UTF-8")
	public String selectSalesPerYearOnce() {
		
		ArrayList<Sales> list = adminService.selectSalesPerYearOnce();
		
		return new Gson().toJson(list);
	}
	
	/**
	 * 관리자 - 월간구독권 연도별 매출 메소드 - 작성자 : 장희연
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="salesPerYearSub.st", produces="application/json; charset=UTF-8")
	public String selectSalesPerYearSub() {
		
		ArrayList<Sales> list = adminService.selectSalesPerYearSub();
		
		return new Gson().toJson(list);
	}
	
	/**
	 * 관리자 - 연도별 매출 메소드 - 작성자 : 장희연
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="salesPerYear.st", produces="application/json; charset=UTF-8")
	public String selectSalesPerYear() {
		
		ArrayList<Sales> list = adminService.selectSalesPerYear();
		
		return new Gson().toJson(list);
	}
	
	@ResponseBody
	@RequestMapping("viewsOfTVPerGenre.st")
	public HashMap<String, Integer> selectViewsTV(String[] genre) {
		// System.out.println("장르: " + Arrays.toString(genre));
		
		HashMap<String, Integer> map = new HashMap();
		
		for(int i = 0; i < genre.length; i++) {
			// System.out.println(genre[i]);
			int views = 0; 
			
			ArrayList<Integer> list = adminService.selectViewsTV(genre[i]);
			// System.out.println(list);
			// System.out.println(list.size());
			for(int j = 0; j < list.size(); j++) {
				views += list.get(j);
			}
			
			map.put(genre[i], views);
		}
		
		System.out.println(map);
		return map;
	}
	
	@ResponseBody
	@RequestMapping("viewsOfMoviePerGenre.st")
	public HashMap<String, Integer> selectViewsMovie(String[] genre) {
		// System.out.println("장르: " + Arrays.toString(genre));
		
		HashMap<String, Integer> map = new HashMap();
		
		for(int i = 0; i < genre.length; i++) {
			// System.out.println(genre[i]);
			int views = 0; 
			
			ArrayList<Integer> list = adminService.selectViewsMovie(genre[i]);
			// System.out.println(list);
			// System.out.println(list.size());
			for(int j = 0; j < list.size(); j++) {
				views += list.get(j);
			}
			
			map.put(genre[i], views);
		}
		
		System.out.println(map);
		return map;
	}
	
	/**
	 * 관리자 페이지 코멘트 관리에서 사용할 페이징 바, 기본 접속 시 전체 코멘트 조회 - 작성자 : 수빈
	 * @param currentPage
	 * @param model
	 * @return
	 */
	@RequestMapping(value="commentList.ad")
	public String selectAdminCommentList(@RequestParam(value="cpage", defaultValue="1") int currentPage, Model model) {
		
		int listCount = adminService.selectAdminCommentListCount();
		
		int pageLimit = 10;
		int boardLimit = 10;
		
		PageInfo pi = Pagination.getPageInfo(listCount, currentPage, pageLimit, boardLimit);
		
		ArrayList<Review> list = adminService.selectAdminCommentList(pi);
		
		model.addAttribute("pi", pi);
		model.addAttribute("list", list);
		
		return "admin/adminCommentView";
	}
	
	/**
	 * 관리자 페이지 코멘트 관리에서 검색 시 사용할 페이징 바, 검색 시 키워드에 해당하는 코멘트를 조회 - 작성자 : 수빈
	 * @param currentPage
	 * @param condition
	 * @param keyword
	 * @param model
	 * @return
	 */
	@RequestMapping(value="searchComment.ad")
	public String searchAdminCommentList(@RequestParam(value="cpage", defaultValue="1") int currentPage, String condition, String keyword, Model model) {
		
		// System.out.println("condition: " + condition);
		// System.out.println("keyword: " + keyword);
		
		HashMap<String, String> map = new HashMap<>();
		map.put("condition", condition);
		map.put("keyword", keyword);
		
		int searchCount = adminService.searchAdminCommentListCount(map);
		
		int pageLimit = 10;
		int boardLimit = 10;
		
		PageInfo pi = Pagination.getPageInfo(searchCount, currentPage, pageLimit, boardLimit);
		
		ArrayList<Review> searchList = adminService.searchAdminCommentList(map, pi);
		
		model.addAttribute("pi", pi);
		model.addAttribute("searchList", searchList);
		
		 // 검색한 조건과 검색어 유지를 위해 다시 jsp로 보내 줌
		model.addAttribute("condition", condition);
		model.addAttribute("keyword", keyword);
		
		return "admin/adminCommentView";
		
	}
	
	/**
	 * 관리자 페이지 코멘트 관리에서 선택된 리뷰 삭제 메소드 - 작성자: 수빈
	 * @param reviewNoArr
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="deleteComment.ad", produces="application/json; charset=UTF-8")
	public int deleteAdminComment(@RequestParam(value="reviewNoArr[]") List<String> reviewNoArr) {
		
		// System.out.println(reviewNoArr); // 체크박스에 담긴 리뷰 번호가 [4, 3] 와 같이 찍힘
		
		int result = 0;
		int checkNum = 0; // 리뷰번호는 1부터 시작함
		
		for(String str : reviewNoArr) {
			checkNum = Integer.parseInt(str);
			// System.out.println(checkNum); // 4, 3이 차례로 찍힘
			adminService.deleteAdminComment(checkNum);
			result++;
		}
		
		return result;
	}
	
	/**
	 * 관리자 페이지 신고 관리에서 사용할 페이징 바, 기본 접속 시 신고된 전체 코멘트 조회 - 작성자 : 수빈
	 * @param currentPage
	 * @param model
	 * @return
	 */
	@RequestMapping("reportedComment.ad")
	public String reportedCommentList(@RequestParam(value="cpage", defaultValue="1") int currentPage, Model model) {
		
		int listCount = adminService.reportedCommentListCount();
		
		int pageLimit = 10;
		int boardLimit = 10;
		
		PageInfo pi = Pagination.getPageInfo(listCount, currentPage, pageLimit, boardLimit);
		
		ArrayList<Report> list = adminService.reportedCommentList(pi);
		
		model.addAttribute("pi", pi);
		model.addAttribute("list", list);
		
		return "admin/adminReportedComment";
		
	}
	
	/**
	 * 관리자 페이지 신고 관리에서 신고된 코멘트를 삭제(STATUS='N') 하는 메소드 - 작성자: 수빈
	 * @param reportNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="deleteReportedComment.ad", produces="application/json; charset=UTF-8")
	public int deleteReportedComment(int reportNo, int reviewNo) {
		
		// System.out.println(reportNo);
		// System.out.println(reviewNo);
		
		int result1 = adminService.changeStatusReportedComment(reportNo); // 신고된 코멘트의 상태를 신고 완료 (STATUS='Y')로 변경
		
		int result2 = adminService.deleteReportedComment(reviewNo); // 코멘트의 상태를 삭제 상태(STATUS='N')로 변경
		
		int result = result1 * result2;
		
		// System.out.println(result);
		
		return result;
		
	}
	
	/**
	 * 관리자 페이지 신고 관리에서 처리된 코멘트 보기 버튼 클릭 시 사용할 페이징 바, 기본 접속 시 처리된 전체 코멘트 조회 - 작성자 : 수빈
	 * @param currentPage
	 * @param model
	 * @return
	 */
	@RequestMapping("processedCommentList.ad")
	public String processedCommentList(@RequestParam(value="cpage", defaultValue="1") int currentPage, Model model) {
		
		int listCount = adminService.processedCommentListCount();
		
		int pageLimit = 10;
		int boardLimit = 10;
		
		PageInfo pi = Pagination.getPageInfo(listCount, currentPage, pageLimit, boardLimit);
		
		ArrayList<Report> list = adminService.processedCommentList(pi);
		
		model.addAttribute("pi", pi);
		model.addAttribute("list", list);
		
		return "admin/adminProcessedComment";
		
	}
	
	/**
	 * 관리자 페이지 신고 관리에서 신고된 코멘트를 되돌리는(STATUS='Y') 하는 메소드 - 작성자: 수빈
	 * @param reportNo
	 * @param reviewNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="resetReportedComment.ad", produces="application/json; charset=UTF-8")
	public int resetReportedComment(int reportNo, int reviewNo) {
		
		// System.out.println(reportNo);
		// System.out.println(reviewNo);
		
		int result1 = adminService.resetStatusReportedComment(reportNo);
		
		int result2 = adminService.resetReportedComment(reviewNo);
		
		int result = result1 * result2;
		
		// System.out.println(result);
		
		return result;
		
	}
	
	@RequestMapping("adminPaymentList.ad")
	public ModelAndView adminPayment(@RequestParam(value = "cpage",defaultValue = "1")int currentPage, ModelAndView mv) {
		
		int listCount = adminService.selectAdminPaymentListCount();
		
		int pageLimit = 10;
		int boardLimit = 10;
		
		PageInfo pi = Pagination.getPageInfo(listCount, currentPage, pageLimit, boardLimit);
		System.out.println(pi);
		
		mv.addObject("pi",pi);
		
		ArrayList<Payment> list = adminService.selectAdminPaymentList(pi);
		System.out.println(list);
		mv.addObject("list", list);
		mv.setViewName("admin/adminPayment");
		
		return mv;
	}
	
	/**
	 * 관리자 페이지 신고 관리: 페이징바와 신고된 댓글 조회 페이지 - 작성자: 수빈
	 * @param currentPage
	 * @param model
	 * @return
	 */
	@RequestMapping("reportedReply.ad")
	public String reportedReplyList(@RequestParam(value="cpage", defaultValue="1") int currentPage, Model model) {
		
		int listCount = adminService.reportedReplyListCount();
		
		int pageLimit = 10;
		int boardLimit = 10;
		
		PageInfo pi = Pagination.getPageInfo(listCount, currentPage, pageLimit, boardLimit);
		
		ArrayList<Report> replyList = adminService.reportedReplyList(pi);
		
		model.addAttribute("pi", pi);
		model.addAttribute("replyList", replyList);
		
		return "admin/adminReportedReply";
	}
	
	/**
	 * 관리자 페이지 신고 관리에서 신고된 댓글을 삭제하는(STATUS='N') 하는 메소드 - 작성자: 수빈
	 * @param reportNo
	 * @param reviewNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value= "deleteReportedReply.ad", produces="application/json; charset=UTF-8")
	public int deleteReportedReply(int reportNo, String comRplNo) {
		
		// System.out.println(reportNo);
		// System.out.println(comRplNo);
		
		int result1 = adminService.changeStatusReportedReply(reportNo); // 신고된 댓글의 상태를 신고 완료 (STATUS='Y')로 변경
		
		int result2 = adminService.deleteReportedReply(Integer.parseInt(comRplNo)); // 댓글의 상태를 삭제 상태(STATUS='N')로 변경
		
		int result = result1 * result2;
		
		// System.out.println(result);
		
		return result;
		
	}
	
	/**
	 * 관리자 페이지 신고 관리에서 처리된 댓글 보기 버튼 클릭 시 사용할 페이징 바, 기본 접속 시 처리된 전체 댓글 조회 - 작성자 : 수빈
	 * @param currentPage
	 * @param model
	 * @return
	 */
	@RequestMapping("processedReplyList.ad")
	public String processedReplyList(@RequestParam(value="cpage", defaultValue="1") int currentPage, Model model) {
		
		int listCount = adminService.processedReplyListCount();
		
		int pageLimit = 10;
		int boardLimit = 10;
		
		PageInfo pi = Pagination.getPageInfo(listCount, currentPage, pageLimit, boardLimit);
		
		ArrayList<Report> replyList = adminService.processedReplyList(pi);
		
		model.addAttribute("pi", pi);
		model.addAttribute("replyList", replyList);
		
		return "admin/adminProcessedReply";
		
	}
	
	/**
	 * 관리자 페이지 신고 관리에서 신고된 댓글을 되돌리는(STATUS='Y') 하는 메소드 - 작성자: 수빈
	 * @param reportReplyNo
	 * @param comRplNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="resetReportedReply.ad", produces="application/json; charset=UTF-8")
	public int resetReportedReply(int reportNo, int comRplNo) {
		
		// System.out.println(reportNo);
		// System.out.println(comRplNo);
		
		int result1 = adminService.resetStatusReportedReply(reportNo);
		
		int result2 = adminService.resetReportedReply(comRplNo);
		
		int result = result1 * result2;
		
		// System.out.println(result);
		
		return result;
		
	}
	
	/**
	 * 관리자 페이지 신고 관리 - 신고된 전체 커뮤니티 게시글 조회 - 작성자 : 황혜진
	 * @param currentPage
	 * @param model
	 * @return
	 */
	@RequestMapping("reportedCommunity.ad")
	public String reportedCommunityList(@RequestParam(value="cpage", defaultValue="1") int currentPage, Model model) {
		
		int listCount = adminService.reportedCommunityListCount();
		
		int pageLimit = 10;
		int boardLimit = 10;
		
		PageInfo pi = Pagination.getPageInfo(listCount, currentPage, pageLimit, boardLimit);
		
		ArrayList<Report> list = adminService.reportedCommunityList(pi);
		
		model.addAttribute("pi", pi);
		model.addAttribute("list", list);
		
		// System.out.println(list);
		
		return "admin/adminReportedCommunity";
	}
	
	/**
	 * 관리자 페이지 신고 관리 - 신고된 커뮤니티 게시글 삭제(STATUS='N')  - 작성자: 황혜진 
	 * @param reportNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="deleteReportedCommunity.ad", produces="application/json; charset=UTF-8")
	public int deleteReportedCommunity(int reportNo, int comNo) {
		
		// System.out.println(reportNo);
		// System.out.println(comNo);
		
		int result1 = adminService.changeStatusReportedCommunity(reportNo);
		
		int result2 = adminService.deleteReportedCommunity(comNo);
		
		int result = result1 * result2;
		
		// System.out.println(result);
		
		return result;
	}
	
	/**
	 * 관리자 페이지 신고 관리 - '처리된 글 보기' 버튼 클릭 시 게시글 조회 - 작성자 : 황혜진 
	 * @param currentPage
	 * @param model
	 * @return
	 */
	@RequestMapping("processedCommunityList.ad")
	public String processedCommunityList(@RequestParam(value="cpage", defaultValue="1") int currentPage, Model model) {
		
		int listCount = adminService.processedCommunityListCount();
		
		int pageLimit = 10;
		int boardLimit = 10;
		
		PageInfo pi = Pagination.getPageInfo(listCount, currentPage, pageLimit, boardLimit);
		
		ArrayList<Report> list = adminService.processedCommunityList(pi);
		
		model.addAttribute("pi", pi);
		model.addAttribute("list", list);
		
		return "admin/adminProcessedCommunity";
	}
	
	/**
	 * 관리자 페이지 신고 관리 - 신고된 코멘트 되돌리기 (STATUS='Y') - 작성자: 황혜진 
	 * @param reportNo
	 * @param comNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="resetReportedCommunity.ad", produces="application/json; charset=UTF-8")
	public int resetReportedCommunity(int reportNo, int comNo) {
		
		// System.out.println(reportNo);
		// System.out.println(comNo);
		
		int result1 = adminService.resetStatusReportedCommunity(reportNo);
		
		int result2 = adminService.resetReportedCommunity(comNo);
		
		int result = result1 * result2;
		
		// System.out.println(result);
		
		return result;
	}
}