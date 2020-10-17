package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;

public class BoardDAO {
	private static SqlSessionFactory ssf;
	
	static
	{
		try {
			Reader reader= Resources.getResourceAsReader("Config.xml");
			ssf=new SqlSessionFactoryBuilder().build(reader);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<BoardVO> boardListData(Map map)
	{
		List<BoardVO> list = new ArrayList<BoardVO>();
		SqlSession session= ssf.openSession();
		list=session.selectList("boardListData",map);
		session.close();
		return list;
	}
	
	public static int boardTotalPage()
	{
		int total=0;
		SqlSession session= ssf.openSession();
		total=session.selectOne("boardTotalPage");
		session.close();
		return total;
	}
	
	public static BoardVO boardDetailData(int no)
	{
		SqlSession session = ssf.openSession();
		session.update("hitIncrement", no);
		session.commit();
		BoardVO vo= session.selectOne("boardDetailData",no);
		session.close();
		return vo;
	}
}
