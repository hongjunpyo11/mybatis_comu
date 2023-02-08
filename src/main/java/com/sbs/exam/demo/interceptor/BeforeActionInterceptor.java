package com.sbs.exam.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.sbs.exam.demo.service.MemberService;
import com.sbs.exam.demo.vo.Rq;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BeforeActionInterceptor implements HandlerInterceptor {
	private final Rq rq;
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		// 이제는 Rq 객체가 자동으로 만들어지기 때문에 필요 없음
		
		rq.initOnBeforeActionInterceptor();

		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}

}
