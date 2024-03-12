package com.apple.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.apple.web.service.IndexService;
import com.apple.web.util.Util;

import jakarta.servlet.http.HttpSession;

@Controller
public class IndexController {
	
	@Autowired
	private IndexService indexService;
	@Autowired
	private Util util;
	
	@GetMapping({"/index", "/"})
	public String index(Model model) {
		model.addAttribute("test", "테스트 입니다.");
		List<Map<String, Object>> list = indexService.boardList();
		model.addAttribute("list", list);
		List<Map<String, Object>> menu = indexService.menu();
		model.addAttribute("menu", menu);
		return "index";
	}
	
	@GetMapping("/board")
	public String freeboard(@RequestParam(value="cate", defaultValue="1") int cate, Model model) {
		List<Map<String, Object>> board = indexService.freeboard(cate);
		List<Map<String, Object>> menu = indexService.menu();
		model.addAttribute("menu", menu);
		model.addAttribute("board", board);
		model.addAttribute("cate", cate);
		model.addAttribute("catename", indexService.getCateName(cate));
		if(cate == 2) {
			return "notice";
		} else {
			return "board";
		}
	}
	
	@GetMapping("/notice")
	public String noticeboard(@RequestParam(value="cate", defaultValue="2") int cate, Model model) {
		List<Map<String, Object>> board = indexService.freeboard(cate);
		model.addAttribute("board", board);
		List<Map<String, Object>> menu = indexService.menu();
		model.addAttribute("menu", menu);
		return "notice";
	}
	
	//20240307 안드로이드 앱 프로그래밍
	// 상세보기 -> no 잡기 -> 확인
	// detail.html
	// 값 -> DB에 물어보기 어떤 형태로? BoardDTO
	@GetMapping("/detail")
	public String detail(@RequestParam("no") int no, Model model) {
		Map<String, Object> detail = indexService.boardDetail(no);
		//BoardDTO detail = indexService.detail(no);
		model.addAttribute("detail", detail);
		List<Map<String, Object>> menu = indexService.menu();
		model.addAttribute("menu", menu);
		return "detail";
	}
	
	@GetMapping("/write")
	public String write(Model model) {
		List<Map<String, Object>> menu = indexService.menu();
		model.addAttribute("menu", menu);
		HttpSession session = util.getSession();
		if(session.getAttribute("mid") == null) {
			return "redirect:/login";
		} else {
			return "write";
		}
	}
	
	// 글 쓰고 자기가 쓴 글로 이동시키려면 mybatis selectKey
	@PostMapping("/write")
	public String write(@RequestParam Map<String, Object> map) {
		System.out.println(map);
		HttpSession session = util.getSession();
		if(session.getAttribute("mid") == null) {
			return "redirect:/login";
		} else {
			int result = indexService.write(map);
			System.out.println("글쓴 결과: " + result);
			//System.out.println(map);
			return "redirect:/detail?no="+map.get("mtno");
		}
	}
	
	@PostMapping("/postDel")
	public String postDel(@RequestParam("no") int no) {
		//System.out.println(no);
		int result = indexService.postDel(no);
		System.out.println("글삭제: " + result);
		return "redirect:/board";
	}
	
	@GetMapping("/postUpdate")
	public String postUpdate(@RequestParam("no") int no, Model model) {
		if(util.getSession().getAttribute("mid") == null) {
			return "redirect:/login";
		} else {
			List<Map<String, Object>> menu = indexService.menu();
			model.addAttribute("menu", menu);
			model.addAttribute("update", indexService.detail(no));
			return "update";
		}
	}
	
	@PostMapping("/postUpdate")
	public String postUpdate(@RequestParam() Map<String, Object> map) {
		if(util.getSession().getAttribute("mid") == null) {
			return "redirect:/login";
		} else {
			int result = indexService.postUpdate(map);
			System.out.println("글 수정 결과: " + result);
			return "redirect:/detail?no=" + map.get("mtno");
		}
	}
	
}
