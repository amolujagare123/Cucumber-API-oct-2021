Feature:  All API scenarios

  # sample user create request

  @sampleUserCreate @sampleUser
  Scenario: Verify user is created successfully
    Given Add User Payload for Create User is created
    When user calls AddUser request with POST http request
    Then the API call got success with status code "201"
   # Then job should be trainer;
    Then "job" should be "leader";

  @sampleUserUpdate @sampleUser
  Scenario: Verify user is Updated successfully
    Given Update User Payload for Update User is created
    When user calls UpdateUser request with PUT http request with path parameter "2"
    Then the API call got success with status code "200"
    Then "name" should be "Rajesh";

    # try delete request

