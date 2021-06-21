USE OLAP;

#&&1
SELECT status, statusId
FROM results AS r
WHERE resultId IS NULL AND qualifyId IS NULL;

#&&2
SELECT d.nationality, AVG(millisecondsStop)
FROM driver AS d 
JOIN races AS r ON d.raceId = r.raceId
JOIN results AS re ON re.raceId = r.raceId
JOIN constructor AS c ON c.constructorId = re.constructorId
GROUP BY d.nationality
HAVING AVG(millisecondsStop) <= ALL (SELECT AVG(millisecondsStop)
								FROM driver AS d 
								JOIN races AS r ON d.raceId = r.raceId
								JOIN results AS re ON re.raceId = r.raceId
								JOIN constructor AS c ON c.constructorId = re.constructorId
								GROUP BY d.nationality);

#&&3
SELECT forename, surname
FROM driver AS d JOIN results AS r ON d.driverId = r.driverId
WHERE q1 > q2 AND q2 > q3 AND positionOrder >= 3 AND positionOrder IS NOT NULL AND fastestLapTime < ALL ((SELECT q1
																									FROM driver AS d1 
																									JOIN results AS r1 ON d1.driverId = r1.driverId
																									WHERE r1.raceId = r.raceId AND d1.driverId <> d.driverId)
																									UNION
																									(SELECT q2
																									FROM driver AS d1 
																									JOIN results AS r1 ON d1.driverId = r1.driverId
																									WHERE r1.raceId = r.raceId AND d1.driverId <> d.driverId)
																									UNION
																									(SELECT q3
																									FROM driver AS d1 
																									JOIN results AS r1 ON d1.driverId = r1.driverId
																									WHERE r1.raceId = r.raceId AND d1.driverId <> d.driverId));
										

#&&4
SELECT forename, surname, FastestLapSpeed, millisecondsLap, nameCircuit
FROM driver AS d
JOIN results AS re ON re.fastestLap = d.lap AND d.raceId = re.raceId AND d.driverId = re.driverId
JOIN race AS ra ON ra.raceId = re.raceId
WHERE FastestLapSpeed >= ALL (SELECT FastestLapSpeed FROM Results AS re1 WHERE re1.raceId = ra.raceId)
AND NOT EXISTS (SELECT forename, surname, FastestLapSpeed, millisecondsLap, nameCircuit
		FROM results AS re
		JOIN race AS ra ON ra.raceId = re.raceId
		JOIN driver AS d ON re.fastestLap = d.lap AND d.raceId = re.raceId AND d.driverId = re.driverId
		WHERE millisecondsLap <= ALL (SELECT millisecondsLap FROM driver AS d1 WHERE d1.raceId = ra.raceId) 
		GROUP BY ra.raceId)
GROUP BY ra.raceId
UNION
SELECT forename, surname, FastestLapSpeed, millisecondsLap, nameCircuit
FROM results AS re
JOIN race AS ra ON ra.raceId = re.raceId
JOIN driver AS d ON re.fastestLap = d.lap AND d.raceId = re.raceId AND d.driverId = re.driverId
WHERE millisecondsLap <= ALL (SELECT millisecondsLap FROM driver AS d1 WHERE d1.raceId = ra.raceId) 
AND NOT EXISTS (SELECT forename, surname, FastestLapSpeed, millisecondsLap, nameCircuit
			FROM driver AS d
			JOIN results AS re ON re.fastestLap = d.lap AND d.raceId = re.raceId AND d.driverId = re.driverId
			JOIN race AS ra ON ra.raceId = re.raceId
			WHERE FastestLapSpeed >= ALL (SELECT FastestLapSpeed FROM Results AS re1 WHERE re1.raceId = ra.raceId) 
			GROUP BY ra.raceId)
GROUP BY ra.raceId;

#&&5
SELECT ('In a lap period'), forename AS nameLP, surname AS surnameLP, nameCircuit AS nameCircuitLP, year AS yearLP, (d2.positionLap - d.positionLap) AS overtakingPositionLP
FROM driver AS d 
JOIN (SELECT lap, positionLap, raceId, driverId FROM driver) AS d2 ON d2.driverId = d.driverId AND d2.raceId = d.raceId AND d2.lap = d.lap + 1
JOIN races AS ra ON d.raceId = ra.raceId
WHERE d.lap <> 1 AND (d2.positionLap - d.positionLap) >= ALL (SELECT (d2.positionLap - d.positionLap)
											FROM driver AS d 
											JOIN (SELECT lap, positionLap, raceId, driverId FROM driver) AS d2 
                                            ON d2.driverId = d.driverId AND d2.raceId = d.raceId AND d2.lap = d.lap + 1)
UNION
SELECT 'For the whole race', forename AS nameWR, surname AS surnameWR, nameCircuit AS nameCircuitWR, year AS yearWR, (d.positionLap - d2.positionLap) AS overtakingPositionWR
FROM driver AS d
JOIN (SELECT lap, positionLap, driverId, raceId FROM driver GROUP BY raceId HAVING lap = MAX(lap)) AS d2 ON d2.driverId = d.driverId AND d2.raceId = d.raceId
JOIN race AS ra ON d.raceId = ra.raceId
WHERE d.lap = 2 AND (d.positionLap - d2.positionLap) >= ALL (SELECT (d.positionLap - d2.positionLap)
															FROM driver AS d
															JOIN (SELECT lap, positionLap, driverId, raceId FROM driver GROUP BY raceId HAVING lap = MAX(lap)) AS d2 
                                                            ON d2.driverId = d.driverId AND d2.raceId = d.raceId
															WHERE d.lap = 2);