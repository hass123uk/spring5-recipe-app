package com.hassanmahmud.hmrecipeproject.services;

import com.hassanmahmud.hmrecipeproject.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getAll();

    Recipe getById(Long id);
}