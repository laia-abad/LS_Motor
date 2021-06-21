package Model;

import java.util.Date;

public class Drivers {
    private int driverId;
    private String driverRef;
    private int number;
    private String code;
    private String forename;
    private String surname;
    private Date dob;
    private String nationality;
    private String url;

    public int getDriverId() {
        return driverId;
    }

    public String getCode() {
        return code;
    }

    public int getNumber() {
        return number;
    }

    public String getDriverRef() {
        return driverRef;
    }

    public String getNationality() {
        return nationality;
    }

    public String getUrl() {
        return url;
    }

    public Date getDob() {
        return dob;
    }

    public String getForename() {
        return forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setDriverRef(String driverRef) {
        this.driverRef = driverRef;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Drivers{" +
                "driverId=" + driverId +
                ", driverRef='" + driverRef + '\'' +
                ", number=" + number +
                ", code='" + code + '\'' +
                ", forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                ", dob=" + dob +
                ", nationality='" + nationality + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
