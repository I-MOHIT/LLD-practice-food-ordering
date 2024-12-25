package main.commands;

import main.OutputPrinter;
import main.models.Command;
import main.services.*;

public abstract class CommandExecutor {
    FoodService foodService;
    OrderService orderService;
    PaymentService paymentService;
    RestaurantService restaurantService;
    UserService userService;
    OutputPrinter outputPrinter;

    public CommandExecutor(FoodService foodService, OrderService orderService, PaymentService paymentService, RestaurantService restaurantService, UserService userService, OutputPrinter outputPrinter) {
        this.foodService = foodService;
        this.orderService = orderService;
        this.paymentService = paymentService;
        this.restaurantService = restaurantService;
        this.userService = userService;
        this.outputPrinter = outputPrinter;
    }

    public abstract boolean validateCommand(Command command);
    public abstract void executeCommand(Command command);
}
