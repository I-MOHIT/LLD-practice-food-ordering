package main.commands;

import main.OutputPrinter;
import main.exceptions.CommandNotFoundException;
import main.models.Command;
import main.models.Location;
import main.models.Restaurant;
import main.models.User;
import main.services.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class GetAllOpenRestaurantsCommandExecutor extends CommandExecutor{
    public final static String COMMAND_NAME = "get_all_open_restaurants";

    public GetAllOpenRestaurantsCommandExecutor(FoodService foodService, OrderService orderService, PaymentService paymentService, RestaurantService restaurantService, UserService userService, OutputPrinter outputPrinter) {
        super(foodService, orderService, paymentService, restaurantService, userService, outputPrinter);
    }

    @Override
    public boolean validateCommand(Command command) {
        if (!command.getCommandName().equals(COMMAND_NAME) || !command.getParams().isEmpty()) {
            throw new CommandNotFoundException();
        }
        return true;
    }

    @Override
    public void executeCommand(Command command) {
        List<Restaurant> restaurantList = new ArrayList<>();
        for (Map.Entry<UUID, Restaurant> uuidRestaurantMapEntry : this.restaurantService.getUuidRestaurantMap().entrySet()) {
            if (uuidRestaurantMapEntry.getValue().isOpen()) {
                restaurantList.add(uuidRestaurantMapEntry.getValue());
            }
        }
        outputPrinter.getAllOpenRestaurants(restaurantList);
    }
}
