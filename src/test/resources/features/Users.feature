Feature: Advanced API Endpoint Testing

  Background:
    Given the API base URL is "https://api.example.com"

  Scenario Outline: Retrieve Information from the API
    When a GET request is made to "<endpoint>"
    Then the response status code should be <expected_status_code>
    And the response should contain "<expected_field>" with value "<expected_value>"

    Examples:
      | endpoint  | expected_status_code | expected_field | expected_value      |
      | /users    | 200                  | userList       | John Doe,jane.smith |
      | /products | 200                  | productCount   | 10                  |

  Scenario Outline: Create a New Entity
    Given the request body:
      """
      {
        "name": "<name>",
        "email": "<email>"
      }
      """
    When a POST request is made to "<endpoint>"
    Then the response status code should be <expected_status_code>
    And the response should contain "<expected_field>" with value "<expected_value>"

    Examples:
      | endpoint  | expected_status_code | expected_field | expected_value |
      | /users    | 201                  | name           | John Doe       |
      | /products | 201                  | productAdded   | true           |

  Scenario Outline: Update Entity Information
    Given the request body:
      """
      {
        "name": "<new_name>"
      }
      """
    When a PUT request is made to "<endpoint>/<entity_id>"
    Then the response status code should be <expected_status_code>
    And the response should contain "<expected_field>" with value "<expected_value>"

    Examples:
      | endpoint  | entity_id | expected_status_code | expected_field | expected_value |
      | /users    | 1         | 200                  | updatedName    | Updated User   |
      | /products | 100       | 200                  | updatedProduct | true           |

  Scenario Outline: Delete an Entity
    When a DELETE request is made to "<endpoint>/<entity_id>"
    Then the response status code should be <expected_status_code>
    And the response should contain "<expected_field>" with value "<expected_value>"

    Examples:
      | endpoint  | entity_id | expected_status_code | expected_field | expected_value |
      | /users    | 2         | 204                  | deletionStatus | success        |
      | /products | 101       | 204                  | deletionStatus | success        |

  Scenario Outline: Handle Error Scenarios
    When a <request_type> request is made to "<endpoint>"
    Then the response status code should be <expected_status_code>
    And the response should contain "<error_field>" with value "<error_message>"

    Examples:
      | request_type | endpoint     | expected_status_code | error_field     | error_message              |
      | GET          | /nonexistent | 404                  | errorCode       | Resource not found         |
      | POST         | /users       | 400                  | validationError | Invalid request parameters |

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
