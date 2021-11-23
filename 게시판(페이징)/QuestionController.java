package com.fly.pro2;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fly.pro2.DAO.QusetionDAO;
import com.fly.pro2.DTO.Criteria;
import com.fly.pro2.DTO.PageMaker;
import com.fly.pro2.DTO.QuestionDTO;

@Controller
public class QuestionController {

	@Autowired
	QusetionDAO dao;

	@RequestMapping("questionWrite")
	public String create(QuestionDTO questionDTO) {
		//입력값들을 DTO로 dao에 전송
		dao.create(questionDTO);
		// 문의글을 작성하고 질문글 목록으로 이동
		return "redirect:questionList";
	}
	
	
	@RequestMapping("chartReading")
	@ResponseBody
	public String[] chartRead(){
	// 검색결과를 받기 위한 List객체 생성
	List<Object> chartList = dao.chart();
	//Object 스트링으로 변환
	String reservation = String.valueOf(chartList.get(0));
	String feedback = String.valueOf(chartList.get(1));
	String airLine = String.valueOf(chartList.get(2));
	String payment = String.valueOf(chartList.get(3));
	//처리한 값들을 스트링 배열에 넣고 리턴
	String[] arr = {reservation, feedback, airLine, payment};
	  return arr;
	}
	
	
//	@RequestMapping("questionList")
//	public String read(Model model, QuestionDTO questionDTO) {
//		List<QuestionDTO> questionList = dao.readQuestion();
//		model.addAttribute("questionList", questionList);
//		return "questionList";
//	}
//
//	@RequestMapping("questionInfo")
//	public void read(QuestionDTO questionDTO, Model model) {
//		System.out.println(questionDTO.getQno());
//		QuestionDTO questionList = dao.readQuestionInfo(questionDTO);
//		model.addAttribute("questionList", questionList);
//		System.out.println(questionDTO);
//	}
	
	
	@RequestMapping(value = { "/questionList", "/questionInfo" })
	public void read(HttpServletRequest request, QuestionDTO questionDTO, 
					Model model, Criteria cri) {
			// 문의 리스트 전체 가져오기
			// 값이 questionList로 매핑 될 경우 실행
		if (request.getServletPath().equals("/questionList")) {
			// 모두 읽어와서 view에 출력 해야하므로 List에 처리 결과를 담는다 
//			List<QuestionDTO> questionList = dao.readQuestion();
//			// 처리한 결과를 모델에 등록한다
//			model.addAttribute("questionList", questionList);
			List<QuestionDTO> questionList = dao.listPage(cri);
			model.addAttribute("questionList", questionList);
			
			//PageMaker 객체 생성
			PageMaker pageMaker = new PageMaker();
			// Criteria를 파라메터로 받는 setCri 메서드 호출
			// rowStart rowEnd가 Criteria 클래스에 정의 되어 있음
			pageMaker.setCri(cri);
			// 계산을 위한 전체 게시글 갯수 카운트
			pageMaker.setTotalCount(dao.listCount());
			// 처리 결과를 model 객체에 등록 view에 출력
			model.addAttribute("pageMaker",pageMaker);
			
		} else if (request.getServletPath().equals("/questionInfo")) {
			// 문의 상세 글 가져오기
			// 값이 questionInfo로 매핑 될 경우 실행
			// 레코드 하나 검색이므로 리스트가 아닌 DTO에 처리 결과를 넣는다
			QuestionDTO questionList = dao.readQuestionInfo(questionDTO);
			
			// 상세페이지 클릭하면 조회수를 올려야 하기 때문에
			// 상세글 읽어오는것과 같이 처리 해준다
			dao.updateView(questionDTO);
			// 처리 결과를 모델에 등록해줌
			model.addAttribute("questionList", questionList);
		}
	}

	@RequestMapping("questionDelete")
	//ajax를 사용하기 위한 responseBody
	@ResponseBody
	public int deleteQusetion(QuestionDTO questionDTO) {
		// 전송받은 qno값을 dao에 전송
		int result = dao.deleteQuestion(questionDTO);
		// 성공하면 1을 리턴한다
		return result;
	} // 게시글 삭제

	// 수정 입력폼에 뿌려줄 value값 read
	@RequestMapping("questionUpdateRead.question")
	public void updateQuestion(QuestionDTO questionDTO, Model model) {
		//jsp 에서 전송받은 qno값을 dao로 전송
		QuestionDTO questionList = 
				dao.updateQuestionRead(questionDTO);
		// qno에 해당하는 글을 read
		model.addAttribute("questionList", questionList);
	}

	@RequestMapping("update.question")
	public String questionUpdate(QuestionDTO questionDTO) {
		//jsp에서 전송한 qno를 전달
		dao.updateQuestion(questionDTO);
		// update처리 후에 다시 문의리스트로 연결
		return "redirect:questionList";
	} // update입력 폼에서 버튼 클릭시 업데이트 처리

	@RequestMapping("AnswerForm") // AnswerForm에 값을 전달하고 이동
	public ModelAndView moveAnswer(ModelAndView mav, HttpServletRequest request) {
		String fk_seq = request.getParameter("fk_seq");
		// httpServeltRequest를 이용하여 fk_seq값을 받는다
		mav.addObject("fk_seq", fk_seq);
		//addObject 객체에 받은 값을 추가한다
		mav.setViewName("AnswerForm");
		// view Resolver에 전달할 이름을 설정한다
		return mav;
		//위에 설정값들을 리턴한다
	} 

	@RequestMapping("questionAnswer") // 답변 글 insert
	public String Answer(QuestionDTO questionDTO, HttpServletRequest request) {
		// request로 넘어온 값을 받고
		String fk_seq2 = request.getParameter("fk_seq");
		// 인트로 변환한 후에 DTO에 넣어줌
		int fk_seq = Integer.parseInt(fk_seq2);
		questionDTO.setFk_seq(fk_seq);
		//DAO호출, 값들이 들어있는 DTO전송
		dao.answerQuestion(questionDTO);
		return "redirect:questionList";
	}
}
