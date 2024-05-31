## Cook with Class/Gotuj z KLASĄ ;)

### Project - PAP2024L-Z16

### Authors:
- Michał Mokrzycki
- Adam Sokołowski
- Jan Szymczak

### Instructions

To be able to use API you need to insers following APIKey.json file in the root directory of the project.
With format:
```json
{
"recipeApiKey": "",
"recipeApiID": "",
"ingredientApiKey": "",
"ingredientApiID": ""
}
```
And put your API keys in the fields. You can get the API keys by logging into the Edamam API website(link below).
You also need to setup the database connection in the application.properties file in the resources directory.
We recommend using PostgreSQL database. We recommend creating .env file in project root for this.
```properties
DB_PASSWORD=
DB_URL=
DB_USER=
```
App is easy to use and doesn't require any additional setup. Just run the app and enjoy the recipes.
Website is available at localhost:8080 and is planned to be improved in the future.

#### Links

- https://developer.edamam.com/



