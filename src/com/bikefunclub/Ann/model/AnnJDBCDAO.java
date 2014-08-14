package com.bikefunclub.Ann.model;

import java.util.*;
import java.io.*;
import java.sql.*;
import java.sql.Date;

import com.bikefunclub.riderecord.model.RideRecordJDBCDAO;

public class AnnJDBCDAO implements AnnDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String userid = "bikefunclub";
	String passwd = "bikefunclub";

	private static final String INSERT_STMT = "INSERT INTO Ann (annno,empno,anntitle,anncontent,anndate,annfile,annfilename,annextname) VALUES (ANNseq.NEXTVAL, ?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT annno,empno,anntitle,anncontent,anndate,annfile,annfilename,annextname  FROM ANN order by anndate desc";
	private static final String GET_ONE_STMT = "SELECT * FROM ANN where annno = ?";
	private static final String DELETE = "DELETE FROM ANN where annno = ?";
	private static final String UPDATE = "UPDATE ANN set empno=?, anntitle=?, anncontent=?, anndate=?, annfile=? ,annfilename=?,annextname=? where annno = ?";

	@Override
	public void insert(AnnVO annVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, annVO.getEmpno());
			pstmt.setString(2, annVO.getAnntitle());
			pstmt.setString(3, annVO.getAnncontent());
			pstmt.setTimestamp(4, annVO.getAnndate());
			pstmt.setBytes(5, annVO.getAnnfile());
			pstmt.setString(6, annVO.getAnnfilename());
			pstmt.setString(7, annVO.getAnnextname());

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
	public void update(AnnVO annVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, annVO.getEmpno());
			pstmt.setString(2, annVO.getAnntitle());
			pstmt.setString(3, annVO.getAnncontent());
			pstmt.setTimestamp(4, annVO.getAnndate());
			pstmt.setBytes(5, annVO.getAnnfile());
			pstmt.setString(6, annVO.getAnnfilename());
			pstmt.setString(7, annVO.getAnnextname());
			pstmt.setInt(8, annVO.getAnnno());
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
	public void delete(Integer annno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, annno);

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
	public AnnVO findByPrimaryKey(Integer annno) {

		AnnVO annVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, annno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// AnnVO �]�٬� Domain objects
				annVO = new AnnVO();
				annVO.setAnnno(rs.getInt("annno"));
				annVO.setEmpno(rs.getInt("empno"));
				annVO.setAnntitle(rs.getString("anntitle"));
				annVO.setAnncontent(rs.getString("anncontent"));
				annVO.setAnndate(rs.getTimestamp("anndate"));
				annVO.setAnnfile(rs.getBytes("annfile"));
				annVO.setAnnfilename(rs.getString("annfilename"));
				annVO.setAnnextname(rs.getString("annextname"));
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
		return annVO;
	}

	@Override
	public List<AnnVO> getAll() {
		List<AnnVO> list = new ArrayList<AnnVO>();
		AnnVO annVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				annVO = new AnnVO();
				annVO.setAnnno(rs.getInt("annno"));
				annVO.setEmpno(rs.getInt("empno"));
				annVO.setAnntitle(rs.getString("anntitle"));
				annVO.setAnncontent(rs.getString("anncontent"));
				annVO.setAnndate(rs.getTimestamp("anndate"));
				annVO.setAnnfile(rs.getBytes("annfile"));
				annVO.setAnnfilename(rs.getString("annfilename"));
				annVO.setAnnextname(rs.getString("annextname"));
				list.add(annVO); // Store the row in the list
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

		AnnJDBCDAO dao = new AnnJDBCDAO();

		// 新增
		 AnnVO annVO1 = new AnnVO();
		 
		 //Timestamp
		 AnnJDBCDAO dao1 = new AnnJDBCDAO();
		 Date date = new Date(System.currentTimeMillis()); 
		 Timestamp anndate = Timestamp.valueOf(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));

		 
		
		 // 圖片處理
		 
	 try{	 
		 File pic = new File(
		 "C:/Users/cuser/git/bikefunclub/WebContent/img",
		 "slide3.jpg");
		
		 String filename = pic.getName();
		 int dotpos = filename.indexOf('.');
		 String format = filename.substring(dotpos + 1);
		 String newfilename = filename.substring(0, dotpos);
		 InputStream in = new FileInputStream(pic);
		 int picsize = in.available();
		 byte[] picup = new byte[picsize];
		 in.read(picup);
		 
	
		 
		 
		 
		 
		 //新增
		 annVO1.setEmpno(2);
		 annVO1.setAnntitle("summer");
		 annVO1.setAnncontent("summer");
		 annVO1.setAnndate(anndate);
		 annVO1.setAnnfile(picup);
		 annVO1.setAnnfilename(newfilename);
		 annVO1.setAnnextname(format);
		 dao1.insert(annVO1);
		 in.close();
	  }catch(IOException I){
		 I.printStackTrace();
	  }

		// 修改
		AnnVO annVO2 = new AnnVO();
		// 圖片處理
		
	   try{	
		   File pic = new File(
				"C:/Users/cuser/git/bikefunclub/WebContent/img",
				"slide1.jpg");
		
		   String filename = pic.getName();
		   int dotpos = filename.indexOf('.');
		   String format = filename.substring(dotpos + 1);
		   String newfilename = filename.substring(0, dotpos);
		   InputStream in = new FileInputStream(pic);
		   int picsize = in.available();
		   byte[] picup = new byte[picsize];
		   in.read(picup);
			
			annVO2.setEmpno(2);
			annVO2.setAnntitle("updateok");
			annVO2.setAnncontent("updateok");
			annVO2.setAnndate(anndate);
			annVO2.setAnnfile(picup);
			annVO2.setAnnfilename(newfilename);
			annVO2.setAnnextname(format);
			annVO2.setAnnno(20);
			in.close();
	
		    dao.update(annVO2);
		    
	    }catch(IOException I){
			I.printStackTrace();
	    }

		// 刪除
//		 dao.delete(21);

		// 查詢單筆
		 AnnVO annVO3 = dao.findByPrimaryKey(17);
		 System.out.print(annVO3.getAnnno() + ",");
		 System.out.print(annVO3.getEmpno() + ",");
		 System.out.print(annVO3.getAnntitle() + ",");
		 System.out.print(annVO3.getAnncontent() + ",");
		 System.out.print(annVO3.getAnndate() + ",");
		 System.out.print(annVO3.getAnnfilename());
		 System.out.print(annVO3.getAnnextname());
		 System.out.println("---------------------");

		// 查詢多筆
		 List<AnnVO> list = dao.getAll();
		 for (AnnVO aAnn : list) {
		 System.out.print(aAnn.getAnnno() + ",");
		 System.out.print(aAnn.getEmpno() + ",");
		 System.out.print(aAnn.getAnntitle() + ",");
		 System.out.print(aAnn.getAnncontent() + ",");
		 System.out.print(aAnn.getAnndate() + ",");
		 System.out.print(aAnn.getAnnfilename());
		 System.out.print(aAnn.getAnnextname());
		
		 System.out.println();
		 }
	}
  }
	