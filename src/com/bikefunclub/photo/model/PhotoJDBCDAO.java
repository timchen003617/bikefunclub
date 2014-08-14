package com.bikefunclub.photo.model;

import java.util.*;
import java.util.Date;
import java.sql.*;

import com.bikefunclub.gpalbum.model.*;


public class PhotoJDBCDAO implements PhotoDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String userid = "bikefunclub";
	String passwd = "bikefunclub";

	private static final String INSERT_STMT = 
		"INSERT INTO photo (photono,memno,phass,phfilename,phextname,phup,phfile) VALUES (photoseq.NEXTVAL, ?, ?, ?, ?, sysdate, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT photono,memno,phcoo,phass,phfilename,to_char(phup,'YYYY-MM-DD HH24:MI:SS') phup,phextname,phfile FROM photo order by photono";
	private static final String GET_ONE_STMT = 
		"SELECT photono,memno,phcoo,phass,phfilename,to_char(phup,'YYYY-MM-DD HH24:MI:SS') phup,phextname,phfile FROM photo where photono = ?";
	private static final String DELETE = 
		"DELETE FROM photo where photono = ?";
	private static final String DELETE_GPALBUM = 
			"DELETE FROM gpalbum where photono = ?";
	private static final String UPDATE = 
		"UPDATE photo set memno=?, phass=?, phfilename=?, phextname=?, phfile=? where photono = ?";
    
	
	@Override
	public void insert(PhotoVO photoVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, photoVO.getMemno());
			pstmt.setInt(2, photoVO.getPhass());
			pstmt.setString(3, photoVO.getPhfilename());
			pstmt.setString(4, photoVO.getPhextname());
			pstmt.setBytes(5, photoVO.getPhfile());

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
	public void update(PhotoVO photoVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, photoVO.getMemno());
			pstmt.setInt(3, photoVO.getPhass());
			pstmt.setString(4, photoVO.getPhfilename());
			pstmt.setString(5, photoVO.getPhextname());
			pstmt.setTimestamp(6, photoVO.getPhup());
			pstmt.setBytes(7, photoVO.getPhfile());
			pstmt.setInt(8, photoVO.getPhotono());

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

	@SuppressWarnings("resource")
	@Override
	public void delete(Integer photono) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			//先刪關係
			pstmt = con.prepareStatement(DELETE_GPALBUM);

			pstmt.setInt(1, photono);

			pstmt.executeUpdate();
			
			//再刪相片
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, photono);

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
	public PhotoVO findByPrimaryKey(Integer photono) {

		PhotoVO photoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, photono);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				photoVO = new PhotoVO();
				photoVO.setPhotono(rs.getInt("photono"));
				photoVO.setMemno(rs.getInt("memno"));
				photoVO.setPhcoo(rs.getString("phcoo"));
				photoVO.setPhass(rs.getInt("phass"));
				photoVO.setPhfilename(rs.getString("phfilename"));
				photoVO.setPhextname(rs.getString("phextname"));
				photoVO.setPhup(rs.getTimestamp("phup"));
				photoVO.setPhfile(rs.getBytes("phfile"));
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
		return photoVO;
	}

	@Override
	public List<PhotoVO> getAll() {
		List<PhotoVO> list = new ArrayList<PhotoVO>();
		PhotoVO photoVO = null;

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
				photoVO = new PhotoVO();
				photoVO.setPhotono(rs.getInt("photono"));
				photoVO.setMemno(rs.getInt("memno"));
				photoVO.setPhcoo(rs.getString("phcoo"));
				photoVO.setPhass(rs.getInt("phass"));
				photoVO.setPhfilename(rs.getString("phfilename"));
				photoVO.setPhextname(rs.getString("phextname"));
				photoVO.setPhup(rs.getTimestamp("phup"));
				photoVO.setPhfile(rs.getBytes("phfile"));
				list.add(photoVO);// Store the row in the list
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
	public void insertWithGpalbum(PhotoVO photoVO , Integer albno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		// 先新增部門
			String cols[] = {"PHOTONO"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);			
			pstmt.setInt(1, photoVO.getMemno());
			pstmt.setInt(2, photoVO.getPhass());
			pstmt.setString(3, photoVO.getPhfilename());
			pstmt.setString(4, photoVO.getPhextname());
			pstmt.setBytes(5, photoVO.getPhfile());
			pstmt.executeUpdate();
			//掘取對應的自增主鍵值
			String next_photono = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_photono = rs.getString(1);
				System.out.println("自增主鍵值= " + next_photono +"(剛新增成功的相片編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增相片所屬相簿
			GpalbumJDBCDAO dao = new GpalbumJDBCDAO();
			dao.insert2(albno , new Integer(next_photono),con);

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-photo");
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	
	

	public static void main(String[] args) {

		PhotoJDBCDAO dao = new PhotoJDBCDAO();
		
		PhotoVO photoVO = new PhotoVO();
		byte byte1[] = new byte[1024];
		photoVO.setMemno(1);
		photoVO.setPhass(1);
		photoVO.setPhfilename("1234");
		photoVO.setPhextname("jpg");
		photoVO.setPhfile(byte1);
		
		GpalbumVO gpalbumVO = new GpalbumVO();
		gpalbumVO.setAlbno(2);
		gpalbumVO.setPhotono(1);
		
		dao.insertWithGpalbum(photoVO , gpalbumVO.getAlbno());
//		// 新增
//		PhotoVO photoVO1 = new PhotoVO();
//		byte byte1[] = new byte[1024];
//		photoVO1.setMemno(1);
//		photoVO1.setPhass(1);
//		photoVO1.setPhfilename("1234");
//		photoVO1.setPhextname("jpg");
//		photoVO1.setPhfile(byte1);
//		dao.insert(photoVO1);
//
//		// 修改
//		PhotoVO photoVO2 = new PhotoVO();
//		byte[] byte2 = new byte[1024];
//		photoVO2.setPhotono(5);
//		photoVO2.setMemno(1);
//		photoVO2.setPhass(1);
//		photoVO2.setPhfilename("hththhgh");
//		photoVO2.setPhextname("jpg");
//		photoVO2.setPhfile(byte2);
//		dao.update(photoVO2);
//
//		// 刪除
//		dao.delete(5);
//
//		// 查詢
//		PhotoVO photoVO3 = dao.findByPrimaryKey(1);
//		System.out.print(photoVO3.getPhotono() + ",");
//		System.out.print(photoVO3.getMemno() + ",");
//		System.out.print(photoVO3.getPhcoo() + ",");
//		System.out.print(photoVO3.getPhass() + ",");
//		System.out.print(photoVO3.getPhfilename() + ",");
//		System.out.print(photoVO3.getPhextname() + ",");
//		System.out.println(photoVO3.getPhup() + ",");
//		System.out.println(photoVO3.getPhfile());
//		System.out.println("---------------------");
//
//		// 查詢
//		List<PhotoVO> list = dao.getAll();
//		for (PhotoVO photo : list) {
//			System.out.print(photo.getPhotono() + ",");
//			System.out.print(photo.getMemno() + ",");
//			System.out.print(photo.getPhcoo() + ",");
//			System.out.print(photo.getPhass() + ",");
//			System.out.print(photo.getPhfilename() + ",");
//			System.out.print(photo.getPhextname() + ",");
//			System.out.print(photo.getPhup() + ",");
//			System.out.print(photo.getPhfile());
//			System.out.println();
//		}
//	}
	}
}