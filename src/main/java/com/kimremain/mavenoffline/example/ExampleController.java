package com.kimremain.mavenoffline.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ExampleController {

	@GetMapping("/string")
	public ModelAndView string(ModelMap map) {
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("string");
    	modelAndView.addObject("userName", "ityouknow");
        return modelAndView;
    }

	@GetMapping("/if")
	public ModelAndView ifunless(ModelMap map) {
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("if");
    	modelAndView.addObject("flag", "yes");
        return modelAndView;
    }

	@GetMapping("/list")
	public ModelAndView list(ModelMap map) {
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("list");
    	modelAndView.addObject("users", getUserList());
        return modelAndView;
		
    }

	@GetMapping("/url")
	public ModelAndView url(ModelMap map) {
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("url");
    	modelAndView.addObject("type", "link");
    	modelAndView.addObject("pageId", "springcloud/2017/09/11/");
    	modelAndView.addObject("img", "http://www.ityouknow.com/assets/images/neo.jpg");
        return modelAndView;
		
    }

	@GetMapping("/eq")
	public ModelAndView eq(ModelMap map) {
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("eq");
        modelAndView.addObject("name", "neo");
        modelAndView.addObject("age", 31);
        modelAndView.addObject("flag", "yes");
        return modelAndView;
 
    }

	@GetMapping("/switch")
	public ModelAndView switchcase(ModelMap map) {
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("switch");
    	modelAndView.addObject("sex", "woman");
        return modelAndView;

    }

    private List<User> getUserList(){
    	
        List<User> list=new ArrayList<User>();
        User user1=new User("Tom",12,"123456");
        User user2=new User("Ann",6,"123563");
        User user3=new User("Smith",66,"666666");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        return  list;
    }

}