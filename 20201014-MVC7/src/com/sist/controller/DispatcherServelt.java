package com.sist.controller;

import java.io.*;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*
 *  Annotation 사용 목적
 *  1. Method를 알아서 찾아갈 수 있다.
 *  2. Spring 에서  메소드 명에서  자유롭다
 */
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

import com.sun.xml.internal.ws.api.Component;



@WebServlet("/DispatcherServelt")
public class DispatcherServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// clsList : Model 클래스를 저장하는 ArrayList
	List<String> clsList= new ArrayList<String>();
	public void init(ServletConfig config) throws ServletException {
		String xmlPath=config.getInitParameter("contextConfigLocation");
		
		// path : 프로젝트 경로 (예: C:\\SpringStudy\\20201014-MVC_Final\\src)
		String path="path";
		System.out.println("xml-path:"+xmlPath);
		try {
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc=db.parse(new File(xmlPath));
			// 루트태그 : application-context에 component-scan태그 값 읽기
			Element root=doc.getDocumentElement();
			
			// 회원가입, 로그인(세션) ,상세,쿠키,찜하기,예매처리 기능
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
		String cmd=request.getRequestURI();
		cmd=cmd.substring(request.getContextPath().length()+1);
		
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
						if(jsp.equals("redirect"))
						{
							response.sendRedirect(jsp.substring(jsp.indexOf(":")+1));
							// return redirect:list.do
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




