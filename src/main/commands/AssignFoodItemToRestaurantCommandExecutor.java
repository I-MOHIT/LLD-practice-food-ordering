package main.commands;

import main.OutputPrinter;
import main.exceptions.CommandNotFoundException;
import main.exceptions.InvalidFoodItemException;
import main.exceptions.InvalidRestaurantIdException;
import main.models.Command;
import main.models.FoodItem;
import main.models.Restaurant;
import main.services.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class AssignFoodItemToRestaurantCommandExecutor extends CommandExecutor{
    public final static String COMMAND_NAME = "assign_food_item_to_restaurant";

    public AssignFoodItemToRestaurantCommandExecutor(FoodService foodService, OrderService orderService, PaymentService paymentService, RestaurantService restaurantService, UserService userService, OutputPrinter outputPrinter) {
        super(foodService, orderService, paymentService, restaurantService, userService, outputPrinter);
    }

    @Override
    public boolean validateCommand(Command command) {
        if (!command.getCommandName().equals(COMMAND_NAME) || command.getParams().size() != 2) {
            throw new CommandNotFoundException();
        }
        return true;
    }

    @Override
    public void executeCommand(Command command) {
        UUID foodItemId = UUID.fromString(command.getParams().getFirst());
        UUID restaurantId = UUID.fromString(command.getParams().get(1));
        if (!this.foodService.getUuidFoodItemMap().containsKey(foodItemId)) {
            throw new InvalidFoodItemException();
        }
        if (!this.restaurantService.getUuidRestaurantMap().containsKey(restaurantId)) {
            throw new InvalidRestaurantIdException();
        }
        FoodItem foodItem = this.foodService.getUuidFoodItemMap().get(foodItemId);
        Restaurant restaurant = this.restaurantService.getUuidRestaurantMap().get(restaurantId);
        // Update the list in the restaurant object and the uuidRestaurantHashMap
        List<UUID> foodItemIdList = restaurant.getFoodItemList();
        foodItemIdList.add(foodItemId);
        restaurant.setFoodItemList(foodItemIdList);
        this.restaurantService.getUuidRestaurantMap().put(restaurantId, restaurant);
        // Assign it in the foodItemName - Restaurant Map
        this.foodService.addRestaurantToFoodItemRestaurantsHashMap(foodItem.getName(), restaurant);
        // Assign quantity as zero for the food item quantity for the restaurant
        Map<UUID, Integer> foodItemQuantityMap = this.restaurantService.getRestaurantFoodItemQuantityMap().get(restaurantId);
        foodItemQuantityMap.put(foodItemId, 0);
        this.restaurantService.getRestaurantFoodItemQuantityMap().put(restaurantId, foodItemQuantityMap);
        outputPrinter.assignFoodItemToRestaurant(foodItem, restaurant);
    }
}
