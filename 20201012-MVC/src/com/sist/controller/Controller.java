package com.sist.controller;
import java.io.*;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// xml파싱할때 사용하는 라이브러리
import javax.xml.parsers.*;
import org.w3c.dom.*;
import com.sist.model.*;
// 연결(Model <===> JSP)
/*
 *  Controller 등록 => web.xml
 */
import java.util.*;



@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Map clsMap = new HashMap();
	public void init(ServletConfig config) throws ServletException {
		String path=config.getInitParameter("contextConfigLocation");
		System.out.println(path);
		try {
			DocumentBuilderFactory dbf= DocumentBuilderFactory.newInstance();
			DocumentBuilder db= dbf.newDocumentBuilder();
			Document doc= db.parse(new File(path));
			
			Element root=doc.getDocumentElement();
			System.out.println(root.getTagName());
			
			NodeList list=root.getElementsByTagName("bean");
			for(int i=0; i<list.getLength();i++)
			{
				Element bean = (Element)list.item(i);
				String cmd=bean.getAttribute("id");
				String cls=bean.getAttribute("class");
				System.out.println("cmd="+cmd+",class="+cls);
				
				Class clsName=Class.forName(cls);
				Object obj = clsName.newInstance();
				System.out.println("할당된 주소:"+obj);
				
				clsMap.put(cmd,obj);
				
			}
			
			
		} catch (Exception e) {
		}
	}


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd=request.getRequestURI();
		cmd=cmd.substring(request.getContextPath().length()+1);
		Model model=(Model)clsMap.get(cmd);
		String jsp=model.handlerRequest(request);
		
		if(jsp.startsWith("redirect"))
		{
			response.sendRedirect(jsp.substring(jsp.indexOf(":")+1));
		}
		else
		{
			RequestDispatcher rd=request.getRequestDispatcher(jsp);
			rd.forward(request, response);
		}
		

	}

}
