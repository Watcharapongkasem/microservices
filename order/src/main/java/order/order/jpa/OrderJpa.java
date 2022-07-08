package order.order.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import order.order.entity.OrderEntity;

public interface OrderJpa extends JpaRepository<OrderEntity,String> {

    List<OrderEntity> findAllByUserIdAndStatus(String userId,Boolean status);

    Boolean existsByProductIdAndUserIdAndStatus(String productId,String UserId,Boolean status);

    OrderEntity findByProductIdAndUserIdAndStatus(String productId,String UserId,Boolean status);
    
}
