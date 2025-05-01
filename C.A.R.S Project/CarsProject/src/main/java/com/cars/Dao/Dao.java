package com.cars.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.cars.model.Incident;

public interface Dao {

	List<Incident> showAllIncident() throws SQLException, ClassNotFoundException;

	Collection<Incident> searchIncidents(String incidentType) throws ClassNotFoundException, SQLException;

	String searchIncidentDao(int incidentId) throws SQLException, ClassNotFoundException;
}
