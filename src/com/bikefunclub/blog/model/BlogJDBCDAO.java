package com.bikefunclub.blog.model;

import java.util.*;
import java.util.Date;
import java.sql.*;

public class BlogJDBCDAO implements BlogDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String userid = "bikefunclub";
	String passwd = "bikefunclub";

	private static final String INSERT_STMT = 
		"INSERT INTO blog (blogno,memno,blogclsno,bgtitle,bgtext,authname,bgtime) VALUES (blogseq.NEXTVAL, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT blogno,memno,blogclsno,bgtitle,authname,bgtext,to_char(bgtime,'YYYY-MM-DD HH24:MI:SS') bgtime FROM blog order by blogno";
	private static final String GET_ONE_STMT = 
		"SELECT blogno,memno,blogclsno,bgtitle,bgtext,authname,to_char(bgtime,'YYYY-MM-DD HH24:MI:SS') bgtime FROM blog where blogno = ?";
	private static final String DELETE = 
		"DELETE FROM blog where blogno = ?";
	private static final String UPDATE = 
		"UPDATE blog set memno=?, blogclsno=?, bgtitle=?, bgtext=?, authname=?, bgtime=? where blogno = ?";

	@Override
	public void insert(BlogVO blogVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, blogVO.getMemno());
			pstmt.setInt(2, blogVO.getBlogclsno());
			pstmt.setString(3, blogVO.getBgtitle());
			pstmt.setString(4, blogVO.getBgtext());
			pstmt.setString(5, blogVO.getAuthname());
			pstmt.setTimestamp(6, blogVO.getBgtime());

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
	public void update(BlogVO blogVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, blogVO.getMemno());
			pstmt.setInt(2, blogVO.getBlogclsno());
			pstmt.setString(3, blogVO.getBgtitle());
			pstmt.setString(4, blogVO.getBgtext());
			pstmt.setString(5, blogVO.getAuthname());
			pstmt.setTimestamp(6, blogVO.getBgtime());
			pstmt.setInt(7, blogVO.getBlogno());

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
	public void delete(Integer blogno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, blogno);

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
	public BlogVO findByPrimaryKey(Integer blogno) {

		BlogVO blogVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, blogno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				blogVO = new BlogVO();
				blogVO.setBlogno(rs.getInt("blogno"));
				blogVO.setMemno(rs.getInt("memno"));
				blogVO.setBlogclsno(rs.getInt("blogclsno"));
				blogVO.setBgtitle(rs.getString("bgtitle"));
				blogVO.setBgtext(rs.getString("bgtext"));
				blogVO.setAuthname(rs.getString("authname"));
				blogVO.setBgtime(rs.getTimestamp("bgtime"));
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
		return blogVO;
	}

	@Override
	public List<BlogVO> getAll() {
		List<BlogVO> list = new ArrayList<BlogVO>();
		BlogVO blogVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// blogVO 也稱為 Domain objects
				blogVO = new BlogVO();
				blogVO.setBlogno(rs.getInt("blogno"));
				blogVO.setMemno(rs.getInt("memno"));
				blogVO.setBlogclsno(rs.getInt("blogclsno"));
				blogVO.setBgtitle(rs.getString("bgtitle"));
				blogVO.setBgtext(rs.getString("bgtext"));
				blogVO.setAuthname(rs.getString("authname"));
				blogVO.setBgtime(rs.getTimestamp("bgtime"));
				list.add(blogVO); // Store the row in the list
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

	@Override
	public List<BlogVO> getBlogs_frommemno(Integer memno) {
		// TODO Auto-generated method stub
		return null;
	}

//	public static void main(String[] args) {
//
//		BlogJDBCDAO dao = new BlogJDBCDAO();
//		Date date = new Date(System.currentTimeMillis());
//		Timestamp blogdate = Timestamp.valueOf(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
//
//		// 新增
//		BlogVO blogVO1 = new BlogVO();
//		blogVO1.setMemno(1);
//		blogVO1.setBlogclsno(1);
//		blogVO1.setBgtitle("12345");
//		blogVO1.setBgtext("6789");
//		blogVO1.setBgtime(blogdate);
//		blogVO1.setAuthname("public");
//		dao.insert(blogVO1);
//
//		// 修改
//		BlogVO blogVO2 = new BlogVO();
//		blogVO2.setBlogno(8);
//		blogVO2.setMemno(1);
//		blogVO2.setBlogclsno(1);
//		blogVO2.setBgtitle("什麼團");
//		blogVO2.setBgtext("團團團");
//		blogVO2.setBgtime(blogdate);
//		blogVO2.setAuthname("public");
//		dao.update(blogVO2);
//
//		// 刪除
//		dao.delete(8);
//
//		// 查詢
//		BlogVO blogVO3 = dao.findByPrimaryKey(1);
//		System.out.print(blogVO3.getBlogno() + ",");
//		System.out.print(blogVO3.getMemno() + ",");
//		System.out.print(blogVO3.getBlogclsno() + ",");
//		System.out.print(blogVO3.getBgtitle() + ",");
//		System.out.print(blogVO3.getBgtext() + ",");
//		System.out.print(blogVO3.getBgtime() + ",");
//		System.out.println(blogVO3.getAuthname());
//		System.out.println("---------------------");
//
//		// 查詢
//		List<BlogVO> list = dao.getAll();
//		for (BlogVO blog : list) {
//			System.out.print(blog.getBlogno() + ",");
//			System.out.print(blog.getMemno() + ",");
//			System.out.print(blog.getBlogclsno() + ",");
//			System.out.print(blog.getBgtitle() + ",");
//			System.out.print(blog.getBgtext() + ",");
//			System.out.print(blog.getBgtime() + ",");
//			System.out.print(blog.getAuthname());
//			System.out.println();
//		}
//	}
}