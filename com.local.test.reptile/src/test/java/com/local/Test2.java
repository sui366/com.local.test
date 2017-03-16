package com.local;

import org.springframework.util.ClassUtils;

public class Test2 {

	public static void main(String[] args) {// 584900.0==826432.0
		// System.out.println(584900/826432);
		//
		// BigDecimal x1 = new BigDecimal("584900.0");
		// BigDecimal x2 = new BigDecimal("826432.0");
		//// BigDecimal x1 = new BigDecimal("5558");
		//// BigDecimal x2 = new BigDecimal("826");
		// BigDecimal divide = x1.divide(x2, 2, BigDecimal.ROUND_HALF_DOWN);
		// System.out.println(x1 +":"+x2+"====="+x1.divide(x2, 2,
		// BigDecimal.ROUND_HALF_DOWN));

		String link = "<div class=\"data-right\">浏览：7539 / 评论：0</div>";
		String regex = "<div class=\"data-right\">浏览：(\\d+) \\/ 评论：(\\d+)</div>";
//		String link = "<divclass=\"data-right\">浏览7539评论0</div>";
//		String regex = "<divclass=\"data-right\">浏览(\\d+)评论(\\d+)</div>";
		
		String visitCount = link.replaceAll(regex, "$1");
		String cyCommentCount = link.replaceAll(regex, "$2");
		
		
		System.out.println(visitCount + "    " +cyCommentCount);

	}

//	protected final Archive createArchive() throws Exception {
//		ProtectionDomain protectionDomain = getClass().getProtectionDomain();
//		CodeSource codeSource = protectionDomain.getCodeSource();
//		URI location = (codeSource == null ? null : codeSource.getLocation().toURI());
//		String path = (location == null ? null : location.getSchemeSpecificPart());
//		if (path == null) {
//			throw new IllegalStateException("Unable to determine code source archive");
//		}
//		File root = new File(path);
//		if (!root.exists()) {
//			throw new IllegalStateException("Unable to determine code source archive from " + root);
//		}
//
////		return (root.isDirectory() ? new ExplodedArchive(root) : new JarFileArchive(root));
//	}

	private static final String[] WEB_ENVIRONMENT_CLASSES = { "javax.servlet.Servlet", "org.springframework.web.context.ConfigurableWebApplicationContext" };

	private boolean deduceWebEnvironment() {
		for (String className : WEB_ENVIRONMENT_CLASSES) {
			if (!ClassUtils.isPresent(className, null)) {
				return false;
			}
		}
		return true;
	}
}
