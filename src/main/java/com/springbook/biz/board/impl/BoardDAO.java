package com.springbook.biz.board.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//DAO(data access object)

public class BoardDAO {

	//jdbc 하도 안 외워서 되물으니깐 막막하다.
	private conntion conn = null;
	private PreparedStatment stmt = null;
	private ResultSet rs = null;
	
	//sql
	private final String Board_insert = "insert into SQBOARD (SEQ, TITLE, WRITER, CONTENT) "
			+ "VALUES((SELECT NVL (MAX(SEQ), 0)+1 FROM SQBOARD), ?,?,?)";

}
