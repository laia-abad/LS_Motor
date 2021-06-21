DROP DATABASE IF EXISTS OLAP;
CREATE DATABASE OLAP;

USE OLAP;

DROP TABLE IF EXISTS races CASCADE;
CREATE TABLE races (
	#races
	raceId INT,
	year INT,
	round INT,
	circuitId INT,
	nameRace VARCHAR(255),
	date DATE,
	time TIME,
	urlRace VARCHAR(255),
    #seasons
    urlSeason VARCHAR (255), 	
    #circuits
	circuitRef VARCHAR(255),
	nameCircuit VARCHAR(255),
	location VARCHAR(255),
	country VARCHAR(255),
	lat FLOAT,
	lng FLOAT,
	alt INT,
	urlCircuit VARCHAR(255)
);

DROP TABLE IF EXISTS constructor CASCADE;
CREATE TABLE constructor (
	constructorId INT,
	constructorRef VARCHAR(255),
	name VARCHAR(255),
	nationality VARCHAR(255),
	url VARCHAR(255),
    #constructorStandings
    constructorStandingsId INT,
	raceId INT,  
	pointsStandings FLOAT,
	position INT,
	wins INT,
    positionText VARCHAR(255),
    #constructorResults
    constructorResultsId INT,
	pointsResult FLOAT,
    status VARCHAR(255)
);

DROP TABLE IF EXISTS driver CASCADE;
CREATE TABLE driver (
	driverId INT,
	driverRef VARCHAR(255),
	number INT,
	code VARCHAR(255),
	forename VARCHAR(255),
	surname VARCHAR(255),
	dob DATE,
	nationality VARCHAR(255),
	url VARCHAR(255),
    #driverStandings
    driverStandingsId INT,
	raceId INT, 
	points FLOAT,
	positionStanding INT,
	wins INT,
    positionText VARCHAR(255),
    #LapTimes
	lap INT, 
	positionLap INT,
	timeLap VARCHAR(255),
	millisecondsLap INT,
    #PitStops
	stop INT,
	timeStop TIME,
	duration VARCHAR(255),
	millisecondsStop INT
);

DROP TABLE IF EXISTS results CASCADE;
CREATE TABLE results (
	resultId INT,
	raceId INT, 
    driverId INT, 
	constructorId INT, 
	numberResults INT,
	grid INT,
	positionResults INT,
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
    #qualifying
    qualifyId INT,
    numberQualifying INT,
	positionQualifying INT,
    q1 VARCHAR(255), 
    q2 VARCHAR(255),
    q3 VARCHAR(255),
    #Status
    statusId INT,
	status VARCHAR(255)
);