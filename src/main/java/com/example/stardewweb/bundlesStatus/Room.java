package com.example.stardewweb.bundlesStatus;

import java.util.List;

public class Room {
    private String name;
    private boolean complete;
    private List<Bundle> bundles;

    public Room() {
    }

    public Room(String name) {
        this.name = name;
    }

    public Room(String name, List<Bundle> bundles) {
        this.name = name;
        this.bundles = bundles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public List<Bundle> getBundles() {
        return bundles;
    }

    public void setBundles(List<Bundle> bundles) {
        this.bundles = bundles;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", complete=" + complete +
                ", bundles=" + bundles +
                '}';
    }

    public int getCompletedBundleCount() {
        int count = 0;
        for (Bundle bundle : bundles) {
            if (bundle.isComplete()) {
                count++;
            }
        }
        return count;
    }

    public void analyzeProgress(){
        if(complete==false) {
            for (Bundle bundle : bundles) {
                if (!bundle.isComplete()) {
                    for (int i = 0; i < (bundle.getProgressList().size() / 3); i++) {
                        bundle.getItems().get(i).setComplete(bundle.getProgressList().get(i));
                    }

                    int compItem = 0;
                    for (Item item : bundle.getItems()) {
                        if (item.isComplete()) {
                            compItem++;
                        }
                        if (compItem == bundle.getCompleteQuantity()) {
                            bundle.setComplete(true);
                        }
                    }
                }
            }
            int k =0;
            for (Bundle bundle:bundles){
                if(bundle.isComplete()){
                    k++;
                }
            }
            if(k== bundles.size()){
                complete = true;
            }
        }
    }
}
