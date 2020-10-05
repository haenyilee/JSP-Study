package com.sist.manager;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.sist.dao.*;
public class BoardManager {
	public void boardListData(HttpServletRequest request)
	{
		// page 받아오기
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		
		// 페이지 나누기
		int curpage=Integer.parseInt(page);
		int rowSize=10;
		int start=(curpage*rowSize)-(rowSize-1);
		int end = (curpage*rowSize);
		
		// 결과값 읽기
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		List<BoardVO> list = BoardDAO.boardListData(map);
		request.setAttribute("list", list);
		// request에 값을 넣었기 때문에 jsp에서 받아서 출력 가능함
		
	}
	
	public static void main(String[] args) {
		
	}

}
