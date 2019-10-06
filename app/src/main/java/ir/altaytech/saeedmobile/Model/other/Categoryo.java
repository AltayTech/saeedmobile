package ir.altaytech.saeedmobile.Model.other;

public class Categoryo {
    private int id;
    private String title;
    private String image;
    private int parent;

    public Categoryo(int id, String title, String image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }

    public Categoryo(int id, String title, String image, int parent) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.parent = parent;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public int getParent() {
        return parent;
    }
}