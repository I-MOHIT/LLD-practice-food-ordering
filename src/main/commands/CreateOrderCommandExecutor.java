package main.commands;

import main.OutputPrinter;
import main.exceptions.*;
import main.models.*;
import main.services.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CreateOrderCommandExecutor extends CommandExecutor{
    public final static String COMMAND_NAME = "create_order";

    public CreateOrderCommandExecutor(FoodService foodService, OrderService orderService, PaymentService paymentService, RestaurantService restaurantService, UserService userService, OutputPrinter outputPrinter) {
        super(foodService, orderService, paymentService, restaurantService, userService, outputPrinter);
    }

    @Override
    public boolean validateCommand(Command command) {
        if (!command.getCommandName().equals(COMMAND_NAME) || command.getParams().size() < 4) {
            throw new CommandNotFoundException();
        }
        return true;
    }

    @Override
    synchronized public void executeCommand(Command command) {
        UUID userId = UUID.fromString(command.getParams().getFirst());
        if (!this.userService.getUuidUserMap().containsKey(userId)) {
            throw new InvalidUserIdException();
        }
        UUID restaurantId = UUID.fromString(command.getParams().get(1));
        if (!this.restaurantService.getUuidRestaurantMap().containsKey(restaurantId)) {
            throw new InvalidRestaurantIdException();
        }
        Map<UUID, Integer> foodItemQuantityMap = new HashMap<>();
        for (int i = 2; i < command.getParams().size(); i = i+2) {
            UUID foodItemId = UUID.fromString(command.getParams().get(i));
            if (!this.foodService.getUuidFoodItemMap().containsKey(foodItemId)) {
                throw new InvalidFoodItemException();
            }
            int quantity = Integer.parseInt(command.getParams().get(i+1));
            // Ensure if the restaurant has enough quantity of this food item
            if (this.restaurantService.getRestaurantFoodItemQuantityMap().get(restaurantId).get(foodItemId) < quantity) {
                throw new InsufficientFoodItemQuantityException();
            }
            // If it has enough quantity, update the remaining quantity in the inventory
            Map<UUID, Integer> foodItemQuantityRestaurantMap = this.restaurantService.getRestaurantFoodItemQuantityMap().get(restaurantId);
            int currQuantity = foodItemQuantityRestaurantMap.get(foodItemId);
            foodItemQuantityRestaurantMap.put(foodItemId, currQuantity - quantity);
            this.restaurantService.getRestaurantFoodItemQuantityMap().put(restaurantId, foodItemQuantityRestaurantMap);
            // Update the order in the order food item - quantity map
            foodItemQuantityMap.put(foodItemId, quantity);
        }
        User user = this.userService.getUuidUserMap().get(userId);
        Order order = new Order(userId, restaurantId);
        order.setFoodItemQuantityMap(foodItemQuantityMap);
        order.setOrderAmount(this.orderService.calculateOrderAmount(order));
        this.orderService.addOrdersToHashMap(order);
        this.orderService.addOrdersToUserOrdersHashMap(userId, order);
        this.orderService.addOrdersToRestaurantOrdersHashMap(restaurantId, order);
        outputPrinter.createOrder(order, user);
        for (Map.Entry<UUID, Integer> foodItemQuantityEntry : order.getFoodItemQuantityMap().entrySet()) {
            FoodItem foodItem = this.foodService.getUuidFoodItemMap().get(foodItemQuantityEntry.getKey());
            System.out.println(foodItem.getName() + " " + foodItemQuantityEntry.getValue());
        }
    }
}
