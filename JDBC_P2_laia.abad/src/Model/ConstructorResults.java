package Model;

public class ConstructorResults {
    private int constructorResultsId;
    private int raceId;
    private int constructorId;
    private float points;
    private String status;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public void setPoints(float points) {
        this.points = points;
    }

    public void setConstructorResultsId(int constructorResultsId) {
        this.constructorResultsId = constructorResultsId;
    }

    public void setConstructorId(int constructorId) {
        this.constructorId = constructorId;
    }

    public int getConstructorResultsId() {
        return constructorResultsId;
    }

    public int getConstructorId() {
        return constructorId;
    }

    public float getPoints() {
        return points;
    }

    public int getRaceId() {
        return raceId;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "constructorResults{" +
                "constructorResultsId=" + constructorId +
                ", raceId=" + raceId +
                ", constructorId=" + constructorId +
                ", points=" + points +
                ", status='" + status + '\'' +
                '}';
    }
}
