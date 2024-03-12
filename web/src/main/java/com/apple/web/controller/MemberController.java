package com.apple.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.apple.web.service.IndexService;
import com.apple.web.service.MemberService;
import com.apple.web.util.Util;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	
	@Autowired
	private IndexService indexService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private Util util;
	
	@GetMapping("/login")
	public String login(Model model) {
		List<Map<String, Object>> menu = indexService.menu();
		model.addAttribute("menu", menu);
		return "login";
	}
	
	@PostMapping("/login")
	public String login2(@RequestParam Map<String, Object> map) {
		System.out.println(map);
		
		Map<String, Object> result = memberService.login(map);
		System.out.println(result);
		
		if (util.str2Int(result.get("count")) == 0) {
			//로그인 불가 -> 화면 이동, 다시 로그인으로
			return "redirect:/login";
		} else {
			//정상 - 세션 -> board로 이동
			HttpSession session = util.getSession();
			session.setAttribute("mid", map.get("id"));
			session.setAttribute("mname", result.get("mname"));
			session.setAttribute("mgrade", result.get("mgrade"));
//			System.out.println(session.getAttribute("mid"));
//			System.out.println(session.getAttribute("mname"));
//			System.out.println(session.getAttribute("mgrade"));
			return "redirect:/board";
		}
	}
	/*
	 * log in log out
	 * log on log off
	 * sign in sign out
	 */
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		//HttpSession session = util.getSession();
		if(session.getAttribute("mid") != null) {
			session.removeAttribute("mid");
		}
		if(session.getAttribute("mname") != null) {
			session.removeAttribute("mname");
		}
		session.invalidate();
		return "redirect:/login";
	}
	
	@GetMapping("/myInfo@{id}")
	public String myInfo(@PathVariable("id") String id, Model model) {
		Map<String, Object> map = memberService.myInfo(id);
		model.addAttribute("memInfo", map);
		List<Map<String, Object>> menu = indexService.menu();
		model.addAttribute("menu", menu);
		return "myInfo";
	}
	
	
}
