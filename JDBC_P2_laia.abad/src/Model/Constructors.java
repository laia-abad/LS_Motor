package Model;

public class Constructors {
    private int constructorId;
    private String constructorRef;
    private String name;
    private String nationality;
    private String url;

    public String getNationality() {
        return nationality;
    }

    public int getConstructorId() {
        return constructorId;
    }

    public String getName() {
        return name;
    }

    public String getConstructorRef() {
        return constructorRef;
    }

    public String getUrl() {
        return url;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setConstructorRef(String constructorRef) {
        this.constructorRef = constructorRef;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setConstructorId(int constructorId) {
        this.constructorId = constructorId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Constructors{" +
                "constructorId='" + constructorId +
                ", constructorRef='" + constructorRef + '\'' +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
