package com.sist.model;

import javax.servlet.http.HttpServletRequest;

import com.sist.controller.RequestMapping;

public class MainModel {
	@RequestMapping("movie/movie.do")
	public String main_page(HttpServletRequest request)
	{
		request.setAttribute("msg", "Main.jsp");
		return "../main/main.jsp";
	}
	

}
