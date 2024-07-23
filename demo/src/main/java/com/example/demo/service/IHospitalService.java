package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Hospital;

public interface IHospitalService {

	Hospital saveRecord(Hospital hospital);

	List<Hospital> getAllHospitalRecord();

	Hospital getAllHospitalRecordByHospId(String hospId) throws Exception;

	List<Hospital> getHospitalRecordByHospNameAndState(String hospName, String state);

	List<Hospital> getHospitalRecordByHospType(String hospType);

	String deleteHospitalRecordByHospId(String hospId);

	List<Hospital> getHospitalRecordByHospName(String hospName);

	List<Hospital> getHospitalRecordByHospNameAndID(String hospName, Long distId);

	void updateHospName(String hospName, String hospId);

}
