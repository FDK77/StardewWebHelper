package com.example.stardewweb.models.gameOther;

public class Achivements {
    private int id;
    private String name;
    private String description;
    private String img;
    private Boolean assembled;

    public Achivements(int id, String name, String description, String img, Boolean assembled) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.img = img;
        this.assembled = assembled;
    }

    public Achivements() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Boolean getAssembled() {
        return assembled;
    }

    public void setAssembled(Boolean assembled) {
        this.assembled = assembled;
    }

    @Override
    public String toString() {
        return "Achivements{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", img='" + img + '\'' +
                ", assembled=" + assembled +
                '}';
    }

    public void setImage(){
        this.img = new String(String.format("/achivementsPics/%d.jpg", id));
    }
}
