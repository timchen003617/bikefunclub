package com.bikefunclub.gp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class GpJDBCDAO implements GpDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String userid = "bikefunclub";
	String passwd = "bikefunclub";

	private static final String INSERT_STMT = 
		"INSERT INTO gp (gpno,memno,gpclsno,gptitle,gpdesc,gpnote,gpbuilddate,joinstartdate,joinenddate,gpstartdate,gpenddate,gpmaxnum,rotno,gpauth) VALUES (gpseq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT gpno,memno,gpclsno,gptitle,gpdesc,gpnote,gpbuilddate,joinstartdate,joinenddate,gpstartdate,gpenddate,gpmaxnum,rotno,gpauth FROM gp order by gpno desc";
	private static final String GET_ONE_STMT = 
		"SELECT gpno,,memno,gpclsno,gptitle,gpdesc,gpnote,gpbuilddate,joinstartdate,joinenddate,gpstartdate,gpenddate,gpmaxnum,rotno,gpauth FROM gp where gpno = ?";
	private static final String DELETE_BYMEM = 
		"UPDATE gp set memno='' where gpno = ?";
	private static final String DELETE_MEMGP = "DELETE FROM memgp where gpno=?";	
	private static final String DELETE_GP = "DELETE FROM gp where gpno=?";	
	private static final String UPDATE = 
		"UPDATE gp set memno=?,gpclsno=?,gptitle=?,gpdesc=?,gpnote=?,joinstartdate=?,joinenddate=?,gpstartdate=?,gpenddate=?,gpmaxnum=?,rotno=?,gpauth=? where gpno = ?";
	private static final String UPDATE_GPCLSNO = 
			"UPDATE gp set gpclsno=? where gpno = ?";
	private static final String GET_GPSBYMEMNOFROMMEMGP_STMT = "SELECT gpno,memno,gpclsno,gptitle,gpdesc,gpnote,gpbuilddate,joinstartdate,joinenddate,gpstartdate,gpenddate,gpmaxnum,rotno,gpauth FROM gp where gpno =any(select gpno from Memgp where memno=?) order by gpno desc";
	private static final String GET_GPSBYMEMNO_STMT = "SELECT gpno,memno,gpclsno,gptitle,gpdesc,gpnote,gpbuilddate,joinstartdate,joinenddate,gpstartdate,gpenddate,gpmaxnum,rotno,gpauth FROM gp where memno =? order by gpno desc";
	private static final String GET_GPSBYGPCLSNO_STMT = "SELECT gpno,memno,gpclsno,gptitle,gpdesc,gpnote,gpbuilddate,joinstartdate,joinenddate,gpstartdate,gpenddate,gpmaxnum,rotno,gpauth FROM gp where gpclsno =? order by gpno desc";
	private static final String GET_GPSFROMREP_STMT = "SELECT gpno,memno,gpclsno,gptitle,gpdesc,gpnote,gpbuilddate,joinstartdate,joinenddate,gpstartdate,gpenddate,gpmaxnum,rotno,gpauth FROM gp where gpno =any(select gpno from Report) order by gpno desc";

	@Override
	public int insert(GpVO gpVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, gpVO.getMemno());
			pstmt.setInt(2, gpVO.getGpclsno());
			pstmt.setString(3, gpVO.getGptitle());
			pstmt.setString(4, gpVO.getGpdesc());
			pstmt.setString(5, gpVO.getGpnote());
			pstmt.setTimestamp(6, gpVO.getGpbuilddate());
			pstmt.setTimestamp(7, gpVO.getJoinstartdate());
			pstmt.setTimestamp(8, gpVO.getJoinenddate());
			pstmt.setTimestamp(9, gpVO.getGpstartdate());
			pstmt.setTimestamp(10, gpVO.getGpenddate());
			pstmt.setInt(11, gpVO.getGpmaxnum());
			pstmt.setInt(12, gpVO.getRotno());
			pstmt.setString(13, gpVO.getGpauth());			
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
		return 0;
	}

	@Override
	public void update(GpVO gpVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, gpVO.getMemno());
			pstmt.setInt(2, gpVO.getGpclsno());
			pstmt.setString(3, gpVO.getGptitle());
			pstmt.setString(4, gpVO.getGpdesc());
			pstmt.setString(5, gpVO.getGpnote());
			pstmt.setTimestamp(6, gpVO.getJoinstartdate());
			pstmt.setTimestamp(7, gpVO.getJoinenddate());
			pstmt.setTimestamp(8, gpVO.getGpstartdate());
			pstmt.setTimestamp(9, gpVO.getGpenddate());
			pstmt.setInt(10, gpVO.getGpmaxnum());
			pstmt.setInt(11, gpVO.getRotno());
			pstmt.setString(12, gpVO.getGpauth());
			pstmt.setInt(13, gpVO.getGpno());
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
	public void delete_ByMem(Integer gpno) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_BYMEM);

			pstmt.setInt(1, gpno);
			
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
	public void delete(Integer gpno) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			//先刪關係
			pstmt = con.prepareStatement(DELETE_MEMGP);
			pstmt.setInt(1, gpno);
			pstmt.executeUpdate();
			// 再刪除路線
			pstmt = con.prepareStatement(DELETE_GP);
			pstmt.setInt(1, gpno);
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
	public GpVO findByPrimaryKey(Integer gpno) {
		// TODO Auto-generated method stub

		GpVO gpVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, gpno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				gpVO = new GpVO();				
				gpVO.setGpno(rs.getInt("gpno"));
				gpVO.setMemno(rs.getInt("memno"));
				gpVO.setGpclsno(rs.getInt("gpclsno"));
				gpVO.setGptitle(rs.getString("gptitle"));
				gpVO.setGpdesc(rs.getString("gpdesc"));
				gpVO.setGpnote(rs.getString("gpnote"));
				gpVO.setGpbuilddate(rs.getTimestamp("gpbuilddate"));
				gpVO.setJoinstartdate(rs.getTimestamp("joinstartdate"));
				gpVO.setJoinenddate(rs.getTimestamp("joinenddate"));
				gpVO.setGpstartdate(rs.getTimestamp("gpstartdate"));
				gpVO.setGpenddate(rs.getTimestamp("gpenddate"));
				gpVO.setGpmaxnum(rs.getInt("gpmaxnum"));
				gpVO.setRotno(rs.getInt("rotno"));
				gpVO.setGpauth(rs.getString("gpauth"));
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
		return gpVO;
	}

	@Override
	public List<GpVO> getGpsBymemno(Integer memno) {
		// TODO Auto-generated method stub
		List<GpVO> list = new ArrayList<GpVO>();
		GpVO gpVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_GPSBYMEMNO_STMT);
			pstmt.setInt(1, memno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				gpVO = new GpVO();				
				gpVO.setGpno(rs.getInt("gpno"));
				gpVO.setMemno(rs.getInt("memno"));
				gpVO.setGpclsno(rs.getInt("gpclsno"));
				gpVO.setGptitle(rs.getString("gptitle"));
				gpVO.setGpdesc(rs.getString("gpdesc"));
				gpVO.setGpnote(rs.getString("gpnote"));
				gpVO.setGpbuilddate(rs.getTimestamp("gpbuilddate"));
				gpVO.setJoinstartdate(rs.getTimestamp("joinstartdate"));
				gpVO.setJoinenddate(rs.getTimestamp("joinenddate"));
				gpVO.setGpstartdate(rs.getTimestamp("gpstartdate"));
				gpVO.setGpenddate(rs.getTimestamp("gpenddate"));
				gpVO.setGpmaxnum(rs.getInt("gpmaxnum"));
				gpVO.setRotno(rs.getInt("rotno"));
				gpVO.setGpauth(rs.getString("gpauth"));
				list.add(gpVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<GpVO> getGpsBygpclsno(Integer gpclsno) {
		// TODO Auto-generated method stub
		List<GpVO> list = new ArrayList<GpVO>();
		GpVO gpVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_GPSBYGPCLSNO_STMT);
			pstmt.setInt(1, gpclsno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				gpVO = new GpVO();				
				gpVO.setGpno(rs.getInt("gpno"));
				gpVO.setMemno(rs.getInt("memno"));
				gpVO.setGpclsno(rs.getInt("gpclsno"));
				gpVO.setGptitle(rs.getString("gptitle"));
				gpVO.setGpdesc(rs.getString("gpdesc"));
				gpVO.setGpnote(rs.getString("gpnote"));
				gpVO.setGpbuilddate(rs.getTimestamp("gpbuilddate"));
				gpVO.setJoinstartdate(rs.getTimestamp("joinstartdate"));
				gpVO.setJoinenddate(rs.getTimestamp("joinenddate"));
				gpVO.setGpstartdate(rs.getTimestamp("gpstartdate"));
				gpVO.setGpenddate(rs.getTimestamp("gpenddate"));
				gpVO.setGpmaxnum(rs.getInt("gpmaxnum"));
				gpVO.setRotno(rs.getInt("rotno"));
				gpVO.setGpauth(rs.getString("gpauth"));
				list.add(gpVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<GpVO> getGpsBymemnoFromMemgp(Integer memno) {
		// TODO Auto-generated method stub
		List<GpVO> list = new ArrayList<GpVO>();
		GpVO gpVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_GPSBYMEMNOFROMMEMGP_STMT);
			pstmt.setInt(1,memno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				gpVO = new GpVO();				
				gpVO.setGpno(rs.getInt("gpno"));
				gpVO.setMemno(rs.getInt("memno"));
				gpVO.setGpclsno(rs.getInt("gpclsno"));
				gpVO.setGptitle(rs.getString("gptitle"));
				gpVO.setGpdesc(rs.getString("gpdesc"));
				gpVO.setGpnote(rs.getString("gpnote"));
				gpVO.setGpbuilddate(rs.getTimestamp("gpbuilddate"));
				gpVO.setJoinstartdate(rs.getTimestamp("joinstartdate"));
				gpVO.setJoinenddate(rs.getTimestamp("joinenddate"));
				gpVO.setGpstartdate(rs.getTimestamp("gpstartdate"));
				gpVO.setGpenddate(rs.getTimestamp("gpenddate"));
				gpVO.setGpmaxnum(rs.getInt("gpmaxnum"));
				gpVO.setRotno(rs.getInt("rotno"));
				gpVO.setGpauth(rs.getString("gpauth"));
				list.add(gpVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<GpVO> getAll() {
		// TODO Auto-generated method stub
		List<GpVO> list = new ArrayList<GpVO>();
		GpVO gpVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				gpVO = new GpVO();				
				gpVO.setGpno(rs.getInt("gpno"));
				gpVO.setMemno(rs.getInt("memno"));
				gpVO.setGpclsno(rs.getInt("gpclsno"));
				gpVO.setGptitle(rs.getString("gptitle"));
				gpVO.setGpdesc(rs.getString("gpdesc"));
				gpVO.setGpnote(rs.getString("gpnote"));
				gpVO.setGpbuilddate(rs.getTimestamp("gpbuilddate"));
				gpVO.setJoinstartdate(rs.getTimestamp("joinstartdate"));
				gpVO.setJoinenddate(rs.getTimestamp("joinenddate"));
				gpVO.setGpstartdate(rs.getTimestamp("gpstartdate"));
				gpVO.setGpenddate(rs.getTimestamp("gpenddate"));
				gpVO.setGpmaxnum(rs.getInt("gpmaxnum"));
				gpVO.setRotno(rs.getInt("rotno"));
				gpVO.setGpauth(rs.getString("gpauth"));
				list.add(gpVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public int updateGpByGpclsno(Integer gpclsno, Integer gpno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_GPCLSNO);
			
			pstmt.setInt(1, gpclsno);
			pstmt.setInt(2, gpno);
			int count=0;
			count=pstmt.executeUpdate();
			return count;
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
	public List<GpVO> getGpsFromrep() {
		// TODO Auto-generated method stub
		List<GpVO> list = new ArrayList<GpVO>();
		GpVO gpVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_GPSFROMREP_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				gpVO = new GpVO();				
				gpVO.setGpno(rs.getInt("gpno"));
				gpVO.setMemno(rs.getInt("memno"));
				gpVO.setGpclsno(rs.getInt("gpclsno"));
				gpVO.setGptitle(rs.getString("gptitle"));
				gpVO.setGpdesc(rs.getString("gpdesc"));
				gpVO.setGpnote(rs.getString("gpnote"));
				gpVO.setGpbuilddate(rs.getTimestamp("gpbuilddate"));
				gpVO.setJoinstartdate(rs.getTimestamp("joinstartdate"));
				gpVO.setJoinenddate(rs.getTimestamp("joinenddate"));
				gpVO.setGpstartdate(rs.getTimestamp("gpstartdate"));
				gpVO.setGpenddate(rs.getTimestamp("gpenddate"));
				gpVO.setGpmaxnum(rs.getInt("gpmaxnum"));
				gpVO.setRotno(rs.getInt("rotno"));
				gpVO.setGpauth(rs.getString("gpauth"));
				list.add(gpVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}

}
