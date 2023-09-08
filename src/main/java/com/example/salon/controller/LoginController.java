package com.example.salon.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.salon.service.userdetails.UserDetailsImpl;


@Controller
public class LoginController {
	// SecurityConfig の loginPage で指定した URL
	@GetMapping("/login")
	public String loginForm() {
	// ログイン画面を表示
	return "login";
	}
	// SecurityConfig の failureUrl で指定した URL と?のうしろのパラメータ
	@GetMapping(value="/login", params="failure")
	public String loginFail(Model model) {
	model.addAttribute("failureMessage", "ログインに失敗しました");
	// ログイン画面を表示
	return "login";
	}
	// SecurityConfig の defaultSuccessUrl で指定した URL
	@GetMapping("loginsuccess")
	public String loginSuccess(Model model) {
	// ログインに成功したら表示する URL
		// ユーザー名
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetailsImpl principal = (UserDetailsImpl) auth.getPrincipal();
		model.addAttribute("username", principal.getUsername());
	return "success";
	}
	
	@GetMapping("/owner")
	public String owner() {
	return "owner";
	}
	@PostMapping("/owner")
	public void ownerPost() {
	owner();
	}
	@GetMapping("/stylist")
	public String stylist() {
	return "stylist";
	}
	@PostMapping("/stylist")
	public void stylistPost() {
	stylist();
	}
	@GetMapping("/assistant")
	public String assistant() {
	return "assistant";
	}
	@PostMapping("/assistant")
	public void assistantPost() {
	assistant();
	}

}
