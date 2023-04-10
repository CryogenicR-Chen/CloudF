package com.cloud.controller;

import com.cloud.base.Session;
import com.cloud.entity.*;
import com.cloud.service.BlogService;
import com.cloud.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController{

/*    @Autowired
	private RedisService redisService;*/

	@Autowired
	BlogService blogService;

	@Autowired
	LoginService loginService;

	@RequestMapping(value="/index",method= RequestMethod.GET)
	public String index(@RequestParam(value = "nextIndex", defaultValue = "0") Integer nextIndex
			, @RequestParam(value = "offset" ,defaultValue = "5") Integer offset
			, Model model, HttpServletRequest request){
		indexPost(nextIndex,offset,model,request);
		return "index";

	}
	@RequestMapping(value="/index",method= RequestMethod.POST)
	public String indexPost(@RequestParam(value = "nextIndex", defaultValue = "0") Integer nextIndex
			, @RequestParam(value = "offset" ,defaultValue = "5") Integer offset
			, Model model, HttpServletRequest request){

		loginService.indexPost(nextIndex,offset,model,request);
		return "index";
	}

}