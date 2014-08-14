package com.bikefunclub.albcls.model;

import java.util.*;
import java.sql.*;

import com.bikefunclub.album.model.AlbumVO;

public class AlbclsJDBCDAO implements AlbclsDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String userid = "bikefunclub";
	String passwd = "bikefunclub";

	private static final String INSERT_STMT = 
		"INSERT INTO albcls (albclsno,albclsname) VALUES (albclsseq.NEXTVAL, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT albclsno,albclsname FROM albcls order by albclsno";
	private static final String GET_ONE_STMT = 
		"SELECT albclsno,albclsname FROM albcls where albclsno = ?";
	private static final String DELETE = 
		"DELETE FROM albcls where albclsno = ? ";
	private static final String UPDATE = 
		"UPDATE albcls set albclsname=? where albclsno = ?";
	private static final String DELETE_GPALBUM = 
			"DELETE FROM gpalbum where photono = ?";

	@Override
	public void insert(AlbclsVO albclsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, albclsVO.getAlbclsname());

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
	public void update(AlbclsVO albclsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, albclsVO.getAlbclsname());
			pstmt.setInt(2, albclsVO.getAlbclsno());

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
	public void delete(Integer albclsno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, albclsno);

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
	public AlbclsVO findByPrimaryKey(Integer albclsno) {

		AlbclsVO albclsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, albclsno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				albclsVO = new AlbclsVO();
				albclsVO.setAlbclsno(rs.getInt("albclsno"));
				albclsVO.setAlbclsname(rs.getString("albclsname"));
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
		return albclsVO;
	}

	@Override
	public List<AlbclsVO> getAll() {
		List<AlbclsVO> list = new ArrayList<AlbclsVO>();
		AlbclsVO albclsVO = null;

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
				albclsVO = new AlbclsVO();
				albclsVO.setAlbclsno(rs.getInt("albclsno"));
				albclsVO.setAlbclsname(rs.getString("albclsname"));
				list.add(albclsVO); // Store the row in the list
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

		AlbclsJDBCDAO dao = new AlbclsJDBCDAO();

//		// 新增
//		AlbclsVO albclsVO1 = new AlbclsVO();
//		albclsVO1.setAlbclsname("片片");
//		dao.insert(albclsVO1);
//
//		// 修改
//		AlbclsVO albclsVO2 = new AlbclsVO();
//		albclsVO2.setAlbclsno(12);
//		albclsVO2.setAlbclsname("一片片");
//		dao.update(albclsVO2);
//
//		// 刪除
//		dao.delete(12);
//
//		// 查詢
//		AlbclsVO albclsVO3 = dao.findByPrimaryKey(1);
//		System.out.print(albclsVO3.getAlbclsno() + ",");
//		System.out.print(albclsVO3.getAlbclsname());
//
//		// 查詢
//		List<AlbclsVO> list = dao.getAll();
//		for (AlbclsVO albcls : list) {
//			System.out.print(albcls.getAlbclsno() + ",");
//			System.out.print(albcls.getAlbclsname());
//			System.out.println();
//		}
	}

	@Override
	public List<AlbumVO> findByAlbum(Integer albclsno) {
		// TODO Auto-generated method stub
		return null;
	}
}