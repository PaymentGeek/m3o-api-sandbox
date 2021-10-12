# m3o-api-sandbox
Take home project for the Unzer Payment Acceptance team. My implementation choice was Serenity BDD framework with Java. 

# Installation
Just copy the project from GITHUB: https://github.com/PaymentGeek/m3o-api-sandbox
Project should pe public. 

Please note that the project is fully runnable, and all the test validations perform checks and pass. If you have any failures, let me know.

In case of issues please email me at dan.bulacu@gmail.com
# Security 

The project uses the bearer token I generated under my user in the m3o website. I did not waste any time to encrypt it for the purpose of this demo session.
If the authorization fails, most likely the token is expired, so please let me know to get you a new one. It is stored in config.properties.
(m3o API website says that the token will be active until I log out my user; I have no intention of doing that this week, so hopefully, by the time you run it,
it will still be ok).

# Running
Setup a Maven configuration with the following command line parameters:
clean verify clean verify -Dtags=tag

* The first feature file I pushed keeps all of the below in mind, so you should be able to run any of the tags(just one of them, i'd recommend @regression) one time, with no issue.

Tag Legend: 

@e2e - Scenario Outline for an E2E test case that takes a batch of users through all the states(create, update, logon, logoff, delete); feel free to extend the
Examples section and add more users 

@negative - A couple of negative scenarios to serve as an example

@individual - Individual scenarios for all the states outlined under the E2E scenario

@regression - full regression tag to run all of the above

IMPORTANT RUNNING CONSIDERATIONS:
For simplicity, I am passing the user ID order number as a parameter in the phrases. I also do cleanup after the tests(delete the user), however there is an 
error in the API when I try to board a new user I already deleted, because of which I need new user IDs with each run. So is is very important that when running a new regression batch, the user minds the order number parameters, as follows:

- e2e scenario

  Examples:

      |id               |response_code |

      |   delta         |200           |

      |   delta+1       |200           |

      |   delta+2       |200           |

- negative 

	- Scenario 1: delta+3 for both parameter references
	
	- Scenario 2: delta+4

- individual: delta+5

- regression: all of the above
	  
# Test Reporting
Run the clean/verify maven configuration task, and there is a summary of the execution in the console. I've added some verbosing in the console
for a first layer of feedback. However, for a more detailed situation, please consult the generated report by opening it
in your browser. There is a link generated in the console, the below serves as an example only:
file:///C:/Users/<local_User>/IdeaProjects/serenity-rest-starter/target/site/serenity/index.html

This is a serenity report format, quite rich and colourful. It has test summary and detailed test results, steps can be expanded to check their result.
It is pretty cool, please make sure you check it out.

# Test planning
For the purpose of this assignment, I have selected one endpoint(User) which I will cover with a combination of CRUD operations plus logon and logoff, as per the assignment.
This can server as a basis for any other similar service.
I have implemented a lightweight set of validations.

# Test Cases - Demo area with base scripts on all basic operations
End to End
Scenario Outline: E2E scenario that takes a batch of users through all the states - All basic operations for 3 selected user IDs

Negative
Scenario: Create a new user - duplicate
Scenario: Create a new user - missing mandatory fields

Individual
Scenario: Create a new user
Scenario: Update an user
Scenario: Logon user
Scenario: Logout user
Scenario: Delete a new user
  