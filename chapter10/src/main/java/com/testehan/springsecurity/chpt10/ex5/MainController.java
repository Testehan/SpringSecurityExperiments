package com.testehan.springsecurity.chpt10.ex5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    private Logger logger = LoggerFactory.getLogger(MainController.class.getName());
    @GetMapping("/")
    public String main() {
        return "main.html";
    }

    /*
        We define a controller class having an action for the main page and a REST endpoint.
        Because the class is a normal Spring MVC @Controller class, we also have to add the
        @ResponseBody annotation explicitly to the endpoint
     */
    @PostMapping("/test")
    @ResponseBody
    @CrossOrigin("http://localhost:8080")       // by adding this it Allows the localhost origin for cross-origin requests
    // @CrossOrigin("http://localhost_xxxxxxx:8080")     //   by using this instead of above, it will not work
    public String test() {
        logger.info("Test method called");
        return "HELLO";
    }
}
