package com.sist.model;

import javax.servlet.http.HttpServletRequest;

import com.sist.controller.RequestMapping;

public class MemberModel {
	
	@RequestMapping("member/join.do")
	public String memberJoin(HttpServletRequest request)
	{
		
		request.setAttribute("msg","회원가입" );
		return "../member/join.jsp"; // request를 전송할 jsp 파일명이 들어감 (forward vs sendredirect)
	}
	@RequestMapping("member/login.do")
	public String memberLogin(HttpServletRequest request)
	{
		request.setAttribute("msg","로그인" );
		return "../member/login.jsp"; // request를 전송할 jsp 파일명이 들어감
	}

}
