package com.cars.Validation;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
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

	public List<Incident> searchIncidentByType(String incidentType) throws SQLException, ClassNotFoundException {
		return (List<Incident>) d.searchIncidentsByType(incidentType);
	}

	public Incident fetchIncidentById(int incidentId) throws SQLException, ClassNotFoundException {
		Dao dao = new DaoImpl();

		return dao.searchIncidentById(incidentId);
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

	// Incident Date range Validation
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

	public boolean updateIncidentValid(int IncidentId, String status) throws ClassNotFoundException, SQLException {

		if(statusUpdateVal(status)) {
			return d.updateIncidentStatus(IncidentId, status);
		}
		return false;
		
	}

	public boolean statusUpdateVal(String status) {
		List<String> validStatuses = Arrays.asList("Open", "Under Investigation", "Closed", "Resolved");
		if (status == null || !validStatuses.contains(status)) {
			return false;
		}
		return true;
	}

}
