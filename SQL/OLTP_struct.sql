DROP DATABASE IF EXISTS OLTP;
CREATE DATABASE OLTP;

USE OLTP;

DROP TABLE IF EXISTS circuits CASCADE;
CREATE TABLE circuits (
	circuitId INT,
	circuitRef VARCHAR(255),
	name VARCHAR(255),
	location VARCHAR(255),
	country VARCHAR(255),
	lat FLOAT,
	lng FLOAT,
	alt INT,
	url VARCHAR(255), 
    PRIMARY KEY (circuitId)
);

DROP TABLE IF EXISTS seasons CASCADE;
CREATE TABLE seasons (
	year INT,
	url VARCHAR(255),
	PRIMARY KEY (year)
);

DROP TABLE IF EXISTS races CASCADE;
CREATE TABLE races (
	raceId INT,
	year INT,
	round INT,
	circuitId INT,
	name VARCHAR(255),
	date DATE,
	time TIME,
	url VARCHAR(255),
    PRIMARY KEY (raceId)
);

DROP TABLE IF EXISTS constructors CASCADE;
CREATE TABLE constructors (
	constructorId INT,
	constructorRef VARCHAR(255),
	name VARCHAR(255),
	nationality VARCHAR(255),
	url VARCHAR(255),
    PRIMARY KEY (constructorId)
);

DROP TABLE IF EXISTS drivers CASCADE;
CREATE TABLE drivers (
	driverId INT,
	driverRef VARCHAR(255),
	number INT,
	code VARCHAR(255),
	forename VARCHAR(255),
	surname VARCHAR(255),
	dob DATE,
	nationality VARCHAR(255),
	url VARCHAR(255),
    PRIMARY KEY (driverId)
);

DROP TABLE IF EXISTS constructorResults CASCADE;
CREATE TABLE constructorResults (
	constructorResultsId INT,
	raceId INT,
	constructorId INT,  
	points FLOAT,
	status VARCHAR(255),
    PRIMARY KEY (constructorResultsId)
);

DROP TABLE IF EXISTS constructorStandings CASCADE;
CREATE TABLE constructorStandings (
	constructorStandingsId INT,
	raceId INT,  
	constructorId INT,  
	points FLOAT,
	position INT,
	positionText VARCHAR(255),
	wins INT,
    PRIMARY KEY (constructorStandingsId)
);

DROP TABLE IF EXISTS driverStandings CASCADE;
CREATE TABLE driverStandings (
	driverStandingsId INT,
	raceId INT, 
	driverId INT, 
	points FLOAT,
	position INT,
	positionText VARCHAR(255),
	wins INT,
    PRIMARY KEY (driverStandingsId)
);

DROP TABLE IF EXISTS lapTimes CASCADE;
CREATE TABLE lapTimes (
	raceId INT, 
	driverId INT, 
	lap INT, 
	position INT,
	time VARCHAR(255),
	milliseconds INT,
    PRIMARY KEY (raceId, driverId, lap)
);

DROP TABLE IF EXISTS pitStops CASCADE;
CREATE TABLE pitStops (
	raceId INT, 
	driverId INT, 
	stop INT,
	lap INT,
	time TIME,
	duration VARCHAR(255),
	milliseconds INT,
    PRIMARY KEY (raceId, driverId, stop)
);

DROP TABLE IF EXISTS qualifying CASCADE;
CREATE TABLE qualifying (
	qualifyId INT,
	raceId INT,    
	driverId INT,  
	constructorId INT, 
	number INT,
	position INT,
	q1 VARCHAR(255),
	q2 VARCHAR(255),
	q3 VARCHAR(255),
    PRIMARY KEY (qualifyId)
);

DROP TABLE IF EXISTS status CASCADE;
CREATE TABLE status (
	statusId INT,
	status VARCHAR(255),
	PRIMARY KEY (statusId)
);

DROP TABLE IF EXISTS results CASCADE;
CREATE TABLE results (
	resultId INT,
	raceId INT, 
    driverId INT, 
	constructorId INT, 
	number INT,
	grid INT,
	position INT,
	positionText VARCHAR(255),
	positionOrder INT,
	points FLOAT,
	laps INT,
	time VARCHAR(255),
	milliseconds INT, 
	fastestLap INT,
	rank INT,
	fastestLapTime VARCHAR(255),
	fastestLapSpeed VARCHAR(255),
	statusId INT,
    PRIMARY KEY (resultId)
);