package com.kh.bts;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.bts.dto.MemberDto;
import com.kh.bts.dto.RoomDto;
import com.kh.bts.model.biz.MemberBiz;
import com.kh.bts.model.biz.RoomBiz;

@Controller
public class RoomController {
	
	private static final Logger logger = LoggerFactory.getLogger(RoomController.class);
	
	@Autowired
	private RoomBiz roomBiz;
	@Autowired
	private MemberBiz memberBiz;
	
	
	@RequestMapping(value="/member/roomlist")
	public String roomlist(Model model) {
		
		List<RoomDto> list = roomBiz.roomList();
		model.addAttribute("list", list);
		
		return "roomlist";
	}
	
	@RequestMapping(value="/member/makeroomform")
	public String makeroomform() {
		return "makeroomform";
	}
	
	@RequestMapping(value="/member/makeroom")
	public String makeroom(RoomDto dto, Model model) {
		dto.setRoom_addr(makeAddr());
		roomBiz.makeRoom(dto);
		model.addAttribute("room_maxval", dto.getRoom_maxval());
		model.addAttribute("room_addr", dto.getRoom_addr());
		return "joinroom";
		//return "redirect:https://192.168.110.224:3000/meetingRoom?room_addr=" + dto.getRoom_addr() + "&nickname=" + mebmerDto.getMember_nickname();
	}
	
	@RequestMapping(value="/member/joinroom")
	public String joinroom(int room_no, Model model) {
		RoomDto dto = roomBiz.checkRoom(room_no);
		
		if(dto.getRoom_nowval() >= dto.getRoom_maxval()) {
			model.addAttribute("roominfomsg", "방 인원이 가득 가득 찼습니다"); 
			return "redirect:/user/main";
		}else {
			roomBiz.updateRoom(room_no);
			model.addAttribute("room_maxval", dto.getRoom_maxval());
			model.addAttribute("room_addr", dto.getRoom_addr());
			return "joinroom";
			//return "redirect:https://192.168.110.224:3000/meetingRoom?room_addr=" + dto.getRoom_addr() + "&nickname=" + mebmerDto.getMember_nickname();
		} 
		
	}
	
	@RequestMapping(value="/member/outroom", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public void roomOut(String room_addr) {
		
		roomBiz.outRoom(room_addr);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public String makeAddr() {
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		
		for (int i = 0; i < 20; i++) {
		    int rIndex = rnd.nextInt(3);
		    switch (rIndex) {
		    case 0:
		        // a-z
		        temp.append((char) ((int) (rnd.nextInt(26)) + 97));
		        break;
		    case 1:
		        // A-Z
		        temp.append((char) ((int) (rnd.nextInt(26)) + 65));
		        break;
		    case 2:
		        // 0-9
		        temp.append((rnd.nextInt(10)));
		        break;
		    }
		}
		
		String res = temp.toString();
		
		return res;
	}

		
}
