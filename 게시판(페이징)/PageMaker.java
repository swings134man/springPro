package com.fly.pro2.DTO;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {

	private int totalCount; // 게시물의 총 갯수
	private int startPage; // 현재 페이지의 시작 번호(1 , 11 , 21)
	private int endPage; // 현재 페이지의 끝 번호 (10 , 20 , 30)
	private boolean prev; // 이전 페이지로 이동 링크 
	private boolean next; // 다음 페이지로 이동하는 링크 존재 여부
	
	// 한번에 보여줄 페이지 번호는 10 페이지씩
	private int displayPageNum = 10;
	
	//Criteria 클래스에 정의해둔 page , perPageNum
	private Criteria cri;

	
	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		// 아래에서 정의한 계산식이 있는 메서드이다
		// setTotalCount는 게시물의 총 문의글의 개수이기 때문에
		// 게시물 총 갯수로 시작, 끝, 다음, 이전 페이지를 계산해준다
	   calcData();
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public boolean isNext() {
		return next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public Criteria getCri() {
		return cri;
	}

	private void calcData() { //첫페이지, 마지막 페이지 , 이전 목록 , 다음 목록을 계산하는 함수를 만들어준다
		// 끝페이지 계산
		// Math.ceil은 입력된 값을 올림처리 하는 함수이다
		//  10  	==						3		10.0 = 0.3 올림처리 1.0 	*	10
		endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);
		// 시작 페이지 계산
		// 	1	  =   10     - 10 (화면에 출력할 페이징 갯수) 0 + 1 
		startPage = (endPage - displayPageNum) + 1;
		//끝 페이지가 맞는지 확인
		//              =         게시물 갯수 : 	72    /   10  getPerPageNum == 
		//        8     =  Math.ceil(7.2)               	  Criteria 클래스에 정의한 한 페이지에 보여줄 문의글 갯수 
		int tempEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));
		System.out.println(totalCount);
		System.out.println(Math.ceil(totalCount / (double) cri.getPerPageNum()));
		//   10     >   8  true이면
		if (endPage > tempEndPage) {
		//  end 페이지가 더 크다면 tempEndPage(끝페이지 계산)값을 endPage에 대입
			endPage = tempEndPage;
			//endPage = 8
		}
		// 이전 페이지가 있는지
		// 1 == 1   이면 false가 반환된다
		// 이전 이라는 링크를 생성하지 않음
		//          20   == 1   이면 true가 반환된다
		prev = startPage == 1 ? false : true;
		// 다음 페이지를 넣어야 할지 계산
		// 	    8     *      10       80   >=   72(총 문의글 수) false
		// 마지막 페이지와 한페이지에 read해올 문의글 갯수를 곱한 수가 총 문의글과 비교했을 때
		// 크거나 같으면 false로   다음 목록으로 넘어가는 페이지(링크)를 만들지 않음
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
	} 
	
	
	// 리스트 페이지에서 단일 게시글 클릭하면 해당 page정보를 가지고
	// page, perPageNum 파라메터를 포함한 URI 문자열을 만들어서 조회페이지로 이동한다
	// 조회 페이지에서 page, perPageNum, QNO값을 유지하고 있기 때문에
	//다시 리스트 페이지로 이동할때 원래 페이지로 이동한다
	public String makeQuery(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", 
				cri.getPerPageNum())
				.build();
		return uriComponents.toUriString();
	}

}