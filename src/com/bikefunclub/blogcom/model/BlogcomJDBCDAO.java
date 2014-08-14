package com.bikefunclub.blogcom.model;

import java.util.*;
import java.util.Date;
import java.sql.*;

public class BlogcomJDBCDAO implements BlogcomDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String userid = "bikefunclub";
	String passwd = "bikefunclub";

	private static final String INSERT_STMT = 
		"INSERT INTO blogcom (bgcomno, blogno, memno, bgcomtext, bgcomtime) VALUES (blogcomseq.NEXTVAL, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM blogcom order by bgcomno";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM blogcom where bgcomno = ?";
	private static final String DELETE = 
		"DELETE FROM blogcom where bgcomno = ?";
	private static final String UPDATE = 
		"UPDATE blogcom set blogno=?, memno=?, bgcomtext=?, bgcomtime=? where bgcomno = ?";

	@Override
	public void insert(BlogcomVO blogcomVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, blogcomVO.getBlogno());
			pstmt.setInt(2, blogcomVO.getMemno());
			pstmt.setString(3, blogcomVO.getBgcomtext());
			pstmt.setTimestamp(4, blogcomVO.getBgcomtime());

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
	public void update(BlogcomVO blogcomVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, blogcomVO.getBlogno());
			pstmt.setInt(2, blogcomVO.getMemno());
			pstmt.setString(3, blogcomVO.getBgcomtext());
			pstmt.setTimestamp(4, blogcomVO.getBgcomtime());
			pstmt.setInt(5, blogcomVO.getBgcomno());

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
	public void delete(Integer bgcomno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, bgcomno);

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
	public BlogcomVO findByPrimaryKey(Integer bgcomno) {

		BlogcomVO blogcomVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, bgcomno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				blogcomVO = new BlogcomVO();
				blogcomVO.setBgcomno(rs.getInt("bgcomno"));
				blogcomVO.setBlogno(rs.getInt("blogno"));
				blogcomVO.setMemno(rs.getInt("memno"));
				blogcomVO.setBgcomtext(rs.getString("bgcomtext"));
				blogcomVO.setBgcomtime(rs.getTimestamp("bgcomtime"));
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
		return blogcomVO;
	}

	@Override
	public List<BlogcomVO> getAll() {
		List<BlogcomVO> list = new ArrayList<BlogcomVO>();
		BlogcomVO blogcomVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// blogcomVO 也稱為 Domain objects
				blogcomVO = new BlogcomVO();
				blogcomVO.setBgcomno(rs.getInt("bgcomno"));
				blogcomVO.setBlogno(rs.getInt("blogno"));
				blogcomVO.setMemno(rs.getInt("memno"));
				blogcomVO.setBgcomtext(rs.getString("bgcomtext"));
				blogcomVO.setBgcomtime(rs.getTimestamp("bgcomtime"));
				list.add(blogcomVO); // Store the row in the list
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

//	public static void main(String[] args) {
//
//		BlogcomJDBCDAO dao = new BlogcomJDBCDAO();
//		Date date = new Date(System.currentTimeMillis());
//		Timestamp blogcomdate = Timestamp.valueOf(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
//
//		// 新增
//		BlogcomVO blogcomVO1 = new BlogcomVO();
//		blogcomVO1.setBlogno(1);
//		blogcomVO1.setMemno(2);
//		blogcomVO1.setBgcomtext("速求、確實...");
//		blogcomVO1.setBgcomtime(blogcomdate);
//		dao.insert(blogcomVO1);
//
//		// 修改
//		BlogcomVO blogcomVO2 = new BlogcomVO();		
//		blogcomVO2.setBgcomno(5);
//		blogcomVO2.setBlogno(1);
//		blogcomVO2.setMemno(1);
//		blogcomVO2.setBgcomtext("安靜、堅強...");
//		blogcomVO2.setBgcomtime(blogcomdate);	
//		dao.update(blogcomVO2);
//
//		// 刪除
//		dao.delete(5);
//
//		// 查詢
//		BlogcomVO blogcomVO3 = dao.findByPrimaryKey(2);
//		System.out.print(blogcomVO3.getBgcomno() + ",");
//		System.out.print(blogcomVO3.getBlogno() + ",");
//		System.out.print(blogcomVO3.getMemno() + ",");
//		System.out.print(blogcomVO3.getBgcomtext() + ",");
//		System.out.print(blogcomVO3.getBgcomtime() + ",");
//		System.out.println("---------------------");
//
//		// 查詢
//		List<BlogcomVO> list = dao.getAll();
//		for (BlogcomVO blogcom : list) {
//			System.out.print(blogcom.getBgcomno() + ",");
//			System.out.print(blogcom.getBlogno() + ",");
//			System.out.print(blogcom.getMemno() + ",");
//			System.out.print(blogcom.getBgcomtext() + ",");
//			System.out.print(blogcom.getBgcomtime() + ",");
//			System.out.println();
//		}
//	}
}