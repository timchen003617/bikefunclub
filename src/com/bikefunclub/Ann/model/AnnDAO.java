package com.bikefunclub.Ann.model;

import java.util.*;
import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AnnDAO implements AnnDAO_interface {
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

	private static final String INSERT_STMT = "INSERT INTO Ann (annno,empno,anntitle,anncontent,anndate,annfile,annfilename,annextname) VALUES (ANNseq.NEXTVAL, ?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT annno,empno,anntitle,anncontent,anndate,annfile,annfilename,annextname  FROM ANN order by anndate desc";
	private static final String GET_ONE_STMT = "SELECT * FROM ANN where annno = ?";
	private static final String DELETE = "DELETE FROM ANN where annno = ?";
	private static final String UPDATE = "UPDATE ANN set empno=?, anntitle=?, anncontent=?, anndate=?, annfile=? ,annfilename=?,annextname=? where annno = ?";

	@Override
	public void insert(AnnVO annVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, annVO.getEmpno());
			pstmt.setString(2, annVO.getAnntitle());
			pstmt.setString(3, annVO.getAnncontent());
			pstmt.setTimestamp(4, annVO.getAnndate());
			pstmt.setBytes(5, annVO.getAnnfile());
			pstmt.setString(6, annVO.getAnnfilename());
			pstmt.setString(7, annVO.getAnnextname());

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
	public void update(AnnVO annVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, annVO.getEmpno());
			pstmt.setString(2, annVO.getAnntitle());
			pstmt.setString(3, annVO.getAnncontent());
			pstmt.setTimestamp(4, annVO.getAnndate());
			pstmt.setBytes(5, annVO.getAnnfile());
			pstmt.setString(6, annVO.getAnnfilename());
			pstmt.setString(7, annVO.getAnnextname());
			pstmt.setInt(8, annVO.getAnnno());
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
	public void delete(Integer annno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, annno);

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
	public AnnVO findByPrimaryKey(Integer annno) {

		AnnVO annVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, annno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// AnnVO �]�٬� Domain objects
				annVO = new AnnVO();
				annVO.setAnnno(rs.getInt("annno"));
				annVO.setEmpno(rs.getInt("empno"));
				annVO.setAnntitle(rs.getString("anntitle"));
				annVO.setAnncontent(rs.getString("anncontent"));
				annVO.setAnndate(rs.getTimestamp("anndate"));
				annVO.setAnnfile(rs.getBytes("annfile"));
				annVO.setAnnfilename(rs.getString("annfilename"));
				annVO.setAnnextname(rs.getString("annextname"));
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
		return annVO;
	}

	@Override
	public List<AnnVO> getAll() {
		List<AnnVO> list = new ArrayList<AnnVO>();
		AnnVO annVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				annVO = new AnnVO();
				annVO.setAnnno(rs.getInt("annno"));
				annVO.setEmpno(rs.getInt("empno"));
				annVO.setAnntitle(rs.getString("anntitle"));
				annVO.setAnncontent(rs.getString("anncontent"));
				annVO.setAnndate(rs.getTimestamp("anndate"));
				annVO.setAnnfile(rs.getBytes("annfile"));
				annVO.setAnnfilename(rs.getString("annfilename"));
				annVO.setAnnextname(rs.getString("annextname"));
				list.add(annVO); // Store the row in the list
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