package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public class ListModel implements Model {

	@Override
	public String execute(HttpServletRequest request) {
		request.setAttribute("msg","게시판 목록");
		return "board/list.jsp";
	}

}
