package main;

import main.models.FoodItem;
import main.models.Order;
import main.models.Restaurant;
import main.models.User;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OutputPrinter {
    public void hello() {
        System.out.println("Hello!");
    }

    public void exit() {
        System.out.println("Bye!");
    }

    public void createUser(User user) {
        System.out.println("User " + user.getName() + " having user id " + user.getUserId() + " has been created");
    }

    public void createRestaurant(Restaurant restaurant) {
        System.out.println("Restaurant " + restaurant.getName() + " having restaurant id " + restaurant.getRestaurantId() + " has been created");
    }

    public void setRestaurantOpenStatus(Restaurant restaurant, String status) {
        System.out.println("Restaurant " + restaurant.getName() + " status has been changed to " + status);
    }

    public void createFoodItem(FoodItem foodItem) {
        System.out.println("Food Item " + foodItem.getName() + " having food item id " + foodItem.getFoodItemId() + " has been created");
    }

    public void assignFoodItemToRestaurant(FoodItem foodItem, Restaurant restaurant) {
        System.out.println("Food Item " + foodItem.getName() + " is assigned to " + restaurant.getName());
    }

    public void getRestaurantsByFoodItem(String foodItemName, List<Restaurant> restaurantList) {
        System.out.println("The restaurants serving " + foodItemName + " are -");
        for (Restaurant restaurant : restaurantList) {
            System.out.println(restaurant.getName());
        }
    }

    public void createOrder(Order order, User user) {
        System.out.println("An order " + order.getOrderId() + " has been created by the user " + user.getName() + " of amount " + order.getOrderAmount() + " and it comprises of ");
    }

    public void getOrdersByUser(User user) {
        System.out.println("The orders created by the user " + user.getName() + " are -");
    }

    public void getOrdersByRestaurant(Restaurant restaurant) {
        System.out.println("The orders received by the restaurant " + restaurant.getName() + " are -");
    }

    public void getAllOpenRestaurants(List<Restaurant> restaurantList) {
        System.out.println("The currently open restaurants are -");
        for (Restaurant restaurant : restaurantList) {
            System.out.println(restaurant.getName() + " " + restaurant.getLocation().getLatitude() + " " + restaurant.getLocation().getLongitude());
        }
    }

    public void paymentSuccessful(Order order, Restaurant restaurant) {
        System.out.println("The payment of " + order.getOrderAmount() + " for order id " + order.getOrderId() +" is successful. Waiting confirmation from the restaurant " + restaurant.getName());
    }

    public void paymentFailed(Order order) {
        System.out.println("The payment of " + order.getOrderAmount() + " for order id " + order.getOrderId() +" has failed. The above order has been cancelled. Please create a new order.");
    }

    public void restaurantAccepted(Order order, Restaurant restaurant) {
        System.out.println("The order " + order.getOrderId() + " has been confirmed by the restaurant " + restaurant.getName());
    }

    public void restaurantRejected(Order order, Restaurant restaurant) {
        System.out.println("The order " + order.getOrderId() + " has been denied by the restaurant " + restaurant.getName());
    }

    public void getOrderByOrderId(Order order, Restaurant restaurant) {
        System.out.println("The order details are - " + order.getOrderId() + " " + order.getOrderAmount() + " " + restaurant.getName() + " " + order.getOrderStatus());
    }

    public void getRestaurantMenu(Restaurant restaurant) {
        System.out.println("The restaurant menu is as follows - ");
    }

    public void assignFoodItemQuantityToRestaurant(int quantity, FoodItem foodItem, Restaurant restaurant) {
        System.out.println("Food Item " + foodItem.getName() + " quantity has been updated to " + quantity + " for the restaurant " + restaurant.getName());
    }
}
