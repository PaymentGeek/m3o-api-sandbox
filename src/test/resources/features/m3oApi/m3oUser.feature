Feature: m3o User Feature

  @E2EScenario @regression
  Scenario Outline: E2E scenario that takes a batch of users through all the states
    #Create a new user
    Given I call method POST from endpoint Create for user <id> with body from template createUser
    Then the response code should be <response_code>
    And the value in field account is not null
    Given I call method POST from endpoint Update for user <id> with body from template updateUser
    Then the response code should be <response_code>
    # Since the response body of this method is empty, there is no further validation we can perform
    # Logon user
    Given I call method POST from endpoint Login for user <id> with body from template loginUser
    Then the response code should be <response_code>
    # Save the session ID received in this step to a new template to be used for Logout
    And the value in field session.id is saved to logoutUser
    # Logout user
    Given I call method POST from endpoint Logout for user <id> with body from template logoutUser_session
    Then the response code should be <response_code>
    # Since the response body of this method is empty, there is no further validation we can perform
    # Delete a new user
    Given I call method POST from endpoint Delete for user <id> with body from template deleteUser
    Then the response code should be <response_code>
    # Since the response body of this method is empty, there is no further validation we can perform
    
  Examples:
      |id            |response_code |
      |   0020       |200           |
      |   0021       |200           |
      |   0022       |200           |

  @Negative @regression
  Scenario: Create a new user - duplicate
    Given I call method POST from endpoint Create for user 022 with body from template createUser
    Then the response code should be 200
    # Validate the API response is not null
    And the value in field account is not null
    And I call method POST from endpoint Create for user 022 with body from template createUser
    # Validate the response code and some details in the response payload
    Then the response code should be 400
    And the value in field Detail equals username already exists
    And the value in field Status equals Bad Request

  @Negative2 @regression
  Scenario: Create a new user - missing mandatory fields
    Given I call method POST from endpoint Create for user 025 with body from template createUserNegative
    # Validate the response code and some details in the response payload
    Then the response code should be 400
    And the value in field Detail equals email has wrong format
    And the value in field Status equals Bad Request

  @Individual @regression
  Scenario: Create a new user
    Given I call method POST from endpoint Create for user XXX with body from template createUser
    Then the response code should be 200
    # Validate the API response is not null
    And the value in field account is not null

  @Individual @regression
  Scenario: Update an user
    Given I call method POST from endpoint Update for user XXX with body from template updateUser
    Then the response code should be 200
    # Since the response body of this method is empty, there is no further validation we can perform

  @Individual @regression
  Scenario: Logon user
    Given I call method POST from endpoint Login for user XXX with body from template loginUser
    Then the response code should be 200
    # Save the session ID received in this step to a new template to be used for Logout
    And the value in field session.id is saved to logoutUser

  @Individual @regression
  Scenario: Logout user
    Given I call method POST from endpoint Logout for user XXX with body from template logoutUser_session
    Then the response code should be 200
    # Since the response body of this method is empty, there is no further validation we can perform

  @Individual @regression
  Scenario: Delete a new user
    Given I call method POST from endpoint Delete for user XXX with body from template deleteUser
    Then the response code should be 200
    # Since the response body of this method is empty, there is no further validation we can perform