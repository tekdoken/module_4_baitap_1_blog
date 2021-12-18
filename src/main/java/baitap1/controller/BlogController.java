package baitap1.controller;

import baitap1.model.Blog;
import baitap1.service.IBlogservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlogController {
    @Autowired
    IBlogservice blogservice;

    @GetMapping("create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("blog", new Blog());
        return modelAndView;
    }

    @PostMapping("create")
    public String saveNew(@ModelAttribute("blog") Blog blog) {
        blogservice.save(blog);
        return "redirect:/";
    }

    @GetMapping("")
    public String list(Model model) {
        Iterable<Blog> blogs = new ArrayList<>();
        blogs = blogservice.findAll();
        model.addAttribute("blogs", blogs);
        return "list";
    }
    @GetMapping("update")
    public ModelAndView updateForm(@PathVariable int id){
        Optional<Blog> blog=blogservice.findById(id);
        ModelAndView modelAndView=new ModelAndView("update");
        modelAndView.addObject("blog",blog.get());
        return modelAndView;
    }
    @PostMapping("/update")
    public String update(Blog blog){
        blogservice.save(blog);
        return "redirect:/";
    }
    @GetMapping("view/{id}")
    public ModelAndView view(@PathVariable int id){
        Optional<Blog> blog=blogservice.findById(id);
        ModelAndView modelAndView=new ModelAndView("view");
        modelAndView.addObject("blog",blog.get());
        return modelAndView;
    }
@GetMapping("delete/{id}")
    public String delete(@PathVariable int id){
        blogservice.remove(id);
        return "redirect:/";
}
}
