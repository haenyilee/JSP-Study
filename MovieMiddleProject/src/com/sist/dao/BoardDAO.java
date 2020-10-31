package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.BoardVO;
import com.sist.vo.ReplyVO;

public class BoardDAO {
	private static SqlSessionFactory ssf;
	static {
		ssf=CreateSqlSessionFactory.getSsf();
	}
// 댓글형 게시판-------------------------------------------------
	/* 목록 출력 */
	public static List<BoardVO> baordListData(Map map)
	{
		// 연결
		SqlSession session=ssf.openSession();
		List<BoardVO> list=session.selectList("boardListData",map);
		// 반환
		session.close();
		return list;
	}
	
	/* 새글 작성 */
	public static void boardInsert(BoardVO vo)
	{
		// 연결
		SqlSession session=ssf.openSession(true);
		session.insert("boardInsert",vo);
		// 반환
		session.close();
	}
	/* 총 페이지 구하기 */
	public static int boardTotalPage()
	{
		SqlSession session=ssf.openSession();
		int total=session.selectOne("boardTotalPage");
		session.close();
		return total;
	}
	/* 상세보기 */
	public static BoardVO boardDetailData(int no)
	{
		SqlSession session=ssf.openSession();
		/* 조회수 증가 */
		session.update("boardhitIncrement",no);
		session.commit();
		/* 상세 데이터 읽기 */
		BoardVO vo= session.selectOne("boardDetailData",no);
		session.close();
		return vo;
	}
	/* 댓글달기 */
	public static void replyInsert(ReplyVO vo)
	{
		// 연결
		SqlSession session=ssf.openSession(true);
		session.insert("replyInsert",vo);
		// 반환
		session.close();
	}
	/* 댓글 목록 출력 */
	public static List<ReplyVO> replyListData(int bno)
	{
		SqlSession session=ssf.openSession();
		List<ReplyVO> list=session.selectList("replyListData",bno);
		session.close();
		return list;
	}
	
}
