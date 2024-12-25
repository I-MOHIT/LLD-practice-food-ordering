package main.commands;

import main.OutputPrinter;
import main.exceptions.CommandNotFoundException;
import main.models.Command;
import main.models.FoodItem;
import main.models.Location;
import main.models.User;
import main.services.*;

public class CreateFoodItemCommandExecutor extends CommandExecutor{
    public final static String COMMAND_NAME = "create_food_item";

    public CreateFoodItemCommandExecutor(FoodService foodService, OrderService orderService, PaymentService paymentService, RestaurantService restaurantService, UserService userService, OutputPrinter outputPrinter) {
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
        FoodItem foodItem = new FoodItem(command.getParams().getFirst(), Double.parseDouble(command.getParams().get(1)));
        foodService.addFoodItemToHashMap(foodItem);
        outputPrinter.createFoodItem(foodItem);
    }
}
