Handling Path Variables and Query Parameters in Spring Boot

Understanding Path Variables
- /recipes/{recipeId}
- /categories/{recipeCategory}
- /categories/recipeCategory/recipes/{recipeId}

Path Variable Example
@GetMapping("/recipes/{recipeId}")
public Recipe getRecipeById(@PathVariable Long recipeId) {
    return recipeRepository.findById(recipeId)
            .orElseThrow(() -> new IllegalArgumentException("Recipe not found"));
}

Named Path Variables
@GetMapping("/recipes/{recipeId}")
public Recipe getRecipe(@PathVariable("recipeId") Long id) {
    return recipeRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Recipe not found"));
}

Understanding Query Parameters
- used to pass additional info to endpoints, typically for filtering or sorting data
- /recipes?diet=vegan
- /recipes?diet=vegan&complexity=easy
- /recipes?diet=vegan&complexity=easy&prepTime=30

Query Parameters Example
@GetMapping("/recipes")
public List<Recipe> getRecipesByType(@RequestParam String type) {
    return recipeService.getRecipesByType(type);
}

Making Query Parameters Optional
@GetMapping("/recipes")
public List<Recipe> getRecipesByType(@RequestParam(required = false) String type) {
    return recipeService.getRecipesByType(type);
}

Making Query Parameter Optional with java.util.Optional
@GetMapping("/recipes")
public List<Recipe> getRecipesByType(@RequestParam Optional<String> type) {
    return recipeService.getRecipesByType(type.orElse(null));
}

Named Query Parameters
@GetMapping("/recipes")
public List<Recipe> getRecipes(@RequestParam("type") Optional<String> recipeType) {
    return recipeService.getRecipesByType(recipeType);
}

Combining Path Variables and Query Parameters
@GetMapping("/category/{recipeCategory}")
public List<Recipe> getRecipesByCategoryAndDietaryPreference(@PathVariable String recipeCategory, @RequestParam Optional<String> dietaryPreference) {
    List<Recipe> recipes = recipeRepository.findByCategory(recipeCategory);
    
    if (dietaryPreference.isPresent()) {
        return recipes.stream()
                      .filter(recipe -> recipe.getDietaryPreference().equalsIgnoreCase(dietaryPreference.get()))
                      .collect(Collectors.toList());
    }
    
    return recipes;
}