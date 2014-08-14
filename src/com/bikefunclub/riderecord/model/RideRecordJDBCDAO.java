package com.bikefunclub.riderecord.model;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class RideRecordJDBCDAO implements RideRecordDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String userid = "bikefunclub";
	String passwd = "bikefunclub";

	private static final String INSERT_STMT = "INSERT INTO riderecord (riderecordno,memno,rotno,stamp,recordtime,recorddistence,ridetime,newrotno) VALUES (riderecordseq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT riderecordno,memno,rotno,stamp,recordtime,recorddistence,ridetime,newrotno FROM riderecord order by riderecordno";
	private static final String GET_ONE_STMT = "SELECT riderecordno,memno,rotno,stamp,recordtime,recorddistence,ridetime,newrotno FROM riderecord where riderecordno = ?";
	private static final String DELETE = "DELETE FROM riderecord where riderecordno = ?";
	private static final String UPDATE = "UPDATE riderecord set memno=?, rotno=?, stamp=?, recordtime=?, recorddistence=?, ridetime=?,newrotno=? where riderecordno = ?";

	@Override
	public int insert(RideRecordVO rideRecordVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, rideRecordVO.getMemno());
			pstmt.setInt(2, rideRecordVO.getRotno());
			pstmt.setTimestamp(3, rideRecordVO.getStamp());
			pstmt.setTimestamp(4, rideRecordVO.getRecordtime());
			pstmt.setDouble(5, rideRecordVO.getRecorddistence());
			pstmt.setLong(6, rideRecordVO.getRidetime());
			pstmt.setInt(7, rideRecordVO.getNewrotno());
			updateCount = pstmt.executeUpdate();

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
		return updateCount;
	}

	@Override
	public int update(RideRecordVO rideRecordVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, rideRecordVO.getMemno());
			pstmt.setInt(2, rideRecordVO.getRotno());
			pstmt.setTimestamp(3, rideRecordVO.getStamp());
			pstmt.setTimestamp(4, rideRecordVO.getRecordtime());
			pstmt.setDouble(5, rideRecordVO.getRecorddistence());
			pstmt.setLong(6, rideRecordVO.getRidetime());
			pstmt.setInt(7, rideRecordVO.getNewrotno());
			pstmt.setInt(8, rideRecordVO.getRiderecordno());
			updateCount = pstmt.executeUpdate();

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
		return updateCount;
	}

	@Override
	public int delete(Integer riderecordno) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, riderecordno);

			updateCount = pstmt.executeUpdate();

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
		return updateCount;
	}

	@Override
	public RideRecordVO findByPrimaryKey(Integer riderecordno) {

		RideRecordVO rideRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, riderecordno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				rideRecordVO = new RideRecordVO();
				rideRecordVO.setRiderecordno(rs.getInt("riderecordno"));
				rideRecordVO.setMemno(rs.getInt("memno"));
				rideRecordVO.setRotno(rs.getInt("rotno"));
				rideRecordVO.setStamp(rs.getTimestamp("stamp"));
				rideRecordVO.setRecordtime(rs.getTimestamp("recordtime"));
				rideRecordVO.setRecorddistence(rs.getDouble("recorddistence"));
				rideRecordVO.setRidetime(rs.getLong("ridetime"));
				rideRecordVO.setNewrotno(rs.getInt("newrotno"));
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
		return rideRecordVO;
	}

	@Override
	public List<RideRecordVO> getAll() {
		List<RideRecordVO> list = new ArrayList<RideRecordVO>();
		RideRecordVO rideRecordVO = null;

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
				rideRecordVO = new RideRecordVO();
				rideRecordVO.setRiderecordno(rs.getInt("rideRecordno"));
				rideRecordVO.setMemno(rs.getInt("Memno"));
				rideRecordVO.setRotno(rs.getInt("Rotno"));
				rideRecordVO.setStamp(rs.getTimestamp("Stamp"));
				rideRecordVO.setRecordtime(rs.getTimestamp("Recordtime"));
				rideRecordVO.setRecorddistence(rs.getDouble("Recorddistence"));
				rideRecordVO.setRidetime(rs.getLong("Ridetime"));
				rideRecordVO.setNewrotno(rs.getInt("Newrotno"));
				list.add(rideRecordVO); // Store the row in the vector
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

		RideRecordJDBCDAO dao = new RideRecordJDBCDAO();
		Date date = new Date(System.currentTimeMillis());

		// 新增
//		RideRecordVO rideRecordVO1 = new RideRecordVO();
//		rideRecordVO1.setMemno(4);
//		rideRecordVO1.setRotno(4);
//		rideRecordVO1.setStamp(Timestamp
//				.valueOf(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//						.format(date)));
//		rideRecordVO1.setRecordtime(Timestamp
//				.valueOf(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//						.format(date)));
//		rideRecordVO1.setRecorddistence(20000.0);
//		rideRecordVO1.setRidetime(1);
//		rideRecordVO1.setNewrotno(1);
//		int updateCount_insert = dao.insert(rideRecordVO1);
//		System.out.println(updateCount_insert);

		// 修改
//		 RideRecordVO rideRecordVO2= new RideRecordVO();
//		 rideRecordVO2.setRiderecordno(2);
//		 rideRecordVO2.setMemno(6);
//		 rideRecordVO2.setRotno(6);
//		 rideRecordVO2.setStamp(Timestamp
//					.valueOf(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//					.format(date)));
//		 rideRecordVO2.setRecordtime(Timestamp
//					.valueOf(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//					.format(date)));
//		 rideRecordVO2.setRecorddistence(6000.0);
//		 rideRecordVO2.setRidetime(99);
//		 rideRecordVO2.setNewrotno(0);
//
//		 int updateCount_update = dao.update(rideRecordVO2);
//		 System.out.println(updateCount_update);

		// 刪除
		// int updateCount_delete = dao.delete(3);
		// System.out.println(updateCount_delete);

		// 查詢
		RideRecordVO rideRecordVO3 = dao.findByPrimaryKey(1);
		System.out.print(rideRecordVO3.getRiderecordno() + ",");
		System.out.print(rideRecordVO3.getMemno() + ",");
		System.out.print(rideRecordVO3.getRotno() + ",");
		System.out.print(rideRecordVO3.getStamp() + ",");
		System.out.print(rideRecordVO3.getRecordtime() + ",");
		System.out.print(rideRecordVO3.getRecorddistence() + ",");
		System.out.print(rideRecordVO3.getRidetime() + ",");
		System.out.println(rideRecordVO3.getNewrotno());
		System.out.println("---------------------");

		// 查詢
		List<RideRecordVO> list = dao.getAll();
		for (RideRecordVO aRideRecord : list) {
			System.out.print(aRideRecord.getRiderecordno() + ",");
			System.out.print(aRideRecord.getMemno() + ",");
			System.out.print(aRideRecord.getRotno() + ",");
			System.out.print(aRideRecord.getStamp() + ",");
			System.out.print(aRideRecord.getRecordtime() + ",");
			System.out.print(aRideRecord.getRecorddistence() + ",");
			System.out.print(aRideRecord.getRidetime() + ",");
			System.out.println(aRideRecord.getNewrotno());
			System.out.println();
		}
	}
}
