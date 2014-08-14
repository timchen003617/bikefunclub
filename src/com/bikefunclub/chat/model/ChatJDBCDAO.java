package com.bikefunclub.chat.model;

import java.util.*;
import java.sql.*;
import java.sql.Date;

public class ChatJDBCDAO implements ChatDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String userid = "bikefunclub";
	String passwd = "bikefunclub";

	private static final String INSERT_STMT = "INSERT INTO chat (chatno,senderno,receiveno,chattext,chatdate) VALUES (chatseq.NEXTVAL, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT chatno,senderno,receiveno,chattext,to_char(chatdate,'YYYY-MM-DD HH24:MI:SS') chatdate FROM chat order by chatno";
	private static final String GET_ONE_STMT = "SELECT chatno,senderno,receiveno,chattext,to_char(chatdate,'YYYY-MM-DD HH24:MI:SS') chatdate FROM chat where chatno = ?";
	private static final String DELETE = "DELETE FROM chat where chatno = ?";
	private static final String UPDATE = "UPDATE chat set senderno= ?,receiveno= ?,chattext= ?,chatdate= ?  where chatno = ?";

	@Override
	public void insert(ChatVO chatVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, chatVO.getSenderno());
			pstmt.setInt(2, chatVO.getReceiveno());
			pstmt.setString(3, chatVO.getChattext());
			pstmt.setTimestamp(4, chatVO.getChatdate());

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
	public void update(ChatVO chatVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, chatVO.getSenderno());
			pstmt.setInt(2, chatVO.getReceiveno());
			pstmt.setString(3, chatVO.getChattext());
			pstmt.setTimestamp(4, chatVO.getChatdate());
			pstmt.setInt(5, chatVO.getChatno());
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
	public void delete(Integer chatno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, chatno);

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
	public ChatVO findByPrimaryKey(Integer chatno) {

		ChatVO chatVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, chatno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// ChatVO 也稱為 Domain objects
				chatVO = new ChatVO();
				chatVO.setChatno(rs.getInt("chatno"));
				chatVO.setSenderno(rs.getInt("senderno"));
				chatVO.setReceiveno(rs.getInt("receiveno"));
				chatVO.setChattext(rs.getString("chattext"));
				chatVO.setChatdate(rs.getTimestamp("chatdate"));
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
		return chatVO;
	}

	@Override
	public List<ChatVO> getAll() {
		List<ChatVO> list = new ArrayList<ChatVO>();
		ChatVO chatVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				chatVO = new ChatVO();
				chatVO.setChatno(rs.getInt("chatno"));
				chatVO.setSenderno(rs.getInt("senderno"));
				chatVO.setReceiveno(rs.getInt("receiveno"));
				chatVO.setChattext(rs.getString("chattext"));
				chatVO.setChatdate(rs.getTimestamp("chatdate"));
				list.add(chatVO); // Store the row in the list
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

		ChatJDBCDAO dao = new ChatJDBCDAO();
		Date date = new Date(System.currentTimeMillis());
		// 新增

		// ChatVO chatVO1 = new ChatVO();
		// chatVO1.setSenderno(1);
		// chatVO1.setReceiveno(2);
		// chatVO1.setChattext("hello,tim你好");
		// chatVO1.setChatdate(Timestamp.valueOf(new java.text.SimpleDateFormat(
		// "yyyy-MM-dd HH:mm:ss").format(date)));
		//
		// dao.insert(chatVO1);

		// 修改
		// ChatVO chatVO2 = new ChatVO();
		// chatVO2.setSenderno(1);
		// chatVO2.setReceiveno(2);
		// chatVO2.setChattext("tim你好");
		// chatVO2.setChatdate(Timestamp.valueOf(new java.text.SimpleDateFormat(
		// "yyyy-mm-dd HH:mm:ss").format(date)));
		// dao.update(chatVO2);

		// 刪除

		// dao.delete(2);

		// 查詢

		// ChatVO chatVO3 = dao.findByPrimaryKey(1);
		// System.out.println(chatVO3.getChatno() +",");
		// System.out.println(chatVO3.getSenderno() +",");
		// System.out.println(chatVO3.getReceiveno() +",");
		// System.out.println(chatVO3.getChattext() +",");
		// System.out.println(chatVO3.getChatdate() +",");
		// System.out.println("---------------------");

		// 查詢全部

//		List<ChatVO> list = dao.getAll();
//		for (ChatVO aChat : list) {
//			System.out.print(aChat.getChatno() + ",");
//			System.out.println(aChat.getChatno() + ",");
//			System.out.println(aChat.getSenderno() + ",");
//			System.out.println(aChat.getReceiveno() + ",");
//			System.out.println(aChat.getChattext() + ",");
//			System.out.println(aChat.getChatdate() + ",");
//			System.out.println();
//		}

	}
}
