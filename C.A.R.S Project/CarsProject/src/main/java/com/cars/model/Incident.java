package com.cars.model;

import java.sql.Date;

import lombok.Data;

@Data
public class Incident {
	
	private int IncidentId;
	private String IncidentType;
	private Date IncidentDate;
	private double Latitude;
	private double Longitude;
	private String Description;
	private String Status;
	private int VictimId;
	private int SuspectId;
	private int officerId;

}
