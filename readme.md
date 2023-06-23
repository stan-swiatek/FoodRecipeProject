# Food Recipe Application

The Food Recipe Application allows you to manage and explore various recipes. You can create new recipes, add ingredients, update existing recipes, and search for recipes based on title, ingredients, or preparation time.

## Prerequisites

Before running the application, ensure that you have the following prerequisites installed:

- Java Development Kit (JDK) 8 or above
- Maven
- Hibernate ORM
- Spring Boot



## Technologies Used

- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- Thymeleaf (HTML template engine)
- RESTful API

## Getting Started

Follow the steps below to run the Food Recipe Application:

1. Clone the repository to your local machine or download the source code.

2. Open a terminal or command prompt and navigate to the project's root directory.

3. Build the application using Maven by running the following command:


```
mvn clean install
```

4. Configure the database connection in the `application.properties` file located in the `src/main/resources` directory. Provide the necessary details such as the database URL, username, and password.

5. Run the application using the following command:


```
mvn spring-boot:run
```

6. Once the application is running, you can access it by opening a web browser and navigating to `http://localhost:8080/recipes`. This will take you to the homepage of the Food Recipe Application.

## Using the Application

The Food Recipe Application provides the following features:

- **View Recipes**: On the homepage, you can browse and view all available recipes.

- **Search Recipes**: Use the search functionality to find recipes based on title, ingredients, or preparation time.

- **Create Recipe**: Click on the "Create Recipe" button to add a new recipe. Provide the necessary details such as the title, description, preparation time, instructions, and ingredients.

- **Edit Recipe**: Click on the "Edit" button next to a recipe to modify its details. You can update the title, description, preparation time, instructions, and ingredients.

- **Delete Recipe**: To remove a recipe, click on the "Delete" button next to the recipe you want to delete.

## API Documentation

The Food Recipe Application provides a RESTful API that allows programmatic access to the application's functionalities. Here are some important endpoints:

- **GET /recipes**: Retrieves all recipes.
- **GET /recipes/{id}**: Retrieves a recipe by its ID.
- **POST /recipes/create**: Creates a new recipe.
- **PUT /recipes/{id}**: Updates an existing recipe.
- **DELETE /recipes/{id}**: Deletes a recipe.

To explore and interact with the API, you can use tools like Postman or cURL. Send HTTP requests to the above endpoints to perform the desired actions.

## API Call Metrics
The Food Recipe Application tracks API call metrics using Spring Boot Actuator. To view the API call metrics, follow these steps:

Start the Food Recipe Application server.

Open your web browser and visit the following URL:

API Call Count: http://localhost:8080/actuator/metrics/api.calls
All Metrics: http://localhost:8080/actuator/metrics
These URLs provide detailed information about the API call metrics.

## Testing

The Food Recipe Application includes unit tests to ensure the correctness of its functionality. To run the tests, use the following command:

```
mvn test
```


The test results will be displayed in the terminal, indicating whether the tests passed or failed.

## Troubleshooting

If you encounter any issues while running the application, please try the following steps:

- Double-check that all the prerequisites are installed correctly.

- Verify the database connection details in the `application.properties` file.

- Make sure there are no conflicting processes running on port 8080, which is the default port for the application. If necessary, you can change the port in the `application.properties` file.

- Ensure that the Maven dependencies are resolved properly. Try running `mvn clean install` again to update the dependencies.

If the problem persists, please refer to the project documentation or seek assistance from the project maintainers.

## Contributing

Contributions to the Food Recipe Application are welcome.