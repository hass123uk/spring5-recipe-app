package com.hassanmahmud.hmrecipeproject.repositories;

import com.hassanmahmud.hmrecipeproject.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
