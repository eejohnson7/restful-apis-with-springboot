Returning Status Codes with Response Entity

HTTP Status Codes Overview
- 1xx (informational) -> req received, continuing process
- 2xx (successful) -> req was successfully received, understood, and accepted
- 3xx (redirection) -> further action needs to be taken to complete the req
- 4xx (client error) -> req contains bad syntax or cannot be fulfilled
- 5xx (server error) -> server failed to fulfill an apparently valid req

Common HTTP Status Codes
STATUS CODE     DESCRIPTION
200             OK - req has succeeded
201             Created - req has ben fulfilled, resulting in the creation of a new resource
204             No Content - server successfully processed req, but is not returning any content
400             Bad Request - The server could not understand req due to invalid syntax
401             Unauthorized - The client must authenticate itself to get the requested response
404             Not Found - The server cannot find the requested resource
500             Internal Server Error - The server has encountered a situation it doesn't know how to handle


Understanding ResponseEntity
- provides a way to return not only the status code but also the HTTP headers and the body of the response

ResponseEntity in Action
@GetMapping("/recipes/{id}")
public ResponseEntity<RecipeItem> getRecipeById(@PathVariable UUID id) {
    RecipeItem recipe = recipeRepository.findById(id);
    if (recipe == null) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Returns 404 Not Found if the item doesn't exist
    }
    return new ResponseEntity<>(recipe, HttpStatus.OK); // Returns 200 OK if the item exists
}

ResonseEntity Methods
@GetMapping("/recipes/{id}")
public ResponseEntity<RecipeItem> getRecipeById(@PathVariable UUID id) {
    RecipeItem recipe = recipeRepository.findById(id);
    if (recipe == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Returns 404 Not Found if the item doesn't exist
    }
    return ResponseEntity.ok(recipe); // Returns 200 OK if the item exists
}

Using the ResponseEntity Buidler Pattern
- The ResponseEntity class in Spring Boot uses the builder pattern, enabling you to construct response objects in a flexible and readable manner. By using various builder methods, you can set the status, headers, and body of the response in a step-by-step fashion. The terminal .build() method finalizes the object creation and returns the constructed ResponseEntity.
- Here are some common builder methods available for ResponseEntity:
    - .status(HttpStatus status): Sets the HTTP status code.
    - .body(Object body): Sets the body of the response.
    - .header(String headerName, String... headerValues): Adds headers to the response.
    - .contentType(MediaType mediaType): Sets the Content-Type header.
    - .location(URI location): Sets the Location header, commonly used in responses to POST requests that create new resources.
    - .build(): Finalizes the ResponseEntity construction and returns the object.

Other ResponseEntity Mehthods
- ResponseEntity.ok(Object body) – Returns status code 200 (OK) with the specified body.
- ResponseEntity.status(HttpStatus.CREATED).body(Object body) – Returns status code 201 (Created) with a body containing the newly created resource.
- ResponseEntity.noContent() – Returns status code 204 (No Content).
- ResponseEntity.badRequest().body(Object error) – Returns status code 400 (Bad Request) with the specified error message or object.
- ResponseEntity.unauthorized().build() – Returns status code 401 (Unauthorized).
- ResponseEntity.notFound().build() – Returns status code 404 (Not Found).
- ResponseEntity.accepted().body(Object body) – Returns status code 202 (Accepted) indicating that the request has been accepted for processing, but the processing is not yet complete.
- ResponseEntity.unprocessableEntity().body(Object body) – Returns status code 422 (Unprocessable Entity) indicating that the server understands the content type of the request entity, but was unable to process the contained instructions.