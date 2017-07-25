package com.mplatform.domain;

public class CompanyInfo {

	private Integer companyId;
	private String companyName;
	
	
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	@Override
	public String toString() {
		return "CompanyInfo [companyId=" + companyId + ", companyName=" + companyName + "]";
	}
	
}
