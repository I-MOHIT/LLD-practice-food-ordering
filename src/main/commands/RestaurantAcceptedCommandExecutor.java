package main.commands;

import main.OutputPrinter;
import main.exceptions.CommandNotFoundException;
import main.exceptions.InvalidOrderIdException;
import main.exceptions.InvalidOrderStatusException;
import main.models.Command;
import main.models.Order;
import main.models.OrderStatus;
import main.models.Restaurant;
import main.services.*;

import java.util.UUID;

public class RestaurantAcceptedCommandExecutor extends CommandExecutor{
    public final static String COMMAND_NAME = "restaurant_accepted";

    public RestaurantAcceptedCommandExecutor(FoodService foodService, OrderService orderService, PaymentService paymentService, RestaurantService restaurantService, UserService userService, OutputPrinter outputPrinter) {
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
        Order order = this.orderService.getUuidOrderMap().get(orderId);
        if (!order.getOrderStatus().equals(OrderStatus.PLACED)) {
            throw new InvalidOrderStatusException();
        }
        order.setOrderStatus(OrderStatus.CONFIRMED);
        this.orderService.addOrdersToHashMap(order);
        Restaurant restaurant = this.restaurantService.getUuidRestaurantMap().get(order.getRestaurantId());
        outputPrinter.restaurantAccepted(order, restaurant);
    }
}
