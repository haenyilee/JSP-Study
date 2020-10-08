package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class ListModel implements Model{

	@Override
	public String handlerRequest(HttpServletRequest request) {
		List<String> list=new ArrayList<String>();
		list.add("홍길동1");
		list.add("홍길동2");
		list.add("홍길동3");
		list.add("홍길동4");
		list.add("홍길동5");
		request.setAttribute("list", list);
		return "board/list.jsp";
	}


}
