package com.hassanmahmud.hmrecipeproject.controllers;

import com.hassanmahmud.hmrecipeproject.domain.Recipe;
import com.hassanmahmud.hmrecipeproject.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IndexControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    IndexController indexController;

    @BeforeEach
    void setUp() {
        indexController = new IndexController(recipeService);
    }

    @Test
    void getIndexPage_whenCalled_returnRecipes() {
        //Given
        var recipes = Set.of(
                Recipe.builder().id(1L).description("Chicken").build(),
                Recipe.builder().id(2L).description("Beef").build()
        );

        when(recipeService.getAll()).thenReturn(recipes);
        var argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //When
        var result = indexController.getIndexPage(model);

        //Then
        verify(recipeService, times(1)).getAll();
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        assertEquals(recipes.size(), argumentCaptor.getValue().size());
        assertEquals("index", result);
    }
}