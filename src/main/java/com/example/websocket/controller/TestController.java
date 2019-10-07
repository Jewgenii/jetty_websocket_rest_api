package com.example.websocket.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "com/example/websocket/test")
public class TestController {
    @GetMapping(value = "get")
    public ResponseEntity<String> getAll(){
        return new ResponseEntity<String>("hello world", HttpStatus.OK);
    }
}
