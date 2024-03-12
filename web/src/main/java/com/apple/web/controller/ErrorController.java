package com.apple.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ErrorController {
	
	@GetMapping("/error")
	public String error(HttpServletRequest request, Model model) {
		Object code = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		System.out.println(code);
		System.out.println(code.toString());
		model.addAttribute("code", code.toString());
		return "error";
	}
}
