package order.order.serviceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import order.order.entity.OrderEntity;
import order.order.jpa.OrderJpa;
import order.order.services.OrderService;
import order.order.viewmodel.OrderViewModel;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderJpa orderJpa;

    @Override
    public List<OrderViewModel> allOrder(String userId) {

        List<OrderEntity> orderEnList = orderJpa.findAllByUserIdAndStatus(userId,true);
        List<OrderViewModel> orderVmList = new ArrayList<>();
        for (OrderEntity orderEn : orderEnList) {
            OrderViewModel orderVm = new OrderViewModel(orderEn);
            orderVmList.add(orderVm);
        }

        return orderVmList;
    }

    @Override
    public OrderViewModel newOrder(OrderViewModel model) {

        if (orderJpa.existsByProductIdAndUserIdAndStatus(model.getProductId(), model.getUserId(),true)) {

            var orderEn = orderJpa.findByProductIdAndUserIdAndStatus(model.getProductId(), model.getUserId(),true);
            orderEn.setValue(orderEn.getValue() + 1);
            orderJpa.save(orderEn);
            return new OrderViewModel(orderEn);
        } else {

            String orderId = UUID.randomUUID().toString();
            model.setOrderId(orderId);
            model.setStatus(true);
            model.setValue(1);
            orderJpa.save(model.toEntity());

            return model;
        }

    }

    @Override
    public Boolean deleteOrder(String orderId) {

        if (orderJpa.existsById(orderId)) {
            orderJpa.deleteById(orderId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public OrderViewModel updateOrder(String orderId) {

        if (orderJpa.existsById(orderId)) {
            OrderEntity orderEntity = orderJpa.findById(orderId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            orderEntity.setStatus(false);
            orderJpa.save(orderEntity);
            return new OrderViewModel(orderEntity);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }
}
