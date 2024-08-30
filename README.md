Basic Jakarta RESTfull Web Service Using Apache CXF and GraalVM native compilation 
=================

A RESTful customer resource is provided on URL http://localhost:9000/customers and uses XML as data representation format. Please make sure that **JAVA_HOME** points to GraalVM Community distribution (https://github.com/graalvm/graalvm-ce-builds/releases/).

To build:
 - `mvn clean -Dnative test` - captures native image configuration
 - `mvn -Dnative -DskipTests package` - builds native image
 - `./target/cxf-jax-rs-graalvm-server` - runs native executable

To invoke HTTP endpoints (please make sure the native executable is running):
- to add a new customer

  ```  
  curl http://localhost:9000/customers -H "Content-Type: application/xml"  -d @src/test/resources/add_customer.xml 
  ```
     
- to list existing customers

  ```
  curl http://localhost:9000/customers
  ```

References:
- https://graalvm.github.io/native-build-tools/latest/maven-plugin.html
- https://docs.oracle.com/en/graalvm/jdk/21/docs/reference-manual/native-image/guides/use-reachability-metadata-repository-maven/
