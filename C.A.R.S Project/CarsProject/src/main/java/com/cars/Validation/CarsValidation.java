package com.cars.Validation;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import com.cars.Dao.Dao;
import com.cars.Dao.DaoImpl;
import com.cars.model.Incident;
import com.cars.model.Reports;

public class CarsValidation {

	static Dao d;

	static {
		d = new DaoImpl();
	}

	public List<Incident> getAllIncidents() throws SQLException, ClassNotFoundException {
		return d.showAllIncident();
	}

	// 2. Search by Incident Type
	public List<Incident> searchIncidentByType(String incidentType) throws SQLException, ClassNotFoundException {
		return (List<Incident>) d.searchIncidents(incidentType);
	}

	// 3. Search by Incident ID
	public String fetchIncidentById(int incidentId) throws SQLException, ClassNotFoundException {
		try {
			return d.searchIncidentDao(incidentId);
		} catch (ClassNotFoundException e) {
			return "Incident not found: " + e.getMessage();
		}
	}
	
	public Reports generateIncidentReport(Incident incident) throws SQLException, ClassNotFoundException {
        return d.generateIncidentReport(incident);
    }
	
	public List<Reports> getReportSummary() throws ClassNotFoundException, SQLException{
		return d.showAllReportSummary();
	}

}
