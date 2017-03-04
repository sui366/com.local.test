package com.test.zip;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.google.common.collect.Maps;

public class ZipTest {

	public static void scanZipFile(String zipname) {
		try {
			ZipInputStream zin = new ZipInputStream(new FileInputStream(zipname));
			ZipEntry entry;
			while ((entry = zin.getNextEntry()) != null) {
				System.out.println(entry.getName());
				zin.closeEntry();
			}
			zin.close();
		} catch (IOException e) {
		}
	}

	public static HashMap<String, String> loadZipFile(String zipname, String name) {
		HashMap<String, String> map = Maps.newHashMap();
		try {
			ZipInputStream zin = new ZipInputStream(new FileInputStream(zipname));
			ZipEntry entry;
			while ((entry = zin.getNextEntry()) != null) {
				if (entry.getName().equals(name)) {
					BufferedReader in = new BufferedReader(new InputStreamReader(zin));
					String s;
					while ((s = in.readLine()) != null){
						if(org.apache.commons.lang3.StringUtils.isNotBlank(s) && s.contains("=")){
							String[] tmp = s.split("=");
							map.put(tmp[0], tmp[1]);
						}
					}
				}
				zin.closeEntry();
			}
			zin.close();
		} catch (IOException e) {
		}
		return map;
	}
	
	public static void main(String[] args) {
		
		ZipTest.loadZipFile("D:\\java\\static\\appBoss\\nas_pages\\edit\\pages\\theme\\material\\SC_1488164929890_13.zip", "RoleAdmin.ini");
	}
}