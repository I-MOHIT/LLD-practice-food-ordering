package main;

import main.commands.CommandExecutorFactory;
import main.services.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        OutputPrinter outputPrinter = new OutputPrinter();
        FoodService foodService = new FoodService();
        OrderService orderService = new OrderService(foodService);
        PaymentService paymentService = new PaymentService(orderService);
        RestaurantService restaurantService = new RestaurantService();
        UserService userService = new UserService();
        CommandExecutorFactory commandExecutorFactory = new CommandExecutorFactory(foodService, orderService, paymentService, restaurantService, userService, outputPrinter);

        new InteractiveMode(commandExecutorFactory, outputPrinter).process();
    }
}