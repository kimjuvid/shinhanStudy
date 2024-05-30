package org.firstzone.myapp.dept;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
@Service
public class DeptService {
	
	@Autowired
	@Qualifier("deptmybatis")  // ����
	DeptDAOInterface deptDAO;
	
	public int deptDelete(int deptid) {
		return deptDAO.deptDelete(deptid);
	}

	// 5. ?��?��(Update)
	public int deptUpdate(DeptDTO dep) {
		return deptDAO.depUpdate(dep);
	}

	// 4. ?��?��(Insert)
	public int deptInsert(DeptDTO dep) {
		return deptDAO.depInsert(dep);
	}

	// 3. ?��?�� MANAGER?�� �??�� ?��?��보기
	public List<DeptDTO> selectByManagerId(int mid) {
		return deptDAO.selectByManagerId(mid);
	}

	
	// 2.?��?���??��?�� ?��?��보기
	public DeptDTO selectByDeptid(int deptid) {
		return deptDAO.selectByDeptid(deptid);
	}

	// 1. �??��모두 조회
	public List<DeptDTO> selectAll() {

		return deptDAO.selectAll();
	}
}
