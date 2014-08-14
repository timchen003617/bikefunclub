package com.bikefunclub.auth.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthJDBCDAO implements AuthDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String userid = "bikefunclub";
	String passwd = "bikefunclub";
	// authno authname belongauthno authlevel
	private static final String INSERT_STMT = "INSERT INTO auth (authno, authname,authurl, belongauthno, authlevel) VALUES (?,?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT authno, authname,authurl, belongauthno, authlevel FROM auth order by authno";
	private static final String GET_ONE_STMT = "SELECT authno, authname,authurl, belongauthno, authlevel FROM auth where authno = ?";
	private static final String DELETE = "DELETE FROM auth where authno = ?";
	private static final String UPDATE = "UPDATE auth SET authname = ?,authurl = ?, belongauthno = ? , authlevel = ? where authno = ?";

	/********* 新增 *********/
	@Override
	public void insert(AuthVO authVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, authVO.getAuthno());
			pstmt.setString(2, authVO.getAuthname());
			pstmt.setString(3, authVO.getAuthurl());			
			pstmt.setString(4, authVO.getBelongauthno());
			pstmt.setString(5, authVO.getAuthlevel());

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

	/********* 修改 *********/
	@Override
	public void update(AuthVO authVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, authVO.getAuthname());
			pstmt.setString(2, authVO.getAuthurl());
			pstmt.setString(3, authVO.getBelongauthno());
			pstmt.setString(4, authVO.getAuthlevel());
			pstmt.setString(5, authVO.getAuthno());

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

	/********* 刪除 *********/
	@Override
	public void delete(String authno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			// "DELETE FROM auth where authno = ?";
			pstmt.setString(1, authno);

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

	/********* 查詢 *********/
	@Override
	public AuthVO findByPrimaryKey(String authno) {
		AuthVO authVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, authno);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				// "SELECT authno, authname, belongauthno, authlevel FROM auth where authno = ?";
				authVO = new AuthVO();
				authVO.setAuthno(rs.getString("authno"));
				authVO.setAuthname(rs.getString("authname"));
				authVO.setAuthurl(rs.getString("authurl"));
				authVO.setBelongauthno(rs.getString("belongauthno"));
				authVO.setAuthlevel(rs.getString("authlevel"));

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
		return authVO;
	}

	/************ 查詢全部 ***********/
	@Override
	public List<AuthVO> getAll() {
		List<AuthVO> list = new ArrayList<AuthVO>();
		AuthVO authVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				authVO = new AuthVO();
				authVO.setAuthno(rs.getString("authno"));
				authVO.setAuthname(rs.getString("authname"));
				authVO.setAuthurl(rs.getString("authurl"));
				authVO.setBelongauthno(rs.getString("belongauthno"));
				authVO.setAuthlevel(rs.getString("authlevel"));

				list.add(authVO); // Store the row in the list
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
		AuthJDBCDAO dao = new AuthJDBCDAO();

		// 新增
		// AuthVO authVO1 = new AuthVO();
		// authVO1.setAuthno("Z1");
		// authVO1.setAuthname("糞坑管理");
		// authVO1.setBelongauthno("Z");
		// authVO1.setAuthlevel("5");
		// dao.insert(authVO1);

		// 修改
		// AuthVO authVO2 = new AuthVO();
		// authVO2.setAuthno("Z1");
		// authVO2.setAuthname("尿斗管理");
		// authVO2.setBelongauthno("X");
		// authVO2.setAuthlevel("9");
		// dao.update(authVO2);
		/** authno authname belongauthno authlevel **/
		// 刪除
		// dao.delete("Z1");

		// //查詢
		// AuthVO authVO3 = dao.findByPrimaryKey("A1");
		// System.out.print(authVO3.getAuthno() + ",");
		// System.out.print(authVO3.getAuthname() + ",");
		// System.out.print(authVO3.getBelongauthno() + ",");
		// System.out.println(authVO3.getAuthlevel());
		// System.out.println("---------------------");

		// 查全部
		 List<AuthVO> list = dao.getAll();
		 for(AuthVO auth : list){
		 System.out.print(auth.getAuthno() + ",");
		 System.out.print(auth.getAuthname() + ",");
		 System.out.print(auth.getBelongauthno() + ",");
		 System.out.println(auth.getAuthlevel());
		 }
	}

}
