package com.mega.ksj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mega.ksj.DTO.BbsDTO;

@Controller
public class BbsController {

	//서비스
	@Autowired
	BbsService service;
	
	
	@RequestMapping("create.bbs")
	public String create(BbsDTO bbsDTO) {
		int result = service.createS(bbsDTO);
		if (result == 1) {
			return "redirect:bbs.jsp";
		}else {
			return "error";
		}//else
	}//create
	
	@RequestMapping("bbs.main")
	public void bbsList(BbsDTO bbsDTO, Model model) {
		List<BbsDTO> list = service.bbsListS(bbsDTO);
		model.addAttribute("list", list);
		System.out.println("list 넘어감!!!!!@@@@");
	}
	
	
	
}//class
