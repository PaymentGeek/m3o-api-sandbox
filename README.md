# Best-Buy Automated Testing Solution
Project for the Best-Buy API.
# Installation
Just copy the project from GITHUB: https://github.com/PaymentGeek/serenity-rest-starter.git
# Running
Setup a Maven configuration with the following command line parameters:
clean verify clean verify -Dtags=<tag>
<Tag>: 
@bestBuy - full regression
@XRAY-000X - specific test
# Test Reporting
Run the project, and there is a summary of the execution in the console. I've added some verbosing in the console
for a first layer of feedback. However, for a more detailed situation, please consult the generated report by opening it
in your browser.
file:///C:/Users/<local_User>/IdeaProjects/serenity-rest-starter/target/site/serenity/index.html
# Test planning
For the purpose of this assignment, I have selected one endpoint(Product) which I will cover with all basic CRUD operations.
This can server as a basis for any other similar service.
I have implemented a lightweight set of validations.
# Test Cases - Demo area with base scripts on all basic operations
Feature: Best Buy Products Feature
GET - 7 Tests
Scenario 1: XRAY-0001 I get a list of products from Best Buy
Scenario Outline 2( 3 x tests ): XRAY-0002 I get a product based on an ID
Scenario Outline 3( 3 x tests ): XRAY-0003 I get a product based on an invalid ID
POST - 5 Tests
Scenario Outline 4( 3 x tests ): XRAY-0004 POST a new product
Scenario Outline 5( 2 x tests ): XRAY-0005 POST an invalid product
PUT - 6 Tests
Scenario Outline 6( 3 x tests ): XRAY-0006 PUT a new product
Scenario Outline 7( 3 x tests ): XRAY-0007 PUT an invalid product
DELETE - 4 Tests
Scenario 8( 1 x test ): XRAY-0009 DELETE a product
Scenario Outline 9( 3 x tests ): XRAY-0009 DELETE an invalid product
Grand Total: 22 test cases.
Please Note this is a skeleton on the base set of operations, built in the interest of a good time and quality ratio.