package com.bikefunclub.rotcls.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RotclsDAO implements RotclsDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/YA801G2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO rotcls (rotclsno,rotclsname) VALUES (rotclsseq.NEXTVAL, ?)";
	private static final String GET_ALL_STMT = "SELECT rotclsno,rotclsname FROM rotcls order by rotclsno";
	private static final String GET_ONE_STMT = "SELECT rotclsno,rotclsname FROM rotcls where rotclsno = ?";
	private static final String DELETE = "DELETE FROM rotcls where rotclsno = ?";
	private static final String UPDATE = "UPDATE rotcls set rotclsname=? where rotclsno = ?";

	@Override
	public int insert(RotclsVO rotclsVO) {

		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, rotclsVO.getRotclsname());

			updateCount = pstmt.executeUpdate();

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
		return updateCount;
	}

	@Override
	public int update(RotclsVO rotclsVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, rotclsVO.getRotclsname());
			pstmt.setInt(2, rotclsVO.getRotclsno());

			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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
		return updateCount;
	}

	@Override
	public int delete(Integer rotclsno) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, rotclsno);

			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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
		return updateCount;
	}

	@Override
	public RotclsVO findByPrimaryKey(Integer rotclsno) {

		RotclsVO rotclsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, rotclsno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				rotclsVO = new RotclsVO();
				rotclsVO.setRotclsno(rs.getInt("rotclsno"));
				rotclsVO.setRotclsname(rs.getString("rotclsname"));

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
		return rotclsVO;
	}

	@Override
	public List<RotclsVO> getAll() {
		List<RotclsVO> list = new ArrayList<RotclsVO>();
		RotclsVO rotclsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				rotclsVO = new RotclsVO();
				rotclsVO.setRotclsno(rs.getInt("rotclsno"));
				rotclsVO.setRotclsname(rs.getString("rotclsname"));

				list.add(rotclsVO); // Store the row in the vector
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

	public static void main(String[] args) {

		RotclsJDBCDAO dao = new RotclsJDBCDAO();

		// 新增
//		 RotclsVO rotclsV01 = new RotclsVO();
//		 rotclsV01.setRotclsname("娛樂用");
//		 int updateCount_insert = dao.insert(rotclsV01);
//		 System.out.println(updateCount_insert);

		// 修改
//		 RotclsVO rotclsV02 = new RotclsVO();
//		 rotclsV02.setRotclsno(1);
//		 rotclsV02.setRotclsname("觀賞用");
//		 int updateCount_update = dao.update(rotclsV02);
//		 System.out.println(updateCount_update);

		// 刪除
//		 int updateCount_delete = dao.delete(3);
//		 System.out.println(updateCount_delete);

		// 查詢
//		RotclsVO rotclsV03 = dao.findByPrimaryKey(2);
//		System.out.print(rotclsV03.getRotclsno() + ",");
//		System.out.print(rotclsV03.getRotclsname() + ",");
//
//		System.out.println("---------------------");

		// 查詢
//		List<RotclsVO> list = dao.getAll();
//		for (RotclsVO aRotcls : list) {
//			System.out.print(aRotcls.getRotclsno() + ",");
//			System.out.print(aRotcls.getRotclsname() + ",");
//
//			System.out.println();
//		}
	}

}
