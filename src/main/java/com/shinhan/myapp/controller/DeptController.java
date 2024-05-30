package com.shinhan.myapp.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.firstzone.myapp.dept.DeptDTO;
import org.firstzone.myapp.dept.DeptService;
import org.firstzone.myapp.emp.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
@RequestMapping("/dept")
public class DeptController {

	@Autowired
	DeptService dService;
	
	@Autowired
	EmpService eService;
	
	
	@GetMapping("/deptList.do")
	public void retrieve(Model model, HttpServletRequest request) {
		
		
		
		String result = "";
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		if (flashMap != null) {
			result = (String)flashMap.get("deptResult");
			System.out.println(result);
		}
		
		model.addAttribute("deptlist", dService.selectAll());
		model.addAttribute("deptResult", result);

		// return이 void인 경우 다음과 같다 => return "dept/deptList";
		// forward된다. /WEB-INF/views/dept/deptList.jsp
	}
	
	// (insert) 부분 값을 전달하는 2가지 방식
	
//	@GetMapping("/deptInsert.do")
//	public void insert(Model model) {
//		
//		model.addAttribute("mlist", eService.selectAllManager());
//	}
	
	@GetMapping("/deptInsert.do")
	public ModelAndView insert(Model model) {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("mlist", eService.selectAllManager());
		mv.setViewName("dept/deptInsert");
		return mv;
	}
	
	// 값을 하나만 선택해서 가져오는 방법 deptinsert부분
	@PostMapping("/deptInsert.do")
	public String inserDB(DeptDTO dept,
			Integer deptid2,
			Integer department_id,
			RedirectAttributes redirectAttr
			) {
		
		System.out.println(dept);
		System.out.println(deptid2);
		System.out.println(department_id);
		
		int result =  dService.deptInsert(dept);
		
		String message;
		if (result > 0) {
			message = "insert success";
		}else {
			message = "insert fail";
		}
		
		redirectAttr.addFlashAttribute("deptResult",message);
		
		// "redirect:" 이 없다면 default로 forward이다 즉, 요청의 주소는 그대로 이고 보여지는 페이지는 jsp이다.
		// request.getrequestDispatcher("페이지 이름").forward(request, response)
		
		
		
		// "redirect:" 가 있다면 재요청을 의미한다. 새로운 요청이므로 주소가 바뀐다. 이때 request는 전달되지 않는다.
		// response.redirect("요청주소");
		
		
		return "redirect:deptList.do";
	}
	
	@GetMapping("deptUpdate.do")
	public void detail(
			Model model,
			HttpServletRequest request,
			HttpSession session,
			@RequestParam("deptid") Integer id) {
		
		System.out.println("getRemoteAddr : "+request.getRemoteAddr());
		session.setAttribute("deptid", id);
		session.setAttribute("myname", "김주현");
		
		model.addAttribute("dept", dService.selectByDeptid(id));
	}
	
	// 새로운페이지 열고 데이터 전달하는 방법 2 (파라미터에 모델 어트리뷰트 사용)
	@PostMapping("/deptDetail.do")
	public String deptInfoView(@ModelAttribute("dept") DeptDTO dept, Model model) {
		System.out.println(dept);
		
		//model.addAttribute("dept", dept); 	// 기존 방법
		
		
		model.addAttribute("mlist", eService.selectAllManager());
		
		return "dept/deptUpdate_DB";
	}
	
	@PostMapping("/deptDBUpdate.do")
	public String deptDBUpdate(DeptDTO dept, RedirectAttributes redirectAttr) {
		int result = dService.deptUpdate(dept);
		
		String message;
		if (result > 0) {
			message = "insert success";
		}else {
			message = "insert fail";
		}
		
		redirectAttr.addFlashAttribute("deptResult",message);
		dService.deptUpdate(dept);
		
		return "redirect:deptList.do";
	}
	
	@GetMapping("/deptDelete.do")
	public String deptDelete(
			@RequestParam("deptid")
			Integer deptid,
			HttpServletRequest request,
			RedirectAttributes redirectAttr
			) {
		
		String deptid2 = request.getParameter("deptid");
		System.out.println("spring에전달 : " + deptid);
		System.out.println("servlet api 이용 : "+deptid2);
		
		int result = dService.deptDelete(deptid);
		String message;
		if (result > 0) {
			message = "insert success";
		}else {
			message = "insert fail";
		}
		redirectAttr.addFlashAttribute("deptResult",message);
		
		return "redirect:deptList.do";
		
	}
	
}















