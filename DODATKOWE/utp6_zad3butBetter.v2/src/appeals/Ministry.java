package appeals;

public class Ministry {

    int id;
    public  int getId() {
        return id;
    }
    String name;
    public String getName() {
        return name;
    }
    String details;
    public String getDetails() {
        return details;
    }

    public Ministry (int id, String name, String details) {
        this.id = id;
        this.name = name;
        this.details = details;
    }

}