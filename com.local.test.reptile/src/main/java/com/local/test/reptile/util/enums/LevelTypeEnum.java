package com.local.test.reptile.util.enums;

/**
 * 
 * @ClassName: ItemEnum
 * @Description: TODO 分类类别
 * @author: xf.sui
 * @date: 2017年3月7日 上午9:12:13
 */
public enum LevelTypeEnum {

	MENU(1, "菜单"), NEWS(2, "新闻"), STRATEGY(3, "攻略");

	private Integer id;
	private String name;

	LevelTypeEnum(Integer id, String name) {
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
