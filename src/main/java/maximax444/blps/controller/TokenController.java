package maximax444.blps.controller;

import maximax444.blps.entity.Customer;
import lombok.AllArgsConstructor;
import maximax444.blps.service.CustomerInterface;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;

@RestController
@AllArgsConstructor
public class TokenController {

    private final CustomerInterface customerService;

    @PostMapping("/token")
    public ResponseEntity<?> getToken(@RequestParam("username") final String username, @RequestParam("password") final String password) {
        String token = customerService.login(username, password);

        return ResponseEntity.ok().body(token);
    }

    @PostMapping(value = "/registration")
    public ResponseEntity<?> createNewUser(@RequestParam("username") final String username, @RequestParam("password") final String password) {
        String token = customerService.registration(username, password);
        return ResponseEntity.ok().body(token);
    }

}
