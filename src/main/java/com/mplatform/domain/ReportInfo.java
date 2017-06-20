package com.mplatform.domain;

import java.sql.Timestamp;

public class ReportInfo {
	private Integer reporter;
	private String reporterName;
	private String readers;
	private String reportDate;
	private String reportWeek;
	private String reportMonth;
	private Timestamp reportTime;
	private String reportTimeStr;
	private String reportInfo;
	private String reportTips;
	private String reportImage;
	private String reportImageUrl;
	
	public Integer getReporter() {
		return reporter;
	}
	public void setReporter(Integer reporter) {
		this.reporter = reporter;
	}
	
	public String getReporterName() {
		return reporterName;
	}
	public void setReporterName(String reporterName) {
		this.reporterName = reporterName;
	}
	public String getReaders() {
		return readers;
	}
	public void setReaders(String readers) {
		this.readers = readers;
	}
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	public String getReportWeek() {
		return reportWeek;
	}
	public void setReportWeek(String reportWeek) {
		this.reportWeek = reportWeek;
	}
	public String getReportMonth() {
		return reportMonth;
	}
	public void setReportMonth(String reportMonth) {
		this.reportMonth = reportMonth;
	}
	public Timestamp getReportTime() {
		return reportTime;
	}
	public void setReportTime(Timestamp reportTime) {
		this.reportTime = reportTime;
	}
	public String getReportTimeStr() {
		return reportTimeStr;
	}
	public void setReportTimeStr(String reportTimeStr) {
		this.reportTimeStr = reportTimeStr;
	}
	public String getReportInfo() {
		return reportInfo;
	}
	public void setReportInfo(String reportInfo) {
		this.reportInfo = reportInfo;
	}
	public String getReportTips() {
		return reportTips;
	}
	public void setReportTips(String reportTips) {
		this.reportTips = reportTips;
	}
	public String getReportImage() {
		return reportImage;
	}
	public void setReportImage(String reportImage) {
		this.reportImage = reportImage;
	}
	public String getReportImageUrl() {
		return reportImageUrl;
	}
	public void setReportImageUrl(String reportImageUrl) {
		this.reportImageUrl = reportImageUrl;
	}
	@Override
	public String toString() {
		return "ReportInfo [reporter=" + reporter + ", reporterName=" + reporterName + ", readers=" + readers
				+ ", reportDate=" + reportDate + ", reportWeek=" + reportWeek + ", reportMonth=" + reportMonth
				+ ", reportTime=" + reportTime + ", reportTimeStr=" + reportTimeStr + ", reportInfo=" + reportInfo
				+ ", reportTips=" + reportTips + ", reportImage=" + reportImage + ", reportImageUrl=" + reportImageUrl
				+ "]";
	}
	
}
