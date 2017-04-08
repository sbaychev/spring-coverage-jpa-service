**How to Use and Deploy the Coverage Service Application**
----------------------------------------------

The Given Code Base Represents a Rest Client-Server communication that is on a single platform:

CoverageApplication - spring-coverage-jpa-service

Coverage Application takes 3 arguments REST call to exposed endpoint called `createCoverage`, running on port 8080 @localhost
----------------------------------------------
* The 3 arguments are as follow, a valid Double coverageAmount, a valid TypeOfCoverageIndex typeOfProduct (any of the predefined as String constants bike, jewelry, electronics, sportsEquipment) and a valid ComputeCoverageIndex computeCoverageIndex "basic" (as the currently only valid type of calculation for a Coverage available based on the default data pre-set within the CoverageApplication)
----------------------------------------------
- You can see the default Templates for the 4 types of Coverages and their parameters from the CoverageApplication.class
- Each Template Coverage for a product type has a minimum amount of to be insured amount, maximum amount of such and risk premium percentage that the Insurance Cover is based on as named to be "riskPremiumToBePaid" (in essence what Clients owes for the Insurance Coverage) that depends on the type of product and the method of calculation called computeCoverageIndex
    So the calculation components are limited by amount to be insured for requested by the Client, the risk Premium for the given Product type and that makes the riskPremiumToBePaid calculation as already said having just "basic" calculation method done over them two factors as a percentage
- The returned Object value is the name of the CoverageType being insured for and amount that was paid as calculated riskPremiumToBePaid

- The application runs within a Spring Boot, Spring JPA Persistence environment with runtime only DB available.

**Technologies Used:**
----------------------------------------------
1. Java 8
2. Spring Framework | Spring Boot with following modules | and other libraries
 - spring-boot-starter-actuator
 - spring-boot-starter-hateoas
 - spring-boot-starter-validation
 - spring-boot-starter-web
 - spring-boot-starter-data-jpa
 - spring-boot-starter-data-rest
 - com.h2database:h2
 - spring-boot-devtools
 - projectlombok:lombok
 - spring-boot-starter-test
 - spring-restdocs-mockmvc
3. Lombok
4. Gradle
----------------------------------------------

**!NB! Follow the below given as step by step instructions in order to run the Application(s)**
----------------------------------------------
`$ git clone https://github.com/sbaychev/spring-coverage-jpa-service.git`
`$ cd spring-coverage-jpa-service`

**Execute the gradlew shell command as is below, the script would run and execute, no need to have Gradle Installed**
----------------------------------------------
`$ ./gradlew build` (or for Windows `gradlew build`)

**Running the above first time, downloads the gradle 3.1.., downloads project dependencies, then it runs the gradle build task**

**Run the bootRun task - it would deploy and start the spring-coverage-jpa-service application on port 8080, localhost**
----------------------------------------------
`$ ./gradlew bootRun` (or for Windows `gradlew bootRun`)

**Execute the following curl command from within any shell terminal client - suggest using: '| python -m json.tool' (would need python installation if not already present) or other preferred means for display**
----------------------------------------------
`curl --data '{ "coverageAmount":"500.0", "typeOfProduct":"bike", "computeCoverageIndex":"basic" }' -v -X POST -H 'Content-Type:application/json' http://localhost:8080/createCoverage`

