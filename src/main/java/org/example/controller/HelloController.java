package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lol")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello"; // Это предполагает, что у вас есть файл hello.jsp (или hello.html) в папке resources/templates
    }

}
