package com.bikefunclub.rot.model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RotDAO implements RotDAO_interface {
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
		"INSERT INTO rot (rotno,rotclsno,rotname,rotstart,rotend,rotloc,rottag,memno,rotstatus,rotauth,rotdesc) VALUES (rotseq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT rotno,rotclsno,rotname,rotstart,rotend,rotloc,rottag,memno,rotstatus,rotauth,rotdesc FROM rot where rotstatus!='0' order by rotno desc ";
	private static final String GET_ALLSG_STMT = 
			"SELECT rotno,rotclsno,rotname,rotstart,rotend,rotloc,rottag,memno,rotstatus,rotauth,rotdesc FROM rot where rotstatus!='0' and memno is null order by rotno desc ";
	
	private static final String GET_ONE_STMT = 
		"SELECT rotno,rotclsno,rotname,rotstart,rotend,rotloc,rottag,memno,rotstatus,rotauth,rotdesc FROM rot where rotno = ?";
	private static final String DELETE_BYMEM = 
			"UPDATE rot set rotstatus='0' where rotno = ?";
	
	private static final String DELETE_MEM = 
		"UPDATE rot set memno='' where rotno = ?";
	private static final String DELETE_MEMROT = "DELETE FROM memrot where rotno=?";	
	private static final String DELETE_ROT = "DELETE FROM rot where rotno=?";	
	private static final String UPDATE = 
		"UPDATE rot set rotclsno=?, rotname=?, rotstart=?,rotend=?,rotloc=?,rottag=?,memno=?,rotstatus=?,rotauth=?,rotdesc=? where rotno = ?";
	private static final String GET_ROTSBYMEMNOFROMMEMROT_STMT = "SELECT rotno,rotclsno,rotname,rotstart,rotend,rotloc,rottag,memno,rotstatus,rotauth,rotdesc FROM rot where rotno =any(select rotno from Memrot where memno=?) order by rotno desc";
	private static final String GET_ROTSBYMEMNO_STMT = "SELECT rotno,rotclsno,rotname,rotstart,rotend,rotloc,rottag,memno,rotstatus,rotauth,rotdesc FROM rot where memno =? and rotstatus!='0' order by rotno desc";
	private static final String GET_ROTSBYROTCLSNO_STMT = "SELECT rotno,rotclsno,rotname,rotstart,rotend,rotloc,rottag,memno,rotstatus,rotauth,rotdesc FROM rot where rotclsno =? and rotstatus!='0' and rotauth='PUBLIC' order by rotno desc";
	private static final String GET_ROTSBYROTCLSNOFROMBACK_STMT = "SELECT rotno,rotclsno,rotname,rotstart,rotend,rotloc,rottag,memno,rotstatus,rotauth,rotdesc FROM rot where rotclsno =? and rotstatus!='0' and memno is not null order by rotno desc";

	@Override
	public int insert(RotVO rotVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		Integer next_rotno=0;
		try {
			con = ds.getConnection();
			String cols[] = {"ROTNO"};
			pstmt = con.prepareStatement(INSERT_STMT,cols);

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
			
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				next_rotno = rs.getInt(1);
				System.out.println("自增主鍵值= " + next_rotno +"(剛新增成功的部門編號)");
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
		return next_rotno;
	}

	@Override
	public void update(RotVO rotVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
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
			con = ds.getConnection();
			//先刪關係
			pstmt = con.prepareStatement(DELETE_MEMROT);
			pstmt.setInt(1, rotno);
			pstmt.executeUpdate();
			// 再刪除路線
			pstmt = con.prepareStatement(DELETE_ROT);
			pstmt.setInt(1, rotno);
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
	public RotVO findByPrimaryKey(Integer rotno) {
		// TODO Auto-generated method stub

		RotVO rotVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
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
	
	public List<RotVO> getAllSg() {
		// TODO Auto-generated method stub
		List<RotVO> list = new ArrayList<RotVO>();
		RotVO rotVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALLSG_STMT);

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
	public List<RotVO> getAll() {
		// TODO Auto-generated method stub
		List<RotVO> list = new ArrayList<RotVO>();
		RotVO rotVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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
	public void delete_Mem(Integer rotno) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_MEM);

			pstmt.setInt(1, rotno);
			
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
	
	public void delete_ByMem(Integer rotno) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_BYMEM);

			pstmt.setInt(1, rotno);
			
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

	public List<RotVO> getRotsByrotclsnofromback(Integer rotclsno) {
		// TODO Auto-generated method stub
		List<RotVO> list = new ArrayList<RotVO>();
		RotVO rotVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ROTSBYROTCLSNOFROMBACK_STMT);
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
