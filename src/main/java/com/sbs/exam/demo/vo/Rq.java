package com.sbs.exam.demo.vo;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sbs.exam.demo.util.Ut;

import lombok.Getter;

public class Rq { // Request 를 조금 쉽게 쓰려고 커스텀
	@Getter
	private boolean logined;
	@Getter
	private int loginedMemberId;
	
	private HttpServletRequest req;
	private HttpServletResponse resp;
	private HttpSession session;
	
	public Rq(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
		
		this.session = req.getSession();
		boolean isLogined = false;
		int loginedMemberId = 0;
		
		if (session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}
		
		this.logined = isLogined;
		this.loginedMemberId = loginedMemberId;
	}
	
	public void printHistoryBackJs(String msg) {
		resp.setContentType("text/html; charset=UTF-8");

		println("<script>");

		if (!Ut.empty(msg)) {
			println("alert('" + msg + "');");
		}

		println("history.back();");

		println("</script>");
	}
	
	public void print(String str) {
		try {
			resp.getWriter().append(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void println(String str) {
		print(str + "\n");
	}
	
	public void login(Member member) {
		session.setAttribute("loginedMemberId", member.getId());
	}
	
	public void logout() {
		session.removeAttribute("loginedMemberId");
	}
}
