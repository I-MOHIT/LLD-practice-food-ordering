package main.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Restaurant {
    private UUID restaurantId;
    private String name;
    private Location location;
    private List<UUID> foodItemList;
    private boolean isOpen;

    public Restaurant(String name, Location location, boolean isOpen) {
        this.restaurantId = UUID.randomUUID();
        this.name = name;
        this.location = location;
        this.foodItemList = new ArrayList<>();
        this.isOpen = isOpen;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(UUID restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<UUID> getFoodItemList() {
        return foodItemList;
    }

    public void setFoodItemList(List<UUID> foodItemList) {
        this.foodItemList = foodItemList;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
