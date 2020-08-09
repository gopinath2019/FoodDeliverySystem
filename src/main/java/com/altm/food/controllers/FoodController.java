package com.altm.food.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.altm.food.entities.FilterData;
import com.altm.food.repositories.ProductRepository;


@Controller
public class FoodController {
    
    private final ProductRepository prodRepository;

    @Autowired
    public FoodController(ProductRepository prodRepository) {
        this.prodRepository = prodRepository;
    }
    
    @GetMapping("/")
    public String homePage(FilterData fd, BindingResult result, Model model) {
    	model.addAttribute("categoryList", prodRepository.findDistinctCategory());
    	model.addAttribute("productList", prodRepository.findAll());
    	model.addAttribute("fd", fd);
        return "index";
    }
    
    @GetMapping("/fetchByCategory/{category}")
    public String fetchByCategory(@PathVariable("category") String category, FilterData fd,Model model) {
    	model.addAttribute("categoryList", prodRepository.findDistinctCategory());
    	model.addAttribute("productList", prodRepository.findByCategory(category));
    	model.addAttribute("fd", fd);
        return "index";
    }
    
    @PostMapping("/filterItem")
    public String filterItem(FilterData fd,Model model) {
    	model.addAttribute("categoryList", prodRepository.findDistinctCategory());
    	int rat = 0;
    	double prc = 0.0;
    	if(fd.getsRating() != null && fd.getsRating() != "") {
    		rat = Integer.parseInt(fd.getsRating());
    	}
    	if(fd.getiPrice() != null && fd.getiPrice() != "") {
    		prc = Integer.parseInt(fd.getiPrice());
    	}
    	if(rat> 0 && prc > 0)
    		model.addAttribute("productList", prodRepository.findByRatingAndPrice(rat, prc));
    	else if(rat> 0)
    		model.addAttribute("productList", prodRepository.findByRating(rat));
    	else
    		model.addAttribute("productList", prodRepository.findByPrice(prc));
    	model.addAttribute("fd", new FilterData());
        return "index";
    }
    
    @GetMapping("/order/{id}")
    public String order(@PathVariable("id") long id, FilterData fd,Model model) {
    	model.addAttribute("product", prodRepository.findById(id));
        return "order";
    }
   
}