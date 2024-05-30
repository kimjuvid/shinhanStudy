package com.shinhan.myapp.controller;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.firstzone.myapp.dept.DeptService;
import org.firstzone.myapp.emp.EmpDTO;
import org.firstzone.myapp.emp.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shinhan.myapp.util.DateUtil;

@Controller
@RequestMapping("/emp")
public class EmpController {
	
	// @Autowired는 타입이 같으면 자동으로 injection 해준다.  한개밖에 안먹으니 각각 정의해줘야함
	@Autowired
	EmpService eService;
	
	@Autowired
	DeptService dService;
	
	// deptSelect=0 이면 모든 부서를 의미하는 걸로 했음
	// jobSelect=all은 모든 직책을 의미
	@RequestMapping("/empAll2.do")
	public String empAll2(Model model,
			HttpSession session,
			Integer deptSelect, String jobSelect,
			@RequestParam(value="hdate", required = false, defaultValue = "1900-01-01")
			Date hdate, Integer salary) {
		
		
		
		// 세션에 값 저장
		session.setAttribute("deptSelect", deptSelect);
		session.setAttribute("jobSelect", jobSelect);
		session.setAttribute("hdate", hdate);
		session.setAttribute("salary", salary);
		
		
		// condition부분에 인자가 int로 되어 있어서 null을 못받기때문에 null일 경우 0으로 바꾸는 조건문을 추가
		if(salary == null) salary=0;
//		Date startDate = DateUtil.getSQLDate(hdate);
		List<EmpDTO> emplist = eService.selectByCondition(deptSelect, jobSelect, hdate, salary);
		

		model.addAttribute("emplist",emplist);
		model.addAttribute("deptlist",dService.selectAll());
		model.addAttribute("joblist",eService.selectAllJob());
		
		return "emp/emplist";
	}
	
	
	@RequestMapping("/empAll.do")
	public String empAll(Model model,
			Integer deptid, String jobid) {
		
		List<EmpDTO> emplist = null;
		
		if(deptid == null) deptid = 0;
		emplist = eService.selectByCondition(deptid, jobid, null, 0);
		
		
		// 부서가 선택인 경우
		// 직책이 선택인 경우
		// 전부 조회인 경우
//		
//		if(deptid==null && jobid==null) {
//			emplist = eService.selectAll();
//		}else {
//			// 부서조회하기
//			if (deptid!=null && jobid==null) {
//				if(deptid==0) {
//					emplist = eService.selectAll();
//				}else {
//					emplist = eService.selectByDepartmentId(deptid);
//				}
//			}else {
//				if(jobid.equals("all")) {
//					emplist = eService.selectAll();
//				}else {
//					emplist = eService.selectByJobId(jobid);
//				}
//			}
//			
//		}
		
		
		model.addAttribute("emplist",emplist);
		model.addAttribute("deptlist",dService.selectAll());
		model.addAttribute("joblist",eService.selectAllJob());
		// view 이름이 return 된다.
		// viewResolver가 접두사 + view이름 + 접미사 를 붙여서 view로 forward된다.
		// 즉 forward되는 것이니 주소는 바뀌지 않음
		return "emp/emplist";
	}
	
	@GetMapping("/empDetail.do")
	public void detail(Model model,
			@RequestParam("empid") Integer empid2) {
		model.addAttribute("empInfo",eService.selectById(empid2));
		model.addAttribute("deptlist",dService.selectAll());
		model.addAttribute("mlist",eService.selectAllManager());
		model.addAttribute("joblist",eService.selectAllJob());
		eService.selectById(empid2);
	}
	
	// 입력창 보여주기
	@GetMapping("/empInsert.do")
	public void insertDisplay(Model model) {
		model.addAttribute("deptlist",dService.selectAll());
		model.addAttribute("mlist",eService.selectAllManager());
		model.addAttribute("joblist",eService.selectAllJob());
	}
	
	// DB에 입력하기
	@PostMapping("/empInsert.do")
	public String insertDB(EmpDTO emp) {
		System.out.println("insert 확인(javabean): " + emp);
		eService.empInsert(emp);
		return "redirect:empAll.do";
	}
	
	// DB수정하기
	@PostMapping("/empDetail.do")
	public String updateDB(EmpDTO emp) {
		System.out.println("update 확인(javabean): " + emp);
		eService.empUpdate(emp);
		return "redirect:empAll.do";
	}
	
	
	
	@GetMapping("/empIdCheck.do")
	@ResponseBody
	public String test(Integer empid) {
		EmpDTO emp = eService.selectById(empid);
		if (emp==null) return "0";
		return "1";
	}
	
	
	@GetMapping("/empDelete.do")
	public String deleteDB(Integer empid) {
		System.out.println("empid 확인(javabean): " + empid);
		eService.empDelete(empid);
		return "redirect:empAll.do";
	}
}
