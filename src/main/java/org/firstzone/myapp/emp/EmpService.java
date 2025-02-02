package org.firstzone.myapp.emp;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Controller->Service->DAO
//          <-       <-
//Service: 에노테이션으로 내가 서비스라는 것을 말해야함
@Service
public class EmpService {
	
	@Autowired
	EmpDAOMybatis empDAO;

	//濡쒓렇?씤?븯湲? 
		public EmpDTO loginChk(String email,String phone) {
			return empDAO.loginCheck( email, phone);
		}
	

	// 1.紐⑤뱺job 議고쉶
		public List<JobDTO> selectAllJob() {
		return empDAO.selectAllJob();
	}

	// 1.留ㅻ땲??紐⑤몢 議고쉶
	public List<HashMap<String, Object>> selectAllManager() {
		return empDAO.selectAllManager();
	}

	// ?씠硫붿씪以묐났泥댄겕
	public int selectByEmail(String email) {
		return empDAO.selectByEmail(email);
	}

	// 8. ?궘?젣(Delete)
	public int empDelete(int empid) {
		return empDAO.empDelete(empid);
	}

	// 7. ?닔?젙(Update)
	public int empUpdate(EmpDTO emp) {
		return empDAO.empUpdate(emp);
	}

	// 6. ?엯?젰(Insert)
	public int empInsert(EmpDTO emp) {
		return empDAO.empInsert(emp);
	}

	// 5.?떎?뼇?븳 議곌굔?쑝濡? 議고쉶?븯湲?
	// 遺??꽌蹂?(=), 吏곸콉蹂?(=), ?엯?궗?씪蹂?(>=), 湲됱뿬(>=)
	public List<EmpDTO> selectByCondition(int deptid, String jobid, Date hdate, int salary) {
		return empDAO.selectByCondition(deptid, jobid, hdate, salary);
	}

	// 4. ?듅?젙job?씤 吏곸썝議고쉶
	public List<EmpDTO> selectByJobId(String jobId) {

		return empDAO.selectByJobId(jobId);
	}

	// 3. ?듅?젙遺??꽌?쓽 吏곸썝議고쉶
	public List<EmpDTO> selectByDepartmentId(int deptId) {

		return empDAO.selectByDepartmentId(deptId);
	}

	// 2.?듅?젙吏곸썝?쓽 ?긽?꽭蹂닿린
	public EmpDTO selectById(int empId) {
		return empDAO.selectById(empId);
	}

	// 1. 吏곸썝紐⑤몢 議고쉶
	public List<EmpDTO> selectAll() {

		return empDAO.selectAll();
	}
//	//吏곸썝踰덊샇瑜? ?씠?슜?빐?꽌 吏곸썝?쓽 ?씠由꾧낵 吏곸콉怨? 湲됱뿬瑜? 議고쉶?븳?떎.
//	public Map<String, Object> empInfo(int empid) {
//		// TODO Auto-generated method stub
//		return empDAO.empInfo(empid);
//	}

//	// 吏곸썝踰덊샇媛? ?뱾?뼱?삤硫? 吏곸썝 蹂대꼫?뒪瑜? return?븯?뒗 ?븿?닔瑜? ?샇異쒗븳?떎.
//	public double callFunction(int empid) {
//		return empDAO.callFunction(empid);
//	}
}
