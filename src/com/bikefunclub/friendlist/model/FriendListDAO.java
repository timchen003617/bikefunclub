package com.bikefunclub.friendlist.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class FriendListDAO implements FriendListDAO_interface {

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
	private static final String INSERT_STMT = "INSERT INTO friendlist (memno,youno,isblack,isfriend) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM friendlist order by memno,youno";
	private static final String GET_ONE_STMT = "SELECT * FROM friendlist where memno = ? and youno = ?";
	private static final String GET_ONE_FRINAME = "SELECT * FROM friendlist where memno =? and youno in(select memno from member where memname like ?)";
	private static final String GET_ALLFRIEND_STMT = "SELECT * FROM friendlist where memno = ? ";
	private static final String DELETE = "DELETE FROM friendlist where memno = ? and youno = ?";
	private static final String UPDATE = "UPDATE friendlist set memno= ? youno= ? isblack= ? isfriend= ? where memno = ? and youno = ?";

	@Override
	public void insert(FriendListVO friendListvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, friendListvo.getMemno());
			pstmt.setInt(2, friendListvo.getYouno());
			pstmt.setString(3, friendListvo.getIsblack());
			pstmt.setString(4, friendListvo.getIsfriend());

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
	public void update(FriendListVO friendListvo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, friendListvo.getMemno());
			pstmt.setInt(2, friendListvo.getYouno());
			pstmt.setString(3, friendListvo.getIsblack());
			pstmt.setString(4, friendListvo.getIsfriend());
			pstmt.setInt(5, friendListvo.getMemno());
			pstmt.setInt(6, friendListvo.getYouno());
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
	public void delete(Integer memno, Integer youno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memno);
			pstmt.setInt(2, youno);
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
	public FriendListVO findByPrimaryKey(Integer memno, Integer youno) {

		FriendListVO friendListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, memno);
			pstmt.setInt(2, youno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// friendListvo 也稱為 Domain objects
				friendListVO = new FriendListVO();
				friendListVO.setMemno(rs.getInt("memno"));
				friendListVO.setYouno(rs.getInt("youno"));
				friendListVO.setIsblack(rs.getString("isblack"));
				friendListVO.setIsfriend(rs.getString("isfriend"));
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
		return friendListVO;
	}

	@Override
	public List<FriendListVO> findAllFriends(Integer memno) {

		List<FriendListVO> list = new ArrayList<FriendListVO>();
		FriendListVO friendListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALLFRIEND_STMT);
			pstmt.setInt(1, memno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				friendListVO = new FriendListVO();
				friendListVO.setMemno(rs.getInt("memno"));
				friendListVO.setYouno(rs.getInt("youno"));
				friendListVO.setIsblack(rs.getString("isblack"));
				friendListVO.setIsfriend(rs.getString("isfriend"));
				list.add(friendListVO); // Store the row in the list
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

	@Override
	public List<FriendListVO> findByfriname(Integer memno, String friname) {
		List<FriendListVO> list = new ArrayList<FriendListVO>();
		FriendListVO friendListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_FRINAME);

			pstmt.setInt(1, memno);
			pstmt.setString(2, friname);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				friendListVO = new FriendListVO();
				friendListVO.setMemno(rs.getInt("memno"));
				friendListVO.setYouno(rs.getInt("youno"));
				friendListVO.setIsblack(rs.getString("isblack"));
				friendListVO.setIsfriend(rs.getString("isfriend"));
				list.add(friendListVO);
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
	
	@Override
	public List<FriendListVO> getAll() {
		List<FriendListVO> list = new ArrayList<FriendListVO>();
		FriendListVO friendListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				friendListVO = new FriendListVO();
				friendListVO.setMemno(rs.getInt("memno"));
				friendListVO.setYouno(rs.getInt("youno"));
				friendListVO.setIsblack(rs.getString("isblack"));
				friendListVO.setIsfriend(rs.getString("isfriend"));
				list.add(friendListVO); // Store the row in the list
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
