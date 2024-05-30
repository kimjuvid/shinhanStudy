package com.shinhan.myapp.controller;

import java.util.List;

import org.firstzone.myapp.dept.DeptDTO;
import org.firstzone.myapp.dept.DeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // @Controller + @ResponseBody 의 기능을 합친 것과 같음
@RequestMapping("/dept/api/*")
public class DeptRestController {
	
	@Autowired
	DeptService dService;
	
	Logger logger = LoggerFactory.getLogger(DeptRestController.class);
	
	// 삭제
	@DeleteMapping(value="/delete/{deptid}")
	public String delete(@PathVariable("deptid") Integer deptid) {
		int result = dService.deptDelete(deptid);
		return result>0?"삭제성공":"삭제실패";
	}
	
	
	// 수정
	@PutMapping(value = "/update",
			consumes = "application/json",
			produces = "text/plain;charset=utf-8")
	public String update(@RequestBody DeptDTO dept) {
		System.out.println("ㅇ베ㅔ베");
		int result = dService.deptUpdate(dept);
		return "수정 건수" + result + "건";
	}
	
	
	// post는 요청문서의 body에 데이터가 들어온다, 그니까 파라미터부분에 @RequestBody를 꼭 써줘야함
	@PostMapping(value = "/insert",
			 consumes = "application/json",
			 produces = "text/plain;charset=utf-8")
	public String insert(@RequestBody DeptDTO dept) {
		logger.info("들어온 dept :" + dept);
		int result = dService.deptInsert(dept);
		
		return "insert 결과" + result + "건"; 
	}
	
	// JSON으로 내보내기
	// jackson-databind를 이용해서 자바의 객체가 json으로 convert된다.
	
	// 아래 두가지 방식은 동일하게 작동함 (produces 부분)
	// MediaType.APPLICATION_JSON_VALUE
	// "application/json"
	@GetMapping(value = "/deptAll", produces = "application/json")
	public List<DeptDTO> selectAll() {
		List<DeptDTO> deptlist = dService.selectAll();
		return deptlist;
	}
	
	// JSON으로 내보내기
	// jackson-databind를 이용해서 자바의 객체가 json으로 convert된다.
	// @PathVariable path에 값이 들어있다 그거를 읽겠다는 의미
	@GetMapping(value = "/detail/{deptid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public DeptDTO selectById(@PathVariable("deptid") Integer did) {
		DeptDTO dept = dService.selectByDeptid(did);
		return dept;
	}
	
	// 내보내기 : produces 설정
	// 가져오기 : consumes 설정
	@GetMapping(value = "/test1", produces = "text/plain;charset=utf-8")
	public String test() {
		
		return "Restful방식 연습";
	}

}
