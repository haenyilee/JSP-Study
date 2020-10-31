package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sist.controller.RequestMapping;
import com.sist.dao.MovieDAO;
import com.sist.vo.JjimVO;
import com.sist.vo.MovieVO;
import com.sist.vo.ReserveVO;
import com.sist.vo.TheaterVO;

import java.text.SimpleDateFormat;
import java.util.*;
public class ReserveModel {

///////////////////////// 예매하기 페이지로 이동 /////////////////////////////////  
  @RequestMapping("reserve/reserve.do")
  public String reserve_main(HttpServletRequest request)
  {
	  request.setAttribute("main_jsp", "../reserve/reserve.jsp");
	  return "../main/main.jsp";
  }

	  
///////////////////////// 예매 가능한 영화 목록 /////////////////////////////////  
	@RequestMapping("reserve/movie.do")
	public String reserve_movie(HttpServletRequest request)
	{
	List<MovieVO> list=MovieDAO.movieReserveData();
	request.setAttribute("list", list);
	return "../reserve/movie.jsp"; 
	// ajax로 특정 부분만 변화해야 하기 때문에 (단독실행) include하면 안됨 , main.jsp로 이동하면 안됨
	}
	
///////////////////////// 영화관 목록 /////////////////////////////////  
	@RequestMapping("reserve/theater.do")
	public String reserve_theater(HttpServletRequest request)
	{
	// 선택한 영화번호 받기
	String no=request.getParameter("no");
	// DB연동
	String tdata= MovieDAO.movieTheaterNo(Integer.parseInt(no));
	
	StringTokenizer st=new StringTokenizer(tdata,",");
	List<TheaterVO> list = new ArrayList<TheaterVO>();
	
	while(st.hasMoreTokens())
	{
	TheaterVO vo=MovieDAO.theaterListData(Integer.parseInt(st.nextToken()));
	list.add(vo);
	}
	request.setAttribute("list", list);
	return "../reserve/theater.jsp";
	}
  
  
///////////////////////// 예매 달력 /////////////////////////////////    
  @RequestMapping("reserve/date.do")
  public String reserve_date(HttpServletRequest request)
  {
	  String strYear=request.getParameter("year");
	  String strMonth=request.getParameter("month");
	  String tno=request.getParameter("tno");
	  
	  
	  
	  Date date=null;
		try {
			date = new Date();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  // 2020-10
	  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-M-d");
	  // MM dd (X)  M d 01 ~ 09 10 11 12   09
	  // 1 2 3 .... 10 11 12
	  String today=sdf.format(date);
	  StringTokenizer st=new StringTokenizer(today,"-");
	  
	  if(strYear==null)
	  {
		  strYear=st.nextToken();// yyyy
	  }
	  
	  if(strMonth==null)
	  {
		  strMonth=st.nextToken();
	  }
	  
	  // 처음 띄워줄 오늘 날짜
	  int day=Integer.parseInt(st.nextToken());// 화면 
	  int year=Integer.parseInt(strYear);
	  int month=Integer.parseInt(strMonth);
	  
	  Calendar cal=Calendar.getInstance();// Calendar클래스 생성 
	  cal.set(Calendar.YEAR,year);
	  cal.set(Calendar.MONTH,month-1);// month=>0~11
	  cal.set(Calendar.DATE,1);
	  
	  // 요일을 구한다
	  int week=cal.get(Calendar.DAY_OF_WEEK);// 1~7 ==> week-1
	  int lastday=cal.getActualMaximum(Calendar.DATE);
	  String[] strWeek={"일","월","화","수","목","금","토"};
	  System.out.println("요일:"+strWeek[week-1]);
	  // System.out.println("마지막날:"+lastday);
	  
	  // db에서 예약날짜 읽어오기
	  String rday=MovieDAO.theaterReserveData(Integer.parseInt(tno));
	  int [] days=new int[31];
	  StringTokenizer st2=new StringTokenizer(rday,",");
	  
	  while(st2.hasMoreTokens())
	  {
		  String d=st2.nextToken();
		  days[Integer.parseInt(d)]=Integer.parseInt(d);
	  }
	  
	  for(int k:days)
	  {
		  System.out.println("k="+k);
	  }
	  
	  request.setAttribute("rdays", days);
	  
	  // jsp로 전송 
	  request.setAttribute("year", year);
	  request.setAttribute("month", month);
	  request.setAttribute("day", day);
	  request.setAttribute("week", week-1);
	  request.setAttribute("strWeek", strWeek);
	  request.setAttribute("lastday", lastday);
	  // 1일자의 요일 
	  return "../reserve/date.jsp";
  }

  
///////////////////////// 영화 예매 시간 /////////////////////////////////  
  @RequestMapping("reserve/time.do")
  public String reserve_time(HttpServletRequest request)
  {
	  String day=request.getParameter("day");
	  // 데이터베이스 = DB연동
	  String rdays=MovieDAO.dayTimeData(Integer.parseInt(day));
	  // 1(08:00),2(09:00),5(...),6,7,8
	  StringTokenizer st=new StringTokenizer(rdays,",");
	  List<String> list=new ArrayList<String>();
	  while(st.hasMoreTokens())
	  {
		  String s=st.nextToken();
		  String time=MovieDAO.timeData(Integer.parseInt(s));
		  list.add(time);
	  }
	  request.setAttribute("list", list);
	  return "../reserve/time.jsp";
  }
  
///////////////////////// 영화 예매 인원 버튼 /////////////////////////////////  
  @RequestMapping("reserve/inwon.do")
  public String reserve_inwon(HttpServletRequest request)
  {
	  return "../reserve/inwon.jsp";
  }
  
///////////////////////// 예매 정보 전송 /////////////////////////////////  
  @RequestMapping("reserve/reserve_ok.do")
  public String reserve_ok(HttpServletRequest request)
  {
	 
	  try
	  {
		  request.setCharacterEncoding("UTF-8");
	  }catch(Exception ex) {}
	  
	  String mno=request.getParameter("mno");
	  String tname=request.getParameter("tname");
	  String inwon=request.getParameter("inwon");
	  String day=request.getParameter("day");
	  String price=request.getParameter("price");
	  String time=request.getParameter("time");
	  
	  HttpSession session=request.getSession();
	  String id=(String)session.getAttribute("id");
	   
	  ReserveVO vo=new ReserveVO();
	  vo.setId(id);
	  vo.setMno(Integer.parseInt(mno));
	  vo.setInwon(inwon);
	  vo.setPrice(price);
	  vo.setTheater(tname);
	  vo.setTime(day+"("+time+")");
	  // insert
	  MovieDAO.reserveInsert(vo);
	  return "../reserve/reserve_ok.jsp";
  }
  
  
///////////////////////// 영화 예매 마이페이지 /////////////////////////////////  
  @RequestMapping("reserve/mypage.do")
  public String reserve_mypage(HttpServletRequest request)
  {
	  HttpSession session=request.getSession();
	  String id=(String) session.getAttribute("id");
	  List<ReserveVO> list=MovieDAO.mypageReserveListData(id);
	  request.setAttribute("list", list);
	  request.setAttribute("main_jsp", "../reserve/mypage.jsp");
	  
	  // 찜한 정보 출력
	  List<JjimVO> jList=MovieDAO.jjimListData(id);
	  List<MovieVO> mList=new ArrayList<MovieVO>();
	  for(JjimVO vo:jList)
	  {
		  MovieVO mvo=MovieDAO.movieDetailData(vo.getMno());
		  String story=mvo.getStory();
		  // 찜 삭제용 jmno 번호 추가하기
		  mvo.setJmno(vo.getMno());
		  
		  
		  if(story.length()>150)
		  {
			  story=story.substring(0,150)+"...";
			  mvo.setStory(story);
		  }
		  mList.add(mvo);
	  }
	  request.setAttribute("mList", mList);
	  return "../main/main.jsp";
  }
///////////////////////// 예매 관리 어드민 /////////////////////////////////  
  @RequestMapping("reserve/adminpage.do")
  public String reserve_adminpage(HttpServletRequest request)
  {
	  List<ReserveVO> list=MovieDAO.adminpageReserveListData();
	  request.setAttribute("list", list);
	  request.setAttribute("main_jsp", "../reserve/adminpage.jsp");
	  return "../main/main.jsp";
  }
  
///////////////////////// 예매 승인대기 > 승인완료 ///////////////////////////////// 
  @RequestMapping("reserve/admin_ok.do")
  public String reserve_admin_ok(HttpServletRequest request)
  {
	  // 예약 번호 받기
	  String no= request.getParameter("no");
	  // UPDATE하기
	  MovieDAO.reserveOk(Integer.parseInt(no));
	  return "redirect:../reserve/adminpage.do";
  }
///////////////////////// 여러건 동시에 승인완료시키기 /////////////////////////////////   
  @RequestMapping("/reserve/reserve_all_ok.do")
  public String reserve_all_ok(HttpServletRequest request)
  {
	  // 데이터 받기
	  String[] nos=request.getParameterValues("cb");
	  for(String n:nos)
	  {
		  MovieDAO.reserveOk(Integer.parseInt(n));
	  }
	  return "redirect:../reserve/adminpage.do";
  }
}











