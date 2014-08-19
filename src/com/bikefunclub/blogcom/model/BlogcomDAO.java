package com.bikefunclub.blogcom.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BlogcomDAO implements BlogcomDAO_interface {

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
			"INSERT INTO blogcom (bgcomno, blogno, memno, bgcomtext, bgcomtime) VALUES (blogcomseq.NEXTVAL, ?, ?, ?, sysdate)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM blogcom order by bgcomno";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM blogcom where bgcomno = ?";
		private static final String DELETE = 
			"DELETE FROM blogcom where bgcomno = ?";
		private static final String DELETE_FROMFRONT = 
				"DELETE FROM blogcom where bgcomno = ?";
		private static final String UPDATE = 
			"UPDATE blogcom set blogno=?, memno=?, bgcomtext=?, bgcomtime=? where bgcomno = ?";

	@Override
	public void insert(BlogcomVO blogcomVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, blogcomVO.getBlogno());
			pstmt.setInt(2, blogcomVO.getMemno());
			pstmt.setString(3, blogcomVO.getBgcomtext());

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
	public void update(BlogcomVO blogcomVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, blogcomVO.getBlogno());
			pstmt.setInt(2, blogcomVO.getMemno());
			pstmt.setString(3, blogcomVO.getBgcomtext());
			pstmt.setTimestamp(4, blogcomVO.getBgcomtime());
			pstmt.setInt(5, blogcomVO.getBgcomno());

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
	public void delete(Integer bgcomno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, bgcomno);

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
	
	public void delete_fromFront(Integer bgcomno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_FROMFRONT);

			pstmt.setInt(1, bgcomno);

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
	public BlogcomVO findByPrimaryKey(Integer bgcomno) {

		BlogcomVO blogcomVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
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