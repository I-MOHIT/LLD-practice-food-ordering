package main.models;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Order {
    private UUID orderId;
    private UUID userId;
    private double orderAmount;
    private UUID restaurantId;
    private Map<UUID, Integer> foodItemQuantityMap;
    private OrderStatus orderStatus;

    public Order(UUID userId, UUID restaurantId) {
        this.orderId = UUID.randomUUID();
        this.userId = userId;
        this.orderAmount = 0;
        this.restaurantId = restaurantId;
        this.foodItemQuantityMap = new HashMap<>();
        this.orderStatus = OrderStatus.PENDING;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(UUID restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Map<UUID, Integer> getFoodItemQuantityMap() {
        return foodItemQuantityMap;
    }

    public void setFoodItemQuantityMap(Map<UUID, Integer> foodItemQuantityMap) {
        this.foodItemQuantityMap = foodItemQuantityMap;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
