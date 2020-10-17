package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public class InsertOkMode implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return "redirect:list.do";
	}

}
