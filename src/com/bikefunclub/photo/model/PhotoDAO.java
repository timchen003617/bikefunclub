package com.bikefunclub.photo.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.bikefunclub.gpalbum.model.GpalbumDAO;
import com.bikefunclub.gpalbum.model.GpalbumVO;

public class PhotoDAO implements PhotoDAO_interface {

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
			"INSERT INTO photo (photono,memno,phass,phfilename,phextname,phup,phfile) VALUES (photoseq.NEXTVAL, ?, ?, ?, ?, sysdate, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT photono,memno,phcoo,phass,phfilename,to_char(phup,'YYYY-MM-DD HH24:MI:SS') phup,phextname,phfile FROM photo order by photono";
		private static final String GET_ONE_STMT = 
			"SELECT photono,memno,phcoo,phass,phfilename,to_char(phup,'YYYY-MM-DD HH24:MI:SS') phup,phextname,phfile FROM photo where photono = ?";
		private static final String DELETE = 
			"DELETE FROM photo where photono = ?";
		private static final String UPDATE = 
			"UPDATE photo set memno=?, phass=?, phfilename=?, phextname=?, phup=?, phfile=? where photono = ?";

	@Override
	public void insert(PhotoVO photoVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, photoVO.getMemno());
			pstmt.setInt(2, photoVO.getPhass());
			pstmt.setString(3, photoVO.getPhfilename());
			pstmt.setString(4, photoVO.getPhextname());
			pstmt.setBytes(5, photoVO.getPhfile());

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
	public void update(PhotoVO photoVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, photoVO.getMemno());
			pstmt.setInt(2, photoVO.getPhass());
			pstmt.setString(3, photoVO.getPhfilename());
			pstmt.setString(4, photoVO.getPhextname());
			pstmt.setTimestamp(5, photoVO.getPhup());
			pstmt.setBytes(6, photoVO.getPhfile());
			pstmt.setInt(7, photoVO.getPhotono());

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
	public void delete(Integer photono) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

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

	@Override
	public PhotoVO findByPrimaryKey(Integer photono) {

		PhotoVO photoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
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

			con = ds.getConnection();
			
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		// 先新增相片
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
			GpalbumDAO dao = new GpalbumDAO();
			dao.insert2(albno , new Integer(next_photono) , con);

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			
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
}