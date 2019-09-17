package com.springbook.biz.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.springbook.biz.common.JDBCUtil;
import com.springbook.biz.user.UserVO;

// DAO
@Repository("userDAO")
public class UserDAO {
	// JDBC
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	// SQL
	private final String USER_GET = "select * from SQUSERS where id=? and password=?";
	private final String USER_ADD = "insert into SQUSERS (id,password,name,role)"
			+ " values(?,?,?,?)";

	public int addUser(UserVO vo) {
		System.out.println("-->JDBC addUser() 기능 처리");
		int result=0;
		try {
			conn=JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_ADD);
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getPassword());
			stmt.setString(3, vo.getName());
			stmt.setString(4, vo.getRole());
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		return result;
	}
	
	// 회원 상세 조회
	public UserVO getUser(UserVO vo) {
		System.out.println("-->JDBC getUser() 기능 처리");
		UserVO user = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_GET);
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getPassword());
			rs = stmt.executeQuery();
			if (rs.next()) {
				user = new UserVO();
				user.setId(rs.getString("id"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setRole(rs.getString("role"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return user;
	}
}
