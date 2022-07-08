package order.order.viewmodel;

import lombok.Getter;
import lombok.Setter;
import order.order.entity.OrderEntity;

@Getter
@Setter
public class OrderViewModel {

	private String  orderId; 
	private String  productId; 
	private String name;
	private Integer value;
	private String  userId; 
	private Boolean status;

    public OrderViewModel(){}

    public OrderViewModel(OrderEntity entity){

        this.orderId = entity.getOrederId();
        this.productId = entity.getProductId();
        this.name = entity.getName();
        this.value = entity.getValue();
        this.userId = entity.getUserId();
        this.status = entity.getStatus();

    }

    public OrderEntity toEntity(){

        OrderEntity entity = new OrderEntity();
        entity.setOrederId(this.orderId);
        entity.setProductId(this.productId);
        entity.setName(this.name);
        entity.setValue(this.value);
        entity.setUserId(this.userId);
        entity.setStatus(this.status);
        return entity;
        
    }
}
