package com.sist.controller;

import java.util.*;
import java.io.*;

public class ComponentScan {
	
	public List<String> fileConfig(String pack,String path)
	{
		// model ��Ű�� ���� ��� �о �� ���� �̸� ã��
		List<String> list=new ArrayList<String>();
		
		try {
			path=path+"\\"+pack.replace(".", "\\");
			File dir=new File(path);
			File[] files=dir.listFiles();
			for(File f:files)
			{
				System.out.println(f.getName()); // ��� : MainModel.java
				String name=f.getName();
				String ext=name.substring(name.lastIndexOf(".")+1);
				if(ext.equals("java"))
				{
					String s= pack+"."+name.substring(0,name.lastIndexOf("."));
					System.out.println(s); // ��� : com.sist.model.MainModel
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	public static void main(String[] args) {
		ComponentScan cs=new ComponentScan();
		String pack="com.sist.model";
		String path="C:\\SpringStudy\\20201014-MVC_Final\\src";
		cs.fileConfig(pack, path);
	}

}
