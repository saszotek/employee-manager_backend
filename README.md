# Employee Manager - Backend

**This repository is backend part of Employee Manager application.**\
This is the REST API that is integrated with [employee-manager_fronted](https://github.com/saszotek/employee-manager_frontend) repository. It allows you to register or log in using an existing account. Authentication is managed by JWT strategy. By logging in, user can manage employees. Access to management functions is based on assigned role of that account:
- Admin - delete, update, view employee details,
- Moderator - update, view employee details,
- User - view employee details.\

Main page lists all users and includes server-side pagination. All user information is stored in the MySQL database.

## Live version

**Live demo:** *offline*

## Technologies

- Java Spring Boot
- JSON Web Token
- MySQL
