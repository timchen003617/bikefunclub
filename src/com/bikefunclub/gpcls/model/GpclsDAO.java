package com.bikefunclub.gpcls.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class GpclsDAO implements GpclsDAO_interface {
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
		"INSERT INTO gpcls (gpclsno,gpclsname) VALUES (gpclsseq.NEXTVAL, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT gpclsno,gpclsname FROM gpcls order by gpclsno";
	private static final String GET_ONE_STMT = 
		"SELECT gpclsno,gpclsname FROM gpcls where gpclsno = ?";
	private static final String DELETE = 
		"DELETE FROM gpcls where gpclsno = ?";
	private static final String UPDATE = 
		"UPDATE gpcls set gpclsname=? where gpclsno = ?";

	@Override
	public int insert(GpclsVO gpclsVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			
			pstmt.setString(1, gpclsVO.getGpclsname());
			

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
	public int update(GpclsVO gpclsVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, gpclsVO.getGpclsname());
			pstmt.setInt(2, gpclsVO.getGpclsno());

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
	public int delete(Integer gpcls_no) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, gpcls_no);
			
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
	public GpclsVO findByPrimaryKey(Integer gpclsno) {

		GpclsVO gpclsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, gpclsno);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				gpclsVO = new GpclsVO();
				gpclsVO.setGpclsno(rs.getInt("gpclsno"));
				gpclsVO.setGpclsname(rs.getString("gpclsname"));
				
			}

			// Handle any driver errors
		}  catch (SQLException se) {
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
		return gpclsVO;
	}

	@Override
	public List<GpclsVO> getAll() {
		List<GpclsVO> list = new ArrayList<GpclsVO>();
		GpclsVO gpclsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				gpclsVO = new GpclsVO();
				gpclsVO.setGpclsno(rs.getInt("gpclsno"));
				gpclsVO.setGpclsname(rs.getString("gpclsname"));
				
				list.add(gpclsVO); // Store the row in the vector
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

		GpclsJDBCDAO dao = new GpclsJDBCDAO();

		 //新增
//		 GpclsVO gpclsVO1 = new GpclsVO();
//		 gpclsVO1.setGpclsname("娛樂用");
//		 int updateCount_insert = dao.insert(gpclsVO1);
//		 System.out.println(updateCount_insert);
				

		 //修改
//		 GpclsVO gpclsVO2 = new GpclsVO();
//		 gpclsVO2.setGpclsno(1);
//		 gpclsVO2.setGpclsname("觀賞用");
//		 int updateCount_update = dao.update(gpclsVO2);
//		 System.out.println(updateCount_update);
				

		 //刪除
//		 int updateCount_delete = dao.delete(3);
//		 System.out.println(updateCount_delete);

		// 查詢
//		GpclsVO gpclsVO3 = dao.findByPrimaryKey(2);
//		System.out.print(gpclsVO3.getGpclsno() + ",");
//		System.out.print(gpclsVO3.getGpclsname() + ",");
//		
//		System.out.println("---------------------");

		// 查詢
//		List<GpclsVO> list = dao.getAll();
//		for (GpclsVO aGpcls : list) {
//			System.out.print(aGpcls.getGpclsno() + ",");
//			System.out.print(aGpcls.getGpclsname() + ",");
//			
//			System.out.println();
//		}
	}

	
}
