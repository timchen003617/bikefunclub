package com.bikefunclub.auth.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AuthDAO implements AuthDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/YA801G2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}	
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, authVO.getAuthno());
			pstmt.setString(2, authVO.getAuthname());
			pstmt.setString(3, authVO.getAuthurl());			
			pstmt.setString(4, authVO.getBelongauthno());
			pstmt.setString(5, authVO.getAuthlevel());

			pstmt.executeUpdate();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, authVO.getAuthname());
			pstmt.setString(2, authVO.getAuthurl());
			pstmt.setString(3, authVO.getBelongauthno());
			pstmt.setString(4, authVO.getAuthlevel());
			pstmt.setString(5, authVO.getAuthno());

			pstmt.executeUpdate();

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			// "DELETE FROM auth where authno = ?";
			pstmt.setString(1, authno);

			pstmt.executeUpdate();
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
			con = ds.getConnection();
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

			con = ds.getConnection();
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

}
