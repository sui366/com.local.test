package com.local.test.reptile.domain;

import com.shunwang.business.framework.pojo.BasePojo;
import java.sql.Timestamp;
import java.sql.Date;

public class SpiderData extends BasePojo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private Integer taskId;
	private String imgSrc;
	private String title;
	private String abstractContent;
	private Date publishTime;
	private Long cyCommentCount;
	private Long visitCount;
	private String contentUrl;
	private String tag;
	private String author;
	private Integer starCount;
	private Timestamp addTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
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
	public String getContentUrl() {
		return contentUrl;
	}

	public void setContentUrl(String contentUrl) {
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
	public Timestamp getAddTime() {
		return addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	@Override
	public String toString() {
		return "SpiderData "+ 
				"[id=" + id +
				", taskId=" + taskId + 
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
				", addTime=" + addTime + 
		"]";
	}

}