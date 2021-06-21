package Model;

public class ConstructorStandings {
    private int constructorStandingsId;
    private int raceId;
    private int constructorId;
    private float points;
    private int position;
    private String positionText;
    private int wins;

    public void setConstructorId(int constructorId) {
        this.constructorId = constructorId;
    }

    public void setPoints(float points) {
        this.points = points;
    }

    public void setConstructorStandingsId(int constructorStandingsId) {
        this.constructorStandingsId = constructorStandingsId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setPositionText(String positionText) {
        this.positionText = positionText;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getRaceId() {
        return raceId;
    }

    public float getPoints() {
        return points;
    }

    public int getConstructorId() {
        return constructorId;
    }

    public int getConstructorStandingsId() {
        return constructorStandingsId;
    }

    public int getPosition() {
        return position;
    }

    public int getWins() {
        return wins;
    }

    public String getPositionText() {
        return positionText;
    }

    @Override
    public String toString() {
        return "constructorStandings{" +
                "constructorStandingsId=" + constructorId +
                ", raceId=" + raceId +
                ", constructorId=" + constructorId +
                ", points=" + points +
                ", position=" + position +
                ", positionText='" + positionText + '\'' +
                ", wins=" + wins +
                '}';
    }
}
