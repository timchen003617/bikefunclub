package com.bikefunclub.empauth.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class EmpauthDAO implements Empauth_interface {
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
		"INSERT INTO empauth (empno,authno) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM empauth order by empno";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM empauth where empno = ?";
	private static final String DELETE = 
		"DELETE FROM empauth where empno = ?";
	private static final String UPDATE = 
		"UPDATE empauth set authno=? where empno = ?";

	@Override
	public void insert(EmpauthVO EmpauthVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

		
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, EmpauthVO.getEmpno());
			pstmt.setString(2, EmpauthVO.getAuthno());
		

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
	public void update(EmpauthVO empauthVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

	
			pstmt = con.prepareStatement(UPDATE);

		
			pstmt.setString(1, empauthVO.getAuthno());
			pstmt.setInt(2, empauthVO.getEmpno());

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
	public void delete(Integer empno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

	
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, empno);

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
	public EmpauthVO findByPrimaryKey(Integer empno) {

		EmpauthVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

	
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, empno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				empVO = new EmpauthVO();
				empVO.setEmpno(rs.getInt("empno"));
				empVO.setAuthno(rs.getString("authno"));
				
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
	public List<EmpauthVO> getAll() {
		List<EmpauthVO> list = new ArrayList<EmpauthVO>();
		EmpauthVO empauthVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

		
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				empauthVO = new EmpauthVO();
				empauthVO.setEmpno(rs.getInt("empno"));
				empauthVO.setAuthno(rs.getString("authno"));
				
				list.add(empauthVO); // Store the row in the list
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
		return list;
	}

	public static void main(String[] args) {

		EmpauthDAO dao = new EmpauthDAO();

		//新增
//		EmpauthVO empauthVO1 = new EmpauthVO();
//		empauthVO1.setEmpno(3);
//		empauthVO1.setAuthno("B1");
//		dao.insert(empauthVO1);

		//修改
//		EmpauthVO empauthVO2 = new EmpauthVO();
//	    empauthVO2.setAuthno("B");
//		empauthVO2.setEmpno(2);
//		dao.update(empauthVO2);

		//刪除
//		dao.delete(2);

		//查詢單筆
//		EmpauthVO empVO3 = dao.findByPrimaryKey(1);
//		System.out.print(empVO3.getEmpno() + ",");
//		System.out.print(empVO3.getAuthno() + ",");
//		System.out.println("---------------------");

		//查詢多筆
//		List<EmpauthVO> list = dao.getAll();
//		for (EmpauthVO aEmp : list) {
//			System.out.print(aEmp.getEmpno() + ",");
//			System.out.print(aEmp.getAuthno() + ",");
//			System.out.println();
//		}
	}
}