package com.bikefunclub.album.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.bikefunclub.photo.model.PhotoVO;

public class AlbumDAO implements AlbumDAO_interface {

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

	private static final String INSERT_STMT = 
			"INSERT INTO album (albno,memno,albclsno,authname,albtitle,albdesc,albtime) VALUES (albumseq.NEXTVAL, ?, ?, ?, ?, ?, sysdate)";
		private static final String GET_ALL_STMT = 
			"SELECT albno,memno,albclsno,authname,albtitle,albdesc,to_char(albtime,'YYYY-MM-DD HH24:MI:SS')albtime FROM album order by albno";
		private static final String GET_ONE_STMT = 
			"SELECT albno,memno,albclsno,authname,albtitle,albdesc,to_char(albtime,'YYYY-MM-DD HH24:MI:SS')albtime FROM album where albno = ?";
		private static final String DELETE = 
			"DELETE FROM album where albno = ?";
		private static final String UPDATE = 
			"UPDATE album set memno=?, albclsno=?, authname=?, albtitle=?, albdesc=?, albtime=? where albno = ?";
		private static final String GET_ALBNO_TO_GPALBUM = 
			"select * from photo where photono = any(select photono from gpalbum where albno=?)";
		private static final String DELETE_GPALBUM = 
				"DELETE FROM gpalbum where photono = ?";
		

	@Override
	public void insert(AlbumVO albumVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, albumVO.getMemno());
			pstmt.setInt(2, albumVO.getAlbclsno());
			pstmt.setString(3, albumVO.getAuthname());
			pstmt.setString(4, albumVO.getAlbtitle());
			pstmt.setString(5, albumVO.getAlbdesc());

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
	public void update(AlbumVO albumVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, albumVO.getMemno());
			pstmt.setInt(2, albumVO.getAlbclsno());
			pstmt.setString(3, albumVO.getAuthname());
			pstmt.setString(4, albumVO.getAlbtitle());
			pstmt.setString(5, albumVO.getAlbdesc());
			pstmt.setTimestamp(6, albumVO.getAlbtime());
			pstmt.setInt(7, albumVO.getAlbno());

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
	public void delete(Integer albno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, albno);

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
	public AlbumVO findByPrimaryKey(Integer albno) {

		AlbumVO albumVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, albno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				albumVO = new AlbumVO();
				albumVO.setAlbno(rs.getInt("albno"));
				albumVO.setMemno(rs.getInt("memno"));
				albumVO.setAlbclsno(rs.getInt("albclsno"));
				albumVO.setAuthname(rs.getString("authname"));
				albumVO.setAlbtitle(rs.getString("albtitle"));
				albumVO.setAlbdesc(rs.getString("albdesc"));
				albumVO.setAlbtime(rs.getTimestamp("albtime"));
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
		return albumVO;
	}

	@Override
	public List<AlbumVO> getAll() {
		List<AlbumVO> list = new ArrayList<AlbumVO>();
		AlbumVO albumVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				albumVO = new AlbumVO();
				albumVO.setAlbno(rs.getInt("albno"));
				albumVO.setMemno(rs.getInt("memno"));
				albumVO.setAlbclsno(rs.getInt("albclsno"));
				albumVO.setAuthname(rs.getString("authname"));
				albumVO.setAlbtitle(rs.getString("albtitle"));
				albumVO.setAlbdesc(rs.getString("albdesc"));
				albumVO.setAlbtime(rs.getTimestamp("albtime"));
				list.add(albumVO); // Store the row in the list
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
	public List<PhotoVO> getAlbno(Integer albno) {

		List<PhotoVO> list = new ArrayList<PhotoVO>();
		PhotoVO photoVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALBNO_TO_GPALBUM);

			pstmt.setInt(1, albno);

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
				list.add(photoVO);// Store the row in the list
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
	public void deleteGpalbum(Integer photono) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_GPALBUM);

			pstmt.setInt(1, photono);

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
}