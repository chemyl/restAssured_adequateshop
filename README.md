## **Lightweight and powerful hybrid framework for API testing**
===================================================================
#### Under construction
The project contains a suitable framework for easily testing and validation various types of rest-based service APIs.
* =>>> CRUD Requests
* =>>> JSON Schema Validation
* =>>> POJO Configuration
* =>>> Clear Routes architecture
* =>>> DDT with XLS to POJO
* =>>> DDT with @DataProvider
* =>>> Allure Surefire Report
* =>>> TestNG.xml file configuration
* =>>> 

#### Based on:
Java 11; Maven ; TestNG 7.7.1; RestAssured 5.3.0; 

#### Project structure:
src/test/java:
  * /endpoints - contains Routes with all endpoints and ModuleEndPoints with specific implementation 
  * /payloads - may contain all necessary data and POJO classes
  * /test - test-case implementation using ModuleEndPoints methods 
  * /utilities - softeners and helpers

src/test/resources:
  * /schemas - files with json Schema originals 
  * /test_configs - additional framework configuration files

  * pom.xml - maven and dependencies configuration

#### Setting up:

1. Java 11 Required. goTo "Settings" > "Build, Execution, Deployment" > "Build Tools" > "Maven > Runner > JRE"

#### **Usage Examples:**
#### 2.1 Api testing workflow:
  * =>>> Go to Routes.class and create your services and endpoints URLs
  * =>>> Go to ModuleEndPoints.class and create Service/Manager methods with api implementation
  * =>>> Create required POJO
  * =>>> Go to /test create new testNG class and implement your test-cases by using Manager methods.
  * =>>> Implement your dataFiles if required and connect to tests
  * =>>> Configure testNG.xml file
  * =>>> Run mvn test -Dsurefire.suiteXmlFiles=src/test/resources/customerModel.xml 
  * =>>> Take and analyze Allure report
  * =>>> 
#### 2.2 Documentation/Links:
#### 2.3 License:

