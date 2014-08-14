package com.bikefunclub.Ann.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class AnnService {
	private AnnDAO_interface dao;

	public AnnService() {
		dao = new AnnDAO();
	}

	public AnnVO addAnn(Integer empno, String anntitle,
			String anncontent, Timestamp anndate, byte[] annfile,
			String annfilename, String annextname) {

		AnnVO annVO = new AnnVO();

		annVO.setEmpno(empno);
		annVO.setAnntitle(anntitle);
		annVO.setAnncontent(anncontent);
		annVO.setAnndate(anndate);
		annVO.setAnnfile(annfile);
		annVO.setAnnfilename(annfilename);
		annVO.setAnnextname(annextname);
		dao.insert(annVO);

		return annVO;
	}

	// 預留給 Struts 2 用的
	public void addAnn(AnnVO annVO) {
		dao.insert(annVO);
	}

	public AnnVO updateAnn(Integer annno, Integer empno, String anntitle,
			String anncontent, Timestamp anndate, byte[] annfile,
			String annfilename, String annextname) {

		AnnVO annVO = new AnnVO();

		annVO.setAnnno(annno);
		annVO.setEmpno(empno);
		annVO.setAnntitle(anntitle);
		annVO.setAnncontent(anncontent);
		annVO.setAnndate(anndate);
		annVO.setAnnfile(annfile);
		annVO.setAnnfilename(annfilename);
		annVO.setAnnextname(annextname);
		dao.update(annVO);

		return dao.findByPrimaryKey(annno);
	}

	// 預留給 Struts 2 用的
	public void updateAnn(AnnVO annVO) {
		dao.update(annVO);
	}

	public void deleteAnn(Integer annno) {
		dao.delete(annno);
	}

	public AnnVO getOneAnn(Integer annno) {
		return dao.findByPrimaryKey(annno);
	}

	public List<AnnVO> getAll() {
		return dao.getAll();
	}

}
