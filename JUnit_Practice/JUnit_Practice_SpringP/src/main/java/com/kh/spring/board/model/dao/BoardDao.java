package com.kh.spring.board.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.PageInfo;
import com.kh.spring.board.model.vo.Reply;

@Repository
public class BoardDao {

	public int selectListCount(SqlSession sqlSession) {
		return sqlSession.selectOne("boardMapper.selectListCount");
	}

	public ArrayList<Board> selectList(SqlSession sqlSession, PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		
		return (ArrayList) sqlSession.selectList("boardMapper.selectList", null, rowBounds);
	}

	public int insertBoard(SqlSession sqlSession, Board b) {
		return sqlSession.insert("boardMapper.insertBoard", b);
	}

	public int increaseCount(SqlSession sqlSession, int bno) {
		return sqlSession.update("boardMapper.increaseCount", bno);
	}

	public Board selectBoard(SqlSession sqlSession, int bno) {
		return sqlSession.selectOne("boardMapper.selectBoard", bno);
	}

	public int deleteBoard(SqlSession sqlSession, int bno) {
		return sqlSession.update("boardMapper.deleteBoard", bno);
	}

	public int updateBoard(SqlSession sqlSession, Board b) {
		return sqlSession.update("boardMapper.updateBoard", b);
	}

	public ArrayList<Reply> selectReplyList(SqlSession sqlSession, int bno) {
		return (ArrayList) sqlSession.selectList("boardMapper.selectReplyList", bno);
	}

	public int insertReply(SqlSession sqlSession, Reply r) {
		return sqlSession.insert("boardMapper.insertReply", r);
	}

	public ArrayList<Board> selectTopList(SqlSession sqlSession) {
		return (ArrayList) sqlSession.selectList("boardMapper.selectTopList");
	}

}
