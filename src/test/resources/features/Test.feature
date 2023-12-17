Feature: API Endpoint Testing

  Background:
    Given the API base URL is "https://api.example.com"

  Scenario: Retrieve Information from the API
    When a GET request is made to "/users"
    Then the response status code should be 200
    And the response should contain "userList"
    And the response should have the following structure:
      | id   | name       | email                |
      | 1    | John Doe    | john.doe@example.com |
      | 2    | Jane Smith  | jane.smith@example.com |

  Scenario: Create a New User
    Given the request body:
      """
      {
        "name": "New User",
        "email": "new.user@example.com"
      }
      """
    When a POST request is made to "/users"
    Then the response status code should be 201
    And the response should contain "New User"
    And the response should contain "new.user@example.com"

  Scenario: Update User Information
    Given the request body:
      """
      {
        "name": "Updated User"
      }
      """
    When a PUT request is made to "/users/1"
    Then the response status code should be 200
    And the response should contain "Updated User"

  Scenario: Delete a User
    When a DELETE request is made to "/users/2"
    Then the response status code should be 204
    And the response should be empty

  Scenario Outline: Retrieve User Information with Parameters
    Given the request parameters:
      | key    | value      |
      | userId | <user_id>   |
    When a GET request is made to "/users/<user_id>"
    Then the response status code should be <expected_status_code>
    And the response should contain "<expected_name>"
    And the response should contain "<expected_email>"

    Examples:
      | user_id | expected_status_code | expected_name | expected_email           |
      | 1       | 200                 | John Doe      | john.doe@example.com     |
      | 99      | 404                 | null          | null                     |

  Scenario: Handle Authentication
    Given a valid API key
    When a GET request is made to "/secured-endpoint"
    Then the response status code should be 200
    And the response should contain "Access Granted"

  Scenario: Handle Invalid Authentication
    Given an invalid API key
    When a GET request is made to "/secured-endpoint"
    Then the response status code should be 401
    And the response should contain "Access Denied"
