package com.carsproject.Test;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.cars.Dao.Dao;
import com.cars.Dao.DaoImpl;

public class UpdateStatusTest {
	 private Dao dao;

	    @Before
	    public void setUp() {
	        dao = new DaoImpl();
	    }

	    @Test
	    public void testUpdateIncidentStatus_ValidIdAndStatus() throws SQLException, ClassNotFoundException {
	        boolean result = dao.updateIncidentStatus(102, "Opened"); // Assuming ID 1 exists
	        assertTrue("Status should be updated for a valid incident ID", result);
	    }

//	    @Test
//	    public void testUpdateIncidentStatus_InvalidIncidentId() throws SQLException, ClassNotFoundException {	    	
//	    	CarsValidation val = new CarsValidation();
//	        boolean result = val.updateIncidentValid(120, "Closed");
//	        System.out.println(result);// Assuming ID 9999 does not exist
//	        assertFalse("Update should fail for a non-existent incident ID", result);
//	    }
//
//	    @Test
//	    public void testUpdateIncidentStatus_InvalidStatus() throws SQLException, ClassNotFoundException {
//	        boolean result = dao.updateIncidentStatus(102, "INVALID_STATUS"); // Assuming your system validates statuses
//	        assertFalse("Update should fail for an invalid status value", result);
//	    }
//
//	    @Test
//	    public void testUpdateIncidentStatus_NullStatus() throws SQLException, ClassNotFoundException {
//	        boolean result = dao.updateIncidentStatus(102, null);
//	        assertFalse("Update should fail for a null status", result);
//	    }
}
