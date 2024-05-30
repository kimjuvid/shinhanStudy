package org.firstzone.myapp.emp;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Controller->Service->DAO
//          <-       <-
//Service: ¿¡³ëÅ×ÀÌ¼ÇÀ¸·Î ³»°¡ ¼­ºñ½º¶ó´Â °ÍÀ» ¸»ÇØ¾ßÇÔ
@Service
public class EmpService {
	
	@Autowired
	EmpDAOMybatis empDAO;

	//ë¡œê·¸?¸?•˜ê¸? 
		public EmpDTO loginChk(String email,String phone) {
			return empDAO.loginCheck( email, phone);
		}
	

	// 1.ëª¨ë“ job ì¡°íšŒ
		public List<JobDTO> selectAllJob() {
		return empDAO.selectAllJob();
	}

	// 1.ë§¤ë‹ˆ??ëª¨ë‘ ì¡°íšŒ
	public List<HashMap<String, Object>> selectAllManager() {
		return empDAO.selectAllManager();
	}

	// ?´ë©”ì¼ì¤‘ë³µì²´í¬
	public int selectByEmail(String email) {
		return empDAO.selectByEmail(email);
	}

	// 8. ?‚­? œ(Delete)
	public int empDelete(int empid) {
		return empDAO.empDelete(empid);
	}

	// 7. ?ˆ˜? •(Update)
	public int empUpdate(EmpDTO emp) {
		return empDAO.empUpdate(emp);
	}

	// 6. ?…? ¥(Insert)
	public int empInsert(EmpDTO emp) {
		return empDAO.empInsert(emp);
	}

	// 5.?‹¤?–‘?•œ ì¡°ê±´?œ¼ë¡? ì¡°íšŒ?•˜ê¸?
	// ë¶??„œë³?(=), ì§ì±…ë³?(=), ?…?‚¬?¼ë³?(>=), ê¸‰ì—¬(>=)
	public List<EmpDTO> selectByCondition(int deptid, String jobid, Date hdate, int salary) {
		return empDAO.selectByCondition(deptid, jobid, hdate, salary);
	}

	// 4. ?Š¹? •job?¸ ì§ì›ì¡°íšŒ
	public List<EmpDTO> selectByJobId(String jobId) {

		return empDAO.selectByJobId(jobId);
	}

	// 3. ?Š¹? •ë¶??„œ?˜ ì§ì›ì¡°íšŒ
	public List<EmpDTO> selectByDepartmentId(int deptId) {

		return empDAO.selectByDepartmentId(deptId);
	}

	// 2.?Š¹? •ì§ì›?˜ ?ƒ?„¸ë³´ê¸°
	public EmpDTO selectById(int empId) {
		return empDAO.selectById(empId);
	}

	// 1. ì§ì›ëª¨ë‘ ì¡°íšŒ
	public List<EmpDTO> selectAll() {

		return empDAO.selectAll();
	}
//	//ì§ì›ë²ˆí˜¸ë¥? ?´?š©?•´?„œ ì§ì›?˜ ?´ë¦„ê³¼ ì§ì±…ê³? ê¸‰ì—¬ë¥? ì¡°íšŒ?•œ?‹¤.
//	public Map<String, Object> empInfo(int empid) {
//		// TODO Auto-generated method stub
//		return empDAO.empInfo(empid);
//	}

//	// ì§ì›ë²ˆí˜¸ê°? ?“¤?–´?˜¤ë©? ì§ì› ë³´ë„ˆ?Š¤ë¥? return?•˜?Š” ?•¨?ˆ˜ë¥? ?˜¸ì¶œí•œ?‹¤.
//	public double callFunction(int empid) {
//		return empDAO.callFunction(empid);
//	}
}
