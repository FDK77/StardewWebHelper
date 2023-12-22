package com.example.stardewweb.bundlesStatus;

import java.io.File;

public class Item {
    private Integer id;
    private Integer quantity;
    private Integer quality;
    private Boolean complete;
    private String img;
    private String QualityImg;

    public Item(Integer id, Integer quantity, Integer quality) {
        this.id = id;
        this.quantity = quantity;
        this.quality = quality;
        this.img = new String(String.format("/objectImgs/%d.png", id));
        if (this.quantity==0){
            this.QualityImg = null;
        }
        else
        this.QualityImg = new String(String.format("/qualitypic/%d.png", quality));
    }

    public Integer getId() {
        return id;
    }

    public Boolean isComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    public Boolean getComplete() {
        return complete;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getQualityImg() {
        return QualityImg;
    }

    public Item(Integer id, Integer quantity, Integer quality, Boolean complete, String img) {
        this.id = id;
        this.quantity = quantity;
        this.quality = quality;
        this.complete = complete;
        this.img = img;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", quality=" + quality +
                ", complete=" + complete +
                ", img=" + img +
                '}';
    }
}
