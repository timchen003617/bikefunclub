package com.bikefunclub.rot.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RotJDBCDAO implements RotDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String userid = "bikefunclub";
	String passwd = "bikefunclub";

	private static final String INSERT_STMT = 
		"INSERT INTO rot (rotno,rotclsno,rotname,rotstart,rotend,rotloc,rottag,memno,rotstatus,rotauth,rotdesc) VALUES (rotseq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT rotno,rotclsno,rotname,rotstart,rotend,rotloc,rottag,memno,rotstatus,rotauth,rotdesc FROM rot order by rotno desc";
	private static final String GET_ONE_STMT = 
		"SELECT rotno,rotclsno,rotname,rotstart,rotend,rotloc,rottag,memno,rotstatus,rotauth,rotdesc FROM rot where rotno = ?";
	private static final String DELETE_BYMEM = 
		"UPDATE rot set memno='' where rotno = ?";
	private static final String DELETE_MEMROT = "DELETE FROM memrot where rotno=?";	
	private static final String DELETE_ROT = "DELETE FROM rot where rotno=?";	
	private static final String UPDATE = 
		"UPDATE rot set rotclsno=?, rotname=?, rotstart=?,rotend=?,rotloc=?,rottag=?,memno=?,rotstatus=?,rotauth=?,rotdesc=? where rotno = ?";
	private static final String GET_ROTSBYMEMNOFROMMEMROT_STMT = "SELECT rotno,rotclsno,rotname,rotstart,rotend,rotloc,rottag,memno,rotstatus,rotauth,rotdesc FROM rot where rotno =any(select rotno from Memrot where memno=?) order by rotno desc";
	private static final String GET_ROTSBYMEMNO_STMT = "SELECT rotno,rotclsno,rotname,rotstart,rotend,rotloc,rottag,memno,rotstatus,rotauth,rotdesc FROM rot where memno =? order by rotno desc";
	private static final String GET_ROTSBYROTCLSNO_STMT = "SELECT rotno,rotclsno,rotname,rotstart,rotend,rotloc,rottag,memno,rotstatus,rotauth,rotdesc FROM rot where rotclsno =? order by rotno desc";

	@Override
	public int insert(RotVO rotVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, rotVO.getRotclsno());
			pstmt.setString(2, rotVO.getRotname());
			pstmt.setString(3, rotVO.getRotstart());
			pstmt.setString(4, rotVO.getRotend());
			pstmt.setString(5, rotVO.getRotloc());
			pstmt.setString(6, rotVO.getRottag());
			pstmt.setInt(7, rotVO.getMemno());
			pstmt.setString(8, rotVO.getRotstatus());
			pstmt.setString(9, rotVO.getRotauth());
			pstmt.setString(10, rotVO.getRotdesc());
			
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
	public void update(RotVO rotVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, rotVO.getRotclsno());
			pstmt.setString(2, rotVO.getRotname());
			pstmt.setString(3, rotVO.getRotstart());
			pstmt.setString(4, rotVO.getRotend());
			pstmt.setString(5, rotVO.getRotloc());
			pstmt.setString(6, rotVO.getRottag());
			pstmt.setInt(7, rotVO.getMemno());
			pstmt.setString(8, rotVO.getRotstatus());
			pstmt.setString(9, rotVO.getRotauth());
			pstmt.setString(10, rotVO.getRotdesc());
			pstmt.setInt(11, rotVO.getRotno());
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
	public void delete(Integer rotno) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			//先刪關係
			pstmt = con.prepareStatement(DELETE_MEMROT);
			pstmt.setInt(1, rotno);
			pstmt.executeUpdate();
			// 再刪除路線
			pstmt = con.prepareStatement(DELETE_ROT);
			pstmt.setInt(1, rotno);
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
	public RotVO findByPrimaryKey(Integer rotno) {
		// TODO Auto-generated method stub

		RotVO rotVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, rotno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				rotVO = new RotVO();				
				rotVO.setRotno(rs.getInt("rotno"));
				rotVO.setRotclsno(rs.getInt("rotclsno"));
				rotVO.setRotname(rs.getString("rotname"));
				rotVO.setRotstart(rs.getString("rotstart"));
				rotVO.setRotend(rs.getString("rotend"));
				rotVO.setRotloc(rs.getString("rotloc"));
				rotVO.setRottag(rs.getString("rottag"));
				rotVO.setMemno(rs.getInt("memno"));
				rotVO.setRotstatus(rs.getString("rotstatus"));
				rotVO.setRotauth(rs.getString("rotauth"));
				rotVO.setRotdesc(rs.getString("rotdesc"));
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
		return rotVO;
	}

	@Override
	public List<RotVO> getAll() {
		// TODO Auto-generated method stub
		List<RotVO> list = new ArrayList<RotVO>();
		RotVO rotVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rotVO = new RotVO();				
				rotVO.setRotno(rs.getInt("rotno"));
				rotVO.setRotclsno(rs.getInt("rotclsno"));
				rotVO.setRotname(rs.getString("rotname"));
				rotVO.setRotstart(rs.getString("rotstart"));
				rotVO.setRotend(rs.getString("rotend"));
				rotVO.setRotloc(rs.getString("rotloc"));
				rotVO.setRottag(rs.getString("rottag"));
				rotVO.setMemno(rs.getInt("memno"));
				rotVO.setRotstatus(rs.getString("rotstatus"));
				rotVO.setRotauth(rs.getString("rotauth"));
				rotVO.setRotdesc(rs.getString("rotdesc"));
				list.add(rotVO); // Store the row in the list
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
	public List<RotVO> getRotsBymemno(Integer memno) {
		// TODO Auto-generated method stub
		List<RotVO> list = new ArrayList<RotVO>();
		RotVO rotVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ROTSBYMEMNO_STMT);
			pstmt.setInt(1, memno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rotVO = new RotVO();				
				rotVO.setRotno(rs.getInt("rotno"));
				rotVO.setRotclsno(rs.getInt("rotclsno"));
				rotVO.setRotname(rs.getString("rotname"));
				rotVO.setRotstart(rs.getString("rotstart"));
				rotVO.setRotend(rs.getString("rotend"));
				rotVO.setRotloc(rs.getString("rotloc"));
				rotVO.setRottag(rs.getString("rottag"));
				rotVO.setMemno(rs.getInt("memno"));
				rotVO.setRotstatus(rs.getString("rotstatus"));
				rotVO.setRotauth(rs.getString("rotauth"));
				rotVO.setRotdesc(rs.getString("rotdesc"));
				list.add(rotVO); // Store the row in the list
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
	public List<RotVO> getRotsByrotclsno(Integer rotclsno) {
		// TODO Auto-generated method stub
		List<RotVO> list = new ArrayList<RotVO>();
		RotVO rotVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ROTSBYROTCLSNO_STMT);
			pstmt.setInt(1, rotclsno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rotVO = new RotVO();				
				rotVO.setRotno(rs.getInt("rotno"));
				rotVO.setRotclsno(rs.getInt("rotclsno"));
				rotVO.setRotname(rs.getString("rotname"));
				rotVO.setRotstart(rs.getString("rotstart"));
				rotVO.setRotend(rs.getString("rotend"));
				rotVO.setRotloc(rs.getString("rotloc"));
				rotVO.setRottag(rs.getString("rottag"));
				rotVO.setMemno(rs.getInt("memno"));
				rotVO.setRotstatus(rs.getString("rotstatus"));
				rotVO.setRotauth(rs.getString("rotauth"));
				rotVO.setRotdesc(rs.getString("rotdesc"));
				list.add(rotVO); // Store the row in the list
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
	public List<RotVO> getrotsBymemnoFromMemrot(Integer memno) {
		// TODO Auto-generated method stub
		List<RotVO> list = new ArrayList<RotVO>();
		RotVO rotVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ROTSBYMEMNOFROMMEMROT_STMT);
			pstmt.setInt(1,memno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rotVO = new RotVO();				
				rotVO.setRotno(rs.getInt("rotno"));
				rotVO.setRotclsno(rs.getInt("rotclsno"));
				rotVO.setRotname(rs.getString("rotname"));
				rotVO.setRotstart(rs.getString("rotstart"));
				rotVO.setRotend(rs.getString("rotend"));
				rotVO.setRotloc(rs.getString("rotloc"));
				rotVO.setRottag(rs.getString("rottag"));
				rotVO.setMemno(rs.getInt("memno"));
				rotVO.setRotstatus(rs.getString("rotstatus"));
				rotVO.setRotauth(rs.getString("rotauth"));
				rotVO.setRotdesc(rs.getString("rotdesc"));
				list.add(rotVO); // Store the row in the list
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
	public void delete_ByMem(Integer rotno) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_BYMEM);

			pstmt.setInt(1, rotno);
			
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

    	RotJDBCDAO dao = new RotJDBCDAO();
    	
	// 新增
//
//	RotVO rotVO1 = new RotVO();	
//	rotVO1.setRotclsno(3);
//	rotVO1.setRotname("中央大學");
//	rotVO1.setRotstart("(22.6717, 120.3094)");
//	rotVO1.setRotend("(22.6837, 120.2988)");
//	rotVO1.setRotloc("(22.6717, 120.3094);(22.6772, 120.2975);(22.6772, 120.2975);(22.6784, 120.2943);(22.6784, 120.2943);(22.6860, 120.2959);(22.6788, 120.2972);(22.6837, 120.2988)");
//	rotVO1.setRottag("");
//	rotVO1.setMemno(2);
//	rotVO1.setRotstatus("1");
//	rotVO1.setRotauth("PUBLIC");
//	rotVO1.setRotdesc("testtttt");
//	dao.insert(rotVO1);
	
	// 修改
//	RotVO rotVO2 = new RotVO();	
//	rotVO2.setRotno(5);
//	rotVO2.setRotclsno(3);
//	rotVO2.setRotname("中央大學");
//	rotVO2.setRotstart("(22.6717, 120.3094)");
//	rotVO2.setRotend("(22.6837, 120.2988)");
//	rotVO2.setRotloc("(22.6717, 120.3094);(22.6837, 120.2988)");
//	rotVO2.setRottag("");
//	rotVO2.setMemno(2);
//	rotVO2.setRotstatus("1");
//	rotVO2.setRotauth("PUBLIC");
//	rotVO2.setRotdesc("test");
//	dao.update(rotVO2);*/


	//dao.delete_ByMem(1);

	// 刪除
	//dao.delete(6);

	// 查詢
//	RotVO rotVO3 = dao.findByPrimaryKey(2);
//	System.out.print(rotVO3.getRotno() + ",");
//	System.out.print(rotVO3.getRotclsno() + ",");
//	System.out.print(rotVO3.getRotname() + ",");
//	System.out.print(rotVO3.getRotstart() + ",");
//	System.out.print(rotVO3.getRotend());
//	System.out.print(rotVO3.getRotloc() + ",");
//	System.out.print(rotVO3.getRottag() + ",");
//	System.out.print(rotVO3.getMemno() + ",");
//	System.out.print(rotVO3.getRotstatus());
//	System.out.print(rotVO3.getRotauth() + ",");
//	System.out.print(rotVO3.getRotdesc() + ",");
//	System.out.println("---------------------");

	// 查詢
//	List<RotVO> list = dao.getAll();
//    List<RotVO> list = dao.getRotsBymemno(2);
//    List<RotVO> list = dao.getRotsByrotclsno(3);
    List<RotVO> list = dao.getrotsBymemnoFromMemrot(1);
	for (RotVO aRot : list) {
	
		System.out.print(aRot.getRotno() + ",");
		System.out.print(aRot.getRotclsno() + ",");
		System.out.print(aRot.getRotname() + ",");
		System.out.print(aRot.getRotstart() + ",");
		System.out.print(aRot.getRotend());
		System.out.print(aRot.getRotloc() + ",");
		System.out.print(aRot.getRottag() + ",");
		System.out.print(aRot.getMemno() + ",");
		System.out.print(aRot.getRotstatus());
		System.out.print(aRot.getRotauth() + ",");
		System.out.print(aRot.getRotdesc() + ",");

		System.out.println();
	}
  }

	@Override
	public List<RotVO> getRotsByrotclsnofromback(Integer rotclsno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RotVO> getAllSg() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete_Mem(Integer rotno) {
		// TODO Auto-generated method stub
		
	}
}
