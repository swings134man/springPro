package com.fly.pro2.DAO;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fly.pro2.DTO.Criteria;
import com.fly.pro2.DTO.QuestionDTO;

@Repository
public class QusetionDAO {

	@Autowired
	SqlSessionTemplate mybatis;

	// <차트 카테고리 별 카운트>
	public List<Object> chart() {
		// 검색 결과 담을 List 객체 생성
		List<Object> chartList = new ArrayList<Object>();
		// 예약문의 검색 결과
		int reservation = mybatis.selectOne("question.chartReservation");
		// 결제문의 검색 결과
		int payment = mybatis.selectOne("question.chartPayment");
		// 항공 문의 결과
		int airLine = mybatis.selectOne("question.chartAirLine");
		// 피드백문의 결과
		int feedback = mybatis.selectOne("question.chartFeedback");
		// 생성한 리스트에 검색 결과를 넣어준다
		chartList.add(reservation);
		chartList.add(feedback);
		chartList.add(airLine);
		chartList.add(payment);
		return chartList; // 담은 리스트를 리턴
	}

	// 문의글 작성 페이지 insert
	public void create(QuestionDTO questionDTO) {
		// 컨트롤러에서 받은 DTO를 mybatis에 전송
		mybatis.insert("question.questionWrite", questionDTO);
	}

	public void answerQuestion(QuestionDTO questionDTO) {
		// 답변 insert -> 받은 DTO를 mapper로 전송
		mybatis.insert("question.answer", questionDTO);
	}

	public List<QuestionDTO> readQuestion() {
		// db전체 검색, 리스트로 리턴한다
		List<QuestionDTO> questionList = mybatis.selectList("question.questionBoard");
		return questionList;
	} // 문의글 리스트 읽어오기

	public QuestionDTO readQuestionInfo(QuestionDTO questionDTO) {
		QuestionDTO questionList = mybatis.selectOne("question.questionInfo", questionDTO);
		// 상세페이지 클릭시에 조회수 + 1 해주기
		return questionList;
	} // 문의 상세 페이지

	public List<QuestionDTO> listPage(Criteria cri) {
		// Criteria 클래스에 있는 시작값과 끝값을 mapper에 전달
		// 파라메터를 cri로 전달한다
		List<QuestionDTO> questionList = mybatis.selectList("questionBoard", cri);
		// 검색된 결과를 리턴
		return questionList;
	}
	public int listCount() {
		// 게시글 총 갯수 카운트를 위한 메서드
		int count = mybatis.selectOne("question.listCount");
		return count;
	}

	public int deleteQuestion(QuestionDTO questionDTO) {
		// 전달받은 qno로 mybatis에서 DB처리
		int result = mybatis.delete("question.questionDelete", questionDTO);
		// 처리 결과를 int로 리턴
		return result;
	}

	public QuestionDTO updateQuestionRead(QuestionDTO questionDTO) {
		// 전송받은 qno를 DTO에 담아 mybatis에 전송 DB처리
		QuestionDTO questionList = mybatis.selectOne("question.questionUpdateRead", questionDTO);
		return questionList;
	} // 수정 입력폼에 띄워줄 read

	// 조회수를 올리자
	public void updateView(QuestionDTO questionDTO) {
		// QNO에 해당하는 글을 읽어올때 조회수 컬럼을 +1로 업데이트 해준다
		mybatis.update("question.viewCount", questionDTO);
	}

	public void updateQuestion(QuestionDTO questionDTO) {
		// 전달받은 DTO를 mapper에 전달
		mybatis.update("question.questionUpdate", questionDTO);
	}

} // DAO end
