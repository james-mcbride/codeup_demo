package com.codeup.codeup_demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String hello() {
        return "Posts index page";
    }

    @RequestMapping(path="/posts/create", method=RequestMethod.GET)
    @ResponseBody
    public String create() {
        return "view the form for creating a post";
    }

    @RequestMapping(path="/posts/create", method=RequestMethod.POST)
    @ResponseBody
    public String processCreatePost() {
        return "Create a new post";
    }

    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String viewPOst(@PathVariable int id) {
            return "Viewing post for id "+id;
    }
}