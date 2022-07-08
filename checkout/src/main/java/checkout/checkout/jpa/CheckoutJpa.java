package checkout.checkout.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import checkout.checkout.entity.CheckoutEntity;

public interface CheckoutJpa extends JpaRepository<CheckoutEntity,String> {
    
}
