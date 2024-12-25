package main.commands;

import main.OutputPrinter;
import main.exceptions.CommandNotFoundException;
import main.exceptions.InvalidOrderIdException;
import main.models.Command;
import main.models.FoodItem;
import main.models.Order;
import main.services.*;

import java.util.Map;
import java.util.UUID;

public class PaymentFailedCommandExecutor extends CommandExecutor{
    public final static String COMMAND_NAME = "payment_failed";

    public PaymentFailedCommandExecutor(FoodService foodService, OrderService orderService, PaymentService paymentService, RestaurantService restaurantService, UserService userService, OutputPrinter outputPrinter) {
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
        this.paymentService.paymentFailed(orderId);
        // Fetching the order post the payment failed method execution since the order status update in the object and the hash map is taken care by that method
        Order order = this.orderService.getUuidOrderMap().get(orderId);
        // Update the restaurant inventory back from the order inventory
        this.restaurantService.restoreRestaurantInventory(order);
        outputPrinter.paymentFailed(order);
    }
}
