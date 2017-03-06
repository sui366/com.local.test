package com.local.test.reptile.pojo.po;

import java.util.Date;

import com.shunwang.business.framework.pojo.BasePojo;

public class SpiderData extends BasePojo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private Integer sourceType;
	private String imgSrc;
	private String title;
	private String abstractContent;
	private Date publishTime;
	private Long cyCommentCount;
	private Long visitCount;
	private Long contentUrl;
	private String tag;
	private String author;
	private Integer starCount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}
	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getAbstractContent() {
		return abstractContent;
	}

	public void setAbstractContent(String abstractContent) {
		this.abstractContent = abstractContent;
	}
	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public Long getCyCommentCount() {
		return cyCommentCount;
	}

	public void setCyCommentCount(Long cyCommentCount) {
		this.cyCommentCount = cyCommentCount;
	}
	public Long getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(Long visitCount) {
		this.visitCount = visitCount;
	}
	public Long getContentUrl() {
		return contentUrl;
	}

	public void setContentUrl(Long contentUrl) {
		this.contentUrl = contentUrl;
	}
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getStarCount() {
		return starCount;
	}

	public void setStarCount(Integer starCount) {
		this.starCount = starCount;
	}

	@Override
	public String toString() {
		return "SpiderData "+ 
				"[id=" + id +
				", sourceType=" + sourceType + 
				", imgSrc=" + imgSrc + 
				", title=" + title + 
				", abstractContent=" + abstractContent + 
				", publishTime=" + publishTime + 
				", cyCommentCount=" + cyCommentCount + 
				", visitCount=" + visitCount + 
				", contentUrl=" + contentUrl + 
				", tag=" + tag + 
				", author=" + author + 
				", starCount=" + starCount + 
		"]";
	}

}