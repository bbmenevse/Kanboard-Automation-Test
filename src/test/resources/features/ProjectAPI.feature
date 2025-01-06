Feature: Project API Test
  @Test
  Scenario: Validate the number of projects
    Given I have access to the Kanboard API with user "admin" and password "123456"
    When I retrieve the number of projects
    Then the number of projects should be 7
  @Test
  Scenario: Create a project and verify the count increases
    Given I have access to the Kanboard API with user "admin" and password "123456"
    And I retrieve the number of projects
    When I create a new project with the following details:
      | name         | Api StepTest Project   |
      | description  | A test description     |
      | owner_id     | 1                      |
      | identifier   | STEPPROJ               |
    Then the number of projects should increase by 1

  @Test
  Scenario: Update a project and ensure it is updated.
    Given I have access to the Kanboard API with user "admin" and password "123456"
    When I Update a project by id or identifier with the following details:
      | project_id  | 10                       |
      | name        | Updated StepTest Project |
      | description | Updated Description      |
      | owner_id    | 1                        |
      | identifier  | STEPPROJECT              |
    Then the updates should be reflected in the project details

    @Test
    Scenario: Delete a project and confirm it is deleted
      Given I have access to the Kanboard API with user "admin" and password "123456"
      When I remove project with given id: 10