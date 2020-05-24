package com.hassanmahmud.hmrecipeproject.services;

import com.hassanmahmud.hmrecipeproject.domain.Recipe;
import com.hassanmahmud.hmrecipeproject.repositories.RecipeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    @Override
    public Set<Recipe> getAll() {
        log.debug("I'm in the service");
        var recipes = new HashSet<Recipe>();
        recipeRepository.findAll().forEach(recipes::add);
        return recipes;
    }

    @Override
    public Optional<Recipe> getById(Long id) {
        return recipeRepository.findById(id);
    }
}
