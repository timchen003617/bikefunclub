package com.bikefunclub.ad.model;

import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class AdJDBCDAO implements AdDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String userid = "bikefunclub";
	String passwd = "bikefunclub";

	private static final String INSERT_STMT = "INSERT INTO ad (adno,adname,adesc,adfile,adfilename,adextname,adurl) VALUES (adseq.NEXTVAL, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM ad order by adno";
	private static final String GET_ONE_STMT = "SELECT * FROM ad where adno = ?";
	private static final String DELETE = "DELETE FROM ad where adno = ?";
	private static final String UPDATE = "UPDATE ad set adname=?, adesc=?, adfile=?, adfilename=?, adextname=?,adurl=? where adno = ?";

	@Override
	public void insert(AdVO adVo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, adVo.getAdname());
			pstmt.setString(2, adVo.getAdesc());
			pstmt.setBytes(3, adVo.getAdfile());
			pstmt.setString(4, adVo.getFilename());
			pstmt.setString(5, adVo.getExtname());
			pstmt.setString(6, adVo.getAdurl());

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
	public void update(AdVO adVo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, adVo.getAdname());
			pstmt.setString(2, adVo.getAdesc());
			pstmt.setBytes(3, adVo.getAdfile());
			pstmt.setString(4, adVo.getFilename());
			pstmt.setString(5, adVo.getExtname());
			pstmt.setString(6, adVo.getAdurl());
			pstmt.setInt(7, adVo.getAdno());

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
	public void delete(Integer adno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, adno);

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
	public AdVO findByPrimaryKey(Integer adno) {

		AdVO adVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, adno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// adVo �]�٬� Domain objects
				adVo = new AdVO();

				adVo.setAdname(rs.getString("adname"));
				adVo.setAdesc(rs.getString("adesc"));
				adVo.setAdfile(rs.getBytes("adfile"));
				adVo.setFilename(rs.getString("adfilename"));
				adVo.setExtname(rs.getString("adextname"));
				adVo.setAdurl(rs.getString("adurl"));
				adVo.setAdno(rs.getInt("adno"));
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
		return adVo;
	}

	@Override
	public List<AdVO> getAll() {
		List<AdVO> list = new ArrayList<AdVO>();
		AdVO adVo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				adVo = new AdVO();
				adVo.setAdno(rs.getInt("adno"));
				adVo.setAdname(rs.getString("adname"));
				adVo.setAdesc(rs.getString("adesc"));
				adVo.setAdfile(rs.getBytes("adfile"));
				adVo.setFilename(rs.getString("adfilename"));
				adVo.setExtname(rs.getString("adextname"));
				adVo.setAdurl(rs.getString("adurl"));
				adVo.setAdno(rs.getInt("adno"));
				list.add(adVo); // Store the row in the list
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

		AdJDBCDAO dao = new AdJDBCDAO();

		// 新增
		AdVO adVo1 = new AdVO();

		try {
			// 圖片處理
			File pic = new File(
					"C:/Users/cuser/git/bikefunclub/WebContent/img",
					"slide3.jpg");
			// long flen = pic.length();
			String filename = pic.getName();
			int dotpos = filename.indexOf('.');
			String format = filename.substring(dotpos + 1);
			String newfilename = filename.substring(0, dotpos);
			InputStream in = new FileInputStream(pic);
			int picsize = in.available();
			byte[] picup = new byte[picsize];
			in.read(picup);

			adVo1.setAdname("第3張圖");
			adVo1.setAdesc("單車");
			adVo1.setAdfile(picup);
			adVo1.setFilename(newfilename);
			adVo1.setExtname(format);
			adVo1.setAdurl("https://tw.yahoo.com");
			dao.insert(adVo1);
			in.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		// 修改
//		 AdVO adVo2 = new AdVO();
//		
//		try {
//			// 圖片處理
//			File pic = new File(
//					"C:/Users/cuser/git/bikefunclub/WebContent/img",
//					"slide1.jpg");
//			// long flen = pic.length();
//			String filename = pic.getName();
//			int dotpos = filename.indexOf('.');
//			String format = filename.substring(dotpos + 1);
//			String newfilename = filename.substring(0, dotpos);
//			InputStream in = new FileInputStream(pic);
//			int picsize = in.available();
//			byte[] picup = new byte[picsize];
//			in.read(picup);
//
//			adVo2.setAdname("第一張圖");
//			adVo2.setAdesc("在樹陰環繞下");
//			adVo2.setAdfile(picup);
//			adVo2.setFilename(newfilename);
//			adVo2.setExtname(format);
//			adVo2.setAdurl("https://www.google.com.tw");
//			adVo2.setAdno(1);
//			dao.update(adVo2);
//			in.close();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		// 刪除
		// dao.delete(3);

		// 單筆查詢
		// AdVO adVo3 = dao.findByPrimaryKey(1);
		// System.out.print(adVo3.getAdname() + ",");
		// System.out.print(adVo3.getAdesc() + ",");
		// System.out.print(adVo3.getFilename() + ",");
		// System.out.print(adVo3.getExtname() + ",");
		// System.out.println(adVo3.getAdurl()+ ",");
		// System.out.print(adVo3.getAdno() + ",");
		//
		// System.out.println("---------------------");

		// 查全部
//		 List<AdVO> list = dao.getAll();
//		 for (AdVO ad : list) {
//		 System.out.print(ad.getAdno() + ",");
//		 System.out.print(ad.getAdname() + ",");
//		 System.out.print(ad.getAdesc() + ",");
//		 System.out.print(ad.getFilename() + ",");
//		 System.out.print(ad.getExtname() + ",");
//		 System.out.println(ad.getAdurl() + ",");
//		 System.out.print(ad.getAdno() + ",");
//		 System.out.println();
//		 }
	}
}