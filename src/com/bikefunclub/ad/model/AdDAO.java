package com.bikefunclub.ad.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AdDAO implements AdDAO_interface {
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/YA801G2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO ad (adno,adname,adesc,adfile,adfilename,adextname,adurl) VALUES (adseq.NEXTVAL, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM ad order by adno";
	private static final String GET_ONE_STMT = "SELECT * FROM ad where adno = ?";
	private static final String DELETE = "DELETE FROM ad where adno = ?";
	private static final String UPDATE = "UPDATE ad set adname=?, adesc=?, adfile=?, adfilename=?, adextname=?,adurl=? where adno = ?";

	@Override
	public void insert(AdVO adVo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, adVo.getAdname());
			pstmt.setString(2, adVo.getAdesc());
			pstmt.setBytes(3, adVo.getAdfile());
			pstmt.setString(4, adVo.getFilename());
			pstmt.setString(5, adVo.getExtname());
			pstmt.setString(6, adVo.getAdurl());

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

	@Override
	public void update(AdVO adVo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, adVo.getAdname());
			pstmt.setString(2, adVo.getAdesc());
			pstmt.setBytes(3, adVo.getAdfile());
			pstmt.setString(4, adVo.getFilename());
			pstmt.setString(5, adVo.getExtname());
			pstmt.setString(6, adVo.getAdurl());
			pstmt.setInt(7, adVo.getAdno());

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

	@Override
	public void delete(Integer adno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, adno);

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

	@Override
	public AdVO findByPrimaryKey(Integer adno) {

		AdVO adVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, adno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// adVo �]�٬� Domain objects
				adVo = new AdVO();

				adVo.setAdname(rs.getString("adname"));
				adVo.setAdesc(rs.getString("adesc"));
				adVo.setAdfile(rs.getBytes("adfile"));
				adVo.setFilename(rs.getString("adfilename"));
				adVo.setExtname(rs.getString("adextname"));
				adVo.setAdurl(rs.getString("adurl"));
				adVo.setAdno(rs.getInt("adno"));
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
		return adVo;
	}

	@Override
	public List<AdVO> getAll() {
		List<AdVO> list = new ArrayList<AdVO>();
		AdVO adVo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				adVo = new AdVO();
				adVo.setAdno(rs.getInt("adno"));
				adVo.setAdname(rs.getString("adname"));
				adVo.setAdesc(rs.getString("adesc"));
				adVo.setAdfile(rs.getBytes("adfile"));
				adVo.setFilename(rs.getString("adfilename"));
				adVo.setExtname(rs.getString("adextname"));
				adVo.setAdurl(rs.getString("adurl"));
				adVo.setAdno(rs.getInt("adno"));
				list.add(adVo); // Store the row in the list
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