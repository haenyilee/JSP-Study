package com.sist.controller;

import java.util.*;
import java.io.*;
public class ComponentScan {
	public List<String> fileConfig(String pack,String path)
	{
		List<String> list = new ArrayList<String>();
		
		try 
		{
//			path=path+"\\"+pack.replace(".","\\");
			path=path+"/"+pack.replace(".","/");
			File dir = new File(path);
			File[] files = dir.listFiles();
			for(File f:files)
			{
				String name=f.getName();
				String ext=name.substring(name.lastIndexOf(".")+1);
				if(ext.equals("java")) //확장자가 자바 파일만 가지고 들어온다.
				{
					String s=pack+"."+name.substring(0,name.lastIndexOf("."));
					System.out.println("====="+pack+"=====");
					System.out.println(s);
					list.add(s);
				}
			}	
		}catch (Exception e) {}
		return list;
	}
	public static void main(String[] args) {
		ComponentScan cs = new ComponentScan();
		String pack= "com.sist.model";
//		String path="C:\\webDev\\webStudy\\OnLineStudy18_MVCFinal\\src";
		String path="/Users/haeni/Documents/jsp-project/Homefit/Home_fit/src/";
		cs.fileConfig(pack, path);
	}
}
