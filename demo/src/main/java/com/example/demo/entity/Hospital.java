package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

 

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="Hospital")
/*@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor */
@ToString
public class Hospital extends BaseEntity{

	@Id
	private String hospId;
	
	@NotBlank
	@Size(min = 3, max= 28, message="Hospital name should not be less then 3 ")
	@Column(name="HOSP_NAME")
	private String  hospName ;
	
	@Digits(message="Number should contain 6 digits.", fraction = 3, integer = 6)
	@Column(name="STATE_CODE")
	private Long stateCode ;
	
	@NotBlank(message = "state can not be a Blank")
	@NotEmpty(message = "state can not be a empty")
	@Column(name="STATE")
	private String  state ;
	
	@NotNull(message = "districtId can not be a null")

	@Digits(fraction = 3, integer = 6)
	@Column(name="DISTRICTID")
	private Long districtId;
	
	@Column(name="DISTRICT")
	private String  district;
	
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
	@Column(name="HOSP_CONTACT_NO")
	private String  hospContactNo;
	
	@Column(name="HOSP_ADDRESS")
	private String  hospAddress;
	
	@Column(name="HOSP_TYPE")
	private String  hospType;

	public String getHospName() {
		return hospName;
	}
	public void setHospName(String hospName) {
		this.hospName = hospName;
	}
	public Long getStateCode() {
		return stateCode;
	}
	public void setStateCode(Long stateCode) {
		this.stateCode = stateCode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getHospContactNo() {
		return hospContactNo;
	}
	public void setHospContactNo(String hospContactNo) {
		this.hospContactNo = hospContactNo;
	}
	public String getHospAddress() {
		return hospAddress;
	}
	public void setHospAddress(String hospAddress) {
		this.hospAddress = hospAddress;
	}
	public String getHospType() {
		return hospType;
	}
	public void setHospType(String hospType) {
		this.hospType = hospType;
	}
	public String getHospId() {
		return hospId;
	}
	public void setHospId(String hospId) {
		this.hospId = hospId;
	}
	
	@Override
	public String toString() {
		return "Hospital [hospId=" + hospId + ", hospName=" + hospName + ", stateCode=" + stateCode + ", state=" + state
				+ ", districtId=" + districtId + ", district=" + district + ", hospContactNo=" + hospContactNo
				+ ", hospAddress=" + hospAddress + ", hospType=" + hospType + "]";
	}
	public Hospital() {}
	public Hospital(String hospId, String hospName, Long stateCode, String state, Long districtId, String district,
			String hospContactNo, String hospAddress, String hospType) {
		super();
		this.hospId = hospId;
		this.hospName = hospName;
		this.stateCode = stateCode;
		this.state = state;
		this.districtId = districtId;
		this.district = district;
		this.hospContactNo = hospContactNo;
		this.hospAddress = hospAddress;
		this.hospType = hospType;
	}

}
