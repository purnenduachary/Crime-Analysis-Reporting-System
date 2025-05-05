package com.carsproject.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.cars.Dao.DaoImpl;
import com.cars.model.Incident;

public class AppTest {

//  @Test
//  public void testMain() {
//    App.main(null);
//  }
  
  private DaoImpl incidentDao;

  @Before
  public void setUp() {
      incidentDao = new DaoImpl(); // assumes this initializes DB connection
  }

  @Test
  public void testCreateIncident() throws ClassNotFoundException, SQLException {

	  Incident incident = new Incident();
      incident.setIncidentId(108); 
      incident.setIncidentType("Assault");
      incident.setIncidentDate(Date.valueOf("2024-03-01"));
      incident.setLatitude(34.052200);
      incident.setLongitude(-118.243700);
      incident.setDescription("Physical altercation reported");
      incident.setStatus("Open");
      incident.setVictimId(305);
      incident.setSuspectId(505);
      incident.setOfficerId(205);

      boolean result = incidentDao.createIncident(incident);
	

      assertTrue("Incident should be created successfully", result);

      Incident retrieved = incidentDao.searchIncidentById(108);
	

      assertNotNull("Retrieved incident should not be null", retrieved);
      assertEquals("Incident ID should match", 108, retrieved.getIncidentId());
      assertEquals("Type should match", "Assault", retrieved.getIncidentType());
      assertEquals("Date should match", Date.valueOf("2024-03-01"), retrieved.getIncidentDate());
      assertEquals("Latitude should match", 34.052200, retrieved.getLatitude(), 0.0001);
      assertEquals("Longitude should match", -118.243700, retrieved.getLongitude(), 0.0001);
      assertEquals("Description should match", "Physical altercation reported", retrieved.getDescription());
      assertEquals("Status should match", "Open", retrieved.getStatus());
      assertEquals("Victim ID should match", 305, retrieved.getVictimId());
      assertEquals("Suspect ID should match", 505, retrieved.getSuspectId());
      assertEquals("Officer ID should match", 205, retrieved.getOfficerId());
  }

}
