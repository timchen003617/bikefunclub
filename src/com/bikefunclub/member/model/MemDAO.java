package com.bikefunclub.member.model;

import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemDAO implements Mem_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/YA801G2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO member(memno,memacc,mempw,memname,memid,membirth,memnickname,memfile,memfilename,memextname,mememail,memsex,memzip,memaddr,memtelh,memtelo,memtelm, memrgdate) VALUES(memberseq.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM member order by memno";
	private static final String GET_ONE_STMT = "SELECT * FROM member where memno = ?";
	private static final String DELETE = "DELETE FROM member where memno = ?";
	private static final String UPDATE = "UPDATE member set memacc=?,mempw=?,memname=?,memid=?,membirth=?,memnickname=?,memfile=?,memfilename=?,memextname=?,mememail=?,memsex=?,memzip=?,memaddr=?,memtelh=?,memtelo=?,memtelm=? where memno=?";
	private static final String GET_ONE_STMT_ACCOUNT = "SELECT * FROM member where memacc = ?";
	private static final String GET_ONE_STMT_AllFORNEWFRI = "SELECT * FROM member where memno not in(select youno from friendlist where memno = ?)";
	private static final String GET_ONE_STMT_AllFORNEWFRIBYMEMNAME = "SELECT * FROM member where memno not in(select youno from friendlist where memno = ?) and memname like ?";
	
	@Override
	public void insert(MemVO memVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
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
			pstmt.setTimestamp(17, memVO.getMemrgdate());

			pstmt.executeUpdate();

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

			con = ds.getConnection();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memno);

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
	public MemVO findByPrimaryKey(Integer memno) {

		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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
				memVO.setMembirth(rs.getDate("Membirth"));
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
				memVO.setMemrgdate(rs.getTimestamp("memrgdate"));
			}

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

			con = ds.getConnection();
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
				memVO.setMembirth(rs.getDate("Membirth"));
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
				memVO.setMemrgdate(rs.getTimestamp("memrgdate"));
			}

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memVO = new MemVO();
				memVO.setMemno(rs.getInt("memno"));
				memVO.setMemacc(rs.getString("memacc"));
				memVO.setMempw(rs.getString("mempw"));
				memVO.setMemname(rs.getString("memname"));
				memVO.setMemid(rs.getString("memid"));
				memVO.setMembirth(rs.getDate("Membirth"));
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
				memVO.setMemrgdate(rs.getTimestamp("memrgdate"));

				list.add(memVO); // Store the row in the list
			}

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

			con = ds.getConnection();
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
				memVO.setMembirth(rs.getDate("Membirth"));
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
				memVO.setMemrgdate(rs.getTimestamp("memrgdate"));
				list.add(memVO); // Store the row in the list
			}

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
			con = ds.getConnection();
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
				memVO.setMembirth(rs.getDate("Membirth"));
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
				memVO.setMemrgdate(rs.getTimestamp("memrgdate"));
				
				list.add(memVO); // Store the row in the list
			}
			
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
}
