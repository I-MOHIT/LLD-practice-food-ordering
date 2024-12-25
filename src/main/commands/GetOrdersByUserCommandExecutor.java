package main.commands;

import main.OutputPrinter;
import main.exceptions.CommandNotFoundException;
import main.exceptions.InvalidRestaurantIdException;
import main.exceptions.InvalidUserIdException;
import main.models.*;
import main.services.*;

import java.util.List;
import java.util.UUID;

public class GetOrdersByUserCommandExecutor extends CommandExecutor{
    public final static String COMMAND_NAME = "get_orders_by_user";

    public GetOrdersByUserCommandExecutor(FoodService foodService, OrderService orderService, PaymentService paymentService, RestaurantService restaurantService, UserService userService, OutputPrinter outputPrinter) {
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
        UUID userId = UUID.fromString(command.getParams().getFirst());
        if (!this.userService.getUuidUserMap().containsKey(userId)) {
            throw new InvalidUserIdException();
        }
        User user = this.userService.getUuidUserMap().get(userId);
        List<UUID> orderIdList = this.orderService.getUserIdOrdersMap().get(userId);
        outputPrinter.getOrdersByUser(user);
        for (UUID orderId : orderIdList) {
            Order order = this.orderService.getUuidOrderMap().get(orderId);
            Restaurant restaurant = this.restaurantService.getUuidRestaurantMap().get(order.getRestaurantId());
            System.out.println(order.getOrderId() + " " + restaurant.getName() + " " + order.getOrderAmount() + " " + order.getOrderStatus());
        }
    }
}
