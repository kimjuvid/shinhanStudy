package com.shinhan.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

// ##################��û �ּ� ����#####################
@Controller
// �������� path�� class level�� ����, ������ method level�� �ۼ��Ѵ�.
// jsp �����Ҷ��� /�� �ʼ� ���µ� �������� ���� 	
@RequestMapping("/sample")
public class SampleController {
	
	@RequestMapping("hello.do")
	public String test1() {
		return "jsp/hello";	// ���λ�, ���̻� �ڵ����� ���� ���� 
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
		data.addAttribute("myname", "����DS");
		data.addAttribute("myscore", 100);
		return "jsp/hello";
	}
	
	@GetMapping("/hello5.do")
	public ModelAndView test5() {
		ModelAndView data = new ModelAndView("jsp/hello");
		//data.setViewName("jsp/hello"); // ���� ������ �κп� �ּ� ���Ծ��ϸ� �� �ڵ�� ��ü����
		data.addObject("myname", "����DS....");
		data.addObject("myscore", 90);
		return data;
	}
	
	// email�� �����ؾߵǰ� ��й�ȣ�� ��������� ���;ߵǰ� ���� ���������ʾߵȴٴ� ���� ���� 
	@RequestMapping(value="/hello6.do", 
			method = RequestMethod.GET,
			params = {"email", "pwd=1234", "!address"})	
	public String test6(Model model,
			@RequestParam("email") String email,
			String pwd,
			String phone) {
		
		System.out.println("============/hello6.do��û============");
		
		// @requestParam�� �������� ==> request.getparameter("email")
		System.out.println(email);
		System.out.println(pwd);
		System.out.println(phone);
		model.addAttribute("lunch","���� �޴��� ����");
		return "jsp/hello6";
	}
	
	//@RequestMapping(value = "/hello6.do", method = RequestMethod.POST)
	@PostMapping("/hello6.do")
	public void test7(
		@RequestParam("email")	String email, 
		@RequestParam(value="pwd", required = true)	String pwd,	// ���� �ݵ�� ���;���!! required=true / default�� false 
			String phone) {
		// default => ���λ� + "/sample/hello6" + ���̻�
		System.out.println(email);
		System.out.println(pwd);
		System.out.println(phone);
	}
}






