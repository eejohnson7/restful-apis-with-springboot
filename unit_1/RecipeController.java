package unit_1;

/*
Welcome to the first task on REST Controllers! In this exercise, you will work with a simple Spring Boot application that includes a REST controller RecipeController. This controller handles HTTP GET /recipe/sandwich requests and returns a sandwich recipe.

Your goal is to examine the provided code and run the application using the gradle bootRun command. Once the application is running, you can open a new terminal window and execute the following command: curl http://localhost:8080/recipe/sandwich. cURL is a command-line tool used for making HTTP requests in web development. You should see the sandwich recipe message displayed.

Click the Run button to execute the tests, and then press Submit to proceed to the next task.
*/

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecipeController {

    @GetMapping("/recipe/sandwich")
    public String sandwichRecipe() {
        return "Layer ham and cheese between two slices of bread, add mayo and mustard";
    }
}
