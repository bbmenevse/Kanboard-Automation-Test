# Kanboard Test Automation Project
## Summary
This project uses Kanboard as a base for learning test automation. You can check out the original application [here](https://kanboard.org/).

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
### Test Coverage
- **Login Page** – Valid/invalid login tests
- **Dashboard** – Projects, Tasks and Subtasks tests
- **Projects** – Board and List pages tests
- **Management Pages** – User and Group management tests
- **User Details** – User's details pages tests

### Login Page Tests
Login Page Tests include valid/invalid login scenarios and a basic SQL injection test.

### Dashboard Tests
Dashboard tests cover four separate pages: the Projects page, Tasks page, Subtasks page, and Overview page, which combines all three.
All four pages share most of the UI, except for their respective sections.

Tests include sorting, CRUD operations (not all operations are available for each page), and button functionality tests.

### Projects Tests
Each project has two detailed views: List and Board. Both pages display all related tasks for the project.

Both pages have page elements functionality tests. Board additionally has sort and filter tests. 

## Management Pages Tests
There are 2 categories for management pages: User Management page and Group Management page. Group Management page also have a sub page called Group Members.

## To be continued
## Reports will be added, and each part will be explained