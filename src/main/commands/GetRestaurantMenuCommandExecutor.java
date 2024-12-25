package main.commands;

import main.OutputPrinter;
import main.exceptions.CommandNotFoundException;
import main.exceptions.InvalidOrderIdException;
import main.exceptions.InvalidRestaurantIdException;
import main.models.Command;
import main.models.FoodItem;
import main.models.Order;
import main.models.Restaurant;
import main.services.*;

import java.util.List;
import java.util.UUID;

public class GetRestaurantMenuCommandExecutor extends CommandExecutor{
    public final static String COMMAND_NAME = "get_restaurant_menu";

    public GetRestaurantMenuCommandExecutor(FoodService foodService, OrderService orderService, PaymentService paymentService, RestaurantService restaurantService, UserService userService, OutputPrinter outputPrinter) {
        super(foodService, orderService, paymentService, restaurantService, userService, outputPrinter);
    }

    @Override
    public boolean validateCommand(Command command) {
        if (!command.getCommandName().equals(COMMAND_NAME) || command.getParams().size() != 1) {
            throw new CommandNotFoundException();
        }
        return true;
    }

    @Override
    public void executeCommand(Command command) {
        UUID restaurantId = UUID.fromString(command.getParams().getFirst());
        if (!this.restaurantService.getUuidRestaurantMap().containsKey(restaurantId)) {
            throw new InvalidRestaurantIdException();
        }
        Restaurant restaurant = this.restaurantService.getUuidRestaurantMap().get(restaurantId);
        outputPrinter.getRestaurantMenu(restaurant);
        List<UUID> foodItemIdList = restaurant.getFoodItemList();
        for (UUID foodItemId : foodItemIdList) {
            FoodItem foodItem = this.foodService.getUuidFoodItemMap().get(foodItemId);
            boolean isAvailable = this.restaurantService.getRestaurantFoodItemQuantityMap().get(restaurantId).get(foodItemId) > 0;
            System.out.println(foodItem.getName() + " " + foodItem.getPrice() + (!isAvailable ? " Unavailable" : ""));
        }
    }
}
