package com.local.test.reptile.pojo.vo;

import com.local.test.reptile.pojo.bo.SpiderDataBo;
import com.local.test.reptile.util.DateUtil;
import com.local.test.reptile.util.enums.PlatfromEnum;

@SuppressWarnings("serial")
public class SpiderDataVo extends SpiderDataBo {


	public String getPublishTimeStr() {
		return DateUtil.format(getPublishTime(), DateUtil.FORMAT_1);
	}
	
	public String getPublishTimeStr2() {
		return DateUtil.format(getAddTime(), DateUtil.FORMAT_2);
	}


	public String getAddTimeStr() {
		return DateUtil.format(getAddTime(), DateUtil.FORMAT_1);
	}

	public String getPlatformName(){
		return PlatfromEnum.getNameById(getPlatformId());
	}

}