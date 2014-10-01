# cs309t13 Project

## Setup:
1. Clone this repository
2. Install Maven and Tomcat. If on os x, installing homebrew and running `make setup-mac` will do this for you
3. `make install` to install dependencies for the project
4. `make run` to run the application on localhost:8080/

## Database:
- A local MySQL database should be used for local testing. To do this, MySQL will have to be installed on your computer.
- You can start it by running `mysql.server start` via command line.
- You should have the root user configured to have no password to allow connection (this is the default).
- It should also be up to date with the "intialize_database.sql" in src/main/resources.
