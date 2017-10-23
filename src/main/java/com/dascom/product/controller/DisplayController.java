package com.dascom.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("display")
@Controller
public class DisplayController {
	
	@RequestMapping("test")
	public String test(){
		return "Index/index";
	}
	
	
	
	
	
}
