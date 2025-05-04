package com.cars.Main;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.cars.Dao.Dao;
import com.cars.Dao.DaoImpl;
import com.cars.Validation.CarsValidation;
import com.cars.model.Incident;
import com.cars.model.Reports;

public class Main {

	static CarsValidation valid = new CarsValidation();
	static Scanner sc = new Scanner(System.in);
	static Dao d = new DaoImpl();

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		int choice;

		do {
			System.out.println("======================================");
			System.out.println("  Crime Analysis & Reporting System");
			System.out.println("======================================");
			System.out.println("1. Show All Incidents");
			System.out.println("2. Search Incident by ID");
			System.out.println("3. Create an Incident");
			System.out.println("4. Search Incident by Type");
			System.out.println("5. View Incident in Date Range");
			System.out.println("6. Update Incident Status");
			System.out.println("7. Show Incident Staus count: ");
			System.out.println("8. Show Report Summary by Incident");
			System.out.println("9. Show all Incident report");
			System.out.println("10. Exit");
			System.out.print("Enter your choice: ");

			choice = sc.nextInt();
			try {
				switch (choice) {
				case 1:
					showAllIncidentsMain();
					break;
				case 2:
					searchIncidentByIdMain();
					break;
				case 3:
					createIncidentMain();
					break;
				case 4:
					searchIncidentByTypeMain();
					break;
				case 5:
					sc.nextLine();
					showIncidentInDateRange();
					break;
				case 6:
					updateIncidentTypeMain();
					break;
				case 7:
					showIncidentStatusCount();
					break;
				case 8:
					showReportSummary();
					break;
				case 9:
					showAllIncidentReport();
					break;
				case 10:
					System.out.println("Exiting application...");
					break;
				default:
					System.out.println("Invalid choice. Try again.");
				}
			} catch (Exception e) {
				System.out.println("An error occurred: " + e.getMessage());
			}
		} while (choice != 11);
	}

	public static void showAllIncidentsMain() throws ClassNotFoundException, SQLException {
		Collection<Incident> incList = valid.getAllIncidents();
		System.out.println("===========All Incident Details==========");
		for (Incident inc : incList) {
			printIncident(inc);
//			System.out.println(inc);
		}
	}

	public static void searchIncidentByTypeMain() throws SQLException, ClassNotFoundException {
		sc.nextLine(); // consume leftover newline
		System.out.print("Enter Incident Type: ");
		String type = sc.nextLine();
		Collection<Incident> incidents = valid.searchIncidentByType(type);
		System.out.println("===========Incident By Type============");
		for (Incident inc : incidents) {
			printIncident(inc);
		}
	}

	public static void showReportSummary() throws ClassNotFoundException, SQLException {
		System.out.print("Enter Incident ID to generate report: ");
		int incId = sc.nextInt();
		Incident inc = new Incident();
		inc.setIncidentId(incId);
		Reports report = valid.generateIncidentReport(inc);
		printReport(report);
	}

	public static void createIncidentMain() throws ClassNotFoundException, SQLException {
		Incident inc = new Incident();

		System.out.println("Enter Incident Id: ");
		inc.setIncidentId(sc.nextInt());
		sc.nextLine();

		System.out.println("Enter Incident Type: ");
		inc.setIncidentType(sc.nextLine());

		System.out.print("Enter Date (YYYY-MM-DD): ");
		String dateInput = sc.nextLine();
		Date incidentDate = valid.validateDate(dateInput);
		if (incidentDate == null) {
			return;
		}
		inc.setIncidentDate(incidentDate);

		System.out.println("Enter Latitude: ");
		inc.setLatitude(sc.nextDouble());

		System.out.println("Enter Longitude: ");
		inc.setLongitude(sc.nextDouble());
		sc.nextLine();

		System.out.println("Enter Description: ");
		inc.setDescription(sc.nextLine());

		System.out.println("Enter Status of Incident: ");
		inc.setStatus(sc.nextLine());

		System.out.println("Enter Victim Id: ");
		inc.setVictimId(sc.nextInt());

		System.out.println("Enter Suspect Id: ");
		inc.setSuspectId(sc.nextInt());

		System.out.println("Enter Officer Id: ");
		inc.setOfficerId(sc.nextInt());

		String result = valid.createIncident(inc);
		System.out.println(result);
	}

	public static void showIncidentInDateRange() {

		Incident inc = new Incident();

		System.out.println("Enter the start date in this format: (yyyy-mm-dd) ");
		String startDate = sc.nextLine();

		System.out.println("Enter the end date in this format: (yyyy-mm-dd)");
		String endDate = sc.nextLine();

		Collection<Incident> inclist = valid.getIncidentsInRange(startDate, endDate);

		for (Incident incident : inclist) {
			printIncident(incident);
		}
	}

	public static void updateIncidentTypeMain() {
		Incident inc = new Incident();
		System.out.println("Enter Incident Id:");
		int id = sc.nextInt();

		sc.nextLine();

		System.out.println("Enter Incident Type: (Open, Closed, Under Investigation) ");
		String status = sc.nextLine();

		try {
			boolean res = valid.updateIncidentValid(id, status);

			if (res) {
				System.out.println("Status updated successfully");
			} else {
				System.out.println("Status updated successfully");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//New Features
	public static void showIncidentStatusCount() {
	    try {
	        Map<String, Integer> countstatus = d.countIncidentsByStatus();
	        System.out.println("Incident Status Summary:");
	        for (Map.Entry<String, Integer> entry : countstatus.entrySet()) {
	            System.out.println(entry.getKey() + ": " + entry.getValue());
	        }
	    } catch (Exception e) {
	        System.out.println("An error occurred: " + e.getMessage());
	    }
	}


	// New feature
	public static void searchIncidentByIdMain() throws SQLException, ClassNotFoundException {
		System.out.print("Enter Incident ID: ");
		int incidentId = sc.nextInt();
		Incident incident = valid.fetchIncidentById(incidentId);
//		System.out.println(incident);
		printIncident(incident);
	}
	
	

	// New feature
	public static void showAllIncidentReport() throws ClassNotFoundException, SQLException {
		List<Reports> reportlist = valid.getReportSummary();
		for (Reports reports : reportlist) {
			printReport(reports);
		}
	}

	// Print in vertical manner rather that List manner
	public static void printReport(Reports report) {
		System.out.println("--------------Incident Report--------------");
		System.out.println("Report ID          : " + report.getReportId());
		System.out.println("Incident ID        : " + report.getIncidentId());
		System.out.println("Reporting Officer  : " + report.getReportingOfficer());
		System.out.println("Report Date        : " + report.getReportDate());
		System.out.println("Report Details     : " + report.getReportDetails());
		System.out.println("Status             : " + report.getStatus());
	}

	public static void printIncident(Incident inc) {
		System.out.println("--------------------------------------------------");
		System.out.println("Incident ID    : " + inc.getIncidentId());
		System.out.println("Type           : " + inc.getIncidentType());
		System.out.println("Date           : " + inc.getIncidentDate());
		System.out.println("Latitude       : " + inc.getLatitude());
		System.out.println("Longitude      : " + inc.getLongitude());
		System.out.println("Description    : " + inc.getDescription());
		System.out.println("Status         : " + inc.getStatus());
		System.out.println("Victim ID      : " + inc.getVictimId());
		System.out.println("Suspect ID     : " + inc.getSuspectId());
		System.out.println("Officer ID     : " + inc.getOfficerId());
	}
}
