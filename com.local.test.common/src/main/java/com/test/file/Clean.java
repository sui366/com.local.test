package com.test.file;

import java.io.File;


public class Clean {
	
	private String[] spilts = {"java","jsp","html","sql","css","js","png","gif"};
	private String[] excloulds = {"class", "classpath","project","prefs","component","xml","svn-base","db","properties","jar","war","zip","rar"};
	
	private void fileFilter(String path){
		
		File rootFile = new File(path);
		if(rootFile.isDirectory()){
			cleanDirectory(rootFile);
		}
	}
	
	private void cleanDirectory(File derectory){
		File[] files = derectory.listFiles();
		String fileName = null;
		for(File file: files){
			if(file.isFile()){
				if (file.isHidden()) {
					System.out.println("delete file : " + file.getAbsolutePath());
					file.delete();
				}
				else{
					fileName = file.getName();
					for(String str:excloulds){
						if(fileName.toLowerCase().endsWith("." + str)){
							System.out.println("delete file : " + file.getAbsolutePath());
							file.delete();
						}
					}
				}
			}
			else {
				if(file.getName().equalsIgnoreCase(".svn") || file.getName().equalsIgnoreCase(".settings") || file.getName().equalsIgnoreCase("bin") || file.getName().equalsIgnoreCase("target")){
					deleteAll(file);
					System.out.println("delete file : " + file.getAbsolutePath());
					file.delete();
				}else{
					cleanDirectory(file);
					
				}
			}
		}	
	}
	
	private void deleteAll(File file){
		
		File[] files  = file.listFiles();
		for(File file2:files){
			if(file2.isFile()){
				System.out.println("delete file : " + file.getAbsolutePath());
				file2.delete();
			}else{
				deleteAll(file2);
				file2.delete();
			}
		}
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Clean clean = new Clean();
		
		clean.fileFilter("E:\\BaiduYunDownload\\com.shunwang.idc.monitor");
	}

}
