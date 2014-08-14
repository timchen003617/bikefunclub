package com.bikefunclub.emp.model;

import java.util.*;
import java.sql.*;
import java.sql.Date;

import com.bikefunclub.Ann.model.AnnJDBCDAO;

public class EmpJDBCDAO implements EmpDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String userid = "bikefunclub";
	String passwd = "bikefunclub";

	private static final String INSERT_STMT = 
		"INSERT INTO emp (empno,empacc,emppw,empname,empemail,empaddr,empid,emprgdate,emptel) VALUES (empseq.NEXTVAL, ?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT empno,empacc,emppw,empname,empemail,empaddr,empid,emprgdate,emptel FROM emp order by empno";
	private static final String GET_ONE_STMT = 
		"SELECT  empno,empacc,emppw,empname,empemail,empaddr,empid,emprgdate,emptel FROM emp where empno = ?";
	private static final String DELETE = 
		"DELETE FROM emp where empno = ?";
	private static final String UPDATE = 
		"UPDATE emp set empacc=?, emppw=?,empname=?,empemail=?,empaddr=?,empid=?,emprgdate=?,emptel=? where empno = ?";
	private static final String GET_ONE_STMT_ACCOUNT = "SELECT * FROM emp where empacc = ?";

	@Override
	public void insert(EmpVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, empVO.getEmpacc());
			pstmt.setString(2, empVO.getEmppw());
			pstmt.setString(3, empVO.getEmpname());
			pstmt.setString(4, empVO.getEmpemail());
			pstmt.setString(5, empVO.getEmpaddr());
			pstmt.setString(6, empVO.getEmpid());
			pstmt.setTimestamp  (7, empVO.getEmprgdate());
			pstmt.setString(8, empVO.getEmptel());			
			

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
	public void update(EmpVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, empVO.getEmpacc());
			pstmt.setString(2, empVO.getEmppw());
			pstmt.setString(3, empVO.getEmpname());
			pstmt.setString(4, empVO.getEmpemail());
			pstmt.setString(5, empVO.getEmpaddr());
			pstmt.setString(6, empVO.getEmpid());
			pstmt.setTimestamp(7, empVO.getEmprgdate());
			pstmt.setString(8, empVO.getEmptel());		
            pstmt.setInt(9, empVO.getEmpno());
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
	public void delete(Integer empno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, empno);

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
	public EmpVO findByPrimaryKey(Integer empno) {

		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, empno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				empVO = new EmpVO();
				empVO.setEmpno(rs.getInt("empno"));
				empVO.setEmpacc(rs.getString("empacc"));
				empVO.setEmppw(rs.getString("emppw"));
				empVO.setEmpname(rs.getString("empname"));
				empVO.setEmpemail(rs.getString("empemail"));
				empVO.setEmpaddr(rs.getString("empaddr"));
				empVO.setEmpid(rs.getString("empid"));
				empVO.setEmprgdate(rs.getTimestamp("emprgdate"));
				empVO.setEmptel(rs.getString("emptel"));
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
		return empVO;
	}

	@Override
	public List<EmpVO> getAll() {
		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				empVO = new EmpVO();
				empVO.setEmpno(rs.getInt("empno"));
				empVO.setEmpacc(rs.getString("empacc"));
				empVO.setEmppw(rs.getString("emppw"));
				empVO.setEmpname(rs.getString("empname"));
				empVO.setEmpemail(rs.getString("empemail"));
				empVO.setEmpaddr(rs.getString("empaddr"));
				empVO.setEmpid(rs.getString("empid"));
				empVO.setEmprgdate(rs.getTimestamp("emprgdate"));
				empVO.setEmptel(rs.getString("emptel"));
				list.add(empVO); // Store the row in the list
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
	public EmpVO findByAccount(String empacc) {

		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT_ACCOUNT);

			pstmt.setString(1, empacc);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				empVO = new EmpVO();
				empVO.setEmpno(rs.getInt("empno"));
				empVO.setEmpacc(rs.getString("empacc"));
				empVO.setEmppw(rs.getString("emppw"));
				empVO.setEmpname(rs.getString("empname"));
				empVO.setEmpemail(rs.getString("empemail"));
				empVO.setEmpaddr(rs.getString("empaddr"));
				empVO.setEmpid(rs.getString("empid"));
				empVO.setEmprgdate(rs.getTimestamp("emprgdate"));
				empVO.setEmptel(rs.getString("emptel"));
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
		return empVO;
	}
	
	

	public static void main(String[] args) {

		EmpJDBCDAO dao = new EmpJDBCDAO();
		 
		 //Timestamp
		 EmpJDBCDAO dao1 = new EmpJDBCDAO();
		 Date date = new Date(System.currentTimeMillis()); 
		 Timestamp emprgdate = Timestamp.valueOf(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
		

		//新增
//		EmpVO empVO1 = new EmpVO();
//		empVO1.setEmpacc("ming741");//如果要新增修改記得要改變其值
//		empVO1.setEmppw("MANAGER1");
//		empVO1.setEmpname("茅靖原");//如果要新增修改記得要改變其值
//		empVO1.setEmpemail("daniel10637@gmail.com");
//		empVO1.setEmpaddr("桃園縣中壢市中大路72號");
//		empVO1.setEmpid("D127529631");//如果要新增修改記得要改變其值
//		empVO1.setEmprgdate(emprgdate);
//		empVO1.setEmptel("03-26625022");
//		dao.insert(empVO1);

		//修改
//		EmpVO empVO2 = new EmpVO();
//		empVO2.setEmpacc("dani222");//如果要重複修改記得要改變其值
//		empVO2.setEmppw("MANAGER2");
//		empVO2.setEmpname("王曉明");//如果要重複修改記得要改變其值
//		empVO2.setEmpemail("daniel106378@gmail.com");
//		empVO2.setEmpaddr("桃園縣中壢市中大路25號");
//		empVO2.setEmpid("D127232213");//如果要重複修改記得要改變其值
//		empVO2.setEmprgdate(emprgdate);
//		empVO2.setEmptel("03-26562022");
//		empVO2.setEmpno(13);
//		dao.update(empVO2);

		//刪除
//		dao.delete(3);

		//查詢單一筆資訊
//		EmpVO empVO3 = dao.findByPrimaryKey(1);
//		System.out.print  (empVO3.getEmpno() + ",");
//		System.out.println(empVO3.getEmpacc());
//		System.out.print  (empVO3.getEmppw() + ",");
//		System.out.print  (empVO3.getEmpname() + ",");
//		System.out.print  (empVO3.getEmpemail() + ",");
//		System.out.print  (empVO3.getEmpaddr() + ",");
//		System.out.print  (empVO3.getEmpid() + ",");
//		System.out.println(empVO3.getEmprgdate());
//		System.out.println(empVO3.getEmptel());
//		System.out.println("---------------------");

		//查詢全部
		List<EmpVO> list = dao.getAll();
		for (EmpVO aEmp : list) {
			
			System.out.println(aEmp.getEmpacc());
			System.out.print  (aEmp.getEmppw() + ",");
			System.out.print  (aEmp.getEmpname() + ",");
			System.out.print  (aEmp.getEmpemail() + ",");
			System.out.print  (aEmp.getEmpaddr() + ",");
			System.out.print  (aEmp.getEmpid() + ",");
			System.out.println(aEmp.getEmprgdate());
			System.out.println(aEmp.getEmptel());
			System.out.print  (aEmp.getEmpno() + ",");
			System.out.println();
		}
	}
}