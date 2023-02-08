package com.sbs.exam.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.sbs.exam.demo.vo.Rq;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NeedLoginInterceptor implements HandlerInterceptor {
	private final Rq rq;
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		
		if (!rq.isLogined()) {
			rq.printHistoryBackJs("로그인 후 이용해주세요.");
			return false;
		}
		
		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}

}
