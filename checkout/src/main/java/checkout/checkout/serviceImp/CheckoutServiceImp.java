package checkout.checkout.serviceImp;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import checkout.checkout.jpa.CheckoutJpa;
import checkout.checkout.services.CheckoutService;
import checkout.checkout.viewModel.CheckoutViewModel;

@Service
public class CheckoutServiceImp implements CheckoutService{
    
    @Autowired
    private CheckoutJpa checkoutJpa;

    @Override
    public Boolean newCheckout (CheckoutViewModel model){
        model.setCheckOutId(UUID.randomUUID().toString());
        checkoutJpa.save(model.toEntity());
        return true;
    }
}
