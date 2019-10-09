package com.example.websocket.springWebApplication.controller;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.text.View;

@Controller
/*@RequestMapping(value = "webapp/home")*/
@RequestMapping(value = "/")
public class TestController {

    @GetMapping(value = "echo")
    public ResponseEntity<String> echo(@Param(value = "param") String param){

        return new ResponseEntity<String>(param, HttpStatus.OK);
    }

    @GetMapping(value = "home")
    public ModelAndView home(@Param(value = "param") String param){

        ModelAndView m =  new ModelAndView();
        m.setViewName("index.html");
        return m;
    }

    @GetMapping(value = "upload")
    public ModelAndView upload(@Param(value = "param") String param){

        ModelAndView m =  new ModelAndView();
        m.setViewName("websocketfileupload/websocketfileupload.html");
        return m;
    }

}
