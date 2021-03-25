package com.codeup.codeup_demo.controllers;

import com.codeup.codeup_demo.models.Post;
import com.codeup.codeup_demo.repos.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController {

    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }


    @GetMapping("/posts")
    public String viewPosts(Model model) {
        model.addAttribute("posts", postDao.findAll());

        return "posts/index";
    }

    @PostMapping("/posts")
    public String newPost(@RequestParam(name = "title") String title, @RequestParam(name = "body") String body, @RequestParam(name = "id") String postId, Model model) {
        if (postId.equalsIgnoreCase("0")) {
            Post post = new Post(title,body);
            postDao.save(post);
        } else{
            Post post = new Post(title,body,Long.parseLong(postId));
            postDao.save(post);
        }
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @PostMapping("/posts/show")
    public String showPosts(@RequestParam(name = "postId") String postId,Model model) {
        System.out.println("postId: "+postId);
        Post post = postDao.getOne(Long.parseLong(postId));
        System.out.println(post.getBody());
       model.addAttribute("post", postDao.getOne(Long.parseLong(postId)));
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String create(Model model) {
        model.addAttribute("editPost", false);
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String editPost(@RequestParam(name = "postId") String postId,@RequestParam(name = "action") String action, Model model) {
        if (action.equalsIgnoreCase("edit")) {
            model.addAttribute("editPost", true);
            model.addAttribute("post", postDao.getOne(Long.parseLong(postId)));
            return "posts/create";
        } else{

            postDao.deleteById(Long.parseLong(postId));
            model.addAttribute("posts", postDao.findAll());
            return "posts/index";
        }
    }

//    @RequestMapping(path="/posts/create", method=RequestMethod.POST)
//    @ResponseBody
//    public String processCreatePost() {
//        return "Create a new post";
//    }

    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String viewPOst(@PathVariable int id) {
        return "Viewing post for id "+id;
    }
}