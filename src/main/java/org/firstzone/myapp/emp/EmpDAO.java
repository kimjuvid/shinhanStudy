package org.firstzone.myapp.emp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shinhan.myapp.util.DBUtil;


//DAO(Data Access?? ë¹ì???¤ ë¡ì§? ì²ë¦¬?? Object)
@Repository
public class EmpDAO {
	
	// # db¿¬°á¹æ¹ý
	// 1. field ÀÌ¿ëÇÏ±â
	@Autowired
	@Qualifier("dataSource")
	DataSource ds;
	
//	// 2. »ý¼ºÀÚ ÀÌ¿ëÇÏ±â
//	@Autowired
//	public EmpDAO(DataSource ds) {
//		this.ds = ds;
//	}
//	
//	// 3. setter ³Ö±â
//	@Autowired
//	public void setDs(DataSource ds) {
//		this.ds = ds;
//	}
	
	
	Connection conn;
	Statement st;
	PreparedStatement pst; // Statementë¥? ??ë°ì, ë°ì¸?©ë³?? ì§??
	ResultSet rs;

	//ë¡ê·¸?¸?ê¸? 
	public EmpDTO loginChk(String email,String phone) {
		EmpDTO emp = null;
		String sql = "select employee_id,first_name,last_name,phone_number "
				+ " from employees where email =?";
		
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, email);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				if(rs.getString("phone_number").equals(phone)) {
					emp=new EmpDTO();
					emp.setEmployee_id(rs.getInt("employee_id"));
					emp.setFirst_name(rs.getString("first_name"));
					emp.setLast_name(rs.getString("last_name"));
					emp.setEmail(email);
					emp.setPhone_number(phone);
				}else {
					emp=new EmpDTO();
					emp.setEmployee_id(-2);//ë¹ë?ë²í¸ ?¤ë¥?
				}
			}else {
				emp=new EmpDTO();
				emp.setEmployee_id(-1);//ì¡´ì¬?ì§? ?? ì§ì
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		return emp;
	}
	
	// 8. ?­? (Delete)
	public int empDelete(int empid) {
		int result = 0;
		String sql = "delete from employees where EMPLOYEE_ID =?";
		try {
			conn = ds.getConnection();
			// conn.setAutoCommit(true);
			pst = conn.prepareStatement(sql);
			pst.setInt(1, empid);
			result = pst.executeUpdate(); // DMLë¬¸ì¥?? executeUpdate, selectë¬¸ì? executeQuery

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		return result;
	}

	// 7. ?? (Update)
	public int empUpdate(EmpDTO emp) {
		int result = 0;
		String sql = "update employees " + " set FIRST_NAME=?," + " LAST_NAME=?," + " EMAIL=?," + " PHONE_NUMBER=?,"
				+ " HIRE_DATE=?," + " JOB_ID=?," + " SALARY=?," + " COMMISSION_PCT=?," + " MANAGER_ID=?,"
				+ " DEPARTMENT_ID=?" + " where EMPLOYEE_ID =?";
		try {
			conn = ds.getConnection();
			// conn.setAutoCommit(true);
			pst = conn.prepareStatement(sql);
			pst.setInt(11, emp.getEmployee_id());
			pst.setString(1, emp.getFirst_name());
			pst.setString(2, emp.getLast_name());
			pst.setString(3, emp.getEmail());
			pst.setString(4, emp.getPhone_number());
			pst.setDate(5, emp.getHire_date());
			pst.setString(6, emp.getJob_id());
			pst.setInt(7, emp.getSalary());
			pst.setDouble(8, emp.getCommission_pct());
			pst.setInt(9, emp.getManager_id());
			pst.setInt(10, emp.getDepartment_id());
			result = pst.executeUpdate(); // DMLë¬¸ì¥?? executeUpdate, selectë¬¸ì? executeQuery

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		return result;
	}

	// 6. ?? ¥(Insert)
	public int empInsert(EmpDTO emp) {
		int result = 0;
		String sql = "insert into employees values(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			conn = ds.getConnection();
			// conn.setAutoCommit(true);
			pst = conn.prepareStatement(sql);
			pst.setInt(1, emp.getEmployee_id());
			pst.setString(2, emp.getFirst_name());
			pst.setString(3, emp.getLast_name());
			pst.setString(4, emp.getEmail());
			pst.setString(5, emp.getPhone_number());
			pst.setDate(6, emp.getHire_date());
			pst.setString(7, emp.getJob_id());
			pst.setInt(8, emp.getSalary());
			pst.setDouble(9, emp.getCommission_pct());
			pst.setInt(10, emp.getManager_id());
			pst.setInt(11, emp.getDepartment_id());
			result = pst.executeUpdate(); // DMLë¬¸ì¥?? executeUpdate, selectë¬¸ì? executeQuery

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		return result;
	}

	// 5.?¤?? ì¡°ê±´?¼ë¡? ì¡°í?ê¸?
	// ë¶??ë³?(=), ì§ì±ë³?(=), ??¬?¼ë³?(>=), ê¸ì¬(>=)
	public List<EmpDTO> selectByCondition(int deptid, String jobid, Date hdate, int salary) {
		List<EmpDTO> emplist = new ArrayList<EmpDTO>();
		String sql = "select * " + " from employees" + " where department_id =?" + " and job_id=?" + " and hire_date>=?"
				+ " and salary>=?";
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, deptid);// ì²«ë²ì§? ë¬¼ì??? deptidë¥? ?£?´?¼
			pst.setString(2, jobid);// 2ë²ì§¸ ë¬¼ì??? jobIdë¥? ?£?´?¼
			pst.setDate(3, hdate);// 3ë²ì§¸ ë¬¼ì??? hdateë¥? ?£?´?¼
			pst.setInt(4, salary);// 4ë²ì§¸ ë¬¼ì??? salaryë¥? ?£?´?¼
			rs = pst.executeQuery();
			while (rs.next()) {
				EmpDTO emp = makeEmp(rs);
				emplist.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		return emplist;
	}

	// 4-2.?¹? JOB? ì§ì? ??¸ë³´ê¸°

	public List<EmpDTO> selectByJobId(String jobId) {
		List<EmpDTO> emplist = new ArrayList<EmpDTO>();
		String sql = "select * from employees where job_id like ?||'%'";
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, jobId);// ì²«ë²ì§? ë¬¼ì??? jobIdë¥? ?£?´?¼
			rs = pst.executeQuery();
			while (rs.next()) {
				EmpDTO emp = makeEmp(rs);
				emplist.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		return emplist;
	}

	// 4-1.?¹? JOB? ì§ì? ??¸ë³´ê¸°

	public List<EmpDTO> selectByJobId2(String jobId) {
		List<EmpDTO> emplist = new ArrayList<EmpDTO>();
		String sql = "select * from employees where job_id = '" + jobId + "'";
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				EmpDTO emp = makeEmp(rs);
				emplist.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		return emplist;
	}

	// 3-2.?¹? ë¶??? ì§ì? ??¸ë³´ê¸°

	public List<EmpDTO> selectByDepartmentId(int deptId) {
		List<EmpDTO> emplist = new ArrayList<EmpDTO>();
		String sql = "select * from employees where department_id= ?";
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, deptId);
			rs = pst.executeQuery();
			while (rs.next()) {
				EmpDTO emp = makeEmp(rs);
				emplist.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		return emplist;
	}
	// 3-1.?¹? ë¶??? ì§ì? ??¸ë³´ê¸°

	public List<EmpDTO> selectByDepartmentId2(int deptId) {
		List<EmpDTO> emplist = new ArrayList<EmpDTO>();
		String sql = "select * from employees where department_id= " + deptId;
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				EmpDTO emp = makeEmp(rs);
				emplist.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		return emplist;
	}

	// 2.?¹? ì§ì? ??¸ë³´ê¸°
	public EmpDTO selectById(int empId) {
		EmpDTO emp = null;
		String sql = "select * from employees where employee_id = " + empId;
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				emp = makeEmp(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emp;
	}

	public int selectByEmail(String email) {
		EmpDTO emp = null;
		String sql = "select 1 from employees where email = ?";
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, email);
			rs = pst.executeQuery();
			if (rs.next()) {
				return 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		return 0;
	}

	// 1.ëª¨ë job ì¡°í
	public List<JobDTO> selectAllJob() {
		List<JobDTO> joblist = new ArrayList<>();
		String sql = "select * from jobs";
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				JobDTO job = new JobDTO(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getInt(4));
				joblist.add(job);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		return joblist;
	}

	// 1.ë§¤ë??ëª¨ë ì¡°í
	public List<HashMap<String, Object>> selectAllManager() {
		List<HashMap<String, Object>> emplist = new ArrayList<>();
		String sql = "select employee_id, first_name ||'  '|| last_name fullname" + " from employees"
				+ " where employee_id in (" + "                            select distinct manager_id"
				+ "                            from employees"
				+ "                            where manager_id is not null )";
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				HashMap<String, Object> data = new HashMap<>();
				data.put("employee_id", rs.getInt(1));
				data.put("fullname", rs.getString(2));
				emplist.add(data);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		return emplist;
	}

	// 1.ì§ì ëª¨ë ì¡°í
	public List<EmpDTO> selectAll() {
		List<EmpDTO> emplist = new ArrayList<EmpDTO>();
		String sql = "select * from employees";
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				EmpDTO emp = makeEmp(rs);
				emplist.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		return emplist;
	}
	// ?¹? ì§ì 1ëª? ì¡°í

	private EmpDTO makeEmp(ResultSet rs) throws SQLException {
		EmpDTO emp = new EmpDTO();
		emp.setCommission_pct(rs.getDouble("commission_pct"));
		emp.setDepartment_id(rs.getInt("department_id"));
		emp.setEmail(rs.getString("email"));
		emp.setEmployee_id(rs.getInt("employee_id"));
		emp.setFirst_name(rs.getString("first_name"));
		emp.setHire_date(rs.getDate("hire_date"));
		emp.setJob_id(rs.getString("job_id"));
		emp.setLast_name(rs.getString("last_name"));
		emp.setManager_id(rs.getInt("manager_id"));
		emp.setPhone_number(rs.getString("phone_number"));
		emp.setSalary(rs.getInt("salary"));

		return emp;
	}

	// ì§ìë²í¸ë¥? ?? ¥ë°ì? ì§ì? ë³?(?´ë¦?, ì§ì±, ê¸ì¬)ë¥? return
//	public Map<String, Object> empInfo(int empid) {
//		 Map<String, Object> empMap = new HashMap<>();
//		 String fname = null, job=null;
//		 int salary=0;
//		 String sql = "{call sp_empInfo(?,?,?,?)}";
//		 CallableStatement cstmt = null;
//		 conn = DBUtil.dbConnection();
//		 try {
//			cstmt = conn.prepareCall(sql);
//			cstmt.setInt(1, empid);
//			cstmt.registerOutParameter(2, Types.VARCHAR);
//			cstmt.registerOutParameter(3, Types.VARCHAR);
//			cstmt.registerOutParameter(4, Types.INTEGER);
//			boolean result = cstmt.execute();
//			fname = cstmt.getString(2);
//			job = cstmt.getString(3);
//			salary = cstmt.getInt(4);
//			empMap.put("fname", fname);
//			empMap.put("job", job);
//			empMap.put("salary", salary);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			DBUtil.dbDisconnect(conn, cstmt, rs);
//		}
//		return empMap;
	// }
	// ì§ìë²í¸ê°? ?¤?´?¤ë©? ì§ì ë³´ë?¤ë¥? return?? ?¨?ë¥? ?¸ì¶í?¤.
	public double callFunction(int empid) {
		double bonus = 0;
		String sql = "select f_bonus(?) from dual";
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, empid);
			rs = pst.executeQuery();
			if (rs.next()) {
				bonus = rs.getDouble(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bonus;
	}

	// ?¹? ë¶??? ê·¼ë¬´?? ì§ì?¤

	// ?? ¥
	// ?? 
	// ?­? 
}
