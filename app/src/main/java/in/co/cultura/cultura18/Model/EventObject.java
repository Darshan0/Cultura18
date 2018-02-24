package in.co.cultura.cultura18.Model;


public class EventObject {
    private String Name;
    private String Image;
    private String Category;

    public EventObject(String name, String image, String Category) {
        this.Name = name;
        this.Image = image;
        this.Category = Category;
    }

    public String getName() {
        return Name;
    }

    public String getImage() {
        return Image;
    }

    public String getCategory() {
        return Category;
    }
}
