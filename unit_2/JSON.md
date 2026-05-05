Returning JSON Responses in Spring Boot

What is POJO?
- plain old java object
- simple java object that doesn't follow any special conventions or implement in frameworks
- serves as a means to encapsulate data
- typically have fields, constructors, getters, & setters
 public class Recipe {

    private List<String> ingredients;
    private List<String> instructions;

    // Getters and Setters
    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }
}

Use Java Records to Implement POJOs
- simplified syntax to achieve the same goal but with far less boiler plate code
- immutable data carriers that auto generate boiler plate code for ->
    - constructors
    - getters
    - equals()
    - hashCode()
    - toString()
public record Recipe(List<String> ingredients, List<String> instructions) {
}

Understanding Serialization and Jackson Library
- each time you send a GET request to a Spring Boot REST endpoint, the data is transmitted into JSON format for transmission over HTTP 
- serialization is the process of converting a POJO into a transmittable format, such as JSON
- Spring Boot leverages the Jackson library to perform serialization
    - Jackson takes your Java object and converts it into JSON through a series of configs and rules
    - flexible (customizable using annotations)

Tweaking Serialization Process
public class Recipe {

    @JsonProperty("recipe_ingredients") // renamed in JSON response
    private List<String> ingredients;
    
    private List<String> instructions;
    
    @JsonIgnore // excluded in JSON response
    private String internalNotes;

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }

    public String getInternalNotes() {
        return internalNotes;
    }

    public void setInternalNotes(String internalNotes) {
        this.internalNotes = internalNotes;
    }
}

Tweaking Serialization Process using Records
public record Recipe(
    @JsonProperty("recipe_ingredients") List<String> ingredients,
    List<String> instructions,
    @JsonIgnore String internalNotes
) {}

Additional Jackson Annotations
- @JsonInclude -> Can be used on a class or field. Specifies the conditions under which properties are included in the JSON output (e.g., only non-null fields).
- @JsonFormat -> Can be used on a field or method. Defines the format in which a property should be serialized (e.g., date formats).
- @JsonPropertyOrder -> Can be used on a class. Sets the order in which properties will appear in the JSON output.
- @JsonIgnoreProperties -> Can be used on a class. Ignores the specified properties during serialization and deserialization.
- @JsonProperty -> Can be used on a field or method. Changes the property name in the JSON output.
- @JsonIgnore -> Can be used on a field or method. Indicates that the field or method should be ignored during serialization and deserialization.
- @JsonCreator -> Can be used on a constructor or factory method. Indicates a constructor or factory method to be used for creating an instance from JSON.
- @JsonValue -> Can be used on a method. Indicates that the return value of this method will represent the single value to be serialized.