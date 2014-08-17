package com.bikefunclub.riderecord.model;

import java.util.List;

public class RideRecordService {
	private RideRecordDAO_interface dao;
	public RideRecordService(){
		dao=new RideRecordDAO();
	}
	public RideRecordVO insert(RideRecordVO rideRecordVO) {
		dao.insert(rideRecordVO);
		return rideRecordVO;
	}
    public RideRecordVO update(RideRecordVO rideRecordVO) {
    	dao.update(rideRecordVO);
		return rideRecordVO;
	}
    public void delete(Integer riderecordno) {
    	dao.delete(riderecordno);
    }
    public RideRecordVO findByPrimaryKey(Integer riderecordno) {
		return dao.findByPrimaryKey(riderecordno);
	}
    public List<RideRecordVO> getAll() {
		return dao.getAll();
	}
    
    public List<RideRecordVO> getrotrcds_bymemno(Integer memno){
		
    	return dao.getrotrcds_bymemno(memno);    	
    }

}
