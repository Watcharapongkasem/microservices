package order.order.services;

import java.util.List;

import org.springframework.stereotype.Service;

import order.order.viewmodel.OrderViewModel;

@Service
public interface OrderService {
    
    List<OrderViewModel> allOrder(String userId);

    OrderViewModel newOrder(OrderViewModel model);

    OrderViewModel updateOrder(String orderId);

    Boolean deleteOrder(String orderId);
}
