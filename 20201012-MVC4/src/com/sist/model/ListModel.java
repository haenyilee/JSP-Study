package com.sist.model;

import javax.servlet.http.HttpServletRequest;

import java.util.*;
import com.sist.dao.*;

public class ListModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		
		Map map = new HashMap();
		int rowSize=10;
		int start=rowSize*(curpage-1)+1;
		int end = rowSize*curpage;
		
		map.put("start", start);
		map.put("end", end);
		
		List<BoardVO> list=BoardDAO.boardListData(map);
		int totalpage=BoardDAO.boardTotalPage();
		
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		
		return "board/list.jsp";
	}

}
