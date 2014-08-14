package com.bikefunclub.riderecord.model;

import java.util.List;

public interface RideRecordDAO_interface {
	public int insert(RideRecordVO rideRecordVO);
    public int update(RideRecordVO rideRecordVO);
    public int delete(Integer riderecordno);
    public RideRecordVO findByPrimaryKey(Integer riderecordno);
    public List<RideRecordVO> getAll();
}
