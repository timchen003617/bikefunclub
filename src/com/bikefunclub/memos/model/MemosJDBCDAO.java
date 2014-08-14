package com.bikefunclub.memos.model;

import java.util.*;
import java.sql.*;

public class MemosJDBCDAO implements Memos_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String userid = "bikefunclub";
	String passwd = "bikefunclub";

	private static final String INSERT_STMT = 
		"INSERT INTO memos (memno,memcoo) VALUES (?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM memos order by memno";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM memos where memno= ?";
	private static final String DELETE = 
		"DELETE FROM memos where memno = ?";
	private static final String UPDATE = 
		"UPDATE memos set memcoo=?  where memno = ?";

	@Override
	public void insert(MemosVO memosVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setInt(1,memosVO.getMemno());
			pstmt.setString(2,memosVO.getMemcoo());
			
			

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public void update(MemosVO memosVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1,memosVO.getMemcoo());
			pstmt.setInt(2,memosVO.getMemno());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public void delete(Integer memno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memno);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public MemosVO findByPrimaryKey(Integer memno) {

		MemosVO memosVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, memno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memosVO �]�٬� Domain objects
				memosVO = new MemosVO();
				memosVO.setMemcoo(rs.getString("memcoo"));
			    memosVO.setMemno(rs.getInt("memno"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		return memosVO;
	}

	@Override
	public List<MemosVO> getAll() {
		List<MemosVO> list = new ArrayList<MemosVO>();
		MemosVO memosVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memosVO �]�٬� Domain objects
				memosVO = new MemosVO();
				memosVO.setMemno(rs.getInt("memno"));
				memosVO.setMemcoo(rs.getString("memcoo"));
				list.add(memosVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

		MemosJDBCDAO dao = new MemosJDBCDAO();

		//新增
//		MemosVO memosVO1 = new MemosVO();
//		memosVO1.setMemno(8);
//		memosVO1.setMemcoo("21.901880,120.851948");
//		dao.insert(memosVO1);

		//修改
//		MemosVO memosVO2 = new MemosVO();
//		memosVO2.setMemcoo("25.033493,121.564101");
//		memosVO2.setMemno(1);
//		dao.update(memosVO2);

		//刪除
//		dao.delete(1);

		//單筆查詢
//		MemosVO memosVO3 = dao.findByPrimaryKey(2);
//		System.out.print(memosVO3.getMemcoo() + ",");
//		System.out.print(memosVO3.getMemno() + ",");
//		System.out.println("---------------------");

		//查全部
//		List<MemosVO> list = dao.getAll();
//		for (MemosVO aEmp : list) {
//			System.out.print(aEmp.getMemno() + ",");
//			System.out.print(aEmp.getMemcoo() + ",");
//		    System.out.println();
//		}
	}
}