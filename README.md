# Expense Reimbursement System (ERS)

This is a solo project. The expense reimbursement system was created with an AWS Oracle SQL database for storing the system's user info and reimbursement requests. I used .gitignore to keep files off of the Github that store the information for connecting to and logging into the AWS database.

## Requirements
- An Employee can login
- An Employee can view the Employee Homepage
- An Employee can logout
- An Employee can submit a reimbursement request
- An Employee can view their pending reimbursement requests
- An Employee can view their resolved reimbursement requests
- An Employee can view their information
- An Employee can update their information
- An Employee receives an email when one of their reimbursement requests is resolved (optional)

- A Manager can login
- A Manager can view the Manager Homepage
- A Manager can logout
- A Manager can approve/deny pending reimbursement requests
- A Manager can view all pending requests from all employees
- A Manager can view all resolved requests from all employees and see which manager resolved it
- A Manager can view all Employees
- A Manager can view reimbursement requests from a single Employee
- A Manager can register an Employee, which sends the Employee an email with their username and temp password (optional)
     - An Employee can reset their password (Optional - tied with above functional requirement)

## Technologies:
- Java 1.8
- Servlets
- JDBC
- SQL
- HTML/CSS/Javascript
- Bootstrap
- AJAX
- JUnit
- log4j

## Environment:
- Tomcat
- IDE
- DBeaver
