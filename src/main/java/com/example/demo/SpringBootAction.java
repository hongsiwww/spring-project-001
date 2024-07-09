package com.example.demo;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class SpringBootAction {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	@GetMapping("/index")
	public String helloIndex(){
		return "index";
	}
	
	@GetMapping("/Store")
	public String helloStore(){
		return "Store";
	}
	
	@GetMapping("/SignUp")
	public String helloSignUp(){
		return "SignUp";
	}
	
	@ResponseBody
	@RequestMapping(value="/SignIn.action", method=RequestMethod.POST)
	public void SignInAction(HttpServletResponse response,@RequestBody Map<String,String> map ) throws IOException {
		
		String queryId = "";
		queryId = SpringBootService.AccountQueryService(map);
		
		if("".equals(queryId)) {
			response.getWriter().write("帳號不存在");
		}else if ("密碼錯誤".equals(queryId)){
			response.getWriter().write("密碼錯誤");
		}else{
			response.getWriter().write("帳號:" + queryId + " 登入成功");
		}
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	@ResponseBody
	@RequestMapping(value="/SignUp.action", method=RequestMethod.POST)
	public void SignUpAction(HttpServletResponse response,@RequestBody Map<String,String> map ) throws IOException {
		
		int flag = SpringBootService.AccountInsertService(map);
		
		if(0 == flag) {
			response.getWriter().write("註冊成功");
		}else if(2 == flag) {
			response.getWriter().write("帳號已存在");
		}else{
			response.getWriter().write("註冊失敗");
		}
		response.getWriter().flush();
		response.getWriter().close();
	}

}
