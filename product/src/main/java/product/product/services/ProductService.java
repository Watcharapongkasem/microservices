package product.product.services;

import java.util.List;

import org.springframework.stereotype.Service;

import product.product.viewModel.ProductViewModel;

@Service
public interface ProductService {

    List<ProductViewModel>  allProduct();

    ProductViewModel newProduct(ProductViewModel vm);

    ProductViewModel updateProduct(ProductViewModel vm);

    Boolean updateProductByOrder(ProductViewModel vm);

    Boolean deleteProduct(String productId);
}
