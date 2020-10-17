package com.sist.controller;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<String> list= new ArrayList<String>();
	
	public void init(ServletConfig config) throws ServletException {
		list.add("com.sist.model.MovieModel");
		list.add("com.sist.model.MemberModel");
		list.add("com.sist.model.ReserveModel");
		
	}


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try 
		{
			String cmd=request.getRequestURI(); // cmd: URL 중에서 URI
			cmd=cmd.substring(request.getContextPath().length()+1); // cmd: (파일명).jsp
			
			// Annotation찾는 방법
			for(String strCls:list)
			{
				// 메모리 할당
				Class clsName=Class.forName(strCls);
				Object obj=clsName.newInstance();
				
				// 메소드 찾기
				Method[] methods=clsName.getDeclaredMethods();
				for(Method m:methods)
				{
					RequestMapping rm=m.getAnnotation(RequestMapping.class);
					if(cmd.equals(rm.value()))
					{
						String jsp=(String)m.invoke(obj, request);
						if(jsp.startsWith("redirect"))
						{
							// return "redirect:list.do"
							response.sendRedirect(jsp.substring(jsp.indexOf(":")+1));
						}
						else {
							RequestDispatcher rd=request.getRequestDispatcher(jsp);
							rd.forward(request, response);
						}
						return;
					}
				}
				
			}
			
		} catch (Exception e) 
		{
			
		}
		
		
	}

}
