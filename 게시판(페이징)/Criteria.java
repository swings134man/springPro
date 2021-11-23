package com.fly.pro2.DTO;

public class Criteria {
	// Criteria 클래스는 rownumber에 제한값, 
	//제한값, 페이지에 출력되는 게시물 숫자를 제한한다

	// 페이징 처리할때 페이지 넘버를 지정하는 변수
	private int page;
	// 한 페이지에 몇개정도씩 read 해올지 표시 해주는 변수
	private int perPageNum;
	// select 쿼리에서 사용될 시작값으로 사용될 변수
	private int rowStart;
	// select 쿼리에서 사용될 끝값으로 사용될 변수
	private int rowEnd;

	public Criteria() {
		// 기본 생성자로 변수의 디폴트값을 넣어줌
		// 처음 게시판을 들어갔을때 1페이지가 보여야 하기 때문에 1
		this.page = 1;
		// 한 페이지당 10개씩 출력 해주기 위해 10
		this.perPageNum = 10;
	}

	public void setPage(int page) {
		if (page <= 0) {
			// 페이지가 1보다 작은 수로 입력이되면 무조건 1로 리턴한다
			this.page = 1;
			return;
		}
		this.page = page;
	}

	public void setPerPageNum(int perPageNum) {
	// 너무 많은 작거나 많은 수를 한 페이지에 read 불가하게 설정해준다
		if (perPageNum <= 0 || perPageNum > 100) {
			// perPageNum 변수에 10을 넣고 리턴
			this.perPageNum = 10;
			return;
		}
		this.perPageNum = perPageNum;
	}

	public int getPage() {
		return page;
	}

	public int getPerPageNum() {
		return this.perPageNum;
	}

	public int getRowStart() {
		// 게시글 10개중에 시작점을 잡아줄 변수
		// ex) (5 - 1 5page) 4 * 10 + 1 = 5페이지는 41
		rowStart = ((page - 1) * perPageNum) + 1;
		return rowStart;
	}

	public int getRowEnd() {
		// 게시글 10개중에 끝지점을 잡아줄 변수
		// ex) 40 ~ 50 > 10 + 9
		rowEnd = rowStart + perPageNum - 1;
		return rowEnd;
	}

	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + ", rowStart=" + rowStart + ", rowEnd=" + rowEnd
				+ "]";
	}

}