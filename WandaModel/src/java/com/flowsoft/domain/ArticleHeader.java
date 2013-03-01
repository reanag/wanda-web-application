package com.flowsoft.domain;

import java.io.Serializable;
import java.util.StringTokenizer;

public class ArticleHeader extends Article implements Serializable {

	private static final long serialVersionUID = 1L;
	private String contentSnippet;

	private Boolean listLong = true;
	private final static int HEADER_LENGTH = 100;

	public ArticleHeader() {
	}

	public ArticleHeader(Article a) {
		this.id = a.getId();
		this.title = a.getTitle();
		this.setOwner(a.getOwner());
		this.content = a.getContent();
		this.setContentSnippet(generateHeaderContent(a.getContent()));

	}

	private String generateHeaderContent(String c) {
		StringBuilder sb = new StringBuilder();
		// TODO - BACSOG - FIX THIS
		StringTokenizer st = new StringTokenizer(c, ".");
		while (st.hasMoreTokens() && sb.length() < HEADER_LENGTH) {
			sb.append(st.nextToken());
			sb.append(".");
		}
		sb.append("...");
		return sb.toString();
	}

	public Boolean getListLong() {
		return listLong;
	}

	public void setListLong(Boolean listLong) {
		this.listLong = listLong;
	}

	public String getContentSnippet() {
		return contentSnippet;
	}

	public void setContentSnippet(String contentSnippet) {
		this.contentSnippet = contentSnippet;
	}

}
