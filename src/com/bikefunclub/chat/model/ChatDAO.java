package com.bikefunclub.chat.model;

import java.util.*;
import java.sql.*;
import java.sql.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ChatDAO implements ChatDAO_interface {
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, chatVO.getSenderno());
			pstmt.setInt(2, chatVO.getReceiveno());
			pstmt.setString(3, chatVO.getChattext());
			pstmt.setTimestamp(4, chatVO.getChatdate());

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
	public void update(ChatVO chatVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, chatVO.getSenderno());
			pstmt.setInt(2, chatVO.getReceiveno());
			pstmt.setString(3, chatVO.getChattext());
			pstmt.setTimestamp(4, chatVO.getChatdate());
			pstmt.setInt(5, chatVO.getChatno());
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
	public void delete(Integer chatno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, chatno);

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
	public ChatVO findByPrimaryKey(Integer chatno) {

		ChatVO chatVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// chatVO 也稱為 Domain objects
				chatVO = new ChatVO();
				chatVO.setChatno(rs.getInt("chatno"));
				chatVO.setSenderno(rs.getInt("senderno"));
				chatVO.setReceiveno(rs.getInt("receiveno"));
				chatVO.setChattext(rs.getString("chattext"));
				chatVO.setChatdate(rs.getTimestamp("chatdate"));
				list.add(chatVO); // Store the row in the list
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
