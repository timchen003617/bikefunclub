package com.bikefunclub.album.model;

import java.util.*;
import java.util.Date;
import java.sql.*;

import com.bikefunclub.photo.model.PhotoVO;

public class AlbumJDBCDAO implements AlbumDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String userid = "bikefunclub";
	String passwd = "bikefunclub";

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
	//取得該分類所有相簿;新增相簿後,轉送到該分類所有相簿
	private static final String GET_ALBCLSNO_TO_ALBUM = 
			"SELECT * from album where albclsno=? order by albno desc";
	//查詢會員所屬相簿
	private static final String GET_ALBMEM_TO_ALBUM = "select * from album where memno=?";
	//查詢會員所屬相簿分類
	private static final String GET_ALBMEMBYCLS_TO_ALBUM = "select * from album where memno=? and albclsno=?";

	@Override
	public void insert(AlbumVO albumVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, albumVO.getMemno());
			pstmt.setInt(2, albumVO.getAlbclsno());
			pstmt.setString(3, albumVO.getAuthname());
			pstmt.setString(4, albumVO.getAlbtitle());
			pstmt.setString(5, albumVO.getAlbdesc());

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
	public void update(AlbumVO albumVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, albumVO.getMemno());
			pstmt.setInt(2, albumVO.getAlbclsno());
			pstmt.setString(3, albumVO.getAuthname());
			pstmt.setString(4, albumVO.getAlbtitle());
			pstmt.setString(5, albumVO.getAlbdesc());
			pstmt.setInt(6, albumVO.getAlbno());

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
	public void delete(Integer albno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, albno);

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
	public AlbumVO findByPrimaryKey(Integer albno) {

		AlbumVO albumVO = null;
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// albumVO 也稱為 Domain objects
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
	public List<PhotoVO> getAlbno(Integer albno) {

		List<PhotoVO> list = new ArrayList<PhotoVO>();
		PhotoVO photoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALBNO_TO_GPALBUM);
			
			pstmt.setInt(1, albno);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// albumVO 也稱為 Domain objects
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
	public void deleteGpalbum(Integer photono) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_GPALBUM);
			
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
	

	public static void main(String[] args) {

		AlbumJDBCDAO dao = new AlbumJDBCDAO();
		Date date = new Date(System.currentTimeMillis());
		Timestamp albumdate = Timestamp.valueOf(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
//
//		// 新增
//		AlbumVO albumVO1 = new AlbumVO();
//		albumVO1.setMemno(1);
//		albumVO1.setAlbclsno(1);
//		albumVO1.setAuthname("public");
//		albumVO1.setAlbtitle("流流");
//		albumVO1.setAlbdesc("瞄瞄");

//		dao.insert(albumVO1);
//
//		// 修改
//		AlbumVO albumVO2 = new AlbumVO();
//		albumVO2.setAlbno(6);
//		albumVO2.setMemno(2);
//		albumVO2.setAlbclsno(2);
//		albumVO2.setAuthname("PERSONAL");
//		albumVO2.setAlbtitle("出出");
//		albumVO2.setAlbdesc("旺旺");
//		albumVO2.setAlbtime(albumdate);
//		dao.update(albumVO2);
//
//		// 刪除
//		dao.delete(6);
//
//		// 查詢
//		AlbumVO albumVO3 = dao.findByPrimaryKey(1);
//		System.out.print(albumVO3.getAlbno() + ",");
//		System.out.print(albumVO3.getMemno() + ",");
//		System.out.print(albumVO3.getAlbclsno() + ",");
//		System.out.print(albumVO3.getAuthname() + ",");
//		System.out.print(albumVO3.getAlbtitle() + ",");
//		System.out.print(albumVO3.getAlbdesc() + ",");
//		System.out.println(albumVO3.getAlbtime());
//		System.out.println("---------------------");
//
//		// 查詢
//		List<AlbumVO> list = dao.getAll();
//		for (AlbumVO album : list) {
//			System.out.print(album.getAlbno() + ",");
//			System.out.print(album.getMemno() + ",");
//			System.out.print(album.getAlbclsno() + ",");
//			System.out.print(album.getAuthname() + ",");
//			System.out.print(album.getAlbtitle() + ",");
//			System.out.print(album.getAlbdesc() + ",");
//			System.out.print(album.getAlbtime());
//			System.out.println();
//		}
		
		//查詢相簿內相片
		List<PhotoVO> list = dao.getAlbno(2);
		for (PhotoVO photo : list) {
			System.out.print(photo.getPhotono() + ",");
			System.out.print(photo.getMemno() + ",");
			System.out.print(photo.getPhcoo() + ",");
			System.out.print(photo.getPhass() + ",");
			System.out.print(photo.getPhfilename() + ",");
			System.out.print(photo.getPhextname() + ",");
			System.out.print(photo.getPhup() + ",");
			System.out.print(photo.getPhfile());
			System.out.println();
		}
	}

	@Override
	public List<AlbumVO> getAlbclsno(Integer albclsno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AlbumVO> getAlbumbymemno(Integer memno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AlbumVO> getAlbumclsbymemno(Integer memno, Integer albclsno) {
		// TODO Auto-generated method stub
		return null;
	}
}