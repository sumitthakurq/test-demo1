package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Hospital;
import com.example.demo.repository.IHospitalDao;


@Repository("hospitalService")
@Service("hospitalService")
public class HospitalService implements IHospitalService{

	
	@Autowired
	IHospitalDao hospitalDao ;
	
	@Override
	public Hospital saveRecord(Hospital hospital) {
		return hospitalDao.save(hospital);
		 
	}

	@Override
	public List<Hospital> getAllHospitalRecord() {
	//	return hospitalDao.findAll();
	//  return hospitalDao.findAll(Sort.by(Sort.Direction.ASC,"hospName"));
	//	return hospitalDao.findAll(Sort.by("hospName").descending());
    //	return hospitalDao.findAll(Sort.by("hospName").descending().and(Sort.by("district")));
		
		Pageable paging = PageRequest.of(1, 10, Sort.by("hospName"));
	  //  Pageable paging = PageRequest.of(1, 10);
	  //  Page<Hospital> pagedResult = hospitalDao.findAll(paging);
	 //   return pagedResult.toList();
		return hospitalDao.findAll(Sort.by("hospName"));
	
	}

	@Override
	public Hospital getAllHospitalRecordByHospId(String hospId) { 
		return hospitalDao.findById(hospId).get();
	}

	@Override
	public List<Hospital> getHospitalRecordByHospNameAndState(String hospName, String state) {
		return  hospitalDao.findByHospNameAndState(hospName ,state);
	}

	@Override
	public List<Hospital> getHospitalRecordByHospType(String hospType) {
		return  hospitalDao.findByHospTypeIgnoreCase(hospType);
	}

	@Override
	public String deleteHospitalRecordByHospId(String hospId) {
		 hospitalDao.deleteById(hospId);
		 return "Record Deleted Successfully" ;
	}

	@Override
	public List<Hospital> getHospitalRecordByHospName(String hospName ) {
		return hospitalDao.hname(hospName);
	}
	
	@Override
	public List<Hospital> getHospitalRecordByHospNameAndID(String hospName,Long distId) {
		return hospitalDao.getHospitalRecordByHospNameAndID(hospName, distId);
	}

	@Override
	@Transactional
	public void updateHospName(String hospName, String hospId) {
		 hospitalDao.updateHospName(hospName,hospId);
		 
	}


}
