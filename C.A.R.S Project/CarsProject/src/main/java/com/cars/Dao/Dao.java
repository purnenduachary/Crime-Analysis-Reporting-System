package com.cars.Dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import com.cars.model.Incident;
import com.cars.model.Reports;

public interface Dao {

	List<Incident> showAllIncident() throws SQLException, ClassNotFoundException;

	Collection<Incident> searchIncidentsByType(String incidentType) throws ClassNotFoundException, SQLException;

	Incident searchIncidentById(int incidentId) throws SQLException, ClassNotFoundException;
	
	boolean createIncident(Incident incident) throws SQLException, ClassNotFoundException;
	
	Collection<Incident> showIncidentInDateRange(Date startDate, Date endDate) throws SQLException, ClassNotFoundException;
	
	boolean updateIncidentStatus(int IncidentId, String status) throws ClassNotFoundException, SQLException;
	
	
	//Added Features
	Reports generateIncidentReportById(Incident incident) throws SQLException, ClassNotFoundException;
	//Added Features
	List<Reports> showAllReportSummary() throws SQLException, ClassNotFoundException;
}
