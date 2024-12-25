package main.models;

import java.util.UUID;

public class FoodItem {
    private UUID foodItemId;
    private String name;
    private double price;

    public FoodItem(String name, double price) {
        this.foodItemId = UUID.randomUUID();
        this.name = name;
        this.price = price;
    }

    public UUID getFoodItemId() {
        return foodItemId;
    }

    public void setFoodItemId(UUID foodItemId) {
        this.foodItemId = foodItemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
