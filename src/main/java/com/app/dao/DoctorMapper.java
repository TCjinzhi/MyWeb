package com.app.dao;

import com.app.pojo.Doctor;

public interface DoctorMapper {
	Doctor selectByPrimaryKey(Integer id);
}
