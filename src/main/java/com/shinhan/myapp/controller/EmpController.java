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
	
	// @Autowired�� Ÿ���� ������ �ڵ����� injection ���ش�.  �Ѱ��ۿ� �ȸ����� ���� �����������
	@Autowired
	EmpService eService;
	
	@Autowired
	DeptService dService;
	
	// deptSelect=0 �̸� ��� �μ��� �ǹ��ϴ� �ɷ� ����
	// jobSelect=all�� ��� ��å�� �ǹ�
	@RequestMapping("/empAll2.do")
	public String empAll2(Model model,
			HttpSession session,
			Integer deptSelect, String jobSelect,
			@RequestParam(value="hdate", required = false, defaultValue = "1900-01-01")
			Date hdate, Integer salary) {
		
		
		
		// ���ǿ� �� ����
		session.setAttribute("deptSelect", deptSelect);
		session.setAttribute("jobSelect", jobSelect);
		session.setAttribute("hdate", hdate);
		session.setAttribute("salary", salary);
		
		
		// condition�κп� ���ڰ� int�� �Ǿ� �־ null�� ���ޱ⶧���� null�� ��� 0���� �ٲٴ� ���ǹ��� �߰�
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
		
		
		// �μ��� ������ ���
		// ��å�� ������ ���
		// ���� ��ȸ�� ���
//		
//		if(deptid==null && jobid==null) {
//			emplist = eService.selectAll();
//		}else {
//			// �μ���ȸ�ϱ�
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
		// view �̸��� return �ȴ�.
		// viewResolver�� ���λ� + view�̸� + ���̻� �� �ٿ��� view�� forward�ȴ�.
		// �� forward�Ǵ� ���̴� �ּҴ� �ٲ��� ����
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
	
	// �Է�â �����ֱ�
	@GetMapping("/empInsert.do")
	public void insertDisplay(Model model) {
		model.addAttribute("deptlist",dService.selectAll());
		model.addAttribute("mlist",eService.selectAllManager());
		model.addAttribute("joblist",eService.selectAllJob());
	}
	
	// DB�� �Է��ϱ�
	@PostMapping("/empInsert.do")
	public String insertDB(EmpDTO emp) {
		System.out.println("insert Ȯ��(javabean): " + emp);
		eService.empInsert(emp);
		return "redirect:empAll.do";
	}
	
	// DB�����ϱ�
	@PostMapping("/empDetail.do")
	public String updateDB(EmpDTO emp) {
		System.out.println("update Ȯ��(javabean): " + emp);
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
		System.out.println("empid Ȯ��(javabean): " + empid);
		eService.empDelete(empid);
		return "redirect:empAll.do";
	}
}
