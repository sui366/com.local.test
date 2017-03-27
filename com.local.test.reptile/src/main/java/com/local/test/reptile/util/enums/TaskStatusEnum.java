package com.local.test.reptile.util.enums;

/**
 * 
 * @ClassName: ItemEnum
 * @Description: TODO 任务状态
 * @author: xf.sui
 * @date: 2017年3月7日 上午9:12:13
 */
public enum TaskStatusEnum {

	RUN(1, "运行"), STOP(2, "停止");

	private Integer id;
	private String name;

	TaskStatusEnum(Integer id, String name) {
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
