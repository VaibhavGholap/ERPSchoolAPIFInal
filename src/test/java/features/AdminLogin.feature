Feature: Validating the Admin Login API

Scenario Outline: Verify if admin user login sucessfully using userLogin API.
Given user Login payload with "<user_name>" "<password>"
When user call "userLoginAPI" API with "Post" http request
Then the API call got success with status code 200
And "result" in responce is "SUCCESS"
And "code" in responce is "200"
# And verify token created maps to "<name>" using "getAllBatch" API


Examples:
| user_name                 | password |
| yogesh.rade@purestudy.com | 123456   |


Scenario: