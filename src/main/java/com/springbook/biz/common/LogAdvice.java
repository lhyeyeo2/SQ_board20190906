package com.springbook.biz.common;

public class LogAdvice {
	public void printLog() {
		System.out.println("[common log] 비즈니스 로직 수행 전 동작");
	}
	public void printBeforeLog() {
		System.out.println("[common log] 비즈니스 로직 수행 전 동작");
	}
	public void printAfterLog() {
		System.out.println("[common log] 비즈니스 로직 수행 ㅠㅜㅡ 동작");
	}
	public void printThrowLog() {
		System.out.println("[common log] 에러 발생동작");
	}
	
}
