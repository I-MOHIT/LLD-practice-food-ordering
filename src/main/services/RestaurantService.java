package main.services;

import main.models.FoodItem;
import main.models.Order;
import main.models.Restaurant;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RestaurantService {
    private Map<UUID, Restaurant> uuidRestaurantMap;
    private Map<UUID, Map<UUID, Integer>> restaurantFoodItemQuantityMap;

    public RestaurantService() {
        this.uuidRestaurantMap = new HashMap<>();
        this.restaurantFoodItemQuantityMap = new HashMap<>();
    }

    public Map<UUID, Restaurant> getUuidRestaurantMap() {
        return uuidRestaurantMap;
    }

    public void setUuidRestaurantMap(Map<UUID, Restaurant> uuidRestaurantMap) {
        this.uuidRestaurantMap = uuidRestaurantMap;
    }

    public Map<UUID, Map<UUID, Integer>> getRestaurantFoodItemQuantityMap() {
        return restaurantFoodItemQuantityMap;
    }

    public void setRestaurantFoodItemQuantityMap(Map<UUID, Map<UUID, Integer>> restaurantFoodItemQuantityMap) {
        this.restaurantFoodItemQuantityMap = restaurantFoodItemQuantityMap;
    }

    public void addRestaurantsToHashMap(Restaurant restaurant) {
        this.uuidRestaurantMap.put(restaurant.getRestaurantId(), restaurant);
        this.restaurantFoodItemQuantityMap.put(restaurant.getRestaurantId(), new HashMap<>());
    }

    synchronized public void restoreRestaurantInventory(Order order) {
        UUID restaurantId = order.getRestaurantId();
        Map<UUID, Integer> foodItemQuantityRestaurantMap = this.getRestaurantFoodItemQuantityMap().get(restaurantId);
        for (Map.Entry<UUID, Integer> foodItemQuantityOrderEntry : order.getFoodItemQuantityMap().entrySet()) {
            UUID foodItemId = foodItemQuantityOrderEntry.getKey();
            int currRestaurantQuantity = foodItemQuantityRestaurantMap.get(foodItemQuantityOrderEntry.getKey());
            int currOrderQuantity = foodItemQuantityOrderEntry.getValue();
            foodItemQuantityRestaurantMap.put(foodItemId, currRestaurantQuantity + currOrderQuantity);
            this.getRestaurantFoodItemQuantityMap().put(restaurantId, foodItemQuantityRestaurantMap);
        }
    }
}
