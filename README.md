# m3o-api-sandbox
Take home project for the Payment Acceptance team.
# Installation
Just copy the project from GITHUB: https://github.com/PaymentGeek/m3o-api-sandbox
# Running
Setup a Maven configuration with the following command line parameters:
clean verify clean verify -Dtags=<tag>
<Tag>: 
@regression - full regression
@XRAY-000X - specific test
# Test Reporting
Run the project, and there is a summary of the execution in the console. I've added some verbosing in the console
for a first layer of feedback. However, for a more detailed situation, please consult the generated report by opening it
in your browser.
file:///C:/Users/<local_User>/IdeaProjects/serenity-rest-starter/target/site/serenity/index.html
# Test planning
For the purpose of this assignment, I have selected one endpoint(User) which I will cover with a combination of CRUD operations, as per the assignment.
This can server as a basis for any other similar service.
I have implemented a lightweight set of validations.
# Test Cases - Demo area with base scripts on all basic operations
