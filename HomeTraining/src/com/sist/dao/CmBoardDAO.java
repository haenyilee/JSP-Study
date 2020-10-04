package com.sist.dao;
import java.util.*;
import java.sql.*;
public class CmBoardDAO {
    	// 연결 
		private Connection conn;
		// SQL 전송
		private PreparedStatement ps;
		// 오라클 주소 
		private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
		
		public CmBoardDAO()
		{
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
			}catch(Exception ex) {}
		}
		// 2. 연결 메소드
		public void getConnection()
		{
			try
			{
				conn=DriverManager.getConnection(URL,"hr","happy");		
			}catch(Exception ex) {}
		}
		// 3. 닫는 메소드 
		public void disConnection()
		{
			try
			{
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
				// exit 
			}catch(Exception ex) {}
		}
		public ArrayList<CmBoardVO> boardListData(int page)
		{
			ArrayList<CmBoardVO> list=
					new ArrayList<CmBoardVO>();
			try
			{
				getConnection();
				String sql="SELECT no,subject,name,regdate,hit,group_tab,num "
						  +"FROM (SELECT no,subject,name,regdate,hit,group_tab,rownum as num "
						  +"FROM (SELECT no,subject,name,regdate,hit,group_tab "
						  +"FROM CmBoard ORDER BY group_id DESC,group_step ASC)) "
						  +"WHERE num BETWEEN ? AND ?";
					
				ps=conn.prepareStatement(sql);
				int rowSize=10;
				int start=(rowSize*page)-(rowSize-1);
				int end=rowSize*page;
				
				// ? 에 값 채우기 
				ps.setInt(1, start);
				ps.setInt(2, end);
				
				// 실행후에 결과 값 읽기
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					CmBoardVO vo=new CmBoardVO();
					vo.setNo(rs.getInt(1));
					vo.setSubject(rs.getString(2));
					vo.setName(rs.getString(3));
					vo.setRegdate(rs.getDate(4));
					vo.setHit(rs.getInt(5));
					vo.setGroup_tab(rs.getInt(6));
					
					list.add(vo);
				}
				rs.close();
				
			}catch(Exception ex)
			{
				System.out.println(ex.getMessage());
			}
			finally
			{
				disConnection();
			}
			return list;
		}
		
		public int boardRowCount()
		{
			int count=0;
			try
			{
				getConnection();
				String sql="SELECT COUNT(*) FROM CmBoard";
				ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				rs.next();
				count=rs.getInt(1);
				rs.close();
			}catch(Exception ex)
			{
				System.out.println(ex.getMessage());
			}
			finally
			{
				disConnection();
			}
			return count;
		}
		// 2. 상세보기
	
		public CmBoardVO boardDetail(int no,int type)
		{
			CmBoardVO vo=new CmBoardVO();
			try
			{
				getConnection();
				// 조회수 
				String sql="";
				if(type==1)
				{
					sql="UPDATE CmBoard SET "
							  +"hit=hit+1 "
							  +"WHERE no=?";
					ps=conn.prepareStatement(sql);
					ps.setInt(1, no);
					ps.executeUpdate();
				}
				// 증가된 조회수 읽기
				sql="SELECT no,name,subject,content,regdate,hit "
				  +"FROM CmBoard "
				  +"WHERE no=?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, no);
				// 결과값
				ResultSet rs=ps.executeQuery();
		
				rs.next();
				vo.setNo(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setSubject(rs.getString(3));
				vo.setContent(rs.getString(4));
				vo.setRegdate(rs.getDate(5));
				vo.setHit(rs.getInt(6));
				
				rs.close();
				
			}catch(Exception ex)
			{
				System.out.println(ex.getMessage());
			}
			finally
			{
				disConnection();
			}
			return vo;
		}
		// 3. 새글 등록 => INSERT
		public void boardInsert(CmBoardVO vo)
		{
			try
			{
				getConnection();
				String sql="INSERT INTO CmBoard(no,name,subject,content,pwd,group_id) " 
				         + "VALUES(rb_no_seq.nextval,?,?,?,?,"
						 +"(SELECT NVL(MAX(group_id)+1,1) FROM CmBoard))"; 
				ps=conn.prepareStatement(sql);
				ps.setString(1, vo.getName());
				ps.setString(2, vo.getSubject());
				ps.setString(3, vo.getContent());
				ps.setString(4, vo.getPwd());
				
				ps.executeUpdate();
			}catch(Exception ex)
			{
				System.out.println(ex.getMessage());
			}
			finally
			{
				disConnection();
			}
		}
		// 4. 답변하기 
		public void boardReplyInsert(int root,CmBoardVO vo)
		{
			try
			{
				getConnection();
		
				String sql="SELECT group_id,group_step,group_tab "
						  +"FROM CmBoard "
						  +"WHERE no=?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, root);
				ResultSet rs=ps.executeQuery();
				rs.next();
				int gi=rs.getInt(1);// gi
				int gs=rs.getInt(2);// gs+1
				int gt=rs.getInt(3);// gt+1
				rs.close();
				// 문제 발생 
			    sql="UPDATE CmBoard SET "
			       +"group_step=group_step+1 "
			       +"WHERE group_id=? AND group_step>?";
			    ps=conn.prepareStatement(sql);
			    ps.setInt(1, gi);
			    ps.setInt(2, gs);
			    ps.executeUpdate();
		
				// INSERT
			    sql="INSERT INTO CmBoard(no,name,subject,content,pwd,group_id,group_step,group_tab,root) " 
				         + "VALUES(rb_no_seq.nextval,?,?,?,?,"
						 +"?,?,?,?)"; 
			   
				ps=conn.prepareStatement(sql);
				ps.setString(1, vo.getName());
				ps.setString(2, vo.getSubject());
				ps.setString(3, vo.getContent());
				ps.setString(4, vo.getPwd());
				ps.setInt(5, gi);
				ps.setInt(6, gs+1);
				ps.setInt(7, gt+1);
				ps.setInt(8, root);
				ps.executeUpdate();
				// root=>depth+1
				
				sql="UPDATE CmBoard SET "
				   +"depth=depth+1 "
				   +"WHERE no=?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, root);
				ps.executeUpdate();
				
			}catch(Exception ex) 
			{
				System.out.println(ex.getMessage());
			}
			finally
			{
				disConnection();
			}
		}
		// 5. 수정하기 => UPDATE

		public boolean boardUpdate(CmBoardVO vo)
		{
			boolean bCheck=false;
			try
			{
				getConnection();
				String sql="SELECT pwd FROM CmBoard "
						  +"WHERE no=?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, vo.getNo());
				ResultSet rs=ps.executeQuery();
				rs.next();
				String db_pwd=rs.getString(1);
				rs.close();
				
				if(db_pwd.equals(vo.getPwd()))
				{
					bCheck=true;
					
					sql="UPDATE CmBoard SET "
					   +"name=?,subject=?,content=? "
					   +"WHERE no=?";
					ps=conn.prepareStatement(sql);
					ps.setString(1, vo.getName());
					ps.setString(2, vo.getSubject());
					ps.setString(3, vo.getContent());
					ps.setInt(4, vo.getNo());
					ps.executeUpdate();
				}
				
				
			}catch(Exception ex)
            {
				System.out.println(ex.getMessage());
            }
			finally
			{
				disConnection();
			}
			return bCheck;
		}
		// 6. 삭제하기 
		// 7. 찾기 => LIKE , REGEXP_LIKE
		public ArrayList<CmBoardVO> boardFindData(String fs,String ss)
		{
			ArrayList<CmBoardVO> list=
					new ArrayList<CmBoardVO>();
			try
			{
				getConnection();
				String sql="SELECT no,subject,name,regdate,hit "
						  +"FROM CmBoard "
						  +"WHERE "+fs+" LIKE '%"+ss+"%'";
				// ps.setString(1,fs) ==> 'name'
				// WHERE name LIKE
				ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					CmBoardVO vo=new CmBoardVO();
					vo.setNo(rs.getInt(1));
					vo.setSubject(rs.getString(2));
					vo.setName(rs.getString(3));
					vo.setRegdate(rs.getDate(4));
					vo.setHit(rs.getInt(5));
					list.add(vo);
				}
				rs.close();
			}catch(Exception ex)
			{
				System.out.println(ex.getMessage());
			}
			finally
			{
				disConnection();
			}
			return list;
		}
		
}

