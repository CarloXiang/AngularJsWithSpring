package com.comolroy.saajs;

import org.springframework.web.bind.annotation.RequestMapping;

public class TestController {
	@RequestMapping("/test")
	public String test() {
		System.out.println("Hello from junit");
		return "index";
	}
}