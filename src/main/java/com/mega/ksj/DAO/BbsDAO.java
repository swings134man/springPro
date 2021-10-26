package com.mega.ksj.DAO;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mega.ksj.DTO.BbsDTO;

@Repository
public class BbsDAO {

	@Autowired
	SqlSessionTemplate mybatis;
	
	
	public int createD(BbsDTO bbsDTO) {
		try {
		int result = mybatis.insert("bbs.create",bbsDTO);
		return result;
		
		} catch (Exception e) {
			e.printStackTrace();
			int result2 = 0;
			return result2;
		}//catch
	}//create end
	
	
	
	public List<BbsDTO> bbsListD(BbsDTO bbsDTO) {
		List<BbsDTO> list = mybatis.selectList("bbs.all", bbsDTO);
		return list;
	}// mainlist end
	
	
}//class
