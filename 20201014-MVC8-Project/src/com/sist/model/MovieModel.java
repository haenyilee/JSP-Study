package com.sist.model;

import javax.servlet.http.HttpServletRequest;

import com.sist.controller.RequestMapping;

public class MovieModel {
	
	@RequestMapping("movie/real.do")
	public String movie_real(HttpServletRequest request)
	{
		request.setAttribute("msg", "����󿵿�ȭ");
		request.setAttribute("main_jsp", "..movie/real.jsp");
		return "../movie/real.jsp";
	}
	
	@RequestMapping("movie/sch.do")
	public String movie_sch(HttpServletRequest request)
	{
		request.setAttribute("msg", "����������ȭ");
		request.setAttribute("main_jsp", "..movie/sch.jsp");
		return "../movie/sch.jsp";
	}
	
	@RequestMapping("movie/box.do")
	public String movie_box(HttpServletRequest request)
	{
		request.setAttribute("msg", "�ڽ����ǽ�");
		request.setAttribute("main_jsp", "..movie/box.jsp");
		return "../movie/box.jsp";
	}
}
