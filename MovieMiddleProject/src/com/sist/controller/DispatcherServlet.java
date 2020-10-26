package com.sist.controller;

import java.io.*;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    // Model를 저장 => 사용자 요청 => 찾아주는 역할 
	// Model class를 모아서 메모리 할당 => 찾기 
	List<String> clsList=new ArrayList<String>();
	public void init(ServletConfig config) throws ServletException {
		String xmlPath=config.getInitParameter("contextConfigLocation");
		String path=config.getInitParameter("path");
		try
		{
			// 
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			// 파서기 생성
			DocumentBuilder db=dbf.newDocumentBuilder();

			Document doc=db.parse(new File(xmlPath));
			// 루트 태그
			Element root=doc.getDocumentElement();
			// 회원가입 , 로그인(세션) => 상세 => 쿠키 => 찜하기 , 예매처리 
			NodeList list=root.getElementsByTagName("component-scan");
			ComponentScan com=new ComponentScan();
			for(int i=0;i<list.getLength();i++)
			{
				Element cs=(Element)list.item(i);
				String pack=cs.getAttribute("base-package");
				List<String> cList=com.fileConfig(pack, path);
				for(String s:cList)
				{
					System.out.println(s);
					clsList.add(s);
				}
			}
			
		}catch(Exception ex) {}
		System.out.println("DispatcherServlet");
		for(String s:clsList)
		{
			System.out.println(s);
		}
	}
    // 요청 결과값 ==> 해당 JSP로 전송 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 메소드 찾아서 수행 => 결과값을 JSP로 전송
		// 사용자가 요청한 내용을 받는다 
		// http://localhost
		String cmd=request.getRequestURI();
		// main/main.do
		cmd=cmd.substring(request.getContextPath().length()+1);
		System.out.println(cmd);
		///OnLineStudy18_MVCFinal/
		try
		{
			// 메모리 할당  (Model클래스 메모리 할당) => clsList
			// new MainModel() ==> Controller (한개) ==> 공유 (수정을 하면 => 유지보수가 어렵다) => 고정
			// XML => C/Java/C#/JavaScript
			for(String cls:clsList)
			{
				Class clsName=Class.forName(cls);
				Object obj=clsName.newInstance();
				// MainModel m=new MainModel();
				/*
				 *     class A
				 *     class B
				 *     
				 *     //A a=new A();
				 *    // a=new B();
				 *    Object obj=new A();
				 *    obj=new B();
				 */
				// 메소드를 찾아서 호출 (invoke())
				Method[] methods=clsName.getDeclaredMethods();
				// 클래스에 선언된 모든 메소드를 가지고 온다 
				for(Method m:methods)
				{
					RequestMapping rm=m.getAnnotation(RequestMapping.class);
					if(cmd.equals(rm.value()))
					{
						 String jsp=(String)m.invoke(obj, request);
								// a.display()
						if(jsp.startsWith("redirect"))
						{
							response.sendRedirect(jsp.substring(jsp.indexOf(":")+1));
							// return redirect:list.   
							// return "../main/main.jsp"
						}
						else
						{
							// request전송 
							RequestDispatcher rd=request.getRequestDispatcher(jsp);
							rd.forward(request, response);
						}
						return;// 종료 (response=>사용자에게 데이터 전송시 한번만 수행)
						
					}
				}
			}
			
		}catch(Exception ex) 
		{
			ex.printStackTrace();
		}
		
	}

}
