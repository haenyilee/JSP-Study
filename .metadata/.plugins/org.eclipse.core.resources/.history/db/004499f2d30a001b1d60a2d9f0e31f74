package com.sist.dao;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class ChallengeDAO {
	private static SqlSessionFactory ssf;
	static
	{
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf=new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	//<!-- 새 도전 만들기 -->
	//<insert id="challengeInsert" parameterType="ChallengeVO">
	public static void challengeInsert(ChallengeVO vo)
	{
		SqlSession session=null;
		try
		{
			session=ssf.openSession(true);
			session.insert("challengeInsert", vo);
		}catch(Exception ex)
		{
			 ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close(); //connection닫기(반환)
		}
	}

	//<!-- 챌린지 목록 -->
	//<select id="challengeListData" resultType="challengeVO" parameterType="hashmap">
	public static List<ChallengeVO> challengeListData(Map map)
	{
		List<ChallengeVO> list=new ArrayList<ChallengeVO>();
		SqlSession session=null;
		try
		{
			session=ssf.openSession();
			list=session.selectList("challengeListData",map);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		return list;
	}
	
	//<!-- 챌린지 목록 전체 페이지  -->
	//<select id="challengeTotalPage" resultType="int">

	//<!-- 챌린지 cate별로 페이지 나눈 목록 -->
	//<select id="challengeListData" resultType="challengeVO" parameterType="hashmap">

	
	//<!-- 도전 상세보기 -->
	//<select id="challengDetailData" resultType="challengeVO" parameterType="int">

 	//<select id="challengGetPassword" resultType="String" parameterType="int">

}
