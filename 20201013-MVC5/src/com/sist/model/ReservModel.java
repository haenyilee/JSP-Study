package com.sist.model;

import javax.servlet.http.HttpServletRequest;

import com.sist.controller.RequestMapping;

public class ReservModel {
	@RequestMapping("reserve/movie.do")
	public String movieList(HttpServletRequest request)
	{
		request.setAttribute("msg","예매 => 영화정보" );
		return "../reserve/movie.jsp";
	}
	@RequestMapping("reserve/theater.do")
	public String theaterList(HttpServletRequest request)
	{
		request.setAttribute("msg","예매 => 극장정보" );
		return "../reserve/theater.jsp";
	}

}
