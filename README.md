# RS2 Note Application
RS2 Note application is a spring boot based backend app. 

It has different spring modules, spring jpa, spring data, hibernate and spring security modules. 

The project code is structured based on the features and each feature has different layers with abstraction in mind. This makes the code easy to understand and easy to add new features.

## Built With

- SPRING BOOT,
- SPRING SECURITY,
- SPRING JPA,
- SPRING DATA,
- HIBERNATE,
- Junit & Mockito
- H2 IN MEMORY DATABASE,
- Gradle

## Getting Started

To get a local copy up and running follow these simple example steps.

### Prerequisites
To run the project smoothly, you must have the following installed on your machine:

- Gradle.
- Java 11
- Git

### Setup
You need to clone the project by running `git clone https://github.com/berhanu-tarekegn/rs2-note-backend.git` You'd then navigate to the location to which you cloned the project and then run the app. 

The app is set to run at port: 7088
you can access the h2 database using `localhost:7088/rs2-note` with username: `sa` and password: `password`

Copy the script data.sql under `src/main/resource/data.sql` and execute the init script using the h2 console to populate the user and credential table

Use the username and password combination to access the endpoints.

## Authors

👤 **Berhanu Tarekegn**

- Github: [@BerhanuTarekegn](https://github.com/berhanu-tarekegn)
- Linkedin: [Berhanu Tarekegn](https://www.linkedin.com/in/berhanu-tarekegn-687367123/)

## 🤝 Contributing

Contributions, issues and feature requests are welcome!

Feel free to check the [issues page](issues/).

## Show your support

Give a ⭐️ if you like this project!

## Acknowledgments

- RS2
- Spring team
- Castille labs 

## 📝 License

This project is [MIT](lic.url) licensed.
