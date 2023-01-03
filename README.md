# cypher-cli Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

Build for windows
```shell script
mvn package -Pnative -Dquarkus.native.container-build=true -Dquarkus.native.builder-image=<image name>
```

You can then execute your native executable with: `./target/cypher-cli-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.


# demo

neo4j cypher-shell
```
cypher-shell -u neo4j -p test1234 "match (n) return count(*);"  
1.35s user 0.14s system 161% cpu 0.923 total
```

cypher-cli on graal vm native build
```
./target/cypher-cli-1.0.0-SNAPSHOT-runner -u neo4j -p test1234 -a neo4j://localhost:7687 "match (n) return count(*);"  
```
