package com.bikefunclub.friendlist.model;

import java.util.*;
import java.sql.*;

public class FriendListJDBCDAO implements FriendListDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String userid = "bikefunclub";
	String passwd = "bikefunclub";

	private static final String INSERT_STMT = "INSERT INTO friendlist (memno,youno,isblack,isfriend) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM friendlist order by memno,youno";
	private static final String GET_ONE_STMT = "SELECT * FROM friendlist where memno = ? and youno = ?";
	private static final String GET_ONE_FRINAME = "SELECT * FROM friendlist where memno =? and youno in(select memno from member where memname like ?)";
	private static final String GET_ALLFRIEND_STMT = "SELECT * FROM friendlist where memno = ?";
	private static final String DELETE = "DELETE FROM friendlist where memno = ? and youno = ?";
	private static final String UPDATE = "UPDATE friendlist set memno= ? youno= ? isblack= ? isfriend= ? where memno = ? and youno = ?";

	@Override
	public void insert(FriendListVO friendListvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, friendListvo.getMemno());
			pstmt.setInt(2, friendListvo.getYouno());
			pstmt.setString(3, friendListvo.getIsblack());
			pstmt.setString(4, friendListvo.getIsfriend());

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
	public void update(FriendListVO friendListvo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, friendListvo.getMemno());
			pstmt.setInt(2, friendListvo.getYouno());
			pstmt.setString(3, friendListvo.getIsblack());
			pstmt.setString(4, friendListvo.getIsfriend());
			pstmt.setInt(5, friendListvo.getMemno());
			pstmt.setInt(6, friendListvo.getYouno());
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
	public void delete(Integer memno, Integer youno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memno);
			pstmt.setInt(2, youno);
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
	public FriendListVO findByPrimaryKey(Integer memno, Integer youno) {

		FriendListVO friendListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public List<FriendListVO> findByfriname(Integer memno, String friname) {
		List<FriendListVO> list = new ArrayList<FriendListVO>();
		FriendListVO friendListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public List<FriendListVO> getAll() {
		List<FriendListVO> list = new ArrayList<FriendListVO>();
		FriendListVO friendListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

	public static void main(String args[]) {
		FriendListJDBCDAO dao = new FriendListJDBCDAO();
		// 新增
		// FriendListVO friendListvo1 = new FriendListVO();
		// friendListvo1.setMemno(1);
		// friendListvo1.setYouno(2);
		// friendListvo1.setIsblack("N");
		// friendListvo1.setIsfriend("Y");
		// dao.insert(friendListVo1);
		// friendListvo1.setMemno(2);
		// friendListvo1.setYouno(1);
		// friendListvo1.setIsblack("N");
		// friendListvo1.setIsfriend("Y");
		// dao.insert(friendListvo1);

		// 修改
		// FriendListVO friendListvo2 = new FriendListVO();
		// friendListvo2.setMemno(1);
		// friendListvo2.setYouno(2);
		// friendListvo2.setIsblack("Y");
		// friendListvo2.setIsfriend("Y");
		// dao.update(friendListvo2);
		// 刪除
		// FriendListVO friendListvo3 = new FriendListVO();
		// friendListvo3.setMemno(1);
		// friendListvo3.setYouno(2);
		// friendListvo3.setIsblack("Y");
		// friendListvo3.setIsfriend("Y");
		// dao.update(friendListvo3);

		// 查詢單一會員

		// FriendListVO friendListvo4 = dao.findByPrimaryKey(1,2);
		// System.out.println(friendListvo4.getMemno() +",");
		// System.out.println(friendListvo4.getYouno() +",");
		// System.out.println(friendListvo4.getIsblack() +",");
		// System.out.println(friendListvo4.getIsfriend() +",");
		// System.out.println("---------------------");

		// 帶入好友姓名查詢出會員好友
//		List<FriendListVO> list = dao.findByfriname(2, "peter%");
//		 for (FriendListVO friendList : list) {
//		 System.out.println(friendList.getMemno() + ",");
//		 System.out.println(friendList.getYouno() + ",");
//		 System.out.println(friendList.getIsblack() + ",");
//		 System.out.println(friendList.getIsfriend() + ",");
//		 System.out.println();
//		 }
		
		// 查詢會員所有好友
		// List<FriendListVO> list = dao.findAllFriends(1);
		// for (FriendListVO friendList : list) {
		// System.out.println(friendList.getMemno() + ",");
		// System.out.println(friendList.getYouno() + ",");
		// System.out.println(friendList.getIsblack() + ",");
		// System.out.println(friendList.getIsfriend() + ",");
		// System.out.println();
		// }
		// 查詢全部
		// List<FriendListVO> list = dao.getAll();
		// for (FriendListVO afriendList : list) {
		// System.out.println(afriendList.getMemno() +",");
		// System.out.println(afriendList.getYouno() +",");
		// System.out.println(afriendList.getIsblack() +",");
		// System.out.println(afriendList.getIsfriend() +",");
		// System.out.println();
		// }
	}
}
