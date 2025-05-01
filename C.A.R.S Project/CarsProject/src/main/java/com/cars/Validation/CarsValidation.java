package com.cars.Validation;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
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
		return (List<Incident>) d.searchIncidentsByType(incidentType);
	}

	// 3. Search by Incident ID
	public String fetchIncidentById(int incidentId) throws SQLException, ClassNotFoundException {
		try {
			return d.searchIncidentById(incidentId);
		} catch (IllegalArgumentException e) {
			return "Incident not found: " + e.getMessage();
		}
	}

	public String createIncident(Incident incident) {
		try {
			boolean result = d.createIncident(incident);
			return result ? "Incident created successfully." : "Incident creation failed.";
		} catch (Exception e) {
			return "Error while creating incident: " + e.getMessage();
		}
	}

	public Reports generateIncidentReport(Incident incident) throws SQLException, ClassNotFoundException {
		return d.generateIncidentReportById(incident);
	}

	public List<Reports> getReportSummary() throws ClassNotFoundException, SQLException {
		return d.showAllReportSummary();
	}

	public Date validateDate(String input) {
		try {
			return Date.valueOf(input); // converts YYYY-MM-DD to java.sql.Date
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid date format. Please use YYYY-MM-DD.");
			return null;
		}
	}
	
	
	//Incident Date range Validation
	public Collection<Incident> getIncidentsInRange(String startInput, String endInput) {
	    try {
	        Date startDate = Date.valueOf(startInput);
	        Date endDate = Date.valueOf(endInput);

	        if (startDate.after(endDate)) {
	            System.out.println("Start date cannot be after end date.");
	            return Collections.emptyList();
	        }
	        
	        System.out.println("Fetching incidents between " + startDate + " and " + endDate);
	        return d.showIncidentInDateRange(startDate, endDate);

	        
	    } catch (IllegalArgumentException e) {
	        System.out.println("Invalid date format. Please enter dates in YYYY-MM-DD format.");
	        return Collections.emptyList();
	        
	    } catch (Exception e) {
	        System.out.println("Unexpected error fetching incidents: " + e.getMessage());
	        return Collections.emptyList();
	    }
	}

	
	public String updateIncidentValid(int IncidentId, String status) {
		
		try {
	        boolean result = d.updateIncidentStatus(IncidentId, status);
	        return result ? "Incident status updated successfully." : "Incident not found or update failed.";
	    } catch (Exception e) {
	        return "Error updating incident status: " + e.getMessage();
	    }
	}
	

}
