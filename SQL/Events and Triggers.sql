DELIMITER $$

DROP TRIGGER IF EXISTS OLTP.UpdateRaces$$
CREATE TRIGGER OLTP.UpdateRaces 
AFTER INSERT
ON OLTP.races FOR EACH ROW
BEGIN

	INSERT INTO OLAP.races (raceId, year, round, circuitId, nameRace, date, time, urlRace)
    VALUES (NEW.raceId, NEW.year, NEW.round, NEW.circuitId, NEW.name, NEW.date, NEW.time, NEW.url);
    
END$$

DROP TRIGGER IF EXISTS OLTP.UpdateSeasons$$
CREATE TRIGGER OLTP.UpdateSeasons 
AFTER INSERT
ON OLTP.seasons FOR EACH ROW
BEGIN

	UPDATE OLAP.races 
    SET urlSeason = NEW.url
    WHERE OLAP.races.year = NEW.year;
    
END$$

DROP TRIGGER IF EXISTS OLTP.UpdateCircuits;
CREATE TRIGGER OLTP.UpdateCircuits AFTER INSERT ON OLTP.circuits 
FOR EACH ROW
BEGIN
	IF EXISTS (SELECT * FROM OLAP.races WHERE NEW.circuitId = circuitId) THEN
		UPDATE OLAP.races
		SET circuitRef = NEW.circuitRef, nameCircuit = NEW.name, location = NEW.location, country = NEW.country, lat = NEW.lat, lng = NEW.lng, alt = NEW.alt, urlCircuit = NEW.url
		WHERE OLAP.races.circuitId = NEW.circuitId;
    ELSE
		INSERT INTO OLAP.races (circuitId, circuitRef, nameCircuit, location, country, lat, lng, alt, urlCircuit)
		VALUES (NEW.circuitId, NEW.circuitRef, NEW.name, NEW.location, NEW.country, NEW.lat, NEW.lng, NEW.alt, NEW.url);
		
	END IF;
    
END$$

DROP TRIGGER IF EXISTS OLTP.UpdateConstructorStandings$$
CREATE TRIGGER OLTP.UpdateConstructorStandings 
AFTER INSERT
ON OLTP.constructorStandings FOR EACH ROW
BEGIN

	INSERT INTO OLAP.constructor (constructorStandingsId, constructorId, raceId, pointsStandings, position, wins, positionText)
    VALUES (NEW.constructorStandingsId, NEW.constructorId, NEW.raceId, NEW.points, NEW.position, NEW.wins, NEW.positionText);
    
END$$

DROP TRIGGER IF EXISTS OLTP.UpdateconstructorResults$$
CREATE TRIGGER OLTP.UpdateconstructorResults
AFTER INSERT
ON OLTP.constructorResults FOR EACH ROW
BEGIN
	IF EXISTS (SELECT * FROM OLAP.constructor WHERE constructorId = NEW.constructorId AND raceId = NEW.raceId) THEN
		UPDATE OLAP.constructor
		SET constructorResultsId = NEW.constructorResultsId, pointsResult = NEW.points, status = NEW.status
		WHERE OLAP.constructor.constructorId = NEW.constructorId AND OLAP.constructor.raceId = NEW.raceId;
	ELSE
		INSERT INTO	OLAP.constructor (constructorResultsId, constructorId, raceId, pointsResult, status)
		VALUES (NEW.constructorResultsId, NEW.constructorId, NEW.raceId, NEW.points, NEW.status);
    END IF;
    
END$$

DROP TRIGGER IF EXISTS OLTP.UpdateConstructor$$
CREATE TRIGGER OLTP.UpdateConstructor 
AFTER INSERT
ON OLTP.constructors FOR EACH ROW
BEGIN
	IF EXISTS (SELECT * FROM OLAP.constructor WHERE constructorId = NEW.constructorId) THEN
		UPDATE OLAP.constructor
		SET constructorRef = NEW.constructorRef, name = NEW.name, nationality = NEW.nationality, url = NEW.url
		WHERE OLAP.constructor.constructorId = NEW.constructorId;
	ELSE
		INSERT INTO OLAP.constructor (constructorRef, constructorId, name, nationality, url)
		VALUES (NEW.constructorRef, NEW.constructorId, NEW.name, NEW.nationality, NEW.url);
    END IF;
    
END$$ 

DROP TRIGGER IF EXISTS OLTP.UpdateResults$$
CREATE TRIGGER OLTP.UpdateResults 
AFTER INSERT
ON OLTP.results FOR EACH ROW
BEGIN

	INSERT INTO OLAP.results (resultId, raceId, driverId, constructorId, numberResults, grid, positionResults, positionText, positionOrder, points, laps, time, 
								milliseconds, fastestLap, rank, fastestLapTime, fastestLapSpeed, statusId)
    VALUES (NEW.resultId, NEW.raceId, NEW.driverId, NEW.constructorId, NEW.number, NEW.grid, NEW.position, NEW.positionText, NEW.positionOrder, NEW.points, NEW.laps, 
			NEW.time, NEW.milliseconds, NEW.fastestLap, NEW.rank, NEW.fastestLapTime, NEW.fastestLapSpeed, NEW.statusId);
    
END$$

DROP TRIGGER IF EXISTS OLTP.UpdateQualifying$$
CREATE TRIGGER OLTP.UpdateQualifying 
AFTER INSERT
ON OLTP.qualifying FOR EACH ROW
BEGIN

	IF EXISTS (SELECT * FROM OLAP.results WHERE raceId = NEW.raceId AND driverId = NEW.driverId AND constructorId = NEW.constructorId) THEN
		UPDATE OLAP.results 
		SET qualifyId = NEW.qualifyId, q1 = NEW.q1, q2 = NEW.q2, q3 = NEW.q3, numberQualifying = NEW.number, positionQualifying = NEW.position
		WHERE OLAP.results.raceId = NEW.raceId AND OLAP.results.driverId = NEW.driverId AND OLAP.results.constructorId = NEW.constructorId;
	ELSE
		INSERT INTO OLAP.results (qualifyId, q1, q2, q3, numberQualifying, positionQualifying, constructorId, driverId, raceId)
		VALUES (NEW.qualifyId, NEW.q1, NEW.q2, NEW.q3, NEW.number, NEW.position, NEW.constructorId, NEW.driverId, NEW.raceId);
	END IF;
    
END$$

DROP TRIGGER IF EXISTS OLTP.UpdateStatus$$
CREATE TRIGGER OLTP.UpdateStatus 
AFTER INSERT
ON OLTP.status FOR EACH ROW
BEGIN

	IF EXISTS (SELECT * FROM OLAP.results WHERE statusId = NEW.statusId) THEN
		UPDATE OLAP.results 
		SET status = NEW.status
		WHERE OLAP.results.statusId = NEW.statusId;
	ELSE
		INSERT INTO OLAP.results (statusId, status)
		VALUES (NEW.statusId, NEW.status);
	END IF;
    
END$$

DROP TRIGGER IF EXISTS OLTP.UpdateLapTimes$$
CREATE TRIGGER OLTP.UpdateLapTimes 
AFTER INSERT
ON OLTP.lapTimes FOR EACH ROW
BEGIN

	INSERT INTO OLAP.driver (raceId, driverId, lap, positionLap, timeLap, millisecondsLap)
    VALUES (NEW.raceId, NEW.driverId, NEW.lap, NEW.position, NEW.time, NEW.milliseconds);
    
END$$

DROP TRIGGER IF EXISTS OLTP.UpdatePitStops$$
CREATE TRIGGER OLTP.UpdatePitStops 
AFTER INSERT
ON OLTP.pitStops FOR EACH ROW
BEGIN

	UPDATE OLAP.driver
    SET  stop = NEW.stop, timeStop = NEW.time, duration = NEW.duration, millisecondsStop = NEW.milliseconds
    WHERE OLAP.driver.raceId = NEW.raceId AND OLAP.driver.driverId = NEW.driverId AND OLAP.driver.lap = NEW.lap;
    
END$$

DROP TRIGGER IF EXISTS OLTP.UpdateDriverStandings$$
CREATE TRIGGER OLTP.UpdateDriverStandings 
AFTER INSERT
ON OLTP.driverStandings FOR EACH ROW
BEGIN

	IF EXISTS (SELECT * FROM OLAP.driver WHERE raceId = NEW.raceId AND driverId = NEW.driverId) THEN
		UPDATE OLAP.driver 
		SET driverStandingsId = NEW.driverStandingsId, points = NEW.points, positionStanding = NEW.position, wins = NEW.wins, positionText = NEW.positionText
		WHERE OLAP.driver.raceId = NEW.raceId AND OLAP.driver.driverId = NEW.driverId;
	ELSE
		INSERT INTO OLAP.driver (driverStandingsId, raceId, driverId, points, positionStanding, wins, positionText)
		VALUES (NEW.driverStandingsId, NEW.raceId, NEW.driverId, NEW.points, NEW.position, NEW.wins, NEW.positionText);
    END IF;
    
END$$

DROP TRIGGER IF EXISTS OLTP.UpdateDriver$$
CREATE TRIGGER OLTP.UpdateDriver 
AFTER INSERT
ON OLTP.drivers FOR EACH ROW
BEGIN

	IF EXISTS (SELECT * FROM OLAP.driver WHERE driverId = NEW.driverId) THEN
		UPDATE OLAP.driver 
		SET driverRef= NEW.driverRef, number = NEW.number, code = NEW.code, forename = NEW.forename, surname= NEW.surname, dob = NEW.dob, nationality = NEW.nationality, 
			url = NEW.url
		WHERE OLAP.driver.driverId = NEW.driverId;
	ELSE
		INSERT INTO OLAP.driver (driverId, driverRef, number, code, forename, surname, dob, nationality, url)
		VALUES (NEW.driverId, NEW.driverRef, NEW.number, NEW.code, NEW.forename, NEW.surname, NEW.dob, NEW.nationality, NEW.url);
	END IF;
    
END$$

DROP TABLE IF EXISTS checkTables CASCADE$$
CREATE TABLE checkTables (
	Taula VARCHAR(255),
    Correcte BOOLEAN,
    Data DATETIME
)$$

DROP EVENT IF EXISTS checkRaces$$
CREATE EVENT IF NOT EXISTS checkRaces
ON SCHEDULE EVERY 10 MINUTE
DO BEGIN
	IF (SELECT COUNT(*) FROM OLAP.races) = 
		(SELECT COUNT(*) FROM OLTP.races AS r JOIN OLTP.Seasons AS s ON r.year = s.year RIGHT JOIN OLTP.circuits AS c ON c.circuitId = r.circuitId) THEN
		UPDATE checkTables
        SET Correcte = true
        WHERE Taula = 'Races';
	ELSE
		UPDATE checkTables
        SET Correcte = false
        WHERE Taula = 'Races';
	END IF;
END $$

DROP EVENT IF EXISTS checkResults$$
CREATE EVENT IF NOT EXISTS checkResults
ON SCHEDULE EVERY 10 MINUTE
DO BEGIN
	IF (SELECT COUNT(*) FROM OLAP.results) = 
		(SELECT COUNT(*)
        FROM
			((SELECT resultId, qualifyId, s.statusId
			FROM
				((SELECT resultId, qualifyId, statusId
				FROM OLTP.results AS r 
				LEFT JOIN OLTP.qualifying AS q ON r.raceId = q.raceId AND r.driverId = q.driverId AND r.constructorId = q.constructorId)
				UNION
				(SELECT resultId, qualifyId, statusId 
				FROM OLTP.results AS r 
				RIGHT JOIN OLTP.qualifying AS q ON r.raceId = q.raceId AND r.driverId = q.driverId AND r.constructorId = q.constructorId)) AS t1
			LEFT JOIN OLTP.status AS s ON t1.statusId = s.statusId)
		UNION 
			(SELECT resultId, qualifyId, s.statusId
			FROM
				((SELECT resultId, qualifyId, statusId
				FROM OLTP.results AS r 
				LEFT JOIN OLTP.qualifying AS q ON r.raceId = q.raceId AND r.driverId = q.driverId AND r.constructorId = q.constructorId)
				UNION
				(SELECT resultId, qualifyId, statusId 
				FROM OLTP.results AS r 
				RIGHT JOIN OLTP.qualifying AS q ON r.raceId = q.raceId AND r.driverId = q.driverId AND r.constructorId = q.constructorId)) AS t1
			RIGHT JOIN OLTP.status AS s ON t1.statusId = s.statusId)) AS t3) THEN
			
		UPDATE checkTables
        SET Correcte = true
        WHERE Taula = 'Results';
	ELSE
		UPDATE checkTables
        SET Correcte = false
        WHERE Taula = 'Results';
	END IF;
END $$

DROP EVENT IF EXISTS checkConstructor$$
CREATE EVENT IF NOT EXISTS checkConstructor
ON SCHEDULE EVERY 10 MINUTE
DO BEGIN
	IF (SELECT COUNT(*) FROM OLAP.constructor) = (SELECT COUNT(*)
	FROM (SELECT c.constructorId, constructorResultsId, constructorStandingsId
		FROM (SELECT cr.constructorId, constructorResultsId, constructorStandingsId
		FROM OLTP.constructorResults AS cr 
		LEFT JOIN OLTP.constructorStandings AS cs ON cr.constructorId = cs.constructorId AND cr.raceId = cs.raceId
		UNION
		SELECT cr.constructorId, constructorResultsId, constructorStandingsId
		FROM OLTP.constructorResults AS cr 
		RIGHT JOIN OLTP.constructorStandings AS cs ON cr.constructorId = cs.constructorId AND cr.raceId = cs.raceId) AS t1
	RIGHT JOIN OLTP.constructors AS c ON t1.constructorId = c.constructorId
	UNION
	SELECT c.constructorId, constructorResultsId, constructorStandingsId
		FROM (SELECT cr.constructorId, constructorResultsId, constructorStandingsId
		FROM OLTP.constructorResults AS cr 
		LEFT JOIN OLTP.constructorStandings AS cs ON cr.constructorId = cs.constructorId AND cr.raceId = cs.raceId
		UNION
		SELECT cr.constructorId, constructorResultsId, constructorStandingsId
		FROM OLTP.constructorResults AS cr 
		RIGHT JOIN OLTP.constructorStandings AS cs ON cr.constructorId = cs.constructorId AND cr.raceId = cs.raceId) AS t1
	LEFT JOIN OLTP.constructors AS c ON t1.constructorId = c.constructorId) AS t2) THEN
		
        UPDATE checkTables
        SET Correcte = true
        WHERE Taula = 'Constructor';
	ELSE
		UPDATE checkTables
        SET Correcte = false
        WHERE Taula = 'Constructor';
	END IF;
END $$

DROP EVENT IF EXISTS checkDriver$$
CREATE EVENT IF NOT EXISTS checkDriver
ON SCHEDULE EVERY 10 MINUTE
DO BEGIN
	IF (SELECT COUNT(*) FROM OLAP.driver) = 
		(SELECT COUNT(*)
		FROM
		((SELECT stop, pt.time AS time2, duration, pt.milliseconds, lt.lap, lt.position, lt.time AS time1, lt.milliseconds AS ms, driverStandingsId, points, ds.position AS pos, wins, positionText, ds.driverId, ds.raceId
		FROM OLTP.pitStops AS pt 
		RIGHT JOIN OLTP.lapTimes AS lt ON pt.raceId = lt.raceId AND pt.driverId = lt.driverId AND pt.lap = lt.lap 
		RIGHT JOIN OLTP.driverStandings AS ds ON ds.raceId = lt.raceId AND ds.driverId = lt.driverId)
		UNION
		(SELECT stop, pt.time AS time2, duration, pt.milliseconds, lt.lap, lt.position, lt.time AS time1, lt.milliseconds AS ms, driverStandingsId, points, ds.position AS pos, wins, positionText, lt.driverId, lt.raceId
		FROM OLTP.pitStops AS pt 
		RIGHT JOIN OLTP.lapTimes AS lt ON pt.raceId = lt.raceId AND pt.driverId = lt.driverId AND pt.lap = lt.lap 
		LEFT JOIN OLTP.driverStandings AS ds ON ds.raceId = lt.raceId AND ds.driverId = lt.driverId)) AS t3
		RIGHT JOIN drivers AS d ON d.driverId = t3.driverId)
		THEN
        UPDATE checkTables
        SET Correcte = true
        WHERE Taula = 'Driver';
	ELSE
		UPDATE checkTables
        SET Correcte = false
        WHERE Taula = 'Driver';
	END IF;
END $$

DELIMITER ;