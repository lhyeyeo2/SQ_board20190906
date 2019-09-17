package com.springbook.biz.user;

public interface UserService {
	// CRUD 
	// 회원 등록
	public int addUser(UserVO vo);
	// 회원 상세 정보
	public UserVO getUser(UserVO vo);
}
