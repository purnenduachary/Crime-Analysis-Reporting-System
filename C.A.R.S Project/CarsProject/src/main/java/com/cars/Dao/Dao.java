package com.cars.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.cars.model.Incident;
import com.cars.model.Reports;

public interface Dao {

	List<Incident> showAllIncident() throws SQLException, ClassNotFoundException;

	Collection<Incident> searchIncidents(String incidentType) throws ClassNotFoundException, SQLException;

	String searchIncidentDao(int incidentId) throws SQLException, ClassNotFoundException;
	
	Reports generateIncidentReport(Incident incident) throws SQLException, ClassNotFoundException;

	
	List<Reports> showAllReportSummary() throws SQLException, ClassNotFoundException;
}
