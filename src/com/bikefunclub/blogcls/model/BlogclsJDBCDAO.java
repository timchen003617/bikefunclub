package com.bikefunclub.blogcls.model;

import java.util.*;
import java.sql.*;

public class BlogclsJDBCDAO implements BlogclsDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String userid = "bikefunclub";
	String passwd = "bikefunclub";

	private static final String INSERT_STMT = 
		"INSERT INTO blogcls (blogclsno,blogclsname) VALUES (blogclsseq.NEXTVAL, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM blogcls order by blogclsno";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM blogcls where blogclsno = ?";
	private static final String DELETE = 
		"DELETE FROM blogcls where blogclsno = ?";
	private static final String UPDATE = 
		"UPDATE blogcls set blogclsname=? where blogclsno = ?";

	@Override
	public void insert(BlogclsVO blogclsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, blogclsVO.getBlogclsname());

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
	public void update(BlogclsVO blogclsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, blogclsVO.getBlogclsname());
			pstmt.setInt(2, blogclsVO.getBlogclsno());

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
	public void delete(Integer blogclsno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, blogclsno);

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
	public BlogclsVO findByPrimaryKey(Integer blogclsno) {

		BlogclsVO blogclsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, blogclsno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				blogclsVO = new BlogclsVO();
				blogclsVO.setBlogclsno(rs.getInt("blogclsno"));
				blogclsVO.setBlogclsname(rs.getString("blogclsname"));
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
		return blogclsVO;
	}

	@Override
	public List<BlogclsVO> getAll() {
		List<BlogclsVO> list = new ArrayList<BlogclsVO>();
		BlogclsVO blogclsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// blogclsVO 也稱為 Domain objects
				blogclsVO = new BlogclsVO();
				blogclsVO.setBlogclsno(rs.getInt("blogclsno"));
				blogclsVO.setBlogclsname(rs.getString("blogclsname"));
				list.add(blogclsVO); // Store the row in the list
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

		BlogclsJDBCDAO dao = new BlogclsJDBCDAO();

//		// 新增
//		BlogclsVO blogclsVO1 = new BlogclsVO();
//		blogclsVO1.setBlogclsname("街上");
//		dao.insert(blogclsVO1);
//
//		// 修改
//		BlogclsVO blogclsVO2 = new BlogclsVO();
//		blogclsVO2.setBlogclsno(7);
//		blogclsVO2.setBlogclsname("海邊");
//		dao.update(blogclsVO2);
//
//		// 刪除
//		dao.delete(7);
//
//		// 查詢
//		BlogclsVO blogclsVO3 = dao.findByPrimaryKey(1);
//		System.out.print(blogclsVO3.getBlogclsno() + ",");
//		System.out.print(blogclsVO3.getBlogclsname() + ",");
//		System.out.println("---------------------");
//
//		// 查詢
//		List<BlogclsVO> list = dao.getAll();
//		for (BlogclsVO blogcls : list) {
//			System.out.print(blogcls.getBlogclsno() + ",");
//			System.out.print(blogcls.getBlogclsname() + ",");
//			System.out.println();
//		}
	}
}