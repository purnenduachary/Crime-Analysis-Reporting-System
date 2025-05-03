package com.cars.Dao;

import java.sql.Connection;
import java.sql.Date;
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
	public Collection<Incident> searchIncidentsByType(String IncidentType) throws ClassNotFoundException, SQLException {
		List<Incident> resultList = new ArrayList<>();
		conn = ConnectionHelper.getConnection();
		sql = "select * from Incidents where IncidentType LIKE ?";
		pst = conn.prepareStatement(sql);
		
		// Wildcards used for anonymous output
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
	public Incident searchIncidentById(int incidentId) throws SQLException, ClassNotFoundException {
		Incident incident = null;
		sql = "Select * from Incidents where IncidentID = ?";
		conn = ConnectionHelper.getConnection();
		pst = conn.prepareStatement(sql);
		pst.setInt(1, incidentId);

		ResultSet rs = pst.executeQuery();

		if (rs.next()) {
			incident = new Incident();
			 incident.setIncidentId(rs.getInt("IncidentID"));
	            incident.setIncidentType(rs.getString("IncidentType"));
	            incident.setIncidentDate(rs.getDate("IncidentDate"));
	            incident.setLatitude(rs.getDouble("Latitude"));
	            incident.setLongitude(rs.getDouble("Longitude"));
	            incident.setDescription(rs.getString("Description"));
	            incident.setStatus(rs.getString("Status"));
	            incident.setVictimId(rs.getInt("VictimId"));
	            incident.setSuspectId(rs.getInt("SuspectId"));
	            incident.setOfficerId(rs.getInt("OfficerId"));
		} else {
			throw new ClassNotFoundException("Incident ID " + incidentId + " not found.");
		}
		
		return incident;
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

	// Added Feature
	@Override
	public Reports generateIncidentReportById(Incident incident) throws SQLException, ClassNotFoundException {
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

	@Override
	public Collection<Incident> showIncidentInDateRange(Date startDate, Date endDate) throws SQLException, ClassNotFoundException {
		sql = "select * FROM Incidents where IncidentDate BETWEEN ? AND ?";
		conn = ConnectionHelper.getConnection();
		pst = conn.prepareStatement(sql);

		pst.setDate(1, startDate);
		pst.setDate(2, endDate);
		ResultSet rs = pst.executeQuery();
		List<Incident> list = new ArrayList<Incident>();

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
			list.add(inc);
		}
		return list;
	}

	@Override
	public boolean createIncident(Incident incident) throws SQLException, ClassNotFoundException {
		sql = "Insert into Incidents (IncidentId, IncidentType, IncidentDate, Latitude, Longitude,"
				+ "Description, Status, VictimId, SuspectId, OfficerId) values (?,?,?,?,?,?,?,?,?,?)";

		conn = ConnectionHelper.getConnection();
		pst = conn.prepareStatement(sql);
		pst.setInt(1, incident.getIncidentId());
		pst.setString(2, incident.getIncidentType());
		pst.setDate(3, incident.getIncidentDate());
		pst.setDouble(4, incident.getLatitude());
		pst.setDouble(5, incident.getLongitude());
		pst.setString(6, incident.getDescription());
		pst.setString(7, incident.getStatus());
		pst.setInt(8, incident.getVictimId());
		pst.setInt(9, incident.getSuspectId());
		pst.setInt(10, incident.getOfficerId());

		int rowsAffected = pst.executeUpdate();
		return rowsAffected > 0;
	}

	@Override
	public boolean updateIncidentStatus(int IncidentId, String status) throws ClassNotFoundException, SQLException {
	    Incident incident = searchIncidentById(IncidentId);
	    if (incident == null) {
	        return false; // Early return if incident doesn't exist
	    }

	    sql = "UPDATE Incidents SET Status = ? WHERE IncidentID = ?";
	    conn = ConnectionHelper.getConnection();
	    pst = conn.prepareStatement(sql);
	    pst.setString(1, status);
	    pst.setInt(2, IncidentId);

	    int rowsUpdated = pst.executeUpdate();
	    return rowsUpdated > 0;
	}


}
