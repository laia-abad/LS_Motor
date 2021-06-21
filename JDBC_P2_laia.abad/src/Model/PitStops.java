package Model;

import java.sql.Time;

public class PitStops {
    private int raceId;
    private int driverId;
    private int stop;
    private int lap;
    private Time time;
    private String duration;
    private int milliseconds;

    public int getMilliseconds() {
        return milliseconds;
    }

    public int getLap() {
        return lap;
    }

    public int getRaceId() {
        return raceId;
    }

    public int getDriverId() {
        return driverId;
    }

    public int getStop() {
        return stop;
    }

    public String getDuration() {
        return duration;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setMilliseconds(int milliseconds) {
        this.milliseconds = milliseconds;
    }

    public void setLap(int lap) {
        this.lap = lap;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setStop(int stop) {
        this.stop = stop;
    }

    @Override
    public String toString() {
        return "PitStops{" +
                "raceId=" + raceId +
                ", driverId=" + driverId +
                ", stop=" + stop +
                ", time=" + time +
                ", lap=" + lap +
                ", duration='" + duration + '\'' +
                ", milliseconds=" + milliseconds +
                '}';
    }
}
