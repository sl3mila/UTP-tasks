package appeals;

import java.awt.*;

public class TypeOfAppeal {
    int id;
    String type;
    public String getType() {
        return type;
    }
    String details;
    public String getDetails() {
        return details;
    }
    Color color;
    public Color getColor() {
        return color;
    }

    public TypeOfAppeal(int id, String type, String details, Color color) {
        this.id = id;
        this.type = type;
        this.details = details;
        this.color = color;
    }
}
