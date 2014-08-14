package com.bikefunclub.photorot.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhotoRootJDBCDAO implements PhotoRootDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String userid = "bikefunclub";
	String passwd = "bikefunclub";

	private static final String INSERT_STMT = "INSERT INTO photoroot (riderrecordno,photono) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT riderrecordno,photono FROM photoroot order by riderrecordno";
	private static final String GET_ONE_STMT = "SELECT riderrecordno,photono FROM photoroot where riderrecordno = ? and photono=?";
	private static final String DELETE = "DELETE FROM photoroot where riderrecordno = ? and photono=?";
	private static final String UPDATE = "UPDATE photoroot set photono=? where riderrecordno = ?";

	@Override
	public int insert(PhotoRootVO photoRootVO) {

		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, photoRootVO.getRiderrecordno());
			pstmt.setInt(2, photoRootVO.getPhotono());
			updateCount = pstmt.executeUpdate();

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
		return updateCount;
	}

	@Override
	public int update(PhotoRootVO photoRootVO) {
	
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, photoRootVO.getPhotono());
			pstmt.setInt(2, photoRootVO.getRiderrecordno());

			updateCount = pstmt.executeUpdate();

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
		return updateCount;
	}

	@Override
	public int delete(Integer photono,Integer riderrecordno){
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, riderrecordno);
			pstmt.setInt(2, photono);

			updateCount = pstmt.executeUpdate();

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
		return updateCount;
	}

	@Override
	public PhotoRootVO findByPrimaryKey(Integer photono,Integer riderrecordno) {

		PhotoRootVO photoRootVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, riderrecordno);
			pstmt.setInt(2, photono);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				photoRootVO = new PhotoRootVO();
				photoRootVO.setRiderrecordno(rs.getInt("riderrecordno"));
				photoRootVO.setPhotono(rs.getInt("photono"));

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
		return photoRootVO;
	}

	@Override
	public List<PhotoRootVO> getAll() {
		List<PhotoRootVO> list = new ArrayList<PhotoRootVO>();
		PhotoRootVO photoRootVO = null;

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
				photoRootVO = new PhotoRootVO();
				photoRootVO.setRiderrecordno(rs.getInt("riderrecordno"));
				photoRootVO.setPhotono(rs.getInt("photono"));

				list.add(photoRootVO); // Store the row in the vector
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

		PhotoRootJDBCDAO dao = new PhotoRootJDBCDAO();

		// 新增
//		 PhotoRootVO photoRootVO1 = new PhotoRootVO();
//		 photoRootVO1.setRiderrecordno(5);
//		 photoRootVO1.setPhotono(20);
//		 int updateCount_insert = dao.insert(photoRootVO1);
//		 System.out.println(updateCount_insert);

		

		// 刪除
//		 int updateCount_delete = dao.delete(2,2);
//		 System.out.println(updateCount_delete);

		// 查詢
		PhotoRootVO photoRootVO3 = dao.findByPrimaryKey(2,1);
		System.out.print(photoRootVO3.getRiderrecordno() + ",");
		System.out.print(photoRootVO3.getPhotono() + ",");

		System.out.println("---------------------");

		// 查詢
		List<PhotoRootVO> list = dao.getAll();
		for (PhotoRootVO aPhotoRoot : list) {
			System.out.print(aPhotoRoot.getRiderrecordno() + ",");
			System.out.print(aPhotoRoot.getPhotono() + ",");

			System.out.println();
		}
	}

}
