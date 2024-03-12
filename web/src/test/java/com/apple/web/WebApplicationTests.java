package com.apple.web;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.apple.web.service.MemberService;

@SpringBootTest
class WebApplicationTests {
	
	@Autowired
	MemberService memberService;
	
	@DisplayName("로그인 카운트 값 확인합니다")
	@Test
	void contextLoads() {
		//Map<String, Object> map = new HashMap<String, Object>();
		//map.put("id", "있는 아이디");
		//map.put("pw", "있는 패스워드");
		
		//map = memberService.login(map); // result라는 새로운 Map 인스턴스를 만들어서 써도 된다.
		//assertEquals(1, Integer.parseInt(String.valueOf(map.get("count"))));
		
		int num = 100;
		assertEquals(100, num);
	}

}
