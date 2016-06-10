package com.test.sheduler;

import java.util.Map;

/**
 *
 */

public class ProjCheckTimerTask implements JobServ {
	
//	private Logger logger = Logger.getLogger(getClass());
	
	/**是否运行调度*/
	private boolean start_or_stop;
	
	/** 短信接收用户  */
	private Map<String, String> mobileMap;
	
	@Override
	public void execute() {
		if(start_or_stop){
			synchronized(this){
//				logger.info("============【项目对账结束】==============");
			}
		}
	}
	
	
	
	public boolean isStart_or_stop() {
		return start_or_stop;
	}

	public void setStart_or_stop(boolean start_or_stop) {
		this.start_or_stop = start_or_stop;
	}

	public Map<String, String> getMobileMap() {
		return mobileMap;
	}

	public void setMobileMap(Map<String, String> mobileMap) {
		this.mobileMap = mobileMap;
	}
}
