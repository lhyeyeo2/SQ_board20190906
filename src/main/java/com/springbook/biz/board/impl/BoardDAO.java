package com.springbook.biz.board.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.common.JDBCUtil;

//DAO(data access object)
@Repository("boardDAO")
public class BoardDAO {


	
	//JDBC  //(-)jdbc 하도 안 외워서 되물으니깐 막막하다.
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	//sql
	private final String BOARD_INSERT = "INSERT INTO SQBOARD (SEQ, TITLE, WRITER, CONTENT) "
			+ "VALUES((SELECT NVL (MAX(SEQ), 0)+1 FROM SQBOARD), ?,?,?)";
	private final String BOARD_UPDATE = "update SQBOARD SET TITLE = ?, CONTENT=? WHERE SEQ=? ";
	private final String BOARD_DELETE = "DELETE FROM SQBOARD WHERE SEQ=?";
	private final String BOARD_GET = "SELECT * FROM SQBOARD WHERE SEQ=?";
	private final String BOARD_LIST = "SELEVT * FROM SQBOARD ORDER BY SEQ DESC";
	
	//CRUD (CREATE / READ / UPDATE / DELETE ) 메소드 구현
	//글 등록 INSERT
	public void insertBoard(BoardVO vo) {
		System.out.println("-->JDBC insertBoard() 기능 처리");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_INSERT);
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getWriter());
			stmt.setString(3, vo.getContent());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace ();
		}finally {
			JDBCUtil.close(stmt, conn);			
		}	
		
	}
	
	//글 수정  UPDATE
	public void updateBoard(BoardVO vo) {
		System.out.println("-->JDBC updateBoard() 기능 처리");
		try {
			conn  = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_UPDATE);
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getContent());
			stmt.setInt(3, vo.getSeq());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}		
	}
	
	
	//글 삭제 DELETE
	public void deleteBoard(BoardVO vo) {
		System.out.println("-->JDBC deleteBoard() 기능 처리");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_DELETE);
			stmt.setInt(1, vo.getSeq());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);			
		}
		
	}
	
	//글 상세 조회
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("-->JDBC getBoard() 기능 처리");
		BoardVO board = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_GET);
			stmt.setInt(1, vo.getSeq());
			rs = stmt.executeQuery();
			if(rs.next()) {
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("Writer"));
				board.setContent(rs.getString("content"));
				board.setRegDate(rs.getDate("regdate"));
				board.setCnt(rs.getInt("cnt"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return board;
	}
	// 글 목록 조회 select
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("-->JDBC getBoardList() 기능 처리");
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_LIST);
//			stmt.setInt(1, vo.getSeq());
			rs = stmt.executeQuery();
			while(rs.next()) {
				BoardVO board = new BoardVO();
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("Writer"));
				board.setContent(rs.getString("content"));
				board.setRegDate(rs.getDate("regdate"));
				board.setCnt(rs.getInt("cnt"));
				boardList.add(board);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return boardList;		
	}

}
