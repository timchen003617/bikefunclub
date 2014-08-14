package com.bikefunclub.gp.model;

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

public class GpDAO implements GpDAO_interface {
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
			"INSERT INTO gp (gpno,memno,gpclsno,gptitle,gpdesc,gpnote,gpbuilddate,joinstartdate,joinenddate,gpstartdate,gpenddate,gpmaxnum,rotno,gpauth) VALUES (gpseq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT gpno,memno,gpclsno,gptitle,gpdesc,gpnote,gpbuilddate,joinstartdate,joinenddate,gpstartdate,gpenddate,gpmaxnum,rotno,gpauth FROM gp order by gpno desc";
		private static final String GET_ONE_STMT = 
			"SELECT gpno,memno,gpclsno,gptitle,gpdesc,gpnote,gpbuilddate,joinstartdate,joinenddate,gpstartdate,gpenddate,gpmaxnum,rotno,gpauth FROM gp where gpno = ?";
		private static final String DELETE_BYMEM = 
			"UPDATE gp set memno='' where gpno = ?";
		private static final String DELETE_GPCOMM = "DELETE FROM gpcomm where gpno=?";
		private static final String DELETE_MEMGP = "DELETE FROM memgp where gpno=?";	
		private static final String DELETE_GP = "DELETE FROM gp where gpno=?";	
		private static final String UPDATE = 
			"UPDATE gp set memno=?,gpclsno=?,gptitle=?,gpdesc=?,gpnote=?,joinstartdate=?,joinenddate=?,gpstartdate=?,gpenddate=?,gpmaxnum=?,rotno=?,gpauth=? where gpno = ?";
		private static final String UPDATE_GPCLSNO = 
			"UPDATE gp set gpclsno=? where gpno=?";		
		private static final String GET_GPSBYMEMNOFROMMEMGP_STMT = "SELECT gpno,memno,gpclsno,gptitle,gpdesc,gpnote,gpbuilddate,joinstartdate,joinenddate,gpstartdate,gpenddate,gpmaxnum,rotno,gpauth FROM gp where gpno =any(select gpno from Memgp where memno=?) order by gpno desc";
		private static final String GET_GPSBYMEMNO_STMT = "SELECT gpno,memno,gpclsno,gptitle,gpdesc,gpnote,gpbuilddate,joinstartdate,joinenddate,gpstartdate,gpenddate,gpmaxnum,rotno,gpauth FROM gp where memno =? order by gpno desc";
		private static final String GET_GPSBYGPCLSNO_STMT = "SELECT gpno,memno,gpclsno,gptitle,gpdesc,gpnote,gpbuilddate,joinstartdate,joinenddate,gpstartdate,gpenddate,gpmaxnum,rotno,gpauth FROM gp where gpclsno =? order by gpno desc";
		private static final String GET_GPSFROMREP_STMT = "SELECT gpno,memno,gpclsno,gptitle,gpdesc,gpnote,gpbuilddate,joinstartdate,joinenddate,gpstartdate,gpenddate,gpmaxnum,rotno,gpauth FROM gp where gpno =any(select repclsno from Report where repcls='3') order by gpno desc";

		@Override
		public int insert(GpVO gpVO) {
			// TODO Auto-generated method stub

			Connection con = null;
			PreparedStatement pstmt = null;
			Integer next_gpno=0;

			try {
				con = ds.getConnection();
				String cols[] = {"GPNO"};
				pstmt = con.prepareStatement(INSERT_STMT,cols);

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
				
				ResultSet rs = pstmt.getGeneratedKeys();
				
				if (rs.next()) {
					next_gpno = rs.getInt(1);
					System.out.println("自增主鍵值= " + next_gpno +"(剛新增成功的部門編號)");
				} else {
					System.out.println("未取得自增主鍵值");
				}
				rs.close();
				

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
			return next_gpno;
		}

		@Override
		public void update(GpVO gpVO) {
			// TODO Auto-generated method stub

			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				con = ds.getConnection();
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
				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE_BYMEM);

				pstmt.setInt(1, gpno);
				
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
		public void delete(Integer gpno) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				con = ds.getConnection();
				//先刪關係
				pstmt = con.prepareStatement(DELETE_MEMGP);
				pstmt.setInt(1, gpno);
				pstmt.executeUpdate();
				//先刪關係
				pstmt = con.prepareStatement(DELETE_GPCOMM);
				pstmt.setInt(1, gpno);
				pstmt.executeUpdate();
				// 再刪除路線
				pstmt = con.prepareStatement(DELETE_GP);
				pstmt.setInt(1, gpno);
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
		public GpVO findByPrimaryKey(Integer gpno) {
			// TODO Auto-generated method stub

			GpVO gpVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
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
				con = ds.getConnection();
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
				con = ds.getConnection();
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
				con = ds.getConnection();
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
				con = ds.getConnection();
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
			// TODO Auto-generated method stub

			Connection con = null;
			PreparedStatement pstmt = null;
			int count=0;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_GPCLSNO);
				
				pstmt.setInt(1, gpclsno);
				pstmt.setInt(2, gpno);
				count=pstmt.executeUpdate();
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
			return count;
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
				con = ds.getConnection();				pstmt = con.prepareStatement(GET_GPSFROMREP_STMT);
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

}
