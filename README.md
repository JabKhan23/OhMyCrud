# OhMyCrud
 QA Project 2
Coverage: 99%

This is the second project for QA which is a Springboot application that is a task system which has been created using both front and back end technologies. 

The technologies that were used were:
```
Jira - Project Management [Jira](https://jabkhan.atlassian.net/jira/software/projects/PRAC/boards/5/roadmap)
SpringBoot – IDE - End
Java, Maven - Build Tool – Back End
HTML, CSS, Javascript - Front End Source Code
JUnit, Mockito, Selenium - Testing
MySQL - Relational Database Language
Postman - assisted the API development 
```

## Getting Started

The instructions below will help you get started with the program and how it works. For further information please look at deployment. 

### Prerequisites

Have the latest [Java SE Development Kit version 17 installed.](https://www.oracle.com/java/technologies/downloads/#java17)

Have the latest [MySQL Community Server version 8 installed including MySQL Workbench.](https://dev.mysql.com/downloads/windows/installer/8.0.html)

Have the latest [Apache Maven version installed.](https://maven.apache.org/download.cgi)

Everything should run as intended if these technologies are installed

### Installing

You can either clone the repo from GitHub or download the zip and install manually. Once you have installed it you can add it to Eclipse.
Once installed you can open src/main/resources/Task.sql and run the database there. You can also run it in the H2 client. 

### Editing and Running in Eclipse

Open OhMyCrud project and you can run this as a SpringBoot App. Make sure that the Java you are running is Java-17 and application.properties is set up to production. 
You should see on the console this page when you are succesful. A good indicator that you are in is if the time is displayed at the bottom like so. ![image](https://user-images.githubusercontent.com/107106989/184556594-9d4c12dd-6502-4cf2-9d52-e34557ba0bbf.png)


### Using the application

Once Springboot has booted. You can then go on a browser and type in http://localhost:8080/index.html this should boot up the home page like so.

![image](https://user-images.githubusercontent.com/107106989/184556657-52b89cf4-c30f-4dcf-9d86-911a2ecd027d.png)

This is a basic home page but if you click on the "Diary Manager" tab either at the top or bottom you should be greeted with this screen. 

![image](https://user-images.githubusercontent.com/107106989/184556675-493c7b1d-0e2b-4bb5-a445-511f1ea28cf8.png)

Here you can create a list and then add a task to that list. The system should show you the ID for the list so you can add tasks on. Have a look below for an example. 

![image](https://user-images.githubusercontent.com/107106989/184556714-1770961e-d15b-407e-8934-097b8f88ca7e.png)

Make sure when you do add tasks to the list that you put the ID number in for the list. In this case it would be the ID of 1 like below.

![image](https://user-images.githubusercontent.com/107106989/184556753-29c5b723-193a-4ce9-ba13-2ea6ea5852d0.png)

You can then view this list later down the line. 

![image](https://user-images.githubusercontent.com/107106989/184556780-04e87fd0-f1cc-43a0-a3e3-2a4890d12952.png)

Additionally if you have mulitple lists and have forgotten the ID number you can find it here. 

![image](https://user-images.githubusercontent.com/107106989/184556800-dbf24302-abb6-4abf-b206-2648ac276409.png)

Here you can update the title of your list. 

![image](https://user-images.githubusercontent.com/107106989/184556839-6d2f16ed-fa2d-4c10-a4ec-52a75618b3ec.png)

If you have the ID number you can also update any tasks in the list. 

![image](https://user-images.githubusercontent.com/107106989/184556859-4fc5ddc4-7047-4a93-b4a5-e1010b7a102a.png)

Finally you can delete any tasks or lists if you desire.

![image](https://user-images.githubusercontent.com/107106989/184556871-63f8de3e-1884-4764-9052-d0b620ccf0f9.png)


## Running the tests

### Setup

In Eclipse, in src/main/resources open application.properties and change profile to testing. 

All tests are within src/test/java.

![image](https://user-images.githubusercontent.com/107106989/184556904-6e37b2f0-f881-4240-aeb0-20f0cf4907d1.png)


### Unit Tests 

Pick any of the tests above that have Unit Testing at the end of the class name. These test check indiviual units of code and see if it works as intended. 

To do this, right click on the test folder and Coverage As> JUnit Test. 

![image](https://user-images.githubusercontent.com/107106989/184556959-858e07a6-9415-46ac-a2cd-c7bc6975cec1.png)


### Integration Tests 

Intergration Tests check if the codes speaks to other classes correctly and makes sures they are no errors. Similarily you can also run these like Unit tests. 

The coverage of all these test are at 99.5%

![image](https://user-images.githubusercontent.com/107106989/184556992-3fccb02e-af90-41f8-9369-5832c68968cf.png)

### Selenium Tests

Selenium Tests run differently to the others. This checks the front-end and ensures that it is working correctly by giving it a few test. To do this please check the com.qa. tasklist.selenium folder and run the TestMain.java class. Ensure that SpringBoot has been booted up so the tests can be ran correctly. You can see below what can be tested.  

![image](https://user-images.githubusercontent.com/107106989/184557074-79e30ac4-9887-46d3-a677-e0989e4cd515.png)



## Deployment

There should also be a jar file that can be ran from the main folder. 

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* Jabaran Khan 

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

BootStrap [BootStrap](https://github.com/twbs/bootstrap/blob/v4.0.0/LICENSE)*

## Acknowledgments

* Andrew McCall, Chris Yiangou, Aswene Sivaraj - Trainers for this project respectively 
* Youtube, StackOverflow and Google. 
