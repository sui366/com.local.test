package com.local.test.reptile.util.enums;

/**
 * 
 * @ClassName: ItemEnum
 * @Description: TODO 分类类别
 * @author: xf.sui
 * @date: 2017年3月7日 上午9:12:13
 */
public enum LevelTypeEnum {

	GAME_SKAY_MOBILE_GAME(1001, "游牧星空-手机游戏"),
	GAME_SKAY_PC_GAME(1002, "游牧星空-单机游戏"),
	GAME_SKAY_INTERNET_GAME(1003, "游牧星空-网络游戏"),
	GAME_SKAY_MENU(1004, "游牧星空-菜单"), 
	GAME_SKAY_NEWS(1005, "游牧星空-新闻"), 
	GAME_SKAY_SKILL(1006, "游牧星空-攻略"), 
	
	BAIDU_HOT_RANK(2001,"百度热议榜"),
	BAIDU_TIEBA_TYPE(2002,"百度贴吧-分类"),
	
	ENJOY_MENU(3001, "有意思吧-菜单"), 
	ENJOY_MONTH_RANK(3002, "有意思吧月度排行榜"), 
	ENJOY_FORGE(3003, "有意思吧一些段子"),
	ENJOY_MAIN_LIST(3004, "有意思吧一首页列表");
	
	
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
