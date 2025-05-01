package com.cars.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.cars.model.Incident;
import com.cars.model.Reports;
import com.cars.util.ConnectionHelper;

public class DaoImpl implements Dao {

	Connection conn;
	PreparedStatement pst;
	String sql;

	@Override
	public List<Incident> showAllIncident() throws SQLException, ClassNotFoundException {
		List<Incident> inclist = new ArrayList<>();
		sql = "select * from Incidents";
		conn = ConnectionHelper.getConnection();
		pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();

		while (rs.next()) {
			Incident inc = new Incident();
			inc.setIncidentId(rs.getInt("IncidentID"));
			inc.setIncidentType(rs.getString("IncidentType"));
			inc.setIncidentDate(rs.getDate("IncidentDate"));
			inc.setLatitude(rs.getDouble("Latitude"));
			inc.setLongitude(rs.getDouble("Longitude"));
			inc.setDescription(rs.getString("Description"));
			inc.setStatus(rs.getString("Status"));
			inc.setVictimId(rs.getInt("VictimID"));
			inc.setSuspectId(rs.getInt("SuspectID"));
			inc.setOfficerId(rs.getInt("OfficerID"));

			inclist.add(inc);
		}

		return inclist;
	}

	@Override
	public Collection<Incident> searchIncidents(String IncidentType) throws ClassNotFoundException, SQLException {
		List<Incident> resultList = new ArrayList<>();
		conn = ConnectionHelper.getConnection();
		sql = "select * from Incidents where IncidentType LIKE ?";
		pst = conn.prepareStatement(sql);
		//Wildcards used for anonymous output
		pst.setString(1, "%" + IncidentType + "%");

		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			Incident inc = new Incident();
			inc.setIncidentId(rs.getInt("IncidentID"));
			inc.setIncidentType(rs.getString("IncidentType"));
			inc.setIncidentDate(rs.getDate("IncidentDate"));
			inc.setLatitude(rs.getDouble("Latitude"));
			inc.setLongitude(rs.getDouble("Longitude"));
			inc.setDescription(rs.getString("Description"));
			inc.setStatus(rs.getString("Status"));
			inc.setVictimId(rs.getInt("VictimID"));
			inc.setSuspectId(rs.getInt("SuspectID"));
			inc.setOfficerId(rs.getInt("OfficerID"));
			resultList.add(inc);
		}
		return resultList;
	}

	@Override
	public String searchIncidentDao(int incidentId) throws SQLException, ClassNotFoundException {
		sql = "Select * from Incidents where IncidentID = ?";
		conn = ConnectionHelper.getConnection();
		pst = conn.prepareStatement(sql);
		pst.setInt(1, incidentId);

		ResultSet rs = pst.executeQuery();

		if (rs.next()) {
			System.out.println("----------- Incident Details -----------");
			System.out.println("Incident ID    : " + rs.getInt("IncidentID"));
			System.out.println("Type           : " + rs.getString("IncidentType"));
			System.out.println("Date           : " + rs.getDate("IncidentDate"));
			System.out.println("Latitude       : " + rs.getDouble("Latitude"));
			System.out.println("Longitude      : " + rs.getDouble("Longitude"));
			System.out.println("Description    : " + rs.getString("Description"));
			System.out.println("Status         : " + rs.getString("Status"));
			System.out.println("Victim ID      : " + rs.getInt("VictimID"));
			System.out.println("Suspect ID     : " + rs.getInt("SuspectID"));
			System.out.println("Officer ID     : " + rs.getInt("OfficerID"));
			return "Incident Found Successfully";
		} else {
			throw new ClassNotFoundException("Incident ID " + incidentId + " not found.");
		}
	}

	@Override
	public List<Reports> showAllReportSummary() throws SQLException, ClassNotFoundException {
		List<Reports> reportlist = new ArrayList<Reports>();
		sql = "select * from Reports";
		conn = ConnectionHelper.getConnection();
		pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();

		while (rs.next()) {
			Reports report = new Reports();


			report.setReportId(rs.getInt("ReportId"));
			report.setIncidentId(rs.getInt("IncidentId"));
			report.setReportingOfficer(rs.getInt("ReportingOfficer"));
			report.setReportDate(rs.getDate("ReportDate"));
			report.setReportDetails(rs.getString("ReportDetails"));
			report.setStatus(rs.getString("Status"));
			reportlist.add(report);

		}
		return reportlist;
	}

	@Override
	public Reports generateIncidentReport(Incident incident) throws SQLException, ClassNotFoundException {
		sql = "SELECT * FROM Reports WHERE IncidentID = ?";
		conn = ConnectionHelper.getConnection();
		pst = conn.prepareStatement(sql);
		pst.setInt(1, incident.getIncidentId());
		ResultSet rs = pst.executeQuery();

		if (rs.next()) {
			Reports report = new Reports();
			report.setReportId(rs.getInt("ReportID"));
			report.setIncidentId(rs.getInt("IncidentID"));
			report.setReportingOfficer(rs.getInt("ReportingOfficer"));
			report.setReportDate(rs.getDate("ReportDate"));
			report.setReportDetails(rs.getString("ReportDetails"));
			report.setStatus(rs.getString("Status"));
			return report;
		} else {
			return null; // or throw a custom ReportNotFoundException if you want
		}
	}

}
