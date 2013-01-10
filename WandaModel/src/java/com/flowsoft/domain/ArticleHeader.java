package com.flowsoft.domain;

import java.io.Serializable;
import java.util.StringTokenizer;

public class ArticleHeader implements Serializable {

	private static final long serialVersionUID = 1L;
	private String title, author, content, originalContent;
	// listLong = true --> return with title + author + createdTS + 2 sentence
	// from content
	// listLong = false --> return with title + author
	private Integer id;
	private Boolean listLong = true;
	private final static int headerLength = 100;

	public ArticleHeader() {
	}

	public ArticleHeader(Article a) {
		this.id = a.getId();
		this.title = a.getTitle();
		this.setAuthor(a.getOwner().getUsername());
		this.content = generateHeaderContent(a.getContent());
		this.originalContent = a.getContent();
	}

	private String generateHeaderContent(String c) {
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(c, ".");
		while (st.hasMoreTokens() && sb.length() < headerLength) {
			sb.append(st.nextToken());
		}
		sb.append("...");
		return sb.toString();
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Boolean getListLong() {
		return listLong;
	}

	public void setListLong(Boolean listLong) {
		this.listLong = listLong;
	}

	public String getOriginalContent() {
		return originalContent;
	}

	public void setOriginalContent(String originalContent) {
		this.originalContent = originalContent;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
