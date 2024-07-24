package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Hospital;
import com.example.demo.exceptionHandler.BusinessException;
import com.example.demo.service.IHospitalService;

@RestController
@RequestMapping(value="/hospital")
@Validated
public class HospitalController {
	
	@Autowired
	@Qualifier("hospitalService")
	IHospitalService  hospitalService;
	
	public final Logger LOGGER =LoggerFactory.getLogger(HospitalController.class);
  	
	@GetMapping(path = "/getData")
	public String getData() {
		return "Sumit" ;
	}
	//  In below case path variable is optional 
	/*
	@GetMapping({"/myfoos/optional", "/myfoos/optional/{id}"})
	public String getFooByOptionalId(@PathVariable(required = false) String id){
	    return "ID: " + id;
	}
	*/
	
	
	
	@GetMapping("/getData1")
	public void  getData1() {
		LOGGER.info("Hospital Details Inside Get Data :");
 
		System.out.println("Sumit Kumar Singh");
		System.out.println("Sumit Kumar Singh888884");
	     
 
	}
	// @RequestMapping(value="/saveRecord" , method=RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE }, produces =  {MediaType.APPLICATION_JSON_VALUE })
	@PostMapping(value="/saveRecord")//, consumes = {MediaType.APPLICATION_JSON_VALUE }, produces =  {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Hospital> saveRecord(@Valid @RequestBody Hospital hospital) {
		LOGGER.info("Hospital Details :" +hospital);
		 
		if(hospital.getHospName() == null || hospital.getHospName().isEmpty()) {
			System.out.println("AAAAAAAAAAAAAAAA");
		throw new BusinessException("Sumit Bad Request");
		}
		 
		Hospital hosp = hospitalService.saveRecord(hospital); 
	//	return new ResponseEntity<>(hosp,HttpStatus.CREATED);
		return  ResponseEntity.status(HttpStatus.CREATED).body(hospital);
	}

	//@Cacheable(value = "Hospitals")
	@GetMapping(path="/getAllHospitalRecord")
	public List<Hospital> getAllHospitalRecord() {
		LOGGER.info(" Sumit getAllHospitalRecord Details :");
		return hospitalService.getAllHospitalRecord(); 
	}
//	@Cacheable(value = "Hospital", key = "#hospId")
	@GetMapping("/getHospitalRecordById/{hospId}")
	public Hospital getAllHospitalRecordByHospId(@PathVariable("hospId")String hospId) throws Exception {
		return hospitalService.getAllHospitalRecordByHospId(hospId); 
	}
	@CacheEvict(value = "Hospital", key = "#hospId")
	@DeleteMapping("/deleteHospitalRecordById/{hospId}")
	public String deleteHospitalRecordByHospId(@PathVariable("hospId")String hospId) {
		return hospitalService.deleteHospitalRecordByHospId(hospId); 
	}
//	@Cacheable(value = "Hospital", key = "#hospital.hospId")
	@GetMapping("/getHospitalRecordByHospNameAndState")
	public List<Hospital> getHospitalRecordByHospNameAndState(@RequestParam("hospName")String hospName, @RequestParam("state") String state) {
		return hospitalService.getHospitalRecordByHospNameAndState(hospName, state); 
	}
	
//	@Cacheable(value = "Hospital", key = "#Hospital.hospId")
	@GetMapping("/getHospitalRecordByHospName")
	public List<Hospital> getHospitalRecordByHospName(@RequestParam("hospName")String hospName) {
		return hospitalService.getHospitalRecordByHospName(hospName); 
	}
	// there is no default value for path variable 
//	@Cacheable(value = "Hospital", key = "#Hospital.hospId")
	@GetMapping("/getHospitalRecordByHospNameAndID")
	public List<Hospital> getHospitalRecordByHospNameAndID(@RequestParam("hospName")String hospName,@RequestParam(defaultValue ="21") Long distId) {
		return hospitalService.getHospitalRecordByHospNameAndID(hospName,  distId); 
	}
	
//	@Cacheable(value = "Hospital", key = "#Hospital.hospId")
	@GetMapping("/getHospitalRecordByHospType/{hospType}")
	public List<Hospital> getHospitalRecordByHospType(@PathVariable("hospType")String hospType) {
		LOGGER.info("Hospital Details :" +hospType);
		return hospitalService.getHospitalRecordByHospType(hospType); 
	}
//	@CachePut(value = "Hospital", key = "#hospital.hospId")
	@PutMapping("/updateHospitalRecordByHospId")
	public ResponseEntity<Hospital> updateHospitalRecordByHospId(@RequestBody Hospital hospital) {
		Hospital hosp = hospitalService.saveRecord(hospital); 
		return new ResponseEntity<Hospital>(hosp ,HttpStatus.CREATED); 
	}
//	@CachePut(value = "Hospital", key = "#Hospital.hospId")
//	@CachePut(value = "Hospital", key = "#hospId")
	@PutMapping(value="updateHospName/{hospId}")
	public ResponseEntity<String> updateHospName(@RequestParam String hospName, @PathVariable("hospId")String hospId) {
		hospitalService.updateHospName(hospName, hospId); 
		return new ResponseEntity<>("Record Updated Successfully" ,HttpStatus.OK); 
	}
}
