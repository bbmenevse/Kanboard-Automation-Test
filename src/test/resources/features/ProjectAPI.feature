Feature: Project API Test
  @Test
  Scenario: Validate the number of projects
    Given I have access to the Kanboard API
    When I retrieve the number of projects
    Then the number of projects should be 7
  @Test
  Scenario: Create a project and verify the count increases
    Given I have access to the Kanboard API
    And I retrieve the number of projects
    When I create a new project with name "Created By API Project" and description "Description"
    Then the number of projects should increase by 1