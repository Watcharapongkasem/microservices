package product.product.servicesImp;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import product.product.entity.ProductEntity;
import product.product.jpa.ProductJpa;
import product.product.services.ProductService;
import product.product.viewModel.ProductViewModel;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductJpa productJpa;

    @Override
    public List<ProductViewModel> allProduct() {

        List<ProductEntity> productEn = productJpa.findAll();
        List<ProductViewModel> productVmList = new ArrayList<>();

        for (ProductEntity entity : productEn) {
            ProductViewModel productVm = new ProductViewModel(entity);
            productVmList.add(productVm);
        }

        return productVmList;
    }

    @Override
    public ProductViewModel newProduct(ProductViewModel vm) {

        String productId = UUID.randomUUID().toString();
        vm.setProductId(productId);
        ProductEntity entity = vm.toEntity();
        productJpa.save(entity);

        return vm;
    }

    @Override
    public ProductViewModel updateProduct(ProductViewModel vm) {

        productJpa.findById(vm.getProductId())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found"));

        productJpa.save(vm.toEntity());

        return vm;

    }

    @Override
    public Boolean updateProductByOrder(ProductViewModel product) {

        ProductEntity productEn = productJpa.findById(product.getProductId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found"));
        productEn.setBalance(productEn.getBalance() - product.getValue());
        productJpa.save(productEn);

        return true;
    }

    @Override
    public Boolean deleteProduct(String productId) {

        if (productJpa.existsById(productId)) {
            productJpa.deleteById(productId);
            return true;
        } else {
            return false;
        }

    }

}
