package com.hassanmahmud.hmrecipeproject.controllers;

import com.hassanmahmud.hmrecipeproject.services.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

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

        var recipe = recipeService.getById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found"));

        model.addAttribute("recipe", recipe);

        return "recipe";
    }

}
