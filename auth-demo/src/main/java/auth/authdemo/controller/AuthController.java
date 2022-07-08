package auth.authdemo.controller;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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

import auth.authdemo.jwt.JwtUtil;
import auth.authdemo.service.Userservice;
import auth.authdemo.vm.Userviewmodel;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    // @Autowired
    // private EurekaClient eurekaClient;

    @Autowired
    private Userservice userservice;

    @PostMapping("/login")
    public ResponseEntity<Userviewmodel> login(@RequestBody Userviewmodel model) {
        var checkPassword = userservice.verifyPassword(model);
        String token ;
        Userviewmodel result  = new Userviewmodel();
        if(checkPassword){
            token = jwtUtil.generateToken(model.getUserName());
            result = userservice.getUserId(model,token);
        }else{
             throw new ResponseStatusException(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }       

        return new ResponseEntity<Userviewmodel>(result, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> register(HttpServletRequest request, @RequestBody Userviewmodel model) {

        // Persist user to some persistent storage
        // InstanceInfo service = eurekaClient
        // .getApplication("user")
        // .getInstances()
        // .get(0);

        // String hostName = service.getHostName();
        // int port = service.getPort();

        // String jwt = request.getHeader("Authorization");
        // RestTemplate restTemplate = new RestTemplate();
        // HttpHeaders headers = new HttpHeaders();
        // headers.add("Authorization", jwt);
        // HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        // URI url = URI.create("http://" + hostName + ":" + port + "/api/user");
        // ResponseEntity<String> res = restTemplate.exchange(url,
        // HttpMethod.GET,entity,String.class);

        var result = userservice.registerUser(model);

        return new ResponseEntity<Boolean>(result, HttpStatus.OK);
    }

}
