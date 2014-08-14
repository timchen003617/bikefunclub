package com.bikefunclub.report.model;

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

public class ReportDAO implements ReportDAO_interface {
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

	private static final String INSERT_STMT = "INSERT INTO report (repno,memno,empno,byrepno,reptext,repcls,repclsno,repdate,replydate,repprogress,represult) VALUES (reportseq.NEXTVAL, ?, ?, ?, ?, ?, ?, ? ,? , ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM report order by repno";
	private static final String GET_ONE_STMT = "SELECT * FROM report where repno = ?";
	private static final String DELETE = "DELETE FROM report where repno = ?";
	private static final String UPDATE = "UPDATE report set memno = ? ,empno= ? ,byrepno= ? ,reptext = ? ,repcls = ? ,repclsno = ?  ,repdate = ? ,replydate = ? ,repprogress = ? ,represult =? where repno = ?";

	@Override
	public void insert(ReportVO reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, reportVO.getMemno());
			pstmt.setInt(2, reportVO.getEmpno());
			pstmt.setInt(3, reportVO.getByrepno());
			pstmt.setString(4, reportVO.getReptext());
			pstmt.setString(5, reportVO.getRepcls());
			pstmt.setInt(6, reportVO.getRepclsno());
			pstmt.setDate(7, reportVO.getRepdate());
			pstmt.setDate(8, reportVO.getReplydate());
			pstmt.setString(9, reportVO.getRepprogress());
			pstmt.setString(10, reportVO.getRepresult());

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
	public void update(ReportVO reportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, reportVO.getMemno());
			pstmt.setInt(2, reportVO.getEmpno());
			pstmt.setInt(3, reportVO.getByrepno());
			pstmt.setString(4, reportVO.getReptext());
			pstmt.setString(5, reportVO.getRepcls());
			pstmt.setInt(6, reportVO.getRepclsno());
			pstmt.setDate(7, reportVO.getRepdate());
			pstmt.setDate(8, reportVO.getReplydate());
			pstmt.setString(9, reportVO.getRepprogress());
			pstmt.setString(10, reportVO.getRepresult());
			pstmt.setInt(11, reportVO.getRepno());
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
	public void delete(Integer repno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, repno);

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
	public ReportVO findByPrimaryKey(Integer repno) {

		ReportVO reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, repno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				reportVO = new ReportVO();
				reportVO.setRepno(rs.getInt("repno"));
				reportVO.setMemno(rs.getInt("memno"));
				reportVO.setEmpno(rs.getInt("empno"));
				reportVO.setByrepno(rs.getInt("byrepno"));
				reportVO.setReptext(rs.getString("reptext"));
				reportVO.setRepcls(rs.getString("repcls"));
				reportVO.setRepclsno(rs.getInt("repclsno"));
				reportVO.setRepdate(rs.getDate("repdate"));
				reportVO.setReplydate(rs.getDate("replydate"));
				reportVO.setRepprogress(rs.getString("repprogress"));
				reportVO.setRepresult(rs.getString("represult"));
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
		return reportVO;
	}

	@Override
	public List<ReportVO> getAll() {
		List<ReportVO> list = new ArrayList<ReportVO>();
		ReportVO reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				reportVO = new ReportVO();
				reportVO.setRepno(rs.getInt("repno"));
				reportVO.setMemno(rs.getInt("memno"));
				reportVO.setEmpno(rs.getInt("empno"));
				reportVO.setByrepno(rs.getInt("byrepno"));
				reportVO.setReptext(rs.getString("reptext"));
				reportVO.setRepcls(rs.getString("repcls"));
				reportVO.setRepclsno(rs.getInt("repclsno"));
				reportVO.setRepdate(rs.getDate("repdate"));
				reportVO.setReplydate(rs.getDate("replydate"));
				reportVO.setRepprogress(rs.getString("repprogress"));
				reportVO.setRepresult(rs.getString("represult"));
				list.add(reportVO); // Store the row in the list
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
