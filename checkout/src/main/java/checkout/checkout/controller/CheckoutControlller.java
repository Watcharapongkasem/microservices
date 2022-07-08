package checkout.checkout.controller;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import checkout.checkout.services.CheckoutService;
import checkout.checkout.viewModel.CheckoutViewModel;

@RestController
@RequestMapping("/checkout")
public class CheckoutControlller {

    @Autowired
    private CheckoutService checkoutService;

    @Autowired
    private EurekaClient eurekaClient;

    @PostMapping("/new")
    private ResponseEntity<Boolean> newCheckOut(HttpServletRequest request, @RequestBody CheckoutViewModel model) {

        InstanceInfo service = eurekaClient
                .getApplication("order")
                .getInstances()
                .get(0);

        String hostName = service.getHostName();
        int port = service.getPort();

        RestTemplate restTemplate = new RestTemplate();
        URI url = URI.create(
                "http://" + hostName + ":" + port + "/order/checkout" + "?orderId=" + model.getOrderId());
        ResponseEntity<Boolean> res = restTemplate.exchange(url,
                HttpMethod.PUT, null, Boolean.class);

        Boolean result;
        if (res.getStatusCodeValue() == 200) {
            result = checkoutService.newCheckout(model);
        } else {
            throw new ResponseStatusException(HttpStatus.valueOf(500));
        }

        return new ResponseEntity<Boolean>(result, HttpStatus.OK);
    }
}
