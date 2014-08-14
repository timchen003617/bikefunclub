package com.bikefunclub.ad.model;

import java.util.List;

public class AdService {
	private AdDAO_interface dao;

	public AdService() {
		dao = new AdDAO();
	}

	public AdVO addAd(String adname, String adesc, byte[] adfile,
			String filename, String extname, String adurl) {

		AdVO adVO = new AdVO();

		adVO.setAdname(adname);
		adVO.setAdesc(adesc);
		adVO.setAdfile(adfile);
		adVO.setFilename(filename);
		adVO.setExtname(extname);
		adVO.setAdurl(adurl);
		dao.insert(adVO);

		return adVO;
	}

	// 預留給 Struts 2 用的
	public void addAd(AdVO adVO) {
		dao.insert(adVO);
	}

	public AdVO updateAd(Integer adno, String adname, String adesc,
			byte[] adfile, String filename, String extname, String adurl) {

		AdVO adVO = new AdVO();

		adVO.setAdno(adno);
		adVO.setAdname(adname);
		adVO.setAdesc(adesc);
		adVO.setAdfile(adfile);
		adVO.setFilename(filename);
		adVO.setExtname(extname);
		adVO.setAdurl(adurl);
		dao.update(adVO);

		return dao.findByPrimaryKey(adno);
	}

	// 預留給 Struts 2 用的
	public void updateAd(AdVO adVO) {
		dao.update(adVO);
	}

	public void deleteAd(Integer adno) {
		dao.delete(adno);
	}

	public AdVO getOneAd(Integer adno) {
		return dao.findByPrimaryKey(adno);
	}

	public List<AdVO> getAll() {
		return dao.getAll();
	}

}
