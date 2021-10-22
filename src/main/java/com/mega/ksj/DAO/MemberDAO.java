package com.mega.ksj.DAO;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mega.ksj.DTO.MemberDTO;

@Repository
public class MemberDAO {

	@Autowired
	SqlSessionTemplate myBatis;
	
	
	public int joinD(MemberDTO memberDTO) {
		try {
			int result = myBatis.insert("member.create",memberDTO);
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			int result2 = 0;
			return result2;
		}
	}//join
	
	
	public int loginD(MemberDTO memberDTO) {
		int result = myBatis.selectOne("member.login", memberDTO);
		
		return result;
	}//login
	
	
}//class
