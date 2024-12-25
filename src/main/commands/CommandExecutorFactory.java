package main.commands;

import main.OutputPrinter;
import main.exceptions.CommandNotFoundException;
import main.models.Command;
import main.services.*;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutorFactory {
    private FoodService foodService;
    private OrderService orderService;
    private PaymentService paymentService;
    private RestaurantService restaurantService;
    private UserService userService;
    private OutputPrinter outputPrinter;
    private Map<String, CommandExecutor> commandExecutorMap;

    public CommandExecutorFactory(FoodService foodService, OrderService orderService, PaymentService paymentService, RestaurantService restaurantService, UserService userService, OutputPrinter outputPrinter) {
        this.foodService = foodService;
        this.orderService = orderService;
        this.paymentService = paymentService;
        this.restaurantService = restaurantService;
        this.userService = userService;
        this.outputPrinter = outputPrinter;
        this.commandExecutorMap = new HashMap<>();
        this.commandExecutorMap.put(ExitCommandExecutor.COMMAND_NAME, new ExitCommandExecutor(foodService, orderService, paymentService, restaurantService, userService, outputPrinter));
        this.commandExecutorMap.put(AssignFoodItemToRestaurantCommandExecutor.COMMAND_NAME, new AssignFoodItemToRestaurantCommandExecutor(foodService, orderService, paymentService, restaurantService, userService, outputPrinter));
        this.commandExecutorMap.put(CreateFoodItemCommandExecutor.COMMAND_NAME, new CreateFoodItemCommandExecutor(foodService, orderService, paymentService, restaurantService, userService, outputPrinter));
        this.commandExecutorMap.put(CreateOrderCommandExecutor.COMMAND_NAME, new CreateOrderCommandExecutor(foodService, orderService, paymentService, restaurantService, userService, outputPrinter));
        this.commandExecutorMap.put(CreateRestaurantCommandExecutor.COMMAND_NAME, new CreateRestaurantCommandExecutor(foodService, orderService, paymentService, restaurantService, userService, outputPrinter));
        this.commandExecutorMap.put(CreateUserCommandExecutor.COMMAND_NAME, new CreateUserCommandExecutor(foodService, orderService, paymentService, restaurantService, userService, outputPrinter));
        this.commandExecutorMap.put(GetAllOpenRestaurantsCommandExecutor.COMMAND_NAME, new GetAllOpenRestaurantsCommandExecutor(foodService, orderService, paymentService, restaurantService, userService, outputPrinter));
        this.commandExecutorMap.put(GetOrdersByUserCommandExecutor.COMMAND_NAME, new GetOrdersByUserCommandExecutor(foodService, orderService, paymentService, restaurantService, userService, outputPrinter));
        this.commandExecutorMap.put(GetRestaurantsByFoodItemCommandExecutor.COMMAND_NAME, new GetRestaurantsByFoodItemCommandExecutor(foodService, orderService, paymentService, restaurantService, userService, outputPrinter));
        this.commandExecutorMap.put(PaymentFailedCommandExecutor.COMMAND_NAME, new PaymentFailedCommandExecutor(foodService, orderService, paymentService, restaurantService, userService, outputPrinter));
        this.commandExecutorMap.put(PaymentSuccessfulCommandExecutor.COMMAND_NAME, new PaymentSuccessfulCommandExecutor(foodService, orderService, paymentService, restaurantService, userService, outputPrinter));
        this.commandExecutorMap.put(RestaurantAcceptedCommandExecutor.COMMAND_NAME, new RestaurantAcceptedCommandExecutor(foodService, orderService, paymentService, restaurantService, userService, outputPrinter));
        this.commandExecutorMap.put(RestaurantRejectedCommandExecutor.COMMAND_NAME, new RestaurantRejectedCommandExecutor(foodService, orderService, paymentService, restaurantService, userService, outputPrinter));
        this.commandExecutorMap.put(SetRestaurantOpenStatusCommandExecutor.COMMAND_NAME, new SetRestaurantOpenStatusCommandExecutor(foodService, orderService, paymentService, restaurantService, userService, outputPrinter));
        this.commandExecutorMap.put(GetOrderByOrderIdCommandExecutor.COMMAND_NAME, new GetOrderByOrderIdCommandExecutor(foodService, orderService, paymentService, restaurantService, userService, outputPrinter));
        this.commandExecutorMap.put(GetRestaurantMenuCommandExecutor.COMMAND_NAME, new GetRestaurantMenuCommandExecutor(foodService, orderService, paymentService, restaurantService, userService, outputPrinter));
        this.commandExecutorMap.put(AssignFoodItemQuantityToRestaurantCommandExecutor.COMMAND_NAME, new AssignFoodItemQuantityToRestaurantCommandExecutor(foodService, orderService, paymentService, restaurantService, userService, outputPrinter));
        this.commandExecutorMap.put(GetOrdersByRestaurantCommandExecutor.COMMAND_NAME, new GetOrdersByRestaurantCommandExecutor(foodService, orderService, paymentService, restaurantService, userService, outputPrinter));
    }

    public CommandExecutor getCommandExecutor(Command command) {
        if (!this.commandExecutorMap.containsKey(command.getCommandName())) {
            throw new CommandNotFoundException();
        }
        return this.commandExecutorMap.get(command.getCommandName());
    }

    public FoodService getFoodService() {
        return foodService;
    }

    public void setFoodService(FoodService foodService) {
        this.foodService = foodService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public PaymentService getPaymentService() {
        return paymentService;
    }

    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public RestaurantService getRestaurantService() {
        return restaurantService;
    }

    public void setRestaurantService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public OutputPrinter getOutputPrinter() {
        return outputPrinter;
    }

    public void setOutputPrinter(OutputPrinter outputPrinter) {
        this.outputPrinter = outputPrinter;
    }

    public Map<String, CommandExecutor> getCommandExecutorMap() {
        return commandExecutorMap;
    }

    public void setCommandExecutorMap(Map<String, CommandExecutor> commandExecutorMap) {
        this.commandExecutorMap = commandExecutorMap;
    }
}
