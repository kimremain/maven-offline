package com.kimremain.mavenoffline.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HelloController {

	@GetMapping("/")
    public ModelAndView index() {
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("hello");
    	modelAndView.addObject("message", "hello rnb");
        return modelAndView;
    }

}