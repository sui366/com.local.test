package com.local;

import java.math.BigDecimal;

public class Test2 {

	public static void main(String[] args) {//584900.0==826432.0
		System.out.println(584900/826432);
		
		BigDecimal x1 = new BigDecimal("584900.0");
		BigDecimal x2 = new BigDecimal("826432.0");
//		BigDecimal x1 = new BigDecimal("5558");
//		BigDecimal x2 = new BigDecimal("826");
		BigDecimal divide = x1.divide(x2, 2, BigDecimal.ROUND_HALF_DOWN);
		System.out.println(x1 +":"+x2+"====="+x1.divide(x2, 2, BigDecimal.ROUND_HALF_DOWN));
	}
}
