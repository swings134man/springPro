package com.mega.ksj;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mega.ksj.DTO.MemberDTO;

@Controller
public class MemberController {

	
	@Autowired
	MemberService service;
	
	
	@RequestMapping("join.member")
	public String join(MemberDTO memberDTO) {
		int result = service.joinS(memberDTO);
		//리턴 받는부분
		if (result == 1) {
			return "redirect:index.jsp";
		}else {
			return "error";
		}
	}//회원가입. 
	
	
	
	@RequestMapping("login.member")
	public String login(MemberDTO memberDTO, HttpSession session) {
		int result = service.loginS(memberDTO);
		if (result == 1) {
			session.setAttribute("sId", memberDTO.getId()); //아이디 세션
			return "redirect:index.jsp";
		}else {
			return "error";
		}
	}//로그인 
	
	
	@RequestMapping("logout.member")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:index.jsp";
	}//logout
	
	
}//class
