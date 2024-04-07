package com.example.ss_c1_e1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController
{
    @GetMapping("/hello")
    public String hello()
    {
        return "hello";
    }
}
