package Model;

public class Results {
    private int resultId;
    private int raceId;
    private int driverId;
    private int constructorId;
    private int number;
    private int grid;
    private int position;
    private String positionText;
    private int positionOrder;
    private float points;
    private int laps;
    private String time;
    private int milliseconds;
    private int fastestLap;
    private int rank;
    private String fastestLapTime;
    private String fastestLapSpeed;
    private int statusId;

    public int getConstructorId() {
        return constructorId;
    }

    public int getNumber() {
        return number;
    }

    public int getPosition() {
        return position;
    }

    public int getRaceId() {
        return raceId;
    }

    public int getDriverId() {
        return driverId;
    }

    public int getMilliseconds() {
        return milliseconds;
    }

    public String getTime() {
        return time;
    }

    public float getPoints() {
        return points;
    }

    public int getGrid() {
        return grid;
    }

    public int getResultId() {
        return resultId;
    }

    public String getPositionText() {
        return positionText;
    }

    public int getFastestLap() {
        return fastestLap;
    }

    public int getLaps() {
        return laps;
    }

    public int getPositionOrder() {
        return positionOrder;
    }

    public int getRank() {
        return rank;
    }

    public int getStatusId() {
        return statusId;
    }

    public String getFastestLapSpeed() {
        return fastestLapSpeed;
    }

    public String getFastestLapTime() {
        return fastestLapTime;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public void setConstructorId(int constructorId) {
        this.constructorId = constructorId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setMilliseconds(int milliseconds) {
        this.milliseconds = milliseconds;
    }

    public void setGrid(int grid) {
        this.grid = grid;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPoints(float points) {
        this.points = points;
    }

    public void setPositionText(String positionText) {
        this.positionText = positionText;
    }

    public void setFastestLapSpeed(String fastestLapSpeed) {
        this.fastestLapSpeed = fastestLapSpeed;
    }

    public void setPositionOrder(int positionOrder) {
        this.positionOrder = positionOrder;
    }

    public void setFastestLapTime(String fastestLapTime) {
        this.fastestLapTime = fastestLapTime;
    }

    public void setLaps(int laps) {
        this.laps = laps;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public void setFastestLap(int fastestLap) {
        this.fastestLap = fastestLap;
    }

    @Override
    public String toString() {
        return "Results{" +
                "resultId=" + resultId +
                ", raceId=" + raceId +
                ", driverId=" + driverId +
                ", constructorId=" + constructorId +
                ", grid=" + grid +
                ", position=" + position +
                ", positionText='" + positionText + '\'' +
                ", positionOrder=" + positionOrder +
                ", points=" + points +
                ", laps=" + laps +
                ", time='" + time + '\'' +
                ", milliseconds=" + milliseconds +
                ", fastestLapId=" + fastestLap +
                ", rank=" + rank +
                ", fastestLapTime='" + fastestLapTime + '\'' +
                ", fastestLapSpeed='" + fastestLapSpeed + '\'' +
                ", statusId=" + statusId +
                '}';
    }
}
