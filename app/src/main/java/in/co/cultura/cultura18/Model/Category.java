package in.co.cultura.cultura18.Model;



public class Category {
    private String Name;
    private String Image;

    public Category(){

    }
    public Category(String name, String image) {
        Name = name;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public String getImage() {
        return Image;
    }
}
