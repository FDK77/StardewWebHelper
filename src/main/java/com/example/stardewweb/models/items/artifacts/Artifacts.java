package com.example.stardewweb.models.items.artifacts;

import java.util.List;

public class Artifacts {
    private int ItemID;
    private String name;
    private List<String> locations;
    private String img;
    private Boolean assembled;

    public Artifacts(int itemID, String name, List<String> locations, String img, Boolean assembled) {
        ItemID = itemID;
        this.name = name;
        this.locations = locations;
        this.img = img;
        this.assembled = assembled;
        setImage();
    }

    public Artifacts() {
    }

    public int getItemID() {
        return ItemID;
    }

    public void setItemID(int itemID) {
        ItemID = itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
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

    public void setImage(){
        this.img = new String(String.format("/objectImgs/%d.png", ItemID));
    }

}
