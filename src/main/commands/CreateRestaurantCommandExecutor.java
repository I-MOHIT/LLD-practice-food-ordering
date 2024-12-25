package main.commands;

import main.OutputPrinter;
import main.exceptions.CommandNotFoundException;
import main.models.Command;
import main.models.Location;
import main.models.Restaurant;
import main.models.User;
import main.services.*;

import java.util.ArrayList;
import java.util.Random;

public class CreateRestaurantCommandExecutor extends CommandExecutor{
    public final static String COMMAND_NAME = "create_restaurant";

    public CreateRestaurantCommandExecutor(FoodService foodService, OrderService orderService, PaymentService paymentService, RestaurantService restaurantService, UserService userService, OutputPrinter outputPrinter) {
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
        Random random = new Random();
        double latitude = random.nextDouble(-90, 90);
        double longitude = random.nextDouble(-180, 180);
        Location location = new Location(latitude, longitude);
        Restaurant restaurant = new Restaurant(command.getParams().getFirst(), location, Boolean.parseBoolean(command.getParams().get(1)));
        restaurantService.addRestaurantsToHashMap(restaurant);
        // Creating an empty list so that if the orders for a restaurant are queried before a single order is placed to the restaurant, a null pointer exception is not encountered
        this.orderService.getRestaurantIdOrdersMap().put(restaurant.getRestaurantId(), new ArrayList<>());
        outputPrinter.createRestaurant(restaurant);
    }
}
