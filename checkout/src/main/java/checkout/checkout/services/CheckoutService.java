package checkout.checkout.services;

import org.springframework.stereotype.Service;

import checkout.checkout.viewModel.CheckoutViewModel;

@Service
public interface CheckoutService {
    
    Boolean newCheckout (CheckoutViewModel model);
}
