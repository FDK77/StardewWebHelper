package com.example.stardewweb.models.items.fish;
import com.example.stardewweb.models.items.Object;

import java.util.List;


public class Fish extends Object {
    private int ItemID;
    private String difficulty;

    private List<String> locations;
    private int minLevel;
    private List<String> seasons;
    private String time;
    private boolean trapFish;
    private String weather;
    private String img;
    private Boolean caught;

    public Fish(int id, Boolean caught, String category, String description, String name, int itemID, String difficulty, List<String> locations, int minLevel, List<String> seasons, String time, boolean trapFish, String weather, String img) {
        super(id, category, description, name);
        ItemID = itemID;
        this.difficulty = difficulty;
        this.locations = locations;
        this.minLevel = minLevel;
        this.seasons = seasons;
        this.time = time;
        this.trapFish = trapFish;
        this.weather = weather;
        this.img = img;
        this.caught = caught;
    }

    public Fish() {
    }

    public String getDifficulty() {
        return difficulty;
    }

    public Boolean getCaught() {
        return caught;
    }

    public void setCaught(Boolean caught) {
        this.caught = caught;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public int getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(int minLevel) {
        this.minLevel = minLevel;
    }

    public List<String> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<String> seasons) {
        this.seasons = seasons;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isTrapFish() {
        return trapFish;
    }

    public void setTrapFish(boolean trapFish) {
        this.trapFish = trapFish;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getItemID() {
        return ItemID;
    }

    public void setItemID(int itemID) {
        ItemID = itemID;
    }

    @Override
    public String toString() {
        return "Fish{" +
                "ItemID=" + ItemID +
                ", difficulty='" + difficulty + '\'' +
                ", locations=" + locations +
                ", minLevel=" + minLevel +
                ", seasons=" + seasons +
                ", time='" + time + '\'' +
                ", trapFish=" + trapFish +
                ", weather='" + weather + '\'' +
                ", img='" + img + '\'' +
                '}';
    }

    public void setImage(){
        this.img = new String(String.format("/objectImgs/%d.png", ItemID));
    }
}
