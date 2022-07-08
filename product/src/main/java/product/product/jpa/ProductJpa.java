package product.product.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import product.product.entity.ProductEntity;

public interface ProductJpa extends JpaRepository<ProductEntity,String>{
    
}
