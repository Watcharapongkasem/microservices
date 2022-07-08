package product.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import product.product.services.ProductService;
import product.product.viewModel.ProductViewModel;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService proProductService;
    
    @GetMapping("/all")
    public ResponseEntity<List<ProductViewModel>> allProduct(){        
        List<ProductViewModel> result = proProductService.allProduct();
        return new ResponseEntity<List<ProductViewModel>>(result, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<ProductViewModel> createProduct(@RequestBody ProductViewModel vm){
        ProductViewModel result =proProductService.newProduct(vm);
        return new ResponseEntity<ProductViewModel>(result, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ProductViewModel> updateProduct(@RequestBody ProductViewModel vm){
        ProductViewModel result =proProductService.updateProduct(vm);
        return new ResponseEntity<ProductViewModel>(result, HttpStatus.OK);
    }

    @PutMapping("/updateByOrder")
    public ResponseEntity<Boolean> updateProductByOrder(@RequestBody ProductViewModel vm){
        Boolean result =proProductService.updateProductByOrder(vm);
        return new ResponseEntity<Boolean>(result, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<Boolean> deleteProduct(@RequestParam ("productId")String productId){
        Boolean result =proProductService.deleteProduct(productId);
        return new ResponseEntity<Boolean>(result, HttpStatus.OK);
    }
}
