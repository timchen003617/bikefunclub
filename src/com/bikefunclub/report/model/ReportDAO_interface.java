package com.bikefunclub.report.model;

import java.util.List;

public interface ReportDAO_interface {
	public void insert(ReportVO reportVo);

	public void update(ReportVO reportVo);

	public void delete(Integer repno);

	public ReportVO findByPrimaryKey(Integer repno);

	public List<ReportVO> getAll();

}
