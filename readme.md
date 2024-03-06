## How to start this project ?

To get started with the patient-jee project, you'll need to set up a server environment with Tomcat and establish a connection to your database.
Also, ensure you have **Java 17 and don't hesitate to reload your Maven project if needed**.

### 1 - Setting up Tomcat server

To run patient-jee, you can use a Tomcat server, you can follow those instructions :

1. Go to Apache website and download the lastest version of Tomcat : https://tomcat.apache.org/download-10.cgi

2. install "Smart Tomcat" plugin on your IDE (I personnaly use IntellJ), it will facilitate Tomcat management.

<img src="../../patients-jee/src/main/webapp/WEB-INF/assets/smart-tomcat-plugin.jpeg" alt="how to download smart tomcat plugin" width="1000">

3. click on "edit configurations" at the top right of the IDE and add a new smart tomcat

<img src="../../patients-jee/src/main/webapp/WEB-INF/assets/start-smart-tomcat.jpeg" alt="how to start smart tomcat" width="1000">

- Name : you can choose the server name, here that "Unnamed".
- Tomcat Server : select the installation directory of Tomcat on your computer.
- Deployment directory : you must select the path to the "webapp" directory of this project.
- Context path : you can choose your own app root path.

4. Validate your settings and start the server. Then, go to http://localhost:8080/patient-jee to access the application.

<img src="../../patients-jee/src/main/webapp/WEB-INF/assets/server-started.jpeg" alt="how to start smart tomcat" width="400">


### 2 - Setting up database connection

1. To make a database and fill data inside of it, you can use mySql queries that you can find inside of "utils" folder
   and paste it in WorkBench for example.


<img src="../../patients-jee/src/main/webapp/WEB-INF/assets/workbench.jpeg" alt="workbench view" width="1000">



2. Add a "secure.properties" file to resources folder :

<img src="../../patients-jee/src/main/webapp/WEB-INF/assets/secure.properties.jpeg" alt="secure.properties is added to resources folder" width="400">


Then specify inside of it the username and password that you use to connect to MySql :
```
hibernate.connection.username = example
hibernate.connection.password = example
hibernate.connection.db-url=jdbc:mysql://localhost:3306/patient_db
```



