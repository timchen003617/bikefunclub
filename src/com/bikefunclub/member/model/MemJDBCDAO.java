package com.bikefunclub.member.model;

import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class MemJDBCDAO implements Mem_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String userid = "bikefunclub";
	String passwd = "bikefunclub";

	private static final String INSERT_STMT = "INSERT INTO member(memno,memacc,mempw,memname,memid,membirth,memnickname,memfile,memfilename,memextname,mememail,memsex,memzip,memaddr,memtelh,memtelo,memtelm,memgetmailyn,memrgdate) VALUES(memberseq.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM member order by memno";
	private static final String GET_ONE_STMT = "SELECT * FROM member where memno = ?";
	private static final String DELETE = "DELETE FROM member where memno = ?";
	private static final String UPDATE = "UPDATE member set memacc=?,mempw=?,memname=?,memid=?,membirth=?,memnickname=?,memfile=?,memfilename=?,memextname=?,mememail=?,memsex=?,memzip=?,memaddr=?,memtelh=?,memtelo=?,memtelm=? where memno=?";
	private static final String GET_ONE_STMT_ACCOUNT = "SELECT * FROM member where memacc = ?";
	private static final String GET_ONE_STMT_AllFORNEWFRI = "SELECT * FROM member where memno not in(select youno from friendlist where memno = ?)";
	private static final String GET_ONE_STMT_AllFORNEWFRIBYMEMNAME = "SELECT * FROM member where memno not in(select youno from friendlist where memno = ?) and memname like ?";
	private static final String UPDATE_GETMAILYN = "update member set memgetmailyn=? where memno=?";
	@Override
	public void insert(MemVO memVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, memVO.getMemacc());
			pstmt.setString(2, memVO.getMempw());
			pstmt.setString(3, memVO.getMemname());
			pstmt.setString(4, memVO.getMemid());
			pstmt.setDate(5, memVO.getMembirth());
			pstmt.setString(6, memVO.getMemnickname());
			pstmt.setBytes(7, memVO.getMemfile());
			pstmt.setString(8, memVO.getMemfilename());
			pstmt.setString(9, memVO.getMemextname());
			pstmt.setString(10, memVO.getMememail());
			pstmt.setString(11, memVO.getMemsex());
			pstmt.setString(12, memVO.getMemzip());
			pstmt.setString(13, memVO.getMemaddr());
			pstmt.setString(14, memVO.getMemtelh());
			pstmt.setString(15, memVO.getMemtelo());
			pstmt.setString(16, memVO.getMemtelm());
			pstmt.setString(17, memVO.getMemgetmailyn());
			pstmt.setTimestamp(18, memVO.getMemrgdate());

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
	public void update(MemVO memVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memVO.getMemacc());
			pstmt.setString(2, memVO.getMempw());
			pstmt.setString(3, memVO.getMemname());
			pstmt.setString(4, memVO.getMemid());
			pstmt.setDate(5, memVO.getMembirth());
			pstmt.setString(6, memVO.getMemnickname());
			pstmt.setBytes(7, memVO.getMemfile());
			pstmt.setString(8, memVO.getMemfilename());
			pstmt.setString(9, memVO.getMemextname());
			pstmt.setString(10, memVO.getMememail());
			pstmt.setString(11, memVO.getMemsex());
			pstmt.setString(12, memVO.getMemzip());
			pstmt.setString(13, memVO.getMemaddr());
			pstmt.setString(14, memVO.getMemtelh());
			pstmt.setString(15, memVO.getMemtelo());
			pstmt.setString(16, memVO.getMemtelm());
			pstmt.setInt(17, memVO.getMemno());

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
	public void delete(Integer memno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memno);

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
	public MemVO findByPrimaryKey(Integer memno) {

		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, memno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memVO �]�٬� Domain objects
				memVO = new MemVO();
				memVO.setMemno(rs.getInt("memno"));
				memVO.setMemacc(rs.getString("memacc"));
				memVO.setMempw(rs.getString("mempw"));
				memVO.setMemname(rs.getString("memname"));
				memVO.setMemid(rs.getString("memid"));
				memVO.setMembirth(rs.getDate("membirth"));
				memVO.setMemnickname(rs.getString("memnickname"));
				memVO.setMemfile(rs.getBytes("memfile"));
				memVO.setMemfilename(rs.getString("memfilename"));
				memVO.setMemextname(rs.getString("memextname"));
				memVO.setMememail(rs.getString("mememail"));
				memVO.setMemsex(rs.getString("memsex"));
				memVO.setMemzip(rs.getString("memzip"));
				memVO.setMemaddr(rs.getString("Memaddr"));
				memVO.setMemtelh(rs.getString("memtelh"));
				memVO.setMemtelo(rs.getString("memtelo"));
				memVO.setMemtelm(rs.getString("memtelm"));
				memVO.setMemgetmailyn(rs.getString("memgetmailyn"));
				memVO.setMemrgdate(rs.getTimestamp("memrgdate"));
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
		return memVO;
	}

	@Override
	public MemVO findByAccount(String memacc) {

		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT_ACCOUNT);

			pstmt.setString(1, memacc);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				memVO = new MemVO();
				memVO.setMemno(rs.getInt("memno"));
				memVO.setMemacc(rs.getString("memacc"));
				memVO.setMempw(rs.getString("mempw"));
				memVO.setMemname(rs.getString("memname"));
				memVO.setMemid(rs.getString("memid"));
				memVO.setMembirth(rs.getDate("membirth"));
				memVO.setMemnickname(rs.getString("memnickname"));
				memVO.setMemfile(rs.getBytes("memfile"));
				memVO.setMemfilename(rs.getString("memfilename"));
				memVO.setMemextname(rs.getString("memextname"));
				memVO.setMememail(rs.getString("mememail"));
				memVO.setMemsex(rs.getString("memsex"));
				memVO.setMemzip(rs.getString("memzip"));
				memVO.setMemaddr(rs.getString("Memaddr"));
				memVO.setMemtelh(rs.getString("memtelh"));
				memVO.setMemtelo(rs.getString("memtelo"));
				memVO.setMemtelm(rs.getString("memtelm"));
				memVO.setMemgetmailyn(rs.getString("memgetmailyn"));
				memVO.setMemrgdate(rs.getTimestamp("memrgdate"));
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
		return memVO;
	}

	@Override
	public List<MemVO> getAll() {
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memVO �]�٬� Domain objects
				memVO = new MemVO();
				memVO.setMemno(rs.getInt("memno"));
				memVO.setMemacc(rs.getString("memacc"));
				memVO.setMempw(rs.getString("mempw"));
				memVO.setMemname(rs.getString("memname"));
				memVO.setMemid(rs.getString("memid"));
				memVO.setMembirth(rs.getDate("membirth"));
				memVO.setMemnickname(rs.getString("memnickname"));
				memVO.setMemfile(rs.getBytes("memfile"));
				memVO.setMemfilename(rs.getString("memfilename"));
				memVO.setMemextname(rs.getString("memextname"));
				memVO.setMememail(rs.getString("mememail"));
				memVO.setMemsex(rs.getString("memsex"));
				memVO.setMemzip(rs.getString("memzip"));
				memVO.setMemaddr(rs.getString("Memaddr"));
				memVO.setMemtelh(rs.getString("memtelh"));
				memVO.setMemtelo(rs.getString("memtelo"));
				memVO.setMemtelm(rs.getString("Memtelm"));
				memVO.setMemgetmailyn(rs.getString("memgetmailyn"));
				memVO.setMemrgdate(rs.getTimestamp("memrgdate"));

				list.add(memVO); // Store the row in the list
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
	public List<MemVO> findByAllfornewfri(Integer memno) {
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT_AllFORNEWFRI);

			pstmt.setInt(1, memno);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				memVO = new MemVO();
				memVO.setMemno(rs.getInt("memno"));
				memVO.setMemacc(rs.getString("memacc"));
				memVO.setMempw(rs.getString("mempw"));
				memVO.setMemname(rs.getString("memname"));
				memVO.setMemid(rs.getString("memid"));
				memVO.setMembirth(rs.getDate("membirth"));
				memVO.setMemnickname(rs.getString("memnickname"));
				memVO.setMemfile(rs.getBytes("memfile"));
				memVO.setMemfilename(rs.getString("memfilename"));
				memVO.setMemextname(rs.getString("memextname"));
				memVO.setMememail(rs.getString("mememail"));
				memVO.setMemsex(rs.getString("memsex"));
				memVO.setMemzip(rs.getString("memzip"));
				memVO.setMemaddr(rs.getString("memaddr"));
				memVO.setMemtelh(rs.getString("memtelh"));
				memVO.setMemtelo(rs.getString("memtelo"));
				memVO.setMemtelm(rs.getString("memtelm"));
				memVO.setMemgetmailyn(rs.getString("memgetmailyn"));
				memVO.setMemrgdate(rs.getTimestamp("memrgdate"));
				
				list.add(memVO); // Store the row in the list
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			
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
	public List<MemVO> findByAllfornewfriByname(Integer memno,String memname) {
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT_AllFORNEWFRIBYMEMNAME);

			pstmt.setInt(1, memno);
			pstmt.setString(2, memname);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				memVO = new MemVO();
				memVO.setMemno(rs.getInt("memno"));
				memVO.setMemacc(rs.getString("memacc"));
				memVO.setMempw(rs.getString("mempw"));
				memVO.setMemname(rs.getString("memname"));
				memVO.setMemid(rs.getString("memid"));
				memVO.setMembirth(rs.getDate("membirth"));
				memVO.setMemnickname(rs.getString("memnickname"));
				memVO.setMemfile(rs.getBytes("memfile"));
				memVO.setMemfilename(rs.getString("memfilename"));
				memVO.setMemextname(rs.getString("memextname"));
				memVO.setMememail(rs.getString("mememail"));
				memVO.setMemsex(rs.getString("memsex"));
				memVO.setMemzip(rs.getString("memzip"));
				memVO.setMemaddr(rs.getString("memaddr"));
				memVO.setMemtelh(rs.getString("memtelh"));
				memVO.setMemtelo(rs.getString("memtelo"));
				memVO.setMemtelm(rs.getString("memtelm"));
				memVO.setMemgetmailyn(rs.getString("memgetmailyn"));
				memVO.setMemrgdate(rs.getTimestamp("memrgdate"));
				
				list.add(memVO); // Store the row in the list
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			
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
	public void update_getmailyn(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_GETMAILYN);

			pstmt.setString(1, memVO.getMemgetmailyn());
			pstmt.setInt(2, memVO.getMemno());

			pstmt.executeUpdate();

			// Handle any driver errors

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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

		MemJDBCDAO dao = new MemJDBCDAO();
		// try {
		// // 圖片處理
		// File pic = new File(
		// "C:/Users/cuser/git/bikefunclub/WebContent/img",
		// "slide3.jpg");
		// // long flen = pic.length();
		// String filename = pic.getName();
		// int dotpos = filename.indexOf('.');
		// String format = filename.substring(dotpos + 1);
		// String newfilename = filename.substring(0, dotpos);
		// InputStream in = new FileInputStream(pic);
		// int picsize = in.available();
		// byte[] picup = new byte[picsize];
		// in.read(picup);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// 新增
		// MemVO memVO1 = new MemVO();
		//
		// memVO1.setMemacc("anthony8087");//不能重複
		// memVO1.setMempw("333123");//不能空值
		// memVO1.setMemname("沈清春");//不能空值
		// memVO1.setMemid("D124564561");//不能重複
		// memVO1.setMembirth(java.sql.Date.valueOf("1987-09-30"));
		// memVO1.setMemnickname("青春");
		// memVO1.setMemfile(new byte[100]);
		// memVO1.setMemfilename("");
		// memVO1.setMemextname("");
		// memVO1.setMememail("daniel8087@gmail.com");//不能空值
		// memVO1.setMemsex("F");//不能空值
		// memVO1.setMemzip("32005");
		// memVO1.setMemaddr("桃園縣中壢市中大路25號");//不能空值
		// memVO1.setMemtelh("0254506711");
		// memVO1.setMemtelo("0356532111");
		// memVO1.setMemtelm("0931228357");
		// memVO1.setMemrgdate(java.sql.Date.valueOf("2011-04-15"));
		// dao.insert(memVO1);

		// 修改
		// MemVO memVO2 = new MemVO();
		//
		// memVO2.setMemacc("anthony23456");
		// memVO2.setMempw("MANAGER5");
		// memVO2.setMemname("邱孟宇");
		// memVO2.setMemid("G127321798");
		// memVO2.setMembirth(java.sql.Date.valueOf("2002-01-01"));
		// memVO2.setMemnickname("小邱");
		// memVO2.setMemfile("".getBytes());
		// memVO2.setMemfilename("");
		// memVO2.setMemextname("");
		// memVO2.setMememail("daniel123@gmail.com");
		// memVO2.setMemsex("M");
		// memVO2.setMemzip("25164");
		// memVO2.setMemaddr("新北市淡水區新民街56號7樓");
		// memVO2.setMemtelh("0256557011");
		// memVO2.setMemtelo("0354224011");
		// memVO2.setMemtelm("0935018257");
		// memVO2.setMemno(7);
		// dao.update(memVO2);

		// 刪除
		// dao.delete(3);

		// 查詢單筆
		// MemVO memVO3 = dao.findByPrimaryKey(3);
		// System.out.print(memVO3.getMemno() + ",");
		// System.out.print(memVO3.getMemacc() + ",");
		// System.out.print(memVO3.getMempw() + ",");
		// System.out.print(memVO3.getMemname() + ",");
		// System.out.print(memVO3.getMemid() + ",");
		// System.out.print(memVO3.getMembirth() + ",");
		// System.out.print(memVO3.getMemnickname() + ",");
		// System.out.print(memVO3.getMemfile() + ",");
		// System.out.print(memVO3.getMemfilename() + ",");
		// System.out.print(memVO3.getMemextname() + ",");
		// System.out.print(memVO3.getMememail() + ",");
		// System.out.print(memVO3.getMemzip() + ",");
		// System.out.print(memVO3.getMemsex() + ",");
		// System.out.print(memVO3.getMemaddr() + ",");
		// System.out.print(memVO3.getMemtelh() + ",");
		// System.out.print(memVO3.getMemtelo() + ",");
		// System.out.print(memVO3.getMemtelm() + ",");
		// System.out.print(memVO3.getMemrgdate() + ",");
		//
		// System.out.println("---------------------");

		// 查詢全部
		List<MemVO> list = dao.getAll();
		//for (MemVO aMem : list) {
			// System.out.print(aMem.getMemno() + ",");
			// System.out.print(aMem.getMemacc() + ",");
			// System.out.print(aMem.getMempw() + ",");
			// System.out.print(aMem.getMemname() + ",");
			// System.out.print(aMem.getMemid() + ",");
			// System.out.print(aMem.getMembirth() + ",");
			// System.out.print(aMem.getMemnickname() + ",");
			// System.out.print(aMem.getMemfile() + ",");
			// System.out.print(aMem.getMemfilename() + ",");
			// System.out.print(aMem.getMemextname() + ",");
			// System.out.print(aMem.getMememail() + ",");
			// System.out.print(aMem.getMemzip() + ",");
			// System.out.print(aMem.getMemsex() + ",");
			// System.out.print(aMem.getMemaddr() + ",");
			// System.out.print(aMem.getMemtelh() + ",");
			// System.out.print(aMem.getMemtelo() + ",");
			// System.out.print(aMem.getMemtelm() + ",");
			// System.out.print(aMem.getMemrgdate() + ",");
		//}
		// 查詢未加入好友的會員
//		List<MemVO> list2 = dao.findByAllfornewfri(1);
//		for (MemVO aMem : list2) {
//		System.out.println(aMem.getMemno() + ",");
//		System.out.println(aMem.getMemacc() + ",");
//		System.out.println(aMem.getMempw() + ",");
//		System.out.println(aMem.getMemname() + ",");
//		System.out.println(aMem.getMemid() + ",");
//		System.out.println(aMem.getMembirth() + ",");
//		System.out.println(aMem.getMemnickname() + ",");
//		System.out.println(aMem.getMemfile() + ",");
//		System.out.println(aMem.getMemfilename() + ",");
//		System.out.println(aMem.getMemextname() + ",");
//		System.out.println(aMem.getMememail() + ",");
//		System.out.println(aMem.getMemzip() + ",");
//		System.out.println(aMem.getMemsex() + ",");
//		System.out.println(aMem.getMemaddr() + ",");
//		System.out.println(aMem.getMemtelh() + ",");
//		System.out.println(aMem.getMemtelo() + ",");
//		System.out.println(aMem.getMemtelm() + ",");
//		System.out.println(aMem.getMemrgdate() + ",");
//		}
//		System.out.println("---------------------");
		// 查詢未加入好友的會員，帶入姓名
//		List<MemVO> list3 = dao.findByAllfornewfriByname(3,"%peter%");
//		for (MemVO aMem : list3) {
//		System.out.println(aMem.getMemno() + ",");
//		System.out.println(aMem.getMemacc() + ",");
//		System.out.println(aMem.getMempw() + ",");
//		System.out.println(aMem.getMemname() + ",");
//		System.out.println(aMem.getMemid() + ",");
//		System.out.println(aMem.getMembirth() + ",");
//		System.out.println(aMem.getMemnickname() + ",");
//		System.out.println(aMem.getMemfile() + ",");
//		System.out.println(aMem.getMemfilename() + ",");
//		System.out.println(aMem.getMemextname() + ",");
//		System.out.println(aMem.getMememail() + ",");
//		System.out.println(aMem.getMemzip() + ",");
//		System.out.println(aMem.getMemsex() + ",");
//		System.out.println(aMem.getMemaddr() + ",");
//		System.out.println(aMem.getMemtelh() + ",");
//		System.out.println(aMem.getMemtelo() + ",");
//		System.out.println(aMem.getMemtelm() + ",");
//		System.out.println(aMem.getMemrgdate() + ",");
//		}
//		System.out.println("---------------------");
	}


}