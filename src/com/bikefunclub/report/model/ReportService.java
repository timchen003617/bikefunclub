package com.bikefunclub.report.model;

import java.sql.Date;
import java.util.List;

public class ReportService {

	private ReportDAO_interface dao;

	public ReportService() {
		dao = new ReportDAO();
	}

	public ReportVO addReport(Integer repno, Integer memno, Integer empno,
			Integer byrepno, String reptext, String repcls, Integer repclsno,
			Date repdate,Date replydate,String repprogress,String represult) {

		ReportVO reportVO = new ReportVO();

		reportVO.setRepno(repno);
		reportVO.setMemno(memno);
		reportVO.setEmpno(empno);
		reportVO.setByrepno(byrepno);
		reportVO.setReptext(reptext);
		reportVO.setRepcls(repcls);
		reportVO.setRepclsno(repclsno);
		reportVO.setRepdate(repdate);
		reportVO.setReplydate(replydate);
		reportVO.setRepprogress(repprogress);
		reportVO.setRepresult(represult);
		
		dao.insert(reportVO);

		return reportVO;
	}

	// 預留給 Struts 2 用的
	public void addReport(ReportVO reportVO) {
		dao.insert(reportVO);
	}

	public ReportVO updateReport(Integer repno, Integer memno, Integer empno,
			Integer byrepno, String reptext, String repcls, Integer repclsno,
			Date repdate,Date replydate,String repprogress,String represult) {

		ReportVO reportVO = new ReportVO();

		reportVO.setRepno(repno);
		reportVO.setMemno(memno);
		reportVO.setEmpno(empno);
		reportVO.setByrepno(byrepno);
		reportVO.setReptext(reptext);
		reportVO.setRepcls(repcls);
		reportVO.setRepclsno(repclsno);
		reportVO.setRepdate(repdate);
		reportVO.setReplydate(replydate);
		reportVO.setRepprogress(repprogress);
		reportVO.setRepresult(represult);

		dao.update(reportVO);

		return dao.findByPrimaryKey(repno);
	}

	// 預留給 Struts 2 用的
	public void updateReport(ReportVO reportVO) {
		dao.update(reportVO);
	}

	public void deleteReport(Integer reportno) {
		dao.delete(reportno);
	}

	public ReportVO getOneReport(Integer reportno) {
		return dao.findByPrimaryKey(reportno);
	}

	public List<ReportVO> getAll() {
		return dao.getAll();
	}
}
