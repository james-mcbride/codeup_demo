package com.codeup.codeup_demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PostController {

//    @GetMapping("/posts")
//    @ResponseBody
//    public String hello() {
//        return "Posts index page";
//    }
//
//    @RequestMapping(path="/posts/create", method=RequestMethod.GET)
//    @ResponseBody
//    public String create() {
//        return "view the form for creating a post";
//    }
//
//    @RequestMapping(path="/posts/create", method=RequestMethod.POST)
//    @ResponseBody
//    public String processCreatePost() {
//        return "Create a new post";
//    }
//
//    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public String viewPOst(@PathVariable int id) {
//            return "Viewing post for id "+id;
//    }

    @GetMapping("/posts")
    public String viewPosts(Model model) {
        ArrayList<String> posts = new ArrayList<>();
        posts.add("post1");
        posts.add("post2");
        model.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/posts/show")
    public String showPosts(Model model) {
//        model.addAttribute("post", "post1");
        return "posts/show";
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