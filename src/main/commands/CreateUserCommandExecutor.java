package main.commands;

import main.OutputPrinter;
import main.exceptions.CommandNotFoundException;
import main.models.Command;
import main.models.Location;
import main.models.User;
import main.services.*;

import java.util.ArrayList;
import java.util.Random;

public class CreateUserCommandExecutor extends CommandExecutor{
    public final static String COMMAND_NAME = "create_user";

    public CreateUserCommandExecutor(FoodService foodService, OrderService orderService, PaymentService paymentService, RestaurantService restaurantService, UserService userService, OutputPrinter outputPrinter) {
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
        Random random = new Random();
        double latitude = random.nextDouble(-90, 90);
        double longitude = random.nextDouble(-180, 180);
        Location location = new Location(latitude, longitude);
        User user = new User(command.getParams().getFirst(), location);
        userService.addUsersToHashMap(user);
        // Creating an empty list so that if the orders for a user are queried before a single order is placed by the user, a null pointer exception is not encountered
        this.orderService.getUserIdOrdersMap().put(user.getUserId(), new ArrayList<>());
        outputPrinter.createUser(user);
    }
}
