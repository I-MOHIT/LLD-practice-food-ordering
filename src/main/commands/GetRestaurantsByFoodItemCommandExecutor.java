package main.commands;

import main.OutputPrinter;
import main.exceptions.CommandNotFoundException;
import main.exceptions.InvalidFoodItemException;
import main.models.*;
import main.services.*;

import java.util.List;

public class GetRestaurantsByFoodItemCommandExecutor extends CommandExecutor{
    public final static String COMMAND_NAME = "get_restaurants_by_food_item";

    public GetRestaurantsByFoodItemCommandExecutor(FoodService foodService, OrderService orderService, PaymentService paymentService, RestaurantService restaurantService, UserService userService, OutputPrinter outputPrinter) {
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
        String foodItemName = command.getParams().getFirst();
        if (!this.foodService.getFoodItemNameRestaurantMap().containsKey(foodItemName)) {
            throw new InvalidFoodItemException();
        }
        List<Restaurant> restaurantList = this.foodService.getFoodItemNameRestaurantMap().get(foodItemName);
        outputPrinter.getRestaurantsByFoodItem(foodItemName, restaurantList);
    }
}
