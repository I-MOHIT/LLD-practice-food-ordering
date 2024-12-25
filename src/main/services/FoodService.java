package main.services;

import main.models.FoodItem;
import main.models.Restaurant;

import java.util.*;

public class FoodService {
    private Map<UUID, FoodItem> uuidFoodItemMap;
    private Map<String, List<Restaurant>> foodItemNameRestaurantMap;

    public FoodService() {
        this.uuidFoodItemMap = new HashMap<>();
        this.foodItemNameRestaurantMap = new HashMap<>();
    }

    public Map<UUID, FoodItem> getUuidFoodItemMap() {
        return uuidFoodItemMap;
    }

    public void setUuidFoodItemMap(Map<UUID, FoodItem> uuidFoodItemMap) {
        this.uuidFoodItemMap = uuidFoodItemMap;
    }

    public Map<String, List<Restaurant>> getFoodItemNameRestaurantMap() {
        return foodItemNameRestaurantMap;
    }

    public void setFoodItemNameRestaurantMap(Map<String, List<Restaurant>> foodItemNameRestaurantMap) {
        this.foodItemNameRestaurantMap = foodItemNameRestaurantMap;
    }

    public void addFoodItemToHashMap(FoodItem foodItem) {
        this.uuidFoodItemMap.put(foodItem.getFoodItemId(), foodItem);
    }

    public void addRestaurantToFoodItemRestaurantsHashMap(String foodItemName, Restaurant restaurant) {
        if (!this.foodItemNameRestaurantMap.containsKey(foodItemName)) {
            this.foodItemNameRestaurantMap.put(foodItemName, new ArrayList<>());
        }

        List<Restaurant> restaurantList = this.foodItemNameRestaurantMap.get(foodItemName);
        if (!restaurantList.contains(restaurant)) {
            restaurantList.add(restaurant);
        }
        this.foodItemNameRestaurantMap.put(foodItemName, restaurantList);
    }
}
