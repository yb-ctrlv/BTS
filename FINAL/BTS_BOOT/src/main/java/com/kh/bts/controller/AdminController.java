package com.kh.bts.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.bts.dto.MemberCountDto;
import com.kh.bts.dto.MemberDto;
import com.kh.bts.model.biz.MemberBiz;
import com.kh.bts.model.biz.MemberCountBiz;

@Controller
public class AdminController {

	@Autowired
	private MemberBiz biz;
	@Autowired
	private MemberCountBiz countBiz;
	
	@RequestMapping("/")
	public String main() {
		return "index";
	}
	
	@RequestMapping("/loginform")
	public String loginform() {
		System.out.println("들어옴");
		return "loginform";
	}
	
	@RequestMapping("/memberlist")
	public String memberlist(Model model, String page, String id) {
		
		if(id == null) {
			int pageNo = Integer.parseInt(page);
			int allCount = countBiz.totalReg();
			int listCount = 5;
			int totalPage = (allCount - 1) / listCount + 1;
			int blockCount = 5;
			int absolutePage = 0;
			int endPage = 0;
			
			if(pageNo < 1) {
				pageNo = 1;
			}else if(pageNo > totalPage) {
				pageNo = totalPage;
			}
			
			
			if(pageNo % 5 == 0) {
				absolutePage = ((pageNo / 5) * 5) - 4;
				endPage = (pageNo / 5) * 5;
			}else {
				absolutePage = ((pageNo / 5) * 5) + 1;
				endPage = ((pageNo / 5) * 5) + 5;
			}
			
			
			if(endPage > totalPage) {
				endPage = totalPage;
			}
			
			int start = (pageNo - 1) * listCount + 1;
			int end = pageNo * listCount;
			
			List<MemberDto> list = biz.memberListPaging(start, end);
			
			model.addAttribute("list", list);
			model.addAttribute("pageNo", pageNo);
			model.addAttribute("blockCount", blockCount);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("absolutePage", absolutePage);
			model.addAttribute("endPage", endPage);
			return "memberlist";
		
		}else {
			
			int pageNo = Integer.parseInt(page);
			int allCount = biz.findIdCount(id);
			int listCount = 5;
			int totalPage = (allCount - 1) / listCount + 1;
			int blockCount = 5;
			int absolutePage = 0;
			int endPage = 0;
			
			if(pageNo < 1) {
				pageNo = 1;
			}else if(pageNo > totalPage) {
				pageNo = totalPage;
			}
			
			
			if(pageNo % 5 == 0) {
				absolutePage = ((pageNo / 5) * 5) - 4;
				endPage = (pageNo / 5) * 5;
			}else {
				absolutePage = ((pageNo / 5) * 5) + 1;
				endPage = ((pageNo / 5) * 5) + 5;
			}
			
			
			if(endPage > totalPage) {
				endPage = totalPage;
			}
			
			int start = (pageNo - 1) * listCount + 1;
			int end = pageNo * listCount;
			
			List<MemberDto> list = biz.findId(start, end, id);
			
			model.addAttribute("id", id);
			model.addAttribute("list", list);
			model.addAttribute("pageNo", pageNo);
			model.addAttribute("blockCount", blockCount);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("absolutePage", absolutePage);
			model.addAttribute("endPage", endPage);
			return "memberlist";
			
		}
		
	}
	
	@RequestMapping(value = "/membercount", method = {RequestMethod.POST})
	@ResponseBody
	public MemberCountDto membercount() {
		MemberCountDto dto = new MemberCountDto();
		dto.setPreviousReg(countBiz.previousReg());
		dto.setTodayReg(countBiz.todayReg());
		dto.setWeeklyReg(countBiz.weeklyReg());
		dto.setMonthlyReg(countBiz.monthlyReg());
		dto.setTotalReg(countBiz.totalReg());
		
		return dto;
	}
	
	@RequestMapping(value = "/multienable", method = {RequestMethod.POST, RequestMethod.GET})
	public String multienable(String[] member_no, String page, String id) {
		
		biz.multiEnable(member_no);
		
		if(id == null) {
			return "redirect:/memberlist?page=" + page;
		}else {
			return "redirect:/memberlist?page=" + page + "&id=" + id;
		}
		
		
		
	}
	
	@RequestMapping(value = "/multidisable", method = {RequestMethod.POST, RequestMethod.GET})
	public String multidisable(String[] member_no, String page, String id) {
		
		biz.multiDisable(member_no);
		
		if(id == null) {
			return "redirect:/memberlist?page=" + page;
		}else {
			return "redirect:/memberlist?page=" + page + "&id=" + id;
		}
	}
	
}
