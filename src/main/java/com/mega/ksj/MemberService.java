package com.mega.ksj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mega.ksj.DAO.MemberDAO;
import com.mega.ksj.DTO.MemberDTO;

@Service
public class MemberService {

	@Autowired
	MemberDAO dao;
	
	
	public int joinS(MemberDTO memberDTO) {
		int result = dao.joinD(memberDTO);
		return result;
	}//join
	
	
	public int loginS(MemberDTO memberDTO) {
		int result = dao.loginD(memberDTO);
		return result;
	}//login
	
	
}//class
