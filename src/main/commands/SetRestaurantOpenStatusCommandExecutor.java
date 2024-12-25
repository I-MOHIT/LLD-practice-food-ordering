package main.commands;

import main.OutputPrinter;
import main.exceptions.CommandNotFoundException;
import main.exceptions.InvalidRestaurantIdException;
import main.models.Command;
import main.models.Location;
import main.models.Restaurant;
import main.services.*;

import java.util.UUID;

public class SetRestaurantOpenStatusCommandExecutor extends CommandExecutor{
    public final static String COMMAND_NAME = "set_restaurant_open_status";

    public SetRestaurantOpenStatusCommandExecutor(FoodService foodService, OrderService orderService, PaymentService paymentService, RestaurantService restaurantService, UserService userService, OutputPrinter outputPrinter) {
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
        UUID restaurantId = UUID.fromString(command.getParams().getFirst());
        if (!this.restaurantService.getUuidRestaurantMap().containsKey(restaurantId)) {
            throw new InvalidRestaurantIdException();
        }
        Restaurant restaurant = this.restaurantService.getUuidRestaurantMap().get(restaurantId);
        boolean isOpen = command.getParams().get(1).equals("open");
        restaurant.setOpen(isOpen);
        this.restaurantService.getUuidRestaurantMap().put(restaurantId, restaurant);
        String status = "open";
        if (!isOpen) {
            status = "closed";
        }
        outputPrinter.setRestaurantOpenStatus(restaurant, status);
    }
}
