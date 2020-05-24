package com.hassanmahmud.hmrecipeproject.controllers;

import com.hassanmahmud.hmrecipeproject.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {

        model.addAttribute("recipes", recipeService.getAll());

        return "index";
    }

    @RequestMapping({"/recipe/{id}"})
    public String getRecipePage(Model model, @PathVariable Long id) {
        model.addAttribute("recipe", recipeService.getById(id));

        return "recipe";
    }

}
