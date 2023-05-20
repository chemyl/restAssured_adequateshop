Lightweight and powerful hybrid framework for API testing 
===================================================================
## Under construction
## **1. About:**
The project contains a suitable framework for easily testing various types of rest-based service APIs.
=>>> CRUD Requests
=>>> JSON Schema Validation
=>>> POJO Configuration
=>>> Clear Routes architecture
=>>> 
#### 1.2 Based on:
Java 11; Maven ; TestNG 7.7.1; RestAssured 5.3.0; 

#### 1.3 Project structure:
src/test/java:
* /endpoints - contains Routes with all endpoints and ModuleEndPoints with specific implementation 
* /payloads - may contain all necessary data and POJO classes
* /test - test-case implementation using ModuleEndPoints methods 
* /utilities - softeners and helpers

src/test/resources:
* /schemas - files with json Schema originals 
* /test_configs - additional framework configuration files

* pom.xml - maven and dependencies configuration

#### 1.4 Setting up:

1. Java 11 Required. goTo "Settings" > "Build, Execution, Deployment" > "Build Tools" > "Maven > Runner > JRE"

## **2. Usage Examples:**
#### 2.1 Api testing workflow:
#### 2.2 Documentation/Links:
#### 2.3 License: