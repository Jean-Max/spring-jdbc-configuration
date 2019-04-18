spring-jdbc-configuration
===============================
###1. Topic
- Accessing relational data with Spring JDBC
		
###2. To Run this project locally
- git clone https://github.com/Jean-Max/spring-jdbc-configuration
- mvn clean install -DskipTests
- Run main method as a java application

###3. To test this configuration
- By adding the method below, a Database Manager interface will be launched to help you to test your configuration.
~~~~
@PostConstruct
public void startDBManager() {
	DatabaseManagerSwing.main(new String[] { "--url", "jdbc:hsqldb:mem:testdb", "--user", "sa", "--password", "" });
}
~~~~