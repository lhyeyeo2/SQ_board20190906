package com.springbook.biz.user;


import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;



public class UserServiceClient {

	public static void main(String[] args) {
		// 1. Spring 컨테이너를 구동한다.
		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationcontext.xml");
		
		// 2. Spring 컨테이너로부터 UserServiceImpl 객체를 Lookup 한다.
		UserService userService = (UserService)container.getBean("userService");
	
		
		// 3.  회원 등록 기능 테스트
		UserVO vo = new UserVO();
		vo.setId("aaaid");
		vo.setPassword("aaawd");
		vo.setName("bbbname");
		vo.setRole("brole");
		if(userService.addUser(vo)==1)
			System.out.println("회원등록 완료");
		else
			System.out.println("등록 실패");
		
		
		
		
		// 3.  로그인 기능 테스트
		//UserVO vo = new UserVO();
		vo.setId("aaaid");
		vo.setPassword("aaapwd");
		UserVO user = userService.getUser(vo);
		if(user != null) {
			System.out.println("[msg]" + user.getName() + "님 환영합니다.");
			System.out.println(user.toString());
		} else {
			System.out.println("[hign] 로그인 실패");
		}
		
		// 4. Spring 컨테이너 종료
		container.close();
			
	}
}
