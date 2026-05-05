Building RESTful APIs with Spring Boot

What is REST?
Representational State Transfer
- emnphasizes the stateless operations of resources

Resourcesw in REST
- each data unit is considered a resource
- /recipes/cookies
- /recipes/spaghetti

Representation of Resources
{
    "title": "Cookies",
    "ingredients": ["flour", "sugar"]
}

HTTP Methods in REST
- GET -> retrieve a sepcific recipe
- POST -> add a new recipe
- PUT -> update an existing recipe
- DELETE -> remove a recipe
map naturally to CRUD operations

Benefits of REST
- multi-client envs
- reusable backend logic
- single page apps (SPAs)

How Spring Boot Supports REST
- easy REST API creation
- mulitple annotations
- controllers handle requests