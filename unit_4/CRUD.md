Creating CRUD Endpoints


CRUD
4 basic ops we can perform on resources in a RESTful API
- create
- read
- update
- delete


Undertanding CRUD
HTTP METHOD   SPRING ANNOTATION   EXPECTED OP
GET             @GetMapping       Read/Retrieve
POST            @PostMapping      Create
PUT             @PutMapping       Update/Replace
DELETE          @DeleteMapping    Delete
PATCH           @PatchMapping     Partial Update


Retrieving Data
@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping
    public List<RecipeItem> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @GetMapping("/{id}")
    public RecipeItem getRecipeById(@PathVariable UUID id) {
        return recipeRepository.findById(id);
    }
}


Creating Data
@PostMapping("/recipes")
public RecipeItem addRecipe(@RequestBody RecipeItem recipeItem) {
    recipeRepository.save(recipeItem);
    return recipeItem;
}

Updating Data
@PutMapping("/recipes/{id}")
public RecipeItem updateRecipe(@PathVariable UUID id, @RequestBody RecipeItem updatedRecipe) {
    RecipeItem existingRecipe = recipeRepository.findById(id);
    if (existingRecipe == null) {
        throw new RuntimeException("Recipe not found!");
    }

    existingRecipe.setTitle(updatedRecipe.getTitle());
    existingRecipe.setDescription(updatedRecipe.getDescription());

    return existingRecipe;
}

Understanding Req Body
{
  "title": "Updated Recipe Title",
  "description": "Updated Recipe Description"
}

Deleting Data
@DeleteMapping("/recipes/{id}")
public String deleteRecipe(@PathVariable UUID id) {
    RecipeItem recipe = recipeRepository.findById(id);
    if (recipe == null) {
        throw new RuntimeException("Recipe not found!");
    }

    recipeRepository.delete(id);
    return "Recipe deleted!";
}

Setting Common URL PRefix using @RequestMapping
@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping
    public List<RecipeItem> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @PostMapping
    public RecipeItem addRecipe(@RequestBody RecipeItem recipeItem) {
        recipeRepository.save(recipeItem);
        return recipeItem;
    }

    @GetMapping("/{id}")
    public RecipeItem getRecipeById(@PathVariable UUID id) {
        return recipeRepository.findById(id);
    }

    @PutMapping("/{id}")
    public RecipeItem updateRecipe(@PathVariable UUID id, @RequestBody RecipeItem updatedRecipe) {
        RecipeItem existingRecipe = recipeRepository.findById(id);
        if (existingRecipe == null) {
            throw new RuntimeException("Recipe not found!");
        }

        existingRecipe.setTitle(updatedRecipe.getTitle());
        existingRecipe.setDescription(updatedRecipe.getDescription());

        return existingRecipe;
    }

    @DeleteMapping("/{id}")
    public String deleteRecipe(@PathVariable UUID id) {
        RecipeItem recipe = recipeRepository.findById(id);
        if (recipe == null) {
            throw new RuntimeException("Recipe not found!");
        }

        recipeRepository.delete(id);
        return "Recipe deleted!";
    }
}

@RequestMapping on Method Level
@RequestMapping(value = "/recipes", method = RequestMethod.GET)
public List<RecipeItem> getAllRecipes() {
    return recipeRepository.findAll();
}