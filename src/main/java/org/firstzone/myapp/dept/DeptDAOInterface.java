package org.firstzone.myapp.dept;

import java.util.List;

public interface DeptDAOInterface {
	
	public List<DeptDTO> selectAll();

	public int deptDelete(int deptid);

	public int depUpdate(DeptDTO dep);

	public int depInsert(DeptDTO dep);

	public List<DeptDTO> selectByManagerId(int mid);

	public DeptDTO selectByDeptid(int deptid);

	

}
