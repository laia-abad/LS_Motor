package Model;

import java.sql.Time;
import java.util.Date;

public class Races {
    private int raceId;
    private int year;
    private int round;
    private int circuitId;
    private String name;
    private Date date;
    private Time time;
    private String url;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCircuitId(int circuitId) {
        this.circuitId = circuitId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getUrl() {
        return url;
    }

    public int getRaceId() {
        return raceId;
    }

    public Time getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public int getCircuitId() {
        return circuitId;
    }

    public int getYear() {
        return year;
    }

    public int getRound() {
        return round;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Races{" +
                "raceId=" + raceId +
                ", year=" + year +
                ", round=" + round +
                ", circuitId=" + circuitId +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", url=" + url + '\'' +
                '}';
    }
}
