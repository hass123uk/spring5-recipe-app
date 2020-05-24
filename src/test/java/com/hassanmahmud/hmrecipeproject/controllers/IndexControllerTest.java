package com.hassanmahmud.hmrecipeproject.controllers;

import com.hassanmahmud.hmrecipeproject.domain.Recipe;
import com.hassanmahmud.hmrecipeproject.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
        verify(model, times(1))
                .addAttribute(eq("recipes"), argumentCaptor.capture());
        assertEquals(recipes.size(), argumentCaptor.getValue().size());
        assertEquals("index", result);
    }

    @Test
    void getIndexPage_mockMvc_getIndexOnForwardSlash() throws Exception {
        var indexMockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        indexMockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void getRecipePage_validId_returnRecipe() throws Exception {
        var recipe = Optional.of(Recipe.builder().id(1L).build());

        when(recipeService.getById(1L)).thenReturn(recipe);

        var indexMockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        indexMockMvc.perform(get("/recipe/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe"))
                .andExpect(model().attribute("recipe", recipe.get()));
    }


    @Test
    void getRecipePage_invalidId_return404() throws Exception {
        when(recipeService.getById(1L)).thenReturn(Optional.empty());

        var indexMockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        indexMockMvc.perform(get("/recipe/1"))
                .andExpect(status().isNotFound());
    }
}