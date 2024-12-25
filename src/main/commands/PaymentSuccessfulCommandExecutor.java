package main.commands;

import main.OutputPrinter;
import main.exceptions.CommandNotFoundException;
import main.exceptions.InvalidOrderIdException;
import main.models.*;
import main.services.*;

import java.util.UUID;

public class PaymentSuccessfulCommandExecutor extends CommandExecutor{
    public final static String COMMAND_NAME = "payment_successful";

    public PaymentSuccessfulCommandExecutor(FoodService foodService, OrderService orderService, PaymentService paymentService, RestaurantService restaurantService, UserService userService, OutputPrinter outputPrinter) {
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
    synchronized public void executeCommand(Command command) {
        UUID orderId = UUID.fromString(command.getParams().getFirst());
        if (!this.orderService.getUuidOrderMap().containsKey(orderId)) {
            throw new InvalidOrderIdException();
        }
        this.paymentService.paymentCompleted(orderId);
        // Fetching the order post the payment completion method execution since the order status update in the object and the hash map is taken care by that method
        Order order = this.orderService.getUuidOrderMap().get(orderId);
        Restaurant restaurant = this.restaurantService.getUuidRestaurantMap().get(order.getRestaurantId());
        outputPrinter.paymentSuccessful(order, restaurant);
    }
}
