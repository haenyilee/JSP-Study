package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public class DetailModel implements Model {

	@Override
	public String execute(HttpServletRequest request) {
		request.setAttribute("msg","내용보기");
		return "board/detail.jsp";
	}
	
}
