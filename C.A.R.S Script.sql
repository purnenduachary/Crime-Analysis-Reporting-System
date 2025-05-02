-- DROP AND CREATE DATABASE
DROP DATABASE IF EXISTS cars;
CREATE DATABASE cars;
USE cars;

-- DROP TABLES IN CORRECT ORDER (TO HANDLE FOREIGN KEYS)
DROP TABLE IF EXISTS Reports;
DROP TABLE IF EXISTS Evidence;
DROP TABLE IF EXISTS Incidents;
DROP TABLE IF EXISTS Suspects;
DROP TABLE IF EXISTS Victims;
DROP TABLE IF EXISTS Officers;
DROP TABLE IF EXISTS LawEnforcementAgencies;

-- CREATE LawEnforcementAgencies
CREATE TABLE IF NOT EXISTS LawEnforcementAgencies (
    AgencyID INT PRIMARY KEY,
    AgencyName VARCHAR(100),
    Jurisdiction VARCHAR(100),
    ContactInformation VARCHAR(255)
);

INSERT INTO LawEnforcementAgencies (AgencyID, AgencyName, Jurisdiction, ContactInformation) VALUES
(1, 'City Police Department', 'Downtown', '111-222-3333'),
(2, 'Metro Station', 'East Zone', '222-333-4444'),
(3, 'North Watch', 'North Zone', '333-444-5555'),
(4, 'West Precinct', 'West Zone', '444-555-6666'),
(5, 'South Security Unit', 'South Zone', '555-666-7777'),
(6, 'Rural Police HQ', 'Rural Area', '666-777-8888'),
(7, 'Cyber Crime Unit', 'Citywide', '777-888-9999'),
(8, 'Women Protection Cell', 'Statewide', '888-999-0000'),
(9, 'Traffic Division', 'All Zones', '999-000-1111'),
(10, 'Anti-Narcotics Bureau', 'Nationwide', '000-111-2222');

-- CREATE Officers
CREATE TABLE IF NOT EXISTS Officers (
    OfficerID INT PRIMARY KEY,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    BadgeNumber VARCHAR(20),
    ranking VARCHAR(50),
    ContactInformation VARCHAR(255),
    AgencyID INT,
    FOREIGN KEY (AgencyID) REFERENCES LawEnforcementAgencies(AgencyID)
);

INSERT INTO Officers (OfficerID, FirstName, LastName, BadgeNumber, ranking, ContactInformation, AgencyID) VALUES
(201, 'Alok', 'Verma', 'CP201', 'Inspector', 'alok.verma@police.gov', 1),
(202, 'Shalini', 'Saxena', 'CP202', 'Constable', 'shalini.saxena@police.gov', 2),
(203, 'Ritesh', 'Chatterjee', 'CP203', 'Sub-Inspector', 'ritesh.chatterjee@police.gov', 3),
(204, 'Anil', 'Yadav', 'CP204', 'Constable', 'anil.yadav@police.gov', 4),
(205, 'Geeta', 'Kumari', 'CP205', 'Sub-Inspector', 'geeta.kumari@police.gov', 5),
(206, 'Karan', 'Bajwa', 'CP206', 'Inspector', 'karan.bajwa@police.gov', 6),
(207, 'Vikram', 'Saini', 'CP207', 'Inspector', 'vikram.saini@police.gov', 7),
(208, 'Pooja', 'Mishra', 'CP208', 'Constable', 'pooja.mishra@police.gov', 8),
(209, 'Sameer', 'Jain', 'CP209', 'Sub-Inspector', 'sameer.jain@police.gov', 9),
(210, 'Naina', 'Bhatt', 'CP210', 'Inspector', 'naina.bhatt@police.gov', 10);

-- CREATE Victims
CREATE TABLE IF NOT EXISTS Victims (
    VictimID INT PRIMARY KEY,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    DateOfBirth DATE,
    Gender VARCHAR(10),
    ContactInformation VARCHAR(255)
);

INSERT INTO Victims (VictimID, FirstName, LastName, DateOfBirth, Gender, ContactInformation) VALUES
(301, 'Sonia', 'Kapoor', '1992-03-10', 'Female', 'sonia.kapoor@mail.com'),
(302, 'Rahul', 'Bose', '1989-06-22', 'Male', 'rahul.bose@mail.com'),
(303, 'Sneha', 'Iyer', '1995-11-30', 'Female', 'sneha.iyer@mail.com'),
(304, 'Deepak', 'Rana', '1987-12-18', 'Male', 'deepak.rana@mail.com'),
(305, 'Kriti', 'Sen', '1994-04-25', 'Female', 'kriti.sen@mail.com'),
(306, 'Mohit', 'Deshmukh', '1988-01-12', 'Male', 'mohit.deshmukh@mail.com'),
(307, 'Jaya', 'Mishra', '1991-09-08', 'Female', 'jaya.mishra@mail.com'),
(308, 'Raghav', 'Shah', '1990-05-02', 'Male', 'raghav.shah@mail.com'),
(309, 'Nikita', 'Rai', '1996-08-14', 'Female', 'nikita.rai@mail.com'),
(310, 'Aman', 'Gill', '1986-07-21', 'Male', 'aman.gill@mail.com');

-- CREATE Suspects
CREATE TABLE IF NOT EXISTS Suspects (
    SuspectID INT PRIMARY KEY,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    DateOfBirth DATE,
    Gender VARCHAR(10),
    ContactInformation VARCHAR(255)
);

INSERT INTO Suspects (SuspectID, FirstName, LastName, DateOfBirth, Gender, ContactInformation) VALUES
(501, 'Ravi', 'Khandelwal', '1980-01-15', 'Male', 'ravi.khandelwal@mail.com'),
(502, 'Simran', 'Kaur', '1985-04-10', 'Female', 'simran.kaur@mail.com'),
(503, 'Dev', 'Menon', '1990-07-30', 'Male', 'dev.menon@mail.com'),
(504, 'Meera', 'Shetty', '1993-09-09', 'Female', 'meera.shetty@mail.com'),
(505, 'Kunal', 'Yadav', '1988-12-01', 'Male', 'kunal.yadav@mail.com'),
(506, 'Priya', 'Joshi', '1992-03-27', 'Female', 'priya.joshi@mail.com'),
(507, 'Arun', 'Thakur', '1987-10-16', 'Male', 'arun.thakur@mail.com'),
(508, 'Ritika', 'Saxena', '1994-05-11', 'Female', 'ritika.saxena@mail.com'),
(509, 'Nikhil', 'Kapoor', '1983-02-24', 'Male', 'nikhil.kapoor@mail.com'),
(510, 'Divya', 'Malhotra', '1991-06-18', 'Female', 'divya.malhotra@mail.com');

-- CREATE Incidents
CREATE TABLE IF NOT EXISTS Incidents (
    IncidentID INT PRIMARY KEY,
    IncidentType VARCHAR(50),
    IncidentDate DATE,
    Latitude DECIMAL(9,6),
    Longitude DECIMAL(9,6),
    Description TEXT,
    Status VARCHAR(30),
    VictimID INT,
    SuspectID INT,
    OfficerID INT,
    FOREIGN KEY (VictimID) REFERENCES Victims(VictimID),
    FOREIGN KEY (SuspectID) REFERENCES Suspects(SuspectID),
    FOREIGN KEY (OfficerID) REFERENCES Officers(OfficerID)
);

INSERT INTO Incidents (IncidentID, IncidentType, IncidentDate, Latitude, Longitude, Description, Status, VictimID, SuspectID, OfficerID)
VALUES
(401, 'Robbery', '2024-03-01', 40.7128, -74.0060, 'Stolen bag at downtown', 'Closed', 301, 501, 201),
(402, 'Theft', '2024-04-15', 34.0522, -118.2437, 'Bike theft near school', 'Under Investigation', 302, 502, 202),
(403, 'Homicide', '2024-02-20', 41.8781, -87.6298, 'Unsolved murder case', 'Open', 303, 503, 203);

-- CREATE Evidence
CREATE TABLE IF NOT EXISTS Evidence (
    EvidenceID INT PRIMARY KEY,
    Description TEXT,
    LocationFound VARCHAR(255),
    IncidentID INT,
    FOREIGN KEY (IncidentID) REFERENCES Incidents(IncidentID)
);

INSERT INTO Evidence (EvidenceID, Description, LocationFound, IncidentID)
VALUES
(601, 'Black wallet', 'Near downtown park', 401),
(602, 'Security camera footage', 'School entrance', 402),
(603, 'Blood sample', 'Alley behind restaurant', 403);

-- CREATE Reports
CREATE TABLE IF NOT EXISTS Reports (
    ReportID INT PRIMARY KEY,
    IncidentID INT,
    ReportingOfficer INT,
    ReportDate DATE,
    ReportDetails TEXT,
    Status VARCHAR(30),
    FOREIGN KEY (IncidentID) REFERENCES Incidents(IncidentID),
    FOREIGN KEY (ReportingOfficer) REFERENCES Officers(OfficerID)
);

INSERT INTO Reports (ReportID, IncidentID, ReportingOfficer, ReportDate, ReportDetails, Status)
VALUES
(701, 401, 201, '2024-03-02', 'Robbery report with victim and suspect IDs', 'Finalized'),
(702, 402, 202, '2024-04-16', 'Initial report, investigation pending', 'Draft'),
(703, 403, 203, '2024-02-21', 'Homicide case summary and forensics', 'Finalized');

-- VERIFY DATA
SELECT * FROM LawEnforcementAgencies;
SELECT * FROM Officers;
SELECT * FROM Victims;
SELECT * FROM Suspects;
SELECT * FROM Incidents;
SELECT * FROM Evidence;
SELECT * FROM Reports;

-- SELECT TO VERIFY INCIDENT IDs
SELECT IncidentID FROM Incidents WHERE IncidentID IN (401, 402, 403);
