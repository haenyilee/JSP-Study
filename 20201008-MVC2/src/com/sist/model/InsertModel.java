package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public class InsertModel implements Model {

	@Override
	public String execute(HttpServletRequest request) {
		request.setAttribute("msg","새글쓰기");
		return "board/insert.jsp";
	}

}
