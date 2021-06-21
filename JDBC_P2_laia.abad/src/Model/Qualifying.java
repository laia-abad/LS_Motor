package Model;

public class Qualifying {
    private int qualifyId;
    private int raceId;
    private int driverId;
    private int constructorId;
    private int number;
    private int position;
    private String q1;
    private String q2;
    private String q3;

    public int getDriverId() {
        return driverId;
    }

    public int getRaceId() {
        return raceId;
    }

    public int getPosition() {
        return position;
    }

    public int getNumber() {
        return number;
    }

    public int getConstructorId() {
        return constructorId;
    }

    public int getQualifyId() {
        return qualifyId;
    }

    public String getQ1() {
        return q1;
    }

    public String getQ2() {
        return q2;
    }

    public String getQ3() {
        return q3;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setConstructorId(int constructorId) {
        this.constructorId = constructorId;
    }

    public void setQ1(String q1) {
        this.q1 = q1;
    }

    public void setQualifyId(int qualifyId) {
        this.qualifyId = qualifyId;
    }

    public void setQ2(String q2) {
        this.q2 = q2;
    }

    public void setQ3(String q3) {
        this.q3 = q3;
    }

    @Override
    public String toString() {
        return "Qualifying{" +
                "qualifyId=" + qualifyId +
                ", raceId=" + raceId +
                ", driverId=" + driverId +
                ", constructorId=" + constructorId +
                ", number=" + number +
                ", position=" + position +
                ", q1='" + q1 + '\'' +
                ", q2='" + q2 + '\'' +
                ", q3='" + q3 + '\'' +
                '}';
    }
}
