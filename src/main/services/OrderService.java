package main.services;

import main.models.FoodItem;
import main.models.Order;
import main.models.User;

import java.util.*;

public class OrderService {
    FoodService foodService;
    private Map<UUID, Order> uuidOrderMap;
    private Map<UUID, List<UUID>> userIdOrdersMap;
    private Map<UUID, List<UUID>> restaurantIdOrdersMap;

    public OrderService(FoodService foodService) {
        this.foodService = foodService;
        this.uuidOrderMap = new HashMap<>();
        this.userIdOrdersMap = new HashMap<>();
        this.restaurantIdOrdersMap = new HashMap<>();
    }

    public Map<UUID, Order> getUuidOrderMap() {
        return uuidOrderMap;
    }

    public void setUuidOrderMap(Map<UUID, Order> uuidOrderMap) {
        this.uuidOrderMap = uuidOrderMap;
    }

    public Map<UUID, List<UUID>> getUserIdOrdersMap() {
        return userIdOrdersMap;
    }

    public void setUserIdOrdersMap(Map<UUID, List<UUID>> userIdOrdersMap) {
        this.userIdOrdersMap = userIdOrdersMap;
    }

    public Map<UUID, List<UUID>> getRestaurantIdOrdersMap() {
        return restaurantIdOrdersMap;
    }

    public void setRestaurantIdOrdersMap(Map<UUID, List<UUID>> restaurantIdOrdersMap) {
        this.restaurantIdOrdersMap = restaurantIdOrdersMap;
    }

    public void addOrdersToHashMap(Order order) {
        this.uuidOrderMap.put(order.getOrderId(), order);
    }

    public void addOrdersToUserOrdersHashMap(UUID userId, Order order) {
        if (!userIdOrdersMap.containsKey(userId)) {
            userIdOrdersMap.put(userId, new ArrayList<>());
        }
        userIdOrdersMap.get(userId).add(order.getOrderId());
    }

    public void addOrdersToRestaurantOrdersHashMap(UUID restaurantId, Order order) {
        if (!restaurantIdOrdersMap.containsKey(restaurantId)) {
            restaurantIdOrdersMap.put(restaurantId, new ArrayList<>());
        }
        restaurantIdOrdersMap.get(restaurantId).add(order.getOrderId());
    }

    public double calculateOrderAmount(Order order) {
        double totalAmount = 0;
        for(Map.Entry<UUID, Integer> foodItemQuantityMapEntry : order.getFoodItemQuantityMap().entrySet()) {
            FoodItem foodItem = this.foodService.getUuidFoodItemMap().get(foodItemQuantityMapEntry.getKey());
            totalAmount += foodItem.getPrice() * foodItemQuantityMapEntry.getValue();
        }

        return totalAmount;
    }
}
