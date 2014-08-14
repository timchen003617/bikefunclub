package com.bikefunclub.gpalbum.model;

import java.util.*;
import java.sql.*;


public class GpalbumJDBCDAO implements GpalbumDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String userid = "bikefunclub";
	String passwd = "bikefunclub";

	private static final String INSERT_STMT = 
		"INSERT INTO gpalbum (albno,photono) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM gpalbum order by albno";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM gpalbum where albno = ?";
	private static final String DELETE = 
		"DELETE FROM gpalbum where albno = ? and photono = ? ";

	@Override
	public void insert(GpalbumVO gpalbumVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, gpalbumVO.getAlbno());
			pstmt.setInt(2, gpalbumVO.getPhotono());

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
	public void delete(Integer albno,Integer photono) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, albno);
			pstmt.setInt(2, photono);

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
	public GpalbumVO findByAlbno(Integer albno) {

		GpalbumVO gpalbumVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, albno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				gpalbumVO = new GpalbumVO();
				gpalbumVO.setAlbno(rs.getInt("albno"));
				gpalbumVO.setPhotono(rs.getInt("photono"));
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
		return gpalbumVO;
	}

	@Override
	public List<GpalbumVO> getAll() {
		List<GpalbumVO> list = new ArrayList<GpalbumVO>();
		GpalbumVO gpalbumVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				gpalbumVO = new GpalbumVO();
				gpalbumVO.setAlbno(rs.getInt("albno"));
				gpalbumVO.setPhotono(rs.getInt("photono"));
				list.add(gpalbumVO); // Store the row in the list
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
	public void insert2 (Integer albno , Integer photono , Connection con) {

		PreparedStatement pstmt = null;

		try {

     		pstmt = con.prepareStatement(INSERT_STMT);

     		pstmt.setInt(1, albno);
			pstmt.setInt(2, photono);

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-gpalbum");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
		}
	}

	public static void main(String[] args) {

		GpalbumJDBCDAO dao = new GpalbumJDBCDAO();			
//		// 新增
//		GpalbumVO gpalbumVO1 = new GpalbumVO();
//		gpalbumVO1.setAlbno(1);
//		gpalbumVO1.setPhotono(2);
//		dao.insert(gpalbumVO1);
//
//		// 刪除
//		dao.delete(2,2);
//
//		// 查詢
//		GpalbumVO gpalbumVO2 = dao.findByPrimaryKey(1,2);
//		System.out.print(gpalbumVO2.getAlbno() + ",");
//		System.out.print(gpalbumVO2.getPhotono());
//		System.out.println("---------------------");
//
//		// 查詢
//		List<GpalbumVO> list = dao.getAll();
//		for (GpalbumVO gpalbum : list) {
//			System.out.print(gpalbum.getAlbno() + ",");
//			System.out.print(gpalbum.getPhotono());
//			System.out.println();
//		}
	}
}