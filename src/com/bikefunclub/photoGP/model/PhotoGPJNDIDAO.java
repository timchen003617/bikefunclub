package com.bikefunclub.photoGP.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class PhotoGPJNDIDAO implements PhotoGPDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/YA801G2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO photogp (gpno,photono) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT gpno,photono FROM photogp order by gpno";
	private static final String GET_ONE_STMT = "SELECT gpno,photono FROM photogp where gpno = ? and photono=?";
	private static final String DELETE = "DELETE FROM photogp where gpno = ? and photono=?";
	private static final String UPDATE = "UPDATE photogp set photono=? where gpno = ?";

	@Override
	public int insert(PhotoGPVO photoGPVO) {

		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, photoGPVO.getGpno());
			pstmt.setInt(2, photoGPVO.getPhotono());
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
	public int update(PhotoGPVO photoGPVO) {
	
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, photoGPVO.getPhotono());
			pstmt.setInt(2, photoGPVO.getGpno());

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
	public int delete(Integer gpno,Integer photono){
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, gpno);
			pstmt.setInt(2, photono);

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
	public PhotoGPVO findByPrimaryKey(Integer gpno,Integer photono) {

		PhotoGPVO photoGPVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, gpno);
			pstmt.setInt(2, photono);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				photoGPVO = new PhotoGPVO();
				photoGPVO.setGpno(rs.getInt("gpno"));
				photoGPVO.setPhotono(rs.getInt("photono"));

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
		return photoGPVO;
	}

	@Override
	public List<PhotoGPVO> getAll() {
		List<PhotoGPVO> list = new ArrayList<PhotoGPVO>();
		PhotoGPVO photoGPVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				photoGPVO = new PhotoGPVO();
				photoGPVO.setGpno(rs.getInt("gpno"));
				photoGPVO.setPhotono(rs.getInt("photono"));

				list.add(photoGPVO); // Store the row in the vector
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

		PhotoGPJDBCDAO dao = new PhotoGPJDBCDAO();

		// 新增
//		 PhotoGPVO photoGPVO1 = new PhotoGPVO();
//		 photoGPVO1.setGpno(5);
//		 photoGPVO1.setPhotono(20);
//		 int updateCount_insert = dao.insert(photoGPVO1);
//		 System.out.println(updateCount_insert);

		// 修改
//		 PhotoGPVO photoGPVO2 = new PhotoGPVO();
//		 photoGPVO2.setGpno(1);
//		 photoGPVO2.setPhotono(20);
//		 int updateCount_update = dao.update(photoGPVO2);
//		 System.out.println(updateCount_update);

		// 刪除
//		 int updateCount_delete = dao.delete(3,1);
//		 System.out.println(updateCount_delete);

		// 查詢
//		PhotoGPVO photoGPVO3 = dao.findByPrimaryKey(2,1);
//		System.out.print(photoGPVO3.getGpno() + ",");
//		System.out.print(photoGPVO3.getPhotono() + ",");
//
//		System.out.println("---------------------");

		// 查詢
//		List<PhotoGPVO> list = dao.getAll();
//		for (PhotoGPVO aPhotoGP : list) {
//			System.out.print(aPhotoGP.getGpno() + ",");
//			System.out.print(aPhotoGP.getPhotono() + ",");
//
//			System.out.println();
//		}
	}

}
