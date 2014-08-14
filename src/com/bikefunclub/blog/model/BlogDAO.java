package com.bikefunclub.blog.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BlogDAO implements BlogDAO_interface {

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

	private static final String INSERT_STMT = 
		"INSERT INTO blog (blogno,memno,blogclsno,bgtitle,bgtext,authname,bgtime) VALUES (blogseq.NEXTVAL, ?, ?, ?, ?, ?, sysdate)";
	private static final String GET_ALL_STMT = 
			"SELECT blogno,memno,blogclsno,bgtitle,bgtext,authname,to_char(bgtime,'YYYY-MM-DD HH24:MI:SS') bgtime FROM blog order by blogno";
	private static final String GET_ONE_STMT = 
		"SELECT blogno,memno,blogclsno,bgtitle,bgtext,authname,to_char(bgtime,'YYYY-MM-DD HH24:MI:SS') bgtime FROM blog where blogno = ?";
	private static final String DELETE = 
		"DELETE FROM blog where blogno = ?";
	private static final String UPDATE = 
		"UPDATE blog set memno=?, blogclsno=?, bgtitle=?, bgtext=?, authname=?, bgtime=? where blogno = ?";
	private static final String GET_ALL_MEMNO = 
			"SELECT * FROM blog where memno=? order by blogno desc";
	

	@Override
	public void insert(BlogVO blogVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, blogVO.getMemno());
			pstmt.setInt(2, blogVO.getBlogclsno());
			pstmt.setString(3, blogVO.getBgtitle());
			pstmt.setString(4, blogVO.getBgtext());
			pstmt.setString(5, blogVO.getAuthname());

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
	public void update(BlogVO blogVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, blogno);

			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
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
	
	public List<BlogVO> getBlogs_frommemno(Integer memno) {
		List<BlogVO> list = new ArrayList<BlogVO>();
		BlogVO blogVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_MEMNO);
			
			pstmt.setInt(1, memno);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
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