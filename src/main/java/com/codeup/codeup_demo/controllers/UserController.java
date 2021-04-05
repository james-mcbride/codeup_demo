package com.codeup.codeup_demo.controllers;

import com.codeup.codeup_demo.models.Category;
import com.codeup.codeup_demo.models.Post;
import com.codeup.codeup_demo.models.User;
import com.codeup.codeup_demo.repos.CategoryRepository;
import com.codeup.codeup_demo.repos.ImageRepository;
import com.codeup.codeup_demo.repos.PostRepository;
import com.codeup.codeup_demo.repos.UserRepository;
import com.codeup.codeup_demo.services.EmailService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private final PostRepository postDao;
    private final UserRepository users;
    private final ImageRepository imageDao;
    private final CategoryRepository categoryDao;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public UserController(PostRepository postDao, UserRepository userDao, ImageRepository imageDao, EmailService emailService, CategoryRepository categoryDao, PasswordEncoder passwordEncoder) {
        this.postDao = postDao;
        this.users=userDao;
        this.imageDao=imageDao;
        this.emailService=emailService;
        this.categoryDao=categoryDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        users.save(user);
        return "redirect:/login";
    }

    @GetMapping("/profile/{ownerId}")
    public String viewProfile(Model model, @PathVariable String ownerId) {
        List<Post> posts= postDao.findPostsByOwnerId(Long.parseLong(ownerId));
        model.addAttribute("posts", posts);
        List<Category> postCategories = new ArrayList<>();
        for (int i=0; i<posts.size(); i++){
            for (int j=0; j<posts.get(i).getCategories().size(); j++){
                Category category = posts.get(i).getCategories().get(j);
                if (!postCategories.contains(category)){
                    postCategories.add(category);
                }
            }
        }
        model.addAttribute("owner", users.getOne(Long.parseLong(ownerId)));
        model.addAttribute("categories", postCategories);


        return "users/profile";
    }

    @GetMapping("/profile/{ownerId}/category/{categoryId}")
    public String showCategory(@PathVariable String categoryId, @PathVariable String ownerId, Model model) {
        Category searchedCategory = categoryDao.getOne(Long.parseLong(categoryId));
        List<Post> posts = postDao.findPostsByCategoriesIsContainingAndOwner(searchedCategory, users.getOne(Long.parseLong(ownerId)));
//        post.setOwner(userDao.getOne(1L));
        model.addAttribute("posts", posts);
        model.addAttribute("owner", users.getOne(Long.parseLong(ownerId)));
        model.addAttribute("categories", categoryDao.findAll());
        return "users/profile";
    }
}
