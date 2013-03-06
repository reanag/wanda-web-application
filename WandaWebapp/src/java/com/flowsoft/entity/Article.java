package com.flowsoft.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(schema = "WANDA")
public class Article extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	private WandaUser owner;
	@Column(columnDefinition = "Float default '0'")
	private Double rank;
	@Column(columnDefinition = "Number(10) default '0'")
	private Integer rankCount;

	@ManyToMany(fetch = FetchType.LAZY)
	private Map<Integer, Tag> tagList = new HashMap<Integer, Tag>();

	@ManyToOne(fetch = FetchType.LAZY)
	private Category category;
	private String title;
	@Column(columnDefinition = "varchar2(4000)")
	private String content;

	@Transient
	Logger logger = LoggerFactory.getLogger(Article.class);

	public Article() {
		this.initDefaults();

		this.rank = 0.0;
		this.rankCount = 0;

	}

	public Article(WandaUser owner, String title, String content) {
		this.owner = owner;
		this.title = title;
		this.content = content;
		this.initDefaults();
		this.rank = 0.0;
		this.rankCount = 0;

	}

	public WandaUser getOwner() {
		return owner;
	}

	public void setOwner(WandaUser owner) {
		this.owner = owner;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.modifiedTS = new Date(System.currentTimeMillis());
		this.category = category;
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
		this.modifiedTS = new Date(System.currentTimeMillis());
		this.content = content;
	}

	public Map<Integer, Tag> getTagList() {
		return tagList;
	}

	public void setTagList(Map<Integer, Tag> tagList2) {
		tagList = tagList2;

	}

	public Double getRank() {
		return rank;
	}

	public void setRank(Double rank) {
		this.rank = rank;

	}

	public void calculateRank(Double rank) {

		if (rankCount == null) {
			rankCount = 0;
		}
		if (this.rank == null) {
			this.rank = 0.0;
		}
		this.rank = (this.rank * rankCount + rank) / (rankCount + 1);

		this.rankCount++;

	}

	public Integer getRankCount() {
		return rankCount;
	}

	public void setRankCount(Integer rankCount) {
		this.rankCount = rankCount;
	}

}
