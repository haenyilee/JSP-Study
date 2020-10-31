package com.sist.model;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sist.controller.RequestMapping;
import com.sist.dao.BoardDAO;
import com.sist.vo.BoardVO;
import com.sist.vo.ReplyVO;
public class BoardModel {
// 댓글형 게시판-------------------------------------------------
	/* 목록 출력 */
	@RequestMapping("board/list.do")
	public String board_list(HttpServletRequest request)
	{
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		int rowSize=10;
		int start=(rowSize)*(curpage-1)+1;
		int end=rowSize*curpage;
		
		map.put("start",start);
		map.put("end",end);
		
		List<BoardVO> list=BoardDAO.baordListData(map);
		/* 총페이지 구하기 */
		request.setAttribute("list", list);
		request.setAttribute("main_jsp", "../board/list.jsp");
		
		/* 새로운 게시글에 new 붙이기 */
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String today=sdf.format(date);
		
		return "../main/main.jsp"; // include
	}
	/* 새글 작성 */
	@RequestMapping("board/insert.do")
	public String board_insert(HttpServletRequest request)
	{
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		String name=request.getParameter("naem");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		
		BoardVO vo= new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		request.setAttribute("main_jsp", "../board/insert.jsp");
		return "redirect:../board/list.do";
	}
	/* 상세보기 */
	@RequestMapping("board/detail.do")
	public String board_detail(HttpServletRequest request)
	{
		// DB연동
		String no =request.getParameter("no");
		BoardVO vo=BoardDAO.boardDetailData(Integer.parseInt(no));	
		request.setAttribute("vo", vo);
		// 화면
		request.setAttribute("main_jsp", "../board/detail.jsp");
		
		/* 댓글 관련 */
		List<ReplyVO> list=BoardDAO.replyListData(Integer.parseInt(no));
		request.setAttribute("rList", list);
		return "../main/main.jsp";
	}
	/* 게시물에 댓글 달기 */
	@RequestMapping("board/reply_insert.do")
	public String reply_insert(HttpServletRequest request)
	{
		// 요청값 받기
		try {
			request.setCharacterEncoding("utf-8");
		} catch (Exception e) {
		}
		String bno=request.getParameter("bno");
		String msg=request.getParameter("msg");
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		String name=(String)session.getAttribute("name");
		// vo에 값 넣기
		ReplyVO vo= new ReplyVO();
		vo.setBno(Integer.parseInt(bno));
		vo.setMsg(msg);
		vo.setName(name);
		// dao 연결
		BoardDAO.replyInsert(vo);
		return "redirect:../board/detail.do";
	}
	/*  */
	
}
