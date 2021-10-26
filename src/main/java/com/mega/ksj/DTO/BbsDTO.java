package com.mega.ksj.DTO;

public class BbsDTO {

	private int postid;
	private String title;
	private String content;
	private String writer;
	private int view = 0;
	public int getPostid() {
		return postid;
	}
	public void setPostid(int postid) {
		this.postid = postid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getView() {
		return view;
	}
	public void setView(int view) {
		this.view = view;
	}
	@Override
	public String toString() {
		return "BbsDTO [postid=" + postid + ", title=" + title + ", content=" + content + ", writer=" + writer
				+ ", view=" + view + "]";
	}

	
	
}//class
