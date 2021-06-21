package Controller;

import Model.*;

import java.sql.*;
import java.sql.Connection;

public class Controller {

    private Connection remoteConnection;
    private Connection localConnection;

    public boolean startRemoteConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            remoteConnection = DriverManager.getConnection("jdbc:mysql://puigpedros.salleurl.edu/?user=" + Settings.REMOTEUSER + "&password=" + Settings.REMOTEPASSWORD + "&serverTimezone=UTC");
            localConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=" + Settings.LOCALUSER + "&password=" + Settings.LOCALPASSWORD + "&serverTimezone=UTC");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void loadRemoteInfo () throws SQLException {
        ResultSet rs;
        Statement stmt;

        System.out.println("Reading remote info");

        stmt = remoteConnection.createStatement();
        stmt.executeQuery("USE F1;");

        stmt = localConnection.createStatement();
        stmt.executeQuery("USE OLTP;");

        stmt = localConnection.createStatement();
        stmt.executeUpdate("DELETE FROM circuits;");

        stmt = localConnection.createStatement();
        stmt.executeUpdate("DELETE FROM seasons;");

        stmt = localConnection.createStatement();
        stmt.executeUpdate("DELETE FROM races;");

        stmt = localConnection.createStatement();
        stmt.executeUpdate("DELETE FROM constructors;");

        stmt = localConnection.createStatement();
        stmt.executeUpdate("DELETE FROM constructorResults;");

        stmt = localConnection.createStatement();
        stmt.executeUpdate("DELETE FROM constructorStandings;");

        stmt = localConnection.createStatement();
        stmt.executeUpdate("DELETE FROM qualifying;");

        stmt = localConnection.createStatement();
        stmt.executeUpdate("DELETE FROM results;");

        stmt = localConnection.createStatement();
        stmt.executeUpdate("DELETE FROM status;");

        stmt = localConnection.createStatement();
        stmt.executeUpdate("DELETE FROM drivers;");

        stmt = localConnection.createStatement();
        stmt.executeUpdate("DELETE FROM driverStandings;");

        stmt = localConnection.createStatement();
        stmt.executeUpdate("DELETE FROM lapTimes;");

        stmt = localConnection.createStatement();
        stmt.executeUpdate("DELETE FROM pitStops;");

        System.out.println("Delete done");


        int i = 0;
        stmt = remoteConnection.createStatement();
        rs = stmt.executeQuery("SELECT * FROM races");
        Races race;
        while (rs.next()) {
            race = new Races();
            race.setRaceId(rs.getInt("raceId"));
            race.setYear(rs.getInt("year"));
            race.setRound(rs.getInt("round"));
            race.setCircuitId(rs.getInt("circuitId"));
            race.setName(rs.getString("name"));
            race.setDate(rs.getDate("date"));
            race.setTime(rs.getTime("time"));
            race.setUrl(rs.getString("url"));
            stmt = localConnection.createStatement();
            if (race.getDate() == null) {
                stmt.executeUpdate("INSERT INTO races VALUES (" + race.getRaceId() + ", " + race.getYear() + ", " + race.getRound() + ", " + race.getCircuitId() + ", '" +
                        race.getName() + "', " + race.getDate() + ", '" + race.getTime() + "', '" + race.getUrl() + "');");
            } else if (race.getTime() == null) {
                stmt.executeUpdate("INSERT INTO races VALUES (" + race.getRaceId() + ", " + race.getYear() + ", " + race.getRound() + ", " + race.getCircuitId() + ", '" +
                        race.getName() + "', '" + race.getDate() + "', " + race.getTime() + ", '" + race.getUrl() + "');");
            } else {
                stmt.executeUpdate("INSERT INTO races VALUES (" + race.getRaceId() + ", " + race.getYear() + ", " + race.getRound() + ", " + race.getCircuitId() + ", '" +
                        race.getName() + "', '" + race.getDate() + "', '" + race.getTime() + "', '" + race.getUrl() + "');");
            }
            System.out.println("Races: "+ i +" done");
            i++;
        }
        System.out.println("Races done");

        stmt = remoteConnection.createStatement();
        rs = stmt.executeQuery("SELECT * FROM seasons");
        i = 0;
        Seasons season;
        while (rs.next()) {
            season = new Seasons();
            season.setYear(rs.getInt("year"));
            season.setUrl(rs.getString("url"));
            stmt = localConnection.createStatement();
            stmt.executeUpdate("INSERT INTO seasons VALUES (" + season.getYear() + ", '" + season.getUrl() + "');");
            System.out.println("Seasons: "+ i + " done");
            i++;
        }
        System.out.println("Seasons done");

        stmt = remoteConnection.createStatement();
        rs = stmt.executeQuery("SELECT * FROM circuits");
        i = 0;

        Circuits circuit;
        while (rs.next()) {
            circuit = new Circuits();
            circuit.setCircuitId(rs.getInt("circuitId"));
            circuit.setCircuitRef(rs.getString("circuitRef"));
            circuit.setName(rs.getString("name"));
            circuit.setLocation(rs.getString("location"));
            circuit.setCountry(rs.getString("country"));
            circuit.setLat(rs.getFloat("lat"));
            circuit.setLng(rs.getFloat("lng"));
            circuit.setAlt(rs.getInt("alt"));
            circuit.setUrl(rs.getString("url"));
            stmt = localConnection.createStatement();
            stmt.executeUpdate("INSERT INTO circuits VALUES (" +  circuit.getCircuitId() + ", '" + circuit.getCircuitRef() + "', '" + circuit.getName() + "', '" +
                    circuit.getLocation() + "', '" + circuit.getCountry() + "', " + circuit.getLat() + ", " + circuit.getLng() + ", " + circuit.getAlt() + ", '" +
                    circuit.getUrl() + "');");
            System.out.println("Circuits: "+ i + " done");
            i++;
        }
        System.out.println("Circuits done");

        stmt = remoteConnection.createStatement();
        rs = stmt.executeQuery("SELECT * FROM constructorStandings");
        i = 0;

        ConstructorStandings constructorStanding;
        while (rs.next()) {
            constructorStanding = new ConstructorStandings();
            constructorStanding.setConstructorStandingsId(rs.getInt("constructorStandingsId"));
            constructorStanding.setRaceId(rs.getInt("raceId"));
            constructorStanding.setConstructorId(rs.getInt("constructorId"));
            constructorStanding.setPoints(rs.getFloat("points"));
            constructorStanding.setPosition(rs.getInt("position"));
            constructorStanding.setPositionText(rs.getString("positionText"));
            constructorStanding.setWins(rs.getInt("wins"));
            stmt = localConnection.createStatement();
            stmt.executeUpdate("INSERT INTO constructorStandings VALUES (" +  constructorStanding.getConstructorStandingsId() + ", " + constructorStanding.getRaceId() + ", " +
                    constructorStanding.getConstructorId() + ", " + constructorStanding.getPoints() + ", " + constructorStanding.getPosition() + ", '" +
                    constructorStanding.getPositionText() + "', " + constructorStanding.getWins() + ");");
            System.out.println("ConstructorStandings: " + i + " done");
            i++;
        }
        System.out.println("ConstructorStandings done");

        stmt = remoteConnection.createStatement();
        rs = stmt.executeQuery("SELECT * FROM constructorResults");
        i = 0;

        ConstructorResults constructorResult;
        while (rs.next()) {
            constructorResult = new ConstructorResults();
            constructorResult.setConstructorResultsId(rs.getInt("constructorResultsId"));
            constructorResult.setRaceId(rs.getInt("raceId"));
            constructorResult.setConstructorId(rs.getInt("constructorId"));
            constructorResult.setPoints(rs.getFloat("points"));
            constructorResult.setStatus(rs.getString("status"));
            stmt = localConnection.createStatement();
            stmt.executeUpdate("INSERT INTO constructorResults VALUES (" +  constructorResult.getConstructorResultsId() + ", " + constructorResult.getRaceId() + ", " +
                    constructorResult.getConstructorId() + ", " + constructorResult.getPoints() + ", '" + constructorResult.getStatus() + "');");
            System.out.println("ConstructorResults: " + i + " done");
            i++;
        }
        System.out.println("ConstructorResults done");

        stmt = remoteConnection.createStatement();
        rs = stmt.executeQuery("SELECT * FROM constructors");
        i = 0;
        Constructors constructor;
        while (rs.next()) {
            constructor = new Constructors();
            constructor.setConstructorId(rs.getInt("constructorId"));
            constructor.setConstructorRef(rs.getString("constructorRef"));
            constructor.setName(rs.getString("name"));
            constructor.setNationality(rs.getString("nationality"));
            constructor.setUrl(rs.getString("url"));
            stmt = localConnection.createStatement();
            stmt.executeUpdate("INSERT INTO constructors VALUES (" + constructor.getConstructorId() + ", '" + constructor.getConstructorRef() + "', '" + constructor.getName() +
                    "', '" + constructor.getNationality() + "', '" + constructor.getUrl() + "');");
            System.out.println("Constructors: "+ i + " done");
            i++;
        }
        System.out.println("Constructors done");

        stmt = remoteConnection.createStatement();
        rs = stmt.executeQuery("SELECT * FROM results");
        i = 0;

        Results result;
        while (rs.next()) {
            result = new Results();
            result.setResultId(rs.getInt("resultId"));
            result.setRaceId(rs.getInt("raceId"));
            result.setDriverId(rs.getInt("driverId"));
            result.setConstructorId(rs.getInt("constructorId"));
            result.setNumber(rs.getInt("number"));
            result.setGrid(rs.getInt("grid"));
            result.setPosition(rs.getInt("position"));
            result.setPositionText(rs.getString("positionText"));
            result.setPositionOrder(rs.getInt("positionOrder"));
            result.setPoints(rs.getFloat("points"));
            result.setLaps(rs.getInt("laps"));
            result.setTime(rs.getString("time"));
            result.setMilliseconds(rs.getInt("milliseconds"));
            result.setFastestLap(rs.getInt("fastestLap"));
            result.setRank(rs.getInt("rank"));
            result.setFastestLapTime(rs.getString("fastestLapTime"));
            result.setFastestLapSpeed(rs.getString("fastestLapSpeed"));
            result.setStatusId(rs.getInt("statusId"));
            stmt = localConnection.createStatement();
            stmt.executeUpdate("INSERT INTO results VALUES (" + result.getResultId() + ", " + result.getRaceId() + ", " + result.getDriverId() + ", " + result.getConstructorId() + ", " +
                    result.getNumber() + ", " + result.getGrid() + ", " + result.getPosition() + ", '" + result.getPositionText() + "', " + result.getPositionOrder() + ", " +
                    result.getPoints() + ", " + result.getLaps() + ", '" + result.getTime() + "', " + result.getMilliseconds() + ", " + result.getFastestLap() + ", " +
                    result.getRank() + ", '" + result.getFastestLapTime() + "', '" + result.getFastestLapSpeed() + "', " + result.getStatusId() + ");");

            System.out.println("Result: "+ i + " done");
            i++;
        }
        System.out.println("Results done");

        stmt = remoteConnection.createStatement();
        rs = stmt.executeQuery("SELECT * FROM qualifying");
        i = 0;

        Qualifying qualifying;
        while (rs.next()) {
            qualifying = new Qualifying();
            qualifying.setQualifyId(rs.getInt("qualifyId"));
            qualifying.setRaceId(rs.getInt("raceId"));
            qualifying.setDriverId(rs.getInt("driverId"));
            qualifying.setConstructorId(rs.getInt("constructorId"));
            qualifying.setNumber(rs.getInt("number"));
            qualifying.setPosition(rs.getInt("position"));
            qualifying.setQ1(rs.getString("q1"));
            qualifying.setQ2(rs.getString("q2"));
            qualifying.setQ3(rs.getString("q3"));
            stmt = localConnection.createStatement();
            stmt.executeUpdate("INSERT INTO qualifying VALUES (" + qualifying.getQualifyId() + ", " + qualifying.getRaceId() + ", " + qualifying.getDriverId() + ", " +
                    qualifying.getConstructorId() + ", " + qualifying.getNumber() + ", " + qualifying.getPosition() + ", '" + qualifying.getQ1() + "', '" +
                    qualifying.getQ2() + "', '" + qualifying.getQ3() + "');");
            System.out.println("Qualifying: "+ i + " done");
            i++;
        }
        System.out.println("Qualifying done");

        stmt = remoteConnection.createStatement();
        rs = stmt.executeQuery("SELECT * FROM status");
        i = 0;

        Status status;
        while (rs.next()) {
            status = new Status();
            status.setStatusId(rs.getInt("statusId"));
            status.setStatus(rs.getString("status"));
            stmt = localConnection.createStatement();
            stmt.executeUpdate("INSERT INTO status VALUES (" + status.getStatusId() + ", '" + status.getStatus() + "');");
            System.out.println("Status: " + i + " done");
            i++;
        }
        System.out.println("Status done");

        stmt = remoteConnection.createStatement();
        rs = stmt.executeQuery("SELECT * FROM lapTimes");
        i = 0;

        LapTimes lapTime;
        while (rs.next()) {
            lapTime = new LapTimes();
            lapTime.setRaceId(rs.getInt("raceId"));
            lapTime.setDriverId(rs.getInt("driverId"));
            lapTime.setLap(rs.getInt("lap"));
            lapTime.setPosition(rs.getInt("position"));
            lapTime.setTime(rs.getString("time"));
            lapTime.setMilliseconds(rs.getInt("milliseconds"));
            stmt = localConnection.createStatement();
            stmt.executeUpdate("INSERT INTO lapTimes VALUES (" + lapTime.getRaceId() + ", " + lapTime.getDriverId() + ", " + lapTime.getLap() + ", " + lapTime.getPosition() +
                    ", '" + lapTime.getTime() + "', " + lapTime.getMilliseconds() + ");");
            System.out.println("LapTimes: "+ i +" done");
            i++;
        }
        System.out.println("LapTimes done");



        stmt = remoteConnection.createStatement();
        rs = stmt.executeQuery("SELECT * FROM driverStandings");
        i = 0;
        DriverStandings driverStanding;
        while (rs.next()) {
            driverStanding = new DriverStandings();
            driverStanding.setDriverStandingsId(rs.getInt("driverStandingsId"));
            driverStanding.setRaceId(rs.getInt("raceId"));
            driverStanding.setDriverId(rs.getInt("driverId"));
            driverStanding.setPoints(rs.getFloat("points"));
            driverStanding.setPosition(rs.getInt("position"));
            driverStanding.setPositionText(rs.getString("positionText"));
            driverStanding.setWins(rs.getInt("wins"));
            stmt = localConnection.createStatement();
            stmt.executeUpdate("INSERT INTO driverStandings VALUES (" + driverStanding.getDriverStandingsId() + ", " + driverStanding.getRaceId() + ", " +
                    driverStanding.getDriverId() + ", " + driverStanding.getPoints() + ", " + driverStanding.getPosition() + ", '" + driverStanding.getPositionText() + "', " +
                    driverStanding.getWins() + ");");
            System.out.println("DriverStandings: "+ i +" done");
            i++;
        }
        System.out.println("DriverStandings done");

        stmt = remoteConnection.createStatement();
        rs = stmt.executeQuery("SELECT * FROM pitStops");
        i = 0;

        PitStops pitStop;
        while (rs.next()) {
            pitStop = new PitStops();
            pitStop.setRaceId(rs.getInt("raceId"));
            pitStop.setDriverId(rs.getInt("driverId"));
            pitStop.setStop(rs.getInt("stop"));
            pitStop.setLap(rs.getInt("lap"));
            pitStop.setTime(rs.getTime("time"));
            pitStop.setDuration(rs.getString("duration"));
            pitStop.setMilliseconds(rs.getInt("milliseconds"));
            stmt = localConnection.createStatement();
            stmt.executeUpdate("INSERT INTO pitStops VALUES (" + pitStop.getRaceId() + ", " + pitStop.getDriverId() + ", " + pitStop.getStop() + ", " + pitStop.getLap() + ", '" + pitStop.getTime() + "', '" +
                    pitStop.getDuration() + "', " + pitStop.getMilliseconds() + ");");
            System.out.println("PitStops: "+ i +" done");
            i++;
        }
        System.out.println("PitStops done");

        stmt = remoteConnection.createStatement();
        rs = stmt.executeQuery("SELECT * FROM drivers");
        i = 0;

        Drivers driver;
        while (rs.next()) {
            driver = new Drivers();
            driver.setDriverId(rs.getInt("driverId"));
            driver.setDriverRef(rs.getString("driverRef"));
            driver.setNumber(rs.getInt("number"));
            driver.setCode(rs.getString("code"));
            driver.setForename(rs.getString("forename"));
            driver.setSurname(rs.getString("surname"));
            if (driver.getSurname().contains("'")) {
                driver.setSurname(driver.getSurname().replace("'", "''"));
            }
            driver.setDob(rs.getDate("dob"));
            driver.setNationality(rs.getString("nationality"));
            driver.setUrl(rs.getString("url"));
            stmt = localConnection.createStatement();
            stmt.executeUpdate("INSERT INTO drivers VALUES (" + driver.getDriverId() + ", '" + driver.getDriverRef() + "', " + driver.getNumber() + ", '" + driver.getCode() +
                    "', '" + driver.getForename() + "', '" + driver.getSurname() + "', '" + driver.getDob() + "', '" + driver.getNationality() + "', '" + driver.getUrl() + "');");
            System.out.println("Drivers: "+ i +" done");
            i++;
        }
        System.out.println("Drivers done");

        rs.close();
        stmt.close();

        System.out.println("Done");


    }
}
