package com.local.test.reptile.util.enums;

/**
 * 
 * @ClassName: ItemEnum
 * @Description: TODO 数据来源
 * @author: xf.sui
 * @date: 2017年3月7日 上午9:12:13
 */
public enum PlatfromEnum {

	GAME_SKY(1, "游牧星空"), BAIDU_BA(2, "新闻百度贴吧"), INTEREST_BA(3, "有意思吧");

	private Integer id;
	private String name;

	PlatfromEnum(Integer id, String name) {
		this.id = id;
		this.name = name;
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

//	public static Integer getIdByKey(String key) {
//		for (ItemEnum item : ItemEnum.values()) {
//			if (item.getItemKey().equalsIgnoreCase(key)) {
//				return item.getItemId();
//			}
//		}
//		return null;
//	}

}
