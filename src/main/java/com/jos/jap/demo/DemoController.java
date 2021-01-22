package com.jos.jap.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping("/123/hello")
    public String hello() {
        return "haha";
    }
}
