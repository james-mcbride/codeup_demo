package com.codeup.codeup_demo.controllers;

import com.codeup.codeup_demo.models.Image;
import com.codeup.codeup_demo.models.Post;
import com.codeup.codeup_demo.models.User;
import com.codeup.codeup_demo.repos.ImageRepository;
import com.codeup.codeup_demo.repos.PostRepository;
import com.codeup.codeup_demo.repos.UserRepository;
import com.codeup.codeup_demo.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;
    private final ImageRepository imageDao;
    private final EmailService emailService;

    public PostController(PostRepository postDao, UserRepository userDao, ImageRepository imageDao, EmailService emailService) {
        this.postDao = postDao;
        this.userDao=userDao;
        this.imageDao=imageDao;
        this.emailService=emailService;
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
//        post.setOwner(userDao.getOne(1L));
        model.addAttribute("post", postDao.getOne(Long.parseLong(postId)));
        return "posts/show";
    }

    @GetMapping("/posts/{postId}/edit")
    public String editPost(@PathVariable String postId ,Model model) {
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
        model.addAttribute("post", new Post());
        Post post = new Post();
        System.out.println(post.getId());
        System.out.println();
        return "posts/create";
    }
    @PostMapping("/posts/{id}/edit")
    public String editPost(@ModelAttribute Post post, @RequestParam(required = false) String image0, @RequestParam(required = false) String imageId0, @RequestParam(required = false) String image1, @RequestParam(required = false) String imageId1,  @RequestParam(required = false) String image2, @RequestParam(required = false) String imageId2, @RequestParam(required = false) String image3,@RequestParam(required = false) String imageId3, @RequestParam(required = false) String image4, @RequestParam(required = false) String imageId4, @RequestParam(required = false) String image5,@RequestParam(required = false) String imageId5, @RequestParam String numEditImages,@PathVariable String id, Model model) {
        post.setId(Long.parseLong(id));
        System.out.println("id: "+post.getId());
        System.out.println("body: "+post.getBody());
        System.out.println("title: "+post.getTitle());

        post.setOwner((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        postDao.save(post);
        int numberOfImages=Integer.parseInt(numEditImages);
        if(numberOfImages>0){
            if (image0!=null) {
                System.out.println(image0);
                if (image0.equalsIgnoreCase("delete")) {
                    imageDao.deleteById(Long.parseLong(imageId0));
                } else if(imageId0.equalsIgnoreCase("new")){
                    imageDao.save(new Image( image0, post));
                }else{
                    imageDao.save(new Image(Long.parseLong(imageId0), image0, post));
                }
            }
        }
        if(numberOfImages>1){
            System.out.println(image1);
            if (image1!=null) {
                if (image1.equalsIgnoreCase("delete")) {
                    imageDao.deleteById(Long.parseLong(imageId1));
                }  else if(imageId1.equalsIgnoreCase("new")){
                    imageDao.save(new Image( image1, post));
                }else{
                    imageDao.save(new Image(Long.parseLong(imageId1), image1, post));
                }
            }
        }
        if(numberOfImages>2){
            System.out.println(image2);
            System.out.println(imageId2);
            if (image2!=null) {
                if (image2.equalsIgnoreCase("delete")) {
                    imageDao.deleteById(Long.parseLong(imageId2));
                }  else if(imageId2.equalsIgnoreCase("new")){
                    imageDao.save(new Image( image2, post));
                }else {
                    System.out.println("image2: " + image2);
                    imageDao.save(new Image(Long.parseLong(imageId2),image2, post));
                }
            }
        }
        if(numberOfImages>3){
            System.out.println(image3);
            if (image3!=null) {
                if (image3.equalsIgnoreCase("delete")) {
                    imageDao.deleteById(Long.parseLong(imageId3));
                }  else if(imageId3.equalsIgnoreCase("new")){
                    imageDao.save(new Image( image3, post));
                }else {
                    imageDao.save(new Image(Long.parseLong(imageId3),image3, post));
                }
            }

        }
        if(numberOfImages>4){
            System.out.println(image4);
            if (image4!=null) {
                if (image4.equalsIgnoreCase("delete")) {
                    imageDao.deleteById(Long.parseLong(imageId4));
                }  else if(imageId0.equalsIgnoreCase("new")){
                    imageDao.save(new Image( image4, post));
                }else {
                    imageDao.save(new Image(Long.parseLong(imageId4),image4, post));
                }
            }

        }
        return "redirect:/posts";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post, @RequestParam(required = false) String image0, @RequestParam(required = false) String image1, @RequestParam(required = false) String image2, @RequestParam(required = false) String image3, @RequestParam(required = false) String image4, @RequestParam(required = false) String image5, @RequestParam String numImages, Model model) {
        int numberOfImages=Integer.parseInt(numImages);
        post.setOwner((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Post newPost = postDao.save(post);
        emailService.prepareAndSend(newPost, "New post created!", "Thanks for your new post!");
        if(numberOfImages>0){
            System.out.println(image0);
            if (image0!=null) {
                imageDao.save(new Image(image0, post));
            }
        }
        if(numberOfImages>1){
            System.out.println(image1);
            if (image1!=null) {
                    imageDao.save(new Image(image1, post));
                }
        }
        if(numberOfImages>2){
            System.out.println(image2);
            if (image2!=null) {
                    imageDao.save(new Image(image2, post));
            }
        }
        if(numberOfImages>3){
            Image newImage= new Image(image3);
            if (image3!=null) {
                imageDao.save(new Image(image3, post));
            }

        }
        if(numberOfImages>4){
            if (image4!=null) {
                    imageDao.save(new Image(image4, post));
                }

        }
//        post.setImages(images);

        model.addAttribute("posts", postDao.findAll());
        return "redirect:/posts";
    }

    @GetMapping("/posts/search")
    public String search(@RequestParam(name = "term") String term, Model model){
       model.addAttribute("posts",postDao.findPostsByTitleContainingOrBodyContaining(term, term));
       return "posts/index";

    }

//    @RequestMapping(path="/posts/create", method=RequestMethod.POST)
//    @ResponseBody
//    public String processCreatePost() {
//        return "Create a new post";
//    }

}