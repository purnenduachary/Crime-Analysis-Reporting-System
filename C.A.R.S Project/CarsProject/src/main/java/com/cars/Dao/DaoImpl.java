package com.cars.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.cars.model.Incident;
import com.cars.util.ConnectionHelper;

public class DaoImpl implements Dao{
	
	Connection conn;
	PreparedStatement pst;

	@Override
	public List<Incident> showAllIncident() throws SQLException, ClassNotFoundException {
		List<Incident> inclist = new ArrayList<>();
		String sql ="select * from Incidents";
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
	public Collection<Incident> searchIncidents(String incidentType) throws ClassNotFoundException, SQLException {
		List<Incident> resultList = new ArrayList<>();
	    String sql = "SELECT * FROM incident WHERE IncidentType LIKE ?";
	    PreparedStatement pst = conn.prepareStatement(sql);
	    pst.setString(1, "%" + incidentType + "%");

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
		 String sql = "SELECT * FROM incident WHERE IncidentID = ?";
	        PreparedStatement pst = conn.prepareStatement(sql);
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

}
