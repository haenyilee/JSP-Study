package com.sist.dao;
// xml파싱 후에 실행된 결과를 받는 위치이다.
import java.io.*; // xml파일 읽는 용도
import java.util.*; // list를 구현

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.lf5.util.Resource;

public class DataBoardDAO {
	// xml 파싱해서 읽은데이터를 저장해야 하는데 그 기능을 sqlsessionfactory가 한다
	private static SqlSessionFactory ssf;
	// 초기화를 해서 xml읽기가 자동으로 셋팅되게 stati블록을 사용한다.
	static {
		try {
			Reader reader =Resources.getResourceAsReader("Config.xml");
			ssf=new SqlSessionFactoryBuilder().build(reader);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// 목록출력
	public static List<DataBoardVO> boardListData(Map map)
	{
		List<DataBoardVO> list = new ArrayList<DataBoardVO>();
		
		SqlSession session=null;
		try {
			
			// 실행부분
			session=ssf.openSession();
			
			list=session.selectList("boardListData",map);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 반환 : 커넥션을 close시켜준다.
			if(session!=null)
				session.close();
		}
		
		
		return list;
	}
	
	// 총페이지 구하기
	public static int boardTotalPage()
	{
		//지역변수는 반드시 초기화해야한다.
		int total=0;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			total=session.selectOne("boardTotalPage");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally
		{
			if(session!=null) //연결이 되어있다면 연결끊어
				session.close();
		}
		return total;
	}
	
	public static void boardInsert(DataBoardVO vo) 
	   {
	      SqlSession session = null;
	      try {
	         
	         // 미리 만들어 둔 Connection 객체를 가지고 온다.
	         session=ssf.openSession(true);
	         session.insert("boardInsert",vo);
	      }catch (Exception e) {
	         //에러 처리
	         e.printStackTrace();
	      }finally {
	         if(session!=null) //오라클에 연결 되어있다면
	            session.close();
	      }
	   }
	
	// 상세보기
	public static DataBoardVO boardDetailData(int no)
	   {
		   // 데이터를 받을 변수 설정 
		   DataBoardVO vo=new DataBoardVO();
		   // 오라클 연결 
		   SqlSession session=null;// Connection => SqlSession안에 Connection기능을 가지고 있다 
		   // 데이터를 가지고 오는 과정 에러가 발생할 수 있다 => 사전에 에러방지 프로그램 => 예외처리 
		   /*
		    *    예외처리 
		    *     = 직접 처리 (에러가 발생하면 바로 처리해서 사용) try~catch
		    *     = 간접처리 (시스템 에러발생여부만 확인하고 에러 회피) throws 
		    */
		   try
		   {
			   // 정상적으로 실행 => 에러 => catch를 수행하고 점프 
			   // 연결 
			   session=ssf.openSession(); // Commit을 포함하지 않는다 
			   // 작업 수행 
			   // 1. 조회수 증가 
			   // <update id="hitIncrement" parameterType="int">
			   session.update("hitIncrement",no);
			   session.commit();// 포함이 안되면 조회수가 증가를 하지 않는다 
			   // 2. 증가된 조회수를 포함해서 데이터 가지고 오기
			   // <select id="boardDetailData" resultType="DataBoardVO" parameterType="int">
			   vo=session.selectOne("boardDetailData", no);
			   /*
			    *   COMMIT => 저장된 데이터가 변경,추가 ,삭제  => 다시 저장 
			    *             SELECT : 검색 
			    */
			   /*
			    *   <select> 
			    *      = 목록(여러개) => selectList("id명")
			    *      = 한개 => selectOne("id명")
			    *   =================================
			    *   <insert>
			    *      = insert("id명")
			    *   <update>
			    *      = update("id명")
			    *   <delete>
			    *      = delete("id명")
			    *   ================================= 반드시 COMMIT처리를 해야된다 
			    */
		   }catch(Exception ex)
		   {
			   // 에러가 났을 경우에 처리 (복구) 
			   ex.printStackTrace();//어떤 에러가 났는지 확인 
		   }
		   finally
		   {
			   // 정상수행 ,비정상 수행 상관없이 => 무조건 수행 (서버연결 해제,데이터베이스 연결 해제)
			   if(session!=null) // 연결되어 있다면
				   session.close();  // ps.close(), conn.close() => disConnection()
		   }
		   
		   return vo;// 사용자가 요청한 데이터를 받아 볼 수 있다
	   }
	public static int boardFindCount(Map map)
	{
		int count=0;
		SqlSession session=null;
		try {
			// 미리 생성한 커넥션의 생성된  주소를 얻어오기
			session=ssf.openSession();
			count=session.selectOne("boardFindCount",map);
			
		} catch (Exception e) {
			e.getStackTrace();
		} finally
		{
			if(session!=null)
				session.close(); // DBCP반환 재사용
		}
		return count;
	}
	
	public static List<DataBoardVO> boardFindData(Map map)
	{
		List<DataBoardVO> list = new ArrayList<DataBoardVO>();
		
		SqlSession session=null;
		try {
			session=ssf.openSession();
			list=session.selectList("boardFindData",map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null)
				session.close();
		}
		return list;
		
	}
	
	// 삭제하기
	public static boolean boardDelete(int no,String pwd)
	{
		boolean bCheck=false;
		SqlSession session=null;
			try {
			session=ssf.openSession();
			String db_pwd=session.selectOne("boardGetPassword",no);
			
			if(pwd.equals(db_pwd))
			{
				bCheck=true;
				session.delete("boardDelete",no);
				session.commit();
			} 
			else
			{
				bCheck=false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null)
			session.close(); // 커넥션과 preparedstatement가 들어있음
		}
		return bCheck;
	}
	
	public static DataBoardVO boardGetInfoData(int no)
	{
		DataBoardVO vo= new DataBoardVO();
		SqlSession session=null;
		try {
			session=ssf.openSession();
			vo=session.selectOne("boardGetInfoData",no);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null)	
			 session.close();
		}
		
		return vo;
	}
	
	// 수정하기 위해 기존 데이터 읽어오기
	public static DataBoardVO boardUpdateData(int no)
	{
		DataBoardVO vo= new DataBoardVO();
		SqlSession session= null;
		try {
			session=ssf.openSession();
			vo=session.selectOne("boardDetailData",no);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null)
				session.close();
		}
		return vo;
		
	}
	
	// 수정하기 위해 비번 일치 확인
	public static boolean boardUpdate(DataBoardVO vo)
	{
		boolean bCheck=false;
		SqlSession session=null;
		try
		{
			session=ssf.openSession();
			String db_pwd=session.selectOne("boardGetPassword",vo.getNo());
			if(db_pwd.equals(vo.getPwd()))
			{
				bCheck=true;
				session.update("boardUpdate",vo);
				session.commit();
			}
			else
			{
				bCheck=false;
			}
			
		} catch(Exception e)
		{
			e.printStackTrace();
			
		} finally {
			if(session!=null)
				session.close(); 
		}
		return bCheck;
	}
}
