package com.hassanmahmud.hmrecipeproject.bootstrap;

import com.hassanmahmud.hmrecipeproject.domain.*;
import com.hassanmahmud.hmrecipeproject.repositories.CategoryRepository;
import com.hassanmahmud.hmrecipeproject.repositories.RecipeRepository;
import com.hassanmahmud.hmrecipeproject.repositories.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeRepository recipeRepository;

    public DataLoader(CategoryRepository categoryRepository,
                      UnitOfMeasureRepository unitOfMeasureRepository,
                      RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        var teaSpoonUom = unitOfMeasureRepository.findByDescription("Teaspoon").get();
        var tableSpoonUom = unitOfMeasureRepository.findByDescription("Tablespoon").get();
        var piecesUom = unitOfMeasureRepository.findByDescription("Pieces").get();
        var dashUom = unitOfMeasureRepository.findByDescription("Dash").get();
        var pintUom = unitOfMeasureRepository.findByDescription("Pint").get();

        var guacamoleRecipe = new Recipe();
        guacamoleRecipe.setDescription("Perfect Guacamole Recipe");
        guacamoleRecipe.setPrepTime(10);
        guacamoleRecipe.setCookTime(0);
        guacamoleRecipe.setSource("www.simplyrecipes.com");
        guacamoleRecipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamoleRecipe.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score " +
                "the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. " +
                "(See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a" +
                " little chunky.) " +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the " +
                "lime juice will provide some balance to the richness of the avocado and will help delay the avocados " +
                "from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their " +
                "hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of " +
                "hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. " +
                "Start with this recipe and adjust to your taste.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it " +
                "just before serving.\n" +
                "4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the " +
                "guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes " +
                "oxidation which will turn the guacamole brown.) " +
                "Refrigerate until ready to serve.");
        guacamoleRecipe.setDifficulty(Difficulty.EASY);

        var guacamoleIngredients = new HashSet<Ingredient>();
        var avocados = new Ingredient();
        avocados.setDescription("Ripe avocados");
        avocados.setAmount(BigDecimal.valueOf(2));
        avocados.setUnitOfMeasure(piecesUom);
        guacamoleIngredients.add(avocados);
        avocados.setRecipe(guacamoleRecipe);

        var salt = new Ingredient();
        salt.setDescription("Salt");
        salt.setAmount(BigDecimal.valueOf(0.25));
        salt.setUnitOfMeasure(teaSpoonUom);
        guacamoleIngredients.add(salt);
        salt.setRecipe(guacamoleRecipe);

        var limeOrLemonJuice = new Ingredient();
        limeOrLemonJuice.setDescription("fresh lime juice or lemon juice");
        limeOrLemonJuice.setAmount(BigDecimal.valueOf(1));
        limeOrLemonJuice.setUnitOfMeasure(tableSpoonUom);
        guacamoleIngredients.add(limeOrLemonJuice);
        limeOrLemonJuice.setRecipe(guacamoleRecipe);

        var chili = new Ingredient();
        chili.setDescription("serrano chilies, stems and seeds removed, minced");
        chili.setAmount(BigDecimal.valueOf(1));
        chili.setUnitOfMeasure(piecesUom);
        guacamoleIngredients.add(chili);
        chili.setRecipe(guacamoleRecipe);

        guacamoleRecipe.setIngredients(guacamoleIngredients);

        var guacamoleNote = new Notes();
        guacamoleNote.setRecipeNotes("USE RIPE AVOCADOS");
        guacamoleNote.setRecipe(guacamoleRecipe);
        guacamoleRecipe.setNote(guacamoleNote);

        var categories = new HashSet<Category>();
        var mexican = categoryRepository.findByDescription("Mexican").get();
        mexican.setRecipes(Set.of(guacamoleRecipe));
        categories.add(mexican);
        guacamoleRecipe.setCategories(categories);

        recipeRepository.save(guacamoleRecipe);

        var chickenRecipe = new Recipe();
        chickenRecipe.setDescription("Spicy Grilled Chicken Tacos Recipe");
        chickenRecipe.setPrepTime(20);
        chickenRecipe.setCookTime(15);
        chickenRecipe.setSource("www.simplyrecipes.com");
        chickenRecipe.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        chickenRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings. \n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");
        chickenRecipe.setDifficulty(Difficulty.MODERATE);

        var chickenIngredients = new HashSet<Ingredient>();
        var chicken = new Ingredient();
        chicken.setDescription("skinless, boneless chicken thighs");
        chicken.setAmount(BigDecimal.valueOf(6));
        chicken.setUnitOfMeasure(piecesUom);
        chickenIngredients.add(chicken);
        chicken.setRecipe(chickenRecipe);

        var chickenSalt = new Ingredient();
        chickenSalt.setDescription("Salt");
        chickenSalt.setAmount(BigDecimal.valueOf(0.5));
        chickenSalt.setUnitOfMeasure(teaSpoonUom);
        chickenIngredients.add(chickenSalt);
        chickenSalt.setRecipe(chickenRecipe);

        var chickenChili = new Ingredient();
        chickenChili.setDescription("Ancho chili powder");
        chickenChili.setAmount(BigDecimal.valueOf(2));
        chickenChili.setUnitOfMeasure(tableSpoonUom);
        chickenIngredients.add(chickenChili);
        chickenChili.setRecipe(chickenRecipe);

        chickenRecipe.setIngredients(chickenIngredients);

        var chickenNote = new Notes();
        chickenNote.setRecipeNotes("You could also easily double or even triple this recipe for a larger party. ");
        chickenNote.setRecipe(chickenRecipe);
        chickenRecipe.setNote(chickenNote);

        var chickenCategories = new HashSet<Category>();
        var libyan = categoryRepository.findByDescription("Libyan").get();
        libyan.setRecipes(Set.of(chickenRecipe));
        chickenCategories.add(libyan);
        chickenRecipe.setCategories(chickenCategories);

        recipeRepository.save(chickenRecipe);
    }
}
