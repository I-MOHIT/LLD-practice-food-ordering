package main.commands;

import main.OutputPrinter;
import main.exceptions.CommandNotFoundException;
import main.exceptions.InvalidRestaurantIdException;
import main.exceptions.InvalidUserIdException;
import main.models.Command;
import main.models.Order;
import main.models.Restaurant;
import main.models.User;
import main.services.*;

import java.util.List;
import java.util.UUID;

public class GetOrdersByRestaurantCommandExecutor extends CommandExecutor{
    public final static String COMMAND_NAME = "get_orders_by_restaurant";

    public GetOrdersByRestaurantCommandExecutor(FoodService foodService, OrderService orderService, PaymentService paymentService, RestaurantService restaurantService, UserService userService, OutputPrinter outputPrinter) {
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
        UUID restaurantId = UUID.fromString(command.getParams().getFirst());
        if (!this.restaurantService.getUuidRestaurantMap().containsKey(restaurantId)) {
            throw new InvalidRestaurantIdException();
        }
        Restaurant restaurant = this.restaurantService.getUuidRestaurantMap().get(restaurantId);
        List<UUID> orderIdList = this.orderService.getRestaurantIdOrdersMap().get(restaurantId);
        outputPrinter.getOrdersByRestaurant(restaurant);
        for (UUID orderId : orderIdList) {
            Order order = this.orderService.getUuidOrderMap().get(orderId);
            System.out.println(order.getOrderId() + " " + restaurant.getName() + " " + order.getOrderAmount() + " " + order.getOrderStatus());
        }
    }
}
