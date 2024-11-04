package br.com.falzoni.falzoni_java_api.controllers.entries;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    @GetMapping
    public String get() {
        return "Controller de cliente";
    }
}
