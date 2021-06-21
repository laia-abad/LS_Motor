package Model;

public class LapTimes{
    private int raceId;
    private int driverId;
    private int lap;
    private int position;
    private String time;
    private int milliseconds;

    public int getDriverId() {
        return driverId;
    }

    public int getRaceId() {
        return raceId;
    }

    public int getPosition() {
        return position;
    }

    public String getTime() {
        return time;
    }

    public int getLap() {
        return lap;
    }

    public int getMilliseconds() {
        return milliseconds;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setLap(int lap) {
        this.lap = lap;
    }

    public void setMilliseconds(int milliseconds) {
        this.milliseconds = milliseconds;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "LapTimes{" +
                "raceId=" + raceId +
                ", driverId=" + driverId +
                ", lap=" + lap +
                ", position=" + position +
                ", time='" + time + '\'' +
                ", milliseconds=" + milliseconds +
                '}';
    }
}
