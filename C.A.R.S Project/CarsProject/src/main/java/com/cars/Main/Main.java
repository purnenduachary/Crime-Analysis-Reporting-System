package com.cars.Main;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.cars.Dao.Dao;
import com.cars.Dao.DaoImpl;
import com.cars.Validation.CarsValidation;
import com.cars.model.Incident;
import com.cars.model.Reports;

public class Main {

	static CarsValidation valid = new CarsValidation();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		int choice;

		do {
			System.out.println("\n======================================");
			System.out.println("   Crime Analysis & Reporting System");
			System.out.println("======================================");
			System.out.println("1. Show All Incidents");
			System.out.println("2. Search Incident by ID");
			System.out.println("3. Search Incident by Type");
			System.out.println("4. Show Incident Report");
			System.out.println("8: Show all incident report");
			System.out.println("5. Exit");
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
					searchIncidentByTypeMain();
					break;
				case 4:
					showReportSummary();
					break;
				case 8:
					showAllIncidentReport();
					break;
				case 5:
					System.out.println("Exiting application...");
					break;
				default:
					System.out.println("Invalid choice. Try again.");
				}
			} catch (Exception e) {
				System.out.println("An error occurred: " + e.getMessage());
			}
		} while (choice != 9);
	}

	public static void showAllIncidentsMain() throws ClassNotFoundException, SQLException {
		List<Incident> incList = valid.getAllIncidents();
		for (Incident inc : incList) {
			printIncident(inc);
//			System.out.println(inc);
		}
	}

	public static void searchIncidentByTypeMain() throws SQLException, ClassNotFoundException {
		sc.nextLine(); // consume leftover newline
		System.out.print("Enter Incident Type: ");
		String type = sc.nextLine();
		List<Incident> incidents = valid.searchIncidentByType(type);
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
	
	//New feature
		public static void searchIncidentByIdMain() throws SQLException, ClassNotFoundException {
			System.out.print("Enter Incident ID: ");
			int incidentId = sc.nextInt();
			String result = valid.fetchIncidentById(incidentId);
			System.out.println(result);
		}

	//New feature
	public static void showAllIncidentReport() throws ClassNotFoundException, SQLException {
		List<Reports> reportlist = valid.getReportSummary();
		for (Reports reports : reportlist) {
			printReport(reports);
		}
	}

	//Print in vertical manner rather that List manner
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

