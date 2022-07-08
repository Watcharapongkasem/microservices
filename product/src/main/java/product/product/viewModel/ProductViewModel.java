package product.product.viewModel;

import lombok.Getter;
import lombok.Setter;
import product.product.entity.ProductEntity;

@Getter
@Setter
public class ProductViewModel {

	private String  productId; 
	private String name;
	private Integer price;
	private Integer balance;
    private Integer value;

    public ProductViewModel(){}

    public ProductViewModel(ProductEntity entity){
        this.productId = entity.getProductId();
        this.name = entity.getName();
        this.price = entity.getPrice();
        this.balance = entity.getBalance();
    }

    public ProductEntity toEntity(){
        ProductEntity entity = new ProductEntity();
        entity.setProductId(this.productId);
        entity.setName(this.name);
        entity.setPrice(this.price);
        entity.setBalance(this.balance);
        return entity;
    }

}
