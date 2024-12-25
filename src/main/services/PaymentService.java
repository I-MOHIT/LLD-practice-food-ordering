package main.services;

import main.exceptions.InvalidOrderIdException;
import main.exceptions.InvalidOrderStatusException;
import main.models.Order;
import main.models.OrderStatus;

import java.util.UUID;

public class PaymentService {
    OrderService orderService;

    public PaymentService(OrderService orderService) {
        this.orderService = orderService;
    }

    synchronized public void paymentCompleted(UUID orderId) {
        if (!this.orderService.getUuidOrderMap().containsKey(orderId)) {
            throw new InvalidOrderIdException();
        }

        Order order = this.orderService.getUuidOrderMap().get(orderId);
        if (!order.getOrderStatus().equals(OrderStatus.PENDING)) {
            throw new InvalidOrderStatusException();
        }

        order.setOrderStatus(OrderStatus.PLACED);
        this.orderService.getUuidOrderMap().put(orderId, order);
    }

    synchronized public void paymentFailed(UUID orderId) {
        if (!this.orderService.getUuidOrderMap().containsKey(orderId)) {
            throw new InvalidOrderIdException();
        }

        Order order = this.orderService.getUuidOrderMap().get(orderId);
        if (!order.getOrderStatus().equals(OrderStatus.PENDING)) {
            throw new InvalidOrderStatusException();
        }

        order.setOrderStatus(OrderStatus.CANCELLED);
        this.orderService.getUuidOrderMap().put(orderId, order);
    }
}
