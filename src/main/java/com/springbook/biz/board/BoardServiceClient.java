package com.springbook.biz.board;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

//MOVE했더니 에러발생으로 처음자리 갔다가 다시 돌아옴.
//
//src/test/java/.com...
public class BoardServiceClient {
	public static void main(String[] args) {
		//1. Spring 컨테이너를 구동한다.
		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");

		//2. Spring 컨테이너로부터 BoardServiceImpl 객체를 Lookup 한다.
		BoardService boardService = (BoardService) container.getBean("boardService");
		
		//3.  글 등록 기능 테스트
		BoardVO vo = new BoardVO();
		vo.setTitle("aaaTitle");
		vo.setWriter("aaaWriter");
		vo.setContent("aaaContent");
		boardService.insertBoard(vo);
		
		// 4. 글 목록 검색 기능 테스트
		List<BoardVO> boardList = boardService.getBoardList(vo);
		for (BoardVO board : boardList) {
			System.out.println("-->" + board.toString());
		}
		
		// 5. Spring 컨테이너 종료
		container.close();
	}
}
