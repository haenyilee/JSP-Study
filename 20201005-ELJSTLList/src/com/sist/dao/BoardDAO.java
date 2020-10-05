package com.sist.dao;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class BoardDAO {
	private static SqlSessionFactory ssf;
	static
	{
		try {
			Reader reader= Resources.getResourceAsReader("Config.xml");
			// XML파싱해서 필요한 데이터를 저장한다.
			ssf=new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 목록 가져오기
	//<select id="boardListData" resultType="BoardVO" parameterType="hashmap">
	public static List<BoardVO> boardListData(Map map)
	{
		List<BoardVO> list = new ArrayList<BoardVO>();
		SqlSession session =null;
		try {
			session=ssf.openSession();
			// SQL문장 실행 요청
			list=session.selectList("boardListData",map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null)
				session.close();
		}
		return list;
	}
	
}
