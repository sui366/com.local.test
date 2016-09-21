package com.test.dfa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
/**
 * 创建敏感词库
 * @ClassName: Library 
 * @Description: TODO
 * @author: xf.sui
 * @date: 2016年9月21日 下午3:19:13
 * 参考：http://blog.csdn.net/chenssy/article/details/26961957
 */
public class Library {

	/**
	 * 读取敏感词库，将敏感词放入HashSet中，构建一个DFA算法模型：<br>
	 * 中 = { isEnd = 0 国 = {<br>
	 * isEnd = 1 人 = {isEnd = 0 民 = {isEnd = 1} } 男 = { isEnd = 0 人 = { isEnd =
	 * 1 } } } } 五 = { isEnd = 0 星 = { isEnd = 0 红 = { isEnd = 0 旗 = { isEnd = 1
	 * } } } }
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map addSensitiveWordToHashMap(Set<String> keyWordSet) {
		Map sensitiveWordMap = new HashMap(keyWordSet.size()); // 初始化敏感词容器，减少扩容操作
		String key = null;
		Map nowMap = null;
		Map<String, String> newWorMap = null;
		// 迭代keyWordSet
		Iterator<String> iterator = keyWordSet.iterator();
		while (iterator.hasNext()) {
			key = iterator.next(); // 关键字
			nowMap = sensitiveWordMap;
			for (int i = 0; i < key.length(); i++) {
				char keyChar = key.charAt(i); // 转换成char型
				Object wordMap = nowMap.get(keyChar); // 获取

				if (wordMap != null) { // 如果存在该key，直接赋值
					nowMap = (Map) wordMap;
				} else { // 不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
					newWorMap = new HashMap<String, String>();
					newWorMap.put("isEnd", "0"); // 不是最后一个
					nowMap.put(keyChar, newWorMap);
					nowMap = newWorMap;
				}

				if (i == key.length() - 1) {
					nowMap.put("isEnd", "1"); // 最后一个
				}
			}
		}
		return sensitiveWordMap;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set<String> keyWordSet = new HashSet<String>();
		keyWordSet.add("日本人");
		keyWordSet.add("日本男人");
		
		Library library = new Library();
		
		Map nowMap = library.addSensitiveWordToHashMap(keyWordSet);
		
		System.out.println(nowMap.hashCode());
	}

}
