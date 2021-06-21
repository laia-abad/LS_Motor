package Model;

public class DriverStandings {
    private int driverStandingsId;
    private int raceId;
    private int driverId;
    private float points;
    private int position;
    private String positionText;
    private int wins;

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setPositionText(String positionText) {
        this.positionText = positionText;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public void setPoints(float points) {
        this.points = points;
    }

    public void setDriverStandingsId(int driverStandingsId) {
        this.driverStandingsId = driverStandingsId;
    }

    public int getPosition() {
        return position;
    }

    public String getPositionText() {
        return positionText;
    }

    public float getPoints() {
        return points;
    }

    public int getRaceId() {
        return raceId;
    }

    public int getWins() {
        return wins;
    }

    public int getDriverId() {
        return driverId;
    }

    public int getDriverStandingsId() {
        return driverStandingsId;
    }

    @Override
    public String toString() {
        return "DriverStandings{" +
                "driverStandingsId=" + driverStandingsId +
                ", raceId=" + raceId +
                ", driverId=" + driverId +
                ", points=" + points +
                ", position=" + position +
                ", positionText='" + positionText + '\'' +
                ", wins=" + wins +
                '}';
    }
}
