package Model;

public class Circuits {
    private int circuitId;
    private String circuitRef;
    private String name;
    private String location;
    private String country;
    private float lat;
    private float lng;
    private int alt;
    private String url;

    public String getName() {
        return name;
    }

    public float getAlt() {
        return alt;
    }

    public int getCircuitId() {
        return circuitId;
    }

    public String getCircuitRef() {
        return circuitRef;
    }

    public float getLng() {
        return lng;
    }

    public String getCountry() {
        return country;
    }

    public String getUrl() {
        return url;
    }

    public float getLat() {
        return lat;
    }

    public String getLocation() {
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public void setCircuitId(int circuitId) {
        this.circuitId = circuitId;
    }

    public void setCircuitRef(String circuitRef) {
        this.circuitRef = circuitRef;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setAlt(int alt) {
        this.alt = alt;
    }

    @Override
    public String toString() {
        return "Circuit{" +
                "circuitId=" + circuitId +
                ", circuitRef='" + circuitRef + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", country='" + country + '\'' +
                ", lat='" + lat +
                ", lng=" + lng +
                ", alt=" + alt +
                ", url=" + url +
                '}';
    }
}
