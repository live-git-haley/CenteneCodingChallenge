# CenteneCodingChallenge

<h2> Introduction </h2>

This project was created to help manage tracking the status of enrollees in a health care program. The project builds a RESTful Web Service using the Spring Boot Framework.
 
This project incorporates features of a Full-Stack application such as: 
<ul>
<li> REST API </li>
<li> Microservice-based architecture featuring  </li>
 <li> Swagger Api Documentation </li>
 

 </ul>
<h2> Structure </h2>
Spring boot (Java) utilizing hibernate and JPA for data persistence
Management of MySQL database that supports a one-to-many relationship
<ul>
 <li> One Enrollee can have many Dependents </li>
</ul>
Swagger API Documentation : located at http://localhost:8080/swagger-ui.html (Access through the browser, port number changes according to machine)
<h2> Data Persistence </h2>
Data persistence has been implemented using the hibernate framework and MySQL to support a RESTful interface.
<h2> Setting up MySQL Database </h2>
The project sql scripts can be found under src/main/resources/scripts/customers.sql . Run this Sql script on your backend and the dummy data will be inserted into your tables.

