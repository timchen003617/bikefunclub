package com.bikefunclub.report.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReportJDBCDAO implements ReportDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String userid = "bikefunclub";
	String passwd = "bikefunclub";

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void update(ReportVO reportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void delete(Integer repno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, repno);

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
	public ReportVO findByPrimaryKey(Integer repno) {

		ReportVO reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		ReportJDBCDAO dao = new ReportJDBCDAO();
		Date date = new Date(System.currentTimeMillis());
		Calendar cd = Calendar.getInstance();
		cd.add(Calendar.DATE, 1);// 增加一天
		java.util.Date utildateadd = cd.getTime();
		java.sql.Date dateadd1 = new java.sql.Date(utildateadd.getTime());
		// 新增

		 ReportVO reportVO1 = new ReportVO();
		 reportVO1.setMemno(2);
		 reportVO1.setEmpno(1);
		 reportVO1.setByrepno(2);
		 reportVO1.setReptext("圖片不雅");
		 reportVO1.setRepcls("3");//1.照片2.網誌3.路線4.揪團
		 reportVO1.setRepclsno(3);
		 reportVO1.setRepdate(date);
		 reportVO1.setReplydate(dateadd1);
		 reportVO1.setRepprogress("2");//已處理
		 reportVO1.setRepresult("已刪除不雅照");
		 dao.insert(reportVO1);

		// 修改
		// ReportVO reportVO2 = new ReportVO();
		// reportVO2.setMemno(1);
		// reportVO2.setEmpno(1);//預設給總管理員處理,之後總管理員指派給其他管理員處理
		// reportVO2.setByrepno(2);
		// reportVO2.setReptext("圖片不雅照");
		// reportVO2.setRepcls("1");
		// reportVO2.setRepclsno(1);
		// reportVO2.setRepdate(date);
		// reportVO2.setReplydate(dateadd1);
		// reportVO2.setRepprogress("1");
		// reportVO2.setRepresult("");
		// reportVO2.setRepno(1);
		// dao.update(reportVO2);

		// 刪除

		// dao.delete(1);

		// 查詢

//		ReportVO reportVO3 = dao.findByPrimaryKey(1);
//		System.out.println(reportVO3.getRepno() + ",");
//		System.out.println(reportVO3.getMemno() + ",");
//		System.out.println(reportVO3.getEmpno() + ",");
//		System.out.println(reportVO3.getByrepno() + ",");
//		System.out.println(reportVO3.getReptext() + ",");
//		System.out.println(reportVO3.getRepcls() + ",");
//		System.out.println(reportVO3.getRepclsno() + ",");
//		System.out.println(reportVO3.getRepdate() + ",");
//		System.out.println(reportVO3.getReplydate() + ",");
//		System.out.println(reportVO3.getRepprogress() + ",");
//		System.out.println(reportVO3.getRepresult() + ",");
//		System.out.println("---------------------");

		// 查詢全部

//		List<ReportVO> list = dao.getAll();
//		for (ReportVO areportVO : list) {
//			System.out.println(areportVO.getRepno() + ",");
//			System.out.println(areportVO.getMemno() + ",");
//			System.out.println(areportVO.getEmpno() + ",");
//			System.out.println(areportVO.getByrepno() + ",");
//			System.out.println(areportVO.getReptext() + ",");
//			System.out.println(areportVO.getRepcls() + ",");
//			System.out.println(areportVO.getRepclsno() + ",");
//			System.out.println(areportVO.getRepdate() + ",");
//			System.out.println(areportVO.getReplydate() + ",");
//			System.out.println(areportVO.getRepprogress() + ",");
//			System.out.println(areportVO.getRepresult() + ",");
//			System.out.println();
//		}

	}

}
