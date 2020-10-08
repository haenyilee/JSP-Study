package com.sist.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import com.sist.model.*;


@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String[] strCmd= {
			"list",
			"detail",
			"insert",
			"update",
			"delete"
	};
	String[] strClass= {
			"com.sist.model.ListModel",
			"com.sist.model.DetailModel",
			"com.sist.model.InsertModel",
			"com.sist.model.UpdateModel",
			"com.sist.model.DeleteModel"
	};
	private Map clsMap = new HashMap();


	public void init(ServletConfig config) throws ServletException {
		try {
			for(int i=0;i<strClass.length;i++)
			{
				Class cls = Class.forName(strClass[i]);
				Object obj=cls.newInstance(); // 메모리 할당(리플렉션)
				System.out.println(strCmd[i]+":"+obj);
				clsMap.put(strCmd[i], obj);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd=request.getRequestURI();
		System.out.println("cmd:"+cmd);
		cmd=cmd.substring(request.getContextPath().length()+1,cmd.lastIndexOf("."));
		System.out.println("cmd2:"+cmd);
		
		Model model =(Model) clsMap.get(cmd);
		
		String jsp=model.execute(request);
		
		RequestDispatcher rd= request.getRequestDispatcher(jsp);
		rd.forward(request, response);
		
	}

}
