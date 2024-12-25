package main.commands;

import main.OutputPrinter;
import main.exceptions.CommandNotFoundException;
import main.exceptions.InvalidOrderIdException;
import main.exceptions.InvalidUserIdException;
import main.models.Command;
import main.models.Order;
import main.models.Restaurant;
import main.models.User;
import main.services.*;

import java.util.List;
import java.util.UUID;

public class GetOrderByOrderIdCommandExecutor extends CommandExecutor{
    public final static String COMMAND_NAME = "get_order_by_order_id";

    public GetOrderByOrderIdCommandExecutor(FoodService foodService, OrderService orderService, PaymentService paymentService, RestaurantService restaurantService, UserService userService, OutputPrinter outputPrinter) {
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
        UUID orderId = UUID.fromString(command.getParams().getFirst());
        if (!this.orderService.getUuidOrderMap().containsKey(orderId)) {
            throw new InvalidOrderIdException();
        }
        Order order = this.orderService.getUuidOrderMap().get(orderId);
        Restaurant restaurant = this.restaurantService.getUuidRestaurantMap().get(order.getRestaurantId());
        outputPrinter.getOrderByOrderId(order, restaurant);
    }
}
