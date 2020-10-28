package com.sist.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.*;
import com.sist.vo.*;
public class MovieDAO {
   private static SqlSessionFactory ssf;
   static
   {
	   ssf=CreateSqlSessionFactory.getSsf();
   }
// ****영화****
///////////////////////// 카테고리별 영화 목록 /////////////////////////////////
   public static List<MovieVO> movieListData(Map map)
   {
	   SqlSession session=ssf.openSession();
	   List<MovieVO> list=session.selectList("movieListData",map);
	   session.close();// 반환
	   return list;
   }
   
/////////////////////////  영화 카테고리별 전체 페이지수 ////////////////////////////
   public static int movieTotalPage(int cateno)
   {
	   SqlSession session=ssf.openSession();
	   int total=session.selectOne("movieTotalPage", cateno);
	   session.close();
	   return total;
   }
/////////////////////////  영화 전체 리스트  /////////////////////////////////
   public static List<MovieVO> movieTotalData(Map map)
   {
	   SqlSession session=ssf.openSession();
	   List<MovieVO> list=session.selectList("movieTotalData",map);
	   session.close();// 반환
	   return list;
   }
/////////////////////////  영화 전체 페이지 수 / /////////////////////////////////  
   public static int movieTotalPage2()
   {
	   SqlSession session=ssf.openSession();
	   int total=session.selectOne("movieTotalPage2");
	   session.close();
	   return total;
   }
///////////////////////// 영화 상세 보기 /////////////////////////////////
   public static MovieVO movieDetailData(int no)
   {
	   SqlSession session=ssf.openSession();
	   session.update("hitIncrement", no);
	   session.commit();
	   MovieVO vo=session.selectOne("movieDetailData", no);
	   session.close();
	   return vo;
   }

///////////////////////// 영화뉴스 리스트 /////////////////////////////////   
   public static List<NewsVO> movieNewsData(Map map)
   {
	   SqlSession session=ssf.openSession();
	   List<NewsVO> list=session.selectList("movieNewsData",map);
	   session.close();
	   return list;
   }
   
///////////////////////// 영화뉴스 전체 페이지 수 /////////////////////////////////
   public static int newsTotalPage()
   {
	   SqlSession session=ssf.openSession();
	   int total=session.selectOne("newsTotalPage");
	   session.close();
	   return total;
   }
   
///////////////////////// 영화 예매 목록 /////////////////////////////////  
   public static List<MovieVO> movieReserveData()
   {
	   SqlSession session=ssf.openSession();
	   List<MovieVO> list=session.selectList("movieReserveData");
	   session.close();// 반환
	   return list;
   }
   
///////////////////////// 영화의 영화관 번호 찾기????  /////////////////////////////////
   public static String movieTheaterNo(int no)
   {
	   SqlSession session=ssf.openSession();
	   String tdata=session.selectOne("movieTheaterNo",no);
	   session.close();
	   return tdata;
	   
   }
   
///////////////////////// 영화관 정보  찾기  /////////////////////////////////
   public static TheaterVO theaterListData(int tno)
   {
	   SqlSession session=ssf.openSession();
	   TheaterVO tdata=session.selectOne("theatListData",tno);
	   session.close();
	   return tdata;
	   
   }
   
///////////////////////// 영화관 상영일  찾기 /////////////////////////////////
   public static String  theaterReserveData (int tno)
   {
	   SqlSession session= ssf.openSession();
	   String rday=session.selectOne("theaterReserveData",tno);
	   session.close();
	   return rday;
   }
   
///////////////////////// 영화관 상영시간  찾기??????? /////////////////////////////////
   public static String dayTimeData(int rday)
   {
	   SqlSession session = ssf.openSession();
	   String times=session.selectOne("dayTimeData",rday);
	   session.close();
	   return times;
   }
 
///////////////////////// 영화관 상영 시간대 /////////////////////////////////
   public static String timeData(int tno)
   {
	   SqlSession session=ssf.openSession();
	   String time=session.selectOne("timeData", tno);
	   session.close();
	   return time;
   }

///////////////////////// 영화 예매자 정보 추가  /////////////////////////////////
   public static void reserveInsert(ReserveVO vo)
   {
	   SqlSession session=ssf.openSession(true);
	   session.insert("reserveInsert",vo);
	   session.close();
   }
  
///////////////////////// 마이페이지 조회 /////////////////////////////////
   public static List<ReserveVO> mypageReserveListData(String id)
   {
	   SqlSession session=ssf.openSession();
	   List<ReserveVO> list=session.selectList("mypageReserveListData",id);
	   session.close();
	   return list;
   }
   
///////////////////////// 예약 관리자 페이지 조회  /////////////////////////////////
   public static List<ReserveVO> adminpageReserveListData()
   {
	   SqlSession session=ssf.openSession();
	   List<ReserveVO> list=session.selectList("adminpageReserveListData");
	   session.close();
	   return list;
   }
   
   
}




