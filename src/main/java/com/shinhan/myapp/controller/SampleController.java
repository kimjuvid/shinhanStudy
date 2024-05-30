package com.shinhan.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

// ##################요청 주소 연습#####################
@Controller
// 공통적인 path는 class level에 정의, 각가은 method level에 작성한다.
// jsp 서블릿할때는 /가 필수 였는데 스프링은 ㄱㅊ 	
@RequestMapping("/sample")
public class SampleController {
	
	@RequestMapping("hello.do")
	public String test1() {
		return "jsp/hello";	// 접두사, 접미사 자동으로 붙을 거임 
	}

	@RequestMapping("/hello1.do")
	public String test2() {
		return "jsp/hello";	
	}
	
	@RequestMapping(value = {"/hello2.do","/hello3.do" },
					method = RequestMethod.GET)
	public String test3() {
		return "jsp/hello";	
	}
	
	@GetMapping("/hello4.do")
	public String test4(Model data) {
		data.addAttribute("myname", "신한DS");
		data.addAttribute("myscore", 100);
		return "jsp/hello";
	}
	
	@GetMapping("/hello5.do")
	public ModelAndView test5() {
		ModelAndView data = new ModelAndView("jsp/hello");
		//data.setViewName("jsp/hello"); // 위의 생성자 부분에 주소 기입안하면 이 코드로 대체가능
		data.addObject("myname", "신한DS....");
		data.addObject("myscore", 90);
		return data;
	}
	
	// email이 존재해야되고 비밀번호가 정해진대로 들어와야되고 폰이 존재하지않야된다는 조건 설정 
	@RequestMapping(value="/hello6.do", 
			method = RequestMethod.GET,
			params = {"email", "pwd=1234", "!address"})	
	public String test6(Model model,
			@RequestParam("email") String email,
			String pwd,
			String phone) {
		
		System.out.println("============/hello6.do요청============");
		
		// @requestParam은 생략가능 ==> request.getparameter("email")
		System.out.println(email);
		System.out.println(pwd);
		System.out.println(phone);
		model.addAttribute("lunch","오늘 메뉴는 삼겹살");
		return "jsp/hello6";
	}
	
	//@RequestMapping(value = "/hello6.do", method = RequestMethod.POST)
	@PostMapping("/hello6.do")
	public void test7(
		@RequestParam("email")	String email, 
		@RequestParam(value="pwd", required = true)	String pwd,	// 값이 반드시 들어와야해!! required=true / default는 false 
			String phone) {
		// default => 접두사 + "/sample/hello6" + 접미사
		System.out.println(email);
		System.out.println(pwd);
		System.out.println(phone);
	}
}






