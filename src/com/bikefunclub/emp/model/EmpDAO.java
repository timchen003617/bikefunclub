package com.bikefunclub.emp.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class EmpDAO implements EmpDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/YA801G2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
		"INSERT INTO emp (empno,empacc,emppw,empname,empemail,empaddr,empid,emprgdate,emptel) VALUES (empseq.NEXTVAL, ?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT empno,empacc,emppw,empname,empemail,empaddr,empid,emprgdate,emptel FROM emp order by empno";
	private static final String GET_ONE_STMT = 
		"SELECT  empno,empacc,emppw,empname,empemail,empaddr,empid,emprgdate,emptel FROM emp where empno = ?";
	private static final String DELETE = 
		"DELETE FROM emp where empno = ?";
	private static final String UPDATE = 
		"UPDATE emp set empacc=?, empname=? ,empemail=?,empaddr=?,empid=?,emprgdate=?,emptel=? where empno = ?";
	private static final String GET_ONE_STMT_ACCOUNT = "SELECT * FROM emp where empacc = ?";

	@Override
	public void insert(EmpVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, empVO.getEmpacc());
			pstmt.setString(2, empVO.getEmppw());
			pstmt.setString(3, empVO.getEmpname());
			pstmt.setString(4, empVO.getEmpemail());
			pstmt.setString(5, empVO.getEmpaddr());
			pstmt.setString(6, empVO.getEmpid());
			pstmt.setTimestamp  (7, empVO.getEmprgdate());
			pstmt.setString(8, empVO.getEmptel());			
			

			pstmt.executeUpdate();

			// Handle any driver errors
	
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(EmpVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
            //empname=?,
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, empVO.getEmpacc());
//			pstmt.setString(2, empVO.getEmppw());
			pstmt.setString(2, empVO.getEmpname());
			pstmt.setString(3, empVO.getEmpemail());
			pstmt.setString(4, empVO.getEmpaddr());
			pstmt.setString(5, empVO.getEmpid());
			pstmt.setTimestamp(6, empVO.getEmprgdate());
			pstmt.setString(7, empVO.getEmptel());		
            pstmt.setInt(8, empVO.getEmpno());
			pstmt.executeUpdate();

		
	
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer empno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, empno);

			pstmt.executeUpdate();


		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public EmpVO findByPrimaryKey(Integer empno) {

		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, empno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				empVO = new EmpVO();
				empVO.setEmpno(rs.getInt("empno"));
				empVO.setEmpacc(rs.getString("empacc"));
				empVO.setEmppw(rs.getString("emppw"));
				empVO.setEmpname(rs.getString("empname"));
				empVO.setEmpemail(rs.getString("empemail"));
				empVO.setEmpaddr(rs.getString("empaddr"));
				empVO.setEmpid(rs.getString("empid"));
				empVO.setEmprgdate(rs.getTimestamp("emprgdate"));
				empVO.setEmptel(rs.getString("emptel"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return empVO;
	}
	
	@Override
	public EmpVO findByAccount(String empacc) {

		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_ACCOUNT);

			pstmt.setString(1, empacc);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				empVO = new EmpVO();
				empVO.setEmpno(rs.getInt("empno"));
				empVO.setEmpacc(rs.getString("empacc"));
				empVO.setEmppw(rs.getString("emppw"));
				empVO.setEmpname(rs.getString("empname"));
				empVO.setEmpemail(rs.getString("empemail"));
				empVO.setEmpaddr(rs.getString("empaddr"));
				empVO.setEmpid(rs.getString("empid"));
				empVO.setEmprgdate(rs.getTimestamp("emprgdate"));
				empVO.setEmptel(rs.getString("emptel"));
			}

			// Handle any driver errors

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return empVO;
	}
	
	

	@Override
	public List<EmpVO> getAll() {
		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				empVO = new EmpVO();
				empVO.setEmpno(rs.getInt("empno"));
				empVO.setEmpacc(rs.getString("empacc"));
				empVO.setEmppw(rs.getString("emppw"));
				empVO.setEmpname(rs.getString("empname"));
				empVO.setEmpemail(rs.getString("empemail"));
				empVO.setEmpaddr(rs.getString("empaddr"));
				empVO.setEmpid(rs.getString("empid"));
				empVO.setEmprgdate(rs.getTimestamp("emprgdate"));
				empVO.setEmptel(rs.getString("emptel"));
				list.add(empVO); // Store the row in the list
			}


		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

}