package com.codeup.codeup_demo.controllers;

import com.codeup.codeup_demo.models.Post;
import com.codeup.codeup_demo.repos.PostRepository;
import com.codeup.codeup_demo.repos.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao=userDao;
    }


    @GetMapping("/posts")
    public String viewPosts(Model model) {
        model.addAttribute("posts", postDao.findAll());

        return "posts/index";
    }

//    @PostMapping("/posts")
//    public String newPost(@ModelAttribute Post post,  Model model) {
//
//    }


    @GetMapping("/posts/{postId}")
    public String showPostsId(@PathVariable String postId ,Model model) {
        Post post = postDao.getOne(Long.parseLong(postId));
        model.addAttribute("post", postDao.getOne(Long.parseLong(postId)));
        return "posts/show";
    }

    @GetMapping("/posts/{postId}/edit")
    public String editPost(@PathVariable String postId ,Model model) {
        model.addAttribute("editPost", true);
        model.addAttribute("post", postDao.getOne(Long.parseLong(postId)));
        return "posts/create";
    }

    @PostMapping("/posts/delete")
    public String deletePost(@RequestParam(name = "postId") String postId,Model model) {
        postDao.deleteById(Long.parseLong(postId));
        model.addAttribute("posts", postDao.findAll());
        return "redirect:/posts";
    }

    @GetMapping("/posts/create")
    public String create(Model model) {
        model.addAttribute("editPost", false);
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String editPost(@ModelAttribute Post post, Model model) {
        postDao.save(post);
        model.addAttribute("posts", postDao.findAll());
        return "redirect:/posts";
    }

//    @RequestMapping(path="/posts/create", method=RequestMethod.POST)
//    @ResponseBody
//    public String processCreatePost() {
//        return "Create a new post";
//    }

}