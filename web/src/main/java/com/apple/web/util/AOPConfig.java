package com.apple.web.util;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AOPConfig {
	
	@Pointcut("execution(* com.apple.web.controller.*.*(..))")
	public void cut() {
		
	}
	
	@Before("cut()")
	public void before(JoinPoint joinPoint) {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		// 실행되는 함수 이름을 가져오고 출력
		System.out.println("시작할 때 : " + methodSignature.getName()); //실행 메소드명
		System.out.println("시작할 때 : " + methodSignature.getMethod()); //
		
		// 파라미터
		Object[] args = joinPoint.getArgs();
		System.out.println(Arrays.toString(args));
		
		// 파라미터 배열의 종류 값
		for (Object object : args) {
			System.out.println("파라미터 타입 : " + object.getClass().getSimpleName());
			System.out.println("파라미터 값 : " + object);
		}
	}
	
	@After("cut()")
	public void after(JoinPoint joinPoint) {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		// 실행되는 함수 이름을 가져오고 출력
		System.out.println(methodSignature.getName() + " 메소드가 종료되었습니다.");
	}
}
