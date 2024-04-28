package com.example.springboot.modules.wtf;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wtf")
public class WtfController {

    @GetMapping("/get")
    public String getWtf() {
        return "wtf!";
    }
}
