package com.sist.model;

import javax.servlet.http.HttpServletRequest;

import com.sist.controller.RequestMapping;

public class MovieModel {
	@RequestMapping("main/main.do")
	public String main_page(HttpServletRequest request)
	{
		return "../main/main.jsp";
	}
	@RequestMapping("movie/list.do")
	public String movieListData(HttpServletRequest request)
	{
		request.setAttribute("msg","영화목록" );
		request.setAttribute("main_jsp","../movie/list.jsp" );
		return "../main/main.jsp";
	}
	
	@RequestMapping("movie/detail.do")
	public String movieDetailData(HttpServletRequest request)
	{
		request.setAttribute("msg","영화 상세보기" );
		request.setAttribute("main_jsp","../movie/detail.jsp" );
		return "../main/main.jsp";
	}

}
