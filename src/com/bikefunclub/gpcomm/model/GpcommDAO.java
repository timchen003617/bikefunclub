package com.bikefunclub.gpcomm.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class GpcommDAO implements GpcommDAO_interface {
	
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
			"INSERT INTO gpcomm (gpcommno,gpno,memno,gpcomm,gpcommdate) VALUES (gpcommseq.NEXTVAL, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT gpcommno,gpno,memno,gpcomm,gpcommdate FROM gpcomm order by gpcommdate ";
		private static final String GET_ONE_STMT = 
			"SELECT gpcommno,gpno,memno,gpcomm,gpcommdate FROM gpcomm where gpcommno = ?";	
		private static final String DELETE= "DELETE FROM gpcomm where gpcommno=?";	
		private static final String GET_GPCOMMSBYGPNO_STMT = "SELECT gpcommno,gpno,memno,gpcomm,gpcommdate FROM gpcomm where gpno =? order by gpcommno desc";

		@Override
		public void insert(GpcommVO gpcommVO) {
			// TODO Auto-generated method stub

			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setInt(1, gpcommVO.getGpno());
				pstmt.setInt(2, gpcommVO.getMemno());
				pstmt.setString(3, gpcommVO.getGpcomm());
				pstmt.setTimestamp(4, gpcommVO.getGpcommdate());
				
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
		public void delete(Integer gpcommno) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, gpcommno);

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
		public GpcommVO findByPrimaryKey(Integer gpcommno) {
			// TODO Auto-generated method stub
			GpcommVO gpcommVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, gpcommno);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					gpcommVO = new GpcommVO();
					gpcommVO.setGpcommno(rs.getInt("gpcommno"));
					gpcommVO.setGpno(rs.getInt("gpno"));
					gpcommVO.setMemno(rs.getInt("memno"));
					gpcommVO.setGpcomm(rs.getString("gpcomm"));
					gpcommVO.setGpcommdate(rs.getTimestamp("gpcommdate"));
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
			return gpcommVO;
		}

		@Override
		public List<GpcommVO> getGpcommsBygpno(Integer gpno) {
			// TODO Auto-generated method stub
			List<GpcommVO> list = new ArrayList<GpcommVO>();
			GpcommVO gpcommVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_GPCOMMSBYGPNO_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO 也稱為 Domain objects
					gpcommVO = new GpcommVO();
					gpcommVO.setGpcommno(rs.getInt("gpcommno"));
					gpcommVO.setGpno(rs.getInt("gpno"));
					gpcommVO.setMemno(rs.getInt("memno"));
					gpcommVO.setGpcomm(rs.getString("gpcomm"));
					gpcommVO.setGpcommdate(rs.getTimestamp("gpcommdate"));
					list.add(gpcommVO); // Store the row in the list
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
		public List<GpcommVO> getAll() {
			// TODO Auto-generated method stub
			List<GpcommVO> list = new ArrayList<GpcommVO>();
			GpcommVO gpcommVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO 也稱為 Domain objects
					gpcommVO = new GpcommVO();
					gpcommVO.setGpcommno(rs.getInt("gpcommno"));
					gpcommVO.setGpno(rs.getInt("gpno"));
					gpcommVO.setMemno(rs.getInt("memno"));
					gpcommVO.setGpcomm(rs.getString("gpcomm"));
					gpcommVO.setGpcommdate(rs.getTimestamp("gpcommdate"));
					list.add(gpcommVO); // Store the row in the list
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

}
