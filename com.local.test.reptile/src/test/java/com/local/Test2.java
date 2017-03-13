package com.local;

public class Test2 {

	public static void main(String[] args) {//584900.0==826432.0
//		System.out.println(584900/826432);
//		
//		BigDecimal x1 = new BigDecimal("584900.0");
//		BigDecimal x2 = new BigDecimal("826432.0");
////		BigDecimal x1 = new BigDecimal("5558");
////		BigDecimal x2 = new BigDecimal("826");
//		BigDecimal divide = x1.divide(x2, 2, BigDecimal.ROUND_HALF_DOWN);
//		System.out.println(x1 +":"+x2+"====="+x1.divide(x2, 2, BigDecimal.ROUND_HALF_DOWN));
		
		String link = "<a href=\"http://www.u148.net/tale/10380.html\" target=\"_blank\">男人穿衣的20条黄金规律</a>";
    	String regex = "<a([\\S|\\s]+)>([\\S|\\s]+)</a>";
    	System.out.println(link.replaceAll(regex, "$2").trim());
		
	}
}
