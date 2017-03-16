package com.local.test.reptile.util.enums;

/**
 * 
 * @ClassName: ItemEnum
 * @Description: TODO 数据来源
 * @author: xf.sui
 * @date: 2017年3月7日 上午9:12:13
 */
public enum PlatfromEnum {

	GAME_SKY(1, "游牧星空", "http://www.gamersky.com"), 
	BAIDU_BA(2, "百度贴吧", "http://www.baidu.com"), 
	ENJOY(3, "有意思吧", "http://www.u148.net/");

	private Integer id;
	private String name;
	private String url;

	PlatfromEnum(Integer id, String name, String url) {
		this.id = id;
		this.name = name;
		this.url = url;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static String getNameById(Integer id) {
		for (PlatfromEnum item : PlatfromEnum.values()) {
			if (null != id && id.intValue() == item.getId().intValue()) {
				return item.getName();
			}
		}
		return "";
	}
	public static String getUrlById(Integer id) {
		for (PlatfromEnum item : PlatfromEnum.values()) {
			if (null != id && id.intValue() == item.getId().intValue()) {
				return item.getUrl();
			}
		}
		return "";
	}

}
