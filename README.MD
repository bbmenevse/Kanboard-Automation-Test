# Kanboard Test Automation Project
## Summary
This project uses Kanboard as a base for learning test automation. You can check out the original application [here](https://kanboard.org/).

I have used Docker to deploy the application. For the database, I used MariaDB as suggested in Kanboard documentations.
The project consists of two parts: browser automation tests and API tests. I’ve completed the browser-side tests using TestNG, but I’m still working on the API call tests.

### For browser-side tests, I’ve used the following frameworks/libraries:
- Selenium
- Selenide
- Testng

### For api call tests, I have used:
- Testng
- Cucumber
- Rest Assured
- Allure

## Kanboard

Kanboard is a simple, open-source project management tool based on Kanban boards. It helps track tasks, organize workflows, and manage projects visually. With features like swimlanes, subtasks, and automation, it’s great for keeping work organized and boosting productivity. Perfect for teams or solo users looking for a lightweight solution.

## Isolated Tests
### Test Coverage
- **Login Page** – Valid/invalid login tests
- **Dashboard** – Projects, Tasks and Subtasks tests
- **Projects** – Board and List pages tests
- **Management Pages** – User and Group management tests
- **User Details** – User's details pages tests
- **Scenario Tests** - A test that includes multiple pages.

### Login Page Tests
Login Page Tests include valid/invalid login scenarios and a basic SQL injection test.

### Dashboard Tests
Dashboard tests cover four separate pages: the Projects page, Tasks page, Subtasks page, and Overview page, which combines all three.
All four pages share most of the UI, except for their respective sections.

Tests cover sorting, CRUD operations (not all operations are available on each page), and page functionality.
### Projects Tests
Each project has two detailed views: List and Board. Both pages display all related tasks for the project.

Tests cover page functionality, while the Board page additionally includes sorting and filtering tests.
### Management Pages Tests
There are 2 categories for management pages: User Management page and Group Management page. Group Management page also have a sub page called Group Members.

User Management tests covers CRUD operations, page functionality and sorting test. Group management tests cover CRUD operations. Group members tests cover CRUD operations, sorting test and some functionality tests.
### User Details Tests
User details include avatar settings, password changes, profile editing, and the user summary page.

Each page has its own functionality tests.

### Scenario Tests
A test that covers multiple pages and interactions that are related.

## API Tests
There are two types of API tests:

TestNG API Tests – Uses RestAssured and Allure for testing project-related APIs.
Cucumber API Tests – Covers some project API tests and a simple subtask scenario.
I haven't covered most of the API calls yet. I will add more tests later.

**Project API Tests**

I tested the Project API twice—once with TestNG and once with Cucumber. Both versions use RestAssured and generate reports with Allure.

ProjectAPI.feature Scenarios:
* Validate the number of projects
* Create a project and verify the count increases
* Update a project and ensure it’s updated
* Delete a project and confirm it’s deleted
**Subtask API Tests**

There’s also a Cucumber test for MySubtasks, but it doesn’t use RestAssured. It only has one scenario:
* Verify that the icon changes when clicking a subtask

# Manual Tests

Most of the application runs smoothly. I will focus on bugs I have found on this report.



## To be continued
## Reports will be added, and each part will be explained