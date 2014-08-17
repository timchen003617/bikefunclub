package com.bikefunclub.member.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class MemService {

	private Mem_interface dao;

	public MemService() {
		dao = new MemDAO();
	}

	public MemVO addmem(String memacc, String mempw, String memname,
			String memid, Date membirth, String memnickname, byte[] memfile,
			String memfilename, String memextname, String mememail,
			String memsex, String memzip, String memaddr, String memtelh,
			String memtelo, String memtelm,String memgetmailyn,Timestamp memrgdate) {
		MemVO memVO = new MemVO();

		memVO.setMemacc(memacc);
		memVO.setMempw(mempw);
		memVO.setMemname(memname);
		memVO.setMemid(memid);
		memVO.setMembirth(membirth);
		memVO.setMemnickname(memnickname);
		memVO.setMemfile(memfile);
		memVO.setMemfilename(memfilename);
		memVO.setMemextname(memextname);
		memVO.setMememail(mememail);
		memVO.setMemsex(memsex);
		memVO.setMemzip(memzip);
		memVO.setMemaddr(memaddr);
		memVO.setMemtelh(memtelh);
		memVO.setMemtelo(memtelo);
		memVO.setMemtelm(memtelm);
		memVO.setMemgetmailyn(memgetmailyn);
		memVO.setMemrgdate(memrgdate);

		dao.insert(memVO);

		return memVO;
	}

	public MemVO updateMem(Integer memno, String memacc, String mempw,
			String memname, String memid, Date membirth, String memnickname,
			byte[] memfile, String memfilename, String memextname,
			String mememail, String memsex, String memzip, String memaddr,
			String memtelh, String memtelo, String memtelm) {

		MemVO memVO = new MemVO();
		memVO.setMemno(memno);
		memVO.setMemacc(memacc);
		memVO.setMempw(mempw);
		memVO.setMemname(memname);
		memVO.setMemid(memid);
		memVO.setMembirth(membirth);
		memVO.setMemnickname(memnickname);
		memVO.setMemfile(memfile);
		memVO.setMemfilename(memfilename);
		memVO.setMemextname(memextname);
		memVO.setMememail(mememail);
		memVO.setMemsex(memsex);
		memVO.setMemzip(memzip);
		memVO.setMemaddr(memaddr);
		memVO.setMemtelh(memtelh);
		memVO.setMemtelo(memtelo);
		memVO.setMemtelm(memtelm);
		dao.update(memVO);

		return memVO;
	}
	public MemVO updateMem_getmailyn(Integer memno,String memgetmailyn){
		MemVO memVO = new MemVO();
		
		memVO.setMemno(memno);
		memVO.setMemgetmailyn(memgetmailyn);
		dao.update_getmailyn(memVO);
		
		return memVO;
	}
	public void deletemem(Integer memno) {
		dao.delete(memno);
	}

	public MemVO getOnemem(Integer memno) {
		return dao.findByPrimaryKey(memno);
	}

	public List<MemVO> getAllfornewfri(Integer memno) {
		return dao.findByAllfornewfri(memno);
	}
	public List<MemVO> getAllfornewfribyname(Integer memno,String memname){
		return dao.findByAllfornewfriByname(memno,memname);
	}
	public MemVO findByAccount(String Account) {
		return dao.findByAccount(Account);
	}

	public List<MemVO> getAll() {
		return dao.getAll();
	}
}
