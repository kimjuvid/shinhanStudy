package org.firstzone.myapp.emp;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Controller->Service->DAO
//          <-       <-
//Service: �������̼����� ���� ���񽺶�� ���� ���ؾ���
@Service
public class EmpService {
	
	@Autowired
	EmpDAOMybatis empDAO;

	//로그?��?���? 
		public EmpDTO loginChk(String email,String phone) {
			return empDAO.loginCheck( email, phone);
		}
	

	// 1.모든job 조회
		public List<JobDTO> selectAllJob() {
		return empDAO.selectAllJob();
	}

	// 1.매니??모두 조회
	public List<HashMap<String, Object>> selectAllManager() {
		return empDAO.selectAllManager();
	}

	// ?��메일중복체크
	public int selectByEmail(String email) {
		return empDAO.selectByEmail(email);
	}

	// 8. ?��?��(Delete)
	public int empDelete(int empid) {
		return empDAO.empDelete(empid);
	}

	// 7. ?��?��(Update)
	public int empUpdate(EmpDTO emp) {
		return empDAO.empUpdate(emp);
	}

	// 6. ?��?��(Insert)
	public int empInsert(EmpDTO emp) {
		return empDAO.empInsert(emp);
	}

	// 5.?��?��?�� 조건?���? 조회?���?
	// �??���?(=), 직책�?(=), ?��?��?���?(>=), 급여(>=)
	public List<EmpDTO> selectByCondition(int deptid, String jobid, Date hdate, int salary) {
		return empDAO.selectByCondition(deptid, jobid, hdate, salary);
	}

	// 4. ?��?��job?�� 직원조회
	public List<EmpDTO> selectByJobId(String jobId) {

		return empDAO.selectByJobId(jobId);
	}

	// 3. ?��?���??��?�� 직원조회
	public List<EmpDTO> selectByDepartmentId(int deptId) {

		return empDAO.selectByDepartmentId(deptId);
	}

	// 2.?��?��직원?�� ?��?��보기
	public EmpDTO selectById(int empId) {
		return empDAO.selectById(empId);
	}

	// 1. 직원모두 조회
	public List<EmpDTO> selectAll() {

		return empDAO.selectAll();
	}
//	//직원번호�? ?��?��?��?�� 직원?�� ?��름과 직책�? 급여�? 조회?��?��.
//	public Map<String, Object> empInfo(int empid) {
//		// TODO Auto-generated method stub
//		return empDAO.empInfo(empid);
//	}

//	// 직원번호�? ?��?��?���? 직원 보너?���? return?��?�� ?��?���? ?��출한?��.
//	public double callFunction(int empid) {
//		return empDAO.callFunction(empid);
//	}
}
