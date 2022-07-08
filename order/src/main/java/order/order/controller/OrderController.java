package order.order.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import order.order.services.OrderService;
import order.order.viewmodel.OrderViewModel;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private EurekaClient eurekaClient;

    @GetMapping("/all")
    public ResponseEntity<List<OrderViewModel>> allProduct(@RequestParam("userId") String userId) {

        List<OrderViewModel> result = orderService.allOrder(userId);

        return new ResponseEntity<List<OrderViewModel>>(result, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<OrderViewModel> createOrder(@RequestBody OrderViewModel vm) {

        OrderViewModel result = orderService.newOrder(vm);

        return new ResponseEntity<OrderViewModel>(result, HttpStatus.OK);
    }

    @PutMapping("/checkout")
    public ResponseEntity<Boolean> checkoutOrder(HttpServletRequest request,
            @RequestParam("orderId") String orderId) {

        OrderViewModel result = orderService.updateOrder(orderId);

        InstanceInfo service = eurekaClient
                .getApplication("product")
                .getInstances()
                .get(0);

        String hostName = service.getHostName();
        int port = service.getPort();

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<OrderViewModel> entity = new HttpEntity<>(result);
        URI url = URI.create("http://" + hostName + ":" + port + "/product/updateByOrder");
        ResponseEntity<Boolean> res = restTemplate.exchange(url,
                HttpMethod.PUT, entity, Boolean.class);

        return res;
    }

    @DeleteMapping()
    public ResponseEntity<Boolean> deleteProduct(@RequestParam("orderId") String orderId) {
        Boolean result = orderService.deleteOrder(orderId);
        return new ResponseEntity<Boolean>(result, HttpStatus.OK);
    }
}
