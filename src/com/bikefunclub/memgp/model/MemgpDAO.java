package com.bikefunclub.memgp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemgpDAO implements MemgpDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/YA801G2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}	
	private static final String INSERT_STMT = "INSERT INTO Memgp (memno,gpno,adddate) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM Memgp";
	private static final String GET_ONE_STMT = "SELECT * FROM Memgp where memno = ? and gpno=?";
	private static final String GET_GP_STMT = "SELECT * FROM Memgp inner join member on member.memno = Memgp.memno where gpno=?";
	private static final String DELETE = "DELETE FROM Memgp where memno = ? and gpno=?";	

	@Override
	public void insert(MemgpVO memgpVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, memgpVO.getMemno());
			pstmt.setInt(2, memgpVO.getGpno());
			pstmt.setTimestamp(3,memgpVO.getAdddate());

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
	public void delete(Integer memno, Integer gpno) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memno);
			pstmt.setInt(2, gpno);

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
	public MemgpVO findByPrimaryKey(Integer memno, Integer gpno) {
		// TODO Auto-generated method stub
		MemgpVO memgpVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, memno);
			pstmt.setInt(2, gpno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				memgpVO = new MemgpVO();
				memgpVO.setMemno(rs.getInt("memno"));
				memgpVO.setGpno(rs.getInt("gpno"));
				memgpVO.setAdddate(rs.getTimestamp("adddate"));
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
		return memgpVO;
	}

	@Override
	public List<MemgpVO> getAll() {
		// TODO Auto-generated method stub
		List<MemgpVO> list = new ArrayList<MemgpVO>();
		MemgpVO memgpVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				memgpVO = new MemgpVO();
				memgpVO.setMemno(rs.getInt("memno"));
				memgpVO.setGpno(rs.getInt("gpno"));
				memgpVO.setAdddate(rs.getTimestamp("adddate"));
				list.add(memgpVO); // Store the row in the list
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

	@Override
	public List<MemgpVO> findBygpno(Integer gpno) {
		// TODO Auto-generated method stub
		List<MemgpVO> list = new ArrayList<MemgpVO>();
		MemgpVO memgpVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_GP_STMT);
			pstmt.setInt(1, gpno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memgpVO = new MemgpVO();
				memgpVO.setMemno(rs.getInt("memno"));
				memgpVO.setGpno(rs.getInt("gpno"));
				memgpVO.setAdddate(rs.getTimestamp("adddate"));
				memgpVO.setMemname(rs.getString("memname"));
				list.add(memgpVO); // Store the row in the list
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
