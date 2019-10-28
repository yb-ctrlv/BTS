package com.kh.bts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.bts.dto.ChatInfoDto;
import com.kh.bts.dto.MemberDto;
import com.kh.bts.dto.MessageDto;
import com.kh.bts.dto.MongoDto;
import com.kh.bts.dto.RoomDto;
import com.kh.bts.model.biz.ChatInfoBiz;
import com.kh.bts.model.biz.FriendBiz;
import com.kh.bts.model.biz.MailService;
import com.kh.bts.model.biz.MemberBiz;
import com.kh.bts.model.biz.MessageBiz;
import com.kh.bts.model.biz.MongoBiz;
import com.kh.bts.model.biz.RoomBiz;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberBiz biz;
	@Autowired
	private MailService MailService;
	@Autowired
	private ChatInfoBiz chatinfobiz;
	@Autowired
	private MessageBiz messagebiz;
	@Autowired
	private FriendBiz friendbiz;
	@Autowired
	private MongoBiz mongobiz;
	@Autowired
	private RoomBiz roombiz;

	@RequestMapping(value = "/user/loginform")
	public String loginform() {
		return "loginform";
	}

	@RequestMapping(value = "/user/error")
	public String error() {
		return "error";
	}

	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/user/registerform")
	public String registerForm() {
		return "registerform";
	}

	@RequestMapping(value = "/user/idchk", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public int idchk(String id) {
		return biz.idchk(id);
	}

	@RequestMapping(value="/user/emailck",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public int emailck(String email) {
		return biz.emailck(email);
	}
	
	@RequestMapping(value="/user/emailchk",method = {RequestMethod.POST})
	@ResponseBody
	public int emailchk(String email) {
		int emailCode = new Random().nextInt(100000) + 10000;
		String joinCode = String.valueOf(emailCode);
		String subject = "회원가입 인증 코드 발급 안내 입니다.";
		MailService.send(subject, "인증코드 : " + joinCode, "khbtskh@gmail.com", email, null);
		

		return emailCode;
	}
	
	@RequestMapping(value="/user/findidforemail",method = {RequestMethod.POST})
	@ResponseBody
	public boolean findidforemail(String email) {
		String id = biz.findid(email);
		String subject = "가입한 ID를 알려드립니다..";
		MailService.send(subject, "가입한 ID : " + id, "khbtskh@gmail.com", email, null);

		return true;
	}
	
	@RequestMapping(value="/user/findpwforemail",method = {RequestMethod.POST})
	@ResponseBody
	public int findpwforemail(String email) {
		int pw = new Random().nextInt(100000) + 10000;
		String password = String.valueOf(pw);
		String subject = "임시비밀번호를 알려드립니다 로그인후 반드시 비밀번호를 변경해주세요";
		MailService.send(subject, "임시 비밀번호 : " + password, "khbtskh@gmail.com", email, null);
		int res = biz.pwtemp(email, password);

		return res;
	}
	
	@RequestMapping(value="/user/emailchkpage")
	public String emailChkPage() {
		return "emailchk";
	}

	@RequestMapping(value = "/user/main")
	public String main(Principal principal, Model model, String roominfomsg) {
		
		List<RoomDto> list = roombiz.roomList();
		model.addAttribute("roomlist", list);
		if (principal == null) {
			// 로그인안되어있는경우
		} else {
			MemberDto dto = (MemberDto) ((Authentication) principal).getPrincipal();
			List<ChatInfoDto> chatinfoList = chatinfobiz.selectListToMember(dto.getMember_no());
			List<List<MessageDto>> messageList = new ArrayList<List<MessageDto>>();
			if (chatinfoList != null) {
				for (ChatInfoDto item : chatinfoList) {
					messageList.add(messagebiz.seletList(item.getChatroom_no()));
				}
			}
			List<MemberDto> friendList = friendbiz.selectList(dto.getMember_no());

			model.addAttribute("chatinfoList", chatinfoList);
			model.addAttribute("messageList", messageList);
			model.addAttribute("friendList", friendList);
			
			if(roominfomsg != null) {
				model.addAttribute("roominfomsg", roominfomsg);
			}
			
		}
		
		return "mainpage";
	}

	@RequestMapping(value = "/user/register", method = { RequestMethod.POST, RequestMethod.GET })
	public String register(MemberDto dto) {
		int res = biz.insert(dto);
		if (res > 0) {
			return "redirect:/bts/user/main";
		} else {
			return "registerform";
		}
	}

	@RequestMapping(value = "/user/findid")
	public String findid() {
		return "findid";
	}
	
	@RequestMapping(value="/user/findpw")
	public String findpw() {
		return "findpw";
	}

	@RequestMapping(value = "/user/logoutpage")
	public String logoutpage(Model model, String id, String pw) {
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		return "logoutpage";
	}

	@RequestMapping(value = "/user/naverlogin")
	public String logoutpage() {
		return "naverlogin";
	}

	@RequestMapping(value = "/user/snslogin", method = { RequestMethod.POST })
	@ResponseBody
	public boolean snslogin(MemberDto dto) {

		if (biz.idchk(dto.getMember_id()) == 0) {
			biz.insert(dto);
			return true;
		} else {
			return true;
		}
	}

	@RequestMapping(value = "/member/infopwcheck")
	public String infopwcheck() {
		return "infopwcheck";
	}

	@RequestMapping(value = "/member/pwchk", method = { RequestMethod.POST })
	@ResponseBody
	public boolean pwcheck(String member_id, String member_pw) {
		return biz.pwchk(member_id, member_pw);
	}

	@RequestMapping(value = "/member/infoupdateform")
	public String infoupdateform() {

		return "infoupdateform";
	}

	@RequestMapping(value = "/member/snsinfoupdateform")
	public String snsSinfoupdateform() {

		return "snsinfoupdateform";
	}

	@RequestMapping(value = "/member/infoupdateres")
	public String infoupdate(Model model, MemberDto dto) {

		String id = dto.getMember_id();
		String pw = dto.getMember_pw();
		int res = biz.update(dto);

		if (res > 0) {
			model.addAttribute("id", id);
			model.addAttribute("pw", pw);
			return "sessionlogout";
		} else {
			return "infoupdateform";
		}

	}
	@RequestMapping(value="/user/mongo")
	public String MongoList(Model model, String bookname){	
		String s = null;	
		try {
			String[] cmd = new String[3];
			cmd[0] = "python";
			cmd[1] = "C:\\Users\\ILL GANG\\Desktop\\bts_final\\BTS_PRJ\\src\\main\\java\\com\\kh\\bts\\aladin.py";
			cmd[2] = bookname;
			
			Runtime rt = Runtime.getRuntime();
			Process process = rt.exec(cmd);
			
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			
			while((s=stdInput.readLine()) != null) {
				System.out.println("1");
				System.out.println(s);
			}
			
			while((s=stdError.readLine()) != null) {
				System.out.println("2");
				System.out.println(s);
			}
			System.out.println("완료");
		} catch (IOException e) {		
			e.printStackTrace();
		}
		System.out.println("완료2");
		
		List<MongoDto> list = mongobiz.mongolist();
		for(int i=0; i<list.size(); i++) {
			System.out.println("ID: " + list.get(i).getId() +" / TITLE : " + list.get(i).getTitle() + " / VALUE : " +list.get(i).getValue());
		}
		model.addAttribute("list",list);
		
		return "mongo";
	}
}
