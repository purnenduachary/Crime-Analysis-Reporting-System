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
import com.carsproject.App;

public class AppTest {

  @Test
  public void testMain() {
    App.main(null);
  }
  
  private DaoImpl incidentDao;

  @Before
  public void setUp() {
      incidentDao = new DaoImpl(); // assumes this initializes DB connection
  }

  @Test
  public void testCreateIncident() throws ClassNotFoundException, SQLException {
      // Arrange: Create a new incident and set all required fields
      Incident incident = new Incident();
      incident.setIncidentId(102);  // manual ID since it's not auto-increment
      incident.setIncidentType("Assault");
      incident.setIncidentDate(Date.valueOf("2024-03-01"));
      incident.setLatitude(34.052200);
      incident.setLongitude(-118.243700);
      incident.setDescription("Physical altercation reported");
      incident.setStatus("Open");
      incident.setVictimId(305);
      incident.setSuspectId(505);
      incident.setOfficerId(205);

      // Act: Attempt to create the incident in the DB
      boolean result = incidentDao.createIncident(incident);
	

      // Assert: Check that creation succeeded
      assertTrue("Incident should be created successfully", result);

      // Retrieve the incident to verify fields
      Incident retrieved = incidentDao.searchIncidentById(102);
	

      assertNotNull("Retrieved incident should not be null", retrieved);
      assertEquals("Incident ID should match", 102, retrieved.getIncidentId());
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
