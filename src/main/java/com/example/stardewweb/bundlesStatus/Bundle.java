package com.example.stardewweb.bundlesStatus;

import java.util.List;

public class Bundle {
    private Integer id;
    private String name;
    private Integer completeQuantity;
    private boolean complete;
    private List<Item> items;
    private List<Boolean> progressList;

    public Bundle() {
    }

    public Bundle(Integer id, String name, List<Item> items, Integer completeQuantity) {
        this.id = id;
        this.name = name;
        this.items = items;
        this.completeQuantity = completeQuantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Boolean> getProgressList() {
        return progressList;
    }

    public void setProgressList(List<Boolean> progressList) {
        this.progressList = progressList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCompleteQuantity() {
        return completeQuantity;
    }

    public void setCompleteQuantity(Integer completeQuantity) {
        this.completeQuantity = completeQuantity;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Bundle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", completeQuantity=" + completeQuantity +
                ", complete=" + complete +
                ", items=" + items +
                ", progressList=" + progressList +
                '}';
    }

    public int getCompletedItemCount() {
        int count = 0;
        for (Item item : items) {
            if (item.isComplete()) {
                count++;
            }
        }
        return count;
    }
}
