package com.cars.model;

import java.sql.Date;

import lombok.Data;

@Data
public class Reports {
	
	private int ReportId;
	private int IncidentId;
	private int ReportingOfficer;
	private Date ReportDate;
	private String ReportDetails;
	private String Status;

}
