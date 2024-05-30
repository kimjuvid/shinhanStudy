package org.firstzone.myapp.dept;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shinhan.myapp.util.DBUtil;

@Repository
public class DeptDAO implements DeptDAOInterface {
	
	// ≈∏¿‘¿Ã ∞∞¿∏∏È Injection
	// ∞∞¿∫ ≈∏¿‘¿Ã ø©∑Ø∞≥¿Ã∏È error
	@Autowired
	@Qualifier("dataSource")
	DataSource ds;
	
	
	Connection conn;
	Statement st;
	PreparedStatement pst;
	ResultSet rs;

	//6. ?Ç≠?†ú(Delete)
	public int deptDelete(int deptid) {
		int result = 0;
		String sql = "delete from departments"
				+ " where DEPARTMENT_ID=?";
		
		try {
			conn = ds.getConnection(); 
			pst = conn.prepareStatement(sql);
			pst.setInt(1, deptid);
			result = pst.executeUpdate(); //DMLÎ¨∏Ïû•?? executeUpdate, SelectÎ¨∏Ï? executeQuery //Í±¥Ïàò, Î™ªÌïòÎ©? 0, ?óê?ü¨?ÇòÎ©? -1
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		return result;
	}
	
	
	// 5. ?àò?†ï(Update)
			public int depUpdate(DeptDTO dep) {
				int result = 0;
				String sql = "update departments set"
						+ " DEPARTMENT_NAME =?,"
						+ " MANAGER_ID=?,"
						+ " LOCATION_ID=?"
						+ " where department_id =?";
				try {
					conn = ds.getConnection();
					//conn.setAutoCommit(true);
					pst = conn.prepareStatement(sql);
					pst.setString(1, dep.getDepartment_name());
					pst.setInt(2, dep.getManager_id());
					pst.setInt(3, dep.getLocation_id());
					pst.setInt(4, dep.getDepartment_id());
					
					result = pst.executeUpdate(); // DMLÎ¨∏Ïû•?? executeUpdate, selectÎ¨∏Ï? executeQuery

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					DBUtil.dbDisconnect(conn, pst, rs);
				}
				return result;
			}

	
	// 4. ?ûÖ?†•(Insert)
		public int depInsert(DeptDTO dep) {
			int result = 0;
			String sql = "insert into departments(DEPARTMENT_ID,DEPARTMENT_NAME,MANAGER_ID,LOCATION_ID) values(?,?,?,?)";
			try {
				conn = ds.getConnection();
				//conn.setAutoCommit(true);
				pst = conn.prepareStatement(sql);
				pst.setInt(1, dep.getDepartment_id());
				pst.setString(2, dep.getDepartment_name());
				pst.setInt(3, dep.getManager_id());
				pst.setInt(4, dep.getLocation_id());

				result = pst.executeUpdate(); // DMLÎ¨∏Ïû•?? executeUpdate, selectÎ¨∏Ï? executeQuery

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DBUtil.dbDisconnect(conn, pst, rs);
			}
			return result;
		}

	// 3. ?äπ?†ï MANAGER?ùò Î∂??Ñú ?ÉÅ?Ñ∏Î≥¥Í∏∞
	public List<DeptDTO> selectByManagerId(int mid) {
		List<DeptDTO> deptlist = new ArrayList<DeptDTO>();
		String sql = "select * from departments where manager_id= ?";
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, mid);
			rs = pst.executeQuery();
			while (rs.next()) {
				DeptDTO dep = makedept(rs);
				deptlist.add(dep);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		return deptlist;
	}

	// 2.?äπ?†ïÎ∂??Ñú?ùò ?ÉÅ?Ñ∏Î≥¥Í∏∞
	public DeptDTO selectByDeptid(int deptid) {
		DeptDTO dep = null;
		String sql = "select * from departments where department_id = " + deptid;
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				dep = makedept(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dep;
	}
	
	// 1. Î∂??Ñú Î™®Îëê Ï°∞Ìöå
	public List<DeptDTO> selectAll() {
		List<DeptDTO> deptlist = new ArrayList<DeptDTO>();
		String sql = "select * from departments";
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				DeptDTO dep = makedept(rs);
				deptlist.add(dep);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		return deptlist;
	}

	private DeptDTO makedept(ResultSet rs) throws SQLException {
		DeptDTO dep = new DeptDTO();
		dep.setDepartment_id(rs.getInt("department_id"));
		dep.setDepartment_name(rs.getString("department_name"));
		dep.setManager_id(rs.getInt("manager_id"));
		dep.setLocation_id(rs.getInt("location_id"));

		return dep;
	}
}
