package com.sist.dao;
import java.io.*;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MemberDAO {
	private static SqlSessionFactory ssf;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf=new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static MemberVO memberLogin(String id,String pwd)
	{
		MemberVO vo = new MemberVO();
		SqlSession session= null;
		try {
			session=ssf.openSession();
			//<select id="memberIdCheck" resultType="int" parameterType="String">
			int count=session.selectOne("memberIdCheck",id);
			if(count==0) // 아이디 없으면
			{
				vo.setMsg("NOID");
			}
			else
			{
				// 비밀번호 검사
				//<select id="memberGetInfoData" resultType="com.sist.dao.MemberVO" parameterType="String">
				vo=session.selectOne("memberGetInfoData",id);
				if(pwd.equals(vo.getPwd()))
				{
					vo.setMsg("OK");
				}
				else
				{
					vo.setMsg("NOPWD");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null)
				session.close();
		}
		
		return vo;
	}
}
