package checkout.checkout.viewModel;

import java.util.Date;

import checkout.checkout.entity.CheckoutEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckoutViewModel {
    
    private String checkOutId;
    private String orderId;
    private Date checkOutDate;

    public CheckoutViewModel(){}

    public CheckoutViewModel(CheckoutEntity entity){

        this.checkOutId = entity.getCheckOutId();
        this.orderId  = entity.getOrderId();

    }

    public CheckoutEntity toEntity(){

        CheckoutEntity entity = new CheckoutEntity();
        entity.setCheckOutId(this.checkOutId);
        entity.setOrderId(this.orderId);

        return entity;

    }
}
