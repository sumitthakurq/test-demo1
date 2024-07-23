package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Hospital;

//@Service
@Repository
public interface IHospitalDao extends JpaRepository<Hospital, String>{

	 List <Hospital> findByHospName(String hospName);
	 // @Query(value ="select h from HOSPITAL h where h.hospName = ?1", nativeQuery = true) // to enable native query
	// @Query("select h from Hospital h where h.hospName like %?1")
	// @Query("select h from Hospital h where h.hospName like %?1 Order by hospName asc")
	 /*  @Query("select h from Hospital h where h.hospName = ?1")
	 List <Hospital> hname(String hospName);*/
	 
	 @Query("select h from Hospital h where h.hospName =:hospName")
	 List <Hospital> hname(@Param("hospName") String hospName);
	 
	 @Query(value="select h from Hospital h where h.hospName =:hospName and districtId=:distId")
	 List <Hospital> getHospitalRecordByHospNameAndID(@Param("hospName") String hospName, @Param("distId") Long distId);
	
	 
	List<Hospital> findByHospTypeIgnoreCase(String hospType);

	List<Hospital> findByHospNameAndState(String hospName, String state);
	
	@Modifying(clearAutomatically = true)
	@Query("update Hospital hospital set hospital.hospName =:hospName where hospital.hospId=:hospId")
	void updateHospName(@Param(value = "hospName") String hospName, @Param(value = "hospId") String hospId);

}
