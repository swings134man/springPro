package com.mega.ksj;

import java.util.List;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mega.ksj.DAO.BbsDAO;
import com.mega.ksj.DTO.BbsDTO;

@Service
public class BbsService {

	// DAO
	@Autowired
	BbsDAO dao;
	
	
	public int createS(BbsDTO bbsDTO) {
		int result = dao.createD(bbsDTO);
		if (result == 1) {
			return result;
		}else {
			int result2 = 0;
			return result2;
		}//else
	}//create
	
	
	public List<BbsDTO> bbsListS(BbsDTO bbsDTO) {
		List<BbsDTO> list = dao.bbsListD(bbsDTO);
		
		// 스택 class
		StackBbs s = new StackBbs();
		Stack<BbsDTO> dto = s.StackBbs(list);
		
		System.out.println("출력!!" + dto);
		
		return dto;
	} // Service main list up end
	
	
	
	
	
	
}//class
