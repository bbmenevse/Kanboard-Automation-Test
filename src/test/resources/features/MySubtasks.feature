Feature: My Subtasks Page Tests

  Scenario: Verify that the icon changes when clicking a subtask
    Given the user logins with username: "admin" and password: "123456"
    When the user clicks on "Second Subtask"
    Then the icon should change as expected