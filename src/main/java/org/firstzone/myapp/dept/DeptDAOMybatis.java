package org.firstzone.myapp.dept;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("deptmybatis")
public class DeptDAOMybatis implements DeptDAOInterface {

	@Autowired
	SqlSession sqlsession;
	Logger logger = LoggerFactory.getLogger(DeptDAOMybatis.class);
	String namespace = "com.shinhan.dept.";
	
	public List<DeptDTO> selectAll() {
		System.out.println("====지나가냐====");
		logger.info("deptDAOMybatis.....selectAll()");
		
		return sqlsession.selectList(namespace + "selectAll");
	}

	public DeptDTO selectByDeptid(int deptid) {
		logger.info("deptDAOMybatis.....selectByDeptid()");
		return sqlsession.selectOne(namespace + "selectById", deptid);
	}
	
	public int depInsert(DeptDTO dept) {
		System.out.println("======지나가나 insertt======");
		logger.info("deptDAOMybatis.....depInsert()");
		return sqlsession.insert(namespace + "deptInsert", dept);
	}
	
	public int deptDelete(int deptid) {
		logger.info("deptDAOMybatis.....deptDelete()");
		return sqlsession.delete(namespace+"deptDelete",deptid);
	}

	public int depUpdate(DeptDTO dept) {
		logger.info("deptDAOMybatis.....depUpdate()");
		return sqlsession.update(namespace+"deptUpdate",dept);
	}

	
	public List<DeptDTO> selectByManagerId(int mid) {
		return null;
	}
}
